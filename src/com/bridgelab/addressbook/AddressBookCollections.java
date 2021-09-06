package com.bridgelab.addressbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AddressBookCollections {

	public int i = 0;
	public List<CollegeAddressBook> list = new ArrayList<CollegeAddressBook>();

	public void searchCity() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter City: ");
		String city = sc.nextLine();

//		for (int i = 0; i < list.size(); i++) {
//
//			if (city.equals(list.get(i).city)) {
//				System.out.println(list.get(i).firstName);
//			}
//		}
		
		list.stream().filter(p -> p.getCity().equals(city)).collect(Collectors.toList());
		list.forEach(System.out::println);
	}

	public void searchState() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter state: ");
		String state = sc.nextLine();

//		for (int i = 0; i < list.size(); i++) {
//			if (state.equals(list.get(i).state)) {
//				System.out.println(list.get(i).firstName);
//		}
//	}
		list.stream().filter(p -> p.getState().equals(state)).collect(Collectors.toList());
		list.forEach(System.out::println);

	}

	public void countPersons() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter State Name");
		String state = sc.nextLine();

		list.stream().filter(n -> n.equals(state)).forEach(System.out::println);
//		int count = 0;
//		for (int i = 0; i < list.size(); i++) {
//			if (state.equals(list.get(i).state)) {
//				count++;
//
//			}
//
//		}
//		System.out.println(count);
	}

	
	public boolean checkDuplicate(String firstname) {

		for (int i = 0; i < list.size(); i++) {

//			if (firstname.equals(list.get(i).firstName)) {
//				return false;
//			}
			if (list.stream().anyMatch(n -> n.equals(firstname))) {
				return false;
			}
		}
		return true;
	}

	public void addContact() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of entries: ");
		int record = sc.nextInt();
		for (int i = 0; i < record; i++) {
			System.out.println("Enter First Name : ");
			String firstname = sc.next();
			System.out.println("Enter Last Name : ");
			String lastname = sc.next();
			System.out.println("Enter Address : ");
			String address = sc.next();
			System.out.println("Enter City : ");
			String city = sc.next();
			System.out.println("Enter State : ");
			String state = sc.next();
			System.out.println("Enter ZipCode : ");
			String zipcode = sc.next();
			System.out.println("Enter Phone Number : ");
			String phonenum = sc.next();
			System.out.println("Enter Email id : ");
			String email = sc.next();

			if (this.checkDuplicate(firstname)) {

				CollegeAddressBook addressBookCollections = new CollegeAddressBook(firstname, lastname, address, city,
						state, zipcode, phonenum, email);
				list.add(addressBookCollections);
				System.out.println("Contact added ");
			} else {
				System.out.println("Duplicate found");
			}
		}
	}

	public void showContact() {
		list.stream().forEach(System.out::println);
//		Iterator<ContactAddressBook> itr = list.iterator();
//		while (itr.hasNext()) {
//			System.out.println(itr.next());
//		}
	}

	public void deleteContact() {
		this.showContact();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter First Name to delete ");
		String name = sc.next();
		for (int i = 0; i < list.size(); i++) {
			if (name.equals(list.get(i).firstName)) {
				list.remove(i);
			}
		}
	}

}