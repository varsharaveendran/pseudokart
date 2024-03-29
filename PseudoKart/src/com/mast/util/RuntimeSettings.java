package com.mast.util;

public class RuntimeSettings {
	public static final String APPLICATION_NAME = "PseudoKart";
	public static final String VERSION_ID = " version 0.1"
			+ " dated October 28, 2012";
	public static final String SERVER_IP = "localhost";
	static String databaseName = "flipkart";
	public static String dbUserID = "root";
	// "" for apple Macbook Air
	public static String dbPassword = "test";
	public static String RUN_MODE = "Test"; // "Production"; //
	public static boolean IS_IN_DEBUG_MODE = true; // false; //
	static int portNo = 3306;// 5432; //
	public static String scriptSql[] = {
			" use flipkart;",
			"DROP TABLE IF EXISTS login;",

			"DROP TABLE IF EXISTS customer",

			"DROP TABLE IF EXISTS address",

			"DROP TABLE IF EXISTS bankProxy",

			"DROP TABLE IF EXISTS category",

			"DROP TABLE IF EXISTS product",

			"DROP TABLE IF EXISTS notifyme",

			"CREATE TABLE  login(" + "email varchar(30) NOT NULL,"
					+ "password varchar(20) NOT NULL,"
					+ "PRIMARY KEY (`email`));",

			"CREATE TABLE customer(" + "id int(3) auto_increment not null,"
					+ "firstName varchar(20)," + "lastName varchar(20),"
					+ "mobileNumber varchar(10),"
					+ "landlineNumber varchar(10)," + "gender varchar(6),"
					+ "email varchar(30),"
					+ "profileName varchar(20) not null,"
					+ "updatedProfileName varchar(20),"
					+ "CartAppendNo varchar(3)," + "primary key(id),"
					+ "foreign key (email) references login(email)" + ");",

			"create table adminlogin(email varchar(30),password varchar(30),level int(3),primary key(email));",

			"CREATE TABLE address("
					+ "address_id int(3) auto_increment not null,"
					+ "name varchar(40) not null,"
					+ "streetAdress varchar(216) not null,"
					+ "landmark varchar(200) not null,"
					+ "city varchar(50) not null,"
					+ "state varchar(50) not null,"
					+ "country varchar(10) default 'India',"
					+ "pin varchar(6) not null,"
					+ "phone varchar(10) not null,"
					+ "customer_id int(3) not null,"
					+ "primary key(address_id),"
					+ "foreign key (customer_id) references customer(id)"
					+ ");",

			"CREATE TABLE bankProxy("
					+ "accountId INT(10) auto_increment not null ,"
					+ "accountNo varchar(30) unique not null,"
					+ "accountName varchar(20) not null,"
					+ "cardNo varchar(16) unique not null,"
					+ "cardType varchar(10) not null,"
					+ "pin varchar(32) not null ,"
					+ "balance decimal(10,2) not null,"
					+ "expiryDate varchar(20) not null,"
					+ "cvvNo int(3) not null," + "primary key(accountId));",

			"create table category (categoryID varchar(20) NOT NULL,"
					+ "name varchar(30) NOT NULL,"
					+ "parentCatID varchar(20) NOT NULL,"
					+ "primary key(categoryID));",

			"create table product (productId varchar(20) NOT NULL,"
					+ "name varchar(30) NOT NULL,"
					+ "categoryID varchar(20) NOT NULL,"
					+ "price decimal(10,2),"
					+ "description varchar(100),"
					+ "imageUrl varchar(100),"
					+ "primary key(productId) ,"
					+ "foreign key (categoryID) references category(categoryID));",

			"create table notifyme(id int(10) auto_increment not null, email varchar(30), productId varchar(20), foreign key (productId) references product(productId), primary key(id));",

			"CREATE TABLE wallet(wallet_id int(10) unsigned AUTO_INCREMENT,"
					+ "customer_id int(3) not null,"
					+ "balance decimal(7,2) not null,"
					+ "foreign key (customer_id) references customer(id),"
					+ "primary key(wallet_id));",

			"CREATE TABLE voucher(voucher_id int(10) unsigned AUTO_INCREMENT,"
					+ "voucher_number int(10) not null,"
					+ "voucher_pin int(4) not null,"
					+ "balance decimal(7,2) not null," + "purchase_Date date,"
					+ "purchaser_Email varchar(40),"
					+ "primary key(voucher_id));",

			"create table productRatings(productratings_id int(10) unsigned AUTO_INCREMENT,"
					+ "productId varchar(20) UNIQUE NOT NULL,"
					+ "rating int(5) ,"
					+ "numberOfCustomers int(4),"
					+ "FOREIGN KEY (productId) references product(productId),"
					+ "primary key(productratings_id));",

			"create table orderDetails(orderID int(3) auto_increment not null,"
					+ "orderNo varchar(30)," + "cartNo varchar(10),"
					+ "email varchar(30)," + "orderstatus varchar(10),"
					+ "paidstatus varchar(10), " + "primary key(orderID), "
					+ "foreign key (email) references login(email));",

			"create table cart(cartId int(10) auto_increment not null,"
					+ "email varchar(30)," + "cartAppendNo varchar(3),"
					+ "productId varchar(20)," + "quantity int(5),"
					+ "foreign key(email) references login(email),"
					+ "foreign key(productId) references product(productId),"
					+ "primary key(cartId));",

			"create table userRatings(userRatings_id int(4) auto_increment,email varchar(30),productId varchar(20),ratings int(1),primary key(userRatings_id),foreign key (email) references login(email),foreign key (productId) references product(productId));",

			"create table stock ( stockId int(20) auto_increment not null,"
					+ "stockProductId varchar(20) unique not null ,"
					+ "foreign key (stockProductId) references product(productId),"
					+ "quantity int not null," + "primary key(stockId));" };
}
