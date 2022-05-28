package com.zbl.test.jvm;

/**
 * @Author: zbl
 * @Date: Created in 2022/5/29
 * @Description:
 * @Version: $
 *
 * 调试代码参数如下
 *
 * -verbose:gc
 * -Xmx200M
 * -Xms200M
 * -Xmn50M
 * -XX:+PrintGCDetails
 * -XX:TargetSurvivorRatio=60
 * -XX:+PrintTenuringDistribution
 * -XX:+PrintGCDateStamps
 * -XX:MaxTenuringThreshold=3
 * -XX:+UseConcMarkSweepGC
 * -XX:+UseParNewGC
 *
 * 本实例用于java GC以后，新生代survivor区域的变化，以及晋升到老年代的时间和方式的测试代码。需要自行分步注释不需要的代码进行反复测试对比
 * *
 * * 由于java的main函数以及其他基础服务也会占用一些eden空间，所以要提前空跑一次main函数，来看看这部分占用。
 * *
 * * 自定义的代码中，我们使用堆内分配数组和栈内分配数组的方式来分别模拟不可被GC的和可被GC的资源。
 *
 * 注意:如下输出结果中老年代的信息为 concurrent mark-sweep generation 和以前版本略有不同。另外，还列出了某次 GC 后是否重新生成了 threshold，以及各个年龄占用空间的大小。
 *
 * 2021-07-01T10:41:32.257+0800: [GC (Allocation Failure) 2021-07-01T10:41:32.257+0800: [ParNew
 * Desired survivor size 3145728 bytes, new threshold 1 (max 3)
 * - age   1:    3739264 bytes,    3739264 total
 * : 40345K->3674K(46080K), 0.0014584 secs] 40345K->3674K(199680K), 0.0015063 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * 2021-07-01T10:41:32.259+0800: [GC (Allocation Failure) 2021-07-01T10:41:32.259+0800: [ParNew
 * Desired survivor size 3145728 bytes, new threshold 3 (max 3)
 * : 13914K->0K(46080K), 0.0046596 secs] 13914K->13895K(199680K), 0.0046873 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * Heap
 *  par new generation   total 46080K, used 35225K [0x05000000, 0x08200000, 0x08200000)
 *   eden space 40960K,  86% used [0x05000000, 0x072667f0, 0x07800000)
 *   from space 5120K,   0% used [0x07800000, 0x07800000, 0x07d00000)
 *   to   space 5120K,   0% used [0x07d00000, 0x07d00000, 0x08200000)
 *  concurrent mark-sweep generation total 153600K, used 13895K [0x08200000, 0x11800000, 0x11800000)
 *  Metaspace       used 153K, capacity 2280K, committed 2368K, reserved 4480K
 *
 *
 */
public class JavaGCTest {

    public static void main(String[] args) throws InterruptedException {
        //空跑一次main函数来查看java服务本身占用的空间大小，我这里是占用了3M。所以40-3=37，下面分配三个1M的数组和一个34M的垃圾数组。


        // 为了达到TargetSurvivorRatio（期望占用的Survivor区域的大小）这个比例指定的值, 即5M*60%=3M(Desired survivor size)，
        // 这里用1M的数组的分配来达到Desired survivor size
        //说明: 5M为S区的From或To的大小，60%为TargetSurvivorRatio参数指定,可以更改参数获取不同的效果。
        byte[] byte1m_1 = new byte[1 * 1024 * 1024];
        byte[] byte1m_2 = new byte[1 * 1024 * 1024];
        byte[] byte1m_3 = new byte[1 * 1024 * 1024];

        //使用函数方式来申请空间，函数运行完毕以后，就会变成垃圾等待回收。此时应保证eden的区域占用达到100%。可以通过调整传入值来达到效果。
        makeGarbage(34);

        //再次申请一个数组，因为eden已经满了，所以这里会触发Minor GC
        byte[] byteArr = new byte[10 * 1024 * 1024];
        // 这次Minor Gc时, 三个1M的数组因为尚有引用，所以进入From区域（因为是第一次GC）age为1
        // 且由于From区已经占用达到了60%(-XX:TargetSurvivorRatio=60), 所以会重新计算对象晋升的age。
        // 计算方法见上文，计算出age：min(age, MaxTenuringThreshold) = 1，输出中会有Desired survivor size 3145728 bytes, new threshold 1 (max 3)字样
        //新的数组byteArr进入eden区域。


        //再次触发垃圾回收，证明三个1M的数组会因为其第二次回收后age为2，大于上一次计算出的new threshold 1，所以进入老年代。
        //而byteArr因为超过survivor的单个区域，直接进入了老年代。
        makeGarbage(34);
    }

    private static void makeGarbage(int size) {
        byte[] byteArrTemp = new byte[size * 1024 * 1024];
    }

}
