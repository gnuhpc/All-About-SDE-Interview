package org.gnuhpc.bigdata.network.iomodel.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
    private static final int TIMEOUT = 300;
    private static final int PORT = 12112;

    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();

            ServerSocketChannel listenChannel = ServerSocketChannel.open();
            listenChannel.configureBlocking(false);
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                if (selector.select(TIMEOUT) == 0) {
                    System.out.print(".");
                    continue;
                }

                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();

                    //Server socket channel has pending connection request?
                    if (key.isAcceptable()) {
                        SocketChannel channel = listenChannel.accept();
                        channel.configureBlocking(false);
                        SelectionKey connkey = channel.register(selector, SelectionKey.OP_READ);
                        NIOServerConnection conn = new NIOServerConnection(connkey);
                        connkey.attach(conn);
                    }

                    if (key.isReadable()) {
                        NIOServerConnection conn = (NIOServerConnection) key.attachment();
                        conn.handleRead();
                    }

                    if (key.isValid() && key.isWritable()) {
                        NIOServerConnection conn = (NIOServerConnection) key.attachment();
                        conn.handleWrite();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
