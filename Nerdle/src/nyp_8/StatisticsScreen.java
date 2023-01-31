package nyp_8;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;

public class StatisticsScreen extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


    public StatisticsScreen() {
    	setResizable(false);
        FileOperations fileOp = new FileOperations();
        Statistics statistic = fileOp.read("statistics.txt");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 400, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(216, 191, 216));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel isimler = new JLabel("19011010 - Beyda G\u00FCler / 20011055 - \u015Eeymanur Korkmaz");
        isimler.setHorizontalAlignment(SwingConstants.CENTER);
        isimler.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        isimler.setBounds(0, 0, 384, 31);
        contentPane.add(isimler);

        JLabel lblNewLabel = new JLabel("Statistics :");
        lblNewLabel.setForeground(new Color(128, 0, 128));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        lblNewLabel.setBounds(127, 43, 127, 31);
        contentPane.add(lblNewLabel);

        JLabel successfulLabel = new JLabel("X");
        successfulLabel.setText(String.valueOf(statistic.getSuccessful()));
        successfulLabel.setForeground(new Color(95, 158, 160));
        successfulLabel.setHorizontalAlignment(SwingConstants.CENTER);
        successfulLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        successfulLabel.setBounds(39, 85, 46, 48);
        contentPane.add(successfulLabel);

        JLabel abortedLabel = new JLabel("X");
        abortedLabel.setText(String.valueOf(statistic.getAborted()));
        abortedLabel.setForeground(new Color(95, 158, 160));
        abortedLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        abortedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        abortedLabel.setBounds(167, 85, 46, 48);
        contentPane.add(abortedLabel);

        JLabel lbl = new JLabel("Game");
        lbl.setForeground(new Color(95, 158, 160));
        lbl.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setBounds(24, 124, 70, 21);
        contentPane.add(lbl);

        JLabel lblNewLabel_1 = new JLabel("unsuccessful");
        lblNewLabel_1.setForeground(new Color(95, 158, 160));
        lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(274, 144, 89, 21);
        contentPane.add(lblNewLabel_1);

        JLabel lbl_1 = new JLabel("Game");
        lbl_1.setForeground(new Color(95, 158, 160));
        lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lbl_1.setBounds(154, 124, 70, 21);
        contentPane.add(lbl_1);

        JLabel lblNewLabel_1_1 = new JLabel("aborted");
        lblNewLabel_1_1.setForeground(new Color(95, 158, 160));
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblNewLabel_1_1.setBounds(145, 144, 89, 21);
        contentPane.add(lblNewLabel_1_1);

        JLabel unsuccessfulLabel = new JLabel("X");
        unsuccessfulLabel.setText(String.valueOf(statistic.getUnsuccessful()));
        unsuccessfulLabel.setForeground(new Color(95, 158, 160));
        unsuccessfulLabel.setHorizontalAlignment(SwingConstants.CENTER);
        unsuccessfulLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        unsuccessfulLabel.setBounds(286, 85, 46, 48);
        contentPane.add(unsuccessfulLabel);

        JLabel lbl_2 = new JLabel("Game");
        lbl_2.setForeground(new Color(95, 158, 160));
        lbl_2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_2.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lbl_2.setBounds(274, 124, 70, 21);
        contentPane.add(lbl_2);

        JLabel lblNewLabel_1_1_1 = new JLabel("successful");
        lblNewLabel_1_1_1.setForeground(new Color(95, 158, 160));
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblNewLabel_1_1_1.setBounds(10, 144, 106, 21);
        contentPane.add(lblNewLabel_1_1_1);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 0, 0));
        separator.setBounds(39, 201, 305, 2);
        contentPane.add(separator);

        JLabel lblNewLabel_2 = new JLabel("Successful Games  :");
        lblNewLabel_2.setForeground(new Color(128, 0, 128));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Century Gothic", Font.BOLD, 15));
        lblNewLabel_2.setBounds(106, 214, 164, 31);
        contentPane.add(lblNewLabel_2);

        JLabel avgRowLabel = new JLabel("X");
        avgRowLabel.setText(String.valueOf(statistic.getAverageNumberRows()));
        avgRowLabel.setForeground(new Color(95, 158, 160));
        avgRowLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avgRowLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        avgRowLabel.setBounds(88, 256, 46, 48);
        contentPane.add(avgRowLabel);

        JLabel avgTimeLabel = new JLabel("X");
        avgTimeLabel.setText(String.valueOf(statistic.getAverageTime()));
        avgTimeLabel.setForeground(new Color(95, 158, 160));
        avgTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avgTimeLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        avgTimeLabel.setBounds(243, 256, 46, 48);
        contentPane.add(avgTimeLabel);

        JLabel lblNewLabel_3 = new JLabel("Average");
        lblNewLabel_3.setForeground(new Color(95, 158, 160));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblNewLabel_3.setBounds(68, 305, 89, 21);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_3_1 = new JLabel("Average");
        lblNewLabel_3_1.setForeground(new Color(95, 158, 160));
        lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblNewLabel_3_1.setBounds(228, 305, 89, 21);
        contentPane.add(lblNewLabel_3_1);

        JLabel lblNewLabel_4 = new JLabel("row number");
        lblNewLabel_4.setForeground(new Color(95, 158, 160));
        lblNewLabel_4.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(63, 328, 106, 21);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_4_1 = new JLabel("time");
        lblNewLabel_4_1.setForeground(new Color(95, 158, 160));
        lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        lblNewLabel_4_1.setBounds(216, 328, 106, 21);
        contentPane.add(lblNewLabel_4_1);

        JButton btnNewButton = new JButton("Back to Menu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainScreen mainsc = new MainScreen();
                mainsc.setVisible(true);
                setVisible(false);
            }
        });
        btnNewButton.setForeground(new Color(128, 0, 128));
        btnNewButton.setFont(new Font("Century Gothic", Font.PLAIN, 13));
        btnNewButton.setBackground(new Color(245, 245, 245));
        btnNewButton.setBounds(127, 369, 127, 31);
        contentPane.add(btnNewButton);
    }
}
