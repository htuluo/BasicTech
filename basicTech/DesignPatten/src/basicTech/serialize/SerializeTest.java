package basicTech.serialize;

import java.io.*;

/**
 * @description:
 * @author: luolm
 * @createTime： 2018/10/24
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class SerializeTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file=new File("D:/aSerialize.txt");
        SerializeClass serializeClass=new SerializeClass("str0","str1");
        OutputStream os=new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(os);
        oos.writeObject(serializeClass);
        oos.close();

        InputStream is=new FileInputStream(file);
        ObjectInputStream ois=new ObjectInputStream(is);
        SerializeClass readClass = (SerializeClass)ois.readObject();
        System.out.println(readClass.getStr0());
        System.out.println(readClass.getStr1());
        System.out.println(readClass.getStr2());
        System.out.println(SerializeClass.getStr2());
        ois.close();
    }

}
