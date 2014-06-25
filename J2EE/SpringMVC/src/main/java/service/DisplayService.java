package service;

import org.springframework.stereotype.Service;

import pojo.User;

@Service("displayService")
public interface DisplayService {
	public String getHelloWorld();
	
	public int getRandomNumber();
	
	public User getUser();
}
