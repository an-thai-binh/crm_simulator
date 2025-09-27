package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/**
 * BankOwner: la co so du lieu cua 1 ngan hang, chua cac thong tin account co san
 * @author Admin
 *
 */
public class BankOwner {
	private String bankName;
	private List<Account> accountSet;
	public BankOwner() {
		this.bankName = "ExampleBank";
		this.accountSet = new ArrayList<Account>();
		this.getAccountList("accountList.txt");
	}
	
	/**
	 * getAccountList: lay danh sach account tu file txt
	 * @param pathFile: ten file
	 */
	private void getAccountList(String pathFile) {
		URL url = BankOwner.class.getResource(pathFile);
		String path = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
		File f = new File(path);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while((line = br.readLine()) != null) {
				String[] part = line.split("\t");
				String userName = part[0];
				String cardID = part[1];
				String pin = part[2];
				double currentBalance = Double.valueOf(part[3]);
				boolean locked = Boolean.valueOf(part[4]);
				accountSet.add(new Account(userName, cardID, pin, currentBalance, locked));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Account> getAccountList() {
		return accountSet;
	}

	public Account getAccountByInfo(String username, String cardID) {
		for (Account aInfo : accountSet) {
			if(aInfo.getUserName().equals(username) && aInfo.getCardID().equals(cardID))
				return aInfo;
		}
		return null;
	}
	
}
