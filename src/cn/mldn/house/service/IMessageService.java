package cn.mldn.house.service;

import java.util.List;
import java.util.Map;

import cn.mldn.house.vo.Message;

public interface IMessageService extends IService<Integer, Message> {
	public List<Message> listByConn(String toId,String fromId) throws Exception;
	public boolean insert(Message vo) throws Exception;
	public Message getSeq(Message vo) throws Exception;
	public Map<String, List<Message>>getUnViewMsg(String ruid) throws Exception;
}
