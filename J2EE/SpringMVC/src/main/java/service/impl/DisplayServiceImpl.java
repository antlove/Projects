package service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import pojo.User;
import service.DisplayService;

@Service("displayService")
public class DisplayServiceImpl implements DisplayService{

	@Override
	public String getHelloWorld() {
		return "Hello World, Spring 3.0!";
	}

	@Override
	public int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(100);
	}

	@Override
	public User getUser() {
		User user = new User("孝","2000-01-01");
		return user;
	}

}
