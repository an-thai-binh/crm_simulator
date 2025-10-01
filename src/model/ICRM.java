package model;

public interface ICRM {
    boolean checkValidCard(String username, String cardID);
    boolean insertCard(String username, String cardID);
    boolean checkCardStatus();
    boolean checkPIN(String pin);
    void returnCard();
    void lockCard();
    String getInfoUsername();
    String getInfoCardID();
    double getInfoCurrentBalance();
    boolean checkTransactionMoney(double money);
    void withdrawMoney(double money);
    void depositMoney(double money);
    void transferMoney(String account, double money);
    String printInvoice();
    String getNameByAccount(String account);
}
