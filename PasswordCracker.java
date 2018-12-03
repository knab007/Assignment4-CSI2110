// LEYAO LI
// 8776915
// Assignment 4 Part 1

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;

public class PasswordCracker {

	private ArrayList<String> commonPassword;
	public void createDatabase(ArrayList<String> commonPassword, DatabaseInterface database) throws UnsupportedEncodingException {
		// receives list of passwords and populates database with entries consisting
		// of (key,value) pairs where the value is the password and the key is the
		// encrypted password (encrypted using Sha1)
		// in addition to passwords in commonPasswords list, this class is
		// responsible to add mutated passwords according to rules 1-5.
		String plainPassword;
		String encryptedPassword;
		String plainPasswordrule;
		this.commonPassword = commonPassword;
		System.out.println("Please be patient. The running time of this part could be very long.");
		for (int i = 0; i < this.commonPassword.size(); i++) {
			plainPassword = this.commonPassword.get(i);
			encryptedPassword = Sha1.hash(plainPassword);
		    database.save(plainPassword, encryptedPassword);
		    
		    // Rule #1
		    plainPasswordrule = ruleOne(plainPassword);
		    if (plainPasswordrule != null) {
		    	encryptedPassword = Sha1.hash(plainPasswordrule);
			    database.save(plainPasswordrule, encryptedPassword);
		    }
		    
		    // Rule #2
		    plainPasswordrule = ruleTwo(plainPassword);
		    if (plainPasswordrule != null) {
		    	encryptedPassword = Sha1.hash(plainPasswordrule);
			    database.save(plainPasswordrule, encryptedPassword);
		    }
		    
		    // Rule #3
		    plainPasswordrule = ruleThree(plainPassword);
		    if (plainPasswordrule != null) {
		    	encryptedPassword = Sha1.hash(plainPasswordrule);
			    database.save(plainPasswordrule, encryptedPassword);
		    }
		    
		    // Rule #4
		    plainPasswordrule = ruleFour(plainPassword);
		    if (plainPasswordrule != null) {
		    	encryptedPassword = Sha1.hash(plainPasswordrule);
			    database.save(plainPasswordrule, encryptedPassword);
		    }
		    
		    // Rule #5
		    plainPasswordrule = ruleFive(plainPassword);
		    if (plainPasswordrule != null) {
		    	encryptedPassword = Sha1.hash(plainPasswordrule);
			    database.save(plainPasswordrule, encryptedPassword);
		    }
		}
	}
	
	public String crackPassword(String encryptedPassword, DatabaseInterface database) {
		//uses database to crack encrypted password, returning the original password
		return database.decrypt(encryptedPassword);
	}
	
	public String ruleOne(String plainPassword) {
	    // rules #1: Capitalize the first letter of each word starting with a letter
		if (Character.isLetter(plainPassword.charAt(0)))
			if (Character.isLowerCase(plainPassword.charAt(0))) {
				plainPassword = plainPassword.substring(0, 1).toUpperCase() + plainPassword.substring(1);
				if (!this.commonPassword.contains(plainPassword))
					this.commonPassword.add(plainPassword);
				return plainPassword;
			}
		return null;
	}
	
	public String ruleTwo(String plainPassword) {
		// rules #2: Add the current year to the word
		String year = Calendar.getInstance().get(Calendar.YEAR) + "";
		if (plainPassword.length()>=4) {
			String lastFour = plainPassword.substring(plainPassword.length()-4);
			if (lastFour.compareTo(year)==0)
				return null;
		}
		plainPassword = plainPassword + year;
		if (!this.commonPassword.contains(plainPassword))
			this.commonPassword.add(plainPassword);
		return plainPassword;
	}
	
	public String ruleThree(String plainPassword) {
	    // rules #3: Use @ instead of a
		if (plainPassword.indexOf("a")==-1)
			return null;
		else {
			plainPassword = plainPassword.replaceFirst("a", "@");
			if (!this.commonPassword.contains(plainPassword))
				this.commonPassword.add(plainPassword);
		}
	    return plainPassword;
	}
	
	public String ruleFour(String plainPassword) {
	    // rules #4: Use 3 instead of e
		if (plainPassword.indexOf("e")==-1)
			return null;
		else {
			plainPassword = plainPassword.replaceFirst("e", "3");
			if (!this.commonPassword.contains(plainPassword))
				this.commonPassword.add(plainPassword);
		}
	    return plainPassword;
	}
	
	public String ruleFive(String plainPassword) {
	    // rules #5: Use 1 instead of i
		if (plainPassword.indexOf("i")==-1)
			return null;
		else {
			plainPassword = plainPassword.replaceFirst("i", "1");
			if (!this.commonPassword.contains(plainPassword))
				this.commonPassword.add(plainPassword);
		}
	    return plainPassword;
	}
}