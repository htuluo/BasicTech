package basicTech.multiThread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskManager {
	public static BlockingQueue<Integer> taskqueue = new LinkedBlockingQueue();

	public static void main(String[] args) {
		ExecutorService taskexecutor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 200; i++) {
			taskqueue.add(i);
		}

		System.out.println("ssssssssssss" + taskqueue.size());
		for (int i = 0; i < 200; i++) {
			// System.out.println(taskqueue.size());
			Task task = new Task(i);
			taskexecutor.submit(task);
		}
		taskexecutor.shutdown();
		System.out.println("ok");

	}

}
