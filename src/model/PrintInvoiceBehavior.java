package model;

import java.time.LocalDateTime;

public interface PrintInvoiceBehavior {
	public String printInvoice(Account aInfo, LocalDateTime transTime);
}
