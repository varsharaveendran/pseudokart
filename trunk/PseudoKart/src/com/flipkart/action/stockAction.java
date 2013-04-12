package com.flipkart.action;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.flipkart.model.Product;
import com.flipkart.model.Stock;
import com.flipkart.model.stock_prod;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class stockAction extends ActionSupport{

	ArrayList<Product> list;
	ArrayList<Product> selectedProds=new ArrayList<Product>();
	ArrayList<Stock> stock_list=new ArrayList<Stock>();
	ArrayList<stock_prod> fin=new ArrayList<stock_prod>(); 
	String[] productID;
	String to;
	String subject;
	String msg;
	String err_msg="";
	private String stock_quantity;
	ArrayList<Product> stklist;
	
	Map session = ActionContext.getContext().getSession();
	
	
	public ArrayList<Product> getStklist() {
		return stklist;
	}

	public void setStklist(ArrayList<Product> stklist) {
		this.stklist = stklist;
	}

	public String getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(String stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public String execute()
	{
		list=Product.findAll("");
		session.put("stock_list", list);
		if(!err_msg.isEmpty())
			addActionError(err_msg);
		return SUCCESS;
	}
	
	public String back()
	{
		System.out.println("Retrurning....");
		return "back";
	}
	
	public String report()
	{
		System.out.println("Reporting....");
		return "done";
	}
	
	public String send_mail()
	{
		 try
		 {
			
			 String host = "smtp.gmail.com";
			 String from = "com.iiitb.pseudokart";
			 String pass = "kart@123";
			 Properties props = System.getProperties();
			 props.put("mail.smtp.starttls.enable", "true"); // added this line
			 props.put("mail.smtp.host", host);
			 props.put("mail.smtp.user", from);
			 props.put("mail.smtp.password", pass);
			 props.put("mail.smtp.port", "587");
			 props.put("mail.smtp.auth", "true");

			 String to = this.to; // added this line
			 System.out.println("to :"+to);
			 Session session = Session.getDefaultInstance(props, null);
	    
			 MimeMessage message = new MimeMessage(session);
			 message.setFrom(new InternetAddress(from));

			 InternetAddress toAddress = new InternetAddress(to);
	   // InternetAddress[] toAddress = new InternetAddress[to.length];
	    // To get the array of addresses
	   // for( int i=0; i < to.length; i++ ) { // changed from a while loop
	      //  toAddress = new InternetAddress();
	    //}
			 System.out.println(Message.RecipientType.TO);
	   
	   // for( int i=0; i < toAddress.length; i++) { // changed from a while loop
			 message.addRecipient(Message.RecipientType.TO, toAddress);
			 System.out.println(toAddress);
	    //}
			 message.setSubject(this.subject);
			 message.setText(this.msg);
	    
			 Transport transport = session.getTransport("smtp");
			 transport.connect(host, from, pass);
			 transport.sendMessage(message, message.getAllRecipients());
			 transport.close();  
	   }
	 catch(Exception e)
	 {
		 System.out.println(e);
	 }

		
		return "sent";
		
	}
	
	public String getProdStock()
	{
		if(productID!=null)
			{
			for(int i=0;i<productID.length;i++)
			{
			         Stock s=Stock.findOne("where stockProductID='"+productID[i]+"'");
			         Product p=Product.findProduct("where productID='"+productID[i]+"'");
			         if(s!=null && p!=null)
			        	 {
			        	   stock_prod obj=new stock_prod();
			        	   obj.setProdID(p.getProdid());
			        	   obj.setQuantity(s.getQuantity());
			        	   obj.setProdName(p.getName());
			        	   fin.add(obj);
			        	 }
			}
			//System.out.println("*****Stock List*****"+stock_list.size());
			
			
			return "ok";
	}
	else
	{
		 		this.setErr_msg("Choose minimum one product");
				return "empty";
	}
}
	
	
	
	@SuppressWarnings("unchecked")
	public String update_stock(){
		stklist = (ArrayList<Product>)session.get("stock_list");
	for(int i=0 ; i<stklist.size() ; i++)
		System.out.println(stklist.get(i).getProdid());
		
		System.out.println("QUANTITY " + stock_quantity);
		
		String[] qty = stock_quantity.split(",");
		for (int i = 0; i<qty.length;i++)
		{
		//	System.out.println(Integer.parseInt(qty[i]));
			try
			{
			int quantity = Integer.parseInt(qty[i].trim());
			System.out.println(stklist.get(i).getProdid());
			
			String sql =  "where stockProductId='" + stklist.get(i).getProdid() + "';";
			Stock s = Stock.findOne(sql);
			
			if(s == null)
			{
				System.out.println("Not there");
				sql = "'" + stklist.get(i).getProdid() + "'," + quantity ;
				Stock.insertStock(sql);
				
			}
			
			else{
			sql =  quantity + " where stockProductId='" + stklist.get(i).getProdid() + "'";
			Stock.updateStock(sql);
			}
			
			notify_customer(stklist.get(i).getProdid());

			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Blank");
			}
			/*if(qty[i].equals(" "))
			{
				System.out.println(stklist.get(i).getProdid());
			}*/
		}
		
		
		return "success";
	}
	private void notify_customer(String prodid) {

		String sql =  "where productId='" + prodid + "';";
		ArrayList<String> emailids = Stock.findAllNotifyMe(sql);
		for(int i=0 ; i < emailids.size() ; i++)
		{
			System.out.println(emailids.get(i));
		}
		
		
		for(int i =0 ; i < emailids.size();i++)
		{
		
		 try
		 {
			
			 String host = "smtp.gmail.com";
			 String from = "com.iiitb.pseudokart";
			 String pass = "kart@123";
			 Properties props = System.getProperties();
			 props.put("mail.smtp.starttls.enable", "true"); // added this line
			 props.put("mail.smtp.host", host);
			 props.put("mail.smtp.user", from);
			 props.put("mail.smtp.password", pass);
			 props.put("mail.smtp.port", "587");
			 props.put("mail.smtp.auth", "true");

			 String to = emailids.get(i); // added this line
			 System.out.println("to :"+to);
			 Session session = Session.getDefaultInstance(props, null);
	    
			 MimeMessage message = new MimeMessage(session);
			 message.setFrom(new InternetAddress(from));

			 InternetAddress toAddress = new InternetAddress(to);
	   // InternetAddress[] toAddress = new InternetAddress[to.length];
	    // To get the array of addresses
	   // for( int i=0; i < to.length; i++ ) { // changed from a while loop
	      //  toAddress = new InternetAddress();
	    //}
			 System.out.println(Message.RecipientType.TO);
	   
	   // for( int i=0; i < toAddress.length; i++) { // changed from a while loop
			 message.addRecipient(Message.RecipientType.TO, toAddress);
			 System.out.println(toAddress);
	    //}
			 message.setSubject("Flipkart : Product is in Stock!!! ");
			 message.setText("Hello!! You will be delighted to know that your product is in stock--Please login to buy :)");
	    
			 Transport transport = session.getTransport("smtp");
			 transport.connect(host, from, pass);
			 transport.sendMessage(message, message.getAllRecipients());
			 transport.close();  
			 System.out.println("Mail sent....");
	   }
	 catch(Exception e)
	 {
		 System.out.println(e);
	 }
		 
		
		 
		}
		
		 Stock.deleteNotifyme(sql);	
		
	}

	public ArrayList<Product> getList() {
		return list;
	}
	public void setList(ArrayList<Product> list) {
		this.list = list;
	}
	public String[] getProductID() {
		return productID;
	}
	public void setProductID(String[] productID) {
		this.productID = productID;
	}

	public ArrayList<Stock> getStock_list() {
		return stock_list;
	}

	public void setStock_list(ArrayList<Stock> stock_list) {
		this.stock_list = stock_list;
	}

	public ArrayList<Product> getSelectedProds() {
		return selectedProds;
	}

	public void setSelectedProds(ArrayList<Product> selectedProds) {
		this.selectedProds = selectedProds;
	}

	public ArrayList<stock_prod> getFin() {
		return fin;
	}

	public void setFin(ArrayList<stock_prod> fin) {
		this.fin = fin;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getErr_msg() {
		return err_msg;
	}

	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	
}