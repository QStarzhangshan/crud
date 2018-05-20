package com.zs.CRUD_3.domain;

import java.io.Serializable;

public class Client implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String client_name;
	private String client_rtime;
	private String client_person;
	private Integer client_number;
	private String client_address;
	private String client_level;
	private String client_progress;
	private String client_mail;
	private String client_phone;
	private String client_ctime;
	private Integer user_number;
	private String client_money;
	private String client_payment;
	private String client_img;
	
	
	public String getClient_payment() {
		return client_payment;
	}
	public void setClient_payment(String client_payment) {
		this.client_payment = client_payment;
	}
	public String getClient_img() {
		return client_img;
	}
	public void setClient_img(String client_img) {
		this.client_img = client_img;
	}
	public String getClient_money() {
		return client_money;
	}
	public void setClient_money(String client_money) {
		this.client_money = client_money;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_number() {
		return user_number;
	}
	public void setUser_number(Integer user_number) {
		this.user_number = user_number;
	}
	public String getClient_ctime() {
		return client_ctime;
	}
	public void setClient_ctime(String client_ctime) {
		this.client_ctime = client_ctime;
	}
	
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getClient_rtime() {
		return client_rtime;
	}
	public void setClient_rtime(String client_rtime) {
		this.client_rtime = client_rtime;
	}
	public String getClient_person() {
		return client_person;
	}
	public void setClient_person(String client_person) {
		this.client_person = client_person;
	}
	
	
	public Integer getClient_number() {
		return client_number;
	}
	public void setClient_number(Integer client_number) {
		this.client_number = client_number;
	}
	public String getClient_address() {
		return client_address;
	}
	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}
	public String getClient_level() {
		return client_level;
	}
	public void setClient_level(String client_level) {
		this.client_level = client_level;
	}
	public String getClient_progress() {
		return client_progress;
	}
	public void setClient_progress(String client_progress) {
		this.client_progress = client_progress;
	}
	public String getClient_mail() {
		return client_mail;
	}
	public void setClient_mail(String client_mail) {
		this.client_mail = client_mail;
	}
	public String getClient_phone() {
		return client_phone;
	}
	public void setClient_phone(String client_phone) {
		this.client_phone = client_phone;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", client_name=" + client_name + ", client_rtime=" + client_rtime
				+ ", client_person=" + client_person + ", client_number=" + client_number + ", client_address="
				+ client_address + ", client_level=" + client_level + ", client_progress=" + client_progress
				+ ", client_mail=" + client_mail + ", client_phone=" + client_phone + ", client_ctime=" + client_ctime
				+ ", user_number=" + user_number + ", client_money=" + client_money + ", client_payment="
				+ client_payment + ", client_img=" + client_img + "]";
	}
	
	

	
	
	
}
