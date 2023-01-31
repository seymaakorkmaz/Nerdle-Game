package nyp_8;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SadScreen extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

 
    public SadScreen(ArrayList<String> eq) {
    	setResizable(false);
    	Generator generator = new Generator();
        String equation = generator.print(eq);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 400, 450);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(216, 191, 216));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel isimler = new JLabel("19011010 - Beyda Guler / 20011055 - \u015Eeymanur Korkmaz");
        isimler.setHorizontalAlignment(SwingConstants.CENTER);
        isimler.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        isimler.setBounds(0, 0, 382, 31);
        contentPane.add(isimler);

        JLabel lblGameOver = new JLabel("GAME OVER!");
        lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
        lblGameOver.setForeground(new Color(69, 151, 132));
        lblGameOver.setFont(new Font("Century Gothic", Font.PLAIN, 30));
        lblGameOver.setBounds(92, 78, 200, 30);
        contentPane.add(lblGameOver);
        
        //dogru denklem yazilir.
        JLabel equationLabel_1 = new JLabel("CORRECT EQUATION");
        equationLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        equationLabel_1.setForeground(new Color(130, 3, 88));
        equationLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        equationLabel_1.setBounds(46, 119, 300, 80);
        contentPane.add(equationLabel_1);
        
        
        JLabel equationLabel = new JLabel(equation);
        equationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        equationLabel.setForeground(new Color(130, 3, 88));
        equationLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
        equationLabel.setBounds(46, 200, 300, 80);
        contentPane.add(equationLabel);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(69,151,132));
        separator.setBounds(61, 119, 268, 2);
        contentPane.add(separator);

        JButton btnNewButton = new JButton("Back to Menu");
        btnNewButton.setForeground(new Color(72, 61, 139));
        btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        Image img = new ImageIcon(this.getClass().getResource("/menu32.png")).getImage();
        btnNewButton.setIcon(new ImageIcon(img));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.gc();
                for (Window window : getWindows()) {
                       window.dispose();
                }
                MainScreen mainsc = new MainScreen();
                mainsc.setVisible(true);
                setVisible(false);
            }
        });
        btnNewButton.setBackground(new Color(204,204,204));
        btnNewButton.setBounds(92, 318, 200, 40);
        contentPane.add(btnNewButton);
        
      
    }
}
