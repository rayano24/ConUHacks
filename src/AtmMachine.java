import java.util.Scanner;

/* Rayan Osseiran
 * ConUHacks 2018
 */

public class AtmMachine extends HolderAccount {

	private static float balance = 0; // starting balance at $0

	public static void main(String args[]) {
		
		// Setting a pin, simulates an unrealistic registration process

		System.out.print("Welcome to the Generic Bank of Canada, "
				+ "would you like to sign up for online banking? ");

		Scanner scannerinput = new Scanner(System.in);
		String bankingQuery = scannerinput.next();

		if ((bankingQuery.equalsIgnoreCase("Yes") || bankingQuery.equalsIgnoreCase("Sure")) == false) {
			System.out.println("We're sorry to hear that. We look forward to serving you in the future.");
			System.exit(0);
		}

		System.out.println("Welcome, please enter the email associated with your account and a desired 4-digit pin: ");
		
		System.out.print("Email Address: ");
		String emailAddress = scannerinput.next();
		String pinStore = null;
		System.out.print("Pin Number: ");
		try {
			pinStore = scannerinput.next();
		} catch (Exception e) {
			System.out.println("Your pin is not valid. You will be kicked out of the session.");
		}

		if (pinStore.length() != 4) {
			System.out.println("The pin you selected is not valid.");
		}

		int pinNum = Integer.parseInt(pinStore);
		AccountGenerator(emailAddress, pinNum);
		int pinNumber = hm.get(emailAddress);
		
		// Beginning of main ATM body

		while (true) {

			System.out.print("Please enter your pin: ");
			String pin = scannerinput.next();

			if (pinNumber != Integer.parseInt(pin)) {
				System.out.println("You have entered an invalid pin");
				continue;
				
			} 
			else {
				System.out.println("Please select an option:");
				System.out.println("1. Withdraw");
				System.out.println("2. Deposit");
				System.out.println("3. Balance");
			}
			
			String choice = scannerinput.next();

			// 1. Withdrawal 
			
			if (choice.equalsIgnoreCase("Withdraw")) {
				System.out.print("Enter an amount to withdraw: ");
				float withdrawalAmt;
				try {
					withdrawalAmt = scannerinput.nextFloat();
				} catch (Exception e) {
					System.out.println("Improper input. You will be kicked out of the session.");
					continue;
				}

				if (withdrawalAmt <= balance) {
					balance -= withdrawalAmt;
					System.out.print("Your balance is now: $");
					System.out.println(balance);
					System.out.println();

				} else {
					System.out.println("You do not have sufficient funds. You will be kicked out of the session");
					continue; 
				}
			}
			
			// 2. Deposits
			else if (choice.equalsIgnoreCase("Deposit")) {
				Float depositAmt;
				System.out.print("Enter an amount to deposit: ");
				try {
					depositAmt = scannerinput.nextFloat(); // include try catch
				} catch (Exception e) {
					System.out.println("Improper input. You will be kicked out of the session.");
					continue;
				}

				balance += depositAmt;
				System.out.print("Your balance is now: $");
				System.out.println(balance);
				System.out.println("Thank you for banking with us.");
			} 
	
			// 3. Balance
			else if (choice.equalsIgnoreCase("Balance")) {
				System.out.print("Account balance: $");
				System.out.println(balance);
				System.out.println("Thank you for banking with us.");
			}
			else {
				continue;
			}
		}
	}
}