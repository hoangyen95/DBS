package dbs;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;


public class home extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
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
	public home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnYeuCau = new JButton("Y\u00CAU C\u1EA6U KH\u00C1CH H\u00C0NG");
		btnYeuCau.setForeground(new Color(255, 255, 255));
		btnYeuCau.setBackground(new Color(153, 204, 0));
		btnYeuCau.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnYeuCau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				yeucau yc  = new yeucau();
				yc.loadDataIntoJTable();
				yc.setLocationRelativeTo(null);
				yc.setVisible(true);
			}
		});
		btnYeuCau.setBounds(100, 39, 221, 40);
		contentPane.add(btnYeuCau);
		
		JButton btnKhachHang = new JButton("TH\u00D4NG TIN KH\u00C1CH H\u00C0NG");
		btnKhachHang.setForeground(new Color(255, 255, 255));
		btnKhachHang.setBackground(new Color(153, 204, 102));
		btnKhachHang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				customer kh  = new customer();
				kh.loadDataIntoJTable();
				kh.setLocationRelativeTo(null);
				kh.setVisible(true);
			}
		});
		btnKhachHang.setBounds(100, 141, 221, 40);
		contentPane.add(btnKhachHang);
		
		JButton btnKienHang = new JButton("TH\u00D4NG TIN  KI\u1EC6N H\u00C0NG");
		btnKienHang.setForeground(new Color(255, 255, 255));
		btnKienHang.setBackground(new Color(153, 204, 51));
		btnKienHang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnKienHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {setVisible(false);
			kienhang kih  = new kienhang();
			kih.setLocationRelativeTo(null);
			kih.setVisible(true);
			}
		});
		btnKienHang.setBounds(100, 90, 221, 40);
		contentPane.add(btnKienHang);
		
		JButton btnExit = new JButton("THO\u00C1T");
		btnExit.setForeground(new Color(255, 255, 204));
		btnExit.setBackground(new Color(153, 204, 153));
		btnExit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "bạn có muốn thoát khỏi hệ thống?");
				setVisible(false);
				login login  = new login();
				login.setLocationRelativeTo(null);
				login.setVisible(true);
			}
		});
		btnExit.setBounds(100, 192, 221, 40);
		contentPane.add(btnExit);
	}

}
