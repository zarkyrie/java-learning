package cn.liangjieheng.learning.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.*;

public class EchoClient implements Runnable {

    private static final ExecutorService threadPool = new ThreadPoolExecutor(10, 100, 5000, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    private final String host;

    private final int port;

    private final int flag;

    public EchoClient(String host, int port, int flag) {
        this.host = host;
        this.port = port;
        this.flag = flag;
    }

    private static NettyConfig nettyConfig = new NettyConfig();

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pi = socketChannel.pipeline();
                            pi.addLast(new IdleStateHandler(0, 0, 2, TimeUnit.SECONDS));
                            pi.addLast(new EchoClientHandler(nettyConfig));
                        }
                    });
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        Thread A = new Thread(new EchoClient("localhost", 12018, 0));
//        A.run();
        new EchoClient("localhost", 12018, 0).run();
//        nettyConfig.send("hello");
//        Thread.sleep(3000);
//        nettyConfig.send("world");
//        EchoClientHandler echoClientHandler = new EchoClientHandler(0);
//        echoClientHandler.getConfig().send("hello server");
//
//        echoClientHandler.getConfig().send("hello ");

    }
}
