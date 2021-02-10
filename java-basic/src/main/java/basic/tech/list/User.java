package basic.tech.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author ： luoleiming
 * @date ：Created in 2021/2/10
 * @description： //TODO
 */
@Data
@AllArgsConstructor
@ToString
public class User {
    private Integer age;
    private Integer sex;
    private String name;

    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        list.add(new User(12,1,"张1"));
        list.add(new User(13,1,"张2"));
        list.add(new User(14,2,"张3"));
        list.add(new User(15,1,"张3"));
        list.add(new User(12,2,"张1"));
        list.add(new User(13,1,"张1"));
        Map<Integer, List<User>> collect = list.stream().collect(Collectors.groupingBy(d -> d.getAge()));
        for (Integer key:collect.keySet()){
            System.out.println(MessageFormat.format("{0}:{1}",key,collect.get(key)));
        }

        Map<Integer, List<User>> collect2 = list.stream().collect(Collectors.groupingBy(d -> d.getSex()));
        for (Integer key:collect2.keySet()){
            System.out.println(MessageFormat.format("{0}:{1}",key,collect2.get(key)));
        }

        Map<String, List<User>> collect3 = list.stream().collect(Collectors.groupingBy(d->getGroupKey(d)));
        for (String key:collect3.keySet()){
            System.out.println(MessageFormat.format("{0}:{1}",key,collect3.get(key)));
        }

        List<Integer> collect1 = list.stream().map(User::getAge).collect(Collectors.toList());
        boolean b = list.stream().anyMatch(d -> d.getSex().equals(1));
        IntStream intStream = list.stream().mapToInt(User::getAge);
        System.out.println(intStream);
        System.out.println(collect1);
    }

    private static String getGroupKey(User user){
        return MessageFormat.format("{0}_{1}",user.getName(),user.getSex());
    }
}
