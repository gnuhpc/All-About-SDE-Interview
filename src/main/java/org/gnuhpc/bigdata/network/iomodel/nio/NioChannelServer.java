package org.gnuhpc.bigdata.network.iomodel.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioChannelServer {
    private ByteBuffer buff = ByteBuffer.allocate(1024);
    private IntBuffer intBuff = buff.asIntBuffer();
    private SocketChannel clientChannel = null;
    private ServerSocketChannel serverChannel = null;

    public void openChannel() throws Exception {
        serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(8888));
        System.out.println("服务器通道打开");
    }

    public void start() throws Exception {
        openChannel();
        waitReqConn();
        clientChannel.close();

    }

    private void waitReqConn() throws IOException {
        while (true) {
            clientChannel = serverChannel.accept();
            if (null != clientChannel) {
                System.out.println("新连接！");
            }

            processReq();
            clientChannel.close();
        }
    }

    private void processReq() throws IOException {
        System.out.println("开始处理");
        buff.clear();
        clientChannel.read(buff);

        int result = intBuff.get(0) + intBuff.get(1);
        buff.flip();
        buff.clear();

        intBuff.put(0, result);
        clientChannel.write(buff);
        System.out.println("处理完毕！");
    }

    public static void main(String[] args) throws Exception {
        new NioChannelServer().start();
    }
}
