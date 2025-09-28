package model.readerStateImpl;

import java.io.IOException;

import model.Account;
import model.ReaderState;
import model.subjectImpl.CardReader;

public class CardInsideState implements ReaderState {
	private CardReader reader;

	public CardInsideState(CardReader reader) {
		this.reader = reader;
	}

	@Override
	public boolean insertCard(Account aInfo) {
		return false;
	}

	@Override
	public boolean checkCardStatus() {
		Account aInfo = reader.getCurrentAccInfo();
		if (aInfo.isLocked())
			return false;
		return true;
	}

	@Override
	public boolean checkPIN(String pin) {
		Account aInfo = reader.getCurrentAccInfo();
		if (!pin.equals(aInfo.getPin()))
			return false;
		return true;
	}

	@Override
	public void lockCard() {
		Account aInfo = reader.getCurrentAccInfo();
		try {
			aInfo.setLocked(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void returnCard() {
		reader.setCurrentAccInfo(null);
		reader.setState(reader.getNoCardInsideState());
	}
	
}
