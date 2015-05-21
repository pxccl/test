package cn.mldn.house.dao;

import java.util.List;

import cn.mldn.house.vo.PrivilegeDetails;

public interface IPrivilegeDetailsDAO extends IDAO<Integer, PrivilegeDetails> {
	public List<PrivilegeDetails> findByPid(Integer pid) throws Exception;

	public List<PrivilegeDetails> findByRid(Integer rid) throws Exception;
}
