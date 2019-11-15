package basic.tech.list;

import java.util.ArrayList;
import java.util.List;

public class ForEach {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {

			list.add(i);
		}

		for (Integer i : list) {
			if (i == 3) {
				list.remove(i);
				list.iterator().next();
			}
		}
		for (Integer i : list) {
			System.out.println(i);
		}

	}

}
