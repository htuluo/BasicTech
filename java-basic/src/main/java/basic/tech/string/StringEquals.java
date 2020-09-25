package basic.tech.string;

/**
 * @description:
 * @author: luolm
 * @createTime： 2018/10/18
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class StringEquals {
    class  Entity{
        private String aStr;

        public Entity(String aStr) {
            this.aStr = aStr;
        }

        public String getaStr() {
            return aStr;
        }

        public void setaStr(String aStr) {
            this.aStr = aStr;
        }
    }

    public static void main(String[] args) {
        String str0="asdasd  asdf  kkkk llll    mm";
        String[] s = str0.split(" ");
        System.out.println(s);
        String bStr =new String( "e1ae617c1e9d45018638e6e8b59bf508");
        Entity entity=(new StringEquals()).new Entity("e1ae617c1e9d45018638e6e8b59bf508");
        System.out.println(entity.getaStr()== bStr);
        System.out.println(entity.getaStr().equals(bStr));

    }
}
