package basic.tech.inherit;

/**
 * 演示父子类的实例化顺序及过程
 */
public class ParentClass {
    private Integer parentInteger=1;
    private Integer sameInteger = 5;
    private static String parentStr="parent";

    static {
        System.out.println("Parent static init");
    }

    public ParentClass() {
        this.parentInteger = 1;
//        printInfo();
         System.out.println("Parent Contructor init");
    }
    {
        System.out.println("Parent init" + this.sameInteger+" this.parent.hashcode="+this.hashCode());
    }




    public Integer getSameInteger() {
        return sameInteger;
    }

    public void setSameInteger(Integer sameInteger) {
        this.sameInteger = sameInteger;
    }

    public Integer getParentInteger() {
        return parentInteger;
    }

    public void setParentInteger(Integer parentInteger) {
        this.parentInteger = parentInteger;
    }


    public void printInfo() {
        System.out.println("parent printInfo:" + sameInteger);
    }


}
