import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class Estados extends Thread {
    private JFrame win;
    private String myName;
    private String state = "new";
    private boolean running = true;
    private JLabel imageLabel = new JLabel();

    Estados(String name) {
        myName = name;
        createWindow();
    }

    private void createWindow() {
        win = new JFrame(myName);
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        win.setSize(400, 400);

        JPanel mainPanel = new JPanel(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon("huevo.png");
        imageLabel.setIcon(imageIcon);

        mainPanel.add(imageLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton button1 = new JButton("Run");
        JButton button2 = new JButton("Interrupt");
        JButton button3 = new JButton("Terminate");

        button1.setEnabled(true);
        button2.setEnabled(false);
        button3.setEnabled(false);

        button1.addActionListener((e) -> {
            if (state == "new") {
                this.start();
            } else if (state == "interrupt") {
                this.interrupt();
            }
            state = "run";
            button1.setEnabled(false);
            button2.setEnabled(true);
            button3.setEnabled(true);
        });
        button2.addActionListener((e) -> {
            state = "interrupt";
            button1.setEnabled(true);
            button2.setEnabled(false);
            button3.setEnabled(true);
        });
        button3.addActionListener((e) -> {
            ImageIcon newImage = new ImageIcon("polloa.png");
            imageLabel.setIcon(newImage);
            if (state == "interrupt") {
                this.interrupt();
            }
            state = "terminate";
            running = false;
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        win.add(mainPanel);

        win.setVisible(true);
    }

    private void work() {
        while (running) {
            ImageIcon newImage = null;
            newImage = new ImageIcon("pollo.png");
            imageLabel.setIcon(newImage);

            imageLabel.repaint();
            try {
                if (state == "interrupt") {
                    newImage = new ImageIcon("pollod.png");
                    imageLabel.setIcon(newImage);
                    Thread.sleep(999999999);
                } else {
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                if (isInterrupted())
                    System.out.println("Continue");
            }
        }
    }

    public void run() {
        work();
    }

}