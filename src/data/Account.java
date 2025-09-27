package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Account {
	private String userName;
	private String cardID;
	private String pin;
	private double currentBalance;
	private boolean locked;
	public Account(String userName, String cardID, String pin, double currentBalance, boolean locked) {
		this.userName = userName;
		this.cardID = cardID;
		this.pin = pin;
		this.currentBalance = currentBalance;
		this.locked = locked;
	}
	
	/* Getter Setter Start */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double newCurrentBalance) throws IOException {
		modifyFile("src//data//accountList.txt",
				userName + "\t" + cardID + "\t" + pin + "\t" + currentBalance + "\t" + locked,
				userName + "\t" + cardID + "\t" + pin + "\t" + newCurrentBalance + "\t" + locked); // chinh sua lai tren file txt
		this.currentBalance = newCurrentBalance;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean newLocked) throws IOException {
		modifyFile("src//data//accountList.txt",
				userName + "\t" + cardID + "\t" + pin + "\t" + currentBalance + "\t" + locked,
				userName + "\t" + cardID + "\t" + pin + "\t" + currentBalance + "\t" + newLocked); // chinh sua lai tren file txt
		this.locked = newLocked;

	}
	/* Getter Setter End */
	
	/**
	 * modifyFile: chinh sua thong tin cua file txt (data)
	 * @param filePath: duong dan file
	 * @param oldString: thong tin cu
	 * @param newString: thong tin moi
	 * Source: https://javaconceptoftheday.com/modify-replace-specific-string-in-text-file-in-java/
	 */
	public static void modifyFile(String filePath, String oldString, String newString) {
		File fileToBeModified = new File(filePath);

		String oldContent = "";

		BufferedReader reader = null;

		FileWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));

			// Reading all the lines of input text file into oldContent

			String line = reader.readLine();

			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();

				line = reader.readLine();
			}

			// Replacing oldString with newString in the oldContent

			String newContent = oldContent.replaceAll(oldString, newString);

			// Rewriting the input text file with newContent

			writer = new FileWriter(fileToBeModified);

			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Closing the resources

				reader.close();

				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
