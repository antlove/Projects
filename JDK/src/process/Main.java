package process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		readConsole("cmd /c dir c: ");

	}
	
    public static String readConsole(String cmd) throws IOException { 
        StringBuffer cmdout = new StringBuffer(); 
        Process process = Runtime.getRuntime().exec(cmd);     //执行一个系统命令 
        InputStream fis = process.getInputStream(); 
        BufferedReader br = new BufferedReader(new InputStreamReader(fis)); 
        String line = null; 
        while ((line = br.readLine()) != null) { 
                cmdout.append(line); 
        } 
        System.out.println("执行系统命令后的结果为：\n" + cmdout.toString()); 
        return cmdout.toString().trim(); 
} 

}
