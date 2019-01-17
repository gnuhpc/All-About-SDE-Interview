package org.gnuhpc.bigdata.network.iomodel.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class SelectorServerTest {

    public static void main(String[] args) throws Exception {

        Selector selector = null;
        ServerSocketChannel serverChannel = null;

        try {
            // 创建Selector
            selector = Selector.open();

            // 创建非阻塞Server Socket Channel
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.bind(new InetSocketAddress(7994));

            // 将ServerSocketChannel注册到Selector
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                // 等待信道超时，超时事件为3秒
                if (selector.select(3000) == 0) {
                    System.out.println("等待中...");
                    continue;
                }

                Iterator<SelectionKey> iterator = selector.selectedKeys()
                        .iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isConnectable()) {
                        System.out.println("Connecable.");
                    } else if (key.isAcceptable()) {
                        System.out.println("Acceptedable.");
                        handleAccept(key);
                    } else if (key.isReadable()) {
                        System.out.println("Readable.");
                        handleRead(key);
                    }
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            if (serverChannel != null) {
                serverChannel.close();
            }
            if (selector != null) {
                selector.close();
            }
        }
    }

    public static void handleAccept(SelectionKey key) throws IOException {
        // 接受客户端建立连接的请求
        SocketChannel clientChannel = ((ServerSocketChannel) key.channel())
                .accept();
        // 非阻塞式
        clientChannel.configureBlocking(false);
        // 注册到selector
        clientChannel.register(key.selector(), SelectionKey.OP_READ,
                ByteBuffer.allocate(1024));
        // clientChannel.register(key.selector(), SelectionKey.OP_READ
        // | SelectionKey.OP_WRITE, ByteBuffer.allocate(1024));
    }

    public static void handleRead(SelectionKey key) throws IOException {
        // 获得与客户端通信的信道
        SocketChannel clientChannel = (SocketChannel) key.channel();
        // 得到并清空缓冲区
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        if (clientChannel.read(buffer) != -1) {
            buffer.flip();
            byte[] bytes = new byte[buffer.limit()];
            int i = 0;
            while (buffer.hasRemaining()) {
                bytes[i] = buffer.get();
                i++;
            }
            System.out.println(MessageFormat.format("接收到来自[{0}]的消息[{1}]",
                    clientChannel.getRemoteAddress(), new String(bytes)));
            buffer = ByteBuffer.wrap(MessageFormat.format(
                    "你好,客户端. @[{0}]，已经收到你的信息[{1}]",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(new Date()), new String(bytes)).getBytes());
            clientChannel.write(buffer);
        } else {
            // 没有读取到内容的情况
            clientChannel.close();
        }
    }

}

