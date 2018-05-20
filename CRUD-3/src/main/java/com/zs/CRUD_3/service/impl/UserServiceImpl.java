package com.zs.CRUD_3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.CRUD_3.dao.UserDao;
import com.zs.CRUD_3.domain.Client;
import com.zs.CRUD_3.domain.User;
import com.zs.CRUD_3.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	public List<User> findAll() {
		List<User> users =  userDao.findall();
		return users;
	}

	public int addUser(User user) {
		return userDao.addUser(user);
	}

	public int update(User user) {
		return userDao.update(user);
		
		}


	public List<User> findById(int id) {
		return userDao.findById(id);
		}


	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
		}

	@Override
	public boolean usernameisexit(String username) {
		return userDao.usernameisexit(username);
	}

	@Override
	public boolean findByUsernameAndMail(String username, String mail) {
		return userDao.findByUsernameAndMail(username,mail);
	}

	@Override
	public List<Client> findClientByUser_number(User user) {
		return userDao.findClientByUser_number(user);
		
	}

	@Override
	public void addClient(Client client,User user) {
		userDao.addClient(client,user);
	}

	@Override
	public Client findClientById(Integer id) {
		return userDao.findClientById(id);
	}

	@Override
	public void UserState(int i,String username) {
		userDao.UserState(i,username);
		
	}

	@Override
	public void updateClient(Client client) {
		userDao.updateClient(client);
	}



	

	

	

}
