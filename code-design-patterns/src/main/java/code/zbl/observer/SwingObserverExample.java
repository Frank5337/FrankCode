package code.zbl.observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/7
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class SwingObserverExample {

    JFrame frame;

    public static void main(String[] args) throws IOException {
        SwingObserverExample example = new SwingObserverExample();
        example.go();
    }

    public void go() {
        frame = new JFrame();

        JButton button = new JButton("Should I do it ?");
        button.addActionListener(new AngleListener());
        button.addActionListener(new DevilListener());
        frame.getContentPane().add(BorderLayout.CENTER, button);
        //在这里设置frame的属性
        frame.pack();
        frame.setLocation(300, 300);
        frame.setVisible(true);
    }

    static class AngleListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Don't do it, you might regret it!");
        }
    }

    static class DevilListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Come on do it!");
        }
    }
}
