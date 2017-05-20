package pl.janyst.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jola on 2017-05-16.
 */
public class ConfigurationPanel extends JFrame{
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        JButton button = new JButton("Human vs Human");

        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(button, c);

        button.addActionListener(e -> {
            // display/center the jdialog when the button is pressed
            final int FRAME_WIDTH = 1000;
            final int FRAME_HEIGHT = 1000;
            JFrame frame = new JFrame();
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
            frame.setTitle("GomokuBoard Game");
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            GomokuPanel panel = new GomokuPanel();
            frame.add(panel);
            frame.setVisible(true);
        });

        JTextArea difficulty = new JTextArea("Difficulty");
        difficulty.setEnabled(false);
        difficulty.setFont(difficulty.getFont().deriveFont(10f));
        difficulty.setVisible(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        pane.add(difficulty, c);

        JTextArea lineHeuristicWeight = new JTextArea("Line Heuristic Weight");
        lineHeuristicWeight.setEnabled(false);
        lineHeuristicWeight.setFont(lineHeuristicWeight.getFont().deriveFont(10f));
        lineHeuristicWeight.setVisible(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 0;
        pane.add(lineHeuristicWeight, c);

        JTextArea clusterHeuristicWeight = new JTextArea("Cluster Heuristic Weight");
        clusterHeuristicWeight.setEnabled(false);
        clusterHeuristicWeight.setFont(clusterHeuristicWeight.getFont().deriveFont(10f));
        clusterHeuristicWeight.setVisible(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 0;
        pane.add(clusterHeuristicWeight, c);

        JTextArea neighbourHeuristicWeight = new JTextArea("Neighbour Heuristic Weight");
        neighbourHeuristicWeight.setEnabled(false);
        neighbourHeuristicWeight.setFont(neighbourHeuristicWeight.getFont().deriveFont(10f));
        neighbourHeuristicWeight.setVisible(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 5;
        c.gridy = 0;
        pane.add(neighbourHeuristicWeight, c);

        JTextArea orderMoveHeuristics = new JTextArea("Order Move Heuristics");
        orderMoveHeuristics.setEnabled(false);
        orderMoveHeuristics.setFont(orderMoveHeuristics.getFont().deriveFont(10f));
        orderMoveHeuristics.setVisible(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 6;
        c.gridy = 0;
        pane.add(orderMoveHeuristics, c);


        JButton button2 = new JButton("Human vs Minmax");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(button2, c);

        JCheckBox checkBox = new JCheckBox("alpha-beta");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        pane.add(checkBox, c);

        JSlider sliderDepthFirstAI = new JSlider();

        sliderDepthFirstAI.setToolTipText("Depth of first AI");
        sliderDepthFirstAI.setMinimum(1);
        sliderDepthFirstAI.setMaximum(6);
        sliderDepthFirstAI.setMajorTickSpacing(1);
        sliderDepthFirstAI.setPaintTicks(true);
        sliderDepthFirstAI.setSize(5, 200);
        sliderDepthFirstAI.setVisible(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        pane.add(sliderDepthFirstAI, c);

        JSlider sliderHeuristicLineFirstAI = new JSlider();

        sliderHeuristicLineFirstAI.setToolTipText("Line Heuristic weight");
        sliderHeuristicLineFirstAI.setMinimum(1);
        sliderHeuristicLineFirstAI.setMaximum(6);
        sliderHeuristicLineFirstAI.setMajorTickSpacing(1);
        sliderHeuristicLineFirstAI.setPaintTicks(true);
        sliderHeuristicLineFirstAI.setSize(5, 100);
        sliderHeuristicLineFirstAI.setVisible(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 1;
        pane.add(sliderHeuristicLineFirstAI, c);

        JSlider sliderHeuristicClusterFirstAI = new JSlider();

        sliderHeuristicClusterFirstAI.setToolTipText("Cluster Heuristic weight");
        sliderHeuristicClusterFirstAI.setMinimum(1);
        sliderHeuristicClusterFirstAI.setMaximum(6);
        sliderHeuristicClusterFirstAI.setMajorTickSpacing(1);
        sliderHeuristicClusterFirstAI.setPaintTicks(true);
        sliderHeuristicClusterFirstAI.setSize(5, 100);
        sliderHeuristicClusterFirstAI.setVisible(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 1;
        pane.add(sliderHeuristicClusterFirstAI, c);

        JSlider sliderHeuristicNeighboursFirstAI = new JSlider();

        sliderHeuristicNeighboursFirstAI.setToolTipText("Neighbour Heuristic weight");
        sliderHeuristicNeighboursFirstAI.setMinimum(1);
        sliderHeuristicNeighboursFirstAI.setMaximum(6);
        sliderHeuristicNeighboursFirstAI.setMajorTickSpacing(1);
        sliderHeuristicNeighboursFirstAI.setPaintTicks(true);
        sliderHeuristicNeighboursFirstAI.setSize(5, 100);
        sliderHeuristicNeighboursFirstAI.setVisible(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 5;
        c.gridy = 1;
        pane.add(sliderHeuristicNeighboursFirstAI, c);

        String[] data = {"one", "two", "three", "four"};
        final JComboBox<String> cb = new JComboBox<>(data);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 6;
        c.gridy = 1;
        pane.add(cb, c);
        cb.setVisible(true);

        JButton button3 = new JButton("Minmax vs Minmax");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(button3, c);

        JCheckBox checkBox2 = new JCheckBox("alpha-beta");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(checkBox2, c);

        JSlider sliderDepthSecondAI = new JSlider();

        sliderDepthSecondAI.setToolTipText("Depth of second AI");
        sliderDepthSecondAI.setMinimum(1);
        sliderDepthSecondAI.setMaximum(6);
        sliderDepthSecondAI.setMajorTickSpacing(1);
        sliderDepthSecondAI.setPaintTicks(true);
        sliderDepthSecondAI.setSize(5, 200);
        sliderDepthSecondAI.setVisible(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        pane.add(sliderDepthSecondAI, c);

        JSlider sliderHeuristicLineSecondAI = new JSlider();

        sliderHeuristicLineSecondAI.setToolTipText("Line Heuristic weight");
        sliderHeuristicLineSecondAI.setMinimum(1);
        sliderHeuristicLineSecondAI.setMaximum(6);
        sliderHeuristicLineSecondAI.setMajorTickSpacing(1);
        sliderHeuristicLineSecondAI.setPaintTicks(true);
        sliderHeuristicLineSecondAI.setSize(5, 100);
        sliderHeuristicLineSecondAI.setVisible(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 2;
        pane.add(sliderHeuristicLineSecondAI, c);

        JSlider sliderHeuristicClusterSecondAI = new JSlider();

        sliderHeuristicClusterSecondAI.setToolTipText("Cluster Heuristic weight");
        sliderHeuristicClusterSecondAI.setMinimum(1);
        sliderHeuristicClusterSecondAI.setMaximum(6);
        sliderHeuristicClusterSecondAI.setMajorTickSpacing(1);
        sliderHeuristicClusterSecondAI.setPaintTicks(true);
        sliderHeuristicClusterSecondAI.setSize(5, 100);
        sliderHeuristicClusterSecondAI.setVisible(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 2;
        pane.add(sliderHeuristicClusterSecondAI, c);

        JSlider sliderHeuristicNeighboursSecondAI = new JSlider();

        sliderHeuristicNeighboursSecondAI.setToolTipText("Neighbour Heuristic weight");
        sliderHeuristicNeighboursSecondAI.setMinimum(1);
        sliderHeuristicNeighboursSecondAI.setMaximum(6);
        sliderHeuristicNeighboursSecondAI.setMajorTickSpacing(1);
        sliderHeuristicNeighboursSecondAI.setPaintTicks(true);
        sliderHeuristicNeighboursSecondAI.setSize(5, 100);
        sliderHeuristicNeighboursSecondAI.setVisible(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 5;
        c.gridy = 2;
        pane.add(sliderHeuristicNeighboursSecondAI, c);

        String[] data2 = {"one", "two", "three", "four"};
        final JComboBox<String> cb2 = new JComboBox<>(data);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 6;
        c.gridy = 2;
        pane.add(cb2, c);
        cb2.setVisible(true);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setTitle("Gomoku Configuration Panel");
        frame.setResizable(false);
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
