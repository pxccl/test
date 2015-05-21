package cn.mldn.house.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.house.vo.Message;

public interface IMessageDAO extends IDAO<Integer, Message> {
	public List<Message> findAllByConn(String toId,String fromId) throws Exception;
	public Message getSeq(Message vo) throws Exception;
	public Map<String, List<Message>> getUnViewMsg(String ruid) throws Exception;
}
