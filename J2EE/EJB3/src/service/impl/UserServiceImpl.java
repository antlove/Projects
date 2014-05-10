package service.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import service.IUserService;

@Stateless(mappedName="UserServiceBean")
@Remote({IUserService.class})
public class UserServiceImpl implements IUserService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getUserName() {
		
		return "small dog";
	}

	

	
}
