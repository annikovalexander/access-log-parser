import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N = 1;
        while (true) {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (!fileExists && !isDirectory) {
                System.out.println("Указанный файл не существует");
                continue;
            } else if (isDirectory) {
                System.out.println("Указанный путь является путём к папке, а не к файлу!");
                continue;
            }

            System.out.println("Путь указан верно");
            System.out.println("Это файл номер " + N);
            N += 1;

            int totalLines = 0;
            int maxLength = 0;
            int minLength = Integer.MAX_VALUE;

            try (FileReader fileReader = new FileReader(path);
                 BufferedReader reader = new BufferedReader(fileReader)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    totalLines++;
                    int length = line.length();

                    if (length > 1024) {
                        throw new TooLongStringException("Строка длиннее 1024 символов: " + line);
                    }

                    if (length > maxLength) {
                        maxLength = length;
                    }

                    if (length < minLength) {
                        minLength = length;
                    }
                }

                System.out.println("Общее количество строк: " + totalLines);
                System.out.println("Длина самой длинной строки: " + maxLength);
                System.out.println("Длина самой короткой строки: " + (minLength == Integer.MAX_VALUE ? 0 : minLength));

            } catch (TooLongStringException e) {
                System.out.println(e.getMessage());
                break;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

class TooLongStringException extends RuntimeException {
    public TooLongStringException(String message) {
        super(message);
    }
}