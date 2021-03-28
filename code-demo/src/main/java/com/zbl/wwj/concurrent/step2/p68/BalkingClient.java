package com.zbl.wwj.concurrent.step2.p68;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/28
 * @Description:
 * @Version: $
 * https://www.jb51.net/article/115790.htm
 *本文实例讲述了Java多线程模式之Balking模式。分享给大家供大家参考，具体如下：
 *
 * 当现在不适合这个操作，或是没有必要进行这个操作时就直接放弃这个操作而回去。这个就是Balking模式
 *
 * 例如王某在餐厅吃饭，当王某需要点餐时喊服务员需要点餐。当服务员A和B都注意到了王某点餐的示意，这时服务员B看到服务员A已经去响应了王某的点餐请求，所以服务员B就不会再过去响应王某的点餐请求。
 *
 * 程序示例：
 *
 * 程序的需求是模拟一个自动保存的功能。自动保存是为了预防计算机忽然断电或则软件突然出错的危险，定期将数据保存在文件里的功能。
 *
 * 1、Data类：表示是否修改，及修改函数的类
 * 2、SaveThread类：定期保存
 * 3、ChangeThread：修改数据，保存文件
 * 4、Main：模拟数据
 *
 * 什么时候使用Balking模式？
 *
 * 1、不需要刻意去执行的时候
 * 2、不想等待警戒条件时
 * 3、警戒条件只有一次成立时
 */
public class BalkingClient {

    public static void main(String[] args) {
        BalkingData balkingData = new BalkingData("balking.txt", "我是默认内容");
        new ConsumerThread(balkingData).start(); //手动保存
        new WaiterThread(balkingData).start(); //自动保存

//        try (Writer writer = new FileWriter("D:\\develop\\IdeaProjects\\code\\code-demo\\src\\main\\resources\\balking.txt", true);) {
//        String r = BalkingClient.class.getClassLoader().getResource("balking.txt").getPath();
//        System.out.println(r);
        //D:\develop\IdeaProjects\code\code-demo\src\main\resources\balking.txt
        //D:\develop\IdeaProjects\code\balking.txt
//        try (Writer writer = new FileWriter("balking.txt", true);) {
//            writer.write("我是默认内容");
//            writer.write("\n");
//            writer.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
