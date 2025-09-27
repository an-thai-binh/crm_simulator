package model;

import java.time.LocalDateTime;

import data.Account;

public interface PrintInvoiceBehavior {
	public String printInvoice(Account aInfo, LocalDateTime transTime);
}
