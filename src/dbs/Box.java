package dbs;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

public class Box {
	private String maKiH;
	private String khoiLuong;
	private String luuY;
	private String tinhTrang;
	private String nguoiGui;
	private String nguoiNhan;
	private String phuongTien;
	private String taiXe;
	
	public Box() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Box(String maKiH, String khoiLuong, String luuY, String tinhTrang, String nguoiGui, String nguoiNhan,
			String phuongTien, String taiXe) {
		super();
		this.maKiH = maKiH;
		this.khoiLuong = khoiLuong;
		this.luuY = luuY;
		this.tinhTrang = tinhTrang;
		this.nguoiGui = nguoiGui;
		this.nguoiNhan = nguoiNhan;
		this.phuongTien = phuongTien;
		this.taiXe = taiXe;
	}
	public String getMaKiH() {
		return maKiH;
	}
	public void setMaKiH(String maKiH) {
		this.maKiH = maKiH;
	}
	public String getKhoiLuong() {
		return khoiLuong;
	}
	public void setKhoiLuong(String khoiLuong) {
		this.khoiLuong = khoiLuong;
	}
	public String getLuuY() {
		return luuY;
	}
	public void setLuuY(String luuY) {
		this.luuY = luuY;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public String getNguoiGui() {
		return nguoiGui;
	}
	public void setNguoiGui(String nguoiGui) {
		this.nguoiGui = nguoiGui;
	}
	public String getNguoiNhan() {
		return nguoiNhan;
	}
	public void setNguoiNhan(String nguoiNhan) {
		this.nguoiNhan = nguoiNhan;
	}
	public String getPhuongTien() {
		return phuongTien;
	}
	public void setPhuongTien(String phuongTien) {
		this.phuongTien = phuongTien;
	}
	public String getTaiXe() {
		return taiXe;
	}
	public void setTaiXe(String taiXe) {
		this.taiXe = taiXe;
	}
	
	
	
	//lay du lieu tu db cho 
	public static List<Box> getRequireFromDB_KHGUI(){
		try {
			 Connection con = login.bridge();
	    
			 CallableStatement cstmt0,cstmt1,cstmt2,cstmt3,cstmt4,cstmt5,cstmt6;
			 Box box;
			 
			 	//lay nhung yeu cau chua xu ly
			 	//output: mayc, tinhtrang, manv, makh
				String plSql0 = "{call HR.GET_REQUIRE(?,?,?,?,?)}";
				cstmt0 = con.prepareCall(plSql0);
				
				cstmt0.registerOutParameter(1, OracleTypes.CURSOR);
				cstmt0.registerOutParameter(2, java.sql.Types.VARCHAR);
				cstmt0.registerOutParameter(3, java.sql.Types.INTEGER);
				cstmt0.registerOutParameter(4, java.sql.Types.VARCHAR);
				cstmt0.registerOutParameter(5, java.sql.Types.VARCHAR);
				cstmt0.executeQuery();
				
				ResultSet rs0 = (ResultSet)cstmt0.getObject(1);	
				
				//lay du lieu cac cot: mayc, makh, manv
				String yc = null;    
				String khgui,khnhan,taixe,phuongtien = null;
				String mahoadongui,mataixe,maphuongtien = null;
		    
		    List<Box> list = new ArrayList<Box>();
		    while (rs0.next()) {
		    	
		        
		        //lay ma hoa don gui dua vao ma yeu cau
		        String mayc = rs0.getString("MAYC");
		        
		        //System.out.print(" + ten ma yc:" +mayc);
		        
		        String plSql2 = "{call HR.GET_INFORM_RECEIPTS(?,?,?,?,?)}";
			    cstmt2 = con.prepareCall(plSql2);
			    cstmt2.registerOutParameter(1, OracleTypes.CURSOR);
			    cstmt2.setString(2, mayc);
			    cstmt2.registerOutParameter(3, java.sql.Types.VARCHAR);
			    cstmt2.registerOutParameter(4, java.sql.Types.VARCHAR);
			    cstmt2.registerOutParameter(5, java.sql.Types.VARCHAR);
			    cstmt2.executeQuery();
			    
			    ResultSet resultSet2 = (ResultSet)cstmt2.getObject(1);
		          
			    while(resultSet2.next()){
			    	
//					lay ma hoa don,ma tai xe, ma phuong tien
				    mahoadongui = resultSet2.getString("MAGH");
				    mataixe = resultSet2.getString("MATAIXETP");
				    maphuongtien = resultSet2.getString("MAPT");
				    
//				    System.out.print(" + ten ma hoa don gui:" +mahoadongui);
//				    System.out.print(" + ten tai xe:" +mataixe);
//				    System.out.print(" + MA phuong tien:" +maphuongtien);
				    
			       
//					lay ten nhan vien
	       
			        String plSql5 = "{call HR.GET_NAME_STAFF(?,?)}";
				    cstmt5 = con.prepareCall(plSql5);
				    
				    cstmt5.setString(1, mataixe);
				    cstmt5.registerOutParameter(2, java.sql.Types.VARCHAR);
				    cstmt5.execute();
   
			        //TEN NHAN VIEN
			        taixe = cstmt5.getString(2);
			        
//			        System.out.print(" + ten NHANVIEN:" +taixe);
			        

//			        lay ten phuong tien
		        
		        String plSql6 = "{call HR.GET_NAME_VEHICLE(?,?)}";
			    cstmt6 = con.prepareCall(plSql6);
			    
			    cstmt6.setString(1, maphuongtien);
			    cstmt6.registerOutParameter(2, java.sql.Types.VARCHAR);
			    cstmt6.execute();

// 				TEN PT
		        phuongtien = cstmt6.getString(2);
		        
//		        System.out.print(" + ten NHANVIEN:" +phuongtien);
		        
		        
//		        lay thong tin kien hang dua vao ma hoa don gui
		        String plSql3 = "{call HR.BOX_SENDER(?,?,?,?,?,?,?,?)}";
			    cstmt3 = con.prepareCall(plSql3);
			    
			    cstmt3.registerOutParameter(1, OracleTypes.CURSOR);
			    cstmt3.setString(2, mahoadongui);
			    cstmt3.registerOutParameter(3, java.sql.Types.VARCHAR);
			    cstmt3.registerOutParameter(4, java.sql.Types.VARCHAR);
			    cstmt3.registerOutParameter(5, java.sql.Types.VARCHAR);
			    cstmt3.registerOutParameter(6, java.sql.Types.VARCHAR);
			    cstmt3.registerOutParameter(7, java.sql.Types.VARCHAR);
			    cstmt3.registerOutParameter(8, java.sql.Types.VARCHAR);
			    
			    cstmt3.executeQuery();
				
				ResultSet rs3 = (ResultSet)cstmt3.getObject(1);
				
				while(rs3.next()){
					

			    	//lay ten khach hang gui
			    	String makh = rs3.getString("MAKHGUI");
			    	String plSql1 = "{call HR.GET_NAME_CUSTOM(?,?)}";
				    cstmt1 = con.prepareCall(plSql1);
				    cstmt1.setString(1, makh);
				    
				    cstmt1.registerOutParameter(2, java.sql.Types.VARCHAR);
				    cstmt1.execute();
				    
			        //ResultSet resultSet1 = stmt1.executeQuery("select * from hr.khachhang where makh= '"+makh+"'");
			        
			        // result: ten kh gui
			        khgui = cstmt1.getString(2);
			        //System.out.println("ten kh gui:" +khgui);
					
			        
					String makhnhan = rs3.getString("MAKHNHAN");
					String plSql4 = "{call HR.GET_NAME_CUSTOM(?,?)}";
				    cstmt4 = con.prepareCall(plSql4);
				    cstmt4.setString(1, makhnhan);
				    
				    cstmt4.registerOutParameter(2, java.sql.Types.VARCHAR);
				    cstmt4.execute();
	
			        // result: ten kh nhan
			        khnhan = cstmt4.getString(2);	        
			        //System.out.println("KH NHAN: "+khnhan);
					box = new Box(rs3.getString(1),rs3.getString(2),rs3.getString(3),rs3.getString(4),khgui,khnhan,phuongtien,taixe);
					list.add(box);
					
				}
		        
			  }
		        
		    }
			
		    return list;
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		return null;
	}
	
	public static List<Box> getRequireFromDB_KHNHAN(){
		try {
				Connection con = login.bridge();
				
				List<Box> list = new ArrayList<Box>();
				CallableStatement cstmt,cstmt1,cstmt2,cstmt3,cstmt4,cstmt5,cstmt6,cstmt7;
				Box box;
				String khgui,khnhan,taixe,phuongtien = null;
				String mahoadonnhan,mataixe,maphuongtien = null;
				
				String plSql = "{call hr.GET_INFORM_RECEIPTR(?,?,?,?)}";
				cstmt = con.prepareCall(plSql);
				
				cstmt.registerOutParameter(1, OracleTypes.CURSOR);
				cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
				cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
				cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
				cstmt.executeQuery();
				
				ResultSet rs = (ResultSet)cstmt.getObject(1);
				
				while(rs.next()){
					
					//ma hoa don nhan
					mahoadonnhan = rs.getString("MANH");
				    mataixe = rs.getString("MATAIXETP");
				    maphuongtien = rs.getString("MAPT");
				    
//				    System.out.print(" + ten ma hoa don NHAN:" +mahoadonnhan);
//				    System.out.print(" + MA tai xe:" +mataixe);
//				    System.out.print(" + MA phuong tien:" +maphuongtien);
			    
			       
				    //lay ten nhan vien
	       
			        String plSql5 = "{call HR.GET_NAME_STAFF(?,?)}";
				    cstmt5 = con.prepareCall(plSql5);
				    
				    cstmt5.setString(1, mataixe);
				    cstmt5.registerOutParameter(2, java.sql.Types.VARCHAR);
				    cstmt5.execute();

			        //TEN NHAN VIEN
			        taixe = cstmt5.getString(2);
			        
//			        System.out.print(" + ten NHANVIEN:" +taixe);
			                
			        
			        //lay ten nhan vien
			        
			        String plSql6 = "{call HR.GET_NAME_VEHICLE(?,?)}";
				    cstmt6 = con.prepareCall(plSql6);
				    
				    cstmt6.setString(1, maphuongtien);
				    cstmt6.registerOutParameter(2, java.sql.Types.VARCHAR);
				    cstmt6.execute();

			        //TEN NHAN VIEN
			        phuongtien = cstmt6.getString(2);

			        
					
					//lay thong tin kien hang dua vao ma hoa don gui
			        String plSql3 = "{call HR.BOX_RECEIVER(?,?,?,?,?,?,?,?)}";
				    cstmt3 = con.prepareCall(plSql3);
				    
				    cstmt3.registerOutParameter(1, OracleTypes.CURSOR);
				    cstmt3.setString(2, mahoadonnhan);
				    cstmt3.registerOutParameter(3, java.sql.Types.VARCHAR);
				    cstmt3.registerOutParameter(4, java.sql.Types.VARCHAR);
				    cstmt3.registerOutParameter(5, java.sql.Types.VARCHAR);
				    cstmt3.registerOutParameter(6, java.sql.Types.VARCHAR);
				    cstmt3.registerOutParameter(7, java.sql.Types.VARCHAR);
				    cstmt3.registerOutParameter(8, java.sql.Types.VARCHAR);
				    
				    cstmt3.executeQuery();
					
					ResultSet rs3 = (ResultSet)cstmt3.getObject(1);
					
					while(rs3.next()){
						
						String makhnhan = rs3.getString("MAKHNHAN");
						String plSql4 = "{call HR.GET_NAME_CUSTOM(?,?)}";
					    cstmt4 = con.prepareCall(plSql4);
					    cstmt4.setString(1, makhnhan);
					    
					    cstmt4.registerOutParameter(2, java.sql.Types.VARCHAR);
					    cstmt4.execute();
	
				        // result: ten kh nhan
				        khnhan = cstmt4.getString(2);	
				        
				        String makhgui = rs3.getString("MAKHGUI");
						String plSql7 = "{call HR.GET_NAME_CUSTOM(?,?)}";
					    cstmt7 = con.prepareCall(plSql7);
					    cstmt7.setString(1, makhgui);
					    
					    cstmt7.registerOutParameter(2, java.sql.Types.VARCHAR);
					    cstmt7.execute();
			  
				        // result: ten kh nhan
				        khgui = cstmt7.getString(2);	 
				        //System.out.println("KH NHAN: "+khgui);
				        
						box = new Box(rs3.getString(1),rs3.getString(2),rs3.getString(3),rs3.getString(4),khgui,khnhan,phuongtien,taixe);
						list.add(box);
					}
			        
					
				}
				return list;
			} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		return null;
	}
}
