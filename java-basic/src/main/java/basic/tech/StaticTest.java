package basic.tech;

public class StaticTest {
	static class Parent {
		static Integer A = 1;
		static {
			A = 2;
			System.out.println("Parent static init");
		}
	}

	static class Child extends Parent {
		public static Integer B = A=3;
		static {
			System.out.println("child static init");
		}

		{
			System.out.println("child non-static init");

		}
	}

	public static void main(String[] args) {
		System.out.println(Child.B);
	}

}
