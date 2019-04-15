package com.java.oracle.study.java_study.lamdbas.expression;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LamdbsExpression implements Processor {

    public static void main(String[] args) throws Exception {
//        Processor processor = new LamdbsExpression();
//        String result = processor.process(() -> "abc");
//        String result = processor.processSup(() -> "abc");
//        String result = processor.process((Supplier<String>)() -> "ccc");
//        System.out.println(result);

//        int[] ints = new int[]{1};
//        Runnable runnable = () -> ints[0]++;
//        runnable.run();

//        Map<String, Integer> nameMaps = new HashMap<>();
//        Integer value = nameMaps.computeIfAbsent("john", s -> {
//            System.out.println(s);
//            return "tests".length();
//        });
//        System.out.println(value);

//        Function<String, String> function = Function.identity();
//        System.out.println(function.apply("5"));
//
//        Function<Integer, String> intToStr = Object::toString;
//        Function<String, String> quote = s -> "'" + s + "'";
//        Function<Integer, String> quoteIntToString = quote.compose(intToStr);
//
//        System.out.println(quoteIntToString.apply(5));

//        IntFunction intFunction = s -> s;
//        System.out.println(intFunction.apply(3).toString());

//        ToIntFunction toIntFunction = s -> s != null ? Integer.parseInt(s.toString()) : 0;
//        System.out.println(toIntFunction.applyAsInt(null));

//        LongToIntFunction longToIntFunction = s -> Integer.parseInt(Long.toString(s));
//        int a = longToIntFunction.applyAsInt(34L);
//        System.out.println(a);

//        LamdbsExpression lamdbsExpression = new LamdbsExpression();
//        short[] array = {(short) 1, (short) 2, (short) 3};
//        byte[] transformedArray = lamdbsExpression.short2byte(array, s -> (byte) (s * 2));
//        for (byte b : transformedArray){
//            System.out.println(b);
//        }

//        List<String> names = Arrays.asList("zhangsan", "lisi", "wangwu");
//        names.stream().flatMap(s -> {
//            Stream<String> s2 = Stream.of("ss", "zz");
//            return s2;
//        }).forEach(System.out::println);
//        names.forEach(name -> System.out.println("Hello, " + name));
//
//        Map<String, Integer> ages = new HashMap<>();
//        ages.put("zhangsan", 20);
//        ages.put("lisi", 32);
//        ages.put("wangwu", 40);
//
//        ages.forEach((name, age) -> System.out.println(name + "====" + age));

        /**获取单词，并且去重**/
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world",
                "hello world welcome");

        //map和flatmap的区别
        list.stream().map(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("---------- ");
        list.stream().flatMap(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("---------- ");
        list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList())
                .forEach(System.out::println);

    }


    public static double squareLazy(Supplier<Double> lazyValue) {
        return Math.pow(lazyValue.get(), 2);
    }

    public byte[] short2byte(short[] array, ShortToByteFunction function) {
        byte[] bytes = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            bytes[i] = function.applyAsByte(array[i]);
        }

        return bytes;
    }

    @Override
    public String process(Callable<String> c) throws Exception {
        return c.call();
    }

    @Override
    public String process(Supplier<String> supplier) {
        return supplier.get();
    }

    @Override
    public String processSup(Supplier<String> s) {
        return s.get();
    }
}

@FunctionalInterface
interface ShortToByteFunction {

    byte applyAsByte(short s);
}
