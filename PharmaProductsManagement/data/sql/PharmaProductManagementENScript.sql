CREATE DATABASE PharmaProductsManagement;

USE PharmaProductsManagement;

CREATE TABLE Company
(
	CompanyName VARCHAR(50) NOT NULL PRIMARY KEY, 
    CompanyAddress VARCHAR(50)NOT NULL
);

CREATE TABLE Category
(
	CategoryId INT NOT NULL PRIMARY KEY, 
	CategoryName VARCHAR(50) NOT NULL,
    CompanyName VARCHAR(50) NOT NULL, 
	foreign key(CompanyName) references Company(CompanyName) on delete cascade on update cascade
);

CREATE TABLE Product
(
	ProductName VARCHAR(50) NOT NULL PRIMARY KEY, 
    Price FLOAT NOT NULL
);

CREATE TABLE Sell
(
	CategoryId INT NOT NULL ,
	ProductName VARCHAR(50) NOT NULL, 
    Quantity INT NOT NULL,
	ExpDate DATE NOT NULL,
	foreign key(CategoryId) references Category(CategoryId) on delete cascade on update cascade,
	foreign key(ProductName) references Product(ProductName) on delete cascade on update cascade
);

CREATE TABLE LoginData
(
	Username VARCHAR(50) NOT NULL PRIMARY KEY, 
    Password VARCHAR(50) NOT NULL
);

INSERT INTO LoginData VALUES('username','pass'),
							('dan','parola');

INSERT INTO Company VALUES	('MedDep','USA'),
							('Sc Liv Med','Romania'),
							('AC Helcor','Germany'),
							('Troyapharm','UK'),
							('Polfa - Pabianice','UK');

INSERT INTO Category VALUES(4,'Medicines','MedDep'),
						   	(5,'Personal Care','MedDep'),
							(6,'Cosmetics','MedDep'),
							(7,'Cosmetics','Sc Liv Med'),
							(8,'Personal Care','Sc Liv Med'),
							(9,'Medical-Devices','Sc Liv Med'),
							(10,'Vitamin-Supplements','Troyapharm'),
							(12,'Vitamin-Supplements','AC Helcor'),
							(23,'Medicines','Troyapharm'),
							(43,'Medicines','Polfa - Pabianice');


INSERT INTO Product VALUES  ('Activit magneziu',2),
							('Tonometer',3),
							('Centrum A',3000),
							('Massage Equipment',3),
							('Tooth Paste',304),
							('Alcoholometer',3),
							('Nurofen Forte',100),
							('ACC',21),
							('Milk&Sugar Protein',2),
							('Dental articulator',3),
							('Aerosol device',4),
							('Milk&Sugar Supliments',100),
							('Perfume One Million',3000),
							('Cream foundation',1000),
							('MultiHELP komplex x',2.7),
							('Magnesium 300',3.4),
							('Rotating Brush Hair',1210),
							('Sampon Head&Shoulders',1325),
							('ArtroHelp Forte',61.21),
							('Walmark Memo Plus ',32),
							('Actival 50room furniture',48),
							('Actival 50',29),
							('ADDITIVA Energie Drink',95),
							('W Proenzi ArtroStop',2.5);


INSERT INTO  Sell VALUES	(12,'Activit magneziu',2,'2017-11-11'),
							(12,'ADDITIVA Energie Drink',3,'2017-11-11'),
							(12,'Centrum A',1,'2017-11-11'),
							(12,'MultiHELP komplex x',100,'2017-11-11'),
							(12,'Magnesium 300',50,'2017-11-11'),
							(23,'Nurofen Forte',5,'2017-11-11'),
							(23,'ACC',4,'2017-11-11'),
							(12,'Milk&Sugar Supliments',2,'2017-11-11'),
							(43,'W Proenzi ArtroStop',20,'2017-11-11'),
							(43,'ArtroHelp Forte',1000,'2017-11-11'),
							(10,'Milk&Sugar Protein',15,'2017-11-11'),
							(10,'Walmark Memo Plus ',5,'2017-11-11'),
							(9,'Tonometer',7,'2017-11-11'),
							(9,'Massage Equipment',4,'2017-11-11'),
							(9,'Alcoholometer',3,'2017-11-11'),
							(9,'Dental articulator',3,'2017-11-11'),
							(9,'Aerosol device',10,'2017-11-11'),
							(8,'Rotating Brush Hair',5,'2017-11-11'),
							(8,'Sampon Head&Shoulders',6,'2017-11-11'),
							(8,'Tooth Paste',4,'2017-11-11'),
							(5,'Tooth Paste',7,'2017-11-11'),
							(5,'Rotating Brush Hair',1,'2017-11-11'),
							(5,'Sampon Head&Shoulders',4,'2017-11-11'),
							(7,'Perfume One Million',4,'2017-11-11'),
							(6,'Perfume One Million',5,'2017-11-11'),
							(7,'Cream foundation',6,'2017-11-11'),
							(6,'Cream foundation',4,'2017-11-11'),
							(10,'Actival 50',6,'2017-11-11'),
							(10,'Magnesium 300',3,'2017-11-11'),
							(4,'W Proenzi ArtroStop',5,'2017-11-11'),
							(4,'ArtroHelp Forte',30,'2017-11-11'),
							(10,'Centrum A',4,'2017-11-11');
