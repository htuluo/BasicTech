package basic.tech;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 演示堆溢出： 
 * 需要debugArguments设置：-Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 * @author leimingluo
 *
 */
public class MemoryTest {
	static class OOMObject {
	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while (true) {
			list.add(new OOMObject());
		}
	}

}
