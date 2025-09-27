package model.observerImpl.transactions;

import java.io.IOException;
import java.time.LocalDateTime;

import data.Account;
import model.printInvoiceBehaviorImpl.TransferInvoice;
import model.observerImpl.Transaction;

public class Transfer extends Transaction {
	private Account receivedAccount;
	private double money;
	
	public Transfer(Account receivedAccount, double money) {
		this.transTime = LocalDateTime.now();
		this.receivedAccount = receivedAccount;
		this.money = money;
		this.printInvoiceBehavior = new TransferInvoice(receivedAccount.getCardID(), money);
	}

	@Override
	public void doTransaction() {
		// TODO Auto-generated method stub
		double receiveCur = receivedAccount.getCurrentBalance();
		double giveCur = super.aInfo.getCurrentBalance();
		try {
			receivedAccount.setCurrentBalance(receiveCur + money);
			super.aInfo.setCurrentBalance(giveCur - money);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
