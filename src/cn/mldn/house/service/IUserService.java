package cn.mldn.house.service;

import java.util.List;

import cn.mldn.house.vo.User;

public interface IUserService extends IService<String, User> {
	public boolean insert(User vo) throws Exception;

	public boolean update(User vo) throws Exception;
	
	public boolean noExistsUser(String uid) throws Exception;
	
	public boolean regeditUser(User vo) throws Exception;
	
	public User login(User vo) throws Exception;
	
	public boolean updatePasswd(User vo) throws Exception;
	
	public boolean updateLastLoginDate(String uid) throws Exception;
	
	public boolean updateUserDetails(User vo) throws Exception;
		
	public boolean isPerfact(User vo) throws Exception;
	
	public List<User> list(String order) throws Exception;
}
