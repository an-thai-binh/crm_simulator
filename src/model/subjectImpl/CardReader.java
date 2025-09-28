package model.subjectImpl;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.*;
import model.readerStateImpl.CardInsideState;
import model.readerStateImpl.NoCardInsideState;

public class CardReader implements Subject {
	private Account currentAccInfo;
	private ReaderState current;
	private ReaderState cardInsideState;
	private ReaderState noCardInsideState;
	private List<Observer> observers;
	public CardReader() {
		this.observers = new ArrayList<Observer>();
		cardInsideState = new CardInsideState(this);
		noCardInsideState = new NoCardInsideState(this);
		this.current = noCardInsideState;
	}
	
	/* Getter-Setter start */
	public ReaderState getCardInsideState() {
		return cardInsideState;
	}

	public ReaderState getNoCardInsideState() {
		return noCardInsideState;
	}

	public void setState(ReaderState state) {
		this.current = state;
	}
	
	public Account getCurrentAccInfo() {
		return currentAccInfo;
	}
	
	public void setCurrentAccInfo(Account aInfo) {
		this.currentAccInfo = aInfo;
		this.notifyObserver();
	}
	/* Getter-Setter end */
	
	public boolean insertCard(Account aInfo) {
		return current.insertCard(aInfo); 
	}
	
	public boolean checkCardStatus() {
		return current.checkCardStatus();
	}
	
	public boolean checkPIN(String pin) {
		return current.checkPIN(pin);
	}
	
	public void lockCard() {
		current.lockCard();
	}
	
	public void returnCard() {
		current.returnCard();
	}
	
	/* Subject Method */
	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		if(!this.observers.contains(o))
			this.observers.add(o);
	}

	@Override
	public void unregisterObserver(Observer o) {
		// TODO Auto-generated method stub
		if(this.observers.contains(o))
			this.observers.remove(0);
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for (Observer o : observers) {
			o.update(this.currentAccInfo);
		}
	}
}
