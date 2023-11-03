package day4;

public class Entry implements Comparable<Entry> {

    private String command;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Entry(final int month, final int day, final int hour, final int minute, final String command) {
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getDay() {
        return this.day;
    }

    @Override
    public int compareTo(Entry o) {
        if (this.month < o.month) {
            return -1;
        }
        if (this.month > o.month) {
            return 1;
        }
        if (this.day < o.day) {
            return -1;
        }
        if (this.day > o.day) {
            return 1;
        }
        if (this.hour < o.hour) {
            return -1;
        }
        if (this.hour > o.hour) {
            return 1;
        }
        if (this.minute < o.minute) {
            return -1;
        }
        if (this.minute > o.minute) {
            return 1;
        }
        return 0;
    }
}
