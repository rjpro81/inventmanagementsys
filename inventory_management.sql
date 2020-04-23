/**
 * Author:  Ralph Julsaint
 * Created: Apr 19, 2020
 */

DROP TABLE Invoice;
DROP TABLE Order_Table;
DROP TABLE App_User;
DROP TABLE Item;
DROP TABLE Customer;
DROP TABLE Activity;


CREATE TABLE Customer (
  customerNo INT CONSTRAINT customerNoRequired NOT NULL,
  customerName VARCHAR (50) CONSTRAINT customerNameRequired NOT NULL,
  customerAddress VARCHAR (70),
  customerCity VARCHAR (50),
  customerState VARCHAR (15),
  customerZip VARCHAR(10),
  customerEmail VARCHAR (20),
  customerPhone VARCHAR (20),
  CONSTRAINT CustomerPK PRIMARY KEY (customerNo)
);

INSERT INTO Customer (
  customerNo, customerName, customerCity, customerState) VALUES
  (686947, 'Lakeshore Learning', 'Altamonte Springs', 'Florida'), 
  (878943, 'School Specialty', 'Orlando', 'Florida'),
  (678393, 'Walmart', 'Kissimmee', 'Florida'),
  (598434, 'Teachers Depot', 'Lakeland', 'Florida');

CREATE TABLE Item (
  itemNo INT CONSTRAINT itemNoRequired NOT NULL,
  itemName VARCHAR (50) CONSTRAINT itemNameRequired NOT NULL,
  itemQOH INT,
  itemPrice DECIMAL (10, 2), 
  itemExpDate DATE,
  customerNo INT CONSTRAINT customerNoRequired NOT NULL,
  CONSTRAINT ItemPK PRIMARY KEY (itemNo),
  CONSTRAINT ItemFK FOREIGN KEY (customerNo) REFERENCES Customer ON DELETE CASCADE
);

INSERT INTO Item (
  itemNo, itemName, itemPrice, customerNo) VALUES 
  (8934784, 'Erasers', 2.25, 686947),
  (9494743, 'Markers', 1.75, 878943),
  (7585933, 'Marker Boards', 3.99, 686947),
  (9784323, 'Pencils', 0.75, 686947),
  (5783439, 'Colored Pencils', 1.99, 686947),
  (8943845, 'iPad', 199.00, 878943);


CREATE TABLE App_User (
  userNo INT CONSTRAINT userNoRequired NOT NULL,
  userFirstName VARCHAR (50) CONSTRAINT userFirstNameRequired NOT NULL,
  userLastName VARCHAR (50) CONSTRAINT userLastNameRequired NOT NULL,
  userEmail VARCHAR (50),
  userPhone VARCHAR (15), 
  userName VARCHAR (50) CONSTRAINT userNameRequired NOT NULL,
  userPassword VARCHAR (50) CONSTRAINT userPasswordRequired NOT NULL,
  CONSTRAINT UserPK PRIMARY KEY (userNo)
);

INSERT INTO App_User (
  userNo, userFirstName, userLastName, userName, userPassword) VALUES 
  (57844, 'Cindy', 'Jacobs', 'cjacobs', '85783298'),
  (89784, 'Mark', 'Thomas','mthomas', '47834543'),
  (45948, 'Marquise', 'Jacob', 'mjcob', '7438115'),
  (93221, 'Linda', 'Page','lpage', '98431894');

CREATE TABLE Order_Table (
  orderNo INT CONSTRAINT orderNoRequired NOT NULL,
  orderDate DATE CONSTRAINT orderDateRequired NOT NULL,
  userNo INT CONSTRAINT orderUserNoRequired NOT NULL,
  ordShippingAddress VARCHAR (100) CONSTRAINT orderShippingAddressRequired NOT NULL,
  ordCity VARCHAR (50) CONSTRAINT ordCityRequired NOT NULL,
  ordState VARCHAR (12) CONSTRAINT ordStateRequired NOT NULL,
  ordZip INT CONSTRAINT ordZipRequired NOT NULL,
  CONSTRAINT Order_TablePK PRIMARY KEY (orderNo),
  CONSTRAINT Order_TableFK FOREIGN KEY (userNo) REFERENCES App_User ON DELETE CASCADE
);

INSERT INTO Order_Table (
  orderNo, orderDate, userNo, ordShippingAddress, ordCity, ordState, ordZip) VALUES 
  (5783125, '2018-12-03', 57844, '2500 West Michigan Avenue', 'Liberty City', 'VA', 24589),
  (9578431, '2018-11-25', 45948, '2500 West Michigan Avenue', 'Liberty City', 'VA', 24589),
  (5743125, '2018-12-03', 93221, '2500 West Michigan Avenue', 'Liberty City', 'VA', 24589);

CREATE TABLE Invoice (
  invoiceNo INT CONSTRAINT invoiceNoRequired NOT NULL,
  customerNo INT CONSTRAINT customerNoRequired NOT NULL,
  invoiceDate DATE CONSTRAINT invoiceDateRequired NOT NULL,
  CONSTRAINT InvoicePK PRIMARY KEY (invoiceNo),
  CONSTRAINT InvoiceFK FOREIGN KEY (customerNo) REFERENCES Customer ON DELETE CASCADE
);

INSERT INTO Invoice (
  invoiceNo, customerNo, invoiceDate) VALUES
  (578932592, 686947, '2018-12-05');

CREATE TABLE Activity(
  activityId INT CONSTRAINT activityIdRequired NOT NULL GENERATED ALWAYS AS IDENTITY,
  activity VARCHAR (100) CONSTRAINT activityRequired NOT NULL,
  CONSTRAINT activityPK PRIMARY KEY (activityId)
);