package com.zs.CRUD_3.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.zs.CRUD_3.dao.ClientDao;
import com.zs.CRUD_3.domain.Client;
import com.zs.CRUD_3.domain.Payment;
import com.zs.CRUD_3.domain.User;

@Repository
public class ClientDaoImpl implements ClientDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(Client client) {
		String sql = "insert into t_client(client_name,client_rtime,client_person,"
				+ "client_number,client_address,client_level,client_progress,"
				+ "client_mail,client_phone,client_ctime,client_payment) value(?,?,?,?,?,?,?,?,?,?,?) ";
		jdbcTemplate.update(sql, client.getClient_name(),client.getClient_rtime(),client.getClient_person(),client.getClient_number(),client.getClient_address(),client.getClient_level(),
							client.getClient_progress(),client.getClient_mail(),client.getClient_phone(),
							client.getClient_ctime(),client.getClient_payment());
	}
	public List<Client> findAll(){
		String sql = "select * from t_client";
		RowMapper<Client> rowMapper = new BeanPropertyRowMapper<Client>(Client.class);
		List<Client>  clients = jdbcTemplate.query(sql, rowMapper, new Object[]{});
		for(Client client : clients) {
			String ctime1 = client.getClient_ctime();
			StringBuffer sb = new StringBuffer(ctime1);
			sb.insert(4, "年").insert(7, "月").insert(10, "日");
			ctime1 = sb.toString();
			client.setClient_ctime(ctime1);
			String rtime1 = client.getClient_rtime();
			StringBuffer sb1 = new StringBuffer(rtime1);
			sb1.insert(4, "年").insert(7, "月").insert(10, "日");
			rtime1 = sb1.toString();
			client.setClient_rtime(rtime1);
		}
			return clients;
	}
	
	public List<Client> findByRtime(String time1,String time2) {
		RowMapper<Client> rowMapper = new BeanPropertyRowMapper<Client>(Client.class);
		 List<Client> clients = jdbcTemplate.query("SELECT * FROM t_client WHERE client_rtime BETWEEN ? AND ?;", new Object[]{time1,time2}, rowMapper);
		return clients;
	}
	
	public List<Client> Fuzzyquery(String str1){
		RowMapper<Client> rowMapper = new BeanPropertyRowMapper<Client>(Client.class);
		
		List<Client> clients = jdbcTemplate.query("SELECT * FROM t_client WHERE client_name LIKE ?", new Object[]{"%" + str1 + "%"}, rowMapper);
		return clients;
	}
	

	@Override
	public String totalMoney() {
		String sql = " select sum(client_money) from t_client";
		//RowMapper<Client> rowMapper = new BeanPropertyRowMapper<Client>(Client.class);
		return  jdbcTemplate.queryForObject(sql, java.lang.String.class);
	}
	
		String sql = "select sum(client_money) from t_client where client_ctime=?";

		@Override
		public Client findById(Integer id) {
			RowMapper<Client> rowMapper = new BeanPropertyRowMapper<Client>(Client.class);
			 List<Client> clients = jdbcTemplate.query("SELECT * FROM t_client WHERE id = ?;", new Object[]{id}, rowMapper);
			 Client client;
			 if(clients!=null&clients.size()>0) {
				 client = clients.get(0);
			}else {
				return null;
			}
			 return client;
		}
		@Override
		public void updateClient(Client client) {
			String str = "update t_client set client_name=?,client_rtime=?,client_address=?,client_payment=? where id=?";
			jdbcTemplate.update(str, client.getClient_name(),client.getClient_rtime(),client.getClient_address(),client.getClient_payment(),client.getId());
			System.out.println(client.getClient_name());
		}
		@Override
		public void deleteClient(Client client) {
			String str = "delete from t_client where id=?";
			jdbcTemplate.update(str, client.getId());
		
		}
		@Override
		public List<Payment> payment() {
			RowMapper<Payment> rowMapper = new BeanPropertyRowMapper<Payment>(Payment.class);
			String str = "select * from payment_method";
			List<Payment> payments = jdbcTemplate.query(str, rowMapper);
			if(payments!=null&payments.size()>0) {
				return payments;
			}else {
				return null;
			}
		}
}
