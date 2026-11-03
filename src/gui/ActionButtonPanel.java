package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ActionButtonPanel - Four customizable action buttons styled with an Orange & Blue theme.
 */
public class ActionButtonPanel extends JPanel {
    
    private JButton[] buttons;
    private ActionListener actionListener;
    
    // Default button labels
    private static final String[] DEFAULT_LABELS = {
        "Action 1", "Action 2", "Action 3", "Action 4"
    };
    
    // --- Orange & Blue Color Palette ---
    private static final Color PANEL_BG = new Color(26, 32, 44);         // Deep slate blue container
    private static final Color BUTTON_BG = new Color(43, 54, 73);        // Dark slate blue button body
    private static final Color BUTTON_HOVER = new Color(59, 74, 107);    // Brighter slate blue on hover
    private static final Color BUTTON_PRESSED = new Color(229, 142, 38);  // Warm orange when clicked
    private static final Color BUTTON_DISABLED = new Color(45, 50, 60);  // Dark muted grey for disabled state
    
    private static final Color BORDER_COLOR = new Color(255, 159, 67);  // Vibrant orange border
    private static final Color TEXT_DISABLED = new Color(100, 110, 125); // Muted text color when disabled
    
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 15);
    
    /**
     * Constructor
     * @param listener ActionListener to handle button clicks
     */
    public ActionButtonPanel(ActionListener listener) {
        this.actionListener = listener;
        this.buttons = new JButton[4];
        
        setLayout(new GridLayout(1, 4, 12, 0)); // 12px gap between buttons
        setBackground(PANEL_BG);
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8)); // Container padding
        
        createButtons();
    }
    
    /**
     * Create the four action buttons
     */
    private void createButtons() {
        for (int i = 0; i < 4; i++) {
            buttons[i] = createStyledButton(DEFAULT_LABELS[i], i);
            add(buttons[i]);
        }
    }
    
    /**
     * Create a styled button
     * @param label Button label
     * @param index Button index (0-3)
     * @return The styled button
     */
    private JButton createStyledButton(String label, int index) {
        JButton button = new JButton(Emoji.fit(label, BUTTON_FONT));
        button.setFont(BUTTON_FONT);
        button.setBackground(BUTTON_BG);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        
        // Stylish double border: vibrant orange outline with inner margin
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 2, true),
            BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));
        
        button.setActionCommand(String.valueOf(index));
        button.addActionListener(actionListener);
        
        // Add hover and click state effects
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setBackground(BUTTON_HOVER);
                    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setBackground(BUTTON_BG);
                    button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setBackground(BUTTON_PRESSED);
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (button.isEnabled()) {
                    // Return to hover background if mouse is still within bounds
                    Point point = e.getPoint();
                    if (button.contains(point)) {
                        button.setBackground(BUTTON_HOVER);
                    } else {
                        button.setBackground(BUTTON_BG);
                    }
                }
            }
        });
        
        return button;
    }
    
    /**
     * Set custom labels for the buttons
     * @param labels Array of 4 labels
     */
    public void setButtonLabels(String[] labels) {
        if (labels == null || labels.length != 4) {
            throw new IllegalArgumentException("Must provide exactly 4 labels");
        }
        
        for (int i = 0; i < 4; i++) {
            buttons[i].setText(Emoji.fit(labels[i], BUTTON_FONT));
        }
    }
    
    /**
     * Enable or disable all buttons
     * @param enabled true to enable, false to disable
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (JButton button : buttons) {
            applyEnabledState(button, enabled);
        }
    }
    
    /**
     * Enable or disable a specific button
     * @param index Button index (0-3)
     * @param enabled true to enable, false to disable
     */
    public void setButtonEnabled(int index, boolean enabled) {
        if (index >= 0 && index < 4) {
            applyEnabledState(buttons[index], enabled);
        }
    }
    
    /**
     * Helper to update visual state when enabled/disabled
     */
    private void applyEnabledState(JButton button, boolean enabled) {
        button.setEnabled(enabled);
        if (enabled) {
            button.setBackground(BUTTON_BG);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(BUTTON_DISABLED);
            button.setForeground(TEXT_DISABLED);
        }
    }
}