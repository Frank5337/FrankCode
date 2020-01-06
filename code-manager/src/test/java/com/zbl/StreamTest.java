package com.zbl;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: zbl
 * @Date: Created in 10:31 2019/12/16
 * @Description:
 * @Version: $
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class StreamTest {

    List<Student> list = Arrays.asList(
            new Student(1,"z",20,Gender.MALE),
            new Student(2,"zb",21,Gender.MALE),
            new Student(3,"zl",22,Gender.FE_MALE),
            new Student(4,"fk",22,Gender.FE_MALE)
    );

    /**
     * 首先，把集合转换为stream流，filter方法过滤我们需要的数据，调用collect(Collectors.toList())转换为我们需要的数据
     * @throws Exception
     */
    @Test
    public void test01() throws Exception{
        //list.stream(),  获得stream流
        //.filter 过滤
        //. collect(Collector.toList) 转成list
        List<Student> students = list.stream()
                                     .filter(student -> student.getGender().equals(Gender.MALE))
                                     .collect(Collectors.toList());
        System.out.println(students);
    }

    /**
     * 将所有的学生按照年纪分组，并且获取到所有的年纪的集合
     * @throws Exception
     */
    @Test
    public void test02() throws Exception{
        List<Integer> ageList = new ArrayList<>();
        list.forEach(student -> {
            if (!ageList.contains(student.getAge())) {
                ageList.add(student.getAge());
            }
        });

        System.out.println(ageList);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void test03() throws Exception{
        //去重
        List<Integer> ageList = list.stream().map(Student::getAge).distinct().collect(Collectors.toList());
        System.out.println(ageList);
        //分组
        //Map<Integer,List<Student>> map = list.stream().collect(Collectors.groupingBy(Student::getAge));
        Map<Gender, List<Student>> map = list.stream().collect(Collectors.groupingBy(Student::getGender));
        System.out.println(map);
    }

    @Test
    public void test04() throws Exception{
        //按照姓名分组 名字不重复情况
        Map<String, Student> mapName = new HashMap<>();
        list.forEach(student -> {
            mapName.put(student.getName(), student);
        });
        System.out.println(mapName);

        //jdk8
        Map<String, Student> studentMap = list.stream().collect(Collectors.toMap(Student::getName, student -> student));
        System.out.println(studentMap);
    }

    /**
     * 集合遍历
     * @throws Exception
     */
    @Test
    public void test05() throws Exception{
        Map<String, Student> studentMap = list.stream()
                                              .collect(Collectors.toMap(Student::getName, student -> student));
        studentMap.forEach((name, student) -> {
            System.out.println(name + " *** "+ student);
        });
    }

    /**
     * 排序以及类型转换
     * @throws Exception
     */
    @Test
    public void test06() throws Exception{
        List<Student> studentList = list.stream()
                                        .sorted(Comparator.comparing(Student::getAge))
                                        .collect(Collectors.toList());
        System.out.println(studentList);
    }

    @Test
    public void test07() throws Exception{
        System.out.println("abc".substring(2,3));
    }


}
