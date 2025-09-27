package model.readerStateImpl;

import data.Account;
import model.ReaderState;
import model.subjectImpl.CardReader;

public class NoCardInsideState implements ReaderState {
	private CardReader reader;

	public NoCardInsideState(CardReader reader) {
		this.reader = reader;
	}

	@Override
	public boolean insertCard(Account aInfo) {
		reader.setCurrentAccInfo(aInfo);
		reader.setState(reader.getCardInsideState());
		return true;
	}

	@Override
	public boolean checkCardStatus() {
		return false;
	}

	@Override
	public boolean checkPIN(String pin) {
		return false;
	}

	@Override
	public void lockCard() {
		return;
	}

	@Override
	public void returnCard() {
		return;
	}
}
