package day1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class day1 {

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
        var start = 0;
        for (var entry : input) {
             start += Integer.parseInt(entry);
        }
        return start;
    }

    public static Integer part2(final List<String> input) {
        var currentFrequency = 0;
        final Set<Integer> frequencies = new HashSet<>();
        while (true) {
            for (var entry : input) {
                currentFrequency += Integer.parseInt(entry);
                var currentSize = frequencies.size();
                frequencies.add(currentFrequency);
                if (currentSize == frequencies.size()) {
                    return currentFrequency;
                }
            }
        }
    }

    public static void main(String... args) {
        var fileContent = getFileContent("java/day1/input.txt");
//        var intInput = fileContent.stream().map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}