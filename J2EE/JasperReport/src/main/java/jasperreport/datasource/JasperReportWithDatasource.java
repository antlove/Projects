package jasperreport.datasource;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

public class JasperReportWithDatasource {
	public static void export() throws Exception{
		
		InputStream inputStream = JasperReportWithDatasource.class.getResourceAsStream("DBReport.jasper");
		Map<Object,Object> parameters = new HashMap<Object,Object>();
		Connection connection = ConnectionProvider.getConnection();
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, connection);
		
		
		JRTextExporter exporter = new JRTextExporter();
		
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "db.txt");  
        exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, 200);  
        exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, 100);  
		exporter.exportReport();
	}
	
	public static void main(String[] args) throws Exception{
		export();
	}
}
