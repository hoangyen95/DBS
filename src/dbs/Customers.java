package dbs;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

public class Customers {
	String HoTen;
	String MST;
	String DienThoai;
	String DiaChi;
	String Email;
	String CTDaiDien;
	String DoanhThu;
	

	double CongNo;
	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customers(String hoTen, String mST, String dienThoai, String diaChi, String email, String cTDaiDien,
			double congNo) {
		super();
		HoTen = hoTen;
		MST = mST;
		DienThoai = dienThoai;
		DiaChi = diaChi;
		Email = email;
		CTDaiDien = cTDaiDien;
		CongNo = congNo;
	}
	
	
	
	public Customers(String hoTen, String doanhThu) {
		super();
		HoTen = hoTen;
		DoanhThu = doanhThu;
	}

	public String getDoanhThu() {
		return DoanhThu;
	}
	public void setDoanhThu(String doanhThu) {
		DoanhThu = doanhThu;
	}
	
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getMST() {
		return MST;
	}
	public void setMST(String mST) {
		MST = mST;
	}
	public String getDienThoai() {
		return DienThoai;
	}
	public void setDienThoai(String dienThoai) {
		DienThoai = dienThoai;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getCTDaiDien() {
		return CTDaiDien;
	}
	public void setCTDaiDien(String cTDaiDien) {
		CTDaiDien = cTDaiDien;
	}
	public double getCongNo() {
		return CongNo;
	}
	public void setCongNo(double congNo) {
		CongNo = congNo;
	}
	
	public static List<Customers> getCustomerFromDB1(String start, String end){	
		try {
			Connection con = login.bridge();
			
			String plSql = "{call HR.func2(?,?,?,?,?)}";
	    	CallableStatement cstmt;
	    	
			cstmt = con.prepareCall(plSql);
			
			cstmt.setString(1, start);
			cstmt.setString(2, end);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
			cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt.executeQuery();
		
			ResultSet rs = (ResultSet)cstmt.getObject(3);

			 List<Customers> list = new ArrayList<Customers>();
		        while (rs.next()) {
		            Customers cs = new Customers(rs.getString(2),rs.getString(1));
		            list.add(cs);
		        }
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	public static List<Customers> getCustomersFromDB(String search){
	    try {
	    	Connection con = login.bridge();
	    	
	    	String plSql = "{call HR.GET_INFORM_CUSTOM(?,?,?,?,?,?,?,?,?)}";
	    	CallableStatement cstmt = con.prepareCall(plSql);			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.setString(2,search);	
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(8, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(9, java.sql.Types.DOUBLE);
			cstmt.executeQuery();
					
			ResultSet rs = (ResultSet)cstmt.getObject(1);
			    
	        List<Customers> list = new ArrayList<Customers>();
	        while (rs.next()) {
	            Customers cs = new Customers(rs.getString("HOTEN"), rs.getString("MST"), rs.getString("DIENTHOAI"), rs.getString("DIACHI"),rs.getString("EMAIL"),rs.getString("CT_DAIDIEN"),rs.getDouble("CONGNO"));
	            list.add(cs);
	        }
	        return list;

	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        return null;
	    }
	    
	}
	
}
