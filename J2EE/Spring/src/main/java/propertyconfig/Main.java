package propertyconfig;

import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("propertyconfig/applicationContext.xml");
		
		Map<String,String> properties = CustomPropertyConfigurer.getProperties();
		
		System.out.println(properties);
		
	}

}
