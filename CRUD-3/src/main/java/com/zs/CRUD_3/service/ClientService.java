package com.zs.CRUD_3.service;

import java.util.List;

import com.zs.CRUD_3.domain.Client;
import com.zs.CRUD_3.domain.Payment;
import com.zs.CRUD_3.util.Page;

public interface ClientService {

	void add(Client client);

	List<Client> findAll();

	List<Client> findByRtime(String time1, String time2);

	List<Client> Fuzzyquery(String str1);

	String TotalMoney();

	Client findById(Integer id);

	void updateClient(Client client);

	void deleteClient(Client client);

	List<Payment> payment();

	


}
