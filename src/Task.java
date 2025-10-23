import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Duration;

class Task implements Serializable {
    private String name;
    private LocalDateTime startTime;
    private Duration totalTime;
    private boolean isRunning;

    public Task(String name) {
        this.name = name;
        this.totalTime = Duration.ZERO;
        this.isRunning = false;
    }

    public String getName() {
        return name;
    }

    public void start() {
        startTime = LocalDateTime.now();
        isRunning = true;
    }

    public void stop() {
        if (startTime != null) {
            LocalDateTime stopTime = LocalDateTime.now();
            totalTime = totalTime.plus(Duration.between(startTime, stopTime));
            startTime = null;
            isRunning = false;
        }
    }

    public Duration getTotalTime() {
        if (isRunning) {
            LocalDateTime now = LocalDateTime.now();
            return totalTime.plus(Duration.between(startTime, now));
        } else {
            return totalTime;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}