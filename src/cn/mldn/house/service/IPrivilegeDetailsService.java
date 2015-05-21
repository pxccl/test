package cn.mldn.house.service;

import java.util.List;

import cn.mldn.house.vo.PrivilegeDetails;

public interface IPrivilegeDetailsService extends
		IService<Integer, PrivilegeDetails> {
	public List<PrivilegeDetails> listByRid(int rid) throws Exception;
}
