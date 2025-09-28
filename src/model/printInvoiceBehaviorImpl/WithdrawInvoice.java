package model.printInvoiceBehaviorImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.Account;
import model.PrintInvoiceBehavior;

public class WithdrawInvoice implements PrintInvoiceBehavior {
	private double money;
	
	public WithdrawInvoice(double money) {	
		this.money = money;
	}

	@Override
	public String printInvoice(Account aInfo, LocalDateTime transTime) {
		String result = "";
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");	// formatter lai date time
		result += "KHACH HANG: " + aInfo.getUserName() + "\n";
		result += "THOI GIAN GD: " + dtFormatter.format(transTime)+ "\n";
		result += "LOAI GIAO DICH: RUT TIEN" + "\n";
		result += "CHI TIET GIAO DICH: " + "-" + money + "VND";
		return result;
	}

}
