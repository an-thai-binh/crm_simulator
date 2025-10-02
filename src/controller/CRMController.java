package controller;

import model.CRM;
import model.ICRM;
import utils.Formatter;
import view.ICRMView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class CRMController implements ICRMController {
	private ICRM model;
	private ICRMView view;

	public CRMController(ICRMView view, ICRM model) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void start() {
		view.display(new CRMListener());
	}

	/**
	 * insertCard: dua the vao CRM
	 * @param username: ten tai khoan
	 * @param cardID: ma the
	 * @return insert thanh cong (true/false)
	 */
	public boolean insertCard(String username, String cardID) {
		// TODO Auto-generated method stub
		return model.insertCard(username, cardID);
	}
	
	public boolean checkCardStatus() {
		// TODO Auto-generated method stub
		return model.checkCardStatus();
	}
	
	public boolean checkPIN(String pin) {
		// TODO Auto-generated method stub
		return model.checkPIN(pin);
	}

	public void lockCard() {
		// TODO Auto-generated method stub
		model.lockCard();
	}

	public String getInfoCurrentBalance() {
		// TODO Auto-generated method stub
		return "" + model.getInfoCurrentBalance();
	}

	public void returnCard() {
		model.returnCard();
	}

	public String getInfoUsername() {
		// TODO Auto-generated method stub
		return model.getInfoUsername();
	}

	public String getInfoCardID() {
		// TODO Auto-generated method stub
		return model.getInfoCardID();
	}

	public boolean checkTransactionMoney(double money) {
		// TODO Auto-generated method stub
		return model.checkTransactionMoney(money);
	}

	public void withdrawMoney(double money) {
		// TODO Auto-generated method stub
		model.withdrawMoney(money);
	}
	
	public void depositMoney(double money) {
		// TODO Auto-generated method stub
		model.depositMoney(money);
	}

	public void transferMoney(String account, double money) {
		model.transferMoney(account, money);
	}

	public String printInvoice() {
		return model.printInvoice();
	}
	
	public boolean checkValidCard(String username, String cardID) {
		// TODO Auto-generated method stub
		return model.checkValidCard(username, cardID);
	}

	public String getNameByAccount(String account) {
		// TODO Auto-generated method stub
		return model.getNameByAccount(account);
	}

	class CRMListener implements ActionListener {
		private Formatter formatter;
		private int pinCount;
		private JPasswordField pinField;
		private JTextField withdrawAmountField, depositAmountField, transferAccountField, transferAmountField;
		private Double depositAmount;

		public CRMListener() {
			depositAmount = null;
			formatter = new Formatter();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String input = e.getActionCommand();
			StringTokenizer tokens = new StringTokenizer(input, " ");
			switch (tokens.hasMoreTokens() ? tokens.nextToken() : "") {
				case "INSERT_CARD": {
					JTextField usernameField = new JTextField();
					JTextField cardIdField = new JTextField();
					Object[] inputs = {"Tên chủ thẻ", usernameField, "Số thẻ", cardIdField};
					int result = JOptionPane.showConfirmDialog(view.getContentPane(), inputs, "CARD", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.OK_OPTION) {
						if(!checkValidCard(usernameField.getText(), cardIdField.getText())) {
							view.showErrorDialog("Không tìm thấy thẻ");
							return;
						}
						if(!insertCard(usernameField.getText(), cardIdField.getText())) {
							view.showErrorDialog("Hệ thống đang bận xử lý 1 thẻ khác");
							return;
						}
						if(!checkCardStatus()) {
							view.showErrorDialog("Thẻ này đã bị khóa");
							return;
						}
						pinCount = 5;
						pinField = new JPasswordField();
						view.renderInsertPinScreen(pinField);
					}
					break;
				}
				case "PIN": {
					String value = tokens.hasMoreTokens() ? tokens.nextToken() : "";
					try {
						int num = Integer.parseInt(value);
						pinField.setText(String.valueOf(pinField.getPassword()) + num);
					} catch (Exception ex) {
                        switch (value) {
							case "CANCEL": {
								returnCard();
								view.renderGreetingScreen();
								break;
							}
							case "ENTER": {
								if(!checkPIN(String.valueOf(pinField.getPassword()))) {
									if(pinCount == 1) {	// nếu lúc này chỉ còn 1 lần thử
										lockCard();
										returnCard();
										view.showErrorDialog("Tài khoản đã bị khóa do nhập sai quá số lần cho phép");
										view.renderGreetingScreen();
									} else {
										pinCount--;
										view.showErrorDialog("Mã PIN không chính xác. Quý khách còn " + pinCount + " lần thử");
									}
									return;
								}
								view.renderMainMenuScreen(getInfoUsername(), getInfoCardID(),-1);
								break;
							}
							case "CLEAR": {
								pinField.setText("");
								break;
							}
						}
                    }
					break;
				}
				case "MAIN_MENU": {
					String value = tokens.hasMoreTokens() ? tokens.nextToken() : "";
					if(value.equals("CANCEL")) {
						returnCard();
						view.renderGreetingScreen();
						return;
					}
					break;
				}
				case "CHECK_BALANCE": {
					view.renderMainMenuScreen(getInfoUsername(), getInfoCardID(), Double.parseDouble(getInfoCurrentBalance()));
					break;
				}
				case "WITHDRAW": {
					withdrawAmountField = new JTextField();
					view.renderWithdrawScreen(withdrawAmountField);
					break;
				}
				case "WITHDRAW_INPUT": {
					String value = tokens.hasMoreTokens() ? tokens.nextToken() : "";
					try {
						int num = Integer.parseInt(value);
						withdrawAmountField.setText(withdrawAmountField.getText() + num);
					} catch (Exception ex) {
						switch (value) {
							case "CANCEL": {
								view.renderMainMenuScreen(getInfoUsername(), getInfoCardID(), -1);
								break;
							}
							case "ENTER": {
								String amountStr = withdrawAmountField.getText();
								if(amountStr.isEmpty()) {
									view.showErrorDialog("Vui lòng nhập số tiền muốn rút");
									return;
								}
								double amount = Double.parseDouble(amountStr);
								if(amount < 50000) {
									view.showErrorDialog("Chỉ được phép rút tối thiểu 50.000đ");
									return;
								}
								if(!checkTransactionMoney(amount)) {
									view.showErrorDialog("Số dư không đủ để thực hiện giao dịch");
									return;
								}
								withdrawMoney(amount);
								view.renderAskForInvoiceScreen();
								break;
							}
							case "CLEAR": {
								withdrawAmountField.setText("");
								break;
							}
						}
					}
					break;
				}
				case "DEPOSIT": {
					depositAmount = Double.valueOf(0.0);
					depositAmountField = new JTextField(formatter.formatMoney(depositAmount));
					view.renderDepositScreen(depositAmountField);
					break;
				}
				case "DEPOSIT_INPUT": {
					String value = tokens.hasMoreTokens() ? tokens.nextToken() : "";
					switch (value) {
						case "CANCEL": {
							view.renderMainMenuScreen(getInfoUsername(), getInfoCardID(), -1);
							depositAmount = null;
							break;
						}
						case "ENTER": {
							if(depositAmount <= 0) {
								view.showErrorDialog("Số tiền không hợp lệ");
								return;
							}
							depositMoney(depositAmount);
							depositAmount = null;
							view.renderAskForInvoiceScreen();
							break;
						}
					}
					break;
				}
				case "INSERT_MONEY": {
					if(depositAmount == null) {
						return;
					}
					JTextField insertMoneyField = new JTextField();
					Object[] inputs = {"Số tiền", insertMoneyField};
					int result = JOptionPane.showConfirmDialog(view.getContentPane(), inputs, "INSERT MONEY", JOptionPane.OK_CANCEL_OPTION);
					if(result == JOptionPane.OK_OPTION) {
						try {
							double insertMoney = Double.parseDouble(insertMoneyField.getText());
							depositAmount += insertMoney;
							depositAmountField.setText(formatter.formatMoney(depositAmount));
						} catch (Exception ex) {
							view.showErrorDialog("Số tiền không hợp lệ");
						}
					}
					break;
				}
				case "TRANSFER": {
					transferAccountField = new JTextField();
					transferAmountField = new JTextField();
					view.renderTransferScreen(transferAccountField, null, transferAmountField, true);
					break;
				}
				case "TRANSFER_ACCOUNT_INPUT": {
					String value = tokens.hasMoreTokens() ? tokens.nextToken() : "";
					try {
						int num = Integer.parseInt(value);
						transferAccountField.setText(transferAccountField.getText() + num);
					} catch (Exception ex) {
                        switch (value) {
							case "CANCEL": {
								view.renderMainMenuScreen(getInfoUsername(), getInfoCardID(), -1);
								break;
							}
							case "ENTER": {
								if(transferAccountField.getText().equals(getInfoCardID())) {
									view.showErrorDialog("Không thể chuyển tiền cho chính bạn");
									return;
								}
								String accountName = getNameByAccount(transferAccountField.getText());
								if(accountName.equals("Khong tim duoc ten!")) {
									transferAccountField.setText("");
									view.renderTransferScreen(transferAccountField, "Không tìm thấy chủ tài khoản", transferAmountField, true);
									return;
								}
								view.renderTransferScreen(transferAccountField, accountName, transferAmountField, false);
								break;
							}
							case "CLEAR": {
								transferAccountField.setText("");
								break;
							}
						}
                    }
                    break;
				}
				case "TRANSFER_AMOUNT_INPUT": {
					String value = tokens.hasMoreTokens() ? tokens.nextToken() : "";
					try {
						int num = Integer.parseInt(value);
						transferAmountField.setText(transferAmountField.getText() + num);
					} catch (Exception ex) {
						switch (value) {
							case "CANCEL": {
								transferAccountField = new JTextField();
								transferAmountField = new JTextField();
								view.renderTransferScreen(transferAccountField, null, transferAmountField, true);
								break;
							}
							case "ENTER": {
								try {
									double transferAmount = Double.parseDouble(transferAmountField.getText());
									if(transferAmount < 50000) {
										view.showErrorDialog("Chỉ được phép chuyển tối thiểu 50.000đ");
										return;
									}
									if(!checkTransactionMoney(transferAmount)) {
										view.showErrorDialog("Số dư không đủ để thực hiện giao dịch");
										return;
									}
									transferMoney(transferAccountField.getText(), transferAmount);
									view.renderAskForInvoiceScreen();
								} catch (Exception exception) {
                                    view.showErrorDialog("Số tiền không hợp lệ");
                                }
                                break;
							}
							case "CLEAR": {
								transferAmountField.setText("");
								break;
							}
						}
					}
					break;
				}
				case "INVOICE": {
					String value = tokens.hasMoreTokens() ? tokens.nextToken() : "";
					if(value.equals("YES")) {
						view.showInvoiceDialog(printInvoice());
					}
					view.renderMainMenuScreen(getInfoUsername(), getInfoCardID(), -1);
					break;
				}
				default: {
					System.out.println("Command not found");
				}
			}
		}
	}
}
