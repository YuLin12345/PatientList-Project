/*
 * @author Yu Lin CSCI 260 - SPRING 2016 - Patient List Project using GUI
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class UnsortedList implements ListInterface {
	protected int numElements;
	protected LLNode currentPos;
	protected boolean found;
	protected LLNode location;
	protected LLNode previous;
	protected LLNode tail;
	protected LLNode list;
	protected Patient apatient;

	public UnsortedList() {
		numElements = 0;
		list = null;
		tail = null;
		currentPos = null;
	}

	@Override
	public void add(Patient element) {
		LLNode newNode = new LLNode(element);
		if (numElements == 0) {
			list = newNode;
			tail = list;
		} else if (numElements == 1) {
			list.setLink(newNode);
			tail = newNode;
		} else {
			tail.setLink(newNode);
			tail = newNode;
		}
		numElements++;
	}

	protected void find(Patient element) {
		location = list;
		found = false;

		while (location != null) {
			if (location.getInfo().equals(element)) {
				found = true;
				return;
			} else {
				previous = location;
				location = location.getLink();
			}
		}
	}

	@Override
	public int size() {
		return numElements;
	}

	@Override
	public boolean contains(Patient element) {
		find(element);

		return found;
	}

	@Override
	public boolean remove(Patient element) {
		find(element);

		if (found) {
			if (list == location) {
				list = list.getLink();
			} else {
				previous.setLink(location.getLink());
			}

			numElements--;
		}
		return found;
	}

	public String removePatient(String element) {
		LLNode node = list;
		LLNode previous = null;
		LLNode location = node;

		while (node != null) {

			if (((Patient) node.getInfo()).getPatientID().equalsIgnoreCase(element)) {
				if (list == location) {
					list = list.getLink();
				} else {
					previous.setLink(location.getLink());
				}

				numElements--;

				node = node.getLink();
				return "This patient has been removed.";
			} else {
				node = node.getLink();
			}
		}
		return "We could not find this patient on the list.";
	}

	@Override
	public Patient get(Patient element) {
		find(element);

		if (found) {
			return (Patient) location.getInfo();
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		LLNode currNode = list;
		String listString = "Patient List:";

		while (currNode != null) {
			listString = listString + currNode.getInfo() + "\n";
			currNode = currNode.getLink();
		}
		return listString;
	}

	@Override
	public void reset() {
		currentPos = list;
	}

	@Override
	public Patient getNext() {
		Object next = currentPos.getInfo();

		if (currentPos.getLink() == null) {
			currentPos = list;
		} else {
			currentPos = currentPos.getLink();
		}
		return (Patient) next;
	}

	public double getAverageAge() {
		LLNode youngestNode = list;
		double averageAge = 0;
		double sum = 0;

		while (youngestNode != null) {
			sum = sum + ((Patient) youngestNode.getInfo()).getAge();
			youngestNode = youngestNode.getLink();
		}

		averageAge = (sum / size());

		return averageAge;
	}

	public String getYoungestPatient() {
		LLNode youngestNode = list;
		int intialAge = 100;
		int compare = 0;
		String youngestPatient = null;
		while (youngestNode != null) {
			compare = ((Patient) youngestNode.getInfo()).getAge();

			if (intialAge > compare) {
				intialAge = compare;
				youngestNode = youngestNode.getLink();
			} else {
				youngestNode = youngestNode.getLink();
			}
		}
		youngestNode = list;
		youngestPatient = Integer.toString(intialAge);

		while (youngestNode != null) {
			if (((Patient) youngestNode.getInfo()).getAge() == intialAge) {
				youngestPatient = "" + ((Patient) youngestNode.getInfo()).toString();
				youngestNode = youngestNode.getLink();
			} else {
				youngestNode = youngestNode.getLink();
			}
		}
		return youngestPatient;
	}

	public void returnPatientID(String element) {
		LLNode node = list;
		while (node != null) {
			if (((Patient) node.getInfo()).getPatientID().equalsIgnoreCase(element)) {
				JOptionPane.showMessageDialog(null, ((Patient) node.getInfo()).toString());
				node = node.getLink();
			} else {
				node = node.getLink();
			}
		}
	}

	public String checkPatientID(String element) {
		LLNode node = list;
		while (node != null) {
			if (((Patient) node.getInfo()).getPatientID().equalsIgnoreCase(element)) {
				node = node.getLink();
				return "This ID is currently being used.";
			} else {
				node = node.getLink();
			}
		}
		return "This ID is currently not being in use.";
	}

	public void overdue() {
		LLNode node = list;
		int lastVisit = 0;

		while (node != null) {
			lastVisit = ((Patient) node.getInfo()).lastVisit();

			if (lastVisit > 3) {
				JOptionPane.showMessageDialog(null, node.getInfo());
				node = node.getLink();
			} else {
				node = node.getLink();
			}
		}
	}

	public void printTxt() throws IOException {
		LLNode patientNode = list;
		String newPatientName = null;
		String newPatientID = null;
		String newPatientAddress = null;
		int newPatientAge = 0;
		Date newPatientDOB = null;
		Date newInitialVisit = null;
		Date newLastVisit = null;
		double newPatientHeight = 0;
		double newPatientWeight = 0;

		PrintWriter writer = null;

		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File fileName = fileChooser.getSelectedFile();

			try {
				FileWriter fw = new FileWriter(fileName, true);
				writer = new PrintWriter(fw);

				while (patientNode != null) {
					newPatientName = ((Patient) patientNode.getInfo()).name;
					newPatientID = ((Patient) patientNode.getInfo()).patientID;
					newPatientAddress = ((Patient) patientNode.getInfo()).address;
					newPatientDOB = ((Patient) patientNode.getInfo()).dob;
					newPatientAge = ((Patient) patientNode.getInfo()).getAge();
					newPatientHeight = ((Patient) patientNode.getInfo()).height;
					newPatientWeight = ((Patient) patientNode.getInfo()).weight;
					newInitialVisit = ((Patient) patientNode.getInfo()).div;
					newLastVisit = ((Patient) patientNode.getInfo()).dlv;
					writer.println("Patient Name: " + newPatientName);
					writer.println("Patient ID: " + newPatientID);
					writer.println("Address: " + newPatientAddress);
					writer.println("Date of Birth: " + newPatientDOB);
					writer.println("Age: " + newPatientAge);
					writer.println("Height: " + newPatientHeight);
					writer.println("Weight: " + newPatientWeight);
					writer.println("Initial Visit: " + newInitialVisit);
					writer.println("Last Visit: " + newLastVisit);
					writer.println("");
					patientNode = patientNode.getLink();
				}
				writer.close();

				System.exit(0);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
}