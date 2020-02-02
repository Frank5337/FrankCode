package com.zbl.observer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: zbl
 * @Date: Created in 14:27 2020/1/18
 * @Description:
 * @Version: $
 */
public class TestFrame extends Frame {

    public void launch() {
        Button b = new Button("fuck y");
        b.addActionListener(new MyActionListener1());
        b.addActionListener(new MyActionListener2());
        this.add(b);
        this.pack();

        this.setLocation(300, 300);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TestFrame().launch();
    }

    private class MyActionListener1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button pressed 1");
        }
    }

    private class MyActionListener2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button pressed 2");
        }
    }
}
