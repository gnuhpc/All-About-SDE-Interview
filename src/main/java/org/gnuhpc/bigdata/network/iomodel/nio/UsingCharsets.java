package org.gnuhpc.bigdata.network.iomodel.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

/*
 * 字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组  -> 字符串
 */

public class UsingCharsets {
    public static void printAvailableCharsets() {
        Map<String, Charset> map = Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    public static void main(String[] args) throws IOException {

        printAvailableCharsets();

        Charset utf8 = StandardCharsets.UTF_8;

        String hello = "Hello world from José";
        System.out.println("Length = " + hello.length());


        CharBuffer charBuffer = CharBuffer.allocate(1024 * 1024);

        charBuffer.put(hello);
        charBuffer.flip();

        //编码为Bytebuffer
        ByteBuffer buffer = utf8.encode(charBuffer);
        Path path = Paths.get("d:\\temp\\hello.txt");
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

            //写入channel
            channel.write(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("AbstractFile size = " + Files.size(path));

        path = Paths.get("d:\\temp\\hello.txt");
        buffer.clear();
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {

            channel.read(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer.flip();
        //解码为UTF8的charbuffer
        charBuffer = utf8.decode(buffer);

        //转换为String
        String result = new String(charBuffer.array());
        System.out.println(result);
    }
}

