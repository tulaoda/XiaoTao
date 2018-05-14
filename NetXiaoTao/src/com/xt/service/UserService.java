package com.xt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xt.dao.UserDao;
import com.xt.entity.User;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User login(User user) {
		return userDao.login(user);
	}

	public boolean register(User user) {
		if (!userDao.exits(user)) {
			userDao.addNewUser(user);
			return true;
		} else {
			return false;
		}
	}

	public boolean modifyPass(User user) {
		return userDao.modifyPass(user) > 0 ? true : false;
	}

	public boolean modifyAddress(User user) {
		return userDao.modifyAddress(user) > 0 ? true : false;
	}

	public boolean modifyName(User user) {
		return userDao.modifyName(user) > 0 ? true : false;
	}
}
