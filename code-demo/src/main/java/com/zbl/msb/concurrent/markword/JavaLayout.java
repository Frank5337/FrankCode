package com.zbl.msb.concurrent.markword;

import org.openjdk.jol.info.ClassLayout;

/**
 * Created with IDEA
 * author:foreign
 * Date:2020/3/14
 * Time:19:34
 */
public class JavaLayout {
    /**
     * 对象在内存中的存储布局
     * 普通对象new XXX();对象头 + 实例数据 + 对齐
     * 对象头(markword + class pointer) 12个字节
     * markword：其中Lock指令、synchronized的各种信息 8个字节
     * class pointer：该对象数据哪个类型的指向，比如Person.class 4个字节
     * instance data(实例数据)：比如对象中的变量就存在其中
         byte     1字节
         short    2字节
         int      4字节
         long     8字节
         char     2字节（C语言中是1字节）可以存储一个汉字
         float    4字节
         double   8字节
         boolean  false/true(理论上占用1bit,1/8字节，实际处理按1byte处理)
     * padding(对齐)：如果数据不能被8整除、则补齐。CPU读取数据是按总线宽度进行读取、这样提高效率
     */
    public static void main(String[] args) {
        /**
         * fangke@laogongdeMacBook-Pro hodgepodge % java -XX:+PrintCommandLineFlags -version
         *
         * -XX:InitialHeapSize=134217728    起始的堆大小
         * -XX:MaxHeapSize=2147483648       最大堆大小
         * -XX:+PrintCommandLineFlags       命令行参数
         * -XX:+UseCompressedClassPointers  压缩指针 （JVM64位，那么指针长度就应该是64位，8个字节 但是由于开启了该命令，则会压缩为4个字节）
         * -XX:+UseCompressedOops           普通的对象指针（当前对象指针指向另外的对象）
         * -XX:+UseParallelGC
         *
         * java version "1.8.0_181"
         * Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
         * Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
         */
        Object o = new Object();

        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        /**
         * 因为o对象里面没有变量、所以没有instance data
         *
         * /Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/bin/java "-javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=58047:/Applications/IntelliJ IDEA.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath /Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/tools.jar:/Users/fangke/Documents/hodgepodge/muli_thread/target/classes:/Users/fangke/.m2/repository/org/openjdk/jol/jol-core/0.9/jol-core-0.9.jar:/Users/fangke/.m2/repository/org/springframework/boot/spring-boot-starter/2.0.0.RELEASE/spring-boot-starter-2.0.0.RELEASE.jar:/Users/fangke/.m2/repository/org/springframework/boot/spring-boot/2.0.0.RELEASE/spring-boot-2.0.0.RELEASE.jar:/Users/fangke/.m2/repository/org/springframework/spring-context/5.0.4.RELEASE/spring-context-5.0.4.RELEASE.jar:/Users/fangke/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/2.0.0.RELEASE/spring-boot-autoconfigure-2.0.0.RELEASE.jar:/Users/fangke/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.0.0.RELEASE/spring-boot-starter-logging-2.0.0.RELEASE.jar:/Users/fangke/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/Users/fangke/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:/Users/fangke/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/Users/fangke/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.10.0/log4j-to-slf4j-2.10.0.jar:/Users/fangke/.m2/repository/org/apache/logging/log4j/log4j-api/2.10.0/log4j-api-2.10.0.jar:/Users/fangke/.m2/repository/org/slf4j/jul-to-slf4j/1.7.25/jul-to-slf4j-1.7.25.jar:/Users/fangke/.m2/repository/javax/annotation/javax.annotation-api/1.3.2/javax.annotation-api-1.3.2.jar:/Users/fangke/.m2/repository/org/springframework/spring-core/5.0.4.RELEASE/spring-core-5.0.4.RELEASE.jar:/Users/fangke/.m2/repository/org/springframework/spring-jcl/5.0.4.RELEASE/spring-jcl-5.0.4.RELEASE.jar:/Users/fangke/.m2/repository/org/yaml/snakeyaml/1.19/snakeyaml-1.19.jar:/Users/fangke/.m2/repository/org/springframework/boot/spring-boot-starter-web/2.0.0.RELEASE/spring-boot-starter-web-2.0.0.RELEASE.jar:/Users/fangke/.m2/repository/org/springframework/boot/spring-boot-starter-json/2.0.0.RELEASE/spring-boot-starter-json-2.0.0.RELEASE.jar:/Users/fangke/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.9.4/jackson-databind-2.9.4.jar:/Users/fangke/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.9.0/jackson-annotations-2.9.0.jar:/Users/fangke/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.9.4/jackson-core-2.9.4.jar:/Users/fangke/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.9.4/jackson-datatype-jdk8-2.9.4.jar:/Users/fangke/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.9.4/jackson-datatype-jsr310-2.9.4.jar:/Users/fangke/.m2/repository/com/fasterxml/jackson/module/jackson-module-parameter-names/2.9.4/jackson-module-parameter-names-2.9.4.jar:/Users/fangke/.m2/repository/org/springframework/boot/spring-boot-starter-tomcat/2.0.0.RELEASE/spring-boot-starter-tomcat-2.0.0.RELEASE.jar:/Users/fangke/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/8.5.28/tomcat-embed-core-8.5.28.jar:/Users/fangke/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/8.5.28/tomcat-embed-el-8.5.28.jar:/Users/fangke/.m2/repository/org/apache/tomcat/embed/tomcat-embed-websocket/8.5.28/tomcat-embed-websocket-8.5.28.jar:/Users/fangke/.m2/repository/org/hibernate/validator/hibernate-validator/6.0.7.Final/hibernate-validator-6.0.7.Final.jar:/Users/fangke/.m2/repository/javax/validation/validation-api/2.0.1.Final/validation-api-2.0.1.Final.jar:/Users/fangke/.m2/repository/org/jboss/logging/jboss-logging/3.3.2.Final/jboss-logging-3.3.2.Final.jar:/Users/fangke/.m2/repository/com/fasterxml/classmate/1.3.4/classmate-1.3.4.jar:/Users/fangke/.m2/repository/org/springframework/spring-web/5.0.4.RELEASE/spring-web-5.0.4.RELEASE.jar:/Users/fangke/.m2/repository/org/springframework/spring-beans/5.0.4.RELEASE/spring-beans-5.0.4.RELEASE.jar:/Users/fangke/.m2/repository/org/springframework/spring-webmvc/5.0.4.RELEASE/spring-webmvc-5.0.4.RELEASE.jar:/Users/fangke/.m2/repository/org/springframework/spring-aop/5.0.4.RELEASE/spring-aop-5.0.4.RELEASE.jar:/Users/fangke/.m2/repository/org/springframework/spring-expression/5.0.4.RELEASE/spring-expression-5.0.4.RELEASE.jar:/Users/fangke/.m2/repository/org/projectlombok/lombok/1.18.8/lombok-1.18.8.jar:/Users/fangke/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/fangke/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar com.foreign.JavaLayout
         * # WARNING: Unable to attach Serviceability Agent. You can try again with escalated privileges. Two options: a) use -Djol.tryWithSudo=true to try with sudo; b) echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope
         * java.lang.Object object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                                        VALUE
         *       0     4        (object header) mark word                          01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4        (object header) mark word                          00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header) class pointer                      e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
         *      12     4        (loss due to the next object alignment) padding
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        /**
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
         *      12     4        (loss due to the next object alignment)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         *
         * java.lang.Object object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           90 b9 71 0f (10010000 10111001 01110001 00001111) (259111312)
         *       4     4        (object header)                           00 70 00 00 (00000000 01110000 00000000 00000000) (28672)
         *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
         *      12     4        (loss due to the next object alignment)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */

        Test test = new Test();
        System.out.println(ClassLayout.parseInstance(test).toPrintable());
        /**
         * com.foreign.Test object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                                        VALUE
         *       0     4        (object header) mark word                          01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4        (object header) mark word                          00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header) class pointer                      81 f1 00 f8 (10000001 11110001 00000000 11111000) (-134155903)
         *      12     4        int Test.a      instance data                      0
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */

        TestLong testLong = new TestLong();
        System.out.println(ClassLayout.parseInstance(testLong).toPrintable());
        /**
         * com.foreign.TestLong object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                                                VALUE
         *       0     4        (object header)         mark word                          01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4        (object header)         mark word                          00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)         class pointer                      8e f2 00 f8 (10001110 11110010 00000000 11111000) (-134155634)
         *      12     4        (alignment/padding gap) padding
         *      16     8   long TestLong.b              instance data                      0
         * Instance size: 24 bytes
         * Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
         */
    }

    /**
     * synchronized锁的状态
     * new
     * 偏向锁
     * 轻量级锁（无锁/自旋锁/自适应自旋）
     * 重量级锁
     */
}

class Test {
    private int a;
}

class TestLong {
    private long b;
}

