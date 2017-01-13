package basicTech;

public class ParentClass {
	private Integer parentInteger;

	public ParentClass() {
		this.parentInteger = 1;
		System.out.println(this.parentInteger);
	}

	public Integer getParentInteger() {
		return parentInteger;
	}

	public void setParentInteger(Integer parentInteger) {
		this.parentInteger = parentInteger;
	}

}
