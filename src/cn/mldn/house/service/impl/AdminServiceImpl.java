package cn.mldn.house.service.impl;

import cn.mldn.house.dbc.DatabaseConnection;
import cn.mldn.house.factory.DAOFactory;
import cn.mldn.house.service.IAdminService;
import cn.mldn.house.vo.Admin;

public class AdminServiceImpl implements IAdminService {
//	private DataSource dataSource = DataSource.getDataSource();
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public Admin login(String adid, String password) throws Exception {
		try {
			return DAOFactory.getAdminDAOImplInstance(this.dbc.getConnection()).findByIdAndPasswd(adid, password);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.getConnection().close();
		}
	}
	@Override
	public boolean update(Admin vo) throws Exception {
		try {
			return DAOFactory.getAdminDAOImplInstance(this.dbc.getConnection()).doUpdate(vo);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.getConnection().close();
		}
	}
	
}
