package model.observerImpl;

import java.time.LocalDateTime;

import model.Account;
import model.Observer;
import model.PrintInvoiceBehavior;

public abstract class Transaction implements Observer {
	protected Account aInfo;
	protected LocalDateTime transTime;
	protected PrintInvoiceBehavior printInvoiceBehavior;

	@Override
	public void update(Account aInfo) {
		// TODO Auto-generated method stub
		this.aInfo = aInfo;
	}
	
	public abstract void doTransaction();

	public String printInvoice() {
		return printInvoiceBehavior.printInvoice(aInfo, transTime);
	}
	
}
