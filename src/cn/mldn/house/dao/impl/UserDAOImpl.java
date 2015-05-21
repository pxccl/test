package cn.mldn.house.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.mldn.house.dao.IUserDAO;
import cn.mldn.house.vo.User;

public class UserDAOImpl implements IUserDAO {
	private Connection connection;
	private PreparedStatement pStatement;
	
	public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}
	@Override
	public boolean doCreate(User vo) throws Exception {
		String sql = "INSERT INTO user(password,phone,photo,regdate,lastLoginDate,card,address,status,name,uid) VALUES(?,?,?,?,?,?,?,?,?)";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1, vo.getPassword());
		this.pStatement.setString(2, vo.getPhone());
		this.pStatement.setString(3, vo.getPhoto());
		this.pStatement.setTimestamp(4, new Timestamp(vo.getRegdate().getTime()));
		this.pStatement.setTimestamp(5, new Timestamp(vo.getLastLoginDate().getTime()));
		this.pStatement.setString(6, vo.getCard());
		this.pStatement.setString(7, vo.getAddress());
		this.pStatement.setInt(8, vo.getStatus());
		this.pStatement.setString(9, vo.getName());
		this.pStatement.setString(10,vo.getUid());
		return this.pStatement.executeUpdate()>0;
	}

	@Override
	public boolean doUpdate(User vo) throws Exception {
		String sql = "UPDATE user SET phone=?,photo=?,regdate=?,lastLoginDate=?,card=?,address=?,status=?,name=? WHERE uid=? and password=?";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1, vo.getPhone());
		this.pStatement.setString(2, vo.getPhoto());
		this.pStatement.setTimestamp(3, new Timestamp(vo.getRegdate().getTime()));
		this.pStatement.setTimestamp(4, new Timestamp(vo.getLastLoginDate().getTime()));
		this.pStatement.setString(5, vo.getCard());
		this.pStatement.setString(6, vo.getAddress());
		this.pStatement.setInt(7, vo.getStatus());
		this.pStatement.setString(8, vo.getName());
		this.pStatement.setString(9, vo.getUid());
		this.pStatement.setString(10, vo.getPassword());
		return this.pStatement.executeUpdate()>0;
	}

	@Override
	public boolean doRemoveBatch(Set<String> ids) throws Exception {
		return false;
	}

	@Override
	public User findById(String id) throws Exception {
		String sql = "SELECT password,phone,photo,regdate,lastLoginDate,card,address,status,name FROM user WHERE uid=?";
		User user = null;
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1,id);
		ResultSet rSet = this.pStatement.executeQuery();
		if (rSet.next()) {
			user = new User();
			user.setUid(id);
			user.setPassword(rSet.getString(1));
			user.setPhone(rSet.getString(2));
			user.setPhoto(rSet.getString(3));
			user.setRegdate(rSet.getTimestamp(4));
			user.setLastLoginDate(rSet.getTimestamp(5));
			user.setCard(rSet.getString(6));
			user.setAddress(rSet.getString(7));
			user.setStatus(rSet.getInt(8));
			user.setName(rSet.getString(9));
		}
		return user;
	}

	@Override
	public List<User> findAll(String order) throws Exception {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT password,phone,photo,regdate,lastLoginDate,card,address,status,name,uid FROM user where status=1";
		this.pStatement = this.connection.prepareStatement(sql);
		ResultSet rSet = this.pStatement.executeQuery();
		while (rSet.next()) {
			User user= new User();
			user.setPassword(rSet.getString(1));
			user.setPhone(rSet.getString(2));
			user.setPhoto(rSet.getString(3));
			user.setRegdate(rSet.getTimestamp(4));
			user.setLastLoginDate(rSet.getTimestamp(5));
			user.setCard(rSet.getString(6));
			user.setAddress(rSet.getString(7));
			user.setStatus(rSet.getInt(8));
			user.setName(rSet.getString(9));
			user.setUid(rSet.getString(10));
			users.add(user);
		}
		return users;
	}

	@Override
	public List<User> findAll(String column, String keyWord,
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
	public boolean doCreateRegeDit(User user) throws Exception {
		String sql = "INSERT INTO user(uid,password,status) VALUES(?,?,1)";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1, user.getUid());
		this.pStatement.setString(2, user.getPassword());
		return this.pStatement.executeUpdate()>0;
	}
	@Override
	public User findByIdAndPasswd(User vo)
			throws Exception {
		String sql = "SELECT phone,photo,regdate,lastLoginDate,card,address,status,name FROM user  where uid=? and password=? ";
		pStatement = this.connection.prepareStatement(sql);
		pStatement.setString(1, vo.getUid());
		pStatement.setString(2, vo.getPassword());
		ResultSet rSet = pStatement.executeQuery();
		User user = null;
		if (rSet.next()) {
			user = new User();
			user.setUid(vo.getUid());
			user.setPassword(vo.getPassword());
			user.setPhone(rSet.getString(1));
			user.setPhoto(rSet.getString(2));
			user.setRegdate(rSet.getTimestamp(3));
			user.setLastLoginDate(rSet.getTimestamp(4));
			user.setCard(rSet.getString(5));
			user.setAddress(rSet.getString(6));
			user.setStatus(rSet.getInt(7));
			user.setName(rSet.getString(8));
		}
		return user;

	}
	@Override
	public boolean doUpdatePasswd(User vo) throws Exception {
		String sql = "UPDATE user SET password=? WHERE uid=?";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1, vo.getPassword());
		this.pStatement.setString(2, vo.getUid());
		
		return this.pStatement.executeUpdate()>0;
	}
	@Override
	public boolean doUpdateLastLoginDate(String uid) throws Exception {
		String sql = "UPDATE user SET lastLoginDate=? WHERE uid=?";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
		this.pStatement.setString(2, uid);
		return this.pStatement.executeUpdate()>0;
	}
	@Override
	public boolean doUpdateUserDetails(User vo) throws Exception {
		String sql = "UPDATE user SET phone=?,photo=?,card=?,address=?,name=? WHERE uid=? and password=?";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1, vo.getPhone());
		this.pStatement.setString(2, vo.getPhoto());
		this.pStatement.setString(3, vo.getCard());
		this.pStatement.setString(4, vo.getAddress());
		this.pStatement.setString(5, vo.getName());
		this.pStatement.setString(6, vo.getUid());
		this.pStatement.setString(7, vo.getPassword());
		return this.pStatement.executeUpdate()>0;
	}
	@Override
	public boolean isPerfact(User vo) throws Exception {
		String sql = "SELECT password,phone,photo,regdate,lastLoginDate,card,address,status,name FROM user WHERE uid=?";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1,vo.getUid());
		ResultSet rSet = this.pStatement.executeQuery();
		if (rSet.next()) {
			if(rSet.getString(1) == null||"".equals(rSet.getString(1))){
				return false;
			}
			if(rSet.getString(2) == null||"".equals(rSet.getString(2))){
				return false;
			}
			if(rSet.getString(3) == null||"".equals(rSet.getString(3))||"nophoto.jpg".equals(rSet.getString(3))){
				return false;
			}
			if(rSet.getString(6) == null||"".equals(rSet.getString(6))){
				return false;
			}
			if(rSet.getString(7) == null||"".equals(rSet.getString(7))){
				return false;
			}
			if(rSet.getString(9) == null||"".equals(rSet.getString(9))){
				return false;
			}
		}
		return true;
	}

}
