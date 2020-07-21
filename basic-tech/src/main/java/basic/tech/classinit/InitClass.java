package basic.tech.classinit;

/**
 * 演示类的几种创建方式
 */
public class InitClass implements Cloneable {
    static final String Hello = "hello";
    private String proper;

    public String getProper() {
        return proper;
    }

    public void setProper(String proper) {
        this.proper = proper;
    }


    public InitClass() {
        System.out.println("init:" + Hello);
        // TODO Auto-generated constructor stub
    }
    {
        System.out.println("init000:" + Hello);
        // TODO Auto-generated constructor stub
    }
    public static void main(String[] args) {
        InitClass initClass = new InitClass();
        initClass.setProper("aaa");
//
//        System.out.println("initClass:" + initClass.toString() + " and proper =" + initClass.getProper());
//        try {
//            InitClass initClass1 = InitClass.class.newInstance();
//            initClass1.setProper("ccc");
//            System.out.println("newInstance:" + initClass1.toString() + " and proper= " + initClass1.getProper());
//            InitClass clone = (InitClass) initClass.clone();
//            clone.setProper("bbb");
//            System.out.println("cloneClass:" + clone.toString() + " and proper =" + clone.getProper());
//            System.out.println("initClass:" + initClass.toString() + " and proper =" + initClass.getProper());
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//        System.out.println(InitClass.Hello);
//        // TODO Auto-generated method stub

    }

}
