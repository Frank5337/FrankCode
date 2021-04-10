package com.zbl.wwj.concurrent.step3.p110_112_CountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/10
 * @Description:
 * @Version: $
 */
public class CountDownLatchExample4 {

    private static Random random = new Random(System.currentTimeMillis());

    static class Event {
        int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }

    interface Watcher {
//        void startWatch();

        void done(Table table);
    }

    static class Table {
        String tableName;
        long sourceRecordCount = 10;
        long targetCount;
        String columnSchema = "<table name='a'>column name = 'coll'";
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount, long targetCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
            this.targetCount = targetCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetCount=" + targetCount +
                    ", columnSchema='" + columnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    private static List<Table> capture(Event event) {
        List<Table> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Table("table-" + event.id + "-" + i, i * 1000, 0));
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        Event[] events = {new Event(1), new Event(2)};
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (Event event : events) {
            List<Table> tables = capture(event);
            TaskGroup taskGroup = new TaskGroup(tables.size(), event);
            for (Table table : tables) {
                TaskBatch taskBatch = new TaskBatch(2, taskGroup);
                TrustSourceRecordCount sourceRecordCount = new TrustSourceRecordCount(table, taskBatch);
                TrustSourceColumns sourceColumns = new TrustSourceColumns(table, taskBatch);
                service.submit(sourceRecordCount);
                service.submit(sourceColumns);
            }
        }

        Thread.sleep(100000);
        service.shutdown();

    }

    static class TaskGroup implements Watcher {

        private CountDownLatch countDownLatch;

        private Event event;

        public TaskGroup(int size, Event event) {
            this.countDownLatch = new CountDownLatch(size);
            this.event = event;
        }

//        @Override
//        public void startWatch() {
//
//        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("All of table done in event:" + event.id);
            }
        }
    }

    static class TaskBatch implements Watcher {

        private CountDownLatch countDownLatch;

        private TaskGroup taskGroup;

        public TaskBatch(int size, TaskGroup taskGroup) {
            this.countDownLatch = new CountDownLatch(size);
            this.taskGroup = taskGroup;
        }

//        @Override
//        public void startWatch() {
//
//        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("TableName:" + table.tableName + " finish. " + table.toString());
                taskGroup.done(table);
            }
        }
    }

    static class TrustSourceRecordCount implements Runnable {
        private final Table table;

        private final TaskBatch taskBatch;

        public TrustSourceRecordCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.sourceRecordCount;
//            System.out.println("TableName:" + table.tableName + " TrustSourceRecordCount done. ");
            taskBatch.done(table);
        }
    }

    static class TrustSourceColumns implements Runnable {
        private final Table table;

        private final TaskBatch taskBatch;

        public TrustSourceColumns(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.columnSchema;
//            System.out.println("TableName:" + table.tableName + " TrustSourceColumns done. ");
            taskBatch.done(table);
        }
    }
}
