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
                System.out.println("Указанный путь является путём к папке, а не к файлу");
                continue;
            }

            System.out.println("Путь указан верно");
            System.out.println("Это файл номер " + N);
            N += 1;

            int totalLines = 0;
            int googlebotCount = 0;
            int yandexBotCount = 0;
            Statistics statistics = new Statistics();

            try (FileReader fileReader = new FileReader(path);
                 BufferedReader reader = new BufferedReader(fileReader)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    totalLines++;
                    LogEntry entry = new LogEntry(line);
                    statistics.addEntry(entry);

                    if (line.length() > 1024) {
                        throw new TooLongStringException("Строка длиннее 1024 символов: " + line);
                    }

                    String[] parts = line.split(" ", 12);
                    if (parts.length < 12) {
                        System.out.println("Некорректный формат строки: " + line);
                        continue;
                    }

                    String ip = parts[0];
                    String dash1 = parts[1];
                    String dash2 = parts[2];
                    String dateTime = parts[3] + " " + parts[4];
                    String method = parts[5].replace("\"", "");
                    String pathRequest = parts[6];
                    String httpVersion = parts[7];
                    String statusCode = parts[8];
                    String responseSize = parts[9];
                    String referer = parts[10].replace("\"", "");
                    String userAgent = parts[11].replace("\"", "");

                    String botName = extractBotName(userAgent);
                    if (botName != null) {
                        if (botName.equals("Googlebot")) {
                            googlebotCount++;
                        } else if (botName.equals("YandexBot")) {
                            yandexBotCount++;
                        }
                    }
                }

                System.out.println("Общее количество запросов: " + totalLines);
                System.out.println("Количество запросов от Googlebot: " + googlebotCount);
                System.out.println("Количество запросов от YandexBot: " + yandexBotCount);

                if (totalLines > 0) {
                    double googlebotPercentage = (double) googlebotCount / totalLines * 100;
                    double yandexBotPercentage = (double) yandexBotCount / totalLines * 100;
                    System.out.printf("Доля запросов от Googlebot: %.2f%%\n", googlebotPercentage);
                    System.out.printf("Доля запросов от YandexBot: %.2f%%\n", yandexBotPercentage);
                }

            } catch (TooLongStringException e) {
                System.out.println(e.getMessage());
                break;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Средний объём трафика за час: " + statistics.getTrafficRate() + " байт/час");
        }
    }

    private static String extractBotName(String userAgent) {
        String[] parts = userAgent.split(";");
        for (String part : parts) {
            part = part.trim();
            if (part.startsWith("Googlebot") || part.startsWith("YandexBot")) {
                int slashIndex = part.indexOf('/');
                if (slashIndex != -1) {
                    return part.substring(0, slashIndex).trim();
                }
                return part.trim();
            }
        }
        return null;
    }

}

class TooLongStringException extends RuntimeException {
    public TooLongStringException(String message) {
        super(message);
    }
}