package day4;

import java.util.HashMap;
import java.util.Map;

public class Guard {

    private int guardNumber;
    private int sleepDay;
    private boolean asleep = false;

    private int sleepMinute = 0;

    private int totalMinutesSleep = 0;
    private Map<Integer, Integer> sleepMinutes = new HashMap<>();

    public Guard(final int guardNumber) {
        this.guardNumber = guardNumber;
    }

    public int getGuardNumber() {
        return this.guardNumber;
    }

    public void goToSleep(final int sleepDay, final int sleepMinute) {
        this.asleep = true;
        this.sleepDay = sleepDay;
        this.sleepMinute = sleepMinute;
    }

    public void wakeUp(final int wakeDay, final int wakeMinute) {
        this.asleep = false;
        if (wakeDay > sleepDay) {
            return;
        }
        for (int i = sleepMinute; i < wakeMinute; i++) {
            this.totalMinutesSleep++;
            this.addMinute(i);
        }
    }

    public int getSleepyMinutes() {
        return this.totalMinutesSleep;
    }

    private void addMinute(final int minute) {
        if (this.sleepMinutes.containsKey(minute)) {
            this.sleepMinutes.put(minute, this.sleepMinutes.get(minute) + 1);
        }
        else {
            this.sleepMinutes.put(minute, 1);
        }
    }

    public int sleepiestMinute() {
        Map.Entry<Integer, Integer> sleepyMinute = null;
        for (final var minute : sleepMinutes.entrySet()) {
            if (sleepyMinute == null) {
                sleepyMinute = minute;
                continue;
            }
            if (minute.getValue() > sleepyMinute.getValue()) {
                sleepyMinute = minute;
            }
        }
        if (sleepyMinute == null) {
            return 0;
        }
        return sleepyMinute.getKey();
    }

    public int sleepiestMinuteValue() {
        if (this.sleepMinutes.containsKey(sleepiestMinute())) {
            return this.sleepMinutes.get(sleepiestMinute());
        }
        return 0;
    }

}
