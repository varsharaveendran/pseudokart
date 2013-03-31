package com.flipkart.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mast.util.DB;
import com.mast.util.MyLog;
import com.opensymphony.xwork2.ActionContext;

public class Address {
	private int address_id;
	private String name=" ";
	private String streetAddress=" ";
	private String landmark= " ";
	private String city=" ";
	private String state=" ";
	private String country="India";
	private String pin=" ";
	private String phone=" ";


	public int insert() {
		String email=(String)ActionContext.getContext().getSession().get("email");

		Customer cust=Customer.findOne("where email='"+email+"'");
		if(cust!=null)
		{
			String insertSQL = "insert into address"
					+ "(name,streetAdress,landmark,city,state,country,pin,phone,customer_id) " + "values('" + name
					+ "', '" + streetAddress + "' , '" + landmark + "','"+ city +"','" + state +"','"+country+"','" +
					pin +"','"+ phone+"',"+cust.getId()+");";
			System.out.println(insertSQL);
			return DB.update(insertSQL);
		}
		else
			return -1;
	}

public static ArrayList<Address> findAll()
{
	ArrayList<Address> addr_list=new ArrayList<Address>();
	String email=(String)ActionContext.getContext().getSession().get("email");
	ResultSet resultSet = null;
	Customer cust=Customer.findOne("where email='"+email+"'");
	Connection connection = DB.getConnection();
	String findQuery = "select * from address where customer_id="+cust.getId()+";";
	resultSet = DB.readFromDB(findQuery, connection);
	try {
		while (resultSet.next()) {
			Address addr=new Address();
			addr.setAddress_id(resultSet.getInt("address_id"));
			addr.setName(resultSet.getString("name"));
			addr.setStreetAddress(resultSet.getString("streetAdress"));
			addr.setLandmark(resultSet.getString("landmark"));
			addr.setCity(resultSet.getString("city"));
			addr.setState(resultSet.getString("state"));
			addr.setCountry(resultSet.getString("country"));
			addr.setPhone(resultSet.getString("phone"));
			addr.setPin(resultSet.getString("pin"));
		  addr_list.add(addr);
		}
	} catch (SQLException e) {
        MyLog.myCatch("Customer.java",50, e);
		e.printStackTrace();
	}
	DB.close(resultSet);
	DB.close(connection);
	return addr_list;
}

	public static boolean hasMultipleAddrress()
	{
		
		String email=(String)ActionContext.getContext().getSession().get("email");
		ResultSet resultSet = null;
		Customer cust=Customer.findOne("where email='"+email+"'");
		Connection connection = DB.getConnection();
		String findQuery = "select * from address where customer_id="+cust.getId()+";";
		resultSet = DB.readFromDB(findQuery, connection);
	   if(resultSet==null)
		   return false;
	   else
		   return true;
	}


	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}



}
