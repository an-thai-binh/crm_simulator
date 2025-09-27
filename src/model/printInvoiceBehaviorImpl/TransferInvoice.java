package model.printInvoiceBehaviorImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import data.Account;
import model.PrintInvoiceBehavior;

public class TransferInvoice implements PrintInvoiceBehavior {
	private String receivedAccount;
	private double money;
	public TransferInvoice(String receivedAccount, double money) {
		this.receivedAccount = receivedAccount;
		this.money = money;
	}
	@Override
	public String printInvoice(Account aInfo, LocalDateTime transTime) {
		String result = "";
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");	// formatter lai date time
		result += "KHACH HANG: " + aInfo.getUserName() + "\n";
		result += "THOI GIAN GD: " + dtFormatter.format(transTime)+ "\n";
		result += "LOAI GIAO DICH: CHUYEN TIEN" + "\n";
		result += "TAI KHOAN NHAN: " + receivedAccount + "\n"; 
		result += "CHI TIET GIAO DICH: " + "-" + money + "VND";
		return result;
	}
}
