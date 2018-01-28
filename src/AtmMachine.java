/* Rayan Osseiran
 * ConUHacks 2018
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class AtmMachine extends ReqCheck {

	private static float balance = 0; // starting balance at $0

	public static void main(String args[]) {

		// Implementing date and time for post action receipt
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy - H:mm").format(Calendar.getInstance().getTime());

		// Setting a pin, simulates an unrealistic registration process

		System.out.print("Welcome to the Generic Bank of Canada, " + "would you like to sign up for online banking? ");

		@SuppressWarnings("resource")
		Scanner scannerinput = new Scanner(System.in);
		String bankingQuery = scannerinput.next();

		if ((bankingQuery.equalsIgnoreCase("Yes") || bankingQuery.equalsIgnoreCase("Sure")) == false) {
			System.out.println("We're sorry to hear that. We look forward to serving you in the future.");
			System.exit(0);
		}

		System.out.println("Welcome, you will be asked to select a pin number. ");
		System.out.println(
				"For guidelines on selecting a pin, please type help onto the console. Alternatively, press enter to continue to registration.");

		String pinInfo = scannerinput.next();

		if (pinInfo.equalsIgnoreCase("help")) {
			generateHelp();
		} else {
		}

		System.out.println("Please enter the email associated with your account and a desired pin number");
		System.out.print("Email Address: ");
		String emailAddress = scannerinput.next();
		String pinStore = null;
		System.out.print("Pin Number: ");

		try {
			pinStore = scannerinput.next();
		} catch (Exception e) {
			System.out.println("Your pin is not valid. You will be kicked out of the session.");
		}

		int pinNum = Integer.parseInt(pinStore);

		// Checking to see if security requirements are satisfied

		if (pinStore.length() != 4 || hasDistinctDigits(pinNum) == false) {
			System.out.println("The pin you selected is not valid.");
			System.exit(0);
		}

		AccountGenerator(emailAddress, pinNum);
		int pinNumber = hm.get(emailAddress);

		System.out.println("Thank you for banking with us. Be sure to keep your new credentials in a safe place.");

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

			// 1. Withdrawal - Option available pre-ATM

			if (choice.equalsIgnoreCase("Withdraw") || choice.equals("1")) {
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
					System.out.println(timeStamp);
					System.out.print("Your balance is now: $" + balance + ". ");
					System.out.println("Thank you for banking with us");

				} else {
					System.out.println("You do not have sufficient funds. You will be kicked out of the session.");
					continue;
				}
			}

			// 2. Deposits

			else if (choice.equalsIgnoreCase("Deposit") || choice.equals("2")) {
				Float depositAmt;
				System.out.print("Enter an amount to deposit: ");

				try {
					depositAmt = scannerinput.nextFloat();
				} catch (Exception e) {
					System.out.println("Improper input. You will be kicked out of the session.");
					continue;
				}

				balance += depositAmt;
				System.out.println(timeStamp);
				System.out.println("Your balance is now: $" + balance + ". ");
				System.out.println("Thank you for banking with us.");
			}

			// 3. Balance

			else if (choice.equalsIgnoreCase("Balance") || choice.equals("3")) {
				System.out.println("As of " + timeStamp + ", your account balance (ID: " + emailAddress + ") is: $"
						+ balance + ".");
				System.out.println("Thank you for banking with us.");
			}

			else {
				System.out.println("Invalid input. You will be kicked out of the session.");
				continue;
			}
		}
	}
}