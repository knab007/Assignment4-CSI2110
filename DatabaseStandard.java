// LEYAO LI
// 8776915
// Assignment 4 Part 1

import java.util.HashMap;

public class DatabaseStandard implements DatabaseInterface {

		private HashMap<String, String> password;
	
	public DatabaseStandard() {
		// this constructor must create the initial hasp map
		this.password = new HashMap<>();
	}
	
	@Override
	public String save(String plainPassword, String encryptedPassword) {
		// TODO Auto-generated method stub
		return password.put(encryptedPassword, plainPassword);
	}

	@Override
	public String decrypt(String encryptedPassword) {
		// TODO Auto-generated method stub
		return password.getOrDefault(encryptedPassword, "");
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return password.size();
	}

	@Override
	public void printStatistics() {
		// TODO Auto-generated method stub
		System.out.println("*** DatabaseStandard Statistics ***");
		System.out.println("Size is "+this.size()+" passwords.");
		System.out.println("Initial Number of Indexes when Created 16");
		System.out.println("*** End DatabaseStandard Statistics ***");
	}

}
