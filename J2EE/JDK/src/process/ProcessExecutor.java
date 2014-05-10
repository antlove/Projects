package process;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessExecutor {
	public static void execute(String command)throws Exception{
		Process process = Runtime.getRuntime().exec(command);
		// the exit value
		int exitValue = process.waitFor();
		System.out.println("the exit value is "+exitValue);
		
		String line = null;
		
		// the output message from sub process
		InputStream inputStream = process.getInputStream();
		BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sbForInputStream = new StringBuilder();
        while ((line = inputStreamReader.readLine()) != null) { 
        	sbForInputStream.append(line); 
        } 
        inputStreamReader.close();
        System.out.println("below is the output message :\n"+sbForInputStream);
        
        
        InputStream errInputStream = process.getErrorStream();
		BufferedReader errInputStreamReader = new BufferedReader(new InputStreamReader(errInputStream));
		StringBuilder sbForErrInputStream = new StringBuilder();
	    line = null; 
        while ((line = errInputStreamReader.readLine()) != null) { 
        	sbForErrInputStream.append(line); 
        } 
        errInputStreamReader.close();
        System.out.println("below is the error output message :\n"+sbForErrInputStream);
	}
	
	public static void main(String[] args)throws Exception {
//		execute("cmd /c dir c: ");
		execute("cmd /c del C:\\Users\\dell-pc\\Desktop\\test\\e.txt");
	}
}
