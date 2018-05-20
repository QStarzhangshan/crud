package com.zs.CRUD_3.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.zs.CRUD_3.domain.RequestMessage;
import com.zs.CRUD_3.domain.ResponseMessage;

@Controller
public class WsController {
    
	
	@MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }
	/*@MessageMapping("/ws")
	public String addViewControllers() {
		return "/ws";
	}*/


}