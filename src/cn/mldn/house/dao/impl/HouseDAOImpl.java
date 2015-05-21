package cn.mldn.house.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.mldn.house.dao.IHouseDAO;
import cn.mldn.house.vo.House;

public class HouseDAOImpl implements IHouseDAO {
	private Connection connection;
	private PreparedStatement pStatement;
	
	public HouseDAOImpl(Connection connection) {
		this.connection = connection;
	}
	@Override
	public boolean doCreate(House vo) throws Exception {
		String sql = "INSERT into house (uid,community,address,area,total, "
				+ " floor,orientation,structure,renovation,fdate,status,price, "
				+ " visits,type,note) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1, vo.getUser().getUid());
		this.pStatement.setString(2, vo.getCommunity());
		this.pStatement.setString(3, vo.getAddress());
		this.pStatement.setDouble(4, vo.getArea());
		this.pStatement.setString(5, vo.getTotal());
		this.pStatement.setString(6, vo.getFloor());
		this.pStatement.setString(7, vo.getOrientation());
		this.pStatement.setString(8, vo.getStructure());
		this.pStatement.setString(9, vo.getRenovation());
		this.pStatement.setTimestamp(10, new Timestamp(new Date().getTime()));
		this.pStatement.setInt(11,vo.getStatus());
		this.pStatement.setDouble(12, vo.getPrice());
		this.pStatement.setInt(13, vo.getVisits());
		this.pStatement.setString(14, vo.getType());
		this.pStatement.setString(15, vo.getNote());
		return this.pStatement.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(House vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public House findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<House> findAll(String order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<House> findAll(String column, String keyWord,
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
	public List<House> findAllByUid(String uid) throws Exception {
		List<House> data = new ArrayList<House>();
		String sql = "SELECT hsid,uid,community,address,area,total,floor,orientation,structure,renovation,fdate,status,price,visits,type,note FROM house WHERE uid=?";
		this.pStatement = this.connection.prepareStatement(sql);
		this.pStatement.setString(1, uid);
		ResultSet rSet = this.pStatement.executeQuery();
		while (rSet.next()) {
			House house = new House();
			house.setHsid(rSet.getInt(1));
			house.getUser().setUid(rSet.getString(2));
			house.setCommunity(rSet.getString(3));
			house.setAddress(rSet.getString(4));
			house.setArea(rSet.getDouble(5));
			house.setTotal(rSet.getString(6));
			house.setFloor(rSet.getString(7));
			house.setOrientation(rSet.getString(8));
			house.setStructure(rSet.getString(9));
			house.setRenovation(rSet.getString(10));
			house.setFdate(rSet.getTimestamp(11));
			house.setStatus(rSet.getInt(12));
			house.setPrice(rSet.getDouble(13));
			house.setVisits(rSet.getInt(14));
			house.setType(rSet.getString(15));
			house.setNote(rSet.getString(16));
			data.add(house);
		}
		return data;
				
	}

}
