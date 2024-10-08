USE [master]
GO
/****** Object:  Database [JDBC]    Script Date: 10-07-2024 10:42:34 PM ******/
CREATE DATABASE [JDBC]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'JDBC', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\JDBC.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'JDBC_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\JDBC_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [JDBC] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [JDBC].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [JDBC] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [JDBC] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [JDBC] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [JDBC] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [JDBC] SET ARITHABORT OFF 
GO
ALTER DATABASE [JDBC] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [JDBC] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [JDBC] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [JDBC] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [JDBC] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [JDBC] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [JDBC] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [JDBC] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [JDBC] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [JDBC] SET  ENABLE_BROKER 
GO
ALTER DATABASE [JDBC] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [JDBC] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [JDBC] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [JDBC] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [JDBC] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [JDBC] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [JDBC] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [JDBC] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [JDBC] SET  MULTI_USER 
GO
ALTER DATABASE [JDBC] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [JDBC] SET DB_CHAINING OFF 
GO
ALTER DATABASE [JDBC] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [JDBC] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [JDBC] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [JDBC] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [JDBC] SET QUERY_STORE = ON
GO
ALTER DATABASE [JDBC] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [JDBC]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 10-07-2024 10:42:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[id] [int] NOT NULL,
	[productID] [int] NULL,
	[unitPrice] [float] NULL,
	[quantity] [int] NULL,
	[orderID] [nvarchar](50) NULL,
	[total] [float] NULL,
 CONSTRAINT [PK_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 10-07-2024 10:42:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[orderID] [nvarchar](50) NOT NULL,
	[date] [datetime] NULL,
	[customer] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
	[email] [nvarchar](50) NULL,
	[total] [float] NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 10-07-2024 10:42:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[sku] [int] NULL,
	[name] [nvarchar](50) NULL,
	[description] [nvarchar](200) NULL,
	[quantity] [int] NULL,
	[price] [float] NULL,
	[status] [bit] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 10-07-2024 10:42:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration](
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NULL,
	[lastname] [varchar](50) NULL,
	[isAdmin] [bit] NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Product] ([sku], [name], [description], [quantity], [price], [status]) VALUES (1, N'Java', N'Java Book', 5, 10, 1)
INSERT [dbo].[Product] ([sku], [name], [description], [quantity], [price], [status]) VALUES (2, N'Spring ', N'Spring Framework', 10, 3, 1)
INSERT [dbo].[Product] ([sku], [name], [description], [quantity], [price], [status]) VALUES (3, N'JSP', N'JavaEE', 9, 4, 1)
INSERT [dbo].[Product] ([sku], [name], [description], [quantity], [price], [status]) VALUES (4, N'Servlet', N'JavaEE', 6, 3, 0)
INSERT [dbo].[Product] ([sku], [name], [description], [quantity], [price], [status]) VALUES (5, N'Impclicit Obj', N'JSP Tutorial', 0, 3, 1)
INSERT [dbo].[Product] ([sku], [name], [description], [quantity], [price], [status]) VALUES (6, N'Tomcat', N'Container ', 4, 20, 1)
GO
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'minhdat', N'123', N'vu', 1)
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'quocbao', N'123456', N'hoquocbao', 0)
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'quocbao123', N'123456', N'baoho', 0)
INSERT [dbo].[Registration] ([username], [password], [lastname], [isAdmin]) VALUES (N'thanhdo', N'55555', N'tong', 0)
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK__OrderDeta__order__29221CFB] FOREIGN KEY([orderID])
REFERENCES [dbo].[Orders] ([orderID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK__OrderDeta__order__29221CFB]
GO
USE [master]
GO
ALTER DATABASE [JDBC] SET  READ_WRITE 
GO
