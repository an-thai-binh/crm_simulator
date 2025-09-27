package model;

import data.Account;

public interface Observer {

	public void update(Account currentAccInfo);

}
