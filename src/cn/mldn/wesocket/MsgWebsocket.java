package cn.mldn.wesocket;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import cn.mldn.house.factory.ServiceFactory;
import cn.mldn.house.vo.Message;


@ServerEndpoint(value = "/PxcInWebsocket")
public class MsgWebsocket {
	
    private static final Map<String,MsgWebsocket> connections = new HashMap<String, MsgWebsocket>();

    private Session session;
    
    public MsgWebsocket() {
    }
    //private static final Logger sysLogger = Logger.getLogger("sysLog");
    @OnOpen
    public void open(Session session,EndpointConfig config) {
    	 this.session = session;
    	 String pathParam []= session.getRequestURI().toString().split(";");
    	 System.out.println("connection:srcKey"+pathParam[1]);
         connections.put(pathParam[1], this);
    }
    @OnMessage
    public void inMessage(Session session,String message) {
        //sysLogger.info("*** WebSocket Received from sessionId " + this.session.getId() + ": " + message);
       System.out.println("PxcInWebSocket:"+message);
       String pathParam []= session.getRequestURI().toString().split(";");
        try {
        	String params[] = message.split(";");
            //session.getBasicRemote().sendText("WebSocket develop Success!");
            broadcast(pathParam[1],params[1],params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnClose
    public void end(Session session) {
    	Set<Entry<String, MsgWebsocket>> sets = connections.entrySet();
    	Iterator<Entry<String, MsgWebsocket>> iterator = sets.iterator();
    	while (iterator.hasNext()) {
			Entry<String, MsgWebsocket> entry = iterator.next();
			if (entry.getValue() == this) {
				connections.remove(entry.getKey());
				System.out.println("unconnection:srcKey"+entry.getKey());
			}
		}
        //sysLogger.info("*** WebSocket closed from sessionId " + this.session.getId());
    }
    private void broadcast(String srcSessionKey,String msg,String distSessionKey) {
    	if (distSessionKey == null||"".equals(distSessionKey)) {
			return ;
		}
    	Set<Entry<String, MsgWebsocket>> sets = connections.entrySet();
    	Iterator<Entry<String, MsgWebsocket>> iterator = sets.iterator();
    	try {
			this.session.getBasicRemote().sendText(srcSessionKey+": "+msg);
			Message message = new Message();
			message.setConn(srcSessionKey.concat(distSessionKey));
			message.setPubdate(new Date());
			message.setNote(msg);
			message.getSuser().setUid(srcSessionKey);;
			message.getRuser().setUid(distSessionKey);
			message.setStatus(0);
			message.setTitle(msg);
			
    	while (iterator.hasNext()) {
			Entry<String, MsgWebsocket> entry = iterator.next();
			String key = entry.getKey();
			System.out.println("disKey"+key);
			if (distSessionKey.equals(key)) {
				//connections.remove(entry.getKey());
				try {
					synchronized (entry.getValue()) {
						entry.getValue().session.getBasicRemote().sendText(srcSessionKey+": "+msg);
						message.setStatus(1);
					}
				} catch (Exception e) {
					connections.remove(entry.getKey());
					try {
						entry.getValue().session.close();
					} catch (Exception e1) {
						System.out.println("Pxc - websocket has been colosed!");
					}
				}
			}
		}
    	ServiceFactory.getMessageServiceInstance().insert(ServiceFactory.getMessageServiceInstance().getSeq(message));
		} catch (Exception e2) {
			//e2.printStackTrace();
		}
    }
}