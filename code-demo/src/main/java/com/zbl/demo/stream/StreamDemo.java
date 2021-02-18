package com.zbl.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: zbl
 * @Date: Created in 14:57 2020/3/5
 * @Description:
 * Stream是一组用来处理数组, 集合的API
 * 代码简洁(函数式编程) 告别for循环
 * 多核友好, java函数式编程使得编写并行程序从未如此简单, 只需调用parallel()方法
 * Stream特性
 * 1,不是数据结构, 没有内部存储
 * 2,不支持索引访问
 * 3,延迟计算
 * 4,支持并行
 * 5,很容易生成数组或集合(List, Set)
 * 6,支持过滤, 查找, 转换, 汇总, 聚合等操作
 * Stream分为 源source 中间操作 终止操作
 * 一个流的源可以是一个数组 一个集合 一个生成器一个IO通道等等
 * 一个流可以有0个或者多个中间操作, 每一个中间操作都会返回一个新的流
 * 供下一个操作使用, 一个流只会有一个终止操作
 * Stream只有遇到终止操作, 它的源才开始执行遍历操作
 * Stream的创建
 * 1, 通过数组
 * 2, 通过集合
 * 3, 通过Stream.generate方法来创建
 * 4, 通过Stream.iterate方法来创建
 * 5, 其他API创建
 * 中间操作
 * 过滤 filter
 * 去重 distinct
 * 排序 sorted
 * 截取 limit, skip
 * 转换 map/flatMap
 * 其他 peek
 * 终止操作
 * 循环forEach
 * 计算min, max, count, average
 * 匹配 angMatch, allMatch, noneMatch, findFirst, findAny
 * 汇聚 reduce
 * 收集器 toArray collect
 *
 * @Version: $
 */
public class StreamDemo {

    private static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

    private static List<String> strList = Arrays.asList("java", "php" , "go", "c");

    //通过数组来生成
    @Test
    public void test01() throws Exception{
        String[] strs = {"a", "b", "c", "d"};
        Stream<String> stream = Stream.of(strs);
        stream.forEach(System.out::println);
    }

    //通过集合来生成
    @Test
    public void test02() throws Exception{
        List<String> list = Arrays.asList("1", "2", "3");
        list.stream().forEach(System.out::println);
    }

    //generate
    @Test
    public void test03() throws Exception{
        //返回无限顺序无序流
        Stream<Integer> generate = Stream.generate(() -> 1);
        generate.limit(10).forEach(System.out::println);
    }

    //使用iterator
    @Test
    public void test04() throws Exception{
        Stream<Integer> iterate = Stream.iterate(1, a -> a + 1);
        iterate.limit(10).forEach(System.out::println);
    }

    //其他方式
    @Test
    public void test05() throws Exception{
        String str = "abcdefg";
        IntStream chars = str.chars();
        chars.forEach(System.out::println);
    }

    //中间操作
    //如果调用方法返回的结果是stream 就是一个中间操作
    @Test
    public void test06() throws Exception{
        Arrays.asList(1,2,3,4,5).stream().filter( x -> x%2 == 0).forEach(System.out::println);
        List<Integer> collect = Arrays.asList(1, 2, 3, 4, 5).stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(collect);
        //求出结果集中所有偶数的和
        System.out.println(Stream.of(1, 2, 3, 4, 5).filter(x -> x % 2 == 0).mapToInt(x -> x).sum());
        //求集合中的最大值
        System.out.println(Stream.of(1, 2, 3, 4, 5).mapToInt(x -> x).max().getAsInt());
        System.out.println(Stream.of(1, 2, 3, 4, 5).max((a, b) -> a - b).get());
        System.out.println(Stream.of(1, 2, 3, 4, 5).max((a, b) -> a - b).get());
        System.out.println(Stream.of(1, 2, 3, 4, 5).min((a, b) -> a - b).get());
        System.out.println(Stream.of(1, 2, 3, 4, 5).min(Comparator.comparingInt(a -> a)).get());

    }

    @Test
    public void test07() throws Exception{
        Optional<Integer> any = list.stream().filter(x -> x % 2 == 0).findAny();
        System.out.println(any.get());

    }

    @Test
    public void test08() throws Exception{
        Stream<Integer> integerStream = list.stream().filter( x -> {
            System.out.println("hahaha");
            System.out.println(x);
            return x % 2 == 0;
        });
        //上述找到一个 %2 == 0 就会结束
        //System.out.println(integerStream.findFirst());
        System.out.println(integerStream.collect(Collectors.toList()));
    }

    //最小值
    @Test
    public void test09() throws Exception{
        System.out.println(list.stream().sorted().findFirst().get());
    }

    @Test
    public void test10() throws Exception{
        System.out.println(1);
        try {
            int i = 1 / 0;
        } catch (Exception e) {

        }
        System.out.println(2);
    }

    //最大值
    @Test
    public void test11() throws Exception{
        System.out.println(list.stream().sorted((a, b) -> b -a).findFirst().get());
    }

    @Test
    public void test12() throws Exception{
        strList.stream().sorted().forEach(System.out::println);
    }

    @Test
    public void test13() throws Exception{
        strList.stream().sorted((a,b) -> a.length() - b.length()).forEach(System.out::println);
    }

    @Test
    public void test14() throws Exception{
        List<Integer> list = Stream.of(1, 1, 2, 2, 3, 3, 4, 4, 7, 7, 7).distinct().collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test15() throws Exception{
        Set<Integer> list = Stream.of(1, 1, 2, 2, 3, 3, 4, 4, 7, 7, 7).collect(Collectors.toSet());
        System.out.println(list);
    }

    @Test
    public void test16() throws Exception{
        //打印20-30这样的集合数据          1-50      跳过前20  再取10个
        Stream.iterate(1, x-> x+1).limit(50).skip(20).limit(10).forEach(System.out::println);
        Stream.iterate(1, x-> x+1).skip(20).limit(10).forEach(System.out::println);
    }

    @Test
    public void test17() throws Exception{
        String str = "11,22,33,44,55";
        //System.out.println(Stream.of(str.split(",")).mapToInt(x -> Integer.valueOf(x)).sum());
        System.out.println(Stream.of(str.split(",")).mapToInt(Integer::valueOf).sum());
    }

    @Test
    public void test18() throws Exception{
        String str = "11,22,33,44,55";
        //System.out.println(Stream.of(str.split(",")).mapToInt(x -> Integer.valueOf(x)).sum());
        System.out.println(Stream.of(str.split(",")).map(Integer::valueOf).mapToInt(x->x).sum());
    }

    @Test
    public void test19() throws Exception{
        List<String> list = Stream.of("aa", "aa", "bb", "cc").distinct().collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * distinct比较的是hashcode
     * @throws Exception
     */
    @Test
    public void test20() throws Exception{
        List<com.zbl.stream.User> list = Stream.of(
                new com.zbl.stream.User(1, "aa"),
                new com.zbl.stream.User(1, "aa"),
                new com.zbl.stream.User(3, "bb"),
                new com.zbl.stream.User(4, "cc")).distinct().collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test21() throws Exception{
        List<com.zbl.stream.User1> list = Stream.of(
                new com.zbl.stream.User1(1, "aa"),
                new com.zbl.stream.User1(2, "aa"),
                new com.zbl.stream.User1(3, "bb"),
                new com.zbl.stream.User1(4, "cc")).distinct().collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * 对list中的类的某个属性去重
     * @throws Exception
     */
    @Test
    public void test22() throws Exception{
        List<com.zbl.stream.User> list = Stream.of(
                new com.zbl.stream.User(1, "aa"),
                new com.zbl.stream.User(1, "aa"),
                new com.zbl.stream.User(3, "bb"),
                new com.zbl.stream.User(4, "cc")).
                collect(Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(com.zbl.stream.User::getName))),
                        ArrayList::new) );
        System.out.println(list);
    }







}
