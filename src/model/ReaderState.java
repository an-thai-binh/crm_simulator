package model;

import data.Account;

public interface ReaderState {
	public boolean insertCard(Account aInfo);
	public boolean checkCardStatus();
	public boolean checkPIN(String pin);
	public void lockCard();
	public void returnCard();
}
