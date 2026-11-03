package gui;

import javax.swing.*;
import java.awt.*;

/**
 * MessagePanel - Displays battle messages and game events with an Orange & Blue theme.
 */
public class MessagePanel extends JPanel {
    
    private JLabel oldestMessageLabel;
    private JLabel previousMessageLabel;
    private JLabel currentMessageLabel;
    
    // --- Orange & Blue Color Palette ---
    private static final Color PANEL_BG = new Color(26, 32, 44);            // Deep slate blue panel background
    private static final Color BORDER_ORANGE = new Color(255, 159, 67);     // Vibrant orange accent border
    private static final Color TEXT_COLOR = new Color(255, 159, 67);        // Vibrant orange for active message
    private static final Color PREVIOUS_TEXT_COLOR = new Color(144, 205, 244); // Sky blue for previous message
    private static final Color OLDEST_TEXT_COLOR = new Color(74, 85, 104);   // Muted slate grey-blue for oldest message
    
    // Modern Fonts
    private static final Font MESSAGE_FONT = new Font("Segoe UI", Font.BOLD, 15);
    private static final Font PREVIOUS_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font OLDEST_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    
    private static final int PANEL_HEIGHT = 105;

    /**
     * Constructor
     */
    public MessagePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(0, PANEL_HEIGHT));
        setBackground(PANEL_BG);
        
        // Sleek orange border around panel with internal padding
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_ORANGE, 2, true),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        // Oldest message label (most dimmed)
        oldestMessageLabel = new JLabel("", SwingConstants.CENTER);
        oldestMessageLabel.setFont(OLDEST_FONT);
        oldestMessageLabel.setForeground(OLDEST_TEXT_COLOR);
        oldestMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Previous message label (middle, sky blue)
        previousMessageLabel = new JLabel("", SwingConstants.CENTER);
        previousMessageLabel.setFont(PREVIOUS_FONT);
        previousMessageLabel.setForeground(PREVIOUS_TEXT_COLOR);
        previousMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Current message label (newest, vibrant orange highlight)
        currentMessageLabel = new JLabel("Welcome to Monster Battle!", SwingConstants.CENTER);
        currentMessageLabel.setFont(MESSAGE_FONT);
        currentMessageLabel.setForeground(TEXT_COLOR);
        currentMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(oldestMessageLabel);
        add(Box.createVerticalStrut(4));
        add(previousMessageLabel);
        add(Box.createVerticalStrut(6));
        add(currentMessageLabel);
    }
    
    /**
     * Set the message to display (shifts all messages down)
     * @param message The message text
     */
    public void setMessage(String message) {
        // Shift messages: current -> previous -> oldest
        oldestMessageLabel.setText(previousMessageLabel.getText());
        oldestMessageLabel.setForeground(OLDEST_TEXT_COLOR);
        
        previousMessageLabel.setText(currentMessageLabel.getText());
        previousMessageLabel.setForeground(PREVIOUS_TEXT_COLOR);
        
        // Set new current message
        currentMessageLabel.setText(Emoji.fit(message, MESSAGE_FONT));
        currentMessageLabel.setForeground(TEXT_COLOR);
    }
    
    /**
     * Set message with custom color
     * @param message The message text
     * @param color The text color
     */
    public void setMessage(String message, Color color) {
        // Shift messages: current -> previous -> oldest
        oldestMessageLabel.setText(previousMessageLabel.getText());
        oldestMessageLabel.setForeground(OLDEST_TEXT_COLOR);
        
        previousMessageLabel.setText(currentMessageLabel.getText());
        previousMessageLabel.setForeground(PREVIOUS_TEXT_COLOR);
        
        // Set new current message with custom color
        currentMessageLabel.setText(Emoji.fit(message, MESSAGE_FONT));
        currentMessageLabel.setForeground(color);
    }
    
    /**
     * Clear all messages
     */
    public void clearMessage() {
        oldestMessageLabel.setText("");
        previousMessageLabel.setText("");
        currentMessageLabel.setText("");
    }
}