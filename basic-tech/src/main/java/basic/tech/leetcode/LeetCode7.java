package basic.tech.leetcode;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/7/15
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class LeetCode7 {
    public static void main(String[] args) {
//        Integer integer = new Integer("-111111");
//        System.out.println(integer);
        int x=(1<<31)-1;
        String s=String.valueOf(x);
        String reverse=null;
        if (s.startsWith("-")){
            reverse= reverse(s.substring(1).toCharArray());
            reverse="-"+reverse;
        }else {
            reverse = reverse(s.toCharArray());
        }
        try {
            System.out.println(new Integer(reverse));

        }catch (Exception e){
            System.out.println(0);
        }
    }
    public static String reverse(char[] chars){
        StringBuilder stringBuilder=new StringBuilder("");
        boolean start=false;
        for (int i=chars.length-1;i>=0;i--){
            if (chars[i]=='0'&&start){
                start=true;
                continue;
            }else {
                start=false;
                stringBuilder.append(chars[i]);
            }

        }
        return stringBuilder.toString();
    }
}
