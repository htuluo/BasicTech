package basic.tech.multiThread;

public class MHashMap {

  static   class Node {
        private String key;
        private Object value;
        private Node next;
    }

    private int capacity = 10;
    private Node[] nodes = new Node[capacity];


    public void put(String key, Object value) {
        Node node = new Node();
        node.key = key;
        node.value = value;
        int hash = key.hashCode() % capacity;
        if (nodes[hash] != null) {
            Node tmpNode = nodes[hash].next;
            while (tmpNode.next != null) {
                tmpNode = tmpNode.next;
            }
            tmpNode.next = node;
        } else {
            nodes[hash] = node;
        }

    }

    public  Object take(String key){
        int hash = key.hashCode() % capacity;
        if (nodes[hash]!=null){
            Node tmpNode=nodes[hash];
          do {
                if (tmpNode.key.equals(key)){
                    return  tmpNode.value;
                }
                tmpNode=tmpNode.next;
            }  while (tmpNode.next!=null);
            return  null;
        }else {
            return null;
        }
    }


    public static void main(String[] args) {
        MHashMap mHashMap =new MHashMap();
        mHashMap.put("ddd","bbb");
        mHashMap.put("aaa","ccc");
        System.out.println(mHashMap.take("ddd"));
        System.out.println(System.currentTimeMillis() / 1000);
    }
}