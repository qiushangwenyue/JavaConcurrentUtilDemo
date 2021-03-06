package com.util.concurrent.z_atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author y15079
 * @create 2017-09-13 14:42
 * @desc
 **/
public class AtomicReferenceArrayExample {

	private static String[] source=new String[10];
	private static AtomicReferenceArray<String> atomicReferenceArray=new AtomicReferenceArray<String>(source);

	public static void main(String[] args) throws Exception{
		for (int i=0;i<atomicReferenceArray.length();i++){
			atomicReferenceArray.set(i,"item-2");
		}

		Thread t1=new Thread(new Increment());
		Thread t2=new Thread(new Compare());

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}

	static class Increment implements Runnable{
		@Override
		public void run() {
			for (int i=0;i<atomicReferenceArray.length();i++){
				String add = atomicReferenceArray.getAndSet(i,"item-"+(i+1));
				System.out.println("Thread " + Thread.currentThread().getId()
						+ ", index " +i + ", value: "+ add);
			}
		}
	}

	static class Compare implements Runnable{
		@Override
		public void run() {
			for (int i=0;i<atomicReferenceArray.length();i++){
				System.out.println("Thread "+Thread.currentThread().getId()
						+ ", index " +i + ", value: "+ atomicReferenceArray.get(i));
				boolean swapped=atomicReferenceArray.compareAndSet(i,"item-2","updated-item-"+(i+1));
				System.out.println("Item swapped: " + swapped);
				if(swapped){
					System.out.println("Thread " + Thread.currentThread().getId()
							+ ", index " +i + ", updated-item-2");
				}
			}
		}
	}
}
