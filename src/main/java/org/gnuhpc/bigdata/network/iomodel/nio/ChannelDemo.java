package org.gnuhpc.bigdata.network.iomodel.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
/*
 * 一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 二、通道的主要实现类
 * 	java.nio.channels.Channel 接口：
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 *
 * 三、获取通道
 * 1. Java 针对支持通道的类提供了 getChannel() 方法
 * 		本地 IO：
 * 		FileInputStream/FileOutputStream
 * 		RandomAccessFile
 *
 * 		网络IO：
 * 		Socket
 * 		ServerSocket
 * 		DatagramSocket
 *
 * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *

 *
 */

public class ChannelDemo {


    //主要通过读取文件内容，写到ByteBuffer里，然后再从ByteBuffer对象中获取数据，显示到控制台
    private void readFile(String fileName) {
        try {
            //首先开启一个随机访问文件类,然后得到这个文件句柄的nio channel
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();

            //fileChannel = FileChannel.open(Paths.get(fileName),READ);

            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            // 开始进行读文件, 直到读取完毕
            while (fileChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), Charset.forName("UTF8")));
                byteBuffer.clear();
            }

            fileChannel.close();
            randomAccessFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getFile(String fileName) {
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(fileName).getFile();
    }

    //利用通道完成文件的复制（非直接缓冲区）
    public static void test1() {//10874-10953
        long start = System.currentTimeMillis();

        FileInputStream fis = null;
        FileOutputStream fos = null;
        //①获取通道
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            //过去的方式，先创建流再获取channel
            fis = new FileInputStream("d:/spark-summit-2017-SanFrancisco-master.zip");
            fos = new FileOutputStream("d:/1.zip");

            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //②分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //③将通道中的数据存入缓冲区中
            while (inChannel.read(buf) != -1) {
                buf.flip(); //切换读取数据的模式
                //④将缓冲区中的数据写入通道中
                outChannel.write(buf);
                buf.clear(); //清空缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));

    }

    //使用直接缓冲区完成文件的复制(内存映射文件)
    //效率高但是不一定稳定
    public static void test2() throws IOException {
        long start = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("d:/spark-summit-2017-SanFrancisco-master.zip"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("d:/2.zip"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
    }

    //通道之间的数据传输(直接缓冲区)
    public static void test3() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get("d:/spark-summit-2017-SanFrancisco-master.zip"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("d:/2.zip"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
    }


    //分散和聚集
    public static void test4() throws IOException {
        long start = System.currentTimeMillis();
        RandomAccessFile raf1 = new RandomAccessFile("d:/spark-summit-2017-SanFrancisco-master.zip", "rw");

        //1. 获取通道
        FileChannel channel1 = raf1.getChannel();

        //2. 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(1024 * 30);
        ByteBuffer buf2 = ByteBuffer.allocate(1024 * 30);
        ByteBuffer buf3 = ByteBuffer.allocate(1024 * 30);
        ByteBuffer buf4 = ByteBuffer.allocate(1024 * 30);
        ByteBuffer buf5 = ByteBuffer.allocate(1024 * 30);
        ByteBuffer buf6 = ByteBuffer.allocate(1024 * 30);
        ByteBuffer buf7 = ByteBuffer.allocate(1024 * 30);
        RandomAccessFile raf2 = new RandomAccessFile("D://2.zip", "rw");
        FileChannel channel2 = raf2.getChannel();

        //3. 分散读取
        ByteBuffer[] bufs = {buf1, buf2, buf3, buf4, buf5, buf6, buf7};
        while (channel1.read(bufs) != -1) {
            Arrays.stream(bufs).forEach(b -> b.flip());

            //4. 聚集写入
            channel2.write(bufs);
            Arrays.stream(bufs).forEach(b -> b.clear());
        }

        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
        channel1.close();
        channel2.close();

    }


    public static void main(String[] args) throws IOException {
//	    ChannelDemo bbDemo = new ChannelDemo();
//		bbDemo.readFile(bbDemo.getFile("test.log"));
//
//
//		test1();
//		test2();
        test3();
        test4();
    }

}
