package org.gnuhpc.bigdata.network.iomodel.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class NIOServerConnection {
    private static final int BUFFSIZE = 1024;

    SelectionKey key;
    SocketChannel channel;
    ByteBuffer buffer;

    public NIOServerConnection(SelectionKey key) {
        this.key = key;
        this.channel = (SocketChannel) key.channel();
        buffer = ByteBuffer.allocate(BUFFSIZE);
    }

    public void handleRead() throws IOException {
        long bytesRead = channel.read(buffer);

        if (bytesRead == -1) {
            channel.close();
        } else {
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
    }

    public void handleWrite() throws IOException {
        buffer.flip();
        channel.write(buffer);

        if (!buffer.hasRemaining()) {
            key.interestOps(SelectionKey.OP_READ);
        }

        buffer.compact();
    }
}
