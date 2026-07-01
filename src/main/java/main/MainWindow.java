package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
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
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Schedule");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Input", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_3);
		FlowLayout fl_panel_3 = new FlowLayout(FlowLayout.LEFT, 30, 0);
		fl_panel_3.setAlignOnBaseline(true);
		panel_3.setLayout(fl_panel_3);
		
		JLabel lblCurrentDate = new JLabel("Current Date:");
		lblCurrentDate.setVerticalAlignment(SwingConstants.TOP);
		lblCurrentDate.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblCurrentDate);
		
		textCurrentDate = new JTextField();
		panel_3.add(textCurrentDate);
		textCurrentDate.setColumns(10);
		
		JButton btnCalculateNext = new JButton("Calculate next date");
		btnCalculateNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel_3.add(btnCalculateNext);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Configuration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblType = new JLabel("Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 0;
		panel_2.add(lblType, gbc_lblType);
		
		JComboBox comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"Once", "Recurring"}));
		GridBagConstraints gbc_comboBoxType = new GridBagConstraints();
		gbc_comboBoxType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxType.gridx = 1;
		gbc_comboBoxType.gridy = 0;
		panel_2.add(comboBoxType, gbc_comboBoxType);
		
		JCheckBox chckbxEnabled = new JCheckBox("Enabled");
		GridBagConstraints gbc_chckbxEnabled = new GridBagConstraints();
		gbc_chckbxEnabled.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxEnabled.gridx = 2;
		gbc_chckbxEnabled.gridy = 0;
		panel_2.add(chckbxEnabled, gbc_chckbxEnabled);
		
		JLabel lblDateTime = new JLabel("DateTime");
		GridBagConstraints gbc_lblDateTime = new GridBagConstraints();
		gbc_lblDateTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateTime.gridx = 0;
		gbc_lblDateTime.gridy = 1;
		panel_2.add(lblDateTime, gbc_lblDateTime);
		
		textDateTime = new JTextField();
		GridBagConstraints gbc_textDateTime = new GridBagConstraints();
		gbc_textDateTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDateTime.gridwidth = 5;
		gbc_textDateTime.insets = new Insets(0, 0, 5, 5);
		gbc_textDateTime.gridx = 1;
		gbc_textDateTime.gridy = 1;
		panel_2.add(textDateTime, gbc_textDateTime);
		textDateTime.setColumns(10);
		
		JLabel lblOccurs = new JLabel("Occurs");
		GridBagConstraints gbc_lblOccurs = new GridBagConstraints();
		gbc_lblOccurs.insets = new Insets(0, 0, 0, 5);
		gbc_lblOccurs.gridx = 0;
		gbc_lblOccurs.gridy = 2;
		panel_2.add(lblOccurs, gbc_lblOccurs);
		
		JComboBox comboBoxOccurs = new JComboBox();
		comboBoxOccurs.setModel(new DefaultComboBoxModel(new String[] {"Daily"}));
		GridBagConstraints gbc_comboBoxOccurs = new GridBagConstraints();
		gbc_comboBoxOccurs.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxOccurs.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOccurs.gridx = 1;
		gbc_comboBoxOccurs.gridy = 2;
		panel_2.add(comboBoxOccurs, gbc_comboBoxOccurs);
		
		JLabel lblEvery = new JLabel("Every:");
		GridBagConstraints gbc_lblEvery = new GridBagConstraints();
		gbc_lblEvery.anchor = GridBagConstraints.EAST;
		gbc_lblEvery.insets = new Insets(0, 0, 0, 5);
		gbc_lblEvery.gridx = 2;
		gbc_lblEvery.gridy = 2;
		panel_2.add(lblEvery, gbc_lblEvery);
		
		JSpinner spinnerDays = new JSpinner();
		GridBagConstraints gbc_spinnerDays = new GridBagConstraints();
		gbc_spinnerDays.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerDays.gridwidth = 3;
		gbc_spinnerDays.insets = new Insets(0, 0, 0, 5);
		gbc_spinnerDays.gridx = 3;
		gbc_spinnerDays.gridy = 2;
		panel_2.add(spinnerDays, gbc_spinnerDays);
		
		JLabel lblDays = new JLabel("day(s)");
		GridBagConstraints gbc_lblDays = new GridBagConstraints();
		gbc_lblDays.gridx = 6;
		gbc_lblDays.gridy = 2;
		panel_2.add(lblDays, gbc_lblDays);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Limits", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblStartDate = new JLabel("Start date:");
		panel_1.add(lblStartDate);
		
		textStartDate = new JTextField();
		panel_1.add(textStartDate);
		textStartDate.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End date:");
		panel_1.add(lblEndDate);
		
		textEndDate = new JTextField();
		panel_1.add(textEndDate);
		textEndDate.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNextExecTime = new JLabel("Next execution time");
		GridBagConstraints gbc_lblNextExecTime = new GridBagConstraints();
		gbc_lblNextExecTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblNextExecTime.anchor = GridBagConstraints.EAST;
		gbc_lblNextExecTime.gridx = 0;
		gbc_lblNextExecTime.gridy = 0;
		panel.add(lblNextExecTime, gbc_lblNextExecTime);
		
		textNextExecTime = new JTextField();
		textNextExecTime.setEditable(false);
		GridBagConstraints gbc_textNextExecTime = new GridBagConstraints();
		gbc_textNextExecTime.insets = new Insets(0, 0, 5, 0);
		gbc_textNextExecTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNextExecTime.gridx = 1;
		gbc_textNextExecTime.gridy = 0;
		panel.add(textNextExecTime, gbc_textNextExecTime);
		textNextExecTime.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 1;
		panel.add(lblDescription, gbc_lblDescription);
		
		JTextArea textAreaDescription = new JTextArea();
		textAreaDescription.setEditable(false);
		GridBagConstraints gbc_textAreaDescription = new GridBagConstraints();
		gbc_textAreaDescription.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescription.gridx = 1;
		gbc_textAreaDescription.gridy = 1;
		panel.add(textAreaDescription, gbc_textAreaDescription);

	}
	
	private LocalDateTime dateFormatterParser(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String time = " 00:00";
		date.concat(time);
		LocalDateTime currentDate = LocalDateTime.parse(date, formatter);
		return currentDate;
	}

}
