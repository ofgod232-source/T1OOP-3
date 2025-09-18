import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Main class for the Swing application.
 * It sets up the JFrame, menu bar, panels, and handles view navigation with dynamic color changes.
 */
public class ColorChangingSwingApp extends JFrame {

    private CardLayout cardLayout;       // Layout manager for switching views
    private JPanel contentPanel;         // Main content area
    private final Random random = new Random(); // For generating random colors

    /**
     * Constructor: Initializes the GUI components and layout.
     */
    public ColorChangingSwingApp() {
        setTitle("Color Changing Swing App");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window

        initializeMenuBar();       // Set up menu bar
        initializeMainLayout();    // Set up layout and panels
    }

    /**
     * Initializes the menu bar and connects menu items to view switching.
     */
    private void initializeMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu viewMenu = new JMenu("View");

        // Create menu items
        JMenuItem homeItem = new JMenuItem("Home");
        JMenuItem profileItem = new JMenuItem("Profile");
        JMenuItem settingsItem = new JMenuItem("Settings");

        // Add items to menu
        viewMenu.add(homeItem);
        viewMenu.add(profileItem);
        viewMenu.add(settingsItem);
        menuBar.add(viewMenu);
        setJMenuBar(menuBar);

        // Attach view names to menu items
        homeItem.addActionListener(e -> changeView("Home"));
        profileItem.addActionListener(e -> changeView("Profile"));
        settingsItem.addActionListener(e -> changeView("Settings"));
    }

    /**
     * Initializes the main layout including header, navigation, and content panels.
     */
    private void initializeMainLayout() {
        setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.add(new JLabel("Commited to serve you", JLabel.CENTER));
        add(headerPanel, BorderLayout.NORTH);

        // Navigation panel with buttons
        JPanel navPanel = new JPanel(new GridLayout(3, 1));
        navPanel.setBackground(Color.GRAY);

        JButton homeButton = new JButton("Home");
        JButton profileButton = new JButton("Profile");
        JButton settingsButton = new JButton("Settings");

        // Add buttons to navigation panel
        navPanel.add(homeButton);
        navPanel.add(profileButton);
        navPanel.add(settingsButton);
        add(navPanel, BorderLayout.WEST);

        // Attach listeners to navigation buttons
        homeButton.addActionListener(e -> changeView("Home"));
        profileButton.addActionListener(e -> changeView("Profile"));
        settingsButton.addActionListener(e -> changeView("Settings"));

        // Content panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Add views to content panel
        contentPanel.add(createViewPanel("Welcome to the Home view!"), "Home");
        contentPanel.add(createViewPanel("This is your Profile view."), "Profile");
        contentPanel.add(createViewPanel("Adjust your Settings here."), "Settings");

        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Creates a view panel with a label.
     * @param text The text to display in the panel.
     * @return A JPanel containing the label.
     */
    private JPanel createViewPanel(String text) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(label);
        return panel;
    }

    /**
     * Changes the current view and updates its background color randomly.
     * @param viewName The name of the view to display.
     */
    private void changeView(String viewName) {
        cardLayout.show(contentPanel, viewName); // Switch view

        // Update background color of the visible panel
        for (Component comp : contentPanel.getComponents()) {
            if (comp.isVisible()) {
                comp.setBackground(generateRandomColor());
            }
        }
    }

    /**
     * Generates a random pastel-like color.
     * @return A Color object with random RGB values.
     */
    private Color generateRandomColor() {
        int r = 100 + random.nextInt(156);
        int g = 100 + random.nextInt(156);
        int b = 100 + random.nextInt(156);
        return new Color(r, g, b);
    }

    /**
     * Main method: Launches the application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ColorChangingSwingApp().setVisible(true);
        });
    }
}
