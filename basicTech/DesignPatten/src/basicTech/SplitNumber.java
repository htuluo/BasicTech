package basicTech;

/**
 * @author leimingluo 把一个整数拆成连续相加的形式
 */
public class SplitNumber {


	public static void main(String[] args) {
		Integer nInteger = 15;
		for (int i = 2; i < nInteger / 2; i++) {
			if (2 * nInteger % i == 0 && (2 * nInteger / i + 1 - i) % 2 == 0) {
				Integer a1 = (2 * nInteger / i + 1 - i) / 2;
				for (int j = 0; j < i; j++) {
					System.out.print(a1);
					a1++;
				}
			}
			System.out.println();
		}
	}

}
