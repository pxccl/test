package cn.mldn.house.dao;

import cn.mldn.house.vo.User;

public interface IUserDAO extends IDAO<String, User> {
	public boolean doCreateRegeDit(User user) throws Exception;

	public User findByIdAndPasswd(User vo) throws Exception;

	public boolean doUpdatePasswd(User vo) throws Exception;

	public boolean doUpdateLastLoginDate(String uid) throws Exception;

	public boolean doUpdateUserDetails(User vo) throws Exception;
	
	public boolean isPerfact(User vo) throws Exception;
}
