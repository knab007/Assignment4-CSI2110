import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Test2 {
	public static void main(String[] args) throws UnsupportedEncodingException{
		PasswordCracker testCracker = new PasswordCracker();
		DatabaseInterface database2 = new DatabaseMine();
		ArrayList<String> commonPass = new ArrayList<String>();
		commonPass.add("123456");
		commonPass.add("password");
		commonPass.add("12345678");
		commonPass.add("brady");
		testCracker.createDatabase(commonPass, database2);
		database2.printStatistics();
		String code = new String("F35D55B3ACF667911A679B44918F5D88B2400823");
		String discoverPassword = testCracker.crackPassword(code, database2);
		System.out.println("Decrypt: " + code + " Password: " + discoverPassword + ";");
	}
}