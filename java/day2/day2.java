package day2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class day2 {

    public static List<String> getFileContent(final String fileName) {
        try(var bufferedReader = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        System.exit(1);
        return null;
    }

    public static Integer part1(final List<String> input) {
        int count2 = 0;
        int count3 = 0;
        for (var box : input) {
            var map = new HashMap<Character, Integer>();
            for (var chr : box.toCharArray()) {
                if (map.containsKey(chr)) {
                    map.put(chr, map.get(chr) + 1);
                }
                else {
                    map.put(chr, 1);
                }
            }
            var set2 = false;
            var set3 = false;
            for (var value : map.values()) {
                if (!set2 && value == 2) {
                    count2++;
                    set2 = true;
                }
                if (!set3 && value == 3) {
                    count3++;
                    set3 = true;
                }
            }
        }
        return count2 * count3;
    }

    private static String part2(final List<String> input) {
        final Set<String> storedInputs = new HashSet<>();
        for (final var box : input) {
            for (final var stored : storedInputs) {
                Integer i = compareStrings(box, stored);
                if (i == box.length() - 1) {
                    return calculateResultString(box, stored);
                }
            }
            storedInputs.add(box);
        }
        return null;
    }

    private static Integer compareStrings(final String var1, final String var2) {
        int count = 0;
        for (int i = 0; i < var1.length(); i++) {
            if (var1.charAt(i) == var2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    private static String calculateResultString(final String var1, final String var2) {
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < var1.length(); i++) {
            if (var1.charAt(i) == var2.charAt(i)) {
                chars.add(var1.charAt(i));
            }
        }
        return chars.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static void main(String... args) {
        var fileContent = getFileContent("java/day2/input.txt");
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}