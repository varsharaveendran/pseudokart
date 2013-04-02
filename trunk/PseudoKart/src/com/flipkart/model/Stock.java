package com.flipkart.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mast.util.DB;
import com.mast.util.MyLog;

public class Stock {

	int stockID;
	String stockProductID;
	int quantity;
	public int getStockID() {
		return stockID;
	}
	public void setStockID(int stockID) {
		this.stockID = stockID;
	}
	public String getStockProductID() {
		return stockProductID;
	}
	public void setStockProductID(String stockProductID) {
		this.stockProductID = stockProductID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	 public static Stock findOne(String selectionModifier) {
		 Stock s= s=new Stock();
         ResultSet rs = null;
         String query = "select * " +
                         "from stock " + selectionModifier;
         System.out.println(query);
         Connection connection = DB.getConnection();
         rs = DB.readFromDB(query, connection);
         try {
                 if(rs.next()) {
                        
                         s.stockID=rs.getInt("stockID");
                         s.stockProductID=rs.getString("stockProductID");
                         s.quantity=rs.getInt("quantity");
                         }
         } catch (SQLException e) {
         MyLog.myCatch("Product.java",50, e);
                 e.printStackTrace();
         }
         DB.close(rs);
         DB.close(connection);
         return s;
 }


	
}