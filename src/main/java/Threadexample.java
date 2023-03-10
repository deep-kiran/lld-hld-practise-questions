// Java program to implement solution of producer
// consumer problem.

import java.util.LinkedList;

public class Threadexample {
	public static void main(String[] args)
		throws InterruptedException {
		final PC pc = new PC();

		// Create producer thread
		Thread t1 = new Thread(() -> {
			try {
				pc.produce();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		// Create consumer thread
		Thread t2 = new Thread(() -> {
			try {
				pc.consume();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
	public static class PC {

		// Create a list shared by producer and consumer
		// Size of list is 2.
		LinkedList<Integer> list = new LinkedList<>();
		int capacity = 2;

		// Function called by producer thread
		public void produce() throws InterruptedException {
			int value = 0;
			while (true) {
				synchronized (this) {
					while (list.size() == capacity)
						wait();

					System.out.println("Producer produced-"
									+ value);

					// to insert the jobs in the list
					list.add(value++);
					notify();
					Thread.sleep(1000);
				}
			}
		}

		public void consume() throws InterruptedException {
			while (true) {
				synchronized (this) {
					while (list.size() == 0)
						wait();
					int val = list.removeFirst();
					System.out.println("Consumer consumed-"
									+ val);
					notify();
					Thread.sleep(1000);
				}
			}
		}
	}
}
