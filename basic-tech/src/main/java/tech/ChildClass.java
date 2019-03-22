package basic.tech;

public class ChildClass extends ParentClass {
	private Integer childInteger;
	private Integer sameInteger;

	public Integer getSameInteger() {
		return sameInteger;
	}

	public void setSameInteger(Integer sameInteger) {
		this.sameInteger = sameInteger;
	}

	{
		System.out.println("Children init"+this.sameInteger);
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

		ChildClass childClass = new ChildClass();
		System.out.println(childClass.getSameInteger());
		// System.out.println(sChildClass.getChildInteger());
		// System.out.println(sChildClass.getParentInteger());
	}

}
