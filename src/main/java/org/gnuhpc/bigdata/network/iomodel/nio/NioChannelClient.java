package org.gnuhpc.bigdata.network.iomodel.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;

public class NioChannelClient {
    private ByteBuffer buff = ByteBuffer.allocate(8);
    private IntBuffer intBuff = buff.asIntBuffer();
    private SocketChannel channel;

    public SocketChannel connect() throws IOException {
        return SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
    }

    public void sendRequest(int a, int b) throws IOException {
        buff.clear();
        intBuff.put(0, a);
        intBuff.put(1, b);
        channel.write(buff);
        System.out.println("已发送请求！");
    }

    public static void main(String[] args) throws IOException {
        System.out.println("结果是" + new NioChannelClient().getSum(56, 34));
    }

    public int getSum(int a, int b) throws IOException {
        channel = connect();
        sendRequest(a, b);
        return getResult();
    }

    private int getResult() throws IOException {
        buff.clear();
        channel.read(buff);
        return intBuff.get(0);
    }
}
