package com.flipkart.action;

import java.util.ArrayList;
import java.util.Map;

import com.flipkart.model.Cart;
import com.flipkart.model.Order;
import com.flipkart.model.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class orderStatusAction extends ActionSupport{
	//orderdetails table
	private int orderId;
	private String orderNo;
	private String cartNo;//same as cart append number
	private String email;
	private String orderstatus;
	private String paidstatus;
	
	//cart table
	private int cartId;
	private String productId;
	private int quantity;
	private String cartAppendNo;
	
	//product table
	private String name;
	private Double price;
	
	Order order=new Order();
	ArrayList<Cart> cart=new ArrayList<Cart>();
	Product product =new Product();
	
	Map session=ActionContext.getContext().getSession();
	
	public String execute(){
		System.out.println("in order status execute.....");
		email=(String)session.get("email");
		String mod = "where orderNo='" + orderNo + "'" + "and email='" + email + "'" + "and paidstatus='Y'";
		order=Order.findOne(mod);
		if(order==null){
			return "error";
		}
		//System.out.println(order.getCartNo());
		//System.out.println(order.getEmail());
		
		return SUCCESS;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCartNo() {
		return cartNo;
	}

	public void setCartNo(String cartNo) {
		this.cartNo = cartNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getPaidstatus() {
		return paidstatus;
	}

	public void setPaidstatus(String paidstatus) {
		this.paidstatus = paidstatus;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCartAppendNo() {
		return cartAppendNo;
	}

	public void setCartAppendNo(String cartAppendNo) {
		this.cartAppendNo = cartAppendNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ArrayList<Cart> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Cart> cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}