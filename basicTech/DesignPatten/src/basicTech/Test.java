package basicTech;

import basicTech.Bean.Bean3;

class Bean {
	class Bean3 {
		public int k = 0;
	}
}

public class Test {
	class Bean1 {
		public int I = 0;
	}

	static class Bean2 {
		public int J = 0;
	}

	public static void main(String[] args){
		Integer integer = 4;
		int d = 4;
		String dd = "dssss";
		String[] aaStrings = new String[10];
		aaStrings[1] = "bb";

		System.out.println(dd.compareTo("aaa"));
		System.out.println(integer.hashCode());
		System.out.println(dd.hashCode());
		System.out.println(new Integer("2") == 2);
		System.out.println(new Integer("226").equals(226));
		System.out.println(new Integer("999").equals(999));

		for (int i = 0; i < 100;) {
			System.out.println("222_" + i);
			if (++i == 3) {
				System.out.println(i);
				break;
			}

		}

           // 初始化Bean1
		Bean1 bean1 = (new Test()).new Bean1();
           bean1.I++;
           // 初始化Bean2
		Bean2 bean2 = new Bean2();
           bean2.J++;
           //初始化Bean3
		Bean3 bean3 = (new Bean()).new Bean3();
           bean3.k++;
    }
}