package com.util.concurrent.a_unlock;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author y15079
 * @create 2017-09-07 19:35
 * @desc ConcurrentLinkedQueue是一个基于链表实现的无界线程安全队列，它采用先进先出的规则对节点进行排序，
 * 当我们添加一个元素的时候，它会添加到队列的尾部，当我们获取一个元素时，它会返回队列头部的元素。
 * 默认情况下head节点存储的元素为空，tair节点等于head节点。
 * <p>
 * <p>
 * 观察入队和出队的源码可以发现，无论入队还是出队，都是在死循环中进行的，
 * 也就是说，当一个线程调用了入队、出队操作时，会尝试获取链表的tail、head结点进行插入和删除操作，
 * 而插入和删除是通过CAS操作实现的，而CAS具有原子性。
 * 故此，如果有其他任何一个线程成功执行了插入、删除都会改变tail/head结点，
 * 那么当前线程的插入和删除操作就会失败，则通过循环再次定位tail、head结点位置进行插入、删除，直到成功为止。
 * 也就是说，ConcurrentLinkedQueue的线程安全是通过其插入、删除时采取CAS操作来保证的。
 * 不会出现同一个tail结点的next指针被多个同时插入的结点所抢夺的情况出现。
 **/
public class ConcurrentLinkedQueueExample {
	private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
	private static int count = 2;//线程个数
	//CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
	private static CountDownLatch latch = new CountDownLatch(count);

	public static void main(String[] args) throws InterruptedException {
		long timeStart = System.currentTimeMillis();
		ExecutorService es = Executors.newFixedThreadPool(4);
        //往ConcurrentLinkedQueue队列添加数据
		offer();
		for (int i=0;i<count;i++){
			//线程池中的线程逐个启动
			es.submit(new Poll());
		}
		//使得主线程（main）阻塞直到latch.countDown()为零才继续执行
		latch.await();
		System.out.println("cost time "+ (System.currentTimeMillis() - timeStart) + "ms");
		es.shutdown();
	}

	public static void offer() {
		for (int i = 0; i < 100000; i++) {
			queue.offer(i);
		}
	}

	/**
	 * 消费线程，ConcurrentLinkedQueue队列移除一个元素
	 *
	 * 结果居然相差那么大，看了下ConcurrentLinkedQueue的API原来.size()是要遍历一遍集合的
	 * ，难怪那么慢，所以尽量要避免用size而改用isEmpty().
	 */
	static class Poll implements Runnable{
		@Override
		public void run() {
//			while (queue.size()>0){
			while (!queue.isEmpty()){
//				queue.poll();
				System.out.println(queue.poll());
			}
			latch.countDown();
		}
	}
}
