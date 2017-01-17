package basicTech;

public class ParentClass {
	private Integer parentInteger;
	{
		System.out.println("Parent init");
	}

	static {
		System.out.println("Parent static init");
	}
	public ParentClass() {
		this.parentInteger = 1;
		// System.out.println(this.parentInteger);
	}

	public Integer getParentInteger() {
		return parentInteger;
	}

	public void setParentInteger(Integer parentInteger) {
		this.parentInteger = parentInteger;
	}

}
