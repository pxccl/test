package cn.mldn.house.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.house.dao.IPrivilegeDAO;
import cn.mldn.house.vo.Privilege;

public class PrivilegeDAOImpl implements IPrivilegeDAO {
	private Connection connection;
	private PreparedStatement pStatement;
	public PrivilegeDAOImpl(Connection connection) {
		this.connection = connection;
	}
	@Override
	public boolean doCreate(Privilege vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Privilege vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Privilege findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Privilege> findAll(String order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Privilege> findAll(String column, String keyWord,
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
	public List<Privilege> findByRid(Integer rid) throws Exception {
		List<Privilege> data = new ArrayList<Privilege>();
		String sql = "SELECT pid,title,note FROM privilege WHERE pid IN (SELECT pid FROM ROLE_PRIVILEGE WHERE rid=?)";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setInt(1, rid);
		ResultSet rs = this.pStatement.executeQuery();
		while (rs.next()) {
			Privilege privilege = new Privilege();
			privilege.setPid(rs.getInt(1));
			privilege.setTitle(rs.getString(2));
			privilege.setNote(rs.getString(3));
			data.add(privilege);
		}
		return data;
	}

}
