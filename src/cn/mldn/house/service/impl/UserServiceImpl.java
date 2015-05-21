package cn.mldn.house.service.impl;

import java.util.List;

import cn.mldn.house.dbc.DatabaseConnection;
import cn.mldn.house.factory.DAOFactory;
import cn.mldn.house.service.IUserService;
import cn.mldn.house.vo.User;

public class UserServiceImpl implements IUserService {
	private DatabaseConnection dbc = new DatabaseConnection();
	
	@Override
	public boolean insert(User vo) throws Exception {
		try {
			return DAOFactory.getUserDAOInstance(this.dbc.getConnection()).doCreate(vo);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public boolean update(User vo) throws Exception {
		try {
			return DAOFactory.getUserDAOInstance(this.dbc.getConnection()).doUpdate(vo);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public boolean noExistsUser(String uid) throws Exception {
		try {
			return DAOFactory.getUserDAOInstance(this.dbc.getConnection()).findById(uid)==null;
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public boolean regeditUser(User vo) throws Exception {
		try {
			return DAOFactory.getUserDAOInstance(this.dbc.getConnection()).doCreateRegeDit(vo);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public User login(User vo) throws Exception {
		try {
			return DAOFactory.getUserDAOInstance(this.dbc.getConnection()).findByIdAndPasswd(vo);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public boolean updatePasswd(User vo) throws Exception {
		try {
			return DAOFactory.getUserDAOInstance(this.dbc.getConnection()).doUpdatePasswd(vo);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public boolean updateLastLoginDate(String uid) throws Exception {
		try {
			return DAOFactory.getUserDAOInstance(this.dbc.getConnection()).doUpdateLastLoginDate(uid);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public boolean updateUserDetails(User vo) throws Exception {
		try {
			return DAOFactory.getUserDAOInstance(this.dbc.getConnection()).doUpdateUserDetails(vo);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public boolean isPerfact(User vo) throws Exception {
		try {
			return DAOFactory.getUserDAOInstance(this.dbc.getConnection()).isPerfact(vo);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public List<User> list(String order) throws Exception {
		try {
			return DAOFactory.getUserDAOInstance(this.dbc.getConnection()).findAll(order);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

}
