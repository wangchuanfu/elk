package com.demodashi.base;

/**
 * 
 * @author xgchen
 *
 */
public interface UserService {
	
	UserVO validate(String id, String password);
	
	UserVO changePassword(UserVO vo, String oldPassword);
}
