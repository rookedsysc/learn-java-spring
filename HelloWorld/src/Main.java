public class Main {

    public  static  void main(String[] args) {
        int result;
        result = new Add().calc(12, 2);
        System.out.println(result);
        result = new Sub().calc(12, 2);
        System.out.println(result);
        result = new Multiple().calc(12, 2);
        System.out.println(result);
        result = new Divide().calc(12, 2);
        System.out.println(result);
    }
}