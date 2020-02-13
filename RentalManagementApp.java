package ijs_sprint1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class RentalManagementApp {

	private JFrame frmRentalLocationManager;
	private JTable tblLocations;
	private List<RentalLocations> locationList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentalManagementApp window = new RentalManagementApp();
					window.frmRentalLocationManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RentalManagementApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Arial", Font.BOLD, 20));
		
		frmRentalLocationManager = new JFrame();
		frmRentalLocationManager.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 22));
		frmRentalLocationManager.setTitle("Rental Location Manager");
		frmRentalLocationManager.setBounds(100, 100, 800, 500);
		frmRentalLocationManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 22));
		frmRentalLocationManager.getContentPane().add(scrollPane, BorderLayout.CENTER);

		tblLocations = new JTable();
		tblLocations.setFont(new Font("Tahoma", Font.PLAIN, 22));
		tblLocations.setRowHeight(30);
		scrollPane.setViewportView(tblLocations);
		
		JButton btnResetView = new JButton("Reset View");
		btnResetView.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnResetView.addActionListener(e -> {
			updateTable(locationList);
		});
		frmRentalLocationManager.getContentPane().add(btnResetView, BorderLayout.SOUTH);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		frmRentalLocationManager.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mntmSave.addActionListener(e -> {
			if (saveData(locationList)) {
				JOptionPane.showMessageDialog(frmRentalLocationManager, "Data saved successfully!", "Save", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mntmLoad.addActionListener(e -> {
			locationList = loadData();
			updateTable(locationList);
		});
		mnFile.add(mntmLoad);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frmRentalLocationManager, "See ya!");
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnLocation = new JMenu("Location");
		mnLocation.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		menuBar.add(mnLocation);

		JMenuItem mntmNewLocation = new JMenuItem("New Location");
		mntmNewLocation.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mntmNewLocation.addActionListener(e -> {
			newLocation(locationList);
		});
		mnLocation.add(mntmNewLocation);
		
		JMenuItem mntmDeleteLocation = new JMenuItem("Delete Location");
		mntmDeleteLocation.addActionListener(e -> {
			
		});
		
		JMenuItem mntmDeleteLocation_1 = new JMenuItem("Delete Location");
		mntmDeleteLocation_1.addActionListener(e -> {
			boolean success = false;
			while (!success) {
				int id = 0;
				
				try {
					String idIn = JOptionPane.showInputDialog(
							frmRentalLocationManager, "Enter ID of location to delete:", "Delete Location", JOptionPane.QUESTION_MESSAGE);
					if (idIn == null) break;
					id = Integer.parseInt(idIn);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frmRentalLocationManager, "ID must be a number", "Invalid ID", JOptionPane.ERROR_MESSAGE);
					continue;
				}
				
				boolean locFound = false;
				for (RentalLocations rl : locationList) {
					if (rl.getId() == id) {
						locationList.remove(rl);
						updateTable(locationList);
						success = true;
						locFound = true;
						break;
					}
				}
				if (locFound) break;
				
				JOptionPane.showMessageDialog(frmRentalLocationManager, "Location not found.\nPlease check the ID.", "Location Not Found", JOptionPane.ERROR_MESSAGE);
			}
		});
		mntmDeleteLocation_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnLocation.add(mntmDeleteLocation_1);

		JMenu mnLookupLocations = new JMenu("Lookup Locations");
		mnLookupLocations.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnLocation.add(mnLookupLocations);

		JMenuItem mntmByZipCode = new JMenuItem("by ZIP Code");
		mntmByZipCode.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnLookupLocations.add(mntmByZipCode);
		mntmByZipCode.addActionListener(e ->{
			tblLocations.setModel(Queries.locsByZip(locationList,frmRentalLocationManager));
		});

		JMenuItem mntmByName = new JMenuItem("by Name");
		mntmByName.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnLookupLocations.add(mntmByName);
		mntmByName.addActionListener(e -> {
			// Filter rates by a certain Location Name
			updateTable(Queries.detailsByLoc(locationList));

		});

		JMenu mnQueries = new JMenu("Queries");
		mnQueries.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		menuBar.add(mnQueries);

		JMenu mnLookupRentalRates = new JMenu("Lookup Rental Rates");
		mnLookupRentalRates.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnQueries.add(mnLookupRentalRates);

		JMenuItem mntmByLocationName = new JMenuItem("by Location Name");
		mntmByLocationName.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mntmByLocationName.addActionListener(e -> {
			// Filter rates by a certain Location Name
			tblLocations.setModel(Queries.ratesByLoc(locationList));
			
		});
		mnLookupRentalRates.add(mntmByLocationName);

		JMenuItem mntmAvailableVehicles = new JMenuItem("Available Vehicles");
		mntmAvailableVehicles.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mntmAvailableVehicles.addActionListener(e -> {
			// Complete list, pull availableVehicle for each location
			tblLocations.setModel(Queries.availableV(locationList));
		});
		mnQueries.add(mntmAvailableVehicles);

		JMenu mnCalculate = new JMenu("Calculate");
		mnCalculate.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		menuBar.add(mnCalculate);

		JMenuItem mntmDailyRevenueFor = new JMenuItem("Daily Revenue for Location");
		mntmDailyRevenueFor.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mntmDailyRevenueFor.addActionListener(e -> {
			tblLocations.setModel(Queries.calculateRevenue(locationList));
		});
		mnCalculate.add(mntmDailyRevenueFor);
		
		frmRentalLocationManager.setLocationRelativeTo(null);
		
		locationList = loadData();
		updateTable(locationList);
	}

	private void newLocation(List<RentalLocations> list) {

		JPanel pnlNewLocation = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = (GridBagConstraints.HORIZONTAL);
		c.ipadx = 5;
		c.ipady = 10;
		
		JLabel lblName = new JLabel("Location Name:");
		c.gridx = 0;
		c.gridy = 0;
		pnlNewLocation.add(lblName, c);

		JTextField txtName = new JTextField(20);
		c.gridx = 1;
		c.gridy = 0;
		pnlNewLocation.add(txtName, c);

		JLabel lblNameErr = new JLabel();
		lblNameErr.setForeground(Color.RED);
		c.gridx = 2;
		c.gridy = 0;
		pnlNewLocation.add(lblNameErr, c);

		JLabel lblVehicles = new JLabel("# of Rented Vehicles:");
		c.gridx = 0;
		c.gridy = 2;
		pnlNewLocation.add(lblVehicles, c);

		JTextField txtVehicle = new JTextField(20);
		c.gridx = 1;
		c.gridy = 2;
		pnlNewLocation.add(txtVehicle, c);

		JLabel lblVehiclesErr = new JLabel();
		lblVehiclesErr.setForeground(Color.RED);
		c.gridx = 2;
		c.gridy = 2;
		pnlNewLocation.add(lblVehiclesErr, c);

		JLabel lblZip = new JLabel("ZIP Code:");
		c.gridx = 0;
		c.gridy = 3;
		pnlNewLocation.add(lblZip, c);

		JTextField txtZip = new JTextField(20);
		c.gridx = 1;
		c.gridy = 3;
		pnlNewLocation.add(txtZip, c);

		JLabel lblZipErr = new JLabel();
		lblZipErr.setForeground(Color.RED);
		c.gridx = 2;
		c.gridy = 3;
		pnlNewLocation.add(lblZipErr, c);

		boolean complete = false;
		while (!complete) {
			
			int result = JOptionPane.showOptionDialog(null, new Object[] { pnlNewLocation }, "New Location",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			if (result == 0) {
				
				String nameIn = txtName.getText();
				String rentedVehiclesIn = txtVehicle.getText();
				String zipIn = txtZip.getText();
				complete = true;
				int rentedVehicles = 0, zip = 0;
				
				lblNameErr.setText("");
				lblVehiclesErr.setText("");
				lblZipErr.setText("");
				
				if (nameIn.length() < 6 || nameIn.length() > 20) {
					lblNameErr.setText("* Must be between 6 and 20 characters");
					complete = false;
				}
				
				try {
					rentedVehicles = Integer.parseInt(rentedVehiclesIn); 
				} catch (NumberFormatException ex) {
					lblVehiclesErr.setText("* Must be a number");
					complete = false;
				}
				
				try {
					zip = Integer.parseInt(zipIn);
				} catch (NumberFormatException ex) {
					lblZipErr.setText("* Must be a number");
					complete = false;
				}
				
				if (!complete) continue;

				int id = locationList.get(locationList.size() - 1).getId() + 1;
				
				RentalLocations loc = new RentalLocations(nameIn, rentedVehicles, id, zip);

				list.add(loc);
				updateTable(locationList);
			} else {
				complete = true;
			}
		}
	}

	private void updateTable(List<RentalLocations> list) {
		String[] columns = { "ID", "Name", "ZIP Code", "Rented Vehicles", "Avail. Vehicles", "Daily Rate" };
		Object[][] data = new Object[list.size()][6];

		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getId();
			data[i][1] = list.get(i).getName();
			data[i][2] = list.get(i).getZip();
			data[i][3] = list.get(i).getRentedVehicles();
			data[i][4] = list.get(i).availableVehicles();
			data[i][5] = list.get(i).getRates();
		}

		DefaultTableModel dtm = new DefaultTableModel(data, columns);
		tblLocations.setModel(dtm);
	}
	
	private boolean saveData(List<RentalLocations> list) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("locations.data"))) {
			oos.writeObject(list);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<RentalLocations> loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("locations.data"))) {
			ArrayList<RentalLocations> list = (ArrayList<RentalLocations>)ois.readObject();
			return list;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new ArrayList<RentalLocations>();
	}
}