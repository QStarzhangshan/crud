package com.zs.CRUD_3.service;

import java.util.List;

import com.zs.CRUD_3.domain.Client;
import com.zs.CRUD_3.domain.User;


public interface UserService {

	List<User> findAll();

	int addUser(User user);

	
	int update(User user);

	List<User> findById(int id);

	User findUserByUsername(String username);

	boolean usernameisexit(String username);

	boolean findByUsernameAndMail(String username, String mail);

	List<Client> findClientByUser_number(User user);

	void addClient(Client client,User user);

	Client findClientById(Integer id);

	void UserState(int i,String username);

	void updateClient(Client client);

	

	

}