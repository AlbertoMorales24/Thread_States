import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class Principal extends JFrame {
    private int numT;
    private List<Estados> works = new ArrayList<>();
    public List<Thread> ths = new ArrayList<>();

    Principal(String name) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setName(name);
        this.setSize(500, 500);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));
        JButton button = new JButton("New Thread Herencia");
        JButton button2 = new JButton("New Thread Interface");
        JLabel jlabel6 = new JLabel();
        ImageIcon imageIcon = new ImageIcon("up.png");
        jlabel6.setIcon(imageIcon);
        JLabel jlabel2 = new JLabel("Universidad Panamericana - Programación en Paralelo");
        JLabel jlabel = new JLabel("Alberto Morales y Raschid Llamas");
        JLabel jlabel4 = new JLabel("0230866 - 0238166");
        JLabel jlabel3 = new JLabel("Juan Carlos López Pimentel");
        JLabel jlabel5 = new JLabel("Septiembre 26, 2023");

        button.addActionListener((e) -> {
            createThread();
        });

        button2.addActionListener((e) -> {
            createThread2();
        });

        panel.add(button);
        panel.add(button2);
        panel.add(jlabel6);
        panel.add(jlabel);
        panel.add(jlabel4);
        panel.add(jlabel2);
        panel.add(jlabel3);
        panel.add(jlabel5);

        this.add(panel);
        this.setVisible(true);
        numT = 0;
    }

    private void createThread() {
        works.add(new Estados("Work " + numT));
        ths.add(new Thread(works.get(numT)));
        numT += 1;
    }

    private void createThread2() {
        Estados2 estados2 = new Estados2();
        works.add(null);
        Thread thread = new Thread(estados2);
        ths.add(thread);

        estados2.startThread(thread);
        numT += 1;
    }

    public static void main(String[] args) {
        new Principal("Main");
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // print the exception
            }
        }
    }
}