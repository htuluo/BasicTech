package basic.tech;

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

    public static void main(String[] args) {
        InitClass initClass = new InitClass();
        initClass.setProper("aaa");

        System.out.println("initClass:" + initClass.toString() + " and proper =" + initClass.getProper());
        try {
            InitClass clone = (InitClass) initClass.clone();
            clone.setProper("bbb");
            System.out.println(clone.toString() + " and proper =" + clone.getProper());
            System.out.println("initClass:" + initClass.toString() + " and proper =" + initClass.getProper());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(InitClass.Hello);
        // TODO Auto-generated method stub

    }

}
