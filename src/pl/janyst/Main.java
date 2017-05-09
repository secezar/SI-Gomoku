package pl.janyst;

import pl.janyst.GUI.GomokuPanel;

import javax.swing.*;

/**
 * Created by Piotr Janyst on 2017-05-06.
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        final int FRAME_WIDTH = 1000;
        final int FRAME_HEIGHT = 1000;
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("GomokuBoard Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GomokuPanel panel = new GomokuPanel(15);
        frame.add(panel);
        frame.setVisible(true);
    }
}
