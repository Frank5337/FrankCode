package com.zbl.wwj.concurrent.step3.p95_105_Atomic.p101;

import org.junit.Test;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: zbl
 * @Date: Created in 2021/4/7
 * @Description:
 * @Version: $
 */
public class AtomicReferenceTest {

    @Test
    public void test01() throws Exception {
        AtomicReference<Simple> atomic = new AtomicReference<>(new Simple("Alex", 24));
        System.out.println(atomic.get());
        boolean res = atomic.compareAndSet(new Simple("Alex", 24), new Simple("Alex", 24));
        System.out.println(res);

        JButton button = new JButton();
        //default value
        final AtomicReference<Simple> s = new AtomicReference<>(new Simple("test", 12));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //invoke restful service
                s.set(new Simple("alex", 2));
                ;
            }
        });

    }

    static class ObjectWrap<T> {
        private T t;

        public ObjectWrap(T t) {
            this.t = t;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }

    static class Simple {
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
