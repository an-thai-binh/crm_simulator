package model.observerImpl;

import model.Account;
import model.Observer;

public class InformationReader implements Observer {
	private Account aInfo;
	@Override
	public void update(Account aInfo) {
		// TODO Auto-generated method stub
		this.aInfo = aInfo;
	}
	
	/**
	 * displayInfo: hien thi thong tin tai khoan
	 * @return String
	 */
	public String displayInfo() {
		String result = "";
		result += "Ten tai khoan: " + aInfo.getUserName();
		result += "So tai khoan: " + aInfo.getCardID();
		return result;
	}
	
	/**
	 * checkCurrentBalance: kiem tra so du trong tai khoan
	 * @return double
	 */
	public double getCurrentBalance() {
		return this.aInfo.getCurrentBalance();
	}

	public String getInfoUsername() {
		return this.aInfo.getUserName();
	}

	public String getInfoCardID() {
		return this.aInfo.getCardID();
	}

	public boolean checkTransactionMoney(double money) {
		double currentBalance = this.getCurrentBalance();
		return currentBalance - money >= 0;
	}
}
