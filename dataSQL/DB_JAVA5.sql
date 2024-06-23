

-- Tạo bảng Categories
CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY IDENTITY,
    CategoryName NVARCHAR(100) NOT NULL,
    Description nvarchar(max)
);

--Tạo bảng Users
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    FullName NVARCHAR(100),
    Email VARCHAR(100) NOT NULL,
    Phone VARCHAR(20),
    Admin BIT NOT NULL DEFAULT 0
);

-- Tạo bảng Products
CREATE TABLE Products (
    ProductID INT PRIMARY KEY IDENTITY,
    ProductName NVARCHAR(150) NOT NULL,
    Description NVARCHAR(MAX),
    Price FLOAT NOT NULL,
	Discount FLOAT,
    Quantity INT NOT NULL,
	Image VARCHAR(100),
	Status INT,
    CategoryID INT,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);
-- Tạo bảng Orders
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY IDENTITY,
    OrderDate DATETIME DEFAULT GETDATE(),
    UserID INT,
    TotalAmount FLOAT NOT NULL,
    Status INT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Tạo bảng chi tiết order
CREATE TABLE OrderDetails (
    OrderDetailID INT PRIMARY KEY IDENTITY,
    OrderID INT,
    ProductID INT,
    Quantity INT NOT NULL,
    UnitPrice FLOAT NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

DELETE FROM Orders;
DBCC CHECKIDENT ('Orders', RESEED, 0);