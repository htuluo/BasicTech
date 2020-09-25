package basic.tech.leetcode;

import java.util.Scanner;

/**
 * @author ： luolm
 * @date ：Created in 2020/9/17
 * @description： //TODO
 */
public class LongestCommonSubStr {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str1 = scanner.nextLine().toLowerCase();
            String str2 = scanner.nextLine().toLowerCase();
//            lcs(str1, str2);
//            System.out.println(getCommonStrLength(str1, str2));
            System.out.println(getMaxSubString(str1, str2));
        }
    }

    private static int getCommonStrLength(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                dp[i][j] = 0;
            }
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        int max = 0;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

    public static void lcs(String x, String y) {
        // 定义动态规划二维数组
        int[][] c = new int[x.length() + 1][y.length() + 1];
        // 最长公共子串长度
        int maxLength = 0;
        // 可能出现的最长公共子串的个数
        int count = 1;
        // 用于存储最长公共子串末尾元素索引的数组
        int[] index = new int[x.length()];

        for (int j = 0; j < y.length() + 1; j++) {
            for (int i = 0; i < x.length() + 1; i++) {
                // 当i=0||j=0时 c[i,j]=0
                // 定义动态数组时，数组内元素默认值为0
                // 此处if语句方便理解算法
                if ((i == 0) || (j == 0)) {
                    c[i][j] = 0;
                    // 当xi=yj时 c[i,j]=c[i-1,j-1]+1
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;

                    if (c[i][j] > maxLength) {
                        maxLength = c[i][j];
                        // 每一次最长公共子串长度更新，重置索引数组
                        // 每一次最长公共子串长度更新，重置个数
                        for (int k = 0; k < count; k++) {
                            index[k] = 0;
                        }
                        count = 1;
                        index[count - 1] = i;

                        // 如果c[i][j]==maxLength，则说明出现新的一个LCS
                        // 索引数组增加一个元素用于存储新LCS的索引
                        // 子串个数+1
                    } else if (c[i][j] == maxLength) {
                        index[count] = i;
                        count++;
                    }

                    // 当xi!=yj时 0
                } else {
                    c[i][j] = 0;
                }

            }
        }

        // 打印动态规划表c[i,j]
        System.out.println("动态规划表c[i,j]:");
        for (int j = 0; j < y.length() + 1; j++) {
            for (int i = 0; i < x.length() + 1; i++) {
                System.out.print(c[i][j] + "\t");
            }
            System.out.println();
        }

        // 打印最长公共子串
        for (int i = 1; i <= count; i++) {
            System.out.print("Longest Common Substring " + i + ": ");
            for (int j = maxLength; j > 0; j--) {
                System.out.print(x.charAt(index[i - 1] - j));
            }
            System.out.println();
        }

        System.out.println("Amount of Longest Common Substring: " + count);

        System.out.println("Max length of Longest Common Substring length: " + maxLength);

    }

    /**
     * 求最长子串
     * @param str1
     * @param str2
     * @return
     */
    public static String getMaxSubString(String str1,String str2){
        //定义两个变量，用来存储待判断的字符串
        String max="";
        String min="";
        //一些基础的判断
        if(str1==null||str2==null||str1.isEmpty()||str2.isEmpty()){
            return null;
        }
        if(str1.length()>=str2.length()){
            max=str1;
            min=str2;
        }else{
            max=str2;
            min=str1;
        }
        //对长度较短的字符串依次取其子串（n,n-1,n-2......1）
        for(int i=0;i<min.length();i++){
            //每次子串长度减一，判断大串是否包含子串
            for(int beg=0,end=min.length()-i;end<=min.length();beg++,end++){
                //取子串
                String s=min.substring(beg,end);
                //如果大串中含有子串，则返回子串，该子串就是最大的公共子串
                //这里也可以使用java中String类提供的contains()方法，这里选择自己写一个类似的方法
                if(containString(max,s)){
                    return s;
                }
            }
        }
        //否则返回null，表示不存在公共子串
        return null;
    }

    /**
     * 判断一个字符串是否包含另一个字符串
     * @param str1
     * @param str2
     * @return
     */
    public static boolean containString(String str1,String str2){
        //定义两个变量，用来存储两个子串
        String min="";
        String max="";
        if(str1==null||str2==null){
            return false;
        }
        if(str1.length()>=str2.length()){
            max=str1;
            min=str2;
        }else{
            max=str2;
            min=str1;
        }
        //定义一个变量，用来存储判断过程中出现的字符串
        String s="";
        //循环判断两个字符串
        for(int i=0;i<max.length();i++){
            //定义并保存一个变量i的副本
            int index=i;
            for(int j=0;j<min.length();j++){
                //如果两个字符相等，保存这个字符，并将index+1
                if(max.charAt(index)==min.charAt(j)){
                    s+=min.charAt(j)+"";
                    index++;
                }else{//否则的话重置所有中间变量，并退出内层循环，使得i++
                    s="";
                    j=0;
                    index=i;
                    break;
                }
            }
            //每次内层循环结束后，判断s是否等于min，如果相等则返回true，否则继续循环
            if(s.equals(min)){
                return true;
            }
        }
        return false;
    }

}
