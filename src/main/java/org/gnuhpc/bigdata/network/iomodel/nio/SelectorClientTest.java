package org.gnuhpc.bigdata.network.iomodel.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class SelectorClientTest implements Runnable {

    private Selector selector;

    public SelectorClientTest(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            // select()方法只能使用一次，用了之后就会自动删除,每个连接到服务器的选择器都是独立的
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys()
                        .iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        if (channel.read(buffer) != -1) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.limit()];
                            int i = 0;
                            while (buffer.hasRemaining()) {
                                bytes[i] = buffer.get();
                                i++;
                            }
                            System.out.println(MessageFormat
                                    .format("Server返回Response为[{0}]",
                                            new String(bytes)));
                        } else {
                            channel.close();
                        }
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        Selector selector = null;
        SocketChannel channel = null;
        Scanner scanner = null;
        try {
            // 创建Selector
            selector = Selector.open();
            // 创建非阻塞Socket Channel
            channel = SocketChannel.open(new InetSocketAddress("127.0.0.1",
                    7994));
            channel.configureBlocking(false);
            // channel.connect();

            channel.register(selector, SelectionKey.OP_READ
                    | SelectionKey.OP_WRITE);

            System.out.print("Connection setting up.");
            while (!channel.isConnected()) {
                System.out.print(".");
                Thread.sleep(1000);
            }
            System.out.println();

            channel.write(ByteBuffer.wrap("Client startup..".getBytes()));

            // 监听Selector的各种事件
            Executors.newFixedThreadPool(1).submit(
                    new SelectorClientTest(selector));

            while (true) {
                // 控制台输入信息发送给Server
                scanner = new Scanner(System.in);
                channel.write(ByteBuffer.wrap(scanner.next().getBytes()));
            }

        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (channel != null) {
                channel.close();
            }
            if (selector != null) {
                selector.close();
            }
        }
    }
}
