package xml.validation;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XmlValidation {
	public static void main(String[] args)throws Exception {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		InputStream schemaInputStream = XmlValidation.class.getResourceAsStream("students.xsd");
		
		Schema schema = schemaFactory.newSchema(new StreamSource(schemaInputStream));
		
		Validator validator = schema.newValidator();
		
		InputStream xmlInputStream = XmlValidation.class.getResourceAsStream("students.xml");
		StreamSource source = new StreamSource(xmlInputStream);
		
		validator.validate(source);
		
		
	}
}
