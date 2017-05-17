package pl.janyst.GUI;

import pl.janyst.Algorithms.ArtificalIntelligence;
import pl.janyst.Game.Gomoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;


public class GomokuPanel extends JPanel {
    private final int MARGIN = 5;
    private final double PIECE_FRAC = 0.9;

    Gomoku gomoku;
    ArtificalIntelligence ai;
    ArtificalIntelligence ai2;

    private static int GAME_STATE;
    private static int GAME_MODE;

    private static final int HUMAN_VS_HUMAN = 1;
    private static final int HUMAN_VS_MINIMAX = 2;
    private static final int MINIMAX_VS_MINIMAX = 3;

    private static final int PLAY = 1;
    private static final int FINISH = 2;
    private static final int DRAW = 3;

    public GomokuPanel() {
        this(15);
    }

    public GomokuPanel(int size) {
        gomoku = new Gomoku(15);
        addMouseListener(new GomokuListener());
        GAME_STATE = PLAY;
        GAME_MODE = HUMAN_VS_HUMAN;
    }

    public GomokuPanel(int size, ArtificalIntelligence ai) {
        gomoku = new Gomoku(15);
        this.ai = ai;
        GAME_MODE = HUMAN_VS_MINIMAX;
    }

    public GomokuPanel(int size, ArtificalIntelligence ai, ArtificalIntelligence ai2) {
        gomoku = new Gomoku(15);
        this.ai = ai;
        this.ai2 = ai2;
        GAME_MODE = MINIMAX_VS_MINIMAX;
    }

    class GomokuListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            double panelWidth = getWidth();
            double panelHeight = getHeight();
            double boardWidth = Math.min(panelWidth, panelHeight) - 2 * MARGIN;
            int gomokuSize = gomoku.getSize();
            double squareWidth = boardWidth / gomokuSize;
            double xLeft = (panelWidth - boardWidth) / 2 + MARGIN;
            double yTop = (panelHeight - boardWidth) / 2 + MARGIN;
            int column = (int) Math.round((e.getX() - xLeft) / squareWidth - 0.5);
            int row = (int) Math.round((e.getY() - yTop) / squareWidth - 0.5);
            try {
                boolean isMoved = gomoku.makeMove(row,column);
                boolean win = gomoku.isWin(row, column);
                boolean draw = gomoku.getMoves() == gomokuSize * gomokuSize + 1;

                if(win) {
                    JOptionPane.showMessageDialog(null, "The player " + gomoku.getPlayer() + " win.");
                    gomoku = new Gomoku(gomokuSize);
                }
                else {
                    if(draw) {
                        JOptionPane.showMessageDialog(null, "DRAW!");
                        gomoku = new Gomoku(gomokuSize);
                    }
                }
                if (isMoved && !win && !draw)
                    gomoku.switchPlayer();
            }
            catch(IndexOutOfBoundsException ex){
                System.out.println("Border clicked");
            }

            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        double panelWidth = getWidth();
        double panelHeight = getHeight();

        g2.setColor(new Color(0.925f, 0.670f, 0.34f)); // light wood
        g2.fill(new Rectangle2D.Double(0, 0, panelWidth, panelHeight));


        double boardWidth = Math.min(panelWidth, panelHeight) - 2 * MARGIN;
        int size = gomoku.getSize();
        double squareWidth = boardWidth / size;
        double gridWidth = (size - 1) * squareWidth;
        double pieceDiameter = PIECE_FRAC * squareWidth;
        boardWidth -= pieceDiameter;
        double xLeft = (panelWidth - boardWidth) / 2 + MARGIN;
        double yTop = (panelHeight - boardWidth) / 2 + MARGIN;

        g2.setColor(Color.BLACK);
        for (int i = 0; i < size; i++) {
            double offset = i * squareWidth;
            g2.draw(new Line2D.Double(xLeft, yTop + offset,
                    xLeft + gridWidth, yTop + offset));
            g2.draw(new Line2D.Double(xLeft + offset, yTop,
                    xLeft + offset, yTop + gridWidth));
        }

        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++) {
            boolean found = false;
            if(gomoku.getBoardElement(row, column) == 1) {
                found = true;
                g2.setColor(Color.WHITE);
            }
            if(gomoku.getBoardElement(row, column) == 2) {
                found = true;
                g2.setColor(Color.BLACK);
            }
            if(found) {
                double xCenter = xLeft + column * squareWidth;
                double yCenter = yTop + row * squareWidth;
                Ellipse2D.Double circle = new Ellipse2D.Double(xCenter - pieceDiameter / 2,
                        yCenter - pieceDiameter / 2, pieceDiameter, pieceDiameter);
                g2.fill(circle);
                g2.setColor(Color.BLACK);
                g2.draw(circle);
            }
        }
    }
}