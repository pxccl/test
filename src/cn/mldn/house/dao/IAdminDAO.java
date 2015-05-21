package cn.mldn.house.dao;

import cn.mldn.house.vo.Admin;

public interface IAdminDAO extends IDAO<String, Admin> {
	public Admin findByIdAndPasswd(String adid, String password)
			throws Exception;

}
