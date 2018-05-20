package com.zs.CRUD_3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.CRUD_3.dao.ClientDao;
import com.zs.CRUD_3.dao.UserDao;
import com.zs.CRUD_3.domain.Client;
import com.zs.CRUD_3.domain.Payment;
import com.zs.CRUD_3.service.ClientService;
import com.zs.CRUD_3.util.Page;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	ClientDao clientDao;
	
	@Override
	public void add(Client client) {
		clientDao.add(client);
	}

	@Override
	public List<Client> findAll() {
		return clientDao.findAll();
	}

	@Override
	public List<Client> findByRtime(String time1, String time2) {
		return clientDao.findByRtime(time1,time2);
	}

	@Override
	public List<Client> Fuzzyquery(String str1) {
		return clientDao.Fuzzyquery(str1);
	}

	@Override
	public String TotalMoney() {
		return clientDao.totalMoney();
	}

	@Override
	public Client findById(Integer id) {
		return clientDao.findById(id);
	}

	@Override
	public void updateClient(Client client) {
		clientDao.updateClient(client);
		
	}

	@Override
	public void deleteClient(Client client) {
		clientDao.deleteClient(client);
	}

	@Override
	public List<Payment> payment() {
		return clientDao.payment();
	}

	

}
