package basic.tech;

import java.util.Arrays;

public class CharCode {
	public static void main(String[] args) {
		String string = "eee";
		char[] charNumber = new char[] { 'd', 'c' };
		System.out.println(string + String.valueOf(charNumber));
		System.out.println(Arrays.toString(charNumber));
		System.out.println(charNumber);
		
		char[] copyOf = Arrays.copyOf(charNumber, 2);
		System.out.println(copyOf.hashCode() + ":" + (charNumber.hashCode()));
		System.out.println(charNumber);
		System.out.println(copyOf);
	}

}
