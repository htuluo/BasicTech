package basic.tech.regex;

import javax.sound.midi.Soundbank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ： luoleiming
 * @date ：Created in 2021/2/7
 * @description： //Regex替换
 */
public class RegexTest {
    public static void main(String[] args) {
        String text="$$1$$&&$$2$$";
        String regex="\\$\\$(\\d+)\\$\\$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            text=text.replace(matcher.group(),matcher.group(1));
        }
        System.out.println(text);

    }
}
