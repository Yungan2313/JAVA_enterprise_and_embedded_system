package hw2.huge_integer;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input integers as large as 40 digits a：");
        String inta = scanner.nextLine();
        System.out.print("Input integers as large as 40 digits b：");
        String intb = scanner.nextLine();
        HugeInteger a = new HugeInteger(inta);
        HugeInteger b = new HugeInteger(intb);
        System.out.println("a + b: (" + a + ")+(" + b + ")=" + a.add(b));
        System.out.println("a - b: (" + a + ")-(" + b + ")=" + a.subtract(b));
        System.out.println("b - a: (" + b + ")-(" + a + ")=" + b.subtract(a));
        System.out.println("a * b: (" + a + ")*(" + b + ")=" + a.multiply(b));        
        // System.out.println("a / b: (" + a + ")/(" + b + ")=" + a.divide(b, 0));
        // System.out.println("a % b: (" + a + ")*(" + b + ")=" + a.divide(b, 1));
        System.out.println("a == b ? " + a.isEqualTo(b));
        System.out.println("a != b ? " + a.isNotEqualTo(b));
        System.out.println("a > b  ? " + a.isGreaterThan(b));
        System.out.println("a < b  ? " + a.isLessThan(b));
        System.out.println("a >= b ? " + a.isGreaterThanOrEqualTo(b));
        System.out.println("a <= b ? " + a.isLessThanOrEqualTo(b));
    }
}
