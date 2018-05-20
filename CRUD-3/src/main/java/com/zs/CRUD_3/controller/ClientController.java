package com.zs.CRUD_3.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.tools.internal.ws.processor.model.Model;
import com.zs.CRUD_3.domain.Client;
import com.zs.CRUD_3.domain.Payment;
import com.zs.CRUD_3.service.ClientService;
import com.zs.CRUD_3.util.Page;
import com.zs.CRUD_3.util.PageBean;

@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	

	
	@RequestMapping(value="payment")
	@ResponseBody
	public Map<String,Object> payment(){
		List<Payment> payments = clientService.payment();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("payments", payments);
		return map;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addClient(HttpServletRequest request,
										String client_name,String client_rtime,String client_person,
										Integer client_number,String client_address,String client_level,
										String client_progress,String client_mail,String client_phone,
										String client_ctime,Integer client_y,Integer client_m,Integer client_d,
										String client_payment){
		Client client = new Client();
		client.setClient_name(client_name);
		//client.setClient_y(client_y);
		//client.setClient_m(client_m);
		//client.setClient_d(client_d);
		client.setClient_rtime(client_rtime.replaceAll("-", ""));
		client.setClient_person(client_person);
		client.setClient_number(client_number);
		client.setClient_address(client_address);
		client.setClient_level(client_level);
		client.setClient_progress(client_progress);
		client.setClient_mail(client_mail);
		client.setClient_phone(client_phone);
		client.setClient_payment(client_payment);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		client_ctime = dateFormat.format(new Date()).replaceAll("-", "");
		client.setClient_ctime(client_ctime);
		clientService.add(client);
		System.out.println(client.toString());
		return null;
		
	}
	
	@RequestMapping(value="/listUI")
	public String findAllUI() {
		return "ListClient";
	}
	
	public Map<String,Object> findById(Integer id){
		Client client = clientService.findById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("client", client);
		return map;
	}
	
	@RequestMapping(value="/list" , method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> findAll(){
		Map<String,Object> map = new HashMap<String,Object>();
		int currentPage = 1;
		int pageSize = 2;
		PageHelper.startPage(currentPage, pageSize);
		List<Client> clients = clientService.findAll();
		int countNums = clients.size();
		PageBean<Client> pageData = new PageBean<>(currentPage, pageSize, countNums);
		pageData.setItems(clients);
		List<Client> clients1 = pageData.getItems();
		map.put("clinets", clients1);
		return map;
			
	}
	
	@RequestMapping(value="/cheekByRtime")
	public String findByRtime(String time1,String time2){
		List<Client> clients = clientService.findByRtime(time1,time2);
		System.out.println(clients);
		return "success1";
	}
	@RequestMapping(value="/client_namequery", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> client_namequery(String str1) {
		List<Client> clients = clientService.Fuzzyquery(str1);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("clients1", clients);
		System.out.println(clients);
		return map;
	}
	@RequestMapping(value="/client_totalmoney")
	@ResponseBody
	public Map<String,String> client_totalmoney() {
		String money = clientService.TotalMoney();
		Map<String,String> map = new HashMap<String,String>();
		map.put("totalmoney", money);
		System.out.println(money);
		return map;
	}
	@RequestMapping("FuzzyqueryUI")
	public String FuzzyqueryUI() {
		return "success";
	}
	@RequestMapping(value="/modifyUI")
	public String modifyUI(Map<String,Integer> map,Integer client_id) {
		map.put("client_id", client_id);
		System.out.println("!111");
		return "modifyClient";
	}
	@RequestMapping(value="/modifyClient",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updateClient( String client_name, String client_person,
			String client_address,String client_phone,String client_payment,
			Integer id) {
			Map<String,String> map = new HashMap<String,String>();
			Client client = clientService.findById(id);
			client.setClient_name(client_name);
			client.setClient_address(client_address);
			client.setClient_person(client_person);
			client.setClient_phone(client_phone);
			client.setClient_payment(client_payment);
			if(client_name==""||client_name.equals("")) {
				map.put("result", "1");
			}else if(client_address==""||client_address.equals("")) {
				map.put("result", "2");
			}else if(client_person==""||client_person.equals("")) {
				map.put("result", "3");
			}else if(client_phone==""||client_phone.equals("")) {
				map.put("result","4");
			}else {
				clientService.updateClient(client);
				map.put("result", "0");
			}
			return map;
	}
	@RequestMapping(value="/deleteClient",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Client> deleteClient(Integer id){
		Map<String,Client> map = new HashMap<String,Client>();
		Client client = clientService.findById(id);
		map.put("client", client);
		clientService.deleteClient(client);
		return map;
		
	}
	
	@RequestMapping(value="/Fuzzyquery",method = RequestMethod.GET)
	@ResponseBody
	public List<Client> Fuzzyquery(String str1){
		List<Client> clients = clientService.Fuzzyquery(str1);
		for(Client client : clients) {
			System.out.println(client);
		}
		return clients;
	}
	/*public Map<String,String> client_TodayMoney(Client client){
		clientService.TodayMoney
	}*/
}

