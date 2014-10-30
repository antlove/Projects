package jasperreport.javabean;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

public class JasperReportWithJavaBean {
	public static void export() throws Exception{
		
		InputStream inputStream = JasperReportWithJavaBean.class.getResourceAsStream("JavaBeanReport.jasper");
		Map<Object,Object> parameters = new HashMap<Object,Object>();
		
		List<User> users = new ArrayList<User>();
		users.add(new User("user_01",new Date()));
		users.add(new User("user_02",new Date()));
		users.add(new User("user_03",new Date()));
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);
		
		
		JRTextExporter exporter = new JRTextExporter();
		
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "javabean.txt");  
        exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, 200);  
        exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, 100);  
		exporter.exportReport();
	}
	
	public static void main(String[] args) throws Exception{
		export();
	}
}
