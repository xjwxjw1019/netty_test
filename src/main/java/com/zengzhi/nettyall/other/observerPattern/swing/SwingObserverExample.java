package com.zengzhi.nettyall.other.observerPattern.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author xiejiawei
 * @Date 2021-05-05
 * @Time 11:21
 */
public class SwingObserverExample {

    JFrame frame;

    public static void main(String[] args) {
        SwingObserverExample example = new SwingObserverExample();
        example.go();
    }

    public void go() {
        frame = new JFrame();

        JButton button = new JButton("Should I do it?");

        // Without lambdas
        button.addActionListener(new AngelListener());
        button.addActionListener(new DevilListener());

        // With lambdas
//        button.addActionListener(event ->
//                System.out.println("Don't do it, you might regret it!")
//        );
//        button.addActionListener(event ->
//                System.out.println("Come on, do it!")
//        );
//
//         Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, button);
        frame.setSize(300,300);
        frame.setVisible(true);
    }

	class AngelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("Don't do it, you might regret it!");
		}
	}

	class DevilListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("Come on, do it!");
		}
	}

}
