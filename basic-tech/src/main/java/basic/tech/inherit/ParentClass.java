package basic.tech.inherit;

/**
 * 演示父子类的实例化顺序及过程
 */
public class ParentClass {
    private Integer parentInteger;
    private Integer sameInteger = 5;

    static {
        System.out.println("Parent static init");
    }

    {
        System.out.println("Parent init" + this.sameInteger+" this.parent.hashcode="+this.hashCode());
    }


    public ParentClass() {
        this.parentInteger = 1;
        printInfo();
        // System.out.println(this.parentInteger);
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
