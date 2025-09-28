package model;

import data.AccountDao;

import java.util.ArrayList;
import java.util.List;
/**
 * BankOwner: la co so du lieu cua 1 ngan hang, chua cac thong tin account co san
 * @author Admin
 *
 */
public class BankOwner {
	private String bankName;
	private List<Account> accounts;
	public BankOwner() {
		this.bankName = "ExampleBank";
		this.accounts = new ArrayList<Account>();
		this.getAccountList();
	}
	
	/**
	 * getAccountList: lay danh sach account tu db
	 */
	private void getAccountList() {
		AccountDao accountDao = new AccountDao();
		accounts = accountDao.getAll();
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}

	public Account getAccountByInfo(String username, String cardID) {
		for (Account aInfo : accounts) {
			if(aInfo.getUserName().equals(username) && aInfo.getCardID().equals(cardID))
				return aInfo;
		}
		return null;
	}
}
