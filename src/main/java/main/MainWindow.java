package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JCheckBox;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCurrentDate;
	private JTextField textDateTime;
	private JTextField textStartDate;
	private JTextField textEndDate;
	private JTextField textNextExecTime;

	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MainWindow() {
		setTitle("Schedule");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		//Declare all variables so they are available for the mouse click event handler
		JPanel panel_2 = new JPanel();
		JPanel panel_3 = new JPanel();
		JLabel lblCurrentDate = new JLabel("Current Date:");
		textCurrentDate = new JTextField();
		JButton btnCalculateNext = new JButton("Calculate next date");
		JLabel lblType = new JLabel("Type");
		JComboBox comboBoxType = new JComboBox();
		JCheckBox chckbxEnabled = new JCheckBox("Enabled");
		JLabel lblDateTime = new JLabel("DateTime");
		textDateTime = new JTextField();
		JLabel lblOccurs = new JLabel("Occurs");
		JLabel lblEvery = new JLabel("Every:");
		JComboBox<Period> comboBoxOccurs = new JComboBox<Period>();
		JSpinner spinnerDays = new JSpinner();
		JLabel lblDays = new JLabel("day(s)");
		JPanel panel_1 = new JPanel();
		JLabel lblStartDate = new JLabel("Start date:");
		textStartDate = new JTextField();
		JLabel lblEndDate = new JLabel("End date:");
		textEndDate = new JTextField();
		JPanel panel = new JPanel();
		JLabel lblNextExecTime = new JLabel("Next execution time");
		textNextExecTime = new JTextField();
		JLabel lblDescription = new JLabel("Description");
		JTextArea textAreaDescription = new JTextArea();
		
		panel_3.setBorder(new TitledBorder(null, "Input", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_3);
		FlowLayout fl_panel_3 = new FlowLayout(FlowLayout.LEFT, 30, 0);
		fl_panel_3.setAlignOnBaseline(true);
		panel_3.setLayout(fl_panel_3);
		
		
		lblCurrentDate.setVerticalAlignment(SwingConstants.TOP);
		lblCurrentDate.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblCurrentDate);
		
		
		panel_3.add(textCurrentDate);
		textCurrentDate.setColumns(10);
		
		
		btnCalculateNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//error message if task was not enabled
				if (!chckbxEnabled.isSelected()) {
					JOptionPane.showMessageDialog(btnCalculateNext, "Please enable the task", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (comboBoxType.getSelectedItem() == "Once") {
					//error message if any required fields are empty
					if (textDateTime.getText().isBlank() || textStartDate.getText().isBlank()) {
						JOptionPane.showMessageDialog(btnCalculateNext, "Please set the dates to execute the task", 
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//format dates
					LocalDateTime dateTime = DateFormatter.dateFormatterParser(textDateTime.getText());
					LocalDateTime startDate = DateFormatter.dateFormatterParser(textStartDate.getText());
					//create the scheduler
					Scheduler sch = new Scheduler(startDate, null, null, 0);
					//return the strings to the UI
					String desc = sch.occursOnceLogic(dateTime);
					textNextExecTime.setText(textDateTime.getText());
					textAreaDescription.setText(desc);
					
				} else if (comboBoxType.getSelectedItem() == "Recurring") {
					//error message if any required date is empty
					if (textCurrentDate.getText().isBlank() || textStartDate.getText().isBlank()) {
						JOptionPane.showMessageDialog(btnCalculateNext, "Please set the dates to execute the task", 
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//parse and format the dates and other parameters for the scheduler
					LocalDateTime start = DateFormatter.dateFormatterParser(textStartDate.getText());
					LocalDateTime end = null;
					if (!textEndDate.getText().isBlank()) {
						end = DateFormatter.dateFormatterParser(textEndDate.getText());
					}
					Period per = (Period) comboBoxOccurs.getSelectedItem();
					int periodInt = (Integer) spinnerDays.getValue();
					LocalDateTime currentDate = DateFormatter.dateFormatterParser(textCurrentDate.getText());
					//create the scheduler
					Scheduler sch = new Scheduler(start, end, per, periodInt);
					//return the strings to the UI
					LocalDateTime next = sch.calculateNextExcetutionTime(currentDate);
					String desc = sch.occursRecurringText(currentDate);
					if (next == null) {
						//if the task has ended N/A will be displayed as next date
						textNextExecTime.setText("N/A");
					} else {
						textNextExecTime.setText(DateFormatter.dateStringGetter(next, false, false));
					}
					textAreaDescription.setText(desc);
				} else {
					return;
				}
			}
		});
		panel_3.add(btnCalculateNext);
		
		
		panel_2.setBorder(new TitledBorder(null, "Configuration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 0;
		panel_2.add(lblType, gbc_lblType);
		
		
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"Once", "Recurring"}));
		GridBagConstraints gbc_comboBoxType = new GridBagConstraints();
		gbc_comboBoxType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxType.gridx = 1;
		gbc_comboBoxType.gridy = 0;
		panel_2.add(comboBoxType, gbc_comboBoxType);
		
		
		GridBagConstraints gbc_chckbxEnabled = new GridBagConstraints();
		gbc_chckbxEnabled.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxEnabled.gridx = 2;
		gbc_chckbxEnabled.gridy = 0;
		panel_2.add(chckbxEnabled, gbc_chckbxEnabled);
		
		
		GridBagConstraints gbc_lblDateTime = new GridBagConstraints();
		gbc_lblDateTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateTime.gridx = 0;
		gbc_lblDateTime.gridy = 1;
		panel_2.add(lblDateTime, gbc_lblDateTime);
		
		
		GridBagConstraints gbc_textDateTime = new GridBagConstraints();
		gbc_textDateTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDateTime.gridwidth = 5;
		gbc_textDateTime.insets = new Insets(0, 0, 5, 5);
		gbc_textDateTime.gridx = 1;
		gbc_textDateTime.gridy = 1;
		panel_2.add(textDateTime, gbc_textDateTime);
		textDateTime.setColumns(10);
		
		
		GridBagConstraints gbc_lblOccurs = new GridBagConstraints();
		gbc_lblOccurs.insets = new Insets(0, 0, 0, 5);
		gbc_lblOccurs.gridx = 0;
		gbc_lblOccurs.gridy = 2;
		panel_2.add(lblOccurs, gbc_lblOccurs);
		
		
		comboBoxOccurs.setModel(new DefaultComboBoxModel<Period>(Period.values()));
		GridBagConstraints gbc_comboBoxOccurs = new GridBagConstraints();
		gbc_comboBoxOccurs.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxOccurs.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOccurs.gridx = 1;
		gbc_comboBoxOccurs.gridy = 2;
		panel_2.add(comboBoxOccurs, gbc_comboBoxOccurs);
		
		
		GridBagConstraints gbc_lblEvery = new GridBagConstraints();
		gbc_lblEvery.anchor = GridBagConstraints.EAST;
		gbc_lblEvery.insets = new Insets(0, 0, 0, 5);
		gbc_lblEvery.gridx = 2;
		gbc_lblEvery.gridy = 2;
		panel_2.add(lblEvery, gbc_lblEvery);
		
		
		GridBagConstraints gbc_spinnerDays = new GridBagConstraints();
		gbc_spinnerDays.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerDays.gridwidth = 3;
		gbc_spinnerDays.insets = new Insets(0, 0, 0, 5);
		gbc_spinnerDays.gridx = 3;
		gbc_spinnerDays.gridy = 2;
		panel_2.add(spinnerDays, gbc_spinnerDays);
		
		
		GridBagConstraints gbc_lblDays = new GridBagConstraints();
		gbc_lblDays.gridx = 6;
		gbc_lblDays.gridy = 2;
		panel_2.add(lblDays, gbc_lblDays);
		
		
		panel_1.setBorder(new TitledBorder(null, "Limits", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		panel_1.add(lblStartDate);
		
		
		panel_1.add(textStartDate);
		textStartDate.setColumns(10);
		
		
		panel_1.add(lblEndDate);
		
		
		panel_1.add(textEndDate);
		textEndDate.setColumns(10);
		
		
		panel.setBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
		GridBagConstraints gbc_lblNextExecTime = new GridBagConstraints();
		gbc_lblNextExecTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblNextExecTime.anchor = GridBagConstraints.EAST;
		gbc_lblNextExecTime.gridx = 0;
		gbc_lblNextExecTime.gridy = 0;
		panel.add(lblNextExecTime, gbc_lblNextExecTime);
		
		
		textNextExecTime.setEditable(false);
		GridBagConstraints gbc_textNextExecTime = new GridBagConstraints();
		gbc_textNextExecTime.insets = new Insets(0, 0, 5, 0);
		gbc_textNextExecTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNextExecTime.gridx = 1;
		gbc_textNextExecTime.gridy = 0;
		panel.add(textNextExecTime, gbc_textNextExecTime);
		textNextExecTime.setColumns(10);
		
		
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 1;
		panel.add(lblDescription, gbc_lblDescription);
		
		
		textAreaDescription.setEditable(false);
		GridBagConstraints gbc_textAreaDescription = new GridBagConstraints();
		gbc_textAreaDescription.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescription.gridx = 1;
		gbc_textAreaDescription.gridy = 1;
		panel.add(textAreaDescription, gbc_textAreaDescription);

	}
}
