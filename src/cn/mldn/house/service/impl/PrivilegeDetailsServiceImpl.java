package cn.mldn.house.service.impl;

import java.util.List;

import cn.mldn.house.dbc.DatabaseConnection;
import cn.mldn.house.factory.DAOFactory;
import cn.mldn.house.service.IPrivilegeDetailsService;
import cn.mldn.house.vo.PrivilegeDetails;

public class PrivilegeDetailsServiceImpl implements IPrivilegeDetailsService {
//	private DataSource dataSource = DataSource.getDataSource();
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public List<PrivilegeDetails> listByRid(int rid) throws Exception {
		try {
			return DAOFactory.getPrivilegeDetailsDAO(this.dbc.getConnection()).findByRid(rid);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.getConnection().close();
		}
	}

}
