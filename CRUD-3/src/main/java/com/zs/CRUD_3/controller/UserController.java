package com.zs.CRUD_3.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.client.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zs.CRUD_3.domain.Client;
import com.zs.CRUD_3.domain.User;
import com.zs.CRUD_3.identifyCode.Captcha;
import com.zs.CRUD_3.identifyCode.GifCaptcha;
import com.zs.CRUD_3.service.ClientService;
import com.zs.CRUD_3.service.UserService;
import com.zs.CRUD_3.util.MD5Util;
import com.zs.CRUD_3.util.Match;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	ClientService clientService;
	
	@Autowired
	JavaMailSender javaMailSender;
	
    @Value("${spring.mail.username}")
    private String sender;
	
	@Autowired
	DefaultKaptcha defaultKaptcha;
	
	HttpSession session;
	MD5Util md5 = new MD5Util();
	
	@RequestMapping(value="/list" , method = RequestMethod.GET)
	public List<User> findAll(){
		List<User> users =  userService.findAll();
		return users;
	}
	
	@RequestMapping(value="/UI")
	public String loginUI(HttpServletRequest request) {
		session = request.getSession();
		return "/login";
	}
	@RequestMapping(value="registerUI")
	public String registerUI(HttpServletRequest request) {
		session = request.getSession();
		return "/register";
		
	}
	@RequestMapping(value="modifyUI")
	public String modifyUI() {
		return "/sendmail";
	}
	@RequestMapping(value="modify1")
	public String modify1() {
		return "/modify";
	}
	
	  @RequestMapping("/index")
	    public String index(HttpServletRequest request) {
		  session = request.getSession();
		  if(session.getAttribute("_user")==null) {
			  System.out.println("登录");

			  return "login";
		  }else {
			  System.out.println("index");

			  return "index";
		  }
	    }
	
	 @RequestMapping(value="identify")
		public String identify(HttpServletRequest request) {
			
		 //session = request.getSession();
		 return "/yanzhengma";
		}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateUser(
			@RequestParam(value = "username",required=true)String username,
			@RequestParam(value="password",required=true) String password,
			@RequestParam(value="newpassword",required=true) String newpassword) throws NoSuchAlgorithmException {
		User user = userService.findUserByUsername(username);
		if(password.equals(userService.findUserByUsername(username).getPassword())) {
			
			user.setPassword(MD5Util.getMD5(newpassword));
			userService.update(user);
			return "/success";
		}else {
			return "redirect:/modify.html";
		}
		
		
	}
	@RequestMapping(value="/find/{id}",method=RequestMethod.GET)
	public List<User> findById(@PathVariable(value="id")int id){
		List<User> users = userService.findById(id);
		return users;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam(value="username",required=true) String username,
						@RequestParam(value="password",required=true) String password,
						String code,Integer role_id,HttpServletRequest request) throws NoSuchAlgorithmException {
		User user = userService.findUserByUsername(username);
		role_id = user.getRole_id();
		System.out.println(role_id);
		//code = code.toLowerCase();
		//String v = (String) session.getAttribute("_code");
		if(user.getPassword().equals(password)&role_id==1) {
			user.setState(1); 
			userService.UserState(1,username);
			session.setAttribute("user", user);
			System.out.println(user);
			return "/success";
		}else {
			return  "redirect:/user/UI";
		}
	}
	@RequestMapping(value="/loginout",method=RequestMethod.GET)
	public String loginout(HttpServletRequest request,String username) {
		//session = request.getSession();
		User user = (User)session.getAttribute("user");
		username = user.getUsername();
		session.invalidate();
		userService.UserState(0, username);
		System.out.println("-------");
		return "/login1";
	}
	
	@RequestMapping(value="/login1",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login1(HttpServletRequest request,
									String username,String password,String code){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.findUserByUsername(username);
		//session.setAttribute("_user", user);
		code = code.toLowerCase();
		String v = (String) session.getAttribute("_code");
		if(username.equals("")||username=="") {
			map.put("result", "0");
		}else if(password.equals("")||password=="") {
			map.put("result", "1");
		}else if(code.equals("")||code=="") {
			map.put("result", "2");
		}else if(!code.equals(v)) {
			map.put("result", "3");
		}else if(user.getPassword().equals(password)&code.equals(v)) {
			map.put("result", "4");
			System.out.println("账号是：" +    username  + "密码是：" + password);
		}else {
			map.put("result", "5");
			System.out.println("账号是：" +    username  + "密码是：" + password);
		}
		return map;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(@RequestParam(value="username",required=true) String username,
							@RequestParam(value="password",required=true) String password,
							@RequestParam(value="password2",required=false) String password2,
							@RequestParam(value="mail",required=true) String mail,
							@RequestParam(value="role_id",required=true) Integer role_id,
							@RequestParam(value="role_name",required=false) String role_name,
							String code) throws NoSuchAlgorithmException {
		User user = new User();
		Map<Integer, String> map = Match.addNameById(role_id);
		role_name = map.get(role_id);
		System.out.println(role_name);
		code = code.toLowerCase();
		String v = (String) session.getAttribute("_code");
		System.out.println("用户输入验证码:" + code + "--------" + "动态生成的验证码：" + v);
		if(!userService.usernameisexit(username)&password.equals(password2)&code.equals(v)) {
		user.setUsername(username);
		user.setPassword(MD5Util.getMD5(password));
		user.setMail(mail);
		user.setRole_id(role_id);
		user.setRole_name(role_name);
		System.out.println(user.toString());
		userService.addUser(user);
		//sendMail.sendSimpleMail(mail);
		return "/success";
		}else {
			return "redirect:/register.html";
		}	
	}
	//查看用户自己负责的客户，联系多表查询
	@RequestMapping(value="/manageClient",method=RequestMethod.GET)
	@ResponseBody
	public List<Client> manageClient() {
		User user = (User) session.getAttribute("user");
		List<Client> clients = userService.findClientByUser_number(user);
		session.setAttribute("clients", clients);
		System.out.println(clients);
		return clients;
	}
	@RequestMapping(value="/addClientUI")
	public String addClientUI(HttpServletRequest request) {
		request.getSession(); //这里很重要，拿到session保存的所有属性
		return "/test";
	}
	
	
	
	//增加客户
	@RequestMapping(value="/addClient",method=RequestMethod.POST)
	@ResponseBody
	public void addClient(HttpServletRequest request,Integer id,
			String client_name,String client_rtime,String client_person,
			Integer client_number,String client_address,String client_level,
			String client_progress,String client_mail,String client_phone,
			String client_ctime) {
	
		User user = (User)session.getAttribute("user");
		Client client = new Client();
		client.setId(id);
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		client_ctime = dateFormat.format(new Date()).replaceAll("-", "");
		client.setClient_ctime(client_ctime);
		client.setUser_number(user.getNumber());
		userService.addClient(client,user);
		session.setAttribute("client", client);
		System.out.println(session.getAttribute("client"));
	}
	
	@RequestMapping(value="/updateClientUI")
	public String updateClientUI(HttpServletRequest request,Integer id) {
		request.getSession(); //这里很重要，拿到session保存的所有属性
		Map<String, Object> map = new HashMap<String,Object>();
		Client client  = userService.findClientById(id);
		session.setAttribute("client", client);
		map.put("client_name", client.getClient_name());
		map.put("client_person", client.getClient_person());
		map.put("client_address", client.getClient_address());
		map.put("client_number", client.getClient_number());
		System.out.println(map.get("client_name"));
		return "/test";
	}
	
	@RequestMapping(value="/updateClient")
	@ResponseBody
	public void updateClient(String client_name) {
		Client client = (Client)session.getAttribute("client");
		System.out.println(client);
		client.setClient_name(client_name);
		userService.updateClient(client);
		System.out.println((Client)session.getAttribute("client"));
	
	}
	
	
	 @RequestMapping(value = "/sendmail", method = RequestMethod.POST)  
	    public String loginByGet(@RequestParam(value = "username", required = true) String username,  
	            @RequestParam(value = "mail", required = true) String mail) {
	    	boolean bl = userService.findByUsernameAndMail(username, mail);
	    	if(bl) {
	    		new Thread(new Runnable() {
					
					@Override
					public void run() {
						MimeMessage message = null;
				        try {
				            message = javaMailSender.createMimeMessage();
				            MimeMessageHelper helper = new MimeMessageHelper(message, true);
				            helper.setFrom(sender);
				            helper.setTo(mail);
				            helper.setSubject("标题：找回密码");
				            StringBuffer sb = new StringBuffer();
				            sb.append("<h1>找回密码</h1>")
				                    .append("<p style='color:#F00'><a href='http://localhost:8080/resetPass'>http://localhost:8080/resetPass</a></p>");
				            helper.setText(sb.toString(), true);
				        } catch (Exception e) {
				            e.printStackTrace(); 
				        }
				        javaMailSender.send(message);
					}
				}).start();
	    		return "login";
	    	}else {
	    		return "error";
	    	}
	      
	    }
	
	 @RequestMapping(value = "getGifCode", method = RequestMethod.GET)
		public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
			try {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.setContentType("image/gif");
				/**
				 * gif格式动画验证码 宽，高，位数。
				 */
				Captcha captcha = new GifCaptcha(146, 33, 4);
				// 输出
				captcha.out(response.getOutputStream());
				// 存入Session
				session.setAttribute("_code", captcha.text().toLowerCase());
			} catch (Exception e) {
				System.err.println("获取验证码异常：" + e.getMessage());
			}
		}
	
	public void loginornot(HttpServletRequest request,String username) {
		
	}
	 @RequestMapping("/defaultKaptcha") 
	public void defaultKaptcha(HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) throws IOException {
		byte[] captchaChallengeAsJpeg = null;
		
		ByteArrayOutputStream jpegOutputstream = new ByteArrayOutputStream();
		//生成验证码字符串并保存到session中
		String createText = defaultKaptcha.createText();
		httpServletRequest.getSession().setAttribute("vrifyCode", createText);
		//使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
		BufferedImage challenge = defaultKaptcha.createImage(createText);
		ImageIO.write(challenge, "jpg", jpegOutputstream);
		httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		
		//定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组 
		captchaChallengeAsJpeg = jpegOutputstream.toByteArray();
		httpServletResponse.setHeader("Cache-Control", "no-store");    
        httpServletResponse.setHeader("Pragma", "no-cache");    
        httpServletResponse.setDateHeader("Expires", 0);    
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);    
        responseOutputStream.flush();    
        responseOutputStream.close();  

	}
	@RequestMapping("/imgvrifyControllerDefaultKaptcha")  
	public ModelAndView imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){  
	    ModelAndView andView = new ModelAndView();  
	     String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");    
	     String parameter = httpServletRequest.getParameter("vrifyCode");  
	     System.out.println("Session  vrifyCode "+captchaId+" form vrifyCode "+parameter);  
	       
	    if (!captchaId.equals(parameter)) {  
	        andView.addObject("info", "错误的验证码");  
	        andView.setViewName("index");  
	    } else {  
	        andView.addObject("info", "登录成功");  
	        andView.setViewName("succeed");  
	          
	    }  
	    return andView;  
	}  
}
