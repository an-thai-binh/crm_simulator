package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CRMListener implements ActionListener {
	private CRMPanel crmPanel;
	private boolean pinCheck, feature, deposit, fillAcc;
	
	public CRMListener(CRMPanel crmPanel) {
		this.crmPanel = crmPanel;
		this.pinCheck = false;
		this.feature = false;
		this.deposit = false;
		this.fillAcc = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if(src.equals("Insert")) {
			crmPanel.insertCard();
		}
		if(src.equals("Cash Deposit")) {
			crmPanel.deposit();
		}
		// numpad action
		if(pinCheck) {
			switch(src) {
				case "1": crmPanel.insertPINField("1"); break;
				case "2": crmPanel.insertPINField("2"); break;
				case "3": crmPanel.insertPINField("3"); break;
				case "4": crmPanel.insertPINField("4"); break;
				case "5": crmPanel.insertPINField("5"); break;
				case "6": crmPanel.insertPINField("6"); break;
				case "7": crmPanel.insertPINField("7"); break;
				case "8": crmPanel.insertPINField("8"); break;
				case "9": crmPanel.insertPINField("9"); break;
				case "0": crmPanel.insertPINField("0"); break;
				case "Cancel": crmPanel.cancelPINCheck(); break;
				case "Clear": crmPanel.clearPINField(); break;
				case "Enter": crmPanel.checkPIN(); break;
			}
		}
		
		if(feature) {
			if(src.equals("Cancel")) {
				crmPanel.returnToDefault();
			}
			if(src.equals("kiemtrasodu"))
				crmPanel.showCurrentBalance();
			if(src.equals("ruttien"))
				crmPanel.startWithdrawMoney();
			if(src.equals("noptien"))
				crmPanel.startDepositMoney();
			if(src.equals("chuyenkhoan"))
				crmPanel.startTransferMoney();
			if(!deposit) {	// neu dang khong phai giao dich nop tien
				if(fillAcc) {
					switch(src) {
					case "1": crmPanel.insertAccField("1"); break;
					case "2": crmPanel.insertAccField("2"); break;
					case "3": crmPanel.insertAccField("3"); break;
					case "4": crmPanel.insertAccField("4"); break;
					case "5": crmPanel.insertAccField("5"); break;
					case "6": crmPanel.insertAccField("6"); break;
					case "7": crmPanel.insertAccField("7"); break;
					case "8": crmPanel.insertAccField("8"); break;
					case "9": crmPanel.insertAccField("9"); break;
					case "0": crmPanel.insertAccField("0"); break;
//					case "Cancel": crmPanel.returnToMainMenu(); break;
					case "Clear": crmPanel.clearAccField(); break;
					case "Enter": crmPanel.checkAccField(); break;
					}
				} else {
					switch(src) {
					case "1": crmPanel.insertMoneyField("1"); break;
					case "2": crmPanel.insertMoneyField("2"); break;
					case "3": crmPanel.insertMoneyField("3"); break;
					case "4": crmPanel.insertMoneyField("4"); break;
					case "5": crmPanel.insertMoneyField("5"); break;
					case "6": crmPanel.insertMoneyField("6"); break;
					case "7": crmPanel.insertMoneyField("7"); break;
					case "8": crmPanel.insertMoneyField("8"); break;
					case "9": crmPanel.insertMoneyField("9"); break;
					case "0": crmPanel.insertMoneyField("0"); break;
//					case "Cancel": crmPanel.returnToMainMenu(); break;
					case "Clear": crmPanel.clearMoneyField(); break;
//					case "Enter": crmPanel.depositMoney(); break;
					}
				}
			}
			if(src.equals("withdraw"))
				crmPanel.withdrawMoney();
			
			if(src.equals("deposit"))
				crmPanel.depositMoney();
			
			if(src.equals("transfer"))
				crmPanel.transferMoney();
				
			if(src.equals("huygiaodich"))
				crmPanel.returnToMainMenu();
			
			if(src.equals("print")) {
				crmPanel.printInvoice();
				crmPanel.returnToDefault();
			}
			if(src.equals("Khong"))
				crmPanel.returnToDefault();
		}
	}

	public void setPinCheck(boolean pinCheck) {
		this.pinCheck = pinCheck;
	}

	public void setFeature(boolean feature) {
		this.feature = feature;
	}
	
	public void setDeposit(boolean deposit) {
		this.deposit = deposit;
	}

	public boolean getDeposit() {
		return deposit;
	}
	
	public void setFillAcc(boolean fillAcc) {
		this.fillAcc = fillAcc;
	}
}
