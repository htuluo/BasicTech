package basic.tech.leetcode;

import io.netty.util.internal.StringUtil;

import java.text.MessageFormat;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 第三题
 * @description: 字符串的最长子串,
 * @author: luolm
 * @createTime： 2019/7/9
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class LongestSubStr {
    public static void main(String[] args) {
//        Queue<Character> queue = new ArrayBlockingQueue<Character>(20);
//        queue.add(new Character('a'));
//        queue.add(new Character('b'));
//        queue.add(new Character('c'));
//        System.out.println(queue);

        String str = "abcabcbb";
        System.out.println("str is " + str);
        longestSubString(str);
        str="bbbb";
        System.out.println("str is " + str);
        longestSubString(str);
        str="pwwkew";
        System.out.println("str is " + str);
        longestSubString(str);
    }

    /**
     * 不含重复字符最长子串，
     * @param str
     * @return
     */
    public static String longestSubString(String str) {
        if (StringUtil.isNullOrEmpty(str)) {

            return null;
        }
        int maxLenth = 0;
        int startIndex = 0;
        char[] strArray = str.toCharArray();
        Queue<Character> queue = new LinkedBlockingDeque<>();

        for (int i = 0; i < strArray.length; i++) {
            char c = strArray[i];
            int repeatIndex = 0;
            if (maxLenth < queue.size()) {
                maxLenth = queue.size();
                startIndex=i-maxLenth;
            }
            for (Character character : queue) {
                repeatIndex++;
                if (character.equals(c)) {
                    for (int j = 0; j < repeatIndex; j++) {
                        queue.remove();
                    }
                    break;
                }
            }
            queue.add(new Character(c));
//            System.out.println(queue);
        }

        System.out.println(MessageFormat.format("maxLenth={0},startIndex={1}", maxLenth, startIndex));
        System.out.println(String.copyValueOf(strArray, startIndex, maxLenth));

        return null;
    }
}
