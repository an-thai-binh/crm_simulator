package model.observerImpl.transactions;

import model.printInvoiceBehaviorImpl.WithdrawInvoice;
import model.observerImpl.Transaction;

import java.io.IOException;
import java.time.LocalDateTime;

public class Withdraw extends Transaction {
	private double money;

	public Withdraw(double money) {
		this.transTime = LocalDateTime.now();
		this.printInvoiceBehavior = new WithdrawInvoice(money);
		this.money = money;
	}
	
	/**
	 * doTransaction: thuc hien giao dich rut tien 
	 * @return boolean
	 */
	@Override
	public void doTransaction() {
		double cur = super.aInfo.getCurrentBalance();
		cur = cur - money;
		try {
			super.aInfo.setCurrentBalance(cur);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
