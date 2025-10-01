package model;

import java.util.List;

import model.observerImpl.InformationReader;
import model.subjectImpl.CardReader;
import model.observerImpl.transactions.Deposit;
import model.observerImpl.Transaction;
import model.observerImpl.transactions.Transfer;
import model.observerImpl.transactions.Withdraw;

public class CRM implements ICRM {
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

	@Override
	public boolean checkValidCard(String username, String cardID) {
		List<Account> accountList = bank.getAccounts();
		for (Account aInfo : accountList) {
			if(aInfo.getUserName().equals(username) && aInfo.getCardID().equals(cardID))
				return true;
		}
		return false;
	}

	@Override
	public boolean insertCard(String username, String cardID) {
		Account aInfo = bank.getAccountByInfo(username, cardID);
		return this.reader.insertCard(aInfo);
	}

	@Override
	public boolean checkCardStatus() {
		return reader.checkCardStatus();
	}

	@Override
	public boolean checkPIN(String pin) {
		return this.reader.checkPIN(pin);
	}

	@Override
	public void returnCard() {
		this.reader.returnCard();
	}

	@Override
	public void lockCard() {
		this.reader.lockCard();
	}

	@Override
	public String getInfoUsername() {
		return this.info.getInfoUsername();
	}

	@Override
	public String getInfoCardID() {
		return this.info.getInfoCardID();
	}

	@Override
	public double getInfoCurrentBalance() {
		return this.info.getCurrentBalance();
	}

	@Override
	public boolean checkTransactionMoney(double money) {
		// TODO Auto-generated method stub
		return this.info.checkTransactionMoney(money);
	}

	@Override
	public void withdrawMoney(double money) {
		trans = new Withdraw(money);
		this.reader.registerObserver(trans);
		this.reader.notifyObserver(); // update vi co observer moi them vao
		trans.doTransaction();
	}

	@Override
	public void depositMoney(double money) {
		trans = new Deposit(money);
		this.reader.registerObserver(trans);
		this.reader.notifyObserver();
		trans.doTransaction();
	}

	@Override
	public void transferMoney(String account, double money) {
		// TODO Auto-generated method stub
		Account receivedAcc = null;
		List<Account> accountList = bank.getAccounts();
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

	@Override
	public String printInvoice() {
		return trans.printInvoice();
	}

	@Override
	public String getNameByAccount(String account) {
		List<Account> accountList = bank.getAccounts();
		for (Account aInfo : accountList) {
			if(aInfo.getCardID().equals(account))
				return aInfo.getUserName();
		}
		return "Khong tim duoc ten!";
	}
}
