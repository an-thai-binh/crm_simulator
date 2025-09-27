package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.CRMController;

public class CRMPanel extends JPanel {
	private JButton insert, deposit;
	private JPanel side;
	private MainPanel mainPanel;
	private JButton lb1, lb2, lb3, rb1, rb2, rb3; // cac button 2 ben man hinh
	private JPanel startScreen, pinScreen, infoScreen, withdrawScreen, depositScreen, transferScreen, invoiceScreen; // CardLayout panel
	private JPasswordField pinField; // thuoc tinh dung de check pin
	private JTextField moneyField, accField;
	private int count; // thuoc tinh dung de check pin
	private JLabel tLabel, nameLabel; // thuoc tinh dung de kiem tra so du, ho ten
	private CRMListener listener;	// listener
	private CRMController controller; // MVC
	public CRMPanel() {
		listener = new CRMListener(this);
		controller = new CRMController();
		
		this.count = 0;
		
		this.setLayout(new BorderLayout());
		this.addSide();
		this.addMain();
	}
	
	/**
	 * addSide: thanh ben phai
	 */
	private void addSide() {
		side = new JPanel();
		side.setBackground(Color.DARK_GRAY);
		side.setLayout(new GridLayout(2, 1, 50, 50));
		
		insert = new JButton("Insert");
		insert.addActionListener(listener);
		insert.setFocusPainted(false);
		insert.setBackground(Color.WHITE);

		deposit = new JButton("Cash Deposit");
		deposit.addActionListener(listener);
		deposit.setFocusPainted(false);
		deposit.setBackground(Color.WHITE);
		
		side.add(insert);
		side.add(deposit);

		this.add(side, BorderLayout.EAST);
	}
	
	/**
	 * addMain: khu vuc chinh
	 */
	private void addMain() {
		mainPanel = new MainPanel();
		this.add(mainPanel, BorderLayout.CENTER);

	}

	/* Feature Method Start */
	/**
	 * insertCard: dua the vao may
	 */
	public void insertCard() {
		JTextField unField = new JTextField();
		JTextField cardIDField = new JTextField();
		Object[] input = { "TEN CHU SO HUU", unField, "SO THE", cardIDField };
		int result = JOptionPane.showConfirmDialog(this, input, "CARD", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			if (controller.checkValidCard(unField.getText(), cardIDField.getText())) {
				if (controller.insertCard(unField.getText(), cardIDField.getText())) {
					if (controller.checkCardStatus())
						startCheckPIN();
					else
						JOptionPane.showConfirmDialog(this, "The nay da bi khoa", "ERROR", JOptionPane.WARNING_MESSAGE);
				} else
					JOptionPane.showConfirmDialog(this, "He thong dang ban xu ly 1 the khac", "ERROR", JOptionPane.WARNING_MESSAGE);
		} else
			JOptionPane.showMessageDialog(this, "Khong tim thay the", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Check PIN method start 
	/**
	 * startCheckPIN: bat dau check pin (chuyen giao dien)
	 */
	public void startCheckPIN() {
		// Create Check PIN Screen
		listener.setPinCheck(true);	// cho phep nhap ma pin
		pinScreen = new JPanel();
		pinScreen.setLayout(new GridLayout(3, 1));
		pinScreen.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));

		JLabel pinText = new JLabel("Vui long nhap ma PIN", JLabel.CENTER);
		
		pinText.setFont(new Font("Arial", 1, 25));
		
		pinField = new JPasswordField();
		pinField.setEditable(false); pinField.setBackground(Color.WHITE);
		pinField.setPreferredSize(new Dimension(0, 50));
		pinField.setFont(new Font(null, 0, 125));
		
		pinScreen.add(pinText);
		pinScreen.add(pinField);

		this.mainPanel.addCard("pin", pinScreen);
		this.mainPanel.showCard("pin");
	}
	
	/**
	 * insertPassword: nhap mat khau tu ban phim gia lap
	 */
	public void insertPINField(String text) {
		String pass = String.valueOf(pinField.getPassword());
		if(pass.length() <= 5)	// pin co do dai toi da 6 ky tu
			pinField.setText(pass + text);
	}
	
	/**
	 * clearPassword: xoa mat khau dang nhap
	 */
	public void clearPINField() {
		pinField.setText("");
	}
	
	/**
	 * cancelPINCheck: huy nhap pin (chuyen giao dien)
	 */
	public void cancelPINCheck() {
		controller.returnCard();	// tra card
		count = 0;	 // reset count
		mainPanel.showCard("start");
	}
	
	/**
	 * checkPIN: kiem tra ma PIN da nhap 
	 */
	public void checkPIN() {
		boolean check = controller.checkPIN(String.valueOf(pinField.getPassword()));
		if(!check) {	// neu ma pin sai
			count++;
			if(count == 5) {	// nhap sai 5 lan se khoa card
				JOptionPane.showMessageDialog(this, "Tai khoan da bi khoa do nhap sai qua so lan cho phep", "ERROR", JOptionPane.ERROR_MESSAGE);
				controller.lockCard();	// khoa card
				controller.returnCard();
				mainPanel.showCard("start");
			} else {	// thong bao so lan nhap con lai cho nguoi dung
				JOptionPane.showMessageDialog(this, "Mat khau sai ban con " + (5 - count) + " lan thu", "ERROR", JOptionPane.ERROR_MESSAGE);
				clearPINField();
			}
		} else {	// neu ma pin dung
			listener.setPinCheck(false);
			count = 0;	 // reset count
			startMainMenu();
		}
	}
	// Check PIN method end
	
	// Customer Console start
	/**
	 * startShowInfo: hien thi thong tin khach hang (chuyen giao dien)
	 */
	public void startMainMenu() {
		listener.setFeature(true);
		
		infoScreen = new JPanel();
		infoScreen.setLayout(new GridLayout(4, 1));
		infoScreen.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));
		
		Font infoFont = new Font("Arial", 1, 20);
		JLabel hLabel = new JLabel("Xin chao, khach hang ", JLabel.CENTER);
		hLabel.setFont(infoFont);
		JLabel uLabel = new JLabel(controller.getInfoUsername(), JLabel.CENTER);
		uLabel.setFont(infoFont);
		JLabel nLabel = new JLabel("So tai khoan: " + controller.getInfoCardID(), JLabel.CENTER);
		nLabel.setFont(infoFont);
		tLabel = new JLabel("Vui long chon giao dich", JLabel.CENTER);
		tLabel.setFont(infoFont);
	
		infoScreen.add(hLabel);
		infoScreen.add(uLabel);
		infoScreen.add(nLabel);
		infoScreen.add(tLabel);
		
		this.mainPanel.addCard("info", infoScreen);
		this.mainPanel.showCard("info");
		changeSideButton("info");
		
	}
	
	/**
	 * showCurrentBalance: hien thi so du hien tai
	 */
	public void showCurrentBalance() {
		tLabel.setText("So du hien tai: " + controller.getInfoCurrentBalance());
		
	}
	// Customer console end
	
	// Transaction method start
	/**
	 * startWithdrawMoney: bat dau rut tien (chuyen giao dien)
	 */
	public void startWithdrawMoney() {
		withdrawScreen = new JPanel();
		withdrawScreen.setLayout(new GridLayout(4, 1));
		withdrawScreen.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));
		JPanel none = new JPanel();
		
		JLabel hLabel = new JLabel("Nhap so tien ban muon rut", JLabel.CENTER);
		hLabel.setFont(new Font("Arial", 1, 20));
		
		moneyField = new JTextField();
		moneyField.setEditable(false);
		moneyField.setBackground(Color.WHITE);
		moneyField.setFont(new Font(null, 0, 50));
		
		withdrawScreen.add(none);
		withdrawScreen.add(hLabel);
		withdrawScreen.add(moneyField);
		
		changeSideButton("transaction");
		lb2.setActionCommand("withdraw");
		this.mainPanel.addCard("withdraw", withdrawScreen);
		this.mainPanel.showCard("withdraw");
		
	}
	
	public void withdrawMoney() {
		try {
			double money = Double.valueOf(moneyField.getText());
			if(controller.checkTransactionMoney(money)) {
				controller.withdrawMoney(money);
				startPrintInvoice();
			} else
				JOptionPane.showMessageDialog(this, "Quy khach khong du so du de thuc hien giao dich", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "So tien khong hop le", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * startDepositMoney: bat dau nop tien (chuyen giao dien)
	 */
	public void startDepositMoney() {
		listener.setDeposit(true);
		
		depositScreen = new JPanel();
		depositScreen.setLayout(new GridLayout(4, 1));
		depositScreen.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));
		JPanel none = new JPanel();
		
		JLabel n1Label = new JLabel("Dat tien vao khe", JLabel.CENTER);
		JLabel n2Label = new JLabel("de nop tien vao tai khoan", JLabel.CENTER);
		JLabel n3Label = new JLabel("Da nhan:", JLabel.CENTER);
		n1Label.setFont(new Font("Arial", 1, 20));
		n2Label.setFont(new Font("Arial", 1, 20));
		n3Label.setFont(new Font("Arial", 1, 17));
		
		moneyField = new JTextField();
		moneyField.setEditable(false);
		moneyField.setBackground(Color.WHITE);
		moneyField.setFont(new Font(null, 0, 50));
		
		depositScreen.add(n1Label);
		depositScreen.add(n2Label);
		depositScreen.add(n3Label);
		depositScreen.add(moneyField);
		
		changeSideButton("transaction");
		lb2.setActionCommand("deposit");
		this.mainPanel.addCard("deposit", depositScreen);
		this.mainPanel.showCard("deposit");
	}
	
	/**
	 * deposit: nhan tien tu ben ngoai
	 */
	public void deposit() {
		if(listener.getDeposit() == true) {
			JLabel l1 = new JLabel("So tien:");
			JTextField t1 = new JTextField();
			Object[] input = { l1, t1 };
			int result = JOptionPane.showConfirmDialog(this, input, "Dua tien vao", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				insertMoneyField(t1.getText());
				listener.setDeposit(false);
			}
		}
	}
	
	/**
	 * depositMoney: nop tien vao tai khoan
	 */
	public void depositMoney() {
		try {
			double money = Double.valueOf(moneyField.getText());
			controller.depositMoney(money);
			startPrintInvoice();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "So tien khong hop le", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * startTransferMoney: bat dau chuyen tien (chuyen giao dien)
	 */
	public void startTransferMoney() {
		listener.setFillAcc(true);
		
		transferScreen = new JPanel();
		transferScreen.setLayout(new GridLayout(5, 1));
		transferScreen.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));
		
		JLabel n1Label = new JLabel("Nhap so tai khoan nhan", JLabel.CENTER);
		
		accField = new JTextField();
		accField.setEditable(false);
		accField.setBackground(Color.WHITE);
		accField.setFont(new Font(null, 0, 50));
		
		nameLabel = new JLabel("", JLabel.CENTER);
		JLabel n2Label = new JLabel("Nhap so tien chuyen", JLabel.CENTER);
		
		n1Label.setFont(new Font("Arial", 1, 20));
		n2Label.setFont(new Font("Arial", 1, 20));
		nameLabel.setFont(new Font("Arial", 1, 15));
		
		moneyField = new JTextField();
		moneyField.setEditable(false);
		moneyField.setBackground(Color.WHITE);
		moneyField.setFont(new Font(null, 0, 50));
		
		transferScreen.add(n1Label);
		transferScreen.add(accField);
		transferScreen.add(nameLabel);
		transferScreen.add(n2Label);
		transferScreen.add(moneyField);
		
		changeSideButton("transaction");
		lb2.setActionCommand("transfer");
		this.mainPanel.addCard("transfer", transferScreen);
		this.mainPanel.showCard("transfer");
	}
	
	/**
	 * transferMoney: chuyen tien
	 */
	public void transferMoney() {
		try {
			double money = Double.valueOf(moneyField.getText());
			String account = String.valueOf(accField.getText());
			if(controller.checkTransactionMoney(money)) {
				controller.transferMoney(account, money);
				startPrintInvoice();
			} else
				JOptionPane.showMessageDialog(this, "Quy khach khong du so du de thuc hien giao dich", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "So tien khong hop le", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * insertMoney: nhap tien tu ban phim gia lap
	 */
	public void insertMoneyField(String text) {
		String money = String.valueOf(moneyField.getText());
		if(money.length() <= 6) // chi rut va nop toi da hang trieu
			moneyField.setText(money + text);
	}
	
	/**
	 * clearMoney: xoa tien dang nhap
	 */
	public void clearMoneyField() {
		moneyField.setText("");
	}
	
	/**
	 * insertAccField: nhap so tai khoan tu ban phim gia lap
	 * @param text
	 */
	public void insertAccField(String text) {
		String num = String.valueOf(accField.getText());
		accField.setText(num + text);
	}
	
	/**
	 * clearAccField: xoa tai khoan dang nhap
	 */
	public void clearAccField() {
		accField.setText("");
	}
	
	/**
	 * checkAccField: kiem tra xem tai khoan vua nhap co ton tai khong, neu co hien thi ten tai khoan
	 */
	public void checkAccField() {
		String name = controller.getNameByAccount(accField.getText());
		nameLabel.setText(name);
		if(nameLabel.getText().equals("Khong tim duoc ten!"))
			clearAccField();
		else
			listener.setFillAcc(false);
	}
	
	public void startPrintInvoice() {
		invoiceScreen = new JPanel();
		invoiceScreen.setLayout(new GridLayout(4, 1));
		invoiceScreen.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));
		
		Font f = new Font("Arial", 1, 19);
		JPanel none = new JPanel();	
		JLabel transDone = new JLabel("Giao dich thanh cong", JLabel.CENTER);
		transDone.setFont(f);
		JLabel askInvoice = new JLabel("Quy khach co muon in hoa don?", JLabel.CENTER);
		askInvoice.setFont(f);
		
		invoiceScreen.add(none);
		invoiceScreen.add(transDone);
		invoiceScreen.add(askInvoice);
		
		changeSideButton("invoice");
		this.mainPanel.addCard("invoice", invoiceScreen);
		this.mainPanel.showCard("invoice");
		
	}
	
	public void printInvoice() {
		JOptionPane.showMessageDialog(this, controller.printInvoice(), "INVOICE", JOptionPane.INFORMATION_MESSAGE);
	}
	// Transaction method end 
	
	public void changeSideButton(String type) {
		if(type.equals("default")) {
			lb1.setText("");	lb2.setText(""); lb3.setText("");
			rb1.setText(""); rb2.setText(""); rb3.setText("");
		}
		if(type.equals("info")) {
			lb2.setText("<html>Kiem<br/> tra<br/>so du<html>");	// su dung code html de can chinh chu
			lb2.setActionCommand("kiemtrasodu");
			
			rb1.setText("<html>Rut<br/>tien</html>");
			rb1.setActionCommand("ruttien");
			
			rb2.setText("<html>Chuyen<br/>khoan</html>");
			rb2.setActionCommand("chuyenkhoan");
			
			rb3.setText("<html>Nop<br/>tien</html>");
			rb3.setActionCommand("noptien");
			
		}
		if(type.equals("transaction")) {
			lb2.setText("Dong y");
			rb1.setText(""); rb2.setText("Huy"); rb3.setText(""); 
			rb2.setActionCommand("huygiaodich");
		}
		if(type.equals("invoice")) {
			lb2.setText("Co");
			lb2.setActionCommand("print");
			rb1.setText(""); rb2.setText("Khong"); rb3.setText(""); 
			rb2.setActionCommand("Cancel");
		}
	}
	
	public void returnToMainMenu() {
		startMainMenu();
		changeSideButton("info");
	}
	
	public void returnToDefault() {
		listener.setFeature(false);
		controller.returnCard();
		mainPanel.showCard("start");
		changeSideButton("default");
	}
	
	/* Feature Method End */
	
	/* MainPanel Class Start */
	public class MainPanel extends JPanel {
		private JPanel frame, bottom, screen;
		private CardLayout cards;

		public MainPanel() {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			bottom = new JPanel();
			bottom.setLayout(new FlowLayout(FlowLayout.CENTER));
			this.setupFrame();
			this.setupBottom();
		}
		
		/**
		 * setupFrame: cai dat khung
		 */
		private void setupFrame() {
			frame = new JPanel();
			frame.setLayout(new BorderLayout());
			// LEFT SIDE
			JPanel leftSide = new JPanel();
			leftSide.setLayout(new GridLayout(3, 1, 0, 50));

			leftSide.setBackground(Color.GRAY);

			lb1 = new JButton(); lb1.addActionListener(listener);
			lb2 = new JButton(); lb2.addActionListener(listener);
			lb3 = new JButton(); lb3.addActionListener(listener);

			lb1.setPreferredSize(new Dimension(75, 75));
			
			leftSide.add(lb1);
			leftSide.add(lb2);
			leftSide.add(lb3);

			frame.add(leftSide, BorderLayout.WEST);
			// RIGHT SIDE
			JPanel rightSide = new JPanel();
			rightSide.setLayout(new GridLayout(3, 1, 0, 50));

			rightSide.setBackground(Color.GRAY);

			rb1 = new JButton(); rb1.addActionListener(listener);
			rb2 = new JButton(); rb2.addActionListener(listener);
			rb3 = new JButton(); rb3.addActionListener(listener);
			
			rb1.setPreferredSize(new Dimension(75, 75));
			
			rightSide.add(rb1);
			rightSide.add(rb2);
			rightSide.add(rb3);

			frame.add(rightSide, BorderLayout.EAST);
			// SCREEN
			screen = new JPanel();
			cards = new CardLayout();
			screen.setLayout(cards);

			this.setupCardLayout();

			this.add(frame);
		}
		
		/**
		 * setupCardLayout: cai dat CardLayout
		 */
		private void setupCardLayout() {
			/* Start screen */
			startScreen = new JPanel();
			startScreen.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3, true));
			startScreen.setLayout(new GridLayout(5, 1));
			
			JPanel none = new JPanel();
		
			JLabel hello = new JLabel("XIN KINH CHAO QUY KHACH", JLabel.CENTER);
			JLabel please = new JLabel("Vui long nhap the de su dung dich vu", JLabel.CENTER);
			hello.setFont(new Font("Arial", 1, 15));
			please.setFont(new Font("Arial", 1, 15));
			
			startScreen.add(none);
			startScreen.add(hello);
			startScreen.add(please);
			this.addCard("start", startScreen);

			this.showCard("start");

			frame.add(screen, BorderLayout.CENTER);
		}

		// add cardlayout
		public void addCard(String name, JPanel pane) {
			screen.add(name, pane);
		}

		// show cardlayout
		public void showCard(String name) {
			cards.show(screen, name);
		}
		
		/**
		 * setupBottom: cai dat phan duoi (ban phim)
		 */
		private void setupBottom() {
			bottom = new JPanel();
			bottom.setLayout(new FlowLayout(FlowLayout.CENTER));

			bottom.setBackground(Color.GRAY);
			// NUMPAD
			JPanel numPad = new JPanel();
			numPad.setLayout(new GridLayout(4, 4, 5, 5));

			numPad.setBackground(Color.GRAY);
			JButton p1 = new JButton("1"); JButton p2 = new JButton("2"); JButton p3 = new JButton("3"); JButton pCancel = new JButton("Cancel");
			JButton p4 = new JButton("4"); JButton p5 = new JButton("5"); JButton p6 = new JButton("6"); JButton pClear = new JButton("Clear");
			JButton p7 = new JButton("7"); JButton p8 = new JButton("8"); JButton p9 = new JButton("9"); JButton pEnter = new JButton("Enter");
			JButton pSt = new JButton("*"); JButton p0 = new JButton("0"); JButton pSh = new JButton("#"); JButton pNull = new JButton("");
			
			p1.addActionListener(listener); p2.addActionListener(listener); p3.addActionListener(listener);	pCancel.addActionListener(listener);
			p4.addActionListener(listener); p5.addActionListener(listener); p6.addActionListener(listener); pClear.addActionListener(listener);
			p7.addActionListener(listener); p8.addActionListener(listener); p9.addActionListener(listener);	pEnter.addActionListener(listener);
			p0.addActionListener(listener);
			
			pCancel.setBackground(Color.RED);
			pClear.setBackground(Color.YELLOW);
			pEnter.setBackground(Color.GREEN);

			numPad.add(p1); numPad.add(p2); numPad.add(p3); numPad.add(pCancel);
			numPad.add(p4); numPad.add(p5); numPad.add(p6); numPad.add(pClear);
			numPad.add(p7); numPad.add(p8); numPad.add(p9); numPad.add(pEnter);
			numPad.add(pSt); numPad.add(p0); numPad.add(pSh); numPad.add(pNull);

			bottom.add(numPad);

			this.add(bottom);
		}
	}
	/* MainPanel Class End */

}
