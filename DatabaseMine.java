// LEYAO LI
// 8776915
// Assignment 4 Part 2

public class DatabaseMine implements DatabaseInterface {
	int N; 
	// this is a prime number that gives the number of addresses
	// these constructors must create your hash tables with enough positions N
	// to hold the entries you will insert; you may experiment with primes N
	
	public DatabaseMine() {
		this.N = 2;
	} // here you pick suitable default N
	
	public DatabaseMine(int N) {
		this.N = N;
	} // here the N is given by the user
	
	public int hashFunction(String key) {
		int address=key.hashCode()%N;
		return (address>=0)?address:(address+N);
	}

	@Override
	public String save(String plainPassword, String encryptedPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String encryptedPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printStatistics() {
		// TODO Auto-generated method stub

	}

}
