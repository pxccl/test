package cn.mldn.house.dao;

import java.util.List;

import cn.mldn.house.vo.Privilege;

public interface IPrivilegeDAO extends IDAO<Integer, Privilege> {
	public List<Privilege> findByRid(Integer rid) throws Exception;
}
