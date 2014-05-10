package dao;
import javax.persistence.EntityManager;

import pojo.Employee;

public class EmployeeDao {
    private EntityManager em=null;
    
	public EmployeeDao(EntityManager em) {
		this.em = em;
	}

	public Employee createEmployee(String id,String name,double salary){
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        emp.setSalary(salary);
        em.persist(emp);
        return emp;
    }
    
    public void removeEmployee(String id){
        Employee emp = findEmployee(id);
        if(emp==null){
            throw new RuntimeException("The employee whose id is "+ id+" is not existed");
        }
        em.remove(emp);                        
    }
    
    public Employee findEmployee(String id){
        return em.find(Employee.class,id);
    }
    
    public Employee updateEmployee(String id,String name,double salary){
        Employee emp=findEmployee(id);
        if(emp==null){
            throw new RuntimeException("The employee whose id is "+ id+" is not existed");
        }
        emp.setName(name);
        emp.setSalary(salary);
        return emp;
    }
    
}
