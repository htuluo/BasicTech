package basic.tech.consumer;

import java.util.function.Consumer;

public class ConsumerTest {
    public static void main(String[] args) {
        Consumer<String> consumer=item-> System.out.println(item);
        Consumer<? super String> after=item-> System.out.println("item after:"+item);
//        consumer.accept("bbb");
        consumer.andThen(after).accept("BBB");
    }
}
