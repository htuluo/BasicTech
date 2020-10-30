package basic.tech.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ： luoleiming
 * @date ：Created in 2020/10/29
 * @description： //TODO
 */
public class XStreamTest {
    public static void main(String[] args) {
        List<CPU> cpuList = new ArrayList<>();
        CPU cpu1 = new CPU();
        cpu1.setGeneration("8-i5-K");
        cpu1.setName("第一个");
        cpuList.add(cpu1);

        CPU cpu2 = new CPU();
        cpu2.setGeneration("8-i5-U");
        cpu2.setName("第二个");
        cpuList.add(cpu2);


        Computer computer = new Computer();
        computer.setBrand("huawei");
        computer.setName("My computer");
        computer.setProcessors(cpuList);

        XStream xstream = new XStream(new DomDriver());
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(Computer.class);
        xstream.alias("computer",Computer.class);
        xstream.alias("cpu",CPU.class);

        //javabean to xml
        String xml = xstream.toXML(computer);
        System.out.println(xml);

        //xml to javabean
        Computer computerFromXml = (Computer) xstream.fromXML(xml);

        System.out.println(computer.getName());
    }
}
