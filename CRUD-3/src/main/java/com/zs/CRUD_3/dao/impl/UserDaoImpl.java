package com.zs.CRUD_3.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.zs.CRUD_3.dao.UserDao;
import com.zs.CRUD_3.domain.Client;
import com.zs.CRUD_3.domain.User;



@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<User> findall() {
		String sql = "select * from t_user";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		List<User>  users = jdbcTemplate.query(sql, rowMapper, new Object[]{});
		return users;
	}

	public int addUser(User user) {
		return  jdbcTemplate.update("insert into t_user(username,name,password,role_name,role_id,mail) value(?,?,?,?,?,?)",
				user.getUsername(),user.getName(),user.getPassword(),user.getRole_name(),user.getRole_id(),user.getMail());
}

	

	public int update(User user) {
		return jdbcTemplate.update("UPDATE t_user SET password=? where username=?"
				,user.getPassword(),user.getUsername());
	}
	
	public List<User> findById(int id) {
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		 List<User> users = jdbcTemplate.query("select * from t_user where id = ?", new Object[]{id}, rowMapper);
		return users;
	}

	public User findUserByUsername(String username) {
		String sql = "select * from t_user where username = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		List<User> users = jdbcTemplate.query(sql, new Object[]{username}, rowMapper);
		if(users!=null&users.size()>0) {
			User user = users.get(0);
			return user;
		}else {
			return null;
		}
		
	}

	@Override
	public boolean usernameisexit(String username) {
		String sql = "select * from t_user where username = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		List<User> users = jdbcTemplate.query(sql, new Object[] {username}, rowMapper);
		if(users!=null&users.size()>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean findByUsernameAndMail(String username, String mail) {
		String sql = "select * from t_user where username=? and mail=?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		List<User> users = jdbcTemplate.query(sql, new Object[]{username,mail}, rowMapper);
		if(users!=null&users.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	public List<Client> findClientByUser_number(User user){
		String sql = "select * from t_client where user_number = ?";
		RowMapper<Client> rowMapper = new BeanPropertyRowMapper<Client>(Client.class);
		List<Client> clients = jdbcTemplate.query(sql, new Object[]{user.getNumber()}, rowMapper);
		return clients;
	}
	public void addClient(Client client,User user) {
		
		String sql = "insert into t_client(id,client_name,client_rtime,client_person,"
				+ "client_number,client_address,client_level,client_progress,"
				+ "client_mail,client_phone,client_ctime,user_number) value(?,?,?,?,?,?,?,?,?,?,?,?) ";
		
		jdbcTemplate.update(sql, client.getId(),client.getClient_name(),client.getClient_rtime(),client.getClient_person(),client.getClient_number(),client.getClient_address(),client.getClient_level(),
				client.getClient_progress(),client.getClient_mail(),client.getClient_phone(),
				client.getClient_ctime(),user.getNumber());
		}
	
	public void updateClient(Client client,User user) {
		String sql = "update t_client set client_rtime=?,client_person=?,client_number=?,"
				+ "client_address=?,client_level=?,client_progress=?,client_mail=?,client_phone=?,"
				+ "user_number=? where id = ?";
		jdbcTemplate.update(sql, client.getClient_rtime(),client.getClient_person(),client.getClient_number(),client.getClient_address(),
				client.getClient_level(),client.getClient_progress(),client.getClient_mail(),client.getClient_phone(),
				client.getUser_number(),client.getId());
		
		}
	

	@Override
	public Client findClientById(Integer id) {
		RowMapper<Client> rowMapper = new BeanPropertyRowMapper<Client>(Client.class);
		 List<Client> clients = jdbcTemplate.query("select * from t_client where id = ?", new Object[]{id}, rowMapper);
		if(clients!=null&clients.size()>0) {
			Client client = clients.get(0);
			return client;
		}else {
			return null;
		}
	}

	@Override
	public void UserState(int i,String username) {
		if(i==1) {
			String sql = "update t_user set state=1 where username = ?";
			jdbcTemplate.update(sql,username);
		}else if(i==0) {
			String sql1 = "update t_user set state=0 where username = ?";
			jdbcTemplate.update(sql1,username);
		}
	}

	@Override
	public void updateClient(Client client) {
		 jdbcTemplate.update("UPDATE t_client SET client_name=? where id=?"
				,client.getClient_name(),client.getId());
	}
		
	}

