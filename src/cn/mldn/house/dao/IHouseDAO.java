package cn.mldn.house.dao;

import java.util.List;

import cn.mldn.house.vo.House;

public interface IHouseDAO extends IDAO<Integer, House> {
	public List<House> findAllByUid(String uid) throws Exception;
}
