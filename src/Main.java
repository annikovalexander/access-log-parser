import java.io.File;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int N=1;
        while (true) {
             String path = new Scanner(System.in).nextLine();
             File file = new File(path);
             boolean fileExists = file.exists();
             boolean isDirectory = file.isDirectory();
             if (fileExists==false && isDirectory==false) System.out.println("Указанный файл не существует");
             else if (isDirectory==true) System.out.println("Указанный путь является путём к папке, а не к файлу");
             else if (fileExists==true) {
                 System.out.println("Путь указан верно");
                 System.out.println("Это файл номер " + N);
                 N+=1;
             }
         }
        }
    }