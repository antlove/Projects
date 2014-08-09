package com.nio;

import java.io.IOException;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws IOException{
		System.out.println("client start ... ");
		
		Socket socket = new Socket("localhost",8080);
		
		socket.close();
		
		System.out.println("client end ... ");
	}
}
