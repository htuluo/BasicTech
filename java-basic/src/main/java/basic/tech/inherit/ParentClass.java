package basic.tech.inherit;

import lombok.Data;

/**
 * 演示父子类的实例化顺序及过程
 */
@Data
public class ParentClass {
    private Integer parentInteger=1;
    private Integer sameInteger = 5;
    private static String parentStr="parent";
    protected String parentProtectStr="";
    String parentDefaultStr="";

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


    public void printInfo() {
        System.out.println("parent printInfo:" + sameInteger);
    }


}
