package basic.tech.inherit;

public class ChildClass extends ParentClass {
    private Integer childInteger;
    private Integer sameInteger = 12;

    public Integer getSameInteger() {
        return sameInteger;
    }

    public void setSameInteger(Integer sameInteger) {
        this.sameInteger = sameInteger;
    }

    {
        System.out.println("Children init:" + this.sameInteger+" this.hashcode="+this.hashCode());
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

    public void printInfo() {

        System.out.println("child printInfo:" + sameInteger+" this.hashcode="+this.hashCode());
    }

    public static void main(String[] args) {

        ChildClass childClass = new ChildClass();
        System.out.println(childClass.getParentInteger());
        System.out.println(childClass.getSameInteger());
        // System.out.println(sChildClass.getParentInteger());
    }

}
