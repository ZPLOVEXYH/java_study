package com.java.oracle.study.java_study.lamdbas;

import com.java.oracle.study.java_study.lamdbas.bean.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * java 8新特性
 * 使用java1.8的编码方式来实现排序
 */
public class LamdbasTest {

    private static List<User> userList = new ArrayList<>();
    private static Map<Long, User> userMap = new HashMap<>();
    private static Map<Long, String> userMap2 = new HashMap<>();

    static {
        User user01 = new User(3, "zhangsan", 12);
        User user02 = new User(9, "li", 11);
        User user03 = new User(1, "wangwu", 20);
        User user04 = new User(2, "zhaoliu", 20);

        userList.add(user01);
        userList.add(user02);
        userList.add(user03);
        userList.add(user04);

        userMap.put(3L, user01);
        userMap.put(1L, user02);
        userMap.put(9L, user03);
        userMap.put(10L, user04);

        userMap2.put(212L, "test");
    }

    public static void main(String[] args) {
        System.out.println("-----------------------------排序前---------------------------------");
        userList.stream().forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------");

        /**
         * 排序方法一
         */
        System.out.println("-----------------------------排序方法一 排序后---------------------------------");
        // 根据用户年龄正序排
        userList.sort(Comparator.comparing(User::getAge));
        // 根据用户id正序排
        userList.sort(Comparator.comparing(User::getId));
        // 优先根据年龄正序排，然后再根据id倒序排
        userList.sort(Comparator.comparing(User::getAge).thenComparing(User::getId).reversed());
        userList.stream().forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------");

        /**
         * 排序方法二
         */
        System.out.println("-----------------------------排序方法二 排序后---------------------------------");
        userList.stream().sorted(Comparator.comparing(User::getAge).thenComparing(User::getId).reversed()).forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------");


        /**
         * 对map集合对象进行排序
         */
        Map<Long, User> result = userMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> newValue, LinkedHashMap::new));
        result.entrySet().forEach(longUserEntry -> System.out.println(longUserEntry.getKey() + "----" + longUserEntry.getValue()));
        System.out.println("---------------------");
        Map<Long, User> result2 = userMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        result2.entrySet().forEach(longUserEntry -> System.out.println(longUserEntry.getKey() + "----" + longUserEntry.getValue()));


        Map<Long, User> maps = new LinkedHashMap<>();
        userMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(longUserEntry -> maps.put(longUserEntry.getKey(), longUserEntry.getValue()));

        /**
         * 使用collections工具类来进行排序操作
         */
        Collections.sort(userList, (x, y) -> x.getUsername().compareTo(y.getUsername()));

        /**
         * 排列方式
         */
        System.out.println("排序方式4");
        userList.stream().sorted((x, y) -> x.getAge() - y.getAge()).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println(userList.stream().findFirst().toString());
    }
}
