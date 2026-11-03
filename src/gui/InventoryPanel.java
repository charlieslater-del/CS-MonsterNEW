package gui;

import javax.swing.*;

import game.Item;

import java.awt.*;
import java.util.ArrayList;

/**
 * InventoryPanel - Displays player's consumable items with a sleek Orange & Blue aesthetic.
 */
public class InventoryPanel extends JPanel {
    
    private ArrayList<Item> items;
    private JPanel itemListPanel;
    
    // --- Orange & Blue Color Palette ---
    private static final Color DARK_BLUE = new Color(26, 32, 44);       // Deep slate blue panel background
    private static final Color CARD_BG = new Color(45, 55, 72);         // Slate blue for item cards
    private static final Color ACCENT_ORANGE = new Color(255, 159, 67);  // Vibrant orange for highlights & headers
    private static final Color BORDER_ORANGE = new Color(229, 142, 38);  // Card border outline
    private static final Color SOFT_BLUE = new Color(99, 179, 237);      // Sky blue for slot index tags
    private static final Color TEXT_MUTED = new Color(160, 174, 192);    // Muted grey-blue for secondary text
    
    // Modern Fonts
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font ITEM_FONT = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font INDEX_FONT = new Font("Segoe UI", Font.BOLD, 11);
    
    private static final int PANEL_WIDTH = 220;

    /**
     * Constructor
     */
    public InventoryPanel() {
        items = new ArrayList<>();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(PANEL_WIDTH, 0));
        setBackground(DARK_BLUE);
        
        // --- Header / Title ---
        JLabel titleLabel = new JLabel("INVENTORY", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(ACCENT_ORANGE);
        
        // Outer padding + subtle orange bottom divider line
        titleLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, ACCENT_ORANGE),
            BorderFactory.createEmptyBorder(12, 10, 12, 10)
        ));
        add(titleLabel, BorderLayout.NORTH);
        
        // --- Scrollable Item List Container ---
        itemListPanel = new JPanel();
        itemListPanel.setLayout(new BoxLayout(itemListPanel, BoxLayout.Y_AXIS));
        itemListPanel.setBackground(DARK_BLUE);
        itemListPanel.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8)); // Padding inside scroll view
        
        JScrollPane scrollPane = new JScrollPane(itemListPanel);
        scrollPane.setBorder(null);
        scrollPane.setBackground(DARK_BLUE);
        scrollPane.getViewport().setBackground(DARK_BLUE);
        
        // Custom sleek scrollbar appearance
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(6, 0));
        
        add(scrollPane, BorderLayout.CENTER);
        
        // Initial state
        showEmptyMessage();
    }
    
    /**
     * Set the items to display
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
        refreshDisplay();
    }
    
    /**
     * Refresh the item display
     */
    private void refreshDisplay() {
        itemListPanel.removeAll();
        
        if (items == null || items.isEmpty()) {
            showEmptyMessage();
        } else {
            for (int i = 0; i < items.size(); i++) {
                itemListPanel.add(createItemPanel(items.get(i), i));
                itemListPanel.add(Box.createVerticalStrut(8)); // Space between items
            }
        }
        
        itemListPanel.revalidate();
        itemListPanel.repaint();
    }
    
    /**
     * Show empty inventory message
     */
    private void showEmptyMessage() {
        JLabel emptyLabel = new JLabel("Inventory Empty", SwingConstants.CENTER);
        emptyLabel.setFont(ITEM_FONT);
        emptyLabel.setForeground(TEXT_MUTED);
        emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        itemListPanel.add(Box.createVerticalGlue());
        itemListPanel.add(emptyLabel);
        itemListPanel.add(Box.createVerticalGlue());
    }
    
    /**
     * Create a stylized panel for a single item
     */
    private JPanel createItemPanel(Item item, int index) {
        JPanel panel = new JPanel(new BorderLayout(8, 0));
        panel.setBackground(CARD_BG);
        
        // Sleek orange border around each card with internal padding
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_ORANGE, 1, true), // Rounded-feel border
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        
        panel.setMaximumSize(new Dimension(PANEL_WIDTH - 20, 52));
        
        // Item Icon
        Font iconFont = new Font("Serif", Font.PLAIN, 22);
        JLabel iconLabel = new JLabel(Emoji.fit(item.getIcon(), iconFont));
        iconLabel.setFont(iconFont);
        panel.add(iconLabel, BorderLayout.WEST);
        
        // Item Name
        JLabel nameLabel = new JLabel(Emoji.fit(item.getName(), ITEM_FONT));
        nameLabel.setFont(ITEM_FONT);
        nameLabel.setForeground(Color.WHITE);
        panel.add(nameLabel, BorderLayout.CENTER);
        
        // Slot Tag / Index
        JLabel indexLabel = new JLabel("#" + (index + 1));
        indexLabel.setFont(INDEX_FONT);
        indexLabel.setForeground(SOFT_BLUE);
        panel.add(indexLabel, BorderLayout.EAST);
        
        return panel;
    }
}