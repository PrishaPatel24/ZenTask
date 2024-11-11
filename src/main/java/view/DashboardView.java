package view;

import javax.swing.*;

/**
 * Default open screen for the program.
 */
public class DashboardView extends JPanel {
    private final JLabel welcomeText;

    public DashboardView() {
        welcomeText = new JLabel("Welcome to your Dashboard.");
        this.add(welcomeText);
    }
}
