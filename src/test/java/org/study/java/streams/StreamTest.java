package org.study.java.streams;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

public class StreamTest {

    @Test
    public void streamTest() {
        // 배열 스트림
        String[] strArr = new String[]{"one", "two", "three", "four", "five"};
        Stream<String> stream = Arrays.stream(strArr);
        Stream<String> partStream = Arrays.stream(strArr, 1, 2);
        // 컬렉션 스트림
        List<String> list = Arrays.asList("one", "two", "three", "four", "five");
        Stream<String> listStream = list.stream();
        // 병렬 스트림
        Stream<String> parallelStream = list.parallelStream();
        boolean isParallel = parallelStream.isParallel();

        IntStream intParallelStream = IntStream.range(1, 150).parallel();
        boolean isParallel2 = intParallelStream.isParallel();

        // 시퀀셜 스트림으로 원복
        intParallelStream = intParallelStream.sequential();

        // Empty Stream
        Stream<String> emptyStream = Stream.empty();
        // Stream Builder
        Stream<String> builderStream = Stream.<String>builder().add("one").add("two").add("three").build();
        // Stream.generate()
        Stream<String> generateStream = Stream.generate(() -> "gen").limit(5);
        // Stream.iterate()
        Stream<Integer> iterateStream = Stream.iterate(10, n -> n + 2).limit(5);
        // IntStream
        IntStream intStream = IntStream.range(1, 5);
        LongStream longStream = LongStream.rangeClosed(1, 5);
        // boxing : IntStream -> Stream
        Stream<Integer> boxedIntStream = IntStream.range(1, 5).boxed();
        // char stream
        IntStream charsStream = "Stream".chars();
        // RegEx
        Stream<String> stringStream = Pattern.compile(", ").splitAsStream("one, two, three");
        // File Stream
        try {
            Stream<String> lineStream = Files.lines(Paths.get("file.txt"), Charset.forName("UTF-8"));
        } catch (Exception e) {
        }

        // Stream 연결하기
        Stream<String> streamOne = Stream.of("one", "two", "three");
        Stream<String> streamTwon = Stream.of("four", "five", "six");
        Stream<String> concatStream = Stream.concat(streamOne, streamTwon);

        // filtering
        Stream<String> fiterStream = concatStream.filter(num -> num.contains("three"));
        // map
        //Stream<String> uppperStream = concatStream.map(String::toUpperCase);
        // flatMap
        List<List<String>> overlapList = Arrays.asList(Arrays.asList("one"), Arrays.asList("two"));
        List<String> flatList = overlapList.stream().flatMap(Collection::stream).collect(Collectors.toList());
        // Sorting
        List<Integer> sortedList = IntStream.of(14, 11, 20, 39, 23)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        List<String> lang = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");
        lang.stream().sorted().collect(Collectors.toList());
        lang.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        lang.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        lang.stream().sorted((s1, s2) -> s2.length() - s1.length()).collect(Collectors.toList());
        // peek
        int sum = IntStream.of(1, 3, 5, 7, 9).peek(System.out::println).sum();
        // Calculating
        IntStream.of(1, 3, 5, 7, 9).count();
        IntStream.of(1, 3, 5, 7, 9).sum();
        OptionalInt min = IntStream.of(1, 3, 5, 7, 9).min();
        OptionalInt max = IntStream.of(1, 3, 5, 7, 9).max();
        DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5).average().ifPresent(System.out::println);

        // reduce
        OptionalInt reduced = IntStream.range(1, 4).reduce(
                (a, b) -> {
                    return Integer.sum(a, b);
                });
        int reduceTwoParam = IntStream.range(1, 4).reduce(10, Integer::sum);

        Integer reducedParallel = Arrays.asList(1, 2, 3).parallelStream()
                .reduce(10, Integer::sum, (a, b) -> {
                    System.out.println("combiner was called");
                    return a + b;
                });

        // Collecting
        List<Product> productList =
                Arrays.asList(
                        new Product(23, "potatoes"),
                        new Product(14, "orange"),
                        new Product(13, "lemon"),
                        new Product(23, "bread"),
                        new Product(13, "suger"));
        List<String> collectorCollection =
                productList.stream()
                        .map(Product::getName)
                        .collect(Collectors.toList());

        String listToString =
                productList.stream()
                        .map(Product::getName)
                        .collect(Collectors.joining());

        String listToString2 =
                productList.stream()
                        .map(Product::getName)
                        .collect(Collectors.joining(", ", "<", ">"));

        Double averageAmount =
                productList.stream()
                        .collect(Collectors.averagingInt(Product::getAmount));

        Integer sumAmount =
                productList.stream()
                        .collect(Collectors.summingInt(Product::getAmount));

        Integer sumAmount2 = productList.stream().mapToInt(Product::getAmount).sum();

        IntSummaryStatistics statistics =
                productList.stream().collect(Collectors.summarizingInt(Product::getAmount));

        Map<Integer, List<Product>> collectorMapOfLists =
                productList.stream().collect(Collectors.groupingBy(Product::getAmount));

        Map<Boolean, List<Product>> mapPartitioned =
                productList.stream().collect(Collectors.partitioningBy(el -> el.getAmount() > 15));

        Set<Product> unmodifiableSet =
                productList.stream().collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));

        // Collector.of
        Collector<Product, ?, LinkedList<Product>> toLinkedList =
                Collector.of(LinkedList::new, LinkedList::add,
                        (first, second) -> {
                            first.addAll(second);
                            return first;
                        }
                );
        LinkedList<Product> linkedListOfPersons =
                productList.stream().collect(toLinkedList);

        // matching
        List<String> names = Arrays.asList("Eric", "Elena", "Java");

        boolean anyMatch = names.stream().anyMatch(name -> name.contains("J"));
        boolean allMatch = names.stream().allMatch(name -> name.length() > 3);
        boolean noneMatch = names.stream().noneMatch(name -> name.endsWith("s"));

        // iterating
        names.stream().forEach(System.out::println);
    }

    public static class Product {
        private int amount;
        private String name;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Product(int amount, String name) {
            this.amount = amount;
            this.name = name;
        }
    }
}

