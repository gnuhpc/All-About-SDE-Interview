package org.gnuhpc.bigdata.network.iomodel.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;

//SocketChannelClient is its client demo program
public class UsingSelector {
    public static void main(String[] args) throws IOException {
        //生成一个ServerSocketChannel，并设置为non-blocking
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);

        //获取Socket并进行监听
        ServerSocket socket = channel.socket();
        socket.bind(new InetSocketAddress(12345));

        //生成一个Selector，并注册Accept事件处理给前边生成的ServerSocketChannel
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            System.out.println("Wating the events...");

            int select = selector.select();

            System.out.printf("Got %d events\n", select);

            Set<SelectionKey> keys = selector.selectedKeys();

            for (SelectionKey key : keys) {
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    //处理Accept事件，并注册新的channel处理Read消息
                    System.out.println("Accepting the connection");
                    //从ServerSocketChannel的accept生成socketchannel
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    //注册此channel的读事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    keys.remove(key);
                } else if ((SelectionKey.OP_READ & key.readyOps()) == SelectionKey.OP_READ) {
                    //处理读事件
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel.read(buffer);
                    buffer.flip();
                    CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer);
                    String result = new String(charBuffer.array());
                    System.out.println("Read:" + result);
                    buffer.clear();
                    keys.remove(key);
                    key.cancel();
                    socketChannel.close();
                }
            }

        }

    }
}
