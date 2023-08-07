import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SimpleUserInterface extends JFrame implements ActionListener {
    private JTextArea textArea;
    private Color initialBackgroundColor;
    private Random random;

    public SimpleUserInterface() {

        // Set the title of the JFrame
        
        setTitle("Simple User Interface");

        // Set the initial size of the JFrame

        setSize(400, 300);

        // Specify what happens when the user closes the JFrame (exit the application)

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creates and sets up the menu bar with menu items

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem item1 = new JMenuItem("Print Date and Time");
        JMenuItem item2 = new JMenuItem("Write to log.txt");
        JMenuItem item3 = new JMenuItem("Change Background Color");
        JMenuItem item4 = new JMenuItem("Exit");

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);

        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);
        menuBar.add(menu);

        setJMenuBar(menuBar);

        // Creates the text area and adds it to the frame

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        random = new Random();
        initialBackgroundColor = getBackgroundColor();
        getContentPane().setBackground(initialBackgroundColor);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Handles different actions based on the menu item selected

        switch (command) {
            case "Print Date and Time":
                printDateAndTime();
                break;
            case "Write to log.txt":
                writeToFile();
                break;
            case "Change Background Color":
                changeBackgroundColor();
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }

    // Gets the current date and time and formats it using SimpleDateFormat
    private void printDateAndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(new Date());

        // Appends the formatted date and time to the JTextArea

        textArea.append(dateTime + "\n");
    }

    // Writes the contents of the JTextArea to the file "log.txt"
    private void writeToFile() {
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(this, "Contents written to log.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error writing to file: " + ex.getMessage());
        }
    }

    // Generates a random background color using getBackgroundColor() method
    private void changeBackgroundColor() {
        Color randomColor = getBackgroundColor();
        textArea.setBackground(randomColor);
    }

    private Color getBackgroundColor() {
        float hue = random.nextFloat();
        return Color.getHSBColor(hue, 1.0f, 1.0f);
    }

    // Start the Swing application by invoking the SimpleUserInterface constructor in the event dispatch thread

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleUserInterface());
    }
}