wait/notify 和 sleep 方法的异同？
相同点：
它们都可以让线程阻塞。
它们都可以响应 interrupt 中断：在等待的过程中如果收到中断信号，都可以进行响应，并抛出 InterruptedException 异常。

不同点：

wait 方法必须在 synchronized 保护的代码中使用，而 sleep 方法并没有这个要求。
在同步代码中执行 sleep 方法时，并不会释放 monitor 锁，但执行 wait 方法时会主动释放 monitor 锁。
sleep 方法中会要求必须定义一个时间，时间到期后会主动恢复，而对于没有参数的 wait 方法而言，意味着永久等待，直到被中断或被唤醒才能恢复，它并不会主动恢复。
wait/notify 是 Object 类的方法，而 sleep 是 Thread 类的方法。

Pattern：
synchronized (obj) {
     while (condition does not hold)
         obj.wait();
     ... // Perform action appropriate to condition
}