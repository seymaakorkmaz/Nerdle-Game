package nyp_8;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class MainScreen extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private Statistics statistic,newStatistic;
    HalfGame game;
    

    public MainScreen() {
    	FileOperations fileOp =  new FileOperations();
        Image img;

        setResizable(false);
        setTitle("Nerdle");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 680, 725);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(216, 191, 216));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel isimler = new JLabel("19011010 - Beyda Guler / 20011055 - Seymanur Korkmaz");
        isimler.setBounds(140, 11, 400, 31);
        isimler.setHorizontalAlignment(SwingConstants.CENTER);
        isimler.setFont(new Font("Century Gothic", Font.PLAIN, 13));

        JLabel lblNewLabel = new JLabel("nerdle.");
        lblNewLabel.setForeground(new Color(130, 3, 88));
        img = new ImageIcon(this.getClass().getResource("/nerdleLogo60.png")).getImage();
        lblNewLabel.setIcon(new ImageIcon(img));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Ebrima", Font.PLAIN, 40));
        lblNewLabel.setBounds(197, 88, 268, 55);
        contentPane.setLayout(null);
        contentPane.add(isimler);
        contentPane.add(lblNewLabel);

        JButton devametbuton = new JButton("Continue"); //Yarým kalan oyuna devam etme
        devametbuton.setBackground(new Color(245, 245, 245));
        devametbuton.setForeground(new Color(69, 151, 132));
        devametbuton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	File dosya = new File("equation.txt");
                if(dosya.exists()){
                	//istatistik dosyasi kaydedilir.
	                statistic = fileOp.read("statistics.txt");
	                newStatistic = new Statistics(statistic.getAborted()-1,statistic.getUnsuccessful(),statistic.getSuccessful(),statistic.getAverageNumberRows(),statistic.getAverageTime());
	                fileOp.deleteFile("statistics.txt");
	                fileOp.write("statistics.txt",newStatistic);
	                
	                
	                Game game = new Game(1);
	                game.setVisible(true);
	                setVisible(false);
                }else {
                	JOptionPane.showMessageDialog(null, "You don't have any unfinished games");
                
                }
            
            }
        });
        devametbuton.setFont(new Font("Century Gothic", Font.BOLD, 15));
        devametbuton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
        devametbuton.setBounds(265, 401, 150, 45);
        contentPane.add(devametbuton);

        JButton testbuton = new JButton("Test");
        testbuton.setForeground(new Color(69, 151, 132));
        testbuton.setBackground(new Color(245, 245, 245));
        testbuton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Test test = new Test();
                test.setVisible(true);
                setVisible(false);
            }
        });
        testbuton.setFont(new Font("Century Gothic", Font.BOLD, 15));
        testbuton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
        testbuton.setBounds(265, 472, 150, 45);
        contentPane.add(testbuton);

        JButton istatistikbuton = new JButton("Statistics");
        istatistikbuton.setForeground(new Color(69, 151, 132));
        istatistikbuton.setBackground(new Color(245, 245, 245));
        istatistikbuton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StatisticsScreen statistics = new StatisticsScreen();
                statistics.setVisible(true);
                setVisible(false);
            }
        });
        istatistikbuton.setFont(new Font("Century Gothic", Font.BOLD, 15));
        istatistikbuton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
        istatistikbuton.setBounds(265, 544, 150, 45);
        contentPane.add(istatistikbuton);


        JButton yenioyunbuton = new JButton("New Game");
        yenioyunbuton.setForeground(new Color(69, 151, 132));
        yenioyunbuton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
        yenioyunbuton.setBackground(new Color(245, 245, 245));
        yenioyunbuton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game game = new Game(0);
                game.setVisible(true);
                setVisible(false);
            }
        });
        yenioyunbuton.setFont(new Font("Century Gothic", Font.BOLD, 15));
        yenioyunbuton.setBounds(265, 325, 150, 45);
        contentPane.add(yenioyunbuton);
        
        JButton btnNewButton = new JButton("");
        img = new ImageIcon(this.getClass().getResource("/exit24.png")).getImage();
        btnNewButton.setIcon(new ImageIcon(img));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        	}
        });
        btnNewButton.setBackground(new Color(204,204,204));
        btnNewButton.setBounds(630, 11, 24, 24);
        contentPane.add(btnNewButton);
    }
}
