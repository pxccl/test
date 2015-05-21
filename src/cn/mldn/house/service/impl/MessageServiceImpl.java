package cn.mldn.house.service.impl;

import java.util.List;
import java.util.Map;

import cn.mldn.house.dbc.DatabaseConnection;
import cn.mldn.house.factory.DAOFactory;
import cn.mldn.house.service.IMessageService;
import cn.mldn.house.vo.Message;

public class MessageServiceImpl implements IMessageService {
	private DatabaseConnection dbc = new DatabaseConnection();
	
	@Override
	public List<Message> listByConn(String toId,String fromId) throws Exception {
		try {
			return DAOFactory.getMessageDAOInstance(this.dbc.getConnection()).findAllByConn(toId,fromId);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public boolean insert(Message vo) throws Exception {
		try {
			return DAOFactory.getMessageDAOInstance(this.dbc.getConnection()).doCreate(vo);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public Message getSeq(Message vo) throws Exception {
		try {
			return DAOFactory.getMessageDAOInstance(this.dbc.getConnection()).getSeq(vo);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public Map<String, List<Message>> getUnViewMsg(String ruid)
			throws Exception {
		try {
			return DAOFactory.getMessageDAOInstance(this.dbc.getConnection()).getUnViewMsg(ruid);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}
	

}
