package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface ICRMView {
    /**
     * hiển thị giao diện
     * @param listener ActionListener xử lý sự kiện cho giao diện
     */
    void display(ActionListener listener);

    /**
     * lấy ra JPanel làm nội dung cho toàn bộ giao diện
     * @return JPanel
     */
    JPanel getContentPane();
    /**
     * hiển thị màn hình lời chào
     */
    void renderGreetingScreen();

    /**
     * hiển thị màn hình nhập mã Pin
     * @param pinField JPasswordField quản lý mã pin
     */
    void renderInsertPinScreen(JPasswordField pinField);

    /**
     * hiển thị trang chủ
     * @param username tên tài khoản
     * @param cardId số tài khoản
     */
    void renderMainMenuScreen(String username, String cardId, double currentBalance);

    /**
     * hiển thị trang rút tiền
     * @param withdrawAmountField JTextField quản lý số tiền rút ra
     */
    void renderWithdrawScreen(JTextField withdrawAmountField);

    /**
     * hiển thị trang nộp tiền
     * @param depositAmountField JTextField quản lý số tiền đã nộp
     */
    void renderDepositScreen(JTextField depositAmountField);

    /**
     * hiển thị trang chuyển khoản
     * @param transferAccountField JTextField quản lý số tài khoản
     * @param accountName tên tài khoản
     * @param transferAmountField JTextField quản lý số tiền chuyển
     * @param isFillInAccount đang xử lý ô account không? (false thì xử lý ô amount)
     */
    void renderTransferScreen(JTextField transferAccountField, String accountName, JTextField transferAmountField, boolean isFillInAccount);

    /**
     * hiển thị trang hỏi in hóa đơn
     */
    void renderAskForInvoiceScreen();

    /**
     * hiển thị thông báo lỗi
     * @param message nội dung
     */
    void showErrorDialog(String message);

    /**
     * hiển thị hóa đơn
     * @param invoice nội dung hóa đơn
     */
    void showInvoiceDialog(String invoice);
}
