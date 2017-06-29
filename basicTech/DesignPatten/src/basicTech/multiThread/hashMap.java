package basicTech.multiThread;

public  class hashMap{

    class  Node{
    private  String key;
    private  Object value;
    private  Node next;


    }
    private  int capacity=10;
    private  Node[] nodes=new  Node[capacity];


 /*   public  put(String key,Object value){
        Node node=new Node();
        node.key=key;
        node.value=value;
        nodes.

    }*/



    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis()/1000);
    }
}