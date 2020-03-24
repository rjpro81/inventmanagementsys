
CREATE TABLE Supplier (
  suppNo INT CONSTRAINT suppNoRequired NOT NULL,
  suppName VARCHAR (50) CONSTRAINT suppNameRequired NOT NULL,
  suppAddress VARCHAR (70),
  suppCity VARCHAR (50),
  suppState VARCHAR (15),
  suppZip VARCHAR(10),
  suppEmail VARCHAR (20),
  suppPhone VARCHAR (20),
  CONSTRAINT SupplierPK PRIMARY KEY (suppNo)
);

INSERT INTO Supplier (
  suppNo, suppName, suppCity, suppState) VALUES
  (686947, 'Lakeshore Learning', 'Altamonte Springs', 'Florida'), 
  (878943, 'School Specialty', 'Orlando', 'Florida'),
  (678393, 'Walmart', 'Kissimmee', 'Florida'),
  (598434, 'Teachers Depot', 'Lakeland', 'Florida');

CREATE TABLE RFID_Reader (
  partNo INT CONSTRAINT partNoRequired NOT NULL, 
  location VARCHAR (50),
  CONSTRAINT RFID_ReaderPK PRIMARY KEY (partNo)
);

INSERT INTO RFID_Reader (
  partNo) VALUES 
  (35872117),
  (87987198),
  (56901387),
  (76834713),
  (29856195);

CREATE TABLE Item (
  itemNo INT CONSTRAINT itemNoRequired NOT NULL,
  itemName VARCHAR (50) CONSTRAINT itemNameRequired NOT NULL,
  itemQOH INT,
  itemPrice DECIMAL (10, 2), 
  itemExpDate DATE,
  suppNo INT CONSTRAINT suppNoRequired NOT NULL,
  CONSTRAINT ItemPK PRIMARY KEY (itemNo),
  CONSTRAINT ItemFK FOREIGN KEY (suppNo) REFERENCES Supplier ON DELETE CASCADE
);

INSERT INTO Item (
  itemNo, itemName, itemPrice, suppNo) VALUES 
  (8934784, 'Erasers', 2.25, 686947),
  (9494743, 'Markers', 1.75, 878943),
  (7585933, 'Marker Boards', 3.99, 686947),
  (9784323, 'Pencils', 0.75, 686947),
  (5783439, 'Colored Pencils', 1.99, 686947),
  (8943845, 'iPad', 199.00, 878943);


CREATE TABLE Employee (
  empNo INT CONSTRAINT empNoRequired NOT NULL,
  empFirstName VARCHAR (50) CONSTRAINT empFirstNameRequired NOT NULL,
  empLastName VARCHAR (50) CONSTRAINT empLastNameRequired NOT NULL,
  empEmail VARCHAR (50),
  empPhone VARCHAR (15), 
  empUserName VARCHAR (50) CONSTRAINT empUserNameRequired NOT NULL,
  empPassword VARCHAR (50) CONSTRAINT empPasswordRequired NOT NULL,
  CONSTRAINT EmployeePK PRIMARY KEY (empNo)
);

INSERT INTO Employee (
  empNo, empFirstName, empLastName, empUserName, empPassword) VALUES 
  (57840814, 'Cindy', 'Jacobs', 'cjacobs', '85783298'),
  (89438784, 'Mark', 'Thomas','mthomas', '47834543'),
  (24845948, 'Ralph', 'Julsaint', 'rjulsaint', '0414'),
  (57893221, 'Linda', 'Page','lpage', '98431894');

CREATE TABLE Order_Table (
  orderNo INT CONSTRAINT orderNoRequired ON NULL AS IDENTITY,
  itemNo INT CONSTRAINT itemNoRequired NOT NULL,
  itemQty INT CONSTRAINT itemQtyRequired NOT NULL,
  suppNo INT CONSTRAINT suppNoRequired NOT NULL,
  CONSTRAINT Order_TablePK PRIMARY KEY (orderNo),
  CONSTRAINT Order_TableFK FOREIGN KEY (suppNo) REFERENCES Employee ON DELETE CASCADE
  CONSTRAINT Order_TableFK2 FOREIGN KEY (itemNo) REFERENCES Item ON DELETE CASCADE
);

INSERT INTO Order_Table (
  orderNo, orderDate, empNo, ordShippingAddress, ordCity, ordState, ordZip) VALUES 
  (5783125, '2018-12-03', 57840814, '2500 West Michigan Avenue', 'Liberty City', 'VA', 24589),
  (9578431, '2018-11-25', 57840814, '2500 West Michigan Avenue', 'Liberty City', 'VA', 24589),
  (5743125, '2018-12-03', 57840814, '2500 West Michigan Avenue', 'Liberty City', 'VA', 24589);

CREATE TABLE Invoice (
  invoiceNo INT CONSTRAINT invoiceNoRequired NOT NULL,
  suppNo INT CONSTRAINT suppNoRequired NOT NULL,
  invoiceDate DATE CONSTRAINT invoiceDateRequired NOT NULL,
  CONSTRAINT InvoicePK PRIMARY KEY (invoiceNo),
  CONSTRAINT InvoiceFK FOREIGN KEY (suppNo) REFERENCES Supplier ON DELETE CASCADE
);

INSERT INTO Invoice (
  invoiceNo, suppNo, invoiceDate) VALUES
  (578932592, 686947, '2018-12-05');


CREATE TABLE Orderline (
  itemNo INT CONSTRAINT itemNoRequired NOT NULL,
  orderNo INT CONSTRAINT orderNoRequired NOT NULL,
  orderQty INT CONSTRAINT orderQtyRequired NOT NULL,
  invoiceNo INT CONSTRAINT invoiceNoRequired NOT NULL,
  CONSTRAINT OrderlinePK PRIMARY KEY (itemNo,orderNo),
  CONSTRAINT OrderlineFK FOREIGN KEY (invoiceNo) REFERENCES Invoice ON DELETE CASCADE
);

INSERT INTO Orderline (
  itemNo, orderNo, orderQty, invoiceNo) VALUES
  (7585933, 5783125, 50, 578932592);


