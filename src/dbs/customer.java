package dbs;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class customer extends JFrame {

	private JPanel contentPane;
	private JTextField textSearch;
	private JTable table;
	private String search;
	private String convert;
	private DefaultTableModel model;
	private JTable table_1;
	private JTextField txtStart;
	private JTextField txtEnd;
	private String start;
	private String end;
	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customer frame = new customer();
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
	public customer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1075, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1060, 358);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("Thông tin khách hàng", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblTnKhchHng = new JLabel("T\u00EAn kh\u00E1ch h\u00E0ng");
		lblTnKhchHng.setForeground(new Color(0, 51, 153));
		lblTnKhchHng.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTnKhchHng.setBounds(23, 51, 128, 33);
		panel.add(lblTnKhchHng);
		
		textSearch = new JTextField();
		textSearch.setBounds(10, 84, 128, 20);
		panel.add(textSearch);
		textSearch.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setBackground(new Color(102, 153, 255));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search = textSearch.getText();
				convert = search.toUpperCase();
		
				 loadDataIntoJTable();
				 System.out.println("success");
			}
		});
		btnSearch.setBounds(20, 115, 103, 35);
		panel.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(177, 40, 868, 215);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnBack = new JButton("BACK");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(255, 165, 0));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				home home  = new home();
				home.setLocationRelativeTo(null);
				home.setVisible(true);
			}
		});
		btnBack.setBounds(956, 275, 89, 44);
		panel.add(btnBack);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("Doanh thu khách hàng", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(428, 64, 511, 149);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton btnBack_1 = new JButton("BACK");
		btnBack_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack_1.setBackground(new Color(255, 165, 0));
		btnBack_1.setForeground(new Color(255, 255, 255));
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				home home  = new home();
				home.setLocationRelativeTo(null);
				home.setVisible(true);
			}
		});
		btnBack_1.setBounds(850, 249, 89, 35);
		panel_1.add(btnBack_1);
		
		JLabel lblNg = new JLabel("Ngày bắt đầu ");
		lblNg.setForeground(new Color(0, 0, 139));
		lblNg.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNg.setBounds(22, 64, 107, 33);
		panel_1.add(lblNg);
		
		JLabel lblNgyKtThc = new JLabel("Ngày kết thúc ");
		lblNgyKtThc.setForeground(new Color(0, 0, 139));
		lblNgyKtThc.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNgyKtThc.setBounds(22, 129, 107, 33);
		panel_1.add(lblNgyKtThc);
		
		JButton btnSearch_1 = new JButton("SEARCH");
		btnSearch_1.setBackground(new Color(100, 149, 237));
		btnSearch_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearch_1.setForeground(new Color(255, 255, 255));
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String regex = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)";
				
				start=txtStart.getText();
				end=txtEnd.getText();
				
				boolean match1 = start.matches(regex);
		    	boolean match2 = end.matches(regex);
				
		    	if(match1 && match2){
		    		loadDataIntoJTable1();
		    	}
		    	else
		    		JOptionPane.showMessageDialog(frame, "ngày theo định dạng dd-mm-yyyy");
		    		
			}
		});
		btnSearch_1.setBounds(22, 199, 89, 35);
		panel_1.add(btnSearch_1);
		
		txtStart = new JTextField();
		txtStart.setBounds(162, 71, 140, 20);
		panel_1.add(txtStart);
		txtStart.setColumns(10);
		
		txtEnd = new JTextField();
		txtEnd.setBounds(162, 135, 140, 20);
		panel_1.add(txtEnd);
		txtEnd.setColumns(10);
	}
	
	 public void loadDataIntoJTable(){
	        model = new DefaultTableModel();
	        //Set Column Title
	        Vector column = new Vector();
	        column.add("HỌ TÊN");
	        column.add("MÃ SỐ THUẾ");
	        column.add("ĐIỆN THOẠI");
	        column.add("ĐỊA CHỈ");
	        column.add("EMAIL");
	        column.add("CTY ĐẠI DIỆN");
	        column.add("CÔNG NỢ");
	        model.setColumnIdentifiers(column);
	        
	        List<Customers> list = Customers.getCustomersFromDB(convert);
	        //System.out.print(list.size());
	        for (int i = 0; i < list.size(); i++) {
	            Customers cs = (Customers)list.get(i);
	            Vector row = new Vector();
	            row.add(cs.getHoTen());
	            row.add(cs.getMST());
	            row.add(cs.getDienThoai());
	            row.add(cs.getDiaChi());
	            row.add(cs.getEmail());
	            row.add(cs.getCTDaiDien());
	            row.add(cs.getCongNo());
	            model.addRow(row);
	        }

			table.setModel(model);
	    }
	 
	 public void loadDataIntoJTable1(){
	        model = new DefaultTableModel();
	        //Set Column Title
	        Vector column = new Vector();
	        column.add("HỌ TÊN");
	        column.add("DOANH THU");
	        
	        model.setColumnIdentifiers(column);
	        
	        List<Customers> list = Customers.getCustomerFromDB1(start,end);
	        //System.out.print(list.size());
	        for (int i = 0; i < list.size(); i++) {
	            Customers cs = (Customers)list.get(i);
	            Vector row = new Vector();
	            row.add(cs.getHoTen());
	            row.add(cs.getDoanhThu());
	            model.addRow(row);
	        }

	        table_1.setModel(model);
	    }
}
