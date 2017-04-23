package dbs;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.internal.OracleTypes;

public class Require {
	private String maYc;
	private String tinhTrang;
	private String khachHang;
	private String nhanVien;
	public Require() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Require(String maYc, String tinhTrang, String khachHang, String nhanVien) {
		super();
		this.maYc = maYc;
		this.tinhTrang = tinhTrang;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}
	public String getMaYc() {
		return maYc;
	}
	public void setMaYc(String maYc) {
		this.maYc = maYc;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public String getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(String khachHang) {
		this.khachHang = khachHang;
	}
	public String getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(String nhanVien) {
		this.nhanVien = nhanVien;
	}
	
public static List<Require> updateRequire(String tinhtrang,int tinhtrangi, String mayc){
	
		
		try {
			List<Require> list = new ArrayList<Require>();
			//OracleDataSource source;
			//source = new OracleDataSource();
			//source.setURL("jdbc:oracle:thin:@localhost:1521/orcl");
		    //Connection con = source.getConnection("Test", "test");
		    //System.out.println("connect");
			
			Connection con = login.bridge();
		    
		    
		    
		    String plSql = "{call HR.UPDATE_REQUIRE(?,?)}";
		    CallableStatement cstmt = con.prepareCall(plSql);

			cstmt.setInt(1,tinhtrangi);
			cstmt.setString(2, mayc);
						
			cstmt.executeUpdate();
		    cstmt.close();

		    CallableStatement cstmt0,cstmt1,cstmt2;
			
			String plSql0 = "{call HR.GET_REQUIRE(?,?,?,?,?)}";
			cstmt0 = con.prepareCall(plSql0);
			
			cstmt0.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt0.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt0.registerOutParameter(3, java.sql.Types.INTEGER);
			cstmt0.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmt0.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt0.executeQuery();
			
			ResultSet rs0 = (ResultSet)cstmt0.getObject(1);	
				

			    
			    String kh = null;
			    String nv=null;
			    
			    
			    while (rs0.next()) {
			    	
			    	String makh = rs0.getString("MAKH");
			    	
			    	String plSql1 = "{call HR.GET_NAME_CUSTOM(?,?)}";
				    cstmt1 = con.prepareCall(plSql1);
				    
				    cstmt1.setString(1, makh);
				    cstmt1.registerOutParameter(2, java.sql.Types.VARCHAR);
				    cstmt1.execute();
			    	
			        //ResultSet resultSet1 = stmt1.executeQuery("select * from hr.khachhang where makh= '"+makh+"'");
			        
			        // get the participants
			        kh = cstmt1.getString(2);

			        String manv = rs0.getString("MANV");
			        
			        String plSql2 = "{call HR.GET_NAME_STAFF(?,?)}";
				    cstmt2 = con.prepareCall(plSql2);
				    
				    cstmt2.setString(1, manv);
				    cstmt2.registerOutParameter(2, java.sql.Types.VARCHAR);
				    cstmt2.execute();

			        //ResultSet resultSet2 = stmt2.executeQuery("select * from hr.nhanvien where manv= '"+manv+"'");
			        
			        // get the participants
			        nv = cstmt2.getString(2);

			        Require rq = new Require(rs0.getString("MAYC"), rs0.getString("TINHTRANG"), nv, kh);
        	        
			        list.add(rq);
			        
			    }

	        return list;
	        
	        
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return null;
	}
	public static List<Require> getRequireFromDB(){
	try {
		//		OracleDataSource source = new OracleDataSource();
		//	    source.setURL("jdbc:oracle:thin:@localhost:1521/orcl");
		//	    Connection con = source.getConnection("Test", "test");
		//	    System.out.println("connect");
		
		 Connection con = login.bridge();
    
		 CallableStatement cstmt0,cstmt1,cstmt2;
			
			String plSql0 = "{call HR.GET_REQUIRE(?,?,?,?,?)}";
			cstmt0 = con.prepareCall(plSql0);
			
			cstmt0.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt0.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt0.registerOutParameter(3, java.sql.Types.INTEGER);
			cstmt0.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmt0.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt0.executeQuery();
			
			ResultSet rs0 = (ResultSet)cstmt0.getObject(1);	

			    
			String kh = null;
			String nv=null;
	    
	    List<Require> list = new ArrayList<Require>();
	    while (rs0.next()) {
	    	
	    	String makh = rs0.getString("MAKH");
	    	String plSql1 = "{call HR.GET_NAME_CUSTOM(?,?)}";
		    cstmt1 = con.prepareCall(plSql1);
		    cstmt1.setString(1, makh);
		    
		    cstmt1.registerOutParameter(2, java.sql.Types.VARCHAR);
		    cstmt1.execute();
		    
	        //ResultSet resultSet1 = stmt1.executeQuery("select * from hr.khachhang where makh= '"+makh+"'");
	        
	        // get the participants
	        kh = cstmt1.getString(2);

	        String manv = rs0.getString("MANV");
	        
	        String plSql2 = "{call HR.GET_NAME_STAFF(?,?)}";
		    cstmt2 = con.prepareCall(plSql2);
		    
		    cstmt2.setString(1, manv);
		    cstmt2.registerOutParameter(2, java.sql.Types.VARCHAR);
		    cstmt2.execute();

	        //ResultSet resultSet2 = stmt2.executeQuery("select * from hr.nhanvien where manv= '"+manv+"'");
	        
	        // get the participants
	        nv = cstmt2.getString(2);
	        
	        Require rq = new Require(rs0.getString("MAYC"), rs0.getString("TINHTRANG"), nv, kh);
	        
	        list.add(rq);
	        
	    }
		
	    return list;
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
}
	
}
