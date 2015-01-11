package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyServer {
	private static int port = 8888;

	private static EventLoopGroup bossGroup = new NioEventLoopGroup();
	private static EventLoopGroup workerGroup = new NioEventLoopGroup();

	public static void start() throws Exception {
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup)
				       .channel(NioServerSocketChannel.class)
				       .childHandler(new ChannelInitializer<Channel>() {
							@Override
							protected void initChannel(Channel ch) throws Exception {
								ChannelPipeline pipeline = ch.pipeline();
								pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4,0,4));
								pipeline.addLast(new LengthFieldPrepender(4));
								pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
								pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
								pipeline.addLast(new ServerHandler());
							}
				       })
				       .option(ChannelOption.SO_BACKLOG, 128)          // (5)
				       .childOption(ChannelOption.SO_KEEPALIVE, true);
		
		ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
		channelFuture.channel().closeFuture().sync();
	}

	public static void main(String[] args) throws Exception {
		start();
	}
}