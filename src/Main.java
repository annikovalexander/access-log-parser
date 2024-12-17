import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число:");
        int n1 = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
        int n2 = new Scanner(System.in).nextInt();
        int sum=n1+n2;
        System.out.println("Сумма равна: " + sum);
        int diff=n1-n2;
        System.out.println("Разность равна: " + diff);
        int mul=n1*n2;
        System.out.println("Произведение равно: " + mul);
        double quo=(double)n1/n2;
        System.out.println("Частное равно: " + quo);
        }
    }