package com.test.closure;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * Author: zhangxin
 * Date:   15-9-21
 */
public class First {

    public static Supplier<Integer> func() {
        return ()->{
          return 111;
        };
    }

    public static Function<Integer, Integer> func_1() {
        return (Integer y)->{
            return y + 1;
        };
    }

    public static Predicate<Integer> func_predicate() {
        return (Integer x)->{
            if(x > 0)
                return true;
            else
                return false;
        };
    }

    public static BiFunction<Integer, Integer, Integer> func_BiFunction() {
        return (Integer x, Integer y)->{
            return x + y;
        };
    }

    public static Consumer<Integer> func_Consumer() {
        return (Integer x)->{
            System.out.println("Consumer " + x);
        };
    }

    public static void main(String[] args) {
        //Supplier 不接受参数，返回值
        Supplier<Integer> rtn = func();
        System.out.println("Supplier get " + rtn.get());

        //Function 接收一个参数，返回一个值
        Function<Integer, Integer> rtn_1 = func_1();
        System.out.println("Function interface return " + rtn_1.apply(2));
        System.out.println("Function interface andThen " + rtn_1.andThen(rtn_1).apply(3));
        System.out.println("Function interface compose " + rtn_1.compose(rtn_1).apply(5));

        //Predicate 接收一个参数，返回一个布尔值
        Predicate<Integer> rtn_predicate = func_predicate();
        System.out.println("Predicate " + rtn_predicate.test(1));
        System.out.println("Predicate and operator " + rtn_predicate.and(rtn_predicate).test(1));
        System.out.println("Predicate or operator " + rtn_predicate.or(rtn_predicate).test(1));
        System.out.println("Predicate negate operator " + rtn_predicate.negate().test(1));

        //BiFunction 接收两个参数，返回一个值
        BiFunction<Integer, Integer, Integer> rtn_BiFunction = func_BiFunction();
        System.out.println("BiFunction " + rtn_BiFunction.apply(5, 5));
        System.out.println("BiFunction andThen " + rtn_BiFunction.andThen(rtn_1).apply(6, 6));

        //Consumer 接收一个参数，不返回值
        Consumer<Integer> rtn_consumer = func_Consumer();
        rtn_consumer.accept(11);
        rtn_consumer.andThen(rtn_consumer).accept(12);

        BiFunction<Integer, Integer, Integer> func_3Params = (Integer x, Integer y)->x + y;
        Function<Integer, Boolean> func_2Params = (Integer x)->{if(x > 0) return true;
                                                                 else return false;};

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println("=======================================");
        list.stream().forEach((n) -> System.out.println(n));

        System.out.println("=======================================");
        list.stream().map((x)->x * x).forEach((n) -> System.out.println(n));

        System.out.println("=======================================");
        System.out.println(list.stream().reduce(0, (x, y) -> x + y).intValue());

        System.out.println("=======================================");
        List<Integer> sortedList = Arrays.asList(7, 2, 1, 5, 8, 9);
        Collections.sort(sortedList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        sortedList.forEach(System.out::println);

        System.out.println("sortedList_1 =======================================");
        List<Integer> sortedList_1 = Arrays.asList(7, 2, 1, 5, 8, 9);
        Collections.sort(sortedList_1, (o1, o2) -> o1.compareTo(o2));
        sortedList_1.forEach(System.out::println);

        System.out.println("sortedList_2 =======================================");
        List<Integer> sortedList_2 = Arrays.asList(7, 2, 1, 5, 8, 9);
        Collections.sort(sortedList_2, Comparator.comparing((Integer o1) -> o1.intValue()));
        sortedList_2.forEach(System.out::println);

        System.out.println("sortedList_3 =======================================");
        List<Integer> sortedList_3 = Arrays.asList(7, 2, 1, 5, 8, 9);
        Collections.sort(sortedList_3, Comparator.comparing(Integer::intValue));
        sortedList_3.forEach(System.out::println);

        System.out.println("sortedList_4 =======================================");
        List<Integer> sortedList_4 = Arrays.asList(7, 2, 1, 5, 8, 9);
        sortedList_4.sort(Comparator.comparing(Integer::intValue));
        sortedList_4.forEach(System.out::println);
    }
}
