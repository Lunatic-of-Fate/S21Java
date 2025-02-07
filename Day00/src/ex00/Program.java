package src.ex00;

public class Program {
    public static void main(String[] args) {
        int a = 12901212;
        int res = 0;
        while(a > 0) {
            res = res + a % 10;
            a /= 10;
        }
        System.out.println(res);
    }
}