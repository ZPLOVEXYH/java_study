package com.java.oracle.study.java_study.lamdbas.stream;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
 * +--------------------+       +------+   +------+   +---+   +-------+
 * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
 * +--------------------+       +------+   +------+   +---+   +-------+
 * 元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
 */
public class StreamTest {

//    private static List<String> strLists = new ArrayList<>();
//    private static List<Integer> intLists = new ArrayList<>();
//    private static List<User> userLists = new ArrayList<>();
//    private static Supplier<Stream<User>> userStream;
////    private static Stream<User> userStream;
//
//    static {
//        strLists = Arrays.asList("", "2", "3", "4", "1");
//        intLists = Arrays.asList(1, 5, 3, 9, 2);
//
//        User user = new User(1, "zhangsan", 12);
//        User user2 = new User(2, "lisi", 13);
//        User user3 = new User(3, "zhangsan", 13);
//        userStream = () -> Stream.of(user, user2, user3);
////        userStream = Stream.of(user, user2, user3);
//
//        userLists = Arrays.asList(user, user2, user3);
//    }

    public static void main(String[] args) throws URISyntaxException, IOException {
//        String str = strLists.stream().filter(x -> !x.isEmpty()).collect(Collectors.joining(","));
//        System.out.println("输出的字符串为：" + str);
//
//        long a = intLists.stream().collect(Collectors.counting());
//        System.out.println(a);
//
//        userStream.get().collect(Collectors.groupingBy(x -> x.getId())).entrySet().forEach(x -> System.out.println(x.getKey() + "----" + x.getValue()));
//
//        Map<Integer, User> maps = userStream.get().collect(Collectors.toMap(User::getId, user -> user));
//        maps.entrySet().stream().forEach(x -> System.out.println(x.getKey() + "----" + x.getValue()));

//        userStream.collect(Collectors.groupingBy(x -> x.getId())).entrySet().forEach(x -> System.out.println(x.getKey() + "----" + x.getValue()));

//        Stream<String> stringStream = Stream.<String>builder().add("zhangsan").add("lisi").add("wangwu").build();
//        stringStream.forEach(System.out::println);
//
//
//        Map<Integer, User> maps = userStream.collect(Collectors.toMap(User::getId, user -> user));
//        maps.entrySet().stream().forEach(x -> System.out.println(x.getKey() + "----" + x.getValue()));

//         需要保证User的id是唯一的，这样才可以成功从List转成Map<Integer, T>，否则会报错非法语句异常，出现重复的key值：java.lang.IllegalStateException: Duplicate key User(id=1, username=zhangsan, age=12)
//        Map<Integer, User> userMaps = userLists.stream().collect(Collectors.toMap(User::getId, user -> user));
//        userMaps.entrySet().forEach(x -> System.out.println(x.getValue()));

//        // stream流的数据来源1
//        Stream<String> streamStr = Stream.of("a", "c", "b", "e");
//        streamStr.sorted().forEach(System.out::println);
//
//        // stream流的数据来源2
//        Stream<String> streamStr2 = Stream.<String>builder().add("zhangsan").add("lisi").add("wangwu").build();
//        streamStr2.sorted().forEach(System.out::println);
//
//        // stream流的数据来源3
//        Collection<String> collection = Arrays.asList("test", "admin", "zp");
//        collection.stream().forEach(System.out::println);
//
        // stream流的数据来源4
        String[] strs = {"cba", "nba", "fiba", "faba"};
        Supplier<Stream<String>> stream = () -> Arrays.stream(strs);
        stream.get().forEach(System.out::println);

        // stream流的数据来源5
        Supplier<Stream<String>> streams = () -> Stream.generate(() -> "element").limit(10);
        streams.get().forEach(System.out::println);

        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);
        streamIterated.forEach(System.out::println);

        Random random = new Random();
        DoubleStream doubleStream = random.doubles(3);
        doubleStream.forEach(System.out::println);

        IntStream intStream = IntStream.range(1, 3);
        intStream.forEach(System.out::println);

        LongStream longStream = LongStream.rangeClosed(1, 3);
        longStream.forEach(System.out::println);

        IntStream is = "abc".chars();

        Stream<String> intstream = Stream.concat(streams.get(), stream.get());
        intstream.forEach(System.out::println);

        Stream<String> stringStream = SPLIT_STR.splitAsStream("1,2,3,4");
        stringStream.forEach(System.out::println);


        Path path = Paths.get("C:\\Users\\teststream.txt");
        Stream<String> ss = Files.lines(path, Charset.forName("utf-8"));
        ss.forEach(System.out::println);

        Stream<String> streamFind = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
        Optional<String> anyElement = streamFind.findAny();
        System.out.println(anyElement.get());

        Optional<String> strLists = Arrays.asList("", "cd", "xx").stream().findFirst();


        Stream<String> onceModifiedStream = Stream.of("abcd", "bbcd", "cbcd").skip(1);
        onceModifiedStream.forEach(System.out::println);

        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        long size = list.stream().skip(1).map(element -> {
            String str = element.substring(0, 3);
            System.out.println(str);
            return str;
        }).sorted().count();
        System.out.println(size);

        System.out.println("--------------------");
        UserFoo userFoo = new UserFoo();
        Function<Integer, Integer> function = par -> { // 1
            return par + 4; // 4
        };
        int str = userFoo.add(1, function); // 2
        System.out.println(str);


        TestFoo testFoo = new TestFoo();
        System.out.println(testFoo.add("Message-", x -> x + "test"));
    }

    private static Pattern SPLIT_STR = Pattern.compile(",");


}

class UserFoo {
    public int add(Integer string, Function<Integer, Integer> fn) {
        return fn.apply(string); // 3
    }
}

class TestFoo {
    public String add(String msg, Foo foo) {
        return foo.method(msg);
    }
}

@FunctionalInterface
interface Foo {
    String method(String message);
}





























