package com.bridgelab.addressbook;

import java.util.Scanner;

public class AddressBookRunner {
	public static void main(String[] args) {

		System.out.println("Welcome To Address Book Program");
		AddressBookCollections book = new AddressBookCollections();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter Choice: ");
			System.out.println(
					"1. Add Contact , 2. Edit Contact , 3. Show Contacts , 4. Search By City  , 5. Search By State ,  6. View Persons  ,7. Count Persons,   8. Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				book.addContact();
				break;
			case 2:
				book.deleteContact();
				break;
			case 3:
				book.showContact();
				break;
			case 4:
				book.searchCity();
				break;
			case 5:
				book.searchState();
				break;
			case 6:
				book.viewPersons();
				break;
			case 7:
				book.countPersons();
				break;

			default:
				System.out.println("Enter Correct Choice");
				break;
			}
		}

	}
}
