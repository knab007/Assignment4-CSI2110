// LEYAO LI
// 8776915
// Assignment 4 Part 1

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;

public class PasswordCracker {

	public void createDatabase(ArrayList<String> commonPassword, DatabaseInterface database) throws UnsupportedEncodingException {
		// receives list of passwords and populates database with entries consisting
		// of (key,value) pairs where the value is the password and the key is the
		// encrypted password (encrypted using Sha1)
		// in addition to passwords in commonPasswords list, this class is
		// responsible to add mutated passwords according to rules 1-5.
		String plainPassword;
		String encryptedPassword;
		String year = Calendar.getInstance().get(Calendar.YEAR) + "";
		System.out.println("Please be patient. The running time of this part could be long.");
		for (int i = 0; i < commonPassword.size(); i++) {
			plainPassword = commonPassword.get(i);
			ArrayList<String> temp = new ArrayList<String>();
			int num = 1;
			temp.add(plainPassword);
			for (int index = 0; index < plainPassword.length(); index++) {
				if (plainPassword.charAt(index) == 'a') {
					for (int n = 0; n < num; n++) {
						String passwordcopy = temp.get(n).substring(0, index) + "@" + temp.get(n).substring(index+1);
						temp.add(passwordcopy);
					}
					num *= 2;
				}
				if (plainPassword.charAt(index) == 'e') {
					for (int n = 0; n < num; n++) {
						String passwordcopy = temp.get(n).substring(0, index) + "3" + temp.get(n).substring(index+1);
						temp.add(passwordcopy);
					}
					num *= 2;
				}
				if (plainPassword.charAt(index) == 'i'){
					for (int n = 0; n < num; n++) {
						String passwordcopy = temp.get(n).substring(0, index) + "1" + temp.get(n).substring(index+1);
						temp.add(passwordcopy);
					}
					num *= 2;
				}
			}
			
			// Rule #1 (Capitalize the first letter of each word starting with a letter)
			for (int save = 0; save < num; save++) {
				plainPassword = temp.get(save);
				if (Character.isLetter(plainPassword.charAt(0)))
					if (Character.isLowerCase(plainPassword.charAt(0))) {
						plainPassword = plainPassword.substring(0, 1).toUpperCase() + plainPassword.substring(1);
						if (!temp.contains(plainPassword)) {
							temp.add(plainPassword);
							num++;
						}
					}
			}
			
			// Rule #2 (Add 2018 at the end of the password)
			for (int save = 0; save<num; save++) {
				plainPassword = temp.get(save) + year;
				temp.add(plainPassword);
			}
			num*=2;
			
			// Save password into database
		    for (int save = 0; save<num; save++) {
		    	plainPassword = temp.get(save);
		    	encryptedPassword = Sha1.hash(plainPassword);
		    	database.save(plainPassword, encryptedPassword);
		    }
		}
	}
	
	public String crackPassword(String encryptedPassword, DatabaseInterface database) {
		//uses database to crack encrypted password, returning the original password
		return database.decrypt(encryptedPassword);
	}
}