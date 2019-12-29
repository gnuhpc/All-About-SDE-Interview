package org.gnuhpc.bigdata.concurrency.basic;

class Something {
    // final的实例字段
    private final  int       x;
    // 静态字段
    private static Something last = null;

    // 构造函数
    private Something() {
        // 显式地初始化final字段
        x = 123;
    }

    // 将使用new关键字创建的实例赋值给last
    public static Something create() {
        last = new Something();
        return last;
    }

    // 通过last显示final字段的值
    public static void print() {
        if (last != null) {
            System.out.println(last.x);
        }
    }
}

/*
final 关键字可以保证构造函数处理结束后final字段被正确的初始化，对其他线程可见
 */
public class FinalDemo {
    public static void main(String[] args) {
        // 线程A
        new Thread() {
            public void run() {
                Something.create();
            }
        }.start();

        // 线程B
        new Thread() {
            public void run() {
                Something.print();
            }
        }.start();
    }
}
