package nyp_8;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

public class Game extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JToggleButton[][] matrix;
	private int row = 0, column = 0;
	private int k, l;
	private int randomLength;
	private Random random;
	private JLabel timeLabel;
	private Statistics statistic, newStatistic;
	private HalfGame game;
	private SadScreen sadsc;
	private int prevButton = 0;
	Calculator calculator;
	Generator generator;
	ArrayList<String> eq;
	String answer;
	HalfGame gamee;
	int t;
	int a, i;
	int x, y;
	int flag;
	
	public Game(int cont) {
		setResizable(false);
		FileOperations fileOp = new FileOperations();
		fileOp.init("statistics.txt");
		StopWatch.start();

		random = new Random();

		calculator = new Calculator();
		generator = new Generator();
		setTitle("Nerdle");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 680, 725);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(216, 191, 216));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel isimler = new JLabel("19011010 - Beyda Güler / 20011055 - Þeymanur Korkmaz");
		isimler.setHorizontalAlignment(SwingConstants.CENTER);
		isimler.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		isimler.setBounds(107, 11, 372, 31);
		contentPane.add(isimler);

		JPanel gamePanel = new JPanel();
		gamePanel.setBackground(new Color(216, 191, 216));
		gamePanel.setBounds(10, 50, 644, 489);
		contentPane.add(gamePanel);
		gamePanel.setLayout(new GridLayout(6, 7, 30, 25));

		if (cont == 0) { // yarim birakilmis oyun yoksa yeni denklem olusturulur.
			t = 0;
			randomLength = random.nextInt(3) + 7; // denklemin uzunlugu random sekilde secilir.
			eq = generator.GenerateEquation(randomLength); // denklem uretilir.
			matrix = new JToggleButton[6][randomLength];

			// ekrana eklenicek butonlar olusturulur.
			for (k = 0; k < 6; k++) {
				for (l = 0; l < randomLength; l++) {
					matrix[k][l] = new JToggleButton();
					matrix[k][l].setHorizontalAlignment(SwingConstants.CENTER);
					matrix[k][l].setFont(new Font("Century Gothic", Font.BOLD, 17));
					matrix[k][l].setBackground(SystemColor.textInactiveText);
					matrix[k][l].setForeground(new Color(0, 0, 0));
					matrix[k][l].setBounds(30 * k, 30 * l, 30, 30);
					gamePanel.add(matrix[k][l]);
				}
			}

			for (int i = 1; i < 6; i++) {
				for (int j = 0; j < randomLength; j++) {
					matrix[i][j].setEnabled(false);
				}

			}

		} else if (cont == 1) { // yarim birakilmis oyun varsa dosyadan eski oyun cekilir.

			game = fileOp.readEquation("equation.txt"); // dosyadan yarim kalan oyun okunur.

			matrix = new JToggleButton[6][game.getColumn()]; 
			matrix = game.getMatrix(); 
			row = game.getRow();
			randomLength = game.getColumn();
			eq = game.getEquation(); 
			t=game.getTime();
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < game.getColumn(); j++) {
					gamePanel.add(matrix[i][j]); // eski oyun ekrana eklenir.
				}
			}
		}

		JToggleButton sonraBitir = new JToggleButton("Finish Later"); // "daha sonra bitir" butonu
		sonraBitir.setBackground(new Color(240, 240, 240));
		sonraBitir.setForeground(new Color(69, 151, 132));
		sonraBitir.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		sonraBitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StopWatch.stop();
				if (row != 0) { // herhangi bir oyuna baslanmis mi diye kontrol edilir.
					//istatistik dosyasina kayit yapilir.
					statistic = fileOp.read("statistics.txt");
					newStatistic = new Statistics(statistic.getAborted() + 1, statistic.getUnsuccessful(),
							statistic.getSuccessful(), statistic.getAverageNumberRows(), statistic.getAverageTime());
					fileOp.deleteFile("statistics.txt");
					fileOp.write("statistics.txt", newStatistic);
					
					//yarim kalan oyun dosyaya kaydedilir.
					game = new HalfGame(6, randomLength);
					game.setColumn(randomLength);
					game.setRow(row);
					game.setEquation(eq);
					int seconds = (int) StopWatch.getElapsedSeconds();
					game.setTime(seconds);
					
					game.setMatrix(matrix);
					try {
						fileOp.writeEquation("equation.txt", game);
					} catch (Exception x) {
						x.printStackTrace();
					}

				}

				MainScreen mainScreen = new MainScreen();
				mainScreen.setVisible(true);
				setVisible(false);
			}
		});
		sonraBitir.setFont(new Font("Century Gothic", Font.BOLD, 13));
		sonraBitir.setBounds(514, 11, 110, 30);
		contentPane.add(sonraBitir);

		timeLabel = new JLabel("");
		timeLabel.setForeground(new Color(69, 151, 132));
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
		timeLabel.setBounds(10, 11, 87, 31);
		contentPane.add(timeLabel);

		Timer myTimer = new Timer(); //ekranda surenin akmasini saglar.
		

		TimerTask myTask = new TimerTask() {
			int seconds = t;
			String sec;

			@SuppressWarnings("unused")
			public int getSeconds() {
				return seconds;
			}

			public void run() {
				seconds++;
				sec = Integer.toString(seconds);
				if (seconds < 60) {
					if (seconds < 10)
						timeLabel.setText("0:0" + sec);
					else
						timeLabel.setText("0:" + sec);
				}

				else {
					String spentTime;
					if (seconds % 60 < 10)
						spentTime = Integer.toString(seconds / 60) + ":0" + Integer.toString(seconds % 60);
					else
						spentTime = Integer.toString(seconds / 60) + ":" + Integer.toString(seconds % 60);
					timeLabel.setText(spentTime);
				}

			}
		};

		myTimer.schedule(myTask, 0, 1000);

		JPanel operatorPanel = new JPanel();
		operatorPanel.setBackground(new Color(216, 191, 216));
		operatorPanel.setBounds(66, 611, 281, 50);
		contentPane.add(operatorPanel);
		operatorPanel.setLayout(new GridLayout(0, 5, 10, 0));
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		// TUM ÝSLEM BUTONLARINDA AYNI SEYLER YAPÝLDÝGÝNDAN SADECE BIR BUTONUN ALTINA YORUM SATIRI EKLENDÝ!!//
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		

		JButton plusButton = new JButton("+");
		plusButton.setBackground(new Color(240, 240, 240));
		plusButton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		plusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				i = prevButton; //yazmaya kaldigi yerden devam edebilmesi icin son kaldigi yer i'ye atilir.
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;     // bos olan kutu bulunur.
				
				i = chooseButton(i);  //buton secildiginde islemleri yapan fonksiyon cagrilir.
				prevButton = i; //son kalinan yer tekrardan prevButton'a atilir.
				matrix[row][i].setText(plusButton.getText()); //butonun texti güncellenir.
				matrix[row][i].setSelected(false); //butondaki secim kaldirilir.
				if (column <= randomLength - 1) //dolu olan kutucuk sayisi artilir.
					column++;

			}
		});

		plusButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operatorPanel.add(plusButton);
   
		JButton minusButton = new JButton("-");
		minusButton.setBackground(new Color(240, 240, 240));
		minusButton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		minusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(minusButton.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		minusButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		operatorPanel.add(minusButton);

		JButton multButton = new JButton("*");
		multButton.setBackground(new Color(240, 240, 240));
		multButton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		multButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(multButton.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		multButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operatorPanel.add(multButton);

		JButton divButton = new JButton("/");
		divButton.setBackground(new Color(240, 240, 240));
		divButton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		divButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(divButton.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		divButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operatorPanel.add(divButton);

		JButton equalsButton = new JButton("=");
		equalsButton.setBackground(new Color(240, 240, 240));
		equalsButton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		equalsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(equalsButton.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		equalsButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operatorPanel.add(equalsButton);

		JPanel operandPanel = new JPanel();
		operandPanel.setBackground(new Color(216, 191, 216));
		operandPanel.setBounds(47, 550, 562, 50);
		contentPane.add(operandPanel);
		operandPanel.setLayout(new GridLayout(0, 10, 10, 10));

		JButton button1 = new JButton("1");
		button1.setBackground(new Color(240, 240, 240));
		button1.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(button1.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		button1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operandPanel.add(button1);

		JButton button2 = new JButton("2");
		button2.setBackground(new Color(240, 240, 240));
		button2.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(button2.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		button2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operandPanel.add(button2);

		JButton button3 = new JButton("3");
		button3.setBackground(new Color(240, 240, 240));
		button3.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(button3.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		button3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operandPanel.add(button3);

		JButton button4 = new JButton("4");
		button4.setBackground(new Color(240, 240, 240));
		button4.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(button4.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		button4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operandPanel.add(button4);

		JButton button5 = new JButton("5");
		button5.setBackground(new Color(240, 240, 240));
		button5.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(button5.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		button5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operandPanel.add(button5);

		JButton button7 = new JButton("7");
		button7.setBackground(new Color(240, 240, 240));
		button7.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(button7.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		
				JButton button6 = new JButton("6");
				button6.setBackground(new Color(240, 240, 240));
				button6.setBorder(new LineBorder(new Color(69, 151, 132), 2));
				button6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						i = prevButton;
						while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
							i++;

						i = chooseButton(i);
						prevButton = i;
						matrix[row][i].setText(button6.getText());
						matrix[row][i].setSelected(false);
						if (column <= randomLength - 1)
							column++;
					}
				});
				button6.setFont(new Font("Tahoma", Font.PLAIN, 15));
				operandPanel.add(button6);
		button7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operandPanel.add(button7);

		JButton button8 = new JButton("8");
		button8.setBackground(new Color(240, 240, 240));
		button8.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(button8.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		button8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operandPanel.add(button8);

		JButton button9 = new JButton("9");
		button9.setBackground(new Color(240, 240, 240));
		button9.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(button9.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		button9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operandPanel.add(button9);

		JButton button0 = new JButton("0");
		button0.setBackground(new Color(240, 240, 240));
		button0.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				i = prevButton;
				while (i < randomLength - 1 && matrix[row][i].getText().compareTo("") != 0)
					i++;

				i = chooseButton(i);
				prevButton = i;
				matrix[row][i].setText(button0.getText());
				matrix[row][i].setSelected(false);
				if (column <= randomLength - 1)
					column++;
			}
		});
		button0.setFont(new Font("Tahoma", Font.PLAIN, 15));
		operandPanel.add(button0);

		JPanel processPanel = new JPanel();
		processPanel.setBackground(new Color(221, 160, 221));
		processPanel.setBounds(371, 611, 198, 50);
		contentPane.add(processPanel);
		processPanel.setLayout(new GridLayout(0, 2, 10, 0));
  
		JButton enterButton = new JButton("Guess"); //TAHMÝN ETME BUTONU
		enterButton.setBackground(new Color(240, 240, 240));
		enterButton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (column == randomLength) { // tüm kutular doldurulmus mu diye kontrol eder.
					if (isEquationValid()) { 
						if(isEqualtoEquation()) {
							//girilen denklem kurallara uygun mu ve dogru bir denklem mi  kontrolleri yapýlýr. 
							if (control()) {
								//control fonksiyonu (kutu renklendirmelerinin yapýldýgý) cagrilir. True dondurduyse dogru tahmin yapilmis demektir
								StopWatch.stop();  // sayac durdurulur.
								int seconds = (int) StopWatch.getElapsedSeconds();
								//istatistikler dosyay kaydedilir.
								statistic = fileOp.read("statistics.txt"); 
								int rowNum = (((statistic.getSuccessful()) * (statistic.getAverageNumberRows()) + (row + 1))
										/ (statistic.getSuccessful() + 1));
								int time = ((statistic.getSuccessful()) * statistic.getAverageTime() + (int) seconds)
										/ (statistic.getSuccessful() + 1);
								newStatistic = new Statistics(statistic.getAborted(), statistic.getUnsuccessful(),
										(statistic.getSuccessful() + 1), rowNum, time);
								fileOp.deleteFile("statistics.txt");
								fileOp.deleteFile("equation.txt");
								fileOp.write("statistics.txt", newStatistic);

								int a, b;
								//tebrik ekraný acilir ve oyun süresi gonderilir.
								if (seconds > 60) {
									a = (int) (seconds / 60.0);
									b = (int) (seconds % 60.0);
									Congrat congrat = new Congrat(a, b);
									congrat.setVisible(true);
								} else {
									seconds = (int) StopWatch.getElapsedSeconds();
									Congrat congrat = new Congrat(0, seconds);
									congrat.setVisible(true);
								}
							} else if (row == 5) {
								// tum satirlar dolduysa ve dogru tahmin yapilamadiysa kullanicinimn kaybettigini gosteren ekran acilir.
								StopWatch.stop(); // sayac durdurulur.
								sadsc = new SadScreen(eq);
								//istatistikler dosyaya kaydedilir.
								statistic = fileOp.read("statistics.txt");
								newStatistic = new Statistics(statistic.getAborted(), statistic.getUnsuccessful() + 1,
										statistic.getSuccessful(), statistic.getAverageNumberRows(),
										statistic.getAverageTime());
								fileOp.deleteFile("statistics.txt");
								fileOp.write("statistics.txt", newStatistic);
								sadsc.setVisible(true);

							} else {
								//oyun devam ediyorsa alt satira gecilir.
								row++;
								for (int i = 0; i < randomLength; i++) {
									//bulundugumuz satirin enable ý kapatýlýp alt satirin acilir.
									matrix[row - 1][i].setEnabled(false);
									matrix[row][i].setEnabled(true);
								}
								column = 0;
								prevButton = 0;
							}
						} else
						JOptionPane.showMessageDialog(null, "That guess doesn't compute!");
					
					
				} 

				}else
					JOptionPane.showMessageDialog(null, "Your guess is not complete - please fill in the blanks.");
			}
		});
		enterButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		processPanel.add(enterButton);

		JButton deleteButton = new JButton("Delete"); //SILME BUTONU
		deleteButton.setBackground(new Color(240, 240, 240));
		deleteButton.setBorder(new LineBorder(new Color(69, 151, 132), 2));
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i = prevButton;
				i = chooseButton(i);
				if (i > 0) { 
					//ilk kutucuga gidilmediyse silinir.ilk kutucuga ulastiysak indisin negatife düsmemesi icin silme yapilmaz.
					matrix[row][i].setText("");
					if(matrix[row][i].isSelected())
						matrix[row][i].setSelected(false);
					i--;
					prevButton = i;
					column--;
				} else if (i == 0) {
					matrix[row][i].setText("");
					if(matrix[row][i].isSelected())
						matrix[row][i].setSelected(false);
					// column--;
					prevButton = i;
				}

			}
		});
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		processPanel.add(deleteButton);

		/*
		 * for(k = 0 ; k < 6; k++){ for(l = 0; l < randomLength ; l++){
		 * if(matrix[k][l].isSelected()){ y = l; flag = 1; }
		 * 
		 * } }
		 */

	}

	public int chooseButton(int i) {
		l = 0;
		while (l < randomLength - 1 && !matrix[row][l].isSelected())
			l++;

		if (l != randomLength - 1)
			i = l;

		else if (l == randomLength - 1 && matrix[row][l].isSelected())
			i = l;

		return i;
	}

	public String convertString() { //butonlardaki textleri stringe donusturur.
		String equation = "";
		for (int i = 0; i < randomLength; i++)
			equation += matrix[row][i].getText();

		return equation;
	}

	public String convertEquation(ArrayList<String> eq) { //arraylisti stringe donusturur.
		String equation = "";
		for (String s : eq) {
			equation += s;
		}
		return equation;
	}

	public boolean isEquationValid() { //denklem kurallara uygun mu diye kontrol edilir. 
		//icinde en az 1 islem bulunacak ve iki islem arka arkaya gelmeyecek.
		int count = 0;
		for (int i = 0; i < randomLength - 1; i++) {
			if (matrix[row][i].getText().equals("+") || matrix[row][i].getText().equals("-")
					|| matrix[row][i].getText().equals("*") || matrix[row][i].getText().equals("/")
					|| matrix[row][i].getText().equals("=")) {
				count++;
				if (count > 1)
					return false;
			} else {
				count = 0;
			}
		}

		return true;
	}

	public boolean isEqualtoEquation() { // girilen denklem dogry mu diye kontrol edlir
		String eq = convertString();
		int indeks = 0;
		String operation = "";
		String result = "";

		while (indeks < randomLength - 1 && eq.charAt(indeks) != '=') {
			operation += eq.charAt(indeks);
			indeks++;
		}
		if (indeks == randomLength - 1) {
			return false;
		} else {
			for (int i = indeks + 1; i < randomLength; i++) {
				result += eq.charAt(i);
			}
			if (String.valueOf(calculator.calculate(operation)).compareTo(result) == 0)
				return true;
			else
				return false;
		}

	}

	public boolean control() { //butonlari renklendirme
		int count = 0;
		int[] marked = new int[randomLength];
		for (int i = 0; i < randomLength; i++)
			marked[i] = -1;
		String equation = convertString();
		String answer = convertEquation(eq);
		answer += "=";
		answer += String.valueOf(calculator.calculate(answer));
		for (int i = 0; i <= randomLength - 1; i++) {

			if (answer.charAt(i) == equation.charAt(i)) { // eger yeri dogruysa yesile boyanir.
				marked[i] = 0;
				count++;
				matrix[row][i].setBackground(new Color(0, 204, 0));
				matrix[row][i].setForeground(new Color(0, 0, 0));
			}
		}

		if (count == randomLength) //tamami yesile boyandiysa oyunu tamamlamak icin true dondurulur.
			return true;

		for (int i = 0; i <= randomLength - 1; i++) { //denklemde varsa fakat yeri farklýysa sarýya boyanir.
			for (int j = 0; j < randomLength; j++) {
				if ((answer.charAt(i) == equation.charAt(j)) && marked[i] == -1) {
					if (!(matrix[row][j].getBackground().equals(new Color(0, 204, 0)))) {
						matrix[row][j].setBackground(new Color(255, 255, 51));
						marked[i] = 0;

					}

				}
			}
		}
		int k;

		for (int i = 0; i < randomLength; i++) { //hicbir renge boyanmayan butonlar kirmiziya boyanir.
			if (matrix[row][i].getBackground().equals(SystemColor.textInactiveText)) {
				matrix[row][i].setBackground(new Color(240, 0, 0));
				marked[i] = 0;
			}
		}

		return false;
	}
}
