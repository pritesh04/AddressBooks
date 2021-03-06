package com.bridgelab.addressbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;

public class AddressBookCollections {

	public int i = 0;
	public List<CollegeAddressBook> list = new ArrayList<CollegeAddressBook>();

	public void searchCity() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name");
		String name = sc.next();
		System.out.println("Enter City: ");
		String city = sc.nextLine();

//		for (int i = 0; i < list.size(); i++) {
//
//			if (city.equals(list.get(i).city)) {
//				System.out.println(list.get(i).firstName);
//			}
//		}

		list.stream().filter(p -> (p.city.equals(city) && p.firstName.equals(name))).collect(Collectors.toList());
		list.forEach(System.out::println);
	}

	public void searchState() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name");
		String name = sc.next();
		System.out.println("Enter state: ");
		String state = sc.nextLine();

//		for (int i = 0; i < list.size(); i++) {
//			if (state.equals(list.get(i).state)) {
//				System.out.println(list.get(i).firstName);
//		}
//	}
		list.stream().filter(p -> (p.state.equals(state) && p.firstName.equals(name))).collect(Collectors.toList());
		list.forEach(System.out::println);

	}

	public void countPersons() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter State Name");
		String stateName = sc.nextLine();

		System.out.println(list.stream().filter(n -> n.state.equals(stateName)).count());
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

	public void addContact() throws IOException {
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
				this.writeDataInFile();
			} else {
				System.out.println("Duplicate found");
			}
		}
	}

	public void showContact() {
		list.stream().forEach(System.out::println);
	}
//		Iterator<ContactAddressBook> itr = list.iterator();
//		while (itr.hasNext()) {
//			System.out.println(itr.next());
//		}

	public void viewPersons() {
		Scanner sc = new Scanner(System.in);
		System.out.println("For City press 1 and  for state press 2");
		int response = sc.nextInt();
		switch (response) {
		case 1:
			System.out.println("Enter state ");
			String state = sc.nextLine();
			list.stream().filter(p -> p.equals(state)).collect(Collectors.toList());
			list.forEach(System.out::println);
			break;
		case 2:
			System.out.println("Enter city ");
			String city = sc.nextLine();
			list.stream().filter(p -> p.equals(city)).collect(Collectors.toList());
			list.forEach(System.out::println);
			break;
		default:
			System.out.println("Wrong input");
		}

	}

	public void sortByNames() {
		List<CollegeAddressBook> namesList = list.stream()
				.sorted(Comparator.comparing(CollegeAddressBook::getFirstName)).collect(Collectors.toList());
		namesList.forEach(System.out::println);
	}

	public void sortBy() {
		System.out.println("1.city, 2.state, 3.Zipcode");
		Scanner sc = new Scanner(System.in);
		int response = sc.nextInt();
		switch (response) {

		case 1:
			List<CollegeAddressBook> cityList = list.stream().sorted(Comparator.comparing(CollegeAddressBook::getCity))
					.collect(Collectors.toList());
			cityList.forEach(System.out::println);
			break;
		case 2:

			List<CollegeAddressBook> stateList = list.stream()
					.sorted(Comparator.comparing(CollegeAddressBook::getState)).collect(Collectors.toList());
			stateList.forEach(System.out::println);
			break;
		case 3:
			List<CollegeAddressBook> zipList = list.stream().sorted(Comparator.comparing(CollegeAddressBook::getZip))
					.collect(Collectors.toList());
			zipList.forEach(System.out::println);
			break;
		default:
			System.out.println("Wrong input");
			break;
		}
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

	public void writeDataInFile() throws IOException {

		ObjectOutputStream fos = new ObjectOutputStream(new FileOutputStream("F:\\PRITESH\\text.txt"));
		fos.writeObject(list);
		System.out.println("Written Succesfully");
		fos.close();
	}

	public void readDataFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream("F:\\PRITESH\\text.txt"));
		List<CollegeAddressBook> book = (List<CollegeAddressBook>) is.readObject();
		for (int i = 0; i < book.size(); i++) {
			System.out.println(book);
		}
		is.close();

	}

	private void writeAllExample() throws IOException {
		String csv = "F:\\PRITESH\\book.csv";
		CSVWriter writer = new CSVWriter(new FileWriter(csv));
		List<String[]> listCsv = new ArrayList<>();
		listCsv.add(new String[] { "FirstName", "LastName", "Address", "City", "State", "ZipCode", "PhoneNumber",
				"EmailID" });
		for (int i = 0; i < list.size(); i++) {
			listCsv.add(new String[] { list.get(i).firstName, list.get(i).lastName, list.get(i).address,
					list.get(i).city, list.get(i).state, list.get(i).zip, list.get(i).phoneNumber, list.get(i).email });
		}
		writer.writeAll(listCsv);
		writer.close();

	}

	public void readDataFromCSVFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream("F:\\PRITESH\\text.txt"));
		List<CollegeAddressBook> book = (List<CollegeAddressBook>) is.readObject();
		for (int i = 0; i < book.size(); i++) {
			System.out.println(book);
		}

		is.close();

	}

	public void writeDataInFileUsingJson() throws IOException {
		Gson gson = new Gson();
		List<String> jsonList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			jsonList.add(gson.toJson(list.get(i)));
		}
		ObjectOutputStream fos = new ObjectOutputStream(new FileOutputStream("F:\\PRITESH\\abc.json"));
		fos.writeObject(jsonList);
		System.out.println("Written Succesfully");
		fos.close();
	}

	public void readDataFromJsonFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream("F:\\PRITESH\\abc.json"));
		List<CollegeAddressBook> book = (List<CollegeAddressBook>) is.readObject();
		for (int i = 0; i < book.size(); i++) {
			System.out.println(book);
		}

		is.close();

	}

}
