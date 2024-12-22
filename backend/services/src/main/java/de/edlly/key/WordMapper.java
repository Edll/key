package de.edlly.key;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordMapper {

    public Map<String, Integer> getWordMap(String content) {
        Map<String, Integer> wordCounts = Stream.of(content)
                .flatMap(str -> Arrays.stream(str.split("\\p{Punct}| |[0-9]|…|«|»|“|„")))
                .filter(isNoEmptyString())
                .filter(isOnlyWord())
                .collect(Collectors.toMap(
                        String::toLowerCase,
                        i -> 1,
                        Integer::sum)
                );
        return wordCounts;
    }

    private static Predicate<String> isOnlyWord() {
        return str -> str.matches("[a-zA-Z]+");
    }

    private static Predicate<String> isNoEmptyString() {
        return str -> !str.isBlank();
    }
}
