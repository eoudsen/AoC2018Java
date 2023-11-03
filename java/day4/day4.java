package day4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class day4 {

    private static final Pattern GUARD_PATTERN =
            Pattern.compile("\\[(?<year>\\d{1,4})-(?<month>\\d{1,2})-(?<day>\\d{1,2}) (?<hour>\\d{1,2}):(?<minute>\\d{1,2})] (?<command>.*)$");


    public static List<String> getFileContent(final String fileName) {
        try(var bufferedReader = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        System.exit(1);
        return null;
    }

    private static Integer part1(final List<String> input) {
        List<Entry> entries = new ArrayList<>();
        for (final var entry : input) {
            Matcher matcher = GUARD_PATTERN.matcher(entry);
            if (matcher.matches()) {
                int month = Integer.parseInt(matcher.group("month"));
                int day = Integer.parseInt(matcher.group("day"));
                int hour = Integer.parseInt(matcher.group("hour"));
                int minute = Integer.parseInt(matcher.group("minute"));
                String command = matcher.group("command");
                entries.add(new Entry(month, day, hour, minute, command));
            }
            else {
                System.out.println("Everything is broken");
            }
        }
        Collections.sort(entries);
        Map<Integer, Guard> guardMap = new HashMap<>();
        Guard lastGuard = null;
        for (final var entry : entries) {
            final String command = entry.getCommand();
            if (command.equals("wakes up")) {
                lastGuard.wakeUp(entry.getDay(), entry.getMinute());
            }
            else if (command.equals("falls asleep")) {
                lastGuard.goToSleep(entry.getDay(), entry.getMinute());
            }
            else if (command.endsWith("begins shift")) {
                int guard = Integer.parseInt(command.split(" ")[1].split("#")[1]);
                if (!guardMap.containsKey(guard)) {
                    Guard newGuard = new Guard(guard);
                    lastGuard = newGuard;
                    guardMap.put(guard, newGuard);
                }
                else {
                    lastGuard = guardMap.get(guard);
                }
            }
        }
        Map.Entry<Integer, Guard> mostSleepyGuard = null;
        for (final var guard : guardMap.entrySet()) {
            if (mostSleepyGuard == null) {
                mostSleepyGuard = guard;
                continue;
            }
            if (guard.getValue().getSleepyMinutes() > mostSleepyGuard.getValue().getSleepyMinutes()) {
                mostSleepyGuard = guard;
            }
        }

        return mostSleepyGuard.getValue().sleepiestMinute() * mostSleepyGuard.getKey();
    }

    private static Integer part2(final List<String> input) {
        List<Entry> entries = new ArrayList<>();
        for (final var entry : input) {
            Matcher matcher = GUARD_PATTERN.matcher(entry);
            if (matcher.matches()) {
                int month = Integer.parseInt(matcher.group("month"));
                int day = Integer.parseInt(matcher.group("day"));
                int hour = Integer.parseInt(matcher.group("hour"));
                int minute = Integer.parseInt(matcher.group("minute"));
                String command = matcher.group("command");
                entries.add(new Entry(month, day, hour, minute, command));
            }
            else {
                System.out.println("Everything is broken");
            }
        }
        Collections.sort(entries);
        Map<Integer, Guard> guardMap = new HashMap<>();
        Guard lastGuard = null;
        for (final var entry : entries) {
            final String command = entry.getCommand();
            if (command.equals("wakes up")) {
                lastGuard.wakeUp(entry.getDay(), entry.getMinute());
            }
            else if (command.equals("falls asleep")) {
                lastGuard.goToSleep(entry.getDay(), entry.getMinute());
            }
            else if (command.endsWith("begins shift")) {
                int guard = Integer.parseInt(command.split(" ")[1].split("#")[1]);
                if (!guardMap.containsKey(guard)) {
                    Guard newGuard = new Guard(guard);
                    lastGuard = newGuard;
                    guardMap.put(guard, newGuard);
                }
                else {
                    lastGuard = guardMap.get(guard);
                }
            }
        }
        Map.Entry<Integer, Guard> verySleepyGuard = null;
        for (final var guard : guardMap.entrySet()) {
            if (verySleepyGuard == null) {
                verySleepyGuard = guard;
                continue;
            }
            if (guard.getValue().sleepiestMinuteValue() > verySleepyGuard.getValue().sleepiestMinuteValue()) {
                verySleepyGuard = guard;
            }
        }

        return verySleepyGuard.getValue().sleepiestMinute() * verySleepyGuard.getKey();
    }

    public static void main(String... args) {
        var fileContent = getFileContent("java/day4/input.txt");
//        var intInput = fileContent.stream().map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
