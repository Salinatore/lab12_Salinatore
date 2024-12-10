package it.unibo.es3;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GUI extends JFrame {
    
    private final Map<Pair<Integer, Integer>, JButton> buttons = new HashMap<>();
    private final Logics logic;
    
    public GUI(int width) {
        this.logic = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*width, 70*width);
        
        JPanel externalPanel = new JPanel(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(width,width));
                
        for (int i=0; i<width; i++){
            for (int j=0; j<width; j++){
                final JButton jb = new JButton("");
                this.buttons.put(new Pair<>(i,j), jb);
                panel.add(jb);
            }
        }
        
        ActionListener al = e -> {
            this.logic.doIteration();
            this.updateView();
            if (this.logic.isFinished()) {
                System.exit(0);
            }
        };

        JButton iterates = new JButton(">");
        iterates.addActionListener(al);
        
        externalPanel.add(panel, BorderLayout.CENTER);
        externalPanel.add(iterates, BorderLayout.SOUTH);

        this.getContentPane().add(externalPanel);

        this.updateView();
        this.setVisible(true);
    }

    private void updateView() {
        Set<Pair<Integer, Integer>> currentActive = this.logic.activePointsSet();
        for (var pair : currentActive) {
            buttons.get(pair).setText("*");
        }
    }
}