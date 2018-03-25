package com.demodashi.base;

import javax.inject.Named;

import com.demodashi.aop.annotation.ServiceLogAnnotation;


@Named("userService")
public class UserServiceImpl implements UserService {
	
	private static UserVO user = null;
	
	//通过单例，初始化用户信息
	private UserVO getOneUser(){
		if(user == null){
			user = new UserVO();
			user.setId("1001");
			user.setName("tom");
			user.setPassword("123");
		}
		return user;
	}
	
	@Override
	public UserVO validate(String id, String password) {
		user = getOneUser(); 
		if(user.getId().equals(id) && user.getPassword().equals(password))
			return user;
		else
			return null;
	}

	@ServiceLogAnnotation(description = "修改密码")
	@Override
	public UserVO changePassword(UserVO vo, String newPassword) {
		vo.setPassword(newPassword);
		return vo;
	}

}
