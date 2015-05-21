package cn.mldn.house.service;

import java.util.List;

import cn.mldn.house.vo.Privilege;

public interface IPrivilegeService extends IService<Integer, Privilege> {
	public List<Privilege> listByRid(int rid) throws Exception;
}
