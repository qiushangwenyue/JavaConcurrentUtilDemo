package com.util.base.a_interface;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author y15079
 * @create 2017-09-19 11:42
 * @desc
 *
 * Queue
 * 队列是一种特殊的线性表，它只允许在表的前端（front）进行删除操作，而在表的后端（rear）进行插入操作。
 * 进行插入操作的端称为队尾，进行删除操作的端称为队头。队列中没有元素时，称为空队列。
 * 在队列这种数据结构中，最先插入的元素将是最先被删除的元素；反之最后插入的元素将是最后被删除的元素，
 * 因此队列又称为“先进先出”（FIFO—first in first out）的线性表。
 *
 * 在java5中新增加了java.util.Queue接口，用以支持队列的常见操作。该接口扩展了java.util.Collection接口。
 *
 * Queue使用时要尽量避免Collection的add()和remove()方法，而是要使用offer()来加入元素，使用poll()来获取并移出元素。
 * 它们的优点是通过返回值可以判断成功与否，add()和remove()方法在失败的时候会抛出异常。
 * 如果要使用前端而不移出该元素，使用element()或者peek()方法。
 * 值得注意的是LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用
 *
 * LinkedList进行插入、删除操作效率较高
 *
 *
 * Deque
 * 双端队列是一个限定插入和删除操作的数据结构，具有队列和栈的性质。
 * 双端队列是限定插入和删除操作在表的两端进行的线性表。
 *
 * 尽管双端队列看起来似乎比栈和队列更灵活，但实际上在应用程序中远不及栈和队列有用。
 *双端队列就是一个两端都是结尾的队列。队列的每一端都可以插入数据项和移除数据项。
 * 这些方法可以叫作insertLeft()和insertRight()，以及removeLeft()和removeRight()。
 * 如果严格禁止调用insertLeft()和removeLeft()方法（或禁用右段的操作），双端队列功能就和栈一样。
 * 禁止调用insertLeft()和removeRight()（或相反的另一对方法），它的功能就和队列一样了。
 * 双端队列与栈或队列相比，是一种多用途的数据结构，在容器类库中有时会用双端队列来提供栈和队列两种功能。
 **/
public class QueueAndDequeExample {

	public static void QueueDemo(){
		Queue<String> queue=new LinkedList<String>();
		//追加元素
		queue.offer("one");
		queue.offer("two");
		queue.offer("three");
		queue.offer("four");
		//从队首取出元素并删除
		String poll=queue.poll();
		System.out.println(poll);
		System.out.println(queue);
		//从队首取出元素但是不删除
		String peek=queue.peek();
		System.out.println(peek);
		System.out.println(queue);
		//遍历队列，这里要注意，每次取完元素后都会删除，整个
		//队列会变短，所以只需要判断队列的大小即可
		while(queue.size() > 0) {
			System.out.println(queue.poll());
		}
	}

	public static void Deque(){
		Deque<String> deque=new LinkedList<String>();
		deque.push("a");
		deque.push("b");
		deque.push("c");
		System.out.println(deque);
		//获取栈首元素后，元素不会出栈
		String str=deque.peek();
		System.out.println(str);
		System.out.println(deque);
		while (deque.size()>0){
			//获取栈首元素后，元素将会出栈
			System.out.println(deque.pop());
		}
		System.out.println(deque);
	}

	public static void main(String[] args) {
		QueueDemo();
		Deque();
	}


}
