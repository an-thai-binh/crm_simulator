package view2;

import view.CRMFrame;
import view2.screen.WelcomeScreen;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;

public class CRMView extends JFrame implements ICRMView {
    public CRMView() {
        this.setTitle("Cash Recycling Machine");
        this.setIconImage(new ImageIcon(CRMFrame.class.getResource("/images/cashicon.png")).getImage());
        this.setSize(650, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUIDefault();
        this.setContentPane(new CRMContentPane());

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
    public void display() {
        this.setVisible(true);
    }

    class CRMContentPane extends JPanel {
        private CardLayout screenCardLayout;
        private JPanel screenPane;
        private JButton insertCardBtn, insertMoneyBtn;
        public CRMContentPane() {
            // setup screenPane
            screenCardLayout = new CardLayout();
            screenPane = new JPanel();
            screenPane.setLayout(screenCardLayout);
            screenPane.add(new WelcomeScreen(), "WELCOME_SCREEN");

            this.setLayout(new BorderLayout());
            renderUI();
        }

        private void renderUI() {
            renderCenter();
            renderEastSide();
        }

        private void renderCenter() {
            JPanel centerPane = new JPanel(new GridLayout(2, 1));
            centerPane.setBackground(Color.BLUE);
            
            renderScreenSide(centerPane);
            
            renderKeyboardSide(centerPane);

            this.add(centerPane, BorderLayout.CENTER);
        }

        private void renderScreenSide(JPanel centerPane) {
            JPanel mainPane = new JPanel(new BorderLayout());

            JPanel leftBtnPane = new JPanel(new GridLayout(2, 1, 0, 50));
            leftBtnPane.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
            leftBtnPane.setBackground(Color.decode("#24bf65"));
            leftBtnPane.add(new JButton("<html><p style='text-align:center'>Kiểm tra<br/>số dư</p></html>"));
            leftBtnPane.add(new JButton("<html><p style='text-align:center'>Kiểm tra<br/>số dư</p></html>"));

            JPanel rightBtnPane = new JPanel(new GridLayout(2, 1, 0, 50));
            rightBtnPane.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
            rightBtnPane.setBackground(Color.decode("#24bf65"));
            rightBtnPane.add(new JButton("<html><p style='text-align:center'>Kiểm tra<br/>số dư</p></html>"));
            rightBtnPane.add(new JButton("<html><p style='text-align:center'>Kiểm tra<br/>số dư</p></html>"));

            mainPane.add(leftBtnPane, BorderLayout.WEST);
            mainPane.add(screenPane, BorderLayout.CENTER);
            mainPane.add(rightBtnPane, BorderLayout.EAST);

            centerPane.add(mainPane);
        }

        private void renderKeyboardSide(JPanel centerPane) {
            JPanel keyboardPane = new JPanel(new GridLayout(4, 4, 10, 10));
            keyboardPane.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
            keyboardPane.setBackground(Color.decode("#39eb83"));

            JButton n1Btn = new JButton("1"); JButton n2Btn = new JButton("2"); JButton n3Btn = new JButton("3"); JButton cancelBtn = new JButton("Cancel");
            JButton n4Btn = new JButton("4"); JButton n5Btn = new JButton("5"); JButton n6Btn = new JButton("6"); JButton enterBtn = new JButton("Enter");
            JButton n7Btn = new JButton("7"); JButton n8Btn = new JButton("8"); JButton n9Btn = new JButton("9"); JButton clearBtn = new JButton("Clear");
            JButton n0Btn = new JButton("0");

            Font keyboardFont = new Font(null, Font.BOLD, 20);
            n1Btn.setFont(keyboardFont); n2Btn.setFont(keyboardFont); n3Btn.setFont(keyboardFont); cancelBtn.setFont(keyboardFont);
            n4Btn.setFont(keyboardFont); n5Btn.setFont(keyboardFont); n6Btn.setFont(keyboardFont); enterBtn.setFont(keyboardFont);
            n7Btn.setFont(keyboardFont); n8Btn.setFont(keyboardFont); n9Btn.setFont(keyboardFont); clearBtn.setFont(keyboardFont);
            n0Btn.setFont(keyboardFont);

            cancelBtn.setBackground(Color.RED);
            enterBtn.setBackground(Color.GREEN);
            clearBtn.setBackground(Color.YELLOW);

            keyboardPane.add(n1Btn); keyboardPane.add(n2Btn); keyboardPane.add(n3Btn); keyboardPane.add(cancelBtn);
            keyboardPane.add(n4Btn); keyboardPane.add(n5Btn); keyboardPane.add(n6Btn); keyboardPane.add(enterBtn);
            keyboardPane.add(n7Btn); keyboardPane.add(n8Btn); keyboardPane.add(n9Btn); keyboardPane.add(clearBtn);
            keyboardPane.add(new JButton()); keyboardPane.add(n0Btn); keyboardPane.add(new JButton()); keyboardPane.add(new JButton());

            centerPane.add(keyboardPane);
        }

        private void renderEastSide() {
            JPanel eastPane = new JPanel(new GridLayout(2, 1, 0, 100));
            eastPane.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
            eastPane.setBackground(Color.decode("#39eb83"));
            insertCardBtn = new JButton("INSERT CARD");
            insertCardBtn.setBackground(Color.WHITE);
            eastPane.add(insertCardBtn);
            insertMoneyBtn = new JButton("INSERT MONEY");
            insertMoneyBtn.setBackground(Color.WHITE);
            eastPane.add(insertMoneyBtn);
            this.add(eastPane, BorderLayout.EAST);
        }
    }
}
