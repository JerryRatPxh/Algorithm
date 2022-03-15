public class Gcd {


    //recursive version
    public static int gcd(int x,int y) {
        if(y == 0) return x;
        return gcd(y,x%y);
    }

    public static int gcd1(int x,int y) {
        while(y!=0){
            int tmp = y;
            y = x%y;
            x = tmp;
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println(gcd(3,8));
        System.out.println(gcd1(12,18));
    }
}
