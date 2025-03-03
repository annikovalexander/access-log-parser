import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Statistics {
    private int totalTraffic = 0;
    private LocalDateTime minTime = null;
    private LocalDateTime maxTime = null;

    public Statistics() {
    }

    public void addEntry(LogEntry entry) {
        this.totalTraffic += entry.getResponseSize();

        LocalDateTime entryTime = entry.getTime();
        if (this.minTime == null || entryTime.isBefore(this.minTime)) {
            this.minTime = entryTime;
        }
        if (this.maxTime == null || entryTime.isAfter(this.maxTime)) {
            this.maxTime = entryTime;
        }
    }

    public double getTrafficRate() {
        if (minTime == null || maxTime == null) {
            return 0.0;
        }
        long hours = ChronoUnit.HOURS.between(maxTime, minTime);
        if (hours == 0) {
            return totalTraffic;
        }
        return (double) totalTraffic / hours;
    }
}