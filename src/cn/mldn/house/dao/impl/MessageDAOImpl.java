package cn.mldn.house.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.house.dao.IMessageDAO;
import cn.mldn.house.vo.Message;

public class MessageDAOImpl implements IMessageDAO {
	private Connection connection ;
	private PreparedStatement pStatement;
	public MessageDAOImpl(Connection connection) {
		this.connection = connection;
	}
	@Override
	public boolean doCreate(Message vo) throws Exception {
		String sql = "INSERT INTO message (suid,ruid,title,note,status,pubdate,seq,conn) VALUES (?,?,?,?,?,?,?,?)";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1, vo.getSuser().getUid());
		this.pStatement.setString(2, vo.getRuser().getUid());
		this.pStatement.setString(3, vo.getTitle());
		this.pStatement.setString(4, vo.getNote());
		this.pStatement.setInt(5, vo.getStatus());
		this.pStatement.setTimestamp(6, new Timestamp(vo.getPubdate().getTime()));
		this.pStatement.setInt(7, vo.getSeq());
		this.pStatement.setString(8, vo.getConn());
		return this.pStatement.executeUpdate()>0;
		
	}

	@Override
	public boolean doUpdate(Message vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Message findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findAll(String order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize, String order)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findAllByConn(String toId,String fromId) throws Exception {
		List<Message> msgs = new ArrayList<Message>();
		String sql = "SELECT mid,suid,ruid,title,note,status,pubdate,seq,conn FROM message Where conn=? or conn=? order by seq";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1, toId.concat(fromId));
		this.pStatement.setString(2, fromId.concat(toId));
		ResultSet rs = this.pStatement.executeQuery();
		while (rs.next()) {
			Message msg = new Message();
			msg.setMid(rs.getInt(1));
			msg.getSuser().setUid(rs.getString(2));
			msg.getRuser().setUid(rs.getString(3));
			msg.setTitle(rs.getString(4));
			msg.setNote(rs.getString(5));
			msg.setStatus(rs.getInt(6));
			msg.setPubdate(rs.getTimestamp(7));
			msg.setSeq(rs.getInt(8));
			msg.setConn(rs.getString(9));
			msgs.add(msg);
		}
		return msgs;
	}
	@Override
	public Message getSeq(Message vo) throws Exception {
		String sql = "SELECT MAX(mid) FROM message Where conn=? or conn=? ";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1, vo.getSuser().getUid().concat(vo.getRuser().getUid()));
		this.pStatement.setString(2, vo.getRuser().getUid().concat(vo.getSuser().getUid()));
		ResultSet rs = this.pStatement.executeQuery();
		if (rs.next()) {
			vo.setSeq(rs.getInt(1));
		}
		return vo;
	}
	@Override
	public Map<String, List<Message>> getUnViewMsg(String ruid) throws Exception {
		Map<String, List<Message>> data = new HashMap<String, List<Message>>();
		String sql = "SELECT mid,suid,ruid,title,note,status,pubdate,seq,conn FROM message Where ruid=? and status=0 order by seq";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1,ruid);
		ResultSet rs = this.pStatement.executeQuery();
		while (rs.next()) {
			Message msg = new Message();
			msg.setMid(rs.getInt(1));
			msg.getSuser().setUid(rs.getString(2));
			String ruidStr = rs.getString(3);
			msg.getRuser().setUid(ruidStr);
			msg.setTitle(rs.getString(4));
			msg.setNote(rs.getString(5));
			msg.setStatus(rs.getInt(6));
			msg.setPubdate(rs.getTimestamp(7));
			msg.setSeq(rs.getInt(8));
			msg.setConn(rs.getString(9));
			if (data.containsKey(ruidStr)) {
				data.get(ruidStr).add(msg);
			}else {
				List<Message> list = new ArrayList<Message>();
				list.add(msg);
				data.put(ruidStr, list);
			}
		}
		return data;
		
	}

}
