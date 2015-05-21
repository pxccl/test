package cn.mldn.house.service;

import cn.mldn.house.vo.Admin;

public interface IAdminService extends IService<String, Admin> {
	public boolean update(Admin vo) throws Exception;
	public Admin login(String adid, String password) throws Exception;
}
