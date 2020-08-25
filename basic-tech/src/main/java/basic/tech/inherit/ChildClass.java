package basic.tech.inherit;

public class ChildClass extends ParentClass {
    private Integer childInteger;
    private Integer sameInteger = 12;


    {
        System.out.println("Children init:" + this.sameInteger + " this.hashcode=" + this.hashCode());
    }

    static {
        System.out.println("Children static init");
    }

    public ChildClass() {
        System.out.println("Children Constructor init");
    }

    private static String childStr = "child";

    @Override
    public Integer getSameInteger() {
        return sameInteger;
    }

    @Override
    public void setSameInteger(Integer sameInteger) {
        this.sameInteger = sameInteger;
    }

    public Integer getChildInteger() {
        return childInteger;
    }

    public void setChildInteger(Integer childInteger) {
        this.childInteger = childInteger;
    }

    @Override
    public void printInfo() {

        System.out.println("child printInfo:" + sameInteger + " this.hashcode=" + this.hashCode());
    }

    public static void main(String[] args) {

        ChildClass childClass = new ChildClass();
        childClass.parentProtectStr="ddd";
        childClass.parentDefaultStr="aaa";
//        childClass=new ChildClass();
//        System.out.println(childClass.parentProtectStr);
//        System.out.println(childClass.getParentInteger());
//        System.out.println(childClass.getSameInteger());
        // System.out.println(sChildClass.getParentInteger());
    }

}
