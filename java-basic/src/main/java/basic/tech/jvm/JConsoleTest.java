package basic.tech.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leimingluo
 *
 *         设置JVM参数： -Xms50m -Xmx50m -XX:+PrintGCDetails -XX:+UseSerialGC
 */
public class JConsoleTest {
	static class OOMObject {
		public byte[] placeHolder = new byte[64 * 1024];

	}

	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			list.add(new OOMObject());
			Thread.sleep(50);
		}
		System.gc();
	}
	public static void main(String[] args) {
		try {
			fillHeap(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

}
