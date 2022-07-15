package nyp_8;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Congrat extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    public Congrat(int a,int b) {
    	setResizable(false); //Oyun basarili bir sekilde tamamlandiginda acilir.
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(200, 100, 400, 450);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(216, 191, 216));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel CongratLabel = new JLabel("CONGRATULATIONS");
        CongratLabel.setForeground(new Color(128, 0, 128));
        CongratLabel.setHorizontalAlignment(SwingConstants.CENTER);
        CongratLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
        CongratLabel.setBounds(10, 42, 364, 74);
        contentPane.add(CongratLabel);

        JLabel timeLabel = new JLabel(String.valueOf(a) + "." +String.valueOf(b) + " minutes"); //Game ekranýndan alinan bitirme süresi yazdirilir.
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 30));
        timeLabel.setBounds(100, 185, 200, 47);
        timeLabel.setForeground(new Color(69, 151, 132));
        contentPane.add(timeLabel);

        JLabel compTimeLabel = new JLabel("Completion Time"); 
        compTimeLabel.setForeground(new Color(69, 151, 132));
        compTimeLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        compTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        compTimeLabel.setBounds(100, 142, 200, 47);
        contentPane.add(compTimeLabel);

        JLabel isimler = new JLabel("19011010 - Beyda G\u00FCler / 20011055 - \u015Eeymanur Korkmaz");
        isimler.setHorizontalAlignment(SwingConstants.CENTER);
        isimler.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        isimler.setBounds(0, 0, 384, 31);
        contentPane.add(isimler);
        
        JButton btnNewButton = new JButton("Back to Menu");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	       System.gc();
                   for (Window window : getWindows()) {
                       window.dispose();
                   }
                MainScreen main = new MainScreen();
        		main.setVisible(true);
        		setVisible(false);
        	}
        });
        btnNewButton.setForeground(new Color(72, 61, 139));
        btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        btnNewButton.setBackground(new Color(204, 204, 204));
        btnNewButton.setBounds(100, 327, 200, 40);
        contentPane.add(btnNewButton);
    }
}
