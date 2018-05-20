package com.zs.CRUD_3.dao;

import java.util.List;

import com.zs.CRUD_3.domain.Client;
import com.zs.CRUD_3.domain.Payment;

public interface ClientDao {

	void add(Client client);

	List<Client> findAll();

	List<Client> findByRtime(String time1, String time2);

	List<Client> Fuzzyquery(String str1);

	String totalMoney();

	Client findById(Integer id);

	void updateClient(Client client);

	void deleteClient(Client client);

	List<Payment> payment();



}
