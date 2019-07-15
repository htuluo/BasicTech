package basic.tech.leecode;

import io.netty.util.internal.StringUtil;

/**
 * @description:  第5题最长回文子串
 * @author: luolm
 * @createTime： 2019/7/15
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class LeetCode5 {
    public static void main(String[] args) {
        System.out.println(longestString("aabmccmb"));
    }
    public static String longestString(String str){
        if (StringUtil.isNullOrEmpty(str)){
            return "";
        }
        char[] array=str.toCharArray();
        int maxLength=0,indx=0;

        int status=0;
        for (int i=1;i<array.length;i++){
           int tmpMaxLength=0;
            if (array[i-1]==array[i]){
                status=1;
                tmpMaxLength=2;
                for (int j=i-2;j>=0;j--){
                    int k=i-j;
                    if ((i+k-1)<array.length&&array[i-k]==array[i+k-1]){
                        tmpMaxLength+=2;
                    }else {
                        break;
                    }
                }
            }else {
                tmpMaxLength=1;
                for (int j=i-1;j>=0;j--){
                    int k=i-j;
                    if ((i+k)<array.length&&array[i-k]==array[i+k]){
                        status=2;
                        tmpMaxLength+=2;
                    }else {
                        break;
                    }
                }
            }
            if (maxLength<tmpMaxLength){
                maxLength=tmpMaxLength;
                indx=i;
            }



        }
        if (status>0){
            return str.substring(indx-maxLength/2,indx-maxLength/2+maxLength);
        }
//        if (status==2){
//            return str.substring(indx-maxLength/2,indx-maxLength/2+maxLength);
//        }
        return "";
    }
}
