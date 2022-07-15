package nyp_8;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;

public class Test extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


    public Test() {
    	setResizable(false);
        Random random = new Random();
        Generator generator = new Generator();
        int randomLength = random.nextInt(3) + 7 ;
        String equation = generator.print(generator.GenerateEquation(randomLength));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 400, 450);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(216, 191, 216));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton mainScreenButton = new JButton("Back to Menu");
        mainScreenButton.setBackground(new Color(240, 240, 240));
        mainScreenButton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
        mainScreenButton.setForeground(new Color(69, 151, 132));
        mainScreenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainScreen mainscreen = new MainScreen();
                mainscreen.setVisible(true);
                setVisible(false);
            }
        });
        mainScreenButton.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        mainScreenButton.setBounds(70, 330, 260, 40);
        contentPane.add(mainScreenButton);

        JLabel equationLabel = new JLabel(equation);
        equationLabel.setForeground(new Color(69, 151, 132));
        equationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        equationLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
        equationLabel.setBounds(50, 120, 300, 80);
        contentPane.add(equationLabel);

        JButton newEquationButton = new JButton("Generate New Equation");
        newEquationButton.setBackground(new Color(240, 240, 240));
        newEquationButton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
        newEquationButton.setForeground(new Color(69, 151, 132));
        newEquationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int x = random.nextInt(3) + 7 ;
                equationLabel.setText(generator.print(generator.GenerateEquation(x)));
            }
        });
        newEquationButton.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        newEquationButton.setBounds(70, 270, 260, 40);
        contentPane.add(newEquationButton);

        JLabel lblNewLabel = new JLabel("EQUATION:");
        lblNewLabel.setForeground(new Color(69, 151, 132));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        lblNewLabel.setBounds(102, 79, 200, 30);
        contentPane.add(lblNewLabel);

        JLabel isimler = new JLabel("19011010 - Beyda Guler / 20011055 - Seymanur Korkmaz");
        isimler.setHorizontalAlignment(SwingConstants.CENTER);
        isimler.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        isimler.setBounds(0, 0, 372, 31);
        contentPane.add(isimler);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 0, 0));
        separator.setBackground(new Color(0, 0, 0));
        separator.setBounds(32, 120, 320, 1);
        contentPane.add(separator);


    }
}

