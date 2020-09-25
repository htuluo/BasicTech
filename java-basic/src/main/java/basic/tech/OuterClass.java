package basic.tech;

public class OuterClass {
	private Integer i = 0;
	public String string;

	public void outFunc() {
		InnerClass innerClass = new InnerClass();
		System.out.println(innerClass.i);
	}

	private class InnerClass {

		private Integer inner_j = 200;
		private Integer i = 201;

		public InnerClass() {
			System.out.println(this.inner_j);
			System.out.println(i);
			System.out.println(OuterClass.this.i);
		}
	}

	public static void main(String[] args) {
		OuterClass outerClass = new OuterClass();
		outerClass.outFunc();
		// System.out.println(outerClass.i);
		InnerClass innerClass = outerClass.new InnerClass();

	}

}
