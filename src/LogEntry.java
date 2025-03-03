import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {
    private final String ipAddr;
    private final LocalDateTime time;
    private final HttpMethod method;
    private final String path;
    private final int responseCode;
    private final int responseSize;
    private final String referer;
    private final UserAgent agent;

    public LogEntry(String logLine) {
        String[] parts = logLine.split(" ", 12);

        this.ipAddr = parts[0];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        this.time = LocalDateTime.parse(parts[3].substring(1) + " " + parts[4].substring(0, parts[4].length() - 1), formatter);

        this.method = HttpMethod.valueOf(parts[5].replace("\"", ""));

        this.path = parts[6];

        this.responseCode = Integer.parseInt(parts[8]);

        this.responseSize = Integer.parseInt(parts[9]);

        this.referer = parts[10].replace("\"", "");

        this.agent = new UserAgent(parts[11].replace("\"", ""));
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getAgent() {
        return agent;
    }
}

enum HttpMethod {
    GET, POST, PUT, DELETE, HEAD, OPTIONS, PATCH
}