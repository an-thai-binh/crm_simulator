package model;

import data.AccountDao;

import java.io.IOException;

public class Account {
	private String userName;
	private String cardID;
	private String pin;
	private double currentBalance;
	private boolean locked;
	public Account(String userName, String cardID, String pin, double currentBalance, boolean locked) {
		this.userName = userName;
		this.cardID = cardID;
		this.pin = pin;
		this.currentBalance = currentBalance;
		this.locked = locked;
	}
	
	/* Getter Setter Start */
	public String getUserName() {
		return userName;
	}

	public String getCardID() {
		return cardID;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double newCurrentBalance) throws IOException {
		this.currentBalance = newCurrentBalance;
		// save db
		AccountDao accountDao = new AccountDao();
		accountDao.updateCurrentBalance(this.getCardID(), newCurrentBalance);
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean isLocked) throws IOException {
		this.locked = isLocked;
		// save db
		AccountDao accountDao = new AccountDao();
		accountDao.lockCard(this.getCardID());
	}
	/* Getter Setter End */
}
