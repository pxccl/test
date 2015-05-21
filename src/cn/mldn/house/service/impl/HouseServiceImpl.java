package cn.mldn.house.service.impl;

import java.util.List;

import cn.mldn.house.dbc.DatabaseConnection;
import cn.mldn.house.factory.DAOFactory;
import cn.mldn.house.service.IHouseService;
import cn.mldn.house.vo.House;

public class HouseServiceImpl implements IHouseService {
	private DatabaseConnection dbc = new DatabaseConnection();
	
	@Override
	public boolean insert(House vo) throws Exception {
		try {
			return DAOFactory.getHouseDAOInstance(this.dbc.getConnection()).doCreate(vo);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}

	@Override
	public List<House> list(String uid) throws Exception {
		try {
			return DAOFactory.getHouseDAOInstance(this.dbc.getConnection()).findAllByUid(uid);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
	}
}
