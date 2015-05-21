package cn.mldn.house.servlet.front;

import java.util.Iterator;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.mldn.house.factory.ServiceFactory;
import cn.mldn.house.util.action.DispatcherServlet;
import cn.mldn.house.vo.Message;
import cn.mldn.house.vo.User;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/page/front/user/msgServlet/*")
public class MsgServlet extends DispatcherServlet {
	private Message message = new Message();
	public String initMsgRecorder(){
		String fromId = super.getRequest().getParameter("toId");
		String toId = super.getRequest().getParameter("fromId");
		try {
			JSONObject data = new JSONObject();
			List<Message> msgs = ServiceFactory.getMessageServiceInstance().listByConn(toId, fromId);
			Iterator<Message> iterator = msgs.iterator();
			JSONArray jArray = new JSONArray();
			while (iterator.hasNext()) {
				Message message = iterator.next();
				JSONObject temp = new JSONObject();
				temp.put("mid", message.getMid());
				temp.put("suid", message.getSuser().getUid());
				temp.put("ruid", message.getRuser().getUid());
				temp.put("title", message.getTitle());
				temp.put("conn", message.getClass());
				temp.put("pubdate", message.getPubdate());
				temp.put("status", message.getStatus());
				temp.put("seq", message.getSeq());
				jArray.add(temp);
			}
			data.put("allRecorderMessage", jArray);
			super.getResponse().setCharacterEncoding("utf-8");
			super.getResponse().getWriter().print(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json";
	}
	
	public String initMsgPage(){
		try {
			User user = (User) super.getSession().getAttribute("signUser");
			List<User> users = ServiceFactory.getUserServiceInstance().list("");
			System.out.println(users);
			super.getRequest().setAttribute("user", user);
			super.getRequest().setAttribute("users", users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user.msg.page";
	}
	@Override
	public String getObjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnData() {
		// TODO Auto-generated method stub
		return null;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}

}
