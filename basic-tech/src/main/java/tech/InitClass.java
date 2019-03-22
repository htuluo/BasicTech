package basic.tech;

public class InitClass {
	static final String Hello = "hello";

	public InitClass() {
		System.out.println("init:" + Hello);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		InitClass initClass = new InitClass();
		System.out.println(InitClass.Hello);
		// TODO Auto-generated method stub

	}

}
