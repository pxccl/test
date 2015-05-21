package cn.mldn.house.service.impl;

import java.util.List;

import cn.mldn.house.dbc.DatabaseConnection;
import cn.mldn.house.factory.DAOFactory;
import cn.mldn.house.service.IPrivilegeService;
import cn.mldn.house.vo.Privilege;

public class PrivilegeServiceImpl implements IPrivilegeService {
//	private DataSource dataSource = DataSource.getDataSource();
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public List<Privilege> listByRid(int rid) throws Exception {
		try {
			return DAOFactory.getPrivilegeDAOImplInstance(this.dbc.getConnection()).findByRid(rid);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.getConnection().close();
		}
	}

}
