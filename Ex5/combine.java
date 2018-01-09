package ex5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.UIManager;

public class combine {

	private JFrame frame;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JPasswordField textPassword;
	private JButton btnFileChooser;
	private String folderPath = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					combine window = new combine();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public combine() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.setBounds(100, 100, 761, 597);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnFileChooser = new JButton("Open Path");
		btnFileChooser.setForeground(new Color(0, 51, 255));
		btnFileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser dirChooser = new JFileChooser();
				dirChooser.setFileFilter(null);
				dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				dirChooser.setCurrentDirectory(null); // null sets default
				
				int returnVal = dirChooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					 folderPath = dirChooser.getSelectedFile().getPath();	
				}
			}
		});
		btnFileChooser.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnFileChooser.setBounds(468, 195, 213, 37);
		frame.getContentPane().add(btnFileChooser);
		
		JLabel lblScanningPorts = new JLabel("scanning ports:");
		lblScanningPorts.setForeground(new Color(0, 51, 255));
		lblScanningPorts.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblScanningPorts.setBounds(230, 16, 234, 52);
		frame.getContentPane().add(lblScanningPorts);
		
		JButton btnSlowscanning = new JButton("Slow Scanning");
		btnSlowscanning.setForeground(new Color(0, 51, 255));
		btnSlowscanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PortScan slow= new PortScan();
				if(!folderPath.equals("")){
				slow.slowScanInOrder(folderPath);	
				}
				else{
					JOptionPane.showMessageDialog(null, "Choose path to save the output file");
				}
			}
		});
		btnSlowscanning.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSlowscanning.setBounds(33, 127, 192, 37);
		frame.getContentPane().add(btnSlowscanning);
		
		JButton btnRandomScanning = new JButton("Random Scanning");
		btnRandomScanning.setForeground(new Color(0, 51, 255));
		btnRandomScanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PortScan random= new PortScan();
				if(!folderPath.equals("")){
					random.randomScan(folderPath);	
				}
				else{
					JOptionPane.showMessageDialog(null, "Choose path to save the output file");
				}
			}
		});
		btnRandomScanning.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnRandomScanning.setBounds(241, 127, 223, 37);
		frame.getContentPane().add(btnRandomScanning);
		
		JButton btnEfficientScanning = new JButton("Efficient Scanning");
		btnEfficientScanning.setForeground(new Color(0, 51, 255));
		btnEfficientScanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PortScan Efficient= new PortScan();
				if(!folderPath.equals("")){
					Efficient.smartScan(folderPath);	
				}
				else{
					JOptionPane.showMessageDialog(null, "please enter path to save the output file");
				}
			}
		});
		btnEfficientScanning.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnEfficientScanning.setBounds(480, 127, 213, 37);
		frame.getContentPane().add(btnEfficientScanning);
		
		JLabel lblPleaseOpenWireshark = new JLabel("Please open wireshark before scaning.");
		lblPleaseOpenWireshark.setForeground(new Color(0, 51, 255));
		lblPleaseOpenWireshark.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPleaseOpenWireshark.setBounds(142, 67, 484, 44);
		frame.getContentPane().add(lblPleaseOpenWireshark);
		
		JLabel lblPasswordGuessing = new JLabel("password guessing:");
		lblPasswordGuessing.setForeground(new Color(0, 51, 255));
		lblPasswordGuessing.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblPasswordGuessing.setBounds(213, 308, 319, 52);
		frame.getContentPane().add(lblPasswordGuessing);
		
		JLabel lblUsername = new JLabel("enter username:");
		lblUsername.setForeground(new Color(0, 51, 255));
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUsername.setBounds(87, 376, 223, 37);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblEnterPassword = new JLabel("enter password:");
		lblEnterPassword.setForeground(new Color(0, 51, 255));
		lblEnterPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEnterPassword.setBounds(87, 436, 183, 37);
		frame.getContentPane().add(lblEnterPassword);
		
		textUsername = new JTextField();
		textUsername.setBackground(UIManager.getColor("Button.highlight"));
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textUsername.setBounds(284, 377, 205, 35);
		frame.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		JButton btnCheck = new JButton("check");
		btnCheck.setForeground(new Color(0, 51, 255));
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textUsername.getText().isEmpty() && !textPassword.getText().isEmpty()){
					passwordGuessing ps= new passwordGuessing();
					ps.readFile(textUsername.getText(), textPassword.getText());
				}
				if(textUsername.getText().isEmpty() || textPassword.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Make sure you enter username and password");
				}
				
			}
		});
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnCheck.setBounds(328, 488, 129, 37);
		frame.getContentPane().add(btnCheck);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(521, 337, -209, 26);
		frame.getContentPane().add(passwordField);
		
		textPassword = new JPasswordField();
		textPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textPassword.setEchoChar('*');
		textPassword.setBounds(284, 437, 207, 35);
		frame.getContentPane().add(textPassword);
		
		JLabel lblPleaseEnterPath = new JLabel("Please choose a path to save the file: ");
		lblPleaseEnterPath.setForeground(new Color(0, 51, 255));
		lblPleaseEnterPath.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPleaseEnterPath.setBounds(33, 190, 438, 44);
		frame.getContentPane().add(lblPleaseEnterPath);
		
		
	}
}
