package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.CharsetUtil;

import org.apache.log4j.Logger;

public class NettyClient {
	private static Logger logger = Logger.getLogger(NettyClient.class);
	
	private static String host = "localhost";
	
	private static int port = 8888;
	
    public static void sendMsg(String msg) {
            EventLoopGroup group = new NioEventLoopGroup();
            try {
	                Bootstrap b = new Bootstrap();
	                b.group(group)
	                 .channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
	                 .handler(new ChannelInitializer<SocketChannel>() {
		                   @Override
		                    protected void initChannel(SocketChannel ch) throws Exception {
		                        ChannelPipeline pipeline = ch.pipeline();
		                        pipeline.addLast( new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4,0,4));
		                        pipeline.addLast( new LengthFieldPrepender(4));
		                        pipeline.addLast( new StringDecoder(CharsetUtil.UTF_8));
		                        pipeline.addLast( new StringEncoder(CharsetUtil.UTF_8));
		                        pipeline.addLast( new ReadTimeoutHandler(1));
		
		                        pipeline.addLast( new NettyClientHandler());
		                    }
	                });
		            ChannelFuture f = b.connect(host, port).sync();
		            f.channel().writeAndFlush(msg);
		            f.channel().closeFuture().sync();
            } catch (Exception e) {
            	logger.error("encounter an exception",e);
            } finally {
                group.shutdownGracefully();
            }
      }

        public static void main(String[] args) throws Exception {
        	sendMsg("");
        }
}