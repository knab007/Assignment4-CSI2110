// LEYAO LI
// 8776915
// Assignment 4 Part 2

public class DatabaseMine implements DatabaseInterface {
	int N; 
	// this is a prime number that gives the number of addresses
	// these constructors must create your hash tables with enough positions N
	// to hold the entries you will insert; you may experiment with primes N

	int size;
	// This is the amount of entries in the hash table
	// @N: capacity or number of indexes
	// @len: size
	// Load factor = @size / @N

	int nProbes;
	// number of probes.
	// Thus the average number of probes = @nProbes / @size

	int nDisp;
	// number of displacements:
	// amount of elements suck that
	// index[elem] != hashFunc[elem]

	String[] keys;
	String[] vals;
	
	
	public DatabaseMine(int N) {
		this.N = N;
		this.keys = new String[N];
		this.vals = new String[N];
	} // here the N is given by the user. Note that N should be a large prime number

	public DatabaseMine() {
		this(37);
	} // here you pick suitable default N
	
	// private:
	public int hashFunction(String key) {
		int address=key.hashCode()%N;
		return (address>=0)?address:(address+N);
	}

	@Override
	public String save(String plainPassword, String encryptedPassword) {
		// TODO Auto-generated method stub

		// key: encryptedPassword
		// val: plainPassword

		int hashval = this.hashFunction(encryptedPassword);
		int idx = 0;
		int offset = 0;
		String oldval = null; // not null only if SHA has already exist

		if (this.keys[hashval] != null) {
			this.nDisp += 1;
		}
		// start probling, i.e., finding a place for this element to sleep...
		while (this.keys[(idx = (hashval + offset) % this.N)] != null) {
			offset += 1;
			if (offset > this.N) {
				// buckets full. Nowhere to sleep
				return plainPassword;
			}
		}
		this.keys[idx] = encryptedPassword;
		this.vals[idx] = plainPassword;
		this.size += 1;
		this.nProbes += (offset + 1);

		return oldval;
	}

	@Override
	public String decrypt(String encryptedPassword) {
		// TODO Auto-generated method stub
		int hashval = this.hashFunction(encryptedPassword);
		int offset = 0;
		int idx;
		while (!this.keys[(idx = (hashval + offset) % this.N)].equals(encryptedPassword)) {
			offset += 1;
			if (offset > this.N) {
				// not found
				return "";
			}
		}
		return this.vals[idx];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public void printStatistics() {
		// TODO Auto-generated method stub
		System.out.println("*** DatabaseMine Statistics ***");
		System.out.println("Size is " + this.size + " passwords");
		System.out.println("Number of Indexes is " + this.N);
		System.out.println("Load Factor is " + (double)(this.size) / this.N);
		System.out.println("Average Number of Probes is " + (double)(this.nProbes) / this.size);
		System.out.println("Number of displacements (due to collisions) " + this.nDisp);
		System.out.println("*** End DatabaseMine Statistics ***");
	}

}