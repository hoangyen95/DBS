package dbs;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class login extends JFrame {

	private JPanel contentPane;
	private static JTextField username;
	private static JPasswordField userpass;
	private static String userName;
	private static String password;
	private JFrame frame;
	public boolean check;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblngNhpVo = new JLabel("\u0110\u0102NG NH\u1EACP V\u00C0O C\u01A0 S\u1EDE D\u1EEE LI\u1EC6U");
		lblngNhpVo.setForeground(new Color(0, 102, 0));
		lblngNhpVo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblngNhpVo.setBounds(86, 22, 276, 24);
		contentPane.add(lblngNhpVo);
		
		JLabel lblTnngNhp = new JLabel("T\u00CAN \u0110\u0102NG NH\u1EACP");
		lblTnngNhp.setForeground(new Color(0, 51, 153));
		lblTnngNhp.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnngNhp.setBounds(27, 67, 122, 33);
		contentPane.add(lblTnngNhp);
		
		JLabel lblMtKhu = new JLabel("M\u1EACT KH\u1EA8U");
		lblMtKhu.setForeground(new Color(0, 51, 153));
		lblMtKhu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMtKhu.setBounds(27, 111, 107, 24);
		contentPane.add(lblMtKhu);
		
		username = new JTextField();
		username.setBounds(159, 71, 137, 23);
		contentPane.add(username);
		username.setColumns(10);
		
		userpass = new JPasswordField();
		userpass.setBounds(159, 114, 137, 23);
		contentPane.add(userpass);
		userName = username.getText();
        password = userpass.getText();
		JButton btnngNhp = new JButton("\u0110\u0102NG NH\u1EACP");
		btnngNhp.setForeground(new Color(255, 255, 255));
		btnngNhp.setBackground(new Color(102, 153, 255));
		btnngNhp.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnngNhp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					
					OracleDataSource ods = new OracleDataSource();
					ods.setURL("jdbc:oracle:thin:@localhost:1521/orcl");
					System.out.println("ten: " +getName());
					System.out.println("pass: " +getPass());
					Connection con = ods.getConnection(getName(),getPass());
					

				    if(con!=null){
				    setVisible(false);
					home home  = new home();
					home.setLocationRelativeTo(null);
					home.setVisible(true);
					//con.close();
				    }
				    else{
				    	JOptionPane.showMessageDialog(frame, "invalid username/password; logon denied");
				    }
					
				}
				catch(Exception e1){
					//System.out.println(e1);
					JOptionPane.showMessageDialog(frame, "invalid username/password; logon denied");
				
				}
			}
		});
		btnngNhp.setBounds(159, 175, 137, 35);
		contentPane.add(btnngNhp);
	}
	
	
	
	public String getPass(){
		return userpass.getText();
	}
	
	public String getName(){
		return username.getText();
	}
	
	public static Connection bridge(){
		
		try {
			OracleDataSource ods;
			ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521/orcl");
			Connection con = ods.getConnection(username.getText(),userpass.getText());
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

}
