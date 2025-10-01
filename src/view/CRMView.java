package view;

import utils.Formatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CRMView extends JFrame implements ICRMView {
    private CRMContentPane contentPane;
    public CRMView() {
        this.setTitle("Cash Recycling Machine");
        this.setIconImage(new ImageIcon(CRMView.class.getResource("/images/cashicon.png")).getImage());
        this.setSize(650, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUIDefault();
    }

    /**
     * điều chỉnh một số thuộc tính mặc định của giao diện
     */
    private void setUIDefault() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ToolTipManager.sharedInstance().setInitialDelay(100);	// tool tip text hiển thị nhanh hơn
    }

    @Override
    public void display(ActionListener listener) {
        contentPane = new CRMContentPane(listener);
        this.setContentPane(contentPane);
        this.setVisible(true);
    }

    @Override
    public JPanel getContentPane() {
        return this.contentPane;
    }

    @Override
    public void renderGreetingScreen() {
        this.contentPane.renderGreetingScreen();
        this.contentPane.changeScreenButtonType("NONE");
        this.contentPane.changeKeyboardType("NONE");
    }

    @Override
    public void renderInsertPinScreen(JPasswordField pinField) {
        this.contentPane.renderInsertPinScreen(pinField);
        this.contentPane.changeKeyboardType("PIN");
    }

    @Override
    public void renderMainMenuScreen(String username, String cardId, double currentBalance) {
        this.contentPane.renderMainMenuScreen(username, cardId, currentBalance);
        this.contentPane.changeScreenButtonType("MAIN_MENU");
        this.contentPane.changeKeyboardType("MAIN_MENU");
    }

    @Override
    public void renderWithdrawScreen(JTextField withdrawAmountField) {
        this.contentPane.renderWithdrawScreen(withdrawAmountField);
        this.contentPane.changeScreenButtonType("WITHDRAW");
        this.contentPane.changeKeyboardType("WITHDRAW");
    }

    @Override
    public void renderDepositScreen(JTextField depositAmountField) {
        this.contentPane.renderDepositScreen(depositAmountField);
        this.contentPane.changeScreenButtonType("DEPOSIT");
        this.contentPane.changeKeyboardType("DEPOSIT");
    }

    @Override
    public void renderTransferScreen(JTextField transferAccountField, String accountName, JTextField transferAmountField, boolean isFillInAccount) {
        this.contentPane.renderTransferScreen(transferAccountField, accountName, transferAmountField);
        if(isFillInAccount) {
            this.contentPane.changeScreenButtonType("TRANSFER_ACCOUNT");
            this.contentPane.changeKeyboardType("TRANSFER_ACCOUNT");
        } else {
            this.contentPane.changeScreenButtonType("TRANSFER_AMOUNT");
            this.contentPane.changeKeyboardType("TRANSFER_AMOUNT");
        }
    }

    @Override
    public void renderAskForInvoiceScreen() {
        this.contentPane.renderAskForInvoiceScreen();
        this.contentPane.changeScreenButtonType("INVOICE");
        this.contentPane.changeKeyboardType("NONE");
    }

    @Override
    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this.getContentPane(), message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showInvoiceDialog(String invoice) {
        JOptionPane.showMessageDialog(this.getContentPane(), invoice, "INVOICE", JOptionPane.INFORMATION_MESSAGE);
    }

    class CRMContentPane extends JPanel {
        private Formatter formatter;
        private ActionListener listener;
        private JPanel screenPane;
        private JButton insertCardBtn, insertMoneyBtn;
        // screen btn
        private JButton topLeftBtn, botLeftBtn, topRightBtn, botRightBtn;
        // keyboard btn
        private JButton n0Btn, n1Btn, n2Btn, n3Btn, n4Btn, n5Btn, n6Btn, n7Btn, n8Btn, n9Btn, cancelBtn, enterBtn, clearBtn;
        public CRMContentPane(ActionListener listener) {
            this.formatter = new Formatter();
            this.listener = listener;
            screenPane = new JPanel();
            this.setLayout(new BorderLayout());
            renderUI();
        }

        /**
         * render giao diện chính
         */
        private void renderUI() {
            renderCenter();
            renderEastSide();
        }

        /**
         * render giao diện khu vực trung tâm
         */
        private void renderCenter() {
            JPanel centerPane = new JPanel(new GridLayout(2, 1));
            centerPane.setBackground(Color.BLUE);
            
            renderScreenSide(centerPane);
            
            renderKeyboardSide(centerPane);

            this.add(centerPane, BorderLayout.CENTER);
        }

        /**
         * render giao diện khu vực trung tâm - phần màn hình
         * @param centerPane JPanel của khu vực trung tâm
         */
        private void renderScreenSide(JPanel centerPane) {
            JPanel mainPane = new JPanel(new BorderLayout());

            JPanel leftBtnPane = new JPanel(new GridLayout(2, 1, 0, 50));
            leftBtnPane.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
            leftBtnPane.setBackground(Color.decode("#24bf65"));
            leftBtnPane.setPreferredSize(new Dimension(75, 0));
            topLeftBtn = new JButton();
            topLeftBtn.addActionListener(listener);
            botLeftBtn = new JButton();
            botLeftBtn.addActionListener(listener);
            leftBtnPane.add(topLeftBtn);
            leftBtnPane.add(botLeftBtn);

            JPanel rightBtnPane = new JPanel(new GridLayout(2, 1, 0, 50));
            rightBtnPane.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
            rightBtnPane.setBackground(Color.decode("#24bf65"));
            rightBtnPane.setPreferredSize(new Dimension(75, 0));
            topRightBtn = new JButton();
            topRightBtn.addActionListener(listener);
            botRightBtn = new JButton();
            botRightBtn.addActionListener(listener);
            rightBtnPane.add(topRightBtn);
            rightBtnPane.add(botRightBtn);

            mainPane.add(leftBtnPane, BorderLayout.WEST);
            renderGreetingScreen();
            mainPane.add(screenPane, BorderLayout.CENTER);
            mainPane.add(rightBtnPane, BorderLayout.EAST);

            centerPane.add(mainPane);
        }

        /**
         * render giao diện khu vực trung tâm - phần bàn phím
         * @param centerPane JPanel của khu vực trung tâm
         */
        private void renderKeyboardSide(JPanel centerPane) {
            JPanel keyboardPane = new JPanel(new GridLayout(4, 4, 10, 10));
            keyboardPane.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
            keyboardPane.setBackground(Color.decode("#39eb83"));

            n1Btn = new JButton("1"); n2Btn = new JButton("2"); n3Btn = new JButton("3"); cancelBtn = new JButton("Cancel");
            n4Btn = new JButton("4"); n5Btn = new JButton("5"); n6Btn = new JButton("6"); enterBtn = new JButton("Enter");
            n7Btn = new JButton("7"); n8Btn = new JButton("8"); n9Btn = new JButton("9"); clearBtn = new JButton("Clear");
            n0Btn = new JButton("0");

            Font keyboardFont = new Font(null, Font.BOLD, 20);
            n1Btn.setFont(keyboardFont); n2Btn.setFont(keyboardFont); n3Btn.setFont(keyboardFont); cancelBtn.setFont(keyboardFont);
            n4Btn.setFont(keyboardFont); n5Btn.setFont(keyboardFont); n6Btn.setFont(keyboardFont); enterBtn.setFont(keyboardFont);
            n7Btn.setFont(keyboardFont); n8Btn.setFont(keyboardFont); n9Btn.setFont(keyboardFont); clearBtn.setFont(keyboardFont);
            n0Btn.setFont(keyboardFont);

            n1Btn.addActionListener(listener); n2Btn.addActionListener(listener); n3Btn.addActionListener(listener); cancelBtn.addActionListener(listener);
            n4Btn.addActionListener(listener); n5Btn.addActionListener(listener); n6Btn.addActionListener(listener); enterBtn.addActionListener(listener);
            n7Btn.addActionListener(listener); n8Btn.addActionListener(listener); n9Btn.addActionListener(listener); clearBtn.addActionListener(listener);
            n0Btn.addActionListener(listener);

            cancelBtn.setBackground(Color.RED);
            enterBtn.setBackground(Color.GREEN);
            clearBtn.setBackground(Color.YELLOW);

            keyboardPane.add(n1Btn); keyboardPane.add(n2Btn); keyboardPane.add(n3Btn); keyboardPane.add(cancelBtn);
            keyboardPane.add(n4Btn); keyboardPane.add(n5Btn); keyboardPane.add(n6Btn); keyboardPane.add(enterBtn);
            keyboardPane.add(n7Btn); keyboardPane.add(n8Btn); keyboardPane.add(n9Btn); keyboardPane.add(clearBtn);
            keyboardPane.add(new JButton()); keyboardPane.add(n0Btn); keyboardPane.add(new JButton()); keyboardPane.add(new JButton());

            centerPane.add(keyboardPane);
        }

        /**
         * render giao diện khu vực lề phải
         */
        private void renderEastSide() {
            JPanel eastPane = new JPanel(new GridLayout(2, 1, 0, 100));
            eastPane.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
            eastPane.setBackground(Color.decode("#39eb83"));

            insertCardBtn = new JButton("<html><p style='text-align:center; font-size: 14px;'><b>INSERT<br/>CARD</b></p></html>");
            insertCardBtn.addActionListener(listener);
            insertCardBtn.setActionCommand("INSERT_CARD");
            insertCardBtn.setBackground(Color.WHITE);
            eastPane.add(insertCardBtn);

            insertMoneyBtn = new JButton("<html><p style='text-align:center; font-size: 14px;'><b>INSERT<br/>MONEY</b></p></html>");
            insertMoneyBtn.addActionListener(listener);
            insertMoneyBtn.setActionCommand("INSERT_MONEY");
            insertMoneyBtn.setBackground(Color.WHITE);
            eastPane.add(insertMoneyBtn);

            this.add(eastPane, BorderLayout.EAST);
        }

        // START - RENDER CÁC SCREEN CHO MÀN HÌNH (screenPane)

        /**
         * render màn hình lời chào
         */
        public void renderGreetingScreen() {
            screenPane.removeAll();
            screenPane.setLayout(new BorderLayout());
            screenPane.setBackground(Color.WHITE);
            JLabel greeting = new JLabel("<html>" +
                    "<p style='text-align: center; font-size: 20px;'>" +
                    "<b>Vui lòng nhập thẻ<br/>để sử dụng dịch vụ</b>" +
                    "</p></html>");
            greeting.setHorizontalAlignment(SwingConstants.CENTER);
            greeting.setVerticalAlignment(SwingConstants.CENTER);
            screenPane.add(greeting, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }

        /**
         * render màn hình nhập mã Pin
         * @param pinField JPasswordField quản lý mã pin
         */
        public void renderInsertPinScreen(JPasswordField pinField) {
            screenPane.removeAll();
            screenPane.setLayout(new GridLayout(3, 1));
            screenPane.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
            JLabel pinLabel = new JLabel("<html><p style='font-size: 20px'><b>Nhập mã PIN</b></p></html>");
            pinLabel.setHorizontalAlignment(SwingConstants.CENTER);
            screenPane.add(pinLabel);
            pinField.setHorizontalAlignment(SwingConstants.CENTER);
            pinField.setFont(new Font(null, Font.BOLD, 40));
            pinField.setEditable(false);
            screenPane.add(pinField);
            screenPane.add(new JLabel());
            this.revalidate();
            this.repaint();
        }

        /**
         * render trang chủ
         * @param username tên tài khoản
         * @param cardId số tài khoản
         */
        public void renderMainMenuScreen(String username, String cardId, double currentBalance) {
            screenPane.removeAll();
            screenPane.setLayout(new GridLayout(4, 1));
            JLabel welcomeLabel = new JLabel("<html><p style='font-size: 16px'><b>Xin chào khách hàng</b></p></html>");
            welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel usernameLabel = new JLabel("<html><p style='font-size: 16px'><b>" + username + "</b></p></html>");
            usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel cardIdLabel = new JLabel("<html><p style='font-size: 16px'><b>Số tài khoản: " + cardId + "</b></p></html>");
            cardIdLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel guideLabel = new JLabel("<html><p style='font-size: 16px'><b>Vui lòng chọn giao dịch</b></p></html>");
            guideLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel currentBalanceLabel = new JLabel("<html><p style='text-align:center; font-size: 16px'><b>Số dư hiện tại: <br/>"+ formatter.formatMoney(currentBalance) +"</b></p></html>");
            currentBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            screenPane.add(welcomeLabel);
            screenPane.add(usernameLabel);
            screenPane.add(cardIdLabel);
            if(currentBalance < 0) {
                screenPane.add(guideLabel);
            } else {
                screenPane.add(currentBalanceLabel);
            }
            this.revalidate();
            this.repaint();
        }

        /**
         * render trang rút tiền
         * @param withdrawAmountField JTextField quản lý số tiền rút
         */
        public void renderWithdrawScreen(JTextField withdrawAmountField) {
            screenPane.removeAll();
            screenPane.setLayout(new GridLayout(3, 1));
            screenPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            JLabel amountLabel = new JLabel("<html><p style='font-size: 20px'><b>Nhập số tiền cần rút</b></p></html>");
            amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
            screenPane.add(amountLabel);
            withdrawAmountField.setHorizontalAlignment(SwingConstants.CENTER);
            withdrawAmountField.setFont(new Font(null, Font.BOLD, 30));
            withdrawAmountField.setEditable(false);
            screenPane.add(withdrawAmountField);
            screenPane.add(new JLabel());
            this.revalidate();
            this.repaint();
        }

        /**
         * render trang nộp tiền
         * @param depositAmountField JTextField quản lý số tiền nộp
         */
        public void renderDepositScreen(JTextField depositAmountField) {
            screenPane.removeAll();
            screenPane.setLayout(new GridLayout(3, 1));

            JLabel guideLabel = new JLabel("<html><p style='text-align:center;font-size: 14px;'><b>Đặt tiền vào khe<br/>để nộp tiền vào tài khoản</b></p></html>");
            guideLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel receivedLabel = new JLabel("<html><p style='text-align:center;font-size: 16px;'><b>Đã nhận:</b></p></html>");
            receivedLabel.setHorizontalAlignment(SwingConstants.CENTER);

            depositAmountField.setHorizontalAlignment(SwingConstants.CENTER);
            depositAmountField.setFont(new Font(null, Font.BOLD, 20));
            depositAmountField.setEditable(false);

            screenPane.add(guideLabel);
            screenPane.add(receivedLabel);
            screenPane.add(depositAmountField);
            this.revalidate();
            this.repaint();
        }

        /**
         * render trang chuyển khoản
         * @param transferAccountField
         * @param accountName
         * @param transferAmountField
         */
        public void renderTransferScreen(JTextField transferAccountField, String accountName, JTextField transferAmountField) {
            screenPane.removeAll();
            screenPane.setLayout(new GridLayout(5, 1));
            screenPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            Font fieldFont = new Font(null, Font.BOLD, 18);

            JLabel guide1Label = new JLabel("<html><p style='text-align: center; font-size: 15px;'><b>Nhập số tài khoản người nhận</b></p></html>");
            guide1Label.setHorizontalAlignment(SwingConstants.CENTER);

            transferAccountField.setHorizontalAlignment(SwingConstants.CENTER);
            transferAccountField.setFont(fieldFont);
            transferAccountField.setEditable(false);

            JLabel accountNameLabel = new JLabel(accountName != null ?
                    "<html><p style='text-align: center; font-size: 15px;'>" + accountName + "</p></html>" :
                    "");
            accountNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel guide2Label = new JLabel("<html><p style='text-align: center; font-size: 15px;'><b>Nhập số tiền muốn chuyển</b></p></html>");
            guide2Label.setHorizontalAlignment(SwingConstants.CENTER);

            transferAmountField.setHorizontalAlignment(SwingConstants.CENTER);
            transferAmountField.setFont(fieldFont);
            transferAmountField.setEditable(false);

            screenPane.add(guide1Label);
            screenPane.add(transferAccountField);
            screenPane.add(accountNameLabel);
            screenPane.add(guide2Label);
            screenPane.add(transferAmountField);
            this.revalidate();
            this.repaint();
        }

        /**
         * render trang hỏi in hóa đơn
         */
        public void renderAskForInvoiceScreen() {
            screenPane.removeAll();
            screenPane.setLayout(new GridLayout(3, 1));
            screenPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            JLabel successLabel = new JLabel("<html><p style='font-size: 20px'><b>Giao dịch thành công</b></p></html>");
            successLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel askInvoiceLabel = new JLabel("<html><p style='font-size: 16px'><b>Quý khách có muốn in hóa đơn?</b></p></html>");
            askInvoiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            screenPane.add(successLabel);
            screenPane.add(askInvoiceLabel);
            screenPane.add(new JLabel());
            this.revalidate();
            this.repaint();
        }
        /* END */

        /**
         * chuyển đổi giao diện và action command cho các nút màn hình
         * @param type loại command
         */
        public void changeScreenButtonType(String type) {
            switch (type) {
                case "NONE": {
                    topLeftBtn.setText("");
                    topLeftBtn.setActionCommand("");
                    botLeftBtn.setText("");
                    botLeftBtn.setActionCommand("");
                    topRightBtn.setText("");
                    topRightBtn.setActionCommand("");
                    botRightBtn.setText("");
                    botRightBtn.setActionCommand("");
                    break;
                }
                case "MAIN_MENU": {
                    topLeftBtn.setText("<html><p style='text-align:center'>Kiểm tra<br/>số dư</p></html>");
                    topLeftBtn.setActionCommand("CHECK_BALANCE");
                    botLeftBtn.setText("<html><p style='text-align:center'>Nộp<br/>tiền</p></html>");
                    botLeftBtn.setActionCommand("DEPOSIT");
                    topRightBtn.setText("<html><p style='text-align:center'>Rút<br/>tiền</p></html>");
                    topRightBtn.setActionCommand("WITHDRAW");
                    botRightBtn.setText("<html><p style='text-align:center'>Chuyển<br/>khoản</p></html>");
                    botRightBtn.setActionCommand("TRANSFER");
                    break;
                }
                case "WITHDRAW": {
                    topLeftBtn.setText("<html><p style='text-align:center'>Đồng ý</p></html>");
                    topLeftBtn.setActionCommand("WITHDRAW_INPUT ENTER");
                    botLeftBtn.setText("");
                    botLeftBtn.setActionCommand("");
                    topRightBtn.setText("<html><p style='text-align:center'>Hủy</p></html>");
                    topRightBtn.setActionCommand("WITHDRAW_INPUT CANCEL");
                    botRightBtn.setText("");
                    botRightBtn.setActionCommand("");
                    break;
                }
                case "DEPOSIT": {
                    topLeftBtn.setText("<html><p style='text-align:center'>Đồng ý</p></html>");
                    topLeftBtn.setActionCommand("DEPOSIT_INPUT ENTER");
                    botLeftBtn.setText("");
                    botLeftBtn.setActionCommand("");
                    topRightBtn.setText("<html><p style='text-align:center'>Hủy</p></html>");
                    topRightBtn.setActionCommand("DEPOSIT_INPUT CANCEL");
                    botRightBtn.setText("");
                    botRightBtn.setActionCommand("");
                    break;
                }
                case "TRANSFER_ACCOUNT":{
                    topLeftBtn.setText("<html><p style='text-align:center'>Đồng ý</p></html>");
                    topLeftBtn.setActionCommand("TRANSFER_ACCOUNT_INPUT ENTER");
                    botLeftBtn.setText("");
                    botLeftBtn.setActionCommand("");
                    topRightBtn.setText("<html><p style='text-align:center'>Hủy</p></html>");
                    topRightBtn.setActionCommand("TRANSFER_ACCOUNT_INPUT CANCEL");
                    botRightBtn.setText("");
                    botRightBtn.setActionCommand("");
                    break;
                }
                case "TRANSFER_AMOUNT": {
                    topLeftBtn.setText("<html><p style='text-align:center'>Đồng ý</p></html>");
                    topLeftBtn.setActionCommand("TRANSFER_AMOUNT_INPUT ENTER");
                    botLeftBtn.setText("");
                    botLeftBtn.setActionCommand("");
                    topRightBtn.setText("<html><p style='text-align:center'>Hủy</p></html>");
                    topRightBtn.setActionCommand("TRANSFER_AMOUNT_INPUT CANCEL");
                    botRightBtn.setText("");
                    botRightBtn.setActionCommand("");
                    break;
                }
                case "INVOICE": {
                    topLeftBtn.setText("<html><p style='text-align:center'>Có</p></html>");
                    topLeftBtn.setActionCommand("INVOICE YES");
                    botLeftBtn.setText("");
                    botLeftBtn.setActionCommand("");
                    topRightBtn.setText("<html><p style='text-align:center'>Không</p></html>");
                    topRightBtn.setActionCommand("INVOICE NO");
                    botRightBtn.setText("");
                    botRightBtn.setActionCommand("");
                    break;
                }
                default: {
                    System.out.println("Screen button type not found");
                }
            }
        }

        /**
         * chuyển đổi action command cho các phím
         * @param type loại command
         */
        public void changeKeyboardType(String type) {
            switch (type) {
                case "NONE": {
                    n0Btn.setActionCommand("");
                    n1Btn.setActionCommand("");
                    n2Btn.setActionCommand("");
                    n3Btn.setActionCommand("");
                    n4Btn.setActionCommand("");
                    n5Btn.setActionCommand("");
                    n6Btn.setActionCommand("");
                    n7Btn.setActionCommand("");
                    n8Btn.setActionCommand("");
                    n9Btn.setActionCommand("");
                    cancelBtn.setActionCommand("");
                    enterBtn.setActionCommand("");
                    clearBtn.setActionCommand("");
                    break;
                }
                case "PIN": {
                    n0Btn.setActionCommand("PIN 0");
                    n1Btn.setActionCommand("PIN 1");
                    n2Btn.setActionCommand("PIN 2");
                    n3Btn.setActionCommand("PIN 3");
                    n4Btn.setActionCommand("PIN 4");
                    n5Btn.setActionCommand("PIN 5");
                    n6Btn.setActionCommand("PIN 6");
                    n7Btn.setActionCommand("PIN 7");
                    n8Btn.setActionCommand("PIN 8");
                    n9Btn.setActionCommand("PIN 9");
                    cancelBtn.setActionCommand("PIN CANCEL");
                    enterBtn.setActionCommand("PIN ENTER");
                    clearBtn.setActionCommand("PIN CLEAR");
                    break;
                }
                case "MAIN_MENU": {
                    n0Btn.setActionCommand("");
                    n1Btn.setActionCommand("");
                    n2Btn.setActionCommand("");
                    n3Btn.setActionCommand("");
                    n4Btn.setActionCommand("");
                    n5Btn.setActionCommand("");
                    n6Btn.setActionCommand("");
                    n7Btn.setActionCommand("");
                    n8Btn.setActionCommand("");
                    n9Btn.setActionCommand("");
                    cancelBtn.setActionCommand("MAIN_MENU CANCEL");
                    enterBtn.setActionCommand("");
                    clearBtn.setActionCommand("");
                    break;
                }
                case "WITHDRAW": {
                    n0Btn.setActionCommand("WITHDRAW_INPUT 0");
                    n1Btn.setActionCommand("WITHDRAW_INPUT 1");
                    n2Btn.setActionCommand("WITHDRAW_INPUT 2");
                    n3Btn.setActionCommand("WITHDRAW_INPUT 3");
                    n4Btn.setActionCommand("WITHDRAW_INPUT 4");
                    n5Btn.setActionCommand("WITHDRAW_INPUT 5");
                    n6Btn.setActionCommand("WITHDRAW_INPUT 6");
                    n7Btn.setActionCommand("WITHDRAW_INPUT 7");
                    n8Btn.setActionCommand("WITHDRAW_INPUT 8");
                    n9Btn.setActionCommand("WITHDRAW_INPUT 9");
                    cancelBtn.setActionCommand("WITHDRAW_INPUT CANCEL");
                    enterBtn.setActionCommand("WITHDRAW_INPUT ENTER");
                    clearBtn.setActionCommand("WITHDRAW_INPUT CLEAR");
                    break;
                }
                case "DEPOSIT": {
                    n0Btn.setActionCommand("");
                    n1Btn.setActionCommand("");
                    n2Btn.setActionCommand("");
                    n3Btn.setActionCommand("");
                    n4Btn.setActionCommand("");
                    n5Btn.setActionCommand("");
                    n6Btn.setActionCommand("");
                    n7Btn.setActionCommand("");
                    n8Btn.setActionCommand("");
                    n9Btn.setActionCommand("");
                    cancelBtn.setActionCommand("DEPOSIT_INPUT CANCEL");
                    enterBtn.setActionCommand("DEPOSIT_INPUT ENTER");
                    clearBtn.setActionCommand("");
                    break;
                }
                case "TRANSFER_ACCOUNT": {
                    n0Btn.setActionCommand("TRANSFER_ACCOUNT_INPUT 0");
                    n1Btn.setActionCommand("TRANSFER_ACCOUNT_INPUT 1");
                    n2Btn.setActionCommand("TRANSFER_ACCOUNT_INPUT 2");
                    n3Btn.setActionCommand("TRANSFER_ACCOUNT_INPUT 3");
                    n4Btn.setActionCommand("TRANSFER_ACCOUNT_INPUT 4");
                    n5Btn.setActionCommand("TRANSFER_ACCOUNT_INPUT 5");
                    n6Btn.setActionCommand("TRANSFER_ACCOUNT_INPUT 6");
                    n7Btn.setActionCommand("TRANSFER_ACCOUNT_INPUT 7");
                    n8Btn.setActionCommand("TRANSFER_ACCOUNT_INPUT 8");
                    n9Btn.setActionCommand("TRANSFER_ACCOUNT_INPUT 9");
                    cancelBtn.setActionCommand("TRANSFER_ACCOUNT_INPUT CANCEL");
                    enterBtn.setActionCommand("TRANSFER_ACCOUNT_INPUT ENTER");
                    clearBtn.setActionCommand("TRANSFER_ACCOUNT_INPUT CLEAR");
                    break;
                }
                case "TRANSFER_AMOUNT": {
                    n0Btn.setActionCommand("TRANSFER_AMOUNT_INPUT 0");
                    n1Btn.setActionCommand("TRANSFER_AMOUNT_INPUT 1");
                    n2Btn.setActionCommand("TRANSFER_AMOUNT_INPUT 2");
                    n3Btn.setActionCommand("TRANSFER_AMOUNT_INPUT 3");
                    n4Btn.setActionCommand("TRANSFER_AMOUNT_INPUT 4");
                    n5Btn.setActionCommand("TRANSFER_AMOUNT_INPUT 5");
                    n6Btn.setActionCommand("TRANSFER_AMOUNT_INPUT 6");
                    n7Btn.setActionCommand("TRANSFER_AMOUNT_INPUT 7");
                    n8Btn.setActionCommand("TRANSFER_AMOUNT_INPUT 8");
                    n9Btn.setActionCommand("TRANSFER_AMOUNT_INPUT 9");
                    cancelBtn.setActionCommand("TRANSFER_AMOUNT_INPUT CANCEL");
                    enterBtn.setActionCommand("TRANSFER_AMOUNT_INPUT ENTER");
                    clearBtn.setActionCommand("TRANSFER_AMOUNT_INPUT CLEAR");
                    break;
                }
                default: {
                    System.out.println("Keyboard type not found");
                }
            }
        }
    }
}
