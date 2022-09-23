package com.zbl.wwj.concurrent.step3.p159_PriorityBlockingQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

/**
 * @Author: zbl
 * @Date: Created in 2021/12/13 15:57
 * @Description:
 * @Version: $
 */
public class PriorityBlockingQueueExampleTest {

    private PriorityBlockingQueueExample example;

    @Before
    public void setUp() {
        this.example = new PriorityBlockingQueueExample();
    }

    @After
    public void tearDown() {
        this.example = null;
    }

    @Test
    public void testAddNewElement() throws Exception {
        //初始化的容量, 超过会扩容
        PriorityBlockingQueue<Object> queue = example.create(5);
        assertThat(queue.add("hello1"), equalTo(true));
        assertThat(queue.add("hello2"), equalTo(true));
        assertThat(queue.add("hello3"), equalTo(true));
        assertThat(queue.add("hello4"), equalTo(true));
        assertThat(queue.add("hello5"), equalTo(true));
        assertThat(queue.add("hello6"), equalTo(true));
    }

    @Test
    public void testGetNewElement() throws Exception {
        //初始化的容量, 超过会扩容
        PriorityBlockingQueue<Object> queue = example.create(3);
        assertThat(queue.add("hello1"), equalTo(true));
        assertThat(queue.add("hello2"), equalTo(true));
        assertThat(queue.add("hello3"), equalTo(true));

        assertThat(queue.element(), equalTo("hello1"));
        assertThat(queue.size(), equalTo(3));

        assertThat(queue.peek(), equalTo("hello1"));
        assertThat(queue.size(), equalTo(3));

        assertThat(queue.poll(), equalTo("hello1"));
        assertThat(queue.size(), equalTo(2));

        assertThat(queue.remove(), equalTo("hello2"));
        assertThat(queue.size(), equalTo(1));

        assertThat(queue.take(), equalTo("hello3"));
        assertThat(queue.size(), equalTo(0));

        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(() -> {
            queue.add("NEW_DATA");
        }, 1, TimeUnit.SECONDS);
        scheduledExecutor.shutdown();

        assertThat(queue.take(), equalTo("NEW_DATA"));

    }

    @Test(expected = NullPointerException.class)
    public void testAddNullElement() throws Exception {
        PriorityBlockingQueue<Object> queue = example.create(3);
        queue.add(null);
    }

    @Test(expected = ClassCastException.class)
    public void testAddObjectWithNoComparable() {
        PriorityBlockingQueue<Object> queue = example.create(3);
        queue.add(new UserWithNoComparable());
        fail("should not process here");
    }

    @Test
    public void testAddObject_WithNoComparable_WithNoComparator() {

        PriorityBlockingQueue<Object> queue = example.create(3, (o1, o2) -> { return o1.hashCode() - o2.hashCode();});
        queue.add(new UserWithNoComparable());
    }

    //不允许插入一个不能对比的
    static class UserWithNoComparable implements Comparable<UserWithNoComparable> {

        @Override
        public int compareTo(UserWithNoComparable o) {
            return 0;
        }

    }
}