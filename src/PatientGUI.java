/*
 * @author Yu Lin CSCI 260 - SPRING 2016 - Patient List Project using GUI
 */

package Patient;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PatientGUI {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PatientGUI window = new PatientGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PatientGUI() {
		initialize();
	}

	// Frame
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Patient GUI"); // Frame title.
		frame.setBounds(100, 100, 550, 362); // Frame size.
		frame.setResizable(false); // Disable frame resizing.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		// File panel.
		JPanel filePanel = new JPanel();
		frame.getContentPane().add(filePanel, "file");
		filePanel.setLayout(null);

		// Patient panel.
		JPanel patientPanel = new JPanel();
		frame.getContentPane().add(patientPanel, "patient");
		patientPanel.setLayout(null);

		// Add patient panel.
		JPanel addPanel = new JPanel();
		frame.getContentPane().add(addPanel, "add");
		addPanel.setLayout(null);

		// Remove patient panel.
		JPanel removePanel = new JPanel();
		frame.getContentPane().add(removePanel, "remove");
		removePanel.setLayout(null);

		// Patient Info patient panel.
		JPanel infoPanel = new JPanel();
		frame.getContentPane().add(infoPanel, "remove");
		infoPanel.setLayout(null);

		JLabel lblInfoID = new JLabel("Patient ID:");
		lblInfoID.setBounds(127, 110, 64, 14);
		infoPanel.add(lblInfoID);

		textInfoID = new JTextField();
		textInfoID.setBounds(214, 107, 200, 20);
		infoPanel.add(textInfoID);
		textInfoID.setColumns(10);

		JLabel lblPatientID = new JLabel("PatientID:");
		lblPatientID.setBounds(137, 106, 55, 14);
		removePanel.add(lblPatientID);

		textPatientID = new JTextField();
		textPatientID.setBounds(215, 103, 190, 20);
		removePanel.add(textPatientID);
		textPatientID.setColumns(10);

		JLabel lblUserID = new JLabel("PatientID:");
		lblUserID.setBounds(40, 70, 60, 14);
		addPanel.add(lblUserID);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(40, 100, 60, 14);
		addPanel.add(lblName);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(40, 130, 70, 14);
		addPanel.add(lblAddress);

		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(40, 160, 46, 14);
		addPanel.add(lblHeight);

		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(40, 190, 46, 14);
		addPanel.add(lblWeight);

		JLabel lblDateBirth = new JLabel("Date of Birth (mm-dd-yyyy):");
		lblDateBirth.setBounds(40, 220, 180, 14);
		addPanel.add(lblDateBirth);

		JLabel lblDateIV = new JLabel("Initial Visit (mm-dd-yyyy):");
		lblDateIV.setBounds(40, 250, 180, 14);
		addPanel.add(lblDateIV);

		JLabel lblDateLV = new JLabel("Last Visit (mm-dd-yyyy):");
		lblDateLV.setBounds(40, 280, 180, 14);
		addPanel.add(lblDateLV);

		JLabel lblAddTitle = new JLabel("Add New Patient To List");
		lblAddTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddTitle.setBounds(172, 22, 223, 44);
		addPanel.add(lblAddTitle);

		textID = new JTextField();
		textID.setBounds(110, 67, 150, 20);
		addPanel.add(textID);
		textID.setColumns(10);

		textName = new JTextField();
		textName.setBounds(110, 95, 150, 20);
		addPanel.add(textName);
		textName.setColumns(10);

		textAddress = new JTextField();
		textAddress.setBounds(110, 127, 150, 20);
		addPanel.add(textAddress);
		textAddress.setColumns(10);

		textHeight = new JTextField();
		textHeight.setBounds(110, 157, 150, 20);
		addPanel.add(textHeight);
		textHeight.setColumns(10);

		textWeight = new JTextField();
		textWeight.setBounds(110, 187, 150, 20);
		addPanel.add(textWeight);
		textWeight.setColumns(10);

		textBirth = new JTextField();
		textBirth.setBounds(200, 217, 140, 20);
		addPanel.add(textBirth);
		textBirth.setColumns(10);

		textIV = new JTextField();
		textIV.setBounds(200, 247, 140, 20);
		addPanel.add(textIV);
		textIV.setColumns(10);

		textLV = new JTextField();
		textLV.setBounds(200, 277, 140, 20);
		addPanel.add(textLV);
		textLV.setColumns(10);

		// File title label.
		JLabel lblTitle = new JLabel("Project 1 - Patient List");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitle.setBounds(132, 15, 295, 50);
		filePanel.add(lblTitle);

		// File button use to open the file.
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				{
					JFileChooser fileChooser = new JFileChooser();
					int returnValue = fileChooser.showOpenDialog(null);

					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File fileName = fileChooser.getSelectedFile();
						FileReader fr = null;

						try {
							fr = new FileReader(fileName);
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, fileName + " is not found.");
							// e1.printStackTrace();
						}
						char[] a = new char[50];
						try {
							fr.read(a);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						for (char c : a) {
							try {
								fr.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}

					UnsortedList patientList = new UnsortedList();

					// Add button, use to add new patients.
					JButton btnAdd = new JButton("Add");
					btnAdd.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							File fileName = fileChooser.getSelectedFile();
							PrintWriter writer = null;
							FileWriter fw = null;

							try {
								fw = new FileWriter(fileName, true);
							} catch (IOException e1) {
								e1.printStackTrace();
							}

							writer = new PrintWriter(fw);

							String patientID = null;
							String name = null;
							String address = null;
							int height = 0;
							double weight = 0;
							Date dateBirth = null;
							Date dateIV = null;
							Date dateLV = null;
							Patient aPatient = new Patient(patientID);

							// Check if the text fields are empty.
							if (textID.getText().equalsIgnoreCase("") || textName.getText().equalsIgnoreCase("")
									|| textAddress.getText().equalsIgnoreCase("")
									|| textHeight.getText().equalsIgnoreCase("")
									|| textWeight.getText().equalsIgnoreCase("")
									|| textBirth.getText().equalsIgnoreCase("") || textIV.getText().equalsIgnoreCase("")
									|| textLV.getText().equalsIgnoreCase("")) {
								JOptionPane.showMessageDialog(null, "All fields must be filled in.");
							} else {
								// Check if the patientID is being used.
								if (patientList.checkPatientID(textID.getText())
										.equalsIgnoreCase("This ID is currently being used.")) {
									JOptionPane.showMessageDialog(null, "PatientID already exist. Enter a new ID.");
								} else {

									SimpleDateFormat df = new SimpleDateFormat("mm-dd-yyyy");
									df.setLenient(false);

									Calendar today = Calendar.getInstance();
									Calendar bd = Calendar.getInstance();
									Calendar iv = Calendar.getInstance();
									Calendar lv = Calendar.getInstance();

									String stringBirth = textBirth.getText();
									String stringIV = textIV.getText();
									String stringLV = textLV.getText();

									// Check if Birthdate is formated correctly.
									try {
										bd.setTime(df.parse(stringBirth));
									} catch (ParseException e1) {
										JOptionPane.showMessageDialog(null, "Birthdate incorrect format. (mm-dd-yyyy)");
										// e1.printStackTrace();
									}

									// Check if Initial Visit is formated
									// correctly.
									try {
										iv.setTime(df.parse(stringIV));
									} catch (ParseException e1) {
										JOptionPane.showMessageDialog(null,
												"Initial Visit date incorrect format. (mm-dd-yyyy)");
										// e1.printStackTrace();
									}

									// Check if Last Visit is formated
									// correctly.
									try {
										lv.setTime(df.parse(stringLV));
									} catch (ParseException e1) {
										JOptionPane.showMessageDialog(null,
												"Last Visit date incorrect format. (mm-dd-yyyy)");
										// e1.printStackTrace();
									}

									// Check if birthday is in the future.
									if (bd.after(today)) {
										JOptionPane.showMessageDialog(null, "Birthday cannot be in the future.");
									}
									// Check if initial visit is in the future.
									else if (iv.after(today)) {
										JOptionPane.showMessageDialog(null, "Initial Visit cannot be in the future.");
									} else {
										// Get ID from text field.
										String PatientID = textID.getText();
										aPatient.setPatientID(PatientID);

										// Get name from text field.
										name = textName.getText();
										aPatient.setPatientName(name);

										// Get address from text field.
										address = textAddress.getText();
										aPatient.setPatientAddress(address);

										try {
											dateBirth = df.parse(stringBirth);
											aPatient.setDOB(dateBirth);
										} catch (Exception e1) {
											e1.printStackTrace();
										}

										height = Integer.parseInt(textHeight.getText());
										aPatient.setHeight(height);

										weight = Double.parseDouble(textWeight.getText());
										aPatient.setWeight(weight);

										try {
											dateIV = df.parse(stringIV);
											aPatient.setDiv(dateIV);
										} catch (Exception e1) {
											e1.printStackTrace();
										}

										try {
											dateLV = df.parse(stringLV);
											aPatient.setDlv(dateLV);
										} catch (Exception e1) {
											e1.printStackTrace();
										}

										patientList.add(aPatient);
										writer.close();

										addPanel.setVisible(false);
										patientPanel.setVisible(true);
									}
								}
							}
						}
					});
					btnAdd.setBounds(358, 125, 130, 49);
					addPanel.add(btnAdd);

					// Quit button, let the user save the file upon exiting.
					JButton btnQuit = new JButton("Quit");
					btnQuit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								patientList.printTxt();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							System.exit(0);
						}
					});
					btnQuit.setBounds(270, 260, 200, 23);
					patientPanel.add(btnQuit);

					// Display List Button
					JButton btnDisplayList = new JButton("Display List");
					btnDisplayList.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showMessageDialog(null, patientList.toString());
						}
					});
					btnDisplayList.setBounds(60, 50, 200, 23);
					patientPanel.add(btnDisplayList);

					// Display Show Notification List.
					JButton btnShowNotificationfList = new JButton("Show Notification List");
					btnShowNotificationfList.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							patientList.overdue();
						}
					});
					btnShowNotificationfList.setBounds(60, 260, 200, 23);
					patientPanel.add(btnShowNotificationfList);

					// Display Show Info For Youngest Patient.
					JButton btnShowInfoForYoungestPatient = new JButton("Show Info For Youngest Patient");
					btnShowInfoForYoungestPatient.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showMessageDialog(null,
									"Youngest patient is: " + patientList.getYoungestPatient());
						}
					});
					btnShowInfoForYoungestPatient.setBounds(270, 120, 200, 23);
					patientPanel.add(btnShowInfoForYoungestPatient);

					// Display Show Average Patient Age.
					JButton btnShowAveragePatientAge = new JButton("Show Average Patient Age");
					btnShowAveragePatientAge.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showMessageDialog(null,
									"Average patient age is: " + patientList.getAverageAge());
						}
					});
					btnShowAveragePatientAge.setBounds(60, 120, 200, 23);
					patientPanel.add(btnShowAveragePatientAge);

					// Remove button for removing patient by ID.
					JButton btnRemove = new JButton("Remove");
					btnRemove.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String aPatient = textPatientID.getText();
							JOptionPane.showMessageDialog(null, patientList.removePatient(aPatient));
						}
					});
					btnRemove.setBounds(143, 172, 126, 72);
					removePanel.add(btnRemove);

					// Check button for checking patient by ID.
					JButton btnInfoCheck = new JButton("Check");
					btnInfoCheck.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String patientID = textInfoID.getText();
							patientList.returnPatientID(patientID.toString());
						}
					});
					btnInfoCheck.setBounds(127, 174, 139, 83);
					infoPanel.add(btnInfoCheck);
				}

				filePanel.setVisible(false);
				patientPanel.setVisible(true);
			}
		});
		btnOpen.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnOpen.setBounds(132, 132, 280, 115);
		filePanel.add(btnOpen);

		// Open show patient info panel.
		JButton btnShowInfoForPatient = new JButton("Show Info For Patient");
		btnShowInfoForPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				patientPanel.setVisible(false);
				infoPanel.setVisible(true);
			}
		});
		btnShowInfoForPatient.setBounds(270, 50, 200, 23);
		patientPanel.add(btnShowInfoForPatient);

		// Open add new patient panel.
		JButton btnAddNewPatient = new JButton("Add New Patient");
		btnAddNewPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPanel.setVisible(true);
				patientPanel.setVisible(false);
			}
		});
		btnAddNewPatient.setBounds(60, 190, 200, 23);
		patientPanel.add(btnAddNewPatient);

		// Open delete patient info panel.
		JButton btnDeleteAPatient = new JButton("Delete A Patient");
		btnDeleteAPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				patientPanel.setVisible(false);
				removePanel.setVisible(true);
			}
		});
		btnDeleteAPatient.setBounds(270, 190, 200, 23);
		patientPanel.add(btnDeleteAPatient);

		// Cancel button for patient add panel.
		JButton btnAddCancel = new JButton("Cancel");
		btnAddCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPanel.setVisible(false);
				patientPanel.setVisible(true);
			}
		});
		btnAddCancel.setBounds(358, 216, 130, 49);
		addPanel.add(btnAddCancel);

		// Cancel button for patient remove panel.
		JButton btnRemoveCancel = new JButton("Cancel");
		btnRemoveCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel.setVisible(false);
				patientPanel.setVisible(true);
			}
		});
		btnRemoveCancel.setBounds(279, 172, 126, 72);
		removePanel.add(btnRemoveCancel);

		JLabel lblRemoveTitle = new JLabel("Remove Patient From List");
		lblRemoveTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRemoveTitle.setBounds(160, 31, 240, 35);
		removePanel.add(lblRemoveTitle);

		// Cancel button for patient info panel.
		JButton btnInfoCancel = new JButton("Cancel");
		btnInfoCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				infoPanel.setVisible(false);
				patientPanel.setVisible(true);
			}
		});
		btnInfoCancel.setBounds(295, 174, 139, 83);
		infoPanel.add(btnInfoCancel);

		JLabel lblInfoTitle = new JLabel("Check Patient Info by ID");
		lblInfoTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInfoTitle.setBounds(150, 27, 227, 45);
		infoPanel.add(lblInfoTitle);
	}

	private JFrame frame;
	private JTextField textID;
	private JTextField textName;
	private JTextField textAddress;
	private JTextField textHeight;
	private JTextField textWeight;
	private JTextField textBirth;
	private JTextField textIV;
	private JTextField textLV;
	private JTextField textPatientID;
	private JTextField textInfoID;
}