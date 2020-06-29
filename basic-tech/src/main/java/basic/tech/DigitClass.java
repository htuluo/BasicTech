package basic.tech;

public class DigitClass {
	public static void main(String[] args) {
		Integer aInteger = 3;
		Integer bInteger = 4;
		System.out.println((aInteger << 1) + bInteger);
		System.out.println(aInteger & 1 << 2);
		System.out.println((aInteger & 1) << 2);
		System.out.println(aInteger & (1 << 2));
		System.out.println(aInteger.byteValue());
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(1<<31);//补码表示
		System.out.println(Integer.toBinaryString(1<<33));//截取低32位保留高位数字
	}

}
