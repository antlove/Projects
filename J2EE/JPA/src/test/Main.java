package test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pojo.Employee;
import dao.EmployeeDao;
public class Main {

	public static void main(String[] args) {
	    EntityManagerFactory emf=Persistence.createEntityManagerFactory("emp");
	    EntityManager em=emf.createEntityManager();
	    
	    EmployeeDao employeeDao = new EmployeeDao(em);
	    
	    // create employee 
	    em.getTransaction().begin();
	    employeeDao.createEmployee("1", "xiao", 100);
	    em.getTransaction().commit();
	    
	    // update employee
	    em.getTransaction().begin();
	    employeeDao.updateEmployee("1", "xiao", 1000);
	    em.getTransaction().commit();
	    
	    // select employee
	    Employee e=employeeDao.findEmployee("1");
	    System.out.println(e);
	    
	    // remove employee
	    em.getTransaction().begin();
	    employeeDao.removeEmployee("1");
	    em.getTransaction().commit();
	}
}
