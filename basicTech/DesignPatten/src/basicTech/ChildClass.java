package basicTech;

public class ChildClass extends ParentClass {
	private Integer childInteger;
	{
		System.out.println("Children init");
	}

	static {
		System.out.println("Children static init");
	}
	public Integer getChildInteger() {
		return childInteger;
	}

	public void setChildInteger(Integer childInteger) {
		this.childInteger = childInteger;
	}

	public static void main(String[] args) {

		new ChildClass();
		// System.out.println(sChildClass.getChildInteger());
		// System.out.println(sChildClass.getParentInteger());
	}

}
