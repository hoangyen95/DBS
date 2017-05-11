package dbs;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class yeucau extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtMayc;
	private JTextField txtTinhtrang;
	private JTable tableyc;
	private DefaultTableModel modelyc;
	private String mayc;
	private String tinhtrang;
	private int tinhtrangi;
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					yeucau frame = new yeucau();
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
	public yeucau() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 498);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(184, 72, 548, 230);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblYuCuKhch = new JLabel("Y\u00CAU C\u1EA6U KH\u00C1CH H\u00C0NG");
		lblYuCuKhch.setForeground(new Color(0, 102, 0));
		lblYuCuKhch.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblYuCuKhch.setBounds(301, 11, 218, 33);
		contentPane.add(lblYuCuKhch);
		
		JLabel lblCpNhtTnh = new JLabel("C\u1EACP NH\u1EACT T\u00CCNH TR\u1EA0NG");
		lblCpNhtTnh.setForeground(new Color(102, 0, 153));
		lblCpNhtTnh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCpNhtTnh.setBounds(10, 84, 177, 14);
		contentPane.add(lblCpNhtTnh);
		
		JLabel lblMYuCu = new JLabel("M\u00E3 y\u00EAu c\u1EA7u");
		lblMYuCu.setForeground(new Color(0, 51, 153));
		lblMYuCu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMYuCu.setBounds(53, 112, 106, 20);
		contentPane.add(lblMYuCu);
		
		JLabel lblNewLabel = new JLabel("T\u00ECnh tr\u1EA1ng");
		lblNewLabel.setForeground(new Color(0, 51, 153));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(53, 163, 70, 20);
		contentPane.add(lblNewLabel);
		
		txtMayc = new JTextField();
		txtMayc.setBounds(20, 132, 129, 20);
		contentPane.add(txtMayc);
		txtMayc.setColumns(10);
		
		txtTinhtrang = new JTextField();
		txtTinhtrang.setBounds(20, 188, 129, 20);
		contentPane.add(txtTinhtrang);
		txtTinhtrang.setColumns(10);
		
		JButton btnCpNht = new JButton("UPDATE");
		btnCpNht.setBackground(new Color(102, 153, 255));
		btnCpNht.setForeground(new Color(255, 255, 255));
		btnCpNht.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCpNht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tinhtrang=txtTinhtrang.getText();
				tinhtrangi = Integer.parseInt(tinhtrang);
				String regex = "(0|1|2)";
			    mayc=txtMayc.getText().toUpperCase();
			    boolean match1 = tinhtrang.matches(regex);
				
				if(match1){
					loadDataIntoJTable1();
		    	}
		    	else
		    		JOptionPane.showMessageDialog(frame, "tình trạng yêu cầu là 0,1 hoặc 2");
				//JOptionPane.showMessageDialog(frame, "Yêu cầu "+mayc+ " cập nhật thành công.");
				
			}
		});
		btnCpNht.setBounds(32, 219, 99, 35);
		contentPane.add(btnCpNht);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBackground(new Color(255, 165, 0));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				home home  = new home();
				home.setLocationRelativeTo(null);
				home.setVisible(true);
			}
		});
		btnBack.setBounds(643, 407, 89, 41);
		contentPane.add(btnBack);
		
		JLabel lblTnhTrang = new JLabel("Tình trạng: ");
		lblTnhTrang.setForeground(new Color(0, 0, 128));
		lblTnhTrang.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTnhTrang.setBounds(184, 333, 94, 25);
		contentPane.add(lblTnhTrang);
		
		JLabel lbliu = new JLabel("0: đã điều xe");
		lbliu.setForeground(new Color(0, 0, 128));
		lbliu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lbliu.setBounds(288, 334, 89, 25);
		contentPane.add(lbliu);
		
		JLabel lblXeang = new JLabel("1: xe đang trên đường đến ");
		lblXeang.setForeground(new Color(0, 0, 128));
		lblXeang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblXeang.setBounds(288, 370, 188, 27);
		contentPane.add(lblXeang);
		
		JLabel lbln = new JLabel("2: đã đến nơi");
		lbln.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lbln.setForeground(new Color(0, 0, 128));
		lbln.setBounds(288, 407, 133, 33);
		contentPane.add(lbln);
		
	}
	
	public void loadDataIntoJTable(){
        modelyc = new DefaultTableModel();
        //Set Column Title
        Vector column = new Vector();
        column.add("MÃ YÊU CẦU");
        column.add("TÌNH TRẠNG");
        column.add("KHÁCH HÀNG GỬI");
        column.add("NHÂN VIÊN");
        
        modelyc.setColumnIdentifiers(column);
        List<Require> list = Require.getRequireFromDB();
        System.out.print(list.size());
        for (int i = 0; i < list.size(); i++) {
            Require rq = (Require)list.get(i);
            Vector row = new Vector();
            row.add(rq.getMaYc());
            row.add(rq.getTinhTrang());
            row.add(rq.getKhachHang());
            row.add(rq.getNhanVien());
            modelyc.addRow(row);
        }

		table.setModel(modelyc);
    }
	
	private void loadDataIntoJTable1(){
        modelyc = new DefaultTableModel();
        //Set Column Title
        Vector column = new Vector();
        column.add("MÃ YÊU CẦU");
        column.add("TÌNH TRẠNG");
        column.add("KHÁCH HÀNG GỬI");
        column.add("NHÂN VIÊN");
        
        modelyc.setColumnIdentifiers(column);
        List<Require> list = Require.updateRequire(tinhtrang,tinhtrangi,mayc);
        //System.out.print(list.size());
        for (int i = 0; i < list.size(); i++) {
            Require rq = (Require)list.get(i);
            Vector row = new Vector();
            row.add(rq.getMaYc());
            row.add(rq.getTinhTrang());
            row.add(rq.getKhachHang());
            row.add(rq.getNhanVien());
            modelyc.addRow(row);
            
        }

		table.setModel(modelyc);
    }
}
