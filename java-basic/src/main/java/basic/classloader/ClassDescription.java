package basic.classloader;

import sun.applet.Main;

import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/6/3
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class ClassDescription {
    public static void main(String[] args) {
        MyClass<String> stringMyClass = new MyClass<>();
        Class<? extends MyClass> clazz = stringMyClass.getClass();
        System.out.println(clazz.getFields());
        System.out.println(clazz.getDeclaredFields());
//        String classDescription = getClassDescription(MyClass.class);
//        System.out.println(classDescription);
    }



    /**
     *  获取类描述信息
     * @param c
     * @return
     */
    public static String getClassDescription(Class c) {
        StringBuilder stringBuilder = new StringBuilder();
        int modifierBits = 0;
        String keyWord = "";
        if (c.isInterface()) {
            modifierBits = c.getModifiers() & Modifier.interfaceModifiers();
            if (c.isAnnotation()) {
                keyWord = "@interface";
            } else {
                keyWord = "interface";
            }
        } else if (c.isEnum()) {
            modifierBits = c.getModifiers() & Modifier.classModifiers();
            keyWord = "enum";
        }
        modifierBits = c.getModifiers() & Modifier.classModifiers();
        keyWord = "class";
        String modifiers = Modifier.toString(modifierBits);
        stringBuilder.append(modifiers);
        stringBuilder.append(" " + keyWord);
        stringBuilder.append(" " + c.getSimpleName());
        stringBuilder.append(" " + getGenericTypeParams(c));
        Class superclass = c.getSuperclass();
        if (superclass != null) {
            stringBuilder.append(" extends " + superclass.getSimpleName());
        }
        String classInterfaces = ClassDescription.getClassInterfaces(c);
        if (classInterfaces != null) {
            stringBuilder.append(" implements "+classInterfaces);
        }
        return stringBuilder.toString();
    }

    public static String getClassInterfaces(Class c) {
        Class[] interfaces = c.getInterfaces();
        String interfaceList = null;
        if (interfaces.length > 0) {
            String[] interfaceNames = new String[interfaces.length];
            for (int i = 0; i < interfaces.length; i++) {
                interfaceNames[i] = interfaces[i].getSimpleName();
            }
            interfaceList = String.join(",", interfaceNames);
        }
        return interfaceList;
    }

    private static String getGenericTypeParams(Class c) {
        StringBuilder sb = new StringBuilder();
        TypeVariable<?>[] typeParameters = c.getTypeParameters();
        if (typeParameters.length>0){
            String[] paramNames = new String[typeParameters.length];
            for (int i=0;i<typeParameters.length;i++){
                paramNames[i]=typeParameters[i].getTypeName();
            }
            sb.append("<");
            String paramList = String.join(",", paramNames);

            sb.append(paramList);
            sb.append(">");

        }

        return sb.toString();
    }
}
