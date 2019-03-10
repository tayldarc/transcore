import java.util.Random;
import java.util.Scanner;


public class thePlan {
	
	public static String code;
	

	public static void main(String[] args) {

		//User Profiles
		String[] users = {"Bob", "Susan", "Joe"};
		String[] email = {"bob@gmail.com", "susan@gmail.com", "joe@gmail.com" };
		String[] pass = {"password", "pass_word", "12345"};
		int[] status = {0,0};
		int[] counter = {0,0};
		//Set Amount Of User LogIn Per Day Maintain Accountability
		int[] redFlag = {2,3,4};
		
		String logOut = "";

		while(true) {
			//Get User Info
			Scanner myObj = new Scanner(System.in);
			System.out.println("Welcome to TransCore!\n");
			System.out.println("Enter username");
			String userName = myObj.nextLine();  // Read User name
			System.out.println("Thank You " + userName + " Please Enter Password");  // Output user input
			String password = myObj.nextLine();  // Read Password
			String storedCode = "";

			//Verify Info
			//Take Username and Password and run it on the database to see existing user
			for(int i = 0; i < users.length; i++) {
				if(userName.equals(users[i])) {
					//System.out.println("Program Works");
					while(!(password.equals(pass[i]))) {
						System.out.println("Sorry " + userName + " Your Password Is Incorrect.\n\nPlease Enter Your Password Again.");
						password = myObj.nextLine();  // Read Password
					}
					//works
					if(password.equals(pass[i]) && status[i] == 0) {
						System.out.println("Now You Are Connected To TransCore.");
						//Send Email You Are Signed In TimeStamp + Transcore + Location + Current Session
						status[i] = 1;
						counter[i] += 1;
						if(counter[i] == redFlag[i]+1) {
							System.out.println(users[i] + " Please Contact TransCore For Customer Support. XX12454");
						}
						//Check
						do {
							System.out.println("Would You Like To Log Out? (Y / N)");
							logOut = myObj.nextLine();
							if (logOut.equalsIgnoreCase("y") || logOut.equalsIgnoreCase("yes")) {
								status[i] = 0;
								break;
							}
							if (logOut.equalsIgnoreCase("n") || logOut.equalsIgnoreCase("no")) {
								break;
							}
						} while (true);

					}
					else {
						activateDevice(email[i]);
						System.out.println("\nPlease Enter Verification Code: ");
						storedCode = myObj.nextLine();
						if (storedCode.equals(code)) {
							status[i] = 0;
						}
					}	
				}
				else {
					if(i<=users.length) {
						continue;
					}
					else {
						System.out.println("Please Register");
						break;
					}
				}
			}

		}

	}
	
	public static void activateDevice(String i) {
		System.out.println("Sorry, You May Be Logged In To Another Device. To Activate This Device Please Enter Email Authentication Code: " + 
		getCode() + " (This Code Is In Your " + i + " email.");
		/*Send Email Code To User and to TransCore and It Expires Every 5 Minutes ... 
		Code Will Work Like A Virus, If She Opens Her Email On The Computer It Will
		Automatically Authenticate Her Use Through Permission: Read Device Computer 
		and Send Data To TransCore - User will have to consent?*/
		
	}
	
	protected static String getCode() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%&";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of Verification Code
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        code = saltStr;
        return saltStr;

    }

}
