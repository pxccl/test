package cn.mldn.house.service;

import java.util.List;

import cn.mldn.house.vo.House;

public interface IHouseService extends IService<Integer, House> {
	public boolean insert(House vo) throws Exception;

	public List<House> list(String uid) throws Exception;
}
