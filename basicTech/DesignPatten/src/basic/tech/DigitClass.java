package basic.tech;

public class DigitClass {
	public static void main(String[] args) {
		Integer aInteger = 3;
		Integer bInteger = 4;
		System.out.println((aInteger << 1) + bInteger);
		System.out.println(aInteger & 1 << 2);
		System.out.println((aInteger & 1) << 2);
		System.out.println(aInteger & (1 << 2));
	}

}
