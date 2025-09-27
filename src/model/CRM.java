package model;

import java.util.List;

import data.Account;
import data.BankOwner;
import model.observerImpl.InformationReader;
import model.subjectImpl.CardReader;
import model.observerImpl.transactions.Deposit;
import model.observerImpl.Transaction;
import model.observerImpl.transactions.Transfer;
import model.observerImpl.transactions.Withdraw;

public class CRM {
	private BankOwner bank;
	// crm module
	private CardReader reader;
	private InformationReader info;
	private Transaction trans;
	
	public CRM() {
		this.bank = new BankOwner();
		this.reader = new CardReader();
		this.info = new InformationReader();
		this.reader.registerObserver(info);
	
	}
	
	public boolean checkValidCard(String username, String cardID) {
		List<Account> accountList = bank.getAccountList();
		for (Account aInfo : accountList) {
			if(aInfo.getUserName().equals(username) && aInfo.getCardID().equals(cardID))
				return true;
		}
		return false;
	}
	
	public boolean insertCard(String username, String cardID) {
		Account aInfo = bank.getAccountByInfo(username, cardID);
		return this.reader.insertCard(aInfo);
	}

	public boolean checkCardStatus() {
		return reader.checkCardStatus();
	}
	
	public boolean checkPIN(String pin) {
		return this.reader.checkPIN(pin);
	}

	public void returnCard() {
		this.reader.returnCard();
	}

	public void lockCard() {
		this.reader.lockCard();
	}

	public String getInfoUsername() {
		return this.info.getInfoUsername();
	}

	public String getInfoCardID() {
		return this.info.getInfoCardID();
	}

	public double getInfoCurrentBalance() {
		return this.info.getCurrentBalance();
	}
	
	public boolean checkTransactionMoney(double money) {
		// TODO Auto-generated method stub
		return this.info.checkTransactionMoney(money);
	}
	
	public void withdrawMoney(double money) {
		trans = new Withdraw(money);
		this.reader.registerObserver(trans);
		this.reader.notifyObserver(); // update vi co observer moi them vao
		trans.doTransaction();
	}
		
	public void depositMoney(double money) {
		trans = new Deposit(money);
		this.reader.registerObserver(trans);
		this.reader.notifyObserver();
		trans.doTransaction();
	}
	
	public void transferMoney(String account, double money) {
		// TODO Auto-generated method stub
		Account receivedAcc = null;
		List<Account> accountList = bank.getAccountList();
		for (Account aInfo : accountList) {
			if(aInfo.getCardID().equals(account)) {
				receivedAcc = aInfo;
				break;
			}
		}
		trans = new Transfer(receivedAcc, money);
		reader.registerObserver(trans);
		reader.notifyObserver();
		trans.doTransaction();
	}
	
	public String printInvoice() {
		return trans.printInvoice();
	}

	public String getNameByAccount(String account) {
		List<Account> accountList = bank.getAccountList();
		for (Account aInfo : accountList) {
			if(aInfo.getCardID().equals(account))
				return aInfo.getUserName();
		}
		return "Khong tim duoc ten!";
	}
}
