package cn.mldn.house.dao.impl;

import java.util.List;
import java.util.Set;

import cn.mldn.house.dao.IRoleDAO;
import cn.mldn.house.vo.Role;

public class RoleDAOImpl implements IRoleDAO {
	//private Connection connection;
	//private PreparedStatement pStatement;
	//public RoleDAOImpl(Connection connection) {
		//this.connection = connection;
	//}
	@Override
	public boolean doCreate(Role vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Role vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Role findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll(String order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll(String column, String keyWord,
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

}
