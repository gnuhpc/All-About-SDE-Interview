package org.gnuhpc.bigdata.network.iomodel.nio;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.io.IOError;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/*
 * 一、缓冲区（Buffer）：在 Java NIO 中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
 *
 * 根据数据类型不同（boolean 除外），提供了相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
 *
 * 二、缓冲区存取数据的两个核心方法：
 * put() : 存入数据到缓冲区中
 * get() : 获取缓冲区中的数据
 *
 * 三、缓冲区中的四个核心属性：
 * capacity : 容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 * limit : 界限，表示缓冲区中可以操作数据的大小。（limit 后数据不能进行读写）
 * position : 位置，表示缓冲区中正在操作数据的位置。
 *
 * mark : 标记，表示记录当前 position 的位置。可以通过 reset() 恢复到 mark 的位置
 *
 * 0 <= mark <= position <= limit <= capacity
 *
 * 四、直接缓冲区与非直接缓冲区：
 * 非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中
 * 直接缓冲区：通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率
 */


public class ReadingWritingBuffers {
    public static void test1() {
        System.out.println("-----------------test1()----------------");
        String str = "abcde";

        //1. 分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("-----------------allocate()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //2. 利用 put() 存入数据到缓冲区中
        buf.put(str.getBytes());

        System.out.println("-----------------put()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //3. 切换读取数据模式
        buf.flip();

        System.out.println("-----------------flip()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //4. 利用 get() 读取缓冲区中的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));

        System.out.println("-----------------get()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //5. rewind() : 可重复读
        buf.rewind();

        System.out.println("-----------------rewind()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //6. clear() : 清空缓冲区. 但是缓冲区中的数据依然存在，但是处于“被遗忘”状态
        buf.clear();

        System.out.println("-----------------clear()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        System.out.println((char) buf.get());

    }

    public static void test2() {
        System.out.println("-----------------test2()----------------");
        String str = "abcde";

        ByteBuffer buf = ByteBuffer.allocate(1024);

        buf.put(str.getBytes());

        buf.flip();

        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println(buf.position());

        //mark() : 标记
        buf.mark();

        buf.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(buf.position());

        //reset() : 恢复到 mark 的位置
        buf.reset();
        System.out.println(buf.position());

        //判断缓冲区中是否还有剩余数据
        if (buf.hasRemaining()) {

            //获取缓冲区中可以操作的数量
            System.out.println(buf.remaining());
        }
    }

    public static void test3() {
        //分配直接缓冲区
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);

        System.out.println(buf.isDirect());
    }

    public static void test4() {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        int[] array = new int[]{3, 5, 1};
        intBuffer = intBuffer.wrap(array);
        //intBuffer = intBuffer.wrap(array,0,2);

        intBuffer.put(0, 7);

        for (int i = 0; i < intBuffer.limit(); i++) {
            System.out.println(intBuffer.get());
        }

        Arrays.stream(array).forEach(System.out::println);

        IntBuffer intBuffer2 = intBuffer.duplicate();
        //注意复制完前边如果有操作则需要flip。
        intBuffer2.flip();
        for (int i = 0; i < intBuffer2.limit(); i++) {
            System.out.println(intBuffer2.get());
        }
    }

    public static void main(String[] args) throws IOException {

//		ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
//
//		buffer.putInt(10);
//		buffer.putInt(20);
//		buffer.putInt(30);
//		System.out.println("Buffer Position = " + buffer.position());
//		System.out.println("Buffer Limit    = " + buffer.limit());
//
//		buffer.flip();
//        System.out.println("After flipping, Position = " + buffer.position());
//        System.out.println("After flipping, Limit    = " + buffer.limit());
//		IntBuffer intBuffer = buffer.asIntBuffer();
//		List<Integer> ints = new ArrayList<>();
//		try {
//			while (true) {
//				ints.add(intBuffer.get());
//			}
//		} catch (BufferUnderflowException e) {
//		}
//		ints.forEach(System.out::println);
//		System.out.println("Int arraylist Size = " + ints.size());
//		System.out.println("Intbuffer Position = " + intBuffer.position());
//		System.out.println("Intbuffer Limit    = " + intBuffer.limit());
//
//        System.out.println("Buffer Position = " + buffer.position());
//        System.out.println("Buffer Limit    = " + buffer.limit());
//
//		Path path = Paths.get("d:\\temp\\ints.bin");
//		//放在try内部可以保证关闭
//		try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);) {
//			fileChannel.write(buffer);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("AbstractFile size = " + Files.size(path));
//
//		try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);) {
//			buffer.clear();
//			//从Channel中读数据到buffer
//			fileChannel.read(buffer);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("Position = " + buffer.position());
//		System.out.println("Limit    = " + buffer.limit());
//
//
//        System.out.println("Read from buffer:");
//        buffer.flip();
//        try {
//            while (true) {
//                System.out.println(buffer.getInt());
//            }
//        } catch (BufferUnderflowException e) {
//        }

//        test1();
//        test2();
//        test3();
        test4();

    }
}
