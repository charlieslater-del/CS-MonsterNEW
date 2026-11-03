package gui;

import javax.swing.*;

import game.Item;
import game.Monster;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * MonsterBattleGUI - Main window frame with a sleek Dark Slate Blue & Orange aesthetic.
 * 
 * Provides methods to update your game state.
 */
public class MonsterBattleGUI extends JFrame {
    
    // GUI Components
    private MonsterDisplayPanel monsterPanel;
    private ActionButtonPanel buttonPanel;
    private InventoryPanel inventoryPanel;
    private MessagePanel messagePanel;
    private PlayerStatusPanel playerStatusPanel;
    
    // Theme Colors
    private static final Color DARK_BLUE = new Color(26, 32, 44);       // Slate panel background
    private static final Color ACCENT_ORANGE = new Color(255, 159, 67);  // Orange highlight for title/borders
    
    // For button clicks
    private int selectedAction = -1;
    private boolean waitingForInput = false;
    private final Object actionLock = new Object();
    
    /**
     * Constructor
     * @param title Window title
     */
    public MonsterBattleGUI(String title) {
        super(title);
        
        initializeComponents();
        layoutComponents();
        
        setSize(1040, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    /**
     * Initialize components
     */
    private void initializeComponents() {
        monsterPanel = new MonsterDisplayPanel();
        buttonPanel = new ActionButtonPanel(e -> handleButtonClick(e));
        inventoryPanel = new InventoryPanel();
        messagePanel = new MessagePanel();
        playerStatusPanel = new PlayerStatusPanel(100);
    }
    
    /**
     * Layout components with consistent dark backgrounds and spacing
     */
    private void layoutComponents() {
        Container rootPane = getContentPane();
        rootPane.setBackground(DARK_BLUE);
        rootPane.setLayout(new BorderLayout(12, 12));
        
        // Center: Monsters area
        add(monsterPanel, BorderLayout.CENTER);
        
        // Bottom: Player status, action buttons, and battle log
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setOpaque(false);
        
        JPanel controlPanel = new JPanel(new BorderLayout(10, 10));
        controlPanel.setOpaque(false);
        controlPanel.add(playerStatusPanel, BorderLayout.NORTH);
        controlPanel.add(buttonPanel, BorderLayout.CENTER);
        
        bottomPanel.add(controlPanel, BorderLayout.CENTER);
        bottomPanel.add(messagePanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Right: Inventory side panel
        add(inventoryPanel, BorderLayout.EAST);
        
        // Window inner edge margin
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
    }
    
    // ==================== STUDENT API METHODS ====================
    
    /**
     * Update the monster display
     * @param monsters Your list of monsters
     */
    public void updateMonsters(ArrayList<Monster> monsters) {
        SwingUtilities.invokeLater(() -> monsterPanel.setMonsters(monsters));
    }
    
    /**
     * Update player's current health
     * @param health Current health value
     */
    public void updatePlayerHealth(int health) {
        SwingUtilities.invokeLater(() -> playerStatusPanel.setHealth(health));
    }
    
    /**
     * Set player's maximum health (call once at start)
     * @param maxHealth Maximum health value
     */
    public void setPlayerMaxHealth(int maxHealth) {
        SwingUtilities.invokeLater(() -> playerStatusPanel.setMaxHealth(maxHealth));
    }
    
    /**
     * Set player's speed stat
     * @param speed The speed value
     */
    public void setPlayerSpeed(int speed) {
        SwingUtilities.invokeLater(() -> playerStatusPanel.setSpeed(speed));
    }
    
    /**
     * Update the inventory display
     * @param items Your list of items
     */
    public void updateInventory(ArrayList<Item> items) {
        SwingUtilities.invokeLater(() -> inventoryPanel.setItems(items));
    }
    
    /**
     * Display a message to the player
     * @param message The message text
     */
    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> messagePanel.setMessage(message));
    }
    
    /**
     * Set button labels (4 buttons: indices 0-3)
     * @param labels Array of 4 button labels
     */
    public void setActionButtons(String[] labels) {
        if (labels == null || labels.length != 4) {
            throw new IllegalArgumentException("Must provide exactly 4 button labels");
        }
        SwingUtilities.invokeLater(() -> buttonPanel.setButtonLabels(labels));
    }
    
    /**
     * Wait for player to click a button
     * BLOCKS until a button is clicked!
     * @return Button index that was clicked (0-3)
     */
    public int waitForAction() {
        selectedAction = -1;
        waitingForInput = true;
        
        SwingUtilities.invokeLater(() -> buttonPanel.setEnabled(true));
        
        synchronized(actionLock) {
            while (selectedAction == -1) {
                try {
                    actionLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return -1;
                }
            }
        }
        
        waitingForInput = false;
        return selectedAction;
    }
    
    /**
     * Highlight a monster (useful for showing targets)
     * @param index Monster index to highlight, -1 to clear
     */
    public void highlightMonster(int index) {
        SwingUtilities.invokeLater(() -> monsterPanel.highlightMonster(index));
    }
    
    /**
     * Enable/disable buttons
     * @param enabled true to enable, false to disable
     */
    public void setButtonsEnabled(boolean enabled) {
        SwingUtilities.invokeLater(() -> buttonPanel.setEnabled(enabled));
    }
    
    /**
     * Pause execution (useful for showing effects)
     * @param milliseconds Time to pause
     */
    public void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // ==================== INTERNAL METHODS ====================
    
    /**
     * Handle button clicks
     */
    private void handleButtonClick(ActionEvent e) {
        if (!waitingForInput) return;
        
        selectedAction = Integer.parseInt(e.getActionCommand());
        
        SwingUtilities.invokeLater(() -> buttonPanel.setEnabled(false));
        
        synchronized(actionLock) {
            actionLock.notifyAll();
        }
    }
}