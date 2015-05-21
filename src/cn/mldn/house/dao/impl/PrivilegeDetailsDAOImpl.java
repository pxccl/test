package cn.mldn.house.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.house.dao.IPrivilegeDetailsDAO;
import cn.mldn.house.vo.PrivilegeDetails;

public class PrivilegeDetailsDAOImpl implements IPrivilegeDetailsDAO {
	private Connection connection;
	private PreparedStatement pStatement;
	
	public PrivilegeDetailsDAOImpl(Connection connection) {
		this.connection = connection;
	}
	@Override
	public boolean doCreate(PrivilegeDetails vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(PrivilegeDetails vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PrivilegeDetails findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PrivilegeDetails> findAll(String order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PrivilegeDetails> findAll(String column, String keyWord,
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
	public List<PrivilegeDetails> findByPid(Integer pid) throws Exception {
		return null;
	}
	@Override
	public List<PrivilegeDetails> findByRid(Integer rid) throws Exception {
		List<PrivilegeDetails> data = new ArrayList<PrivilegeDetails>();
		String sql = "SELECT pdid,pid,title,url FROM privilege_details WHERE pid IN (SELECT pid FROM ROLE_PRIVILEGE WHERE rid=?)";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setInt(1, rid);
		ResultSet rs = this.pStatement.executeQuery();
		while (rs.next()) {
			PrivilegeDetails privilegeDetails = new PrivilegeDetails();
			privilegeDetails.setPdid(rs.getInt(1));
			privilegeDetails.getPrivilege().setPid(rs.getInt(2));
			privilegeDetails.setTitle(rs.getString(3));
			privilegeDetails.setUrl(rs.getString(4));
			data.add(privilegeDetails);
		}
		return data;
	}

}
