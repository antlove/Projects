package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class DisplayServer {
	private static String dirPath="/G:/Projects/Bootstrap/WebRoot";
	private static Logger logger = Logger.getLogger(DisplayServer.class.getName());
	
	
	private static String retrieveURL(InputStream is) throws IOException{
		StringBuilder firstLine = new StringBuilder();
		byte b = -1;
		while((b=(byte) is.read())>0){
			if(b=='\n'||b=='\r'){
				break;
			}
			firstLine.append((char)b);
		}
		if(firstLine.length()==0){
			return null;
		}
		if(firstLine.toString().split(" ").length!=3){
			return null;
		}
		return firstLine.toString().split(" ")[1];
	}
	
	private static List<byte[]> loadResources(String url) throws IOException{
		List<byte[]> loadBytes = new ArrayList<byte[]>();
		String resourceUrl = dirPath+url;
		File file = new File(resourceUrl);
		if(!file.exists()){
			logger.warning("the specified file ["+dirPath+url+"] is not existed.");
			return null;
		}
		FileInputStream fis = new FileInputStream(file);
		byte[] bytes = new byte[Short.MAX_VALUE];
		int len = -1;
		while((len=fis.read(bytes))>0){
			byte[] bs = Arrays.copyOf(bytes, len);
			loadBytes.add(bs);
		}
		return loadBytes;
	}
	
	private static void handleRequest(final Socket socket){
		Thread thead = new Thread(){
			public void run(){
					try {
						String url = retrieveURL(socket.getInputStream());
						logger.info("url:"+url);
						if(url==null){
							socket.close();
							return;
						}
		
						if(url.equals("/dist/css/bootstrap.css")){
							logger.info("debug");
						}
						List<byte[]> recources = loadResources(url);
						
						if(recources==null){
							socket.close();
							return;
						}
						
						OutputStream os = socket.getOutputStream();
						for(byte[] bytes : recources){
							os.write(bytes);
							os.flush();
							try {
								Thread.sleep(5);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

						socket.shutdownOutput();
						
						socket.close();
						}catch (IOException e) {
							
							e.printStackTrace();
						}
			}
		};
		thead.start();
		
	}
	
	private static void  start() throws Exception{
		ServerSocket serverSocket = new ServerSocket(8888);
		
		while(true){
			Socket socket = serverSocket.accept();
			handleRequest(socket);
		}
		

	}
	
	public static void main(String[] args)throws Exception{
		start();
	}
}

