USE [WalletDB]
GO
/****** Object:  Table [dbo].[CustomerProduct]    Script Date: 19/07/2023 07:11:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CustomerProduct](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [smallint] NOT NULL,
	[ProductID] [smallint] NOT NULL,
	[UnitPrice] [smallmoney] NOT NULL,
	[Quantity] [tinyint] NOT NULL,
	[OrderCode] [varchar](20) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
 CONSTRAINT [PK_CustomerProduct] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customers]    Script Date: 19/07/2023 07:11:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customers](
	[CustomerID] [smallint] IDENTITY(100,10) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[PhoneNumber] [nchar](11) NOT NULL,
	[Address] [varchar](100) NOT NULL,
	[WalletID] [nchar](10) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[Balance] [smallmoney] NOT NULL,
 CONSTRAINT [PK_Customers] PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 19/07/2023 07:11:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[ProductID] [smallint] IDENTITY(50,5) NOT NULL,
	[ProductName] [varchar](50) NOT NULL,
	[Description] [varchar](100) NULL,
	[UnitPrice] [smallmoney] NOT NULL,
	[QuantityInStock] [smallint] NOT NULL,
	[CreatedDate] [datetime] NULL,
	[IsAvailable] [bit] NOT NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CustomerProduct] ADD  CONSTRAINT [DF_CustomerProduct_UnitPrice]  DEFAULT ((0.0)) FOR [UnitPrice]
GO
ALTER TABLE [dbo].[CustomerProduct] ADD  CONSTRAINT [DF_CustomerProduct_Quantity]  DEFAULT ((0)) FOR [Quantity]
GO
ALTER TABLE [dbo].[Customers] ADD  DEFAULT ((0.0)) FOR [Balance]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_UnitPrice]  DEFAULT ((0.0)) FOR [UnitPrice]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_QuantityInStock]  DEFAULT ((0)) FOR [QuantityInStock]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_IsAvailable]  DEFAULT ((1)) FOR [IsAvailable]
GO
ALTER TABLE [dbo].[CustomerProduct]  WITH CHECK ADD  CONSTRAINT [FK_CustomerProduct_Customers] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customers] ([CustomerID])
GO
ALTER TABLE [dbo].[CustomerProduct] CHECK CONSTRAINT [FK_CustomerProduct_Customers]
GO
ALTER TABLE [dbo].[CustomerProduct]  WITH CHECK ADD  CONSTRAINT [FK_CustomerProduct_Products] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Products] ([ProductID])
GO
ALTER TABLE [dbo].[CustomerProduct] CHECK CONSTRAINT [FK_CustomerProduct_Products]
GO
