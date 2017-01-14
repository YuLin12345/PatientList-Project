/*
 * @author Yu Lin CSCI 260 - SPRING 2016 - Patient List Project using GUI
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Patient {
	protected String name;
	protected String patientID;
	protected String address;
	protected int height = 0;
	protected double weight = 0;
	protected Date dob;
	protected Date div;
	protected Date dlv;
	protected LLNode list;

	public Patient(String patientID) {
		this.patientID = patientID;
	}

	public Patient(String patientID, String name, String address, int height, double weight, String dob, String div,
			String dlv) {

		SimpleDateFormat DateFormat = new SimpleDateFormat("mm-dd-yyyy");

		this.patientID = patientID;
		this.name = name;
		this.address = address;
		this.height = height;
		this.weight = weight;

		try {
			this.dob = DateFormat.parse(dob);
			this.div = DateFormat.parse(div);
			this.dlv = DateFormat.parse(dlv);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// Set patient name.
	public void setPatientName(String name) {
		this.name = name;
	}

	// Get patient name.
	public String getPatientName() {
		return name;
	}

	// Set patient ID.
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	// Get patient ID.
	public String getPatientID() {
		return patientID;
	}

	// Set patient address.
	public void setPatientAddress(String address) {
		this.address = address;
	}

	// Get patient address.
	public String getPatientAddress() {
		return address;
	}

	// Calculate height in feet.
	public int heightFeet() {
		return (height / 12);
	}

	// Calculate height in inches.
	public int heightInches() {
		return (height % 12);
	}

	// Set patient height.
	public void setHeight(int height) {
		this.height = height;
	}

	// Get patient height.
	public String getHeight() {
		return (heightFeet() + "ft" + " " + heightInches() + "in.");
	}

	// Set patient weight.
	public void setWeight(double weight) {
		this.weight = weight;
	}

	// Get patient weight.
	public double getWeight() {
		return weight;
	}

	// Set patient date of birth.
	public void setDOB(Date adob) {
		this.dob = adob;
	}

	// Set patient date of birth (month, day, year).
	public void setDOB(int iMonth, int iDay, int iYear) {
		Calendar cal = Calendar.getInstance();
		cal.set(iMonth, iDay, iYear);
		this.dob = cal.getTime();
	}

	// Get patient date of birth.
	public Date getDOB() {
		return dob;
	}

	// Set patient date of initial visit.
	public void setDiv(Date div) {
		this.div = div;
	}

	// Set patient date of initial visit (month, day, year)
	public void setDiv(int iMonth, int iDay, int iYear) {
		Calendar cal = Calendar.getInstance();
		cal.set(iMonth, iDay, iYear);
		this.div = cal.getTime();
	}

	// Get patient date of initial visit.
	public Date getDiv() {
		return div;
	}

	// Set patient date of last visit.
	public void setDlv(Date dlv) {
		this.dlv = dlv;
	}

	// Set patient date of last visit (month, day, year).
	public void setDlv(int iMonth, int iDay, int iYear) {
		Calendar cal = Calendar.getInstance();
		cal.set(iMonth, iDay, iYear);
		this.dlv = cal.getTime();
	}

	// Get patient date of last visit.
	public Date getDlv() {
		return dlv;
	}

	// Get patient age / calculate age.
	public int getAge() {
		Calendar today = Calendar.getInstance();
		Calendar birthDate = Calendar.getInstance();

		birthDate.setTime(dob);

		if (birthDate.after(today)) {
			throw new IllegalArgumentException("Cannot be born in the future.");
		}

		int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

		if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3)
				|| (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
			age--;
		} else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH))
				&& (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
			age--;
		}

		return age;
	}

	// Calculate how many year this patient has been a patient.
	public int yearAsPatient() {
		Calendar today = Calendar.getInstance();
		Calendar yearsAsPatient = Calendar.getInstance();

		yearsAsPatient.setTime(div);

		if (yearsAsPatient.after(today)) {
			throw new IllegalArgumentException("Cannot be born in the future.");
		}

		int yap = today.get(Calendar.YEAR) - yearsAsPatient.get(Calendar.YEAR);

		if ((yearsAsPatient.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3)
				|| (yearsAsPatient.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
			yap--;
		} else if ((yearsAsPatient.get(Calendar.MONTH) == today.get(Calendar.MONTH))
				&& (yearsAsPatient.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
			yap--;
		}
		return yap;
	}

	// Calculate when was the patients last visit.
	public int lastVisit() {
		Calendar dIVisit = Calendar.getInstance();
		Calendar dLVisit = Calendar.getInstance();

		dIVisit.setTime(div);
		dLVisit.setTime(dlv);

		int lastV = dLVisit.get(Calendar.YEAR) - dIVisit.get(Calendar.YEAR);
		lastV--;

		return (lastV + 1);
	}

	// Calculate if the patient is overdue.
	public String overdue() {
		if (lastVisit() > 3) {
			return "This patient is overdue.";
		} else {
			return "This patient is not overdue.";
		}
	}

	// Display patient name.
	public String patientToString() {
		return name + patientID;
	}

	@Override
	public String toString() {
		return ("\nPatient Name: " + name + "\nPatient ID: " + patientID + "\nAddress: " + address + "\nDate of Birth: "
				+ getDOB() + "\nAge: " + getAge() + "\nHeight: " + heightFeet() + "ft." + " " + heightInches() + "in."
				+ "\nWeight: " + weight + "lbs." + "\nYears as a patient: " + yearAsPatient()
				+ "\nYears since last vist: " + lastVisit() + "\n" + overdue());
	}
}