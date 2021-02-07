package basic.tech.regex;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ： luoleiming
 * @date ：Created in 2021/2/7
 * @description： //Regex替换
 */
public class RegexTest {
    public static void main(String[] args) {
        replaceRegex2();

    }

    private static void regexTest() {
        String text = "$1$$&&$2$||$1$";
        String regex = "\\$(\\d+)\\$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            text = text.replace(matcher.group(), matcher.group(1));
        }
        System.out.println(text);
    }

    public static void replaceRegex() {
        String text = "11&&1&&2&&111";
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("11", "a");
        stringObjectHashMap.put("111", "b");
        stringObjectHashMap.put("1", "c");
        String a = null;
        while (matcher.find()) {
                String group = matcher.group();
            Optional<String> first = stringObjectHashMap.keySet().stream().filter(item -> {
                return item.equals(group);
            }).findFirst();
            if (first.isPresent()) {
                a = matcher.replaceFirst(stringObjectHashMap.get(first.get()).toString());
                matcher.reset();
                matcher=pattern.matcher(a);

            }
        }
        System.out.println(a.toString());

    }

    public static void replaceRegex2() {
        String text = "11&&1&&2&&111";
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("11", "a");
        stringObjectHashMap.put("111", "b");
        stringObjectHashMap.put("1", "c");
        String a = text;
        while (matcher.find()) {
            String group = matcher.group();
            Optional<String> first = stringObjectHashMap.keySet().stream().filter(item -> {
                return item.equals(group);
            }).findFirst();
            if (first.isPresent()) {
                StringBuilder sb=new StringBuilder(a);
                sb.replace(matcher.start(),matcher.end(),stringObjectHashMap.get(first.get()).toString());
                a=sb.toString();
                matcher=pattern.matcher(a);

            }
        }
        System.out.println(a.toString());

    }
}
