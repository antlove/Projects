package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class Server {
	private Selector selector = null;
	
	public Server() throws IOException{
		// register the server
		ServerSocketChannel ssc = ServerSocketChannel.open(); 
		ssc.configureBlocking(false);
		int port = 8080;
		InetSocketAddress endpoint = new InetSocketAddress(port );
		ssc.socket().bind(endpoint);
		selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
	}
	
	public void start() throws IOException{
		while(true){
			int num = selector.select();
			
			if(num>0){
				Set<SelectionKey> selectionKeySet = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeySet.iterator();
				while(iterator.hasNext()){
					SelectionKey key = iterator.next();
					iterator.remove();// must remove it
					
					if (key.isAcceptable()) {
						ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
						SocketChannel sc = serverSocketChannel.accept();
						sc.configureBlocking(false);
						sc.register(selector, SelectionKey.OP_READ);
						
					}else if (key.isReadable()) {
						System.out.println("do read ...");
						SocketChannel sc = (SocketChannel) key.channel();
						sc.register(selector, SelectionKey.OP_WRITE);
						
					}else if (key.isWritable()) {
						System.out.println("do write ...");
						SocketChannel sc = (SocketChannel) key.channel();
						sc.close();
						key.cancel();
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		new Server().start();
	}

}


