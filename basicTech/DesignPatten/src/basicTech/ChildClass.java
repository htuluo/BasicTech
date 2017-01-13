package basicTech;

public class ChildClass extends ParentClass {
	private Integer childInteger;

	public Integer getChildInteger() {
		return childInteger;
	}

	public void setChildInteger(Integer childInteger) {
		this.childInteger = childInteger;
	}

	public static void main(String[] args) {

		ChildClass sChildClass = new ChildClass();
		System.out.println(sChildClass.getChildInteger());
		System.out.println(sChildClass.getParentInteger());
	}

}
