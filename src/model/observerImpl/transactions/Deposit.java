package model.observerImpl.transactions;

import model.printInvoiceBehaviorImpl.DepositInvoice;
import model.observerImpl.Transaction;

import java.io.IOException;
import java.time.LocalDateTime;

public class Deposit extends Transaction {
	private double money;
	public Deposit(double money) {
		this.transTime = LocalDateTime.now();
		this.printInvoiceBehavior = new DepositInvoice(money);
		this.money = money;
	}
	@Override
	public void doTransaction() {
		// TODO Auto-generated method stub
		double cur = super.aInfo.getCurrentBalance();
		cur = cur + money;
		try {
			super.aInfo.setCurrentBalance(cur);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
