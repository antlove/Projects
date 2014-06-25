package web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.User;
import service.DisplayService;

@Controller
public class DisplayController {
	private Logger logger = Logger.getLogger(DisplayController.class.getName());
	
	@Autowired
	private DisplayService displayService;
	
	
	@RequestMapping("display")
	public ModelAndView helloWorld() {
		String message = displayService.getHelloWorld();
		logger.info(message);
		return new ModelAndView("display", "message", message);
	}
	
	@RequestMapping(value="display/random")
	public @ResponseBody int displayJson() throws UnsupportedEncodingException{
		return displayService.getRandomNumber();
	}
	
	@RequestMapping(value="display/user",produces={"application/json;charset=UTF-8"})
	public @ResponseBody List<User> displayUser(HttpServletResponse response) throws Exception{
		User user = displayService.getUser();
		ObjectMapper objectMapper = new ObjectMapper();  
		String result = objectMapper.writeValueAsString(user);  
		List<User> users = new ArrayList<User>();
		users.add(user);
		return users; 
	}
	
}
