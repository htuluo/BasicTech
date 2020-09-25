package basic.tech.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @description:
 * @author: luolm
 * @createTime： 2020/6/27
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class KryoDemo {
    public static void main(String[] args) throws FileNotFoundException {
        Kryo kryo = new Kryo();
        // ...
        Output output = new Output(new FileOutputStream("file.bin"));
        SerializeClass object = new SerializeClass("1, 2","aaa");
        kryo.writeObject(output, object);
        output.close();
// ...
        Input input = new Input(new FileInputStream("file.bin"));
        SerializeClass someObject = kryo.readObject(input, SerializeClass.class);
        input.close();
    }
}
