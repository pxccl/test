package cn.mldn.house.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import cn.mldn.house.dao.IAdminDAO;
import cn.mldn.house.vo.Admin;

public class AdminDAOImpl implements IAdminDAO {
	private Connection connection ;
	private PreparedStatement pStatement ;

	public AdminDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean doCreate(Admin vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Admin vo) throws Exception {
		String sql = "update admin set rid=?,password=?,lastlogin=?,flag=? where adid=?";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setInt(1, vo.getRole().getRid());
		this.pStatement.setString(2, vo.getPassword());
		this.pStatement.setTimestamp(3, new Timestamp(vo.getLastlogin().getTime()));
		this.pStatement.setInt(4, vo.getFlag());
		if (this.pStatement.executeUpdate() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<String> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Admin findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findAll(String order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findAll(String column, String keyWord,
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
	public Admin findByIdAndPasswd(String adid, String password)
			throws Exception {
		String sql = "SELECT adid,rid,password,lastlogin,flag from admin where adid=? and password=?";
		pStatement = this.connection.prepareStatement(sql);
		pStatement.setString(1, adid);
		pStatement.setString(2, password);
		ResultSet rSet = pStatement.executeQuery();
		Admin admin = new Admin();
		while (rSet.next()) {
			admin.setAdid(rSet.getString(1));
			admin.getRole().setRid(rSet.getInt(2));
			admin.setPassword(rSet.getString(3));
			admin.setLastlogin(rSet.getTimestamp(4));
			admin.setFlag(rSet.getInt(5));
		}
		return admin;
	}

}
