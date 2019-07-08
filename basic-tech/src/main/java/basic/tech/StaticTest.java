package basic.tech;

public class StaticTest {
	static class Parent {
		static Integer A = 1;
		static {
			A = 2;
		}
	}

	static class Child extends Parent {
		public static Integer B = A=3;
	}

	public static void main(String[] args) {
		System.out.println(Child.B);
	}

}
