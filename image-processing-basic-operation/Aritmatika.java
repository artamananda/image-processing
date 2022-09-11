import java.util.Scanner;

public class Aritmatika{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = {1,2,4,7,11,16,22};
        int un,a,b,n;
        a  = arr[0];
        n = scan.nextInt();
        b = n;
        un = a + (n-1) * b;

        System.out.println(un);
    }
}