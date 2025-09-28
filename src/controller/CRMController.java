package controller;

import model.CRM;

public class CRMController {
	private CRM crm;

	public CRMController() {
		crm = new CRM();
	}

	/**
	 * insertCard: dua the vao CRM
	 * @param username: ten tai khoan
	 * @param cardID: ma the
	 * @return insert thanh cong (true/false)
	 */
	public boolean insertCard(String username, String cardID) {
		// TODO Auto-generated method stub
		return crm.insertCard(username, cardID);
	}
	
	public boolean checkCardStatus() {
		// TODO Auto-generated method stub
		return crm.checkCardStatus();
	}
	
	public boolean checkPIN(String pin) {
		// TODO Auto-generated method stub
		return crm.checkPIN(pin);
	}

	public void lockCard() {
		// TODO Auto-generated method stub
		crm.lockCard();
	}

	public String getInfoCurrentBalance() {
		// TODO Auto-generated method stub
		return "" + crm.getInfoCurrentBalance();
	}

	public void returnCard() {
		crm.returnCard();
	}

	public String getInfoUsername() {
		// TODO Auto-generated method stub
		return crm.getInfoUsername();
	}

	public String getInfoCardID() {
		// TODO Auto-generated method stub
		return crm.getInfoCardID();
	}

	public boolean checkTransactionMoney(double money) {
		// TODO Auto-generated method stub
		return crm.checkTransactionMoney(money);
	}

	public void withdrawMoney(double money) {
		// TODO Auto-generated method stub
		crm.withdrawMoney(money);
		
	}
	
	public void depositMoney(double money) {
		// TODO Auto-generated method stub
		crm.depositMoney(money);
	}
	
	public boolean checkValidCard(String username, String cardID) {
		// TODO Auto-generated method stub
		return crm.checkValidCard(username, cardID);
	}

	public String printInvoice() {
		return crm.printInvoice();
		
	}

	public String getNameByAccount(String account) {
		// TODO Auto-generated method stub
		return crm.getNameByAccount(account);
	}

	public void transferMoney(String account, double money) {
		crm.transferMoney(account, money);
		
	}
}
