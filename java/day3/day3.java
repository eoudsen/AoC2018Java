package day3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class day3 {

    private static final Pattern CLAIM_PATTERN = Pattern.compile("#(?<claim>\\d{1,4}) @ (?<xoffset>\\d{1,3}),(?<yoffset>\\d{1,3}): (?<xsize>\\d{1,3})x(?<ysize>\\d{1,3})");


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
        final Set<Location> duplicatSet = new HashSet<>();
        final Set<Location> places = new HashSet<>();
        for (final var claim : input) {
            Matcher matcher = CLAIM_PATTERN.matcher(claim);
            if (matcher.matches()) {
                int xsize = Integer.parseInt(matcher.group("xsize"));
                int ysize = Integer.parseInt(matcher.group("ysize"));
                int xoffset = Integer.parseInt(matcher.group("xoffset"));
                int yoffset = Integer.parseInt(matcher.group("yoffset"));
                final List<Location> locations = determineLocations(xoffset, yoffset, xsize, ysize);
                for (final var loc : locations) {
                    if (places.contains(loc)) {
                        duplicatSet.add(loc);
                    }
                    places.add(loc);
                }
            }
            else {
                System.out.println("Everything broken!!!!");
            }
        }
        return duplicatSet.size();
    }

    private static Integer part2(final List<String> input) {

        return null;
    }

    private static List<Location> determineLocations(final int xoffset, final int yoffset, final int xsize, final int ysize) {
        final List<Location> locations = new ArrayList<>();
        for (var i = 0; i < xsize; i++) {
            for (var j = 0; j < ysize; j++) {
                locations.add(new Location(xoffset + i, yoffset + j));
            }
        }
        return locations;
    }

    public static void main(String... args) {
        var fileContent = getFileContent("java/day3/input.txt");
//        var intInput = fileContent.stream().map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
