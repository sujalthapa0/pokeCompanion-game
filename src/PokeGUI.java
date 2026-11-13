import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class PokeGUI extends JFrame {

    private Pkmn currentPet;
    private CardLayout cardLayout;
    private JPanel rootPanel;

    // UI Components
    private JLabel spriteLabel;
    private JLabel moodLabel;
    private JProgressBar hungerBar;
    private JProgressBar energyBar;
    private JProgressBar funBar;
    private Timer tickTimer;

    public PokeGUI() {
        super("PokéCompanion - Virtual Pet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 650);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        rootPanel = new JPanel(cardLayout);

        rootPanel.add(createStartScreen(), "START");
        rootPanel.add(createMainScreen(), "MAIN");
        add(rootPanel);

        // Passive decay timer: ticks every 15 seconds
        tickTimer = new Timer(15000, e -> {
            if (currentPet != null) {
                currentPet.tick(); // Triggers the tick method from Pkmn_7.java
                updateUIState();
            }
        });

        cardLayout.show(rootPanel, "START");
    }

    // --- SCREEN 1: ADOPTION CENTER ---
    private JPanel createStartScreen() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;

        JLabel title = new JLabel("Adopt a Pokémon!");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(title, gbc);

        gbc.gridy++;
        String[] starters = {"Pika", "Charmender", "Squirtle", "Bulbasaur"};
        JComboBox<String> selector = new JComboBox<>(starters);
        panel.add(selector, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Give it a Nickname (Optional):"), gbc);

        gbc.gridy++;
        JTextField nicknameField = new JTextField(12);
        panel.add(nicknameField, gbc);

        gbc.gridy++;
        JButton adoptBtn = new JButton("Adopt & Start!");
        adoptBtn.setBackground(new Color(60, 180, 75));
        adoptBtn.setForeground(Color.WHITE);
        adoptBtn.setFocusPainted(false);

        adoptBtn.addActionListener(e -> {
            String choice = (String) selector.getSelectedItem();
            String nick = nicknameField.getText().trim();
            String finalNick = nick.isEmpty() ? null : nick;

            // Instantiate based on subclass files
            switch (choice) {
                case "Pika" -> currentPet = (finalNick == null) ? new Pika() : new Pika(finalNick);
                case "Charmender" -> currentPet = (finalNick == null) ? new Charmender() : new Charmender(finalNick);
                case "Squirtle" -> currentPet = (finalNick == null) ? new Squirtle() : new Squirtle(finalNick);
                case "Bulbasaur" -> currentPet = (finalNick == null) ? new Bulbasaur() : new Bulbasaur(finalNick);
            }

            // Load Image based on species name
            String imgPath = "images/" + currentPet.name.toLowerCase() + ".png";
            ImageIcon icon = new ImageIcon(imgPath);
            // Scale image to fit perfectly
            Image scaledImg = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            spriteLabel.setIcon(new ImageIcon(scaledImg));
            spriteLabel.setText(""); // Remove placeholder text

            updateUIState();
            cardLayout.show(rootPanel, "MAIN");
            tickTimer.start();
        });
        panel.add(adoptBtn, gbc);

        return panel;
    }

    // --- SCREEN 2: MAIN ROOM ---
    private JPanel createMainScreen() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 15, 10, 15));
        panel.setBackground(Color.WHITE);

        // TOP: Vitals Bars
        JPanel statsBox = new JPanel(new GridLayout(3, 1, 5, 5));
        statsBox.setBackground(Color.WHITE);
        hungerBar = createStatBar(new Color(220, 100, 50));
        energyBar = createStatBar(new Color(50, 160, 220));
        funBar = createStatBar(new Color(120, 200, 60));
        statsBox.add(wrapBarWithLabel("🍎 Hunger:", hungerBar));
        statsBox.add(wrapBarWithLabel("💤 Energy:", energyBar));
        statsBox.add(wrapBarWithLabel("⚽ Fun:", funBar));
        panel.add(statsBox, BorderLayout.NORTH);

        // CENTER: Image & Mood
        JPanel roomPanel = new JPanel(new BorderLayout());
        roomPanel.setBackground(new Color(245, 245, 250));

        moodLabel = new JLabel("Mood: HAPPY", SwingConstants.CENTER);
        moodLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        roomPanel.add(moodLabel, BorderLayout.NORTH);

        spriteLabel = new JLabel("[Image Missing]", SwingConstants.CENTER);
        roomPanel.add(spriteLabel, BorderLayout.CENTER);
        panel.add(roomPanel, BorderLayout.CENTER);

        // BOTTOM: Interaction Buttons
        JPanel actionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        actionsPanel.setBackground(Color.WHITE);

        JButton feedBtn = new JButton("Feed");

        feedBtn.addActionListener(e -> { currentPet.feed(); updateUIState(); });

        JButton sleepBtn = new JButton("Sleep");
        sleepBtn.addActionListener(e -> { currentPet.sleep(); updateUIState(); });

        JButton speakBtn = new JButton("Speak");
        speakBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, currentPet.getNickName() +": "+ currentPet.speak());
            currentPet.speak();
            updateUIState();
        });

        JButton playBtn = new JButton("Play Games");
        playBtn.addActionListener(e -> { currentPet.play(); updateUIState(); });

        actionsPanel.add(feedBtn);
        actionsPanel.add(sleepBtn);
        actionsPanel.add(speakBtn);
        actionsPanel.add(playBtn);
        panel.add(actionsPanel, BorderLayout.SOUTH);

        return panel;
    }




    // --- Helpers ---
    private JProgressBar createStatBar(Color color) {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setValue(100);
        bar.setStringPainted(true);
        bar.setForeground(color);
        return bar;
    }

    private JPanel wrapBarWithLabel(String labelText, JProgressBar bar) {
        JPanel p = new JPanel(new BorderLayout(10, 0));
        p.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(labelText);
        lbl.setPreferredSize(new Dimension(80, 20));
        p.add(lbl, BorderLayout.WEST);
        p.add(bar, BorderLayout.CENTER);
        return p;
    }

    private void updateUIState() {
        if (currentPet == null) return;

        String mood = currentPet.getMood();
        moodLabel.setText(currentPet.getNickName() + "'s Mood: " + (mood != null ? mood.toUpperCase() : "UNKNOWN"));

        if ("angry".equalsIgnoreCase(mood)) {
            moodLabel.setForeground(Color.RED);
        } else if ("sad".equalsIgnoreCase(mood)) {
            moodLabel.setForeground(new Color(130, 0, 180));
        } else if ("happy".equalsIgnoreCase(mood)) {
            moodLabel.setForeground(new Color(0, 140, 40));
        } else {
            moodLabel.setForeground(Color.DARK_GRAY);
        }

        hungerBar.setValue(currentPet.getHunger());
        energyBar.setValue(currentPet.getEnergy());
        funBar.setValue(currentPet.getFun());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PokeGUI().setVisible(true);
        });
    }
}
