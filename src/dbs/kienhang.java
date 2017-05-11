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
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class kienhang extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelkih;
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kienhang frame = new kienhang();
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
	public kienhang() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 975, 484);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYuCuKhch = new JLabel("TH\u00D4NG TIN KI\u1EC6N H\u00C0NG");
		lblYuCuKhch.setForeground(new Color(0, 102, 0));
		lblYuCuKhch.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblYuCuKhch.setBounds(362, 23, 270, 32);
		contentPane.add(lblYuCuKhch);
		
		JLabel lblTmKimTheo = new JLabel("T\u00ECm ki\u1EBFm theo");
		lblTmKimTheo.setForeground(new Color(0, 51, 153));
		lblTmKimTheo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTmKimTheo.setBounds(30, 59, 104, 26);
		contentPane.add(lblTmKimTheo);
		
		JRadioButton btnGH = new JRadioButton("KH\u00C1CH H\u00C0NG G\u1EECI");
		btnGH.setBackground(new Color(230, 230, 250));
		btnGH.setForeground(new Color(153, 102, 51));
		btnGH.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGH.setBounds(205, 62, 148, 23);
		contentPane.add(btnGH);
		
		JRadioButton btnNH = new JRadioButton("KH\u00C1CH H\u00C0NG NH\u1EACN");
		btnNH.setBackground(new Color(230, 230, 250));
		btnNH.setForeground(new Color(153, 102, 51));
		btnNH.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNH.setBounds(554, 62, 160, 23);
		contentPane.add(btnNH);
		
		ButtonGroup bg1 = new ButtonGroup( );
		 
		bg1.add(btnGH);
		bg1.add(btnNH);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 99, 919, 263);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setBackground(new Color(102, 153, 255));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
					Connection con = login.bridge();
					
					
					//theo khach hang gui
					if(btnGH.isSelected()){					
						loadDataKHGUI();				
					}
					//theo khach hang nhan
					else if(btnNH.isSelected()){		
						loadDataKHNHAN();		
					}
					//mac dinh ca 2 truong hop
					else{
						JOptionPane.showMessageDialog(frame, "vui lòng chọn lựa chọn");
					}
				
			}
			
		});
		btnSearch.setBounds(860, 56, 89, 34);
		contentPane.add(btnSearch);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				home home  = new home();
				home.setLocationRelativeTo(null);
				home.setVisible(true);
			}
		});
		btnBack.setBackground(new Color(255, 165, 0));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.setBounds(860, 388, 89, 35);
		contentPane.add(btnBack);
	}
	
	
	//load du lieu cho nguoi gui
	public void loadDataKHGUI(){
        modelkih = new DefaultTableModel();
        //Set Column Title
        Vector column = new Vector();
        column.add("MÃ KIỆN HÀNG");
        column.add("KHỐI LƯỢNG");
        column.add("LƯU Ý");
        column.add("TÌNH TRẠNG");
        column.add("TÊN NGƯỜI GỬI");
        column.add("TÊN NGƯỜI NHẬN");
        column.add("PHƯƠNG TIỆN");
        column.add("TÀI XẾ");
        
        modelkih.setColumnIdentifiers(column);
        List<Box> list = Box.getRequireFromDB_KHGUI();
        
        //System.out.print(list.size());
        
        for (int i = 0; i < list.size(); i++) {
        	Box box = (Box)list.get(i);
            Vector row = new Vector();
            row.add(box.getMaKiH());
            row.add(box.getKhoiLuong());
            row.add(box.getLuuY());
            row.add(box.getTinhTrang());
            row.add(box.getNguoiGui());
            row.add(box.getNguoiNhan());
            row.add(box.getPhuongTien());
            row.add(box.getTaiXe());
            modelkih.addRow(row);
        }

		table.setModel(modelkih);
    }
	
	//load du lieu cho nguoi gui
		public void loadDataKHNHAN(){
	        modelkih = new DefaultTableModel();
	        //Set Column Title
	        Vector column = new Vector();
	        column.add("MÃ KIỆN HÀNG");
	        column.add("KHỐI LƯỢNG");
	        column.add("LƯU Ý");
	        column.add("TÌNH TRẠNG");
	        column.add("TÊN NGƯỜI GỬI");
	        column.add("TÊN NGƯỜI NHẬN");
	        column.add("PHƯƠNG TIỆN");
	        column.add("TÀI XẾ");
	        
	        modelkih.setColumnIdentifiers(column);
	        List<Box> list = Box.getRequireFromDB_KHNHAN();
	        
	        //System.out.print(list.size());
	        
	        for (int i = 0; i < list.size(); i++) {
	        	Box box = (Box)list.get(i);
	            Vector row = new Vector();
	            row.add(box.getMaKiH());
	            row.add(box.getKhoiLuong());
	            row.add(box.getLuuY());
	            row.add(box.getTinhTrang());
	            row.add(box.getNguoiGui());
	            row.add(box.getNguoiNhan());
	            row.add(box.getPhuongTien());
	            row.add(box.getTaiXe());
	            modelkih.addRow(row);
	        }

			table.setModel(modelkih);
	    }
}
