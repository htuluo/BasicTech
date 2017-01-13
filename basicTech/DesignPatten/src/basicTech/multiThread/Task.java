package basicTech.multiThread;

public class Task extends Thread {
	private Integer taskNum;

	public Task() {
	}

	public Task(Integer taskNum) {
		this.taskNum=taskNum;
	}

	@Override
	public void run() {
		// System.out.println(this.taskNum);
		Integer integer;
		// Thread.sleep(10);
		integer = TaskManager.taskqueue.poll();
		System.out.println(integer);

	}

}
