package model;

public interface ICRM {
    /**
     * kiểm tra xem 1 thẻ có hợp lệ hay không
     * @param username tên chủ thẻ
     * @param cardID số thẻ
     * @return true nếu hợp lệ/false nếu không hợp lệ
     */
    boolean checkValidCard(String username, String cardID);

    /**
     * nhận thẻ vào máy để xử lý
     * @param username tên chủ thẻ
     * @param cardID số thẻ
     * @return true nếu nhận thẻ thành công/false nếu không thành công
     */
    boolean insertCard(String username, String cardID);

    /**
     * kiểm tra tình trạng thẻ
     * @return true nếu không bị khóa/false nếu đang bị khóa
     */
    boolean checkCardStatus();

    /**
     * kiểm tra mã PIN có hợp lệ không
     * @param pin mã PIN
     * @return true nếu hợp lệ/false nếu không hợp lệ
     */
    boolean checkPIN(String pin);

    /**
     * trả thẻ từ máy về
     */
    void returnCard();

    /**
     * khóa thẻ
     */
    void lockCard();

    /**
     * lấy ra tên tài khoản của thẻ đang làm việc
     * @return tên tài khoản
     */
    String getInfoUsername();

    /**
     * lấy ra số tài khoản của thẻ đang làm việc
     * @return số tài khoản
     */
    String getInfoCardID();

    /**
     * lấy ra số dư hiện tại của tài khoản
     * @return số dư hiện tại
     */
    double getInfoCurrentBalance();

    /**
     * kiểm tra xem số tiền có đủ để thực hiện giao dịch không?
     * @param money số tiền cần chi để thực hiện giao dịch
     * @return true nếu đủ/false nếu không đủ
     */
    boolean checkTransactionMoney(double money);

    /**
     * thực hiện giao dịch rút tiền
     * @param money số tiền cần rút
     */
    void withdrawMoney(double money);

    /**
     * thực hiện giao dịch nộp tiền
     * @param money số tiền cần nộp
     */
    void depositMoney(double money);

    /**
     * thực hiện giao dịch chuyển khoản
     * @param account tài khoản nhận tiền
     * @param money số tiền cần chuyển
     */
    void transferMoney(String account, double money);

    /**
     * in hóa đơn của giao dịch vừa thực hiện
     * @return thông tin hóa đơn
     */
    String printInvoice();

    /**
     * lấy ra tên tài khoản dựa trên số tài khoản
     * @param account số tài khoản
     * @return tên tài khoản
     */
    String getNameByAccount(String account);
}
