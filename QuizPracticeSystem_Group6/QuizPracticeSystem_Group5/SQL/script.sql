USE [QuizPractisingSystem]
GO
/****** Object:  Table [dbo].[Answer]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Answer](
	[answerId] [int] IDENTITY(1,1) NOT NULL,
	[questionId] [int] NOT NULL,
	[answerContent] [nvarchar](1023) NOT NULL,
	[isCorrect] [bit] NOT NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[answerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Blog]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Blog](
	[blogId] [int] IDENTITY(1,1) NOT NULL,
	[blogTitle] [nvarchar](255) NULL,
	[created] [datetime] NOT NULL,
	[lastEdited] [datetime] NOT NULL,
	[author] [int] NOT NULL,
	[detail] [nvarchar](2047) NOT NULL,
	[thumbnail] [nvarchar](255) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[blogId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BlogCategory]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BlogCategory](
	[blogId] [int] NOT NULL,
	[postCateId] [int] NOT NULL,
	[status] [bit] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CategorySubject]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CategorySubject](
	[subjectId] [int] NOT NULL,
	[cateId] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CustomerQuiz]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CustomerQuiz](
	[quizTakeId] [int] IDENTITY(1,1) NOT NULL,
	[quizId] [int] NOT NULL,
	[userId] [int] NOT NULL,
	[score] [int] NULL,
	[time] [int] NULL,
	[sumitedAt] [datetime] NOT NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[quizTakeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Dimension]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Dimension](
	[dimensionId] [int] IDENTITY(1,1) NOT NULL,
	[subjectId] [int] NOT NULL,
	[dimensionTypeId] [int] NOT NULL,
	[dimensionName] [nvarchar](255) NOT NULL,
	[description] [nvarchar](511) NOT NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[dimensionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DimensionType]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DimensionType](
	[dimensionTypeId] [int] IDENTITY(1,1) NOT NULL,
	[dimensionTypeName] [nvarchar](63) NOT NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[dimensionTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lesson]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lesson](
	[lessonId] [int] IDENTITY(1,1) NOT NULL,
	[subjectId] [int] NOT NULL,
	[lessonName] [nvarchar](255) NOT NULL,
	[lessonOrder] [int] NOT NULL,
	[lessonTypeId] [int] NOT NULL,
	[videoLink] [nvarchar](255) NULL,
	[content] [nvarchar](1024) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[lessonId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LessonType]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LessonType](
	[lessonTypeId] [int] IDENTITY(1,1) NOT NULL,
	[lessonTypeName] [nvarchar](63) NOT NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[lessonTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PostCategory]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PostCategory](
	[postCateId] [int] IDENTITY(1,1) NOT NULL,
	[postCateName] [nvarchar](63) NOT NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[postCateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PricePackage]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PricePackage](
	[packId] [int] IDENTITY(1,1) NOT NULL,
	[packName] [nvarchar](255) NOT NULL,
	[subjectId] [int] NOT NULL,
	[duration] [int] NOT NULL,
	[listPrice] [money] NOT NULL,
	[salePrice] [money] NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[packId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[questionId] [int] IDENTITY(1,1) NOT NULL,
	[subjectId] [int] NOT NULL,
	[dimensionId] [int] NOT NULL,
	[lessonId] [int] NOT NULL,
	[content] [nvarchar](1023) NOT NULL,
	[media] [nvarchar](255) NULL,
	[explanation] [nvarchar](1023) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[questionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Quiz]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Quiz](
	[quizId] [int] IDENTITY(1,1) NOT NULL,
	[lessonId] [int] NULL,
	[subjectId] [int] NOT NULL,
	[quizName] [nvarchar](255) NULL,
	[quizLevelId] [int] NULL,
	[quizDuration] [int] NOT NULL,
	[passRate] [int] NULL,
	[testTypeId] [int] NOT NULL,
	[description] [nvarchar](1023) NULL,
	[numberQuestion] [int] NOT NULL,
	[dimensionTypeId] [int] NOT NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[quizId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QuizLevel]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuizLevel](
	[quizLevelId] [int] IDENTITY(1,1) NOT NULL,
	[quizLevelName] [nvarchar](63) NOT NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[quizLevelId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QuizQuestion]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuizQuestion](
	[quizId] [int] NOT NULL,
	[questionId] [int] NOT NULL,
	[status] [bit] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration](
	[regId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NOT NULL,
	[regTime] [datetime] NOT NULL,
	[packId] [int] NOT NULL,
	[cost] [money] NULL,
	[validFrom] [datetime] NOT NULL,
	[validTo] [datetime] NOT NULL,
	[lastUpdatedBy] [int] NOT NULL,
	[note] [nvarchar](255) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[regId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Slider]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Slider](
	[sliderId] [int] IDENTITY(1,1) NOT NULL,
	[sliderTitle] [nvarchar](255) NOT NULL,
	[image] [nvarchar](255) NULL,
	[link] [int] NULL,
	[note] [nvarchar](255) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[sliderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subject]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subject](
	[subjectId] [int] IDENTITY(1,1) NOT NULL,
	[subjectName] [nvarchar](255) NOT NULL,
	[description] [nvarchar](1023) NOT NULL,
	[thumbnail] [nvarchar](255) NULL,
	[featuredSubject] [bit] NULL,
	[status] [bit] NULL,
	[created] [datetime] NULL,
	[lastEdited] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[subjectId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SubjectCategory]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SubjectCategory](
	[subjectCateId] [int] IDENTITY(1,1) NOT NULL,
	[subjectCateName] [nvarchar](63) NOT NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[subjectCateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SubjectExpert]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SubjectExpert](
	[subjectId] [int] NULL,
	[userId] [int] NULL,
	[status] [bit] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TakeAnswer]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TakeAnswer](
	[takeAnswerId] [int] IDENTITY(1,1) NOT NULL,
	[quizTakeId] [int] NOT NULL,
	[questionId] [int] NOT NULL,
	[answerId] [int] NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[takeAnswerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TestType]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TestType](
	[testTypeId] [int] IDENTITY(1,1) NOT NULL,
	[testTypeName] [nvarchar](63) NOT NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[testTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[userId] [int] IDENTITY(1,1) NOT NULL,
	[userName] [nvarchar](63) NOT NULL,
	[password] [nvarchar](255) NOT NULL,
	[roleId] [int] NOT NULL,
	[image] [nvarchar](255) NULL,
	[email] [nvarchar](255) NULL,
	[gender] [bit] NULL,
	[phone] [nchar](10) NULL,
	[status] [bit] NULL,
	[codeVerify] [int] NULL,
 CONSTRAINT [PK__User__CB9A1CFF071C71FD] PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserRole]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserRole](
	[userRoleId] [int] IDENTITY(1,1) NOT NULL,
	[userRoleName] [nvarchar](63) NOT NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[userRoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ViewCount]    Script Date: 6/22/2022 8:11:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ViewCount](
	[date] [date] NOT NULL,
	[view] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[date] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Answer] ON 

INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (1, 1, N'I', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (2, 1, N'You', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (3, 1, N'Friend', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (4, 1, N'Foe', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (5, 2, N'Cat', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (6, 2, N'Dog', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (7, 2, N'Ant', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (8, 2, N'Elephant', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (9, 3, N'Good Morning', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (10, 3, N'Ring', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (11, 3, N'Television', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (12, 3, N'Work', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (13, 4, N'You', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (14, 4, N'Snake', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (15, 4, N'Dice', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (16, 4, N'Brain', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (17, 5, N'Good Night', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (18, 5, N'Ten', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (19, 5, N'Sky', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (20, 5, N'Castle', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (21, 6, N'Thank you', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (22, 6, N'Sorry', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (23, 6, N'Angry', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (24, 6, N'Happy', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (25, 7, N'Short', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (26, 7, N'Long', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (27, 7, N'Straight', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (28, 7, N'Curved', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (29, 8, N'Clock', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (30, 8, N'Dishes', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (31, 8, N'Meet', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (32, 8, N'Chicken', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (33, 9, N'Leg', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (34, 9, N'Face', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (35, 9, N'Ear', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (36, 9, N'Eye', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (37, 10, N'Hair', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (38, 10, N'Finger', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (39, 10, N'Foot', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (40, 10, N'Food', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (41, 11, N'Object-oriented programming (OOP) is a fundamental programming paradigm used by nearly every developer at some point in their career.', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (42, 11, N'Object-oriented programming (OOP) is something.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (43, 11, N'Object-oriented programming (OOP) is important in Java.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (44, 11, N'All the above.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (45, 12, N'Classes,Objects,Inheritance,Polymorphism,Data Abstraction and Encapsulation.', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (46, 12, N'Classes,Objects,Inheritance,Polymorphism,Data Abstraction and Encapsulation,Dynamic Binding,Message Passing.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (47, 12, N'Classes,Objects,Inheritance,Polymorphism.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (48, 12, N'All the above', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (49, 13, N'An object is a noun (or pronoun) governed by a verb or a preposition.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (50, 13, N'Object is an instance of a class. An object in OOPS is nothing but a self-contained component which consists of methods and properties to make a particular type of data useful.A material thing that can be seen and touched.', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (51, 13, N'A person or thing to which a specified action or feeling is directed.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (52, 13, N'All the above', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (53, 14, N'Class in Java determines how an object will behave and what the object will contain.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (54, 14, N'It is a basic concept of Object-Oriented Programming which revolve around the real-life entities.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (55, 14, N'Class are a blueprint or a set of instructions to build a specific type of object.', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (56, 14, N'A Class in object oriented programming is a blueprint or prototype that defines the variables and the methods.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (57, 15, N'The extends keyword extends a class and is an indicator that a class is being inherited by another class.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (58, 15, N'It is a basic concept of Object-Oriented Programming which revolve around the real-life entities.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (59, 15, N'Inheritance uses the “extends” keyword to create a derived class by reusing base class code.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (60, 15, N'Inheritance in Java is a concept that acquires the properties from one class to other classes.', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (61, 16, N'the presence of genetic variation within a population, upon which natural selection can operate.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (62, 16, N'Polymorphism is the method in an object-oriented programming language that performs different things as per the objectclass.', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (63, 16, N'the occurrence of different forms among the members of a population or colony, or in the life cycle of an individual organism.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (64, 16, N'Polymorphism in Java is a concept that acquires the properties from one class to other classes.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (65, 17, N'The extends keyword extends a class and is an indicator that a class is being inherited by another class.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (66, 17, N'A variable in a class are set as “private” as shown below. It can only be accessed with the methods defined in the class. No other class or object can access them.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (67, 17, N'If a data member is private, it means it can only be accessed within the same class. No outside class can access private data member or variable of other class.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (68, 17, N'Encapsulation in Java is a mechanism to wrap up variables(data) and methods(code) together as a single unit.', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (69, 18, N'Abstraction in Programming is about hiding unwanted details while showing most essential information.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (70, 18, N'An abstract class can have both abstract and non-abstract methods.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (71, 18, N'Abstraction is the concept of object-oriented programming that “shows” only essential attributes and “hides” unnecessary information', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (72, 18, N'Inheritance in Java is a concept that acquires the properties from one class to other classes.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (73, 19, N'Adobe Photoshop is software that is extensively used for raster image editing, graphic design and digital art.', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (74, 19, N'Shadows and other effects such as alpha compositing can be applied.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (75, 19, N'It is also possible of apply several color models to these layers – CMYK, RGB, Spot Color, and Duotone and Lap color space.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (76, 19, N'Adobe Photoshop was originally developed in 1987 by Thomas and John Knoll, and then Adobe Systems Inc. bought the license to distribute in 1988.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (77, 20, N'Restore old photographs.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (78, 20, N'Create original artwork.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (79, 20, N'Create Flash movies.', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (80, 20, N'Open JPEG files.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (81, 21, N'True.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (82, 21, N'False.', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (83, 22, N'True.', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (84, 22, N'False.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (85, 23, N'Yellow, orange and green.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (86, 23, N'Blue, red and yellow.', 1, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (87, 23, N'The mix of two complimentary colors.', 0, 1)
INSERT [dbo].[Answer] ([answerId], [questionId], [answerContent], [isCorrect], [status]) VALUES (88, 23, N'Blue, blue-green, and green.', 0, 1)
SET IDENTITY_INSERT [dbo].[Answer] OFF
GO
SET IDENTITY_INSERT [dbo].[Blog] ON 

INSERT [dbo].[Blog] ([blogId], [blogTitle], [created], [lastEdited], [author], [detail], [thumbnail], [status]) VALUES (1, N'Rita’s Way: Why is it so Effective?', CAST(N'2021-08-10T00:00:00.000' AS DateTime), CAST(N'2021-09-10T00:00:00.000' AS DateTime), 1, N'RMC Learning Solutions was founded in 1991 by Rita Mulcahy, who spent years working as a trainer and consultant. What started off as a project management training company with the intention of helping people pass the PMP® exam eventually grew into what it is today: a trusted and proven resource for training and exam prep courses led by renowned experts in their field.
Using our unique Accelerated Learning Theory, RMC will teach you more in much less time. RMC’s guidance, tools, and techniques are used around the world, in a variety of industries from software development and financial services, to construction, to government agencies.', N'Rita-frame.png', 1)
INSERT [dbo].[Blog] ([blogId], [blogTitle], [created], [lastEdited], [author], [detail], [thumbnail], [status]) VALUES (2, N'Write Useful Comments', CAST(N'2021-08-11T00:00:00.000' AS DateTime), CAST(N'2021-09-11T00:00:00.000' AS DateTime), 2, N'Comments are crucial. You won’t appreciate them until you leave your thousand-line script for a couple of days and return to and try and make sense of it. Useful comments make life easier for yourself and those after you who have to maintain your code.
Write meaningful, single line comments for vague lines; write full parameter and functionality descriptions for functions and methods; for tricky logic blocks, describe the logic in words before it if necessary. And don’t forget, always keep your comments up to date!', N'40860463571_9afc286851_c.jpg', 1)
INSERT [dbo].[Blog] ([blogId], [blogTitle], [created], [lastEdited], [author], [detail], [thumbnail], [status]) VALUES (3, N'7 Ways to Be a Healthy Web Programmer', CAST(N'2021-08-12T00:00:00.000' AS DateTime), CAST(N'2021-09-12T00:00:00.000' AS DateTime), 2, N'It’s becoming common knowledge that sitting is the new smoking. We’ve probably all sat in front of our workstations for hours at a time. It’s creating your back problems, straining your eyes, and sapping your energy, and it’s probably why you have a migraine.
So take a walk instead! A recent study has shown that adults who walked for 40 minutes three times a week for a year literally grew their hippocampus! The hippocampus is the part of your brain that helps create new memories. It also helps regulate the autonomic nervous system, as well as your mood. In addition to the cognitive benefits, walking for 20 minutes will cause you to burn an average of 70–140 calories. On top of that, walking’s a great way to prevent back pain!', N'maxresdefault.jpg', 1)
INSERT [dbo].[Blog] ([blogId], [blogTitle], [created], [lastEdited], [author], [detail], [thumbnail], [status]) VALUES (4, N'Decision Making: Going Forward in Reverse', CAST(N'2021-08-13T00:00:00.000' AS DateTime), CAST(N'2021-09-13T00:00:00.000' AS DateTime), 1, N'Busy managers analyze many situations and make hundreds of decisions every day. Why, for example, are sales up in one city but down in another? Would an investment in new equipment mean higher productivity or greater confusion? Is now a good time to look for a joint venture partner, or is it better to wait? Rarely, however, do we stop to think about how we think.
Each decision is the outcome of a complex process that usually involves two different kinds of thinking: looking backward to understand the past and looking forward to predict the future.', N'decisionmaking-1024x683.jpg', 1)
INSERT [dbo].[Blog] ([blogId], [blogTitle], [created], [lastEdited], [author], [detail], [thumbnail], [status]) VALUES (5, N'Coding Math: What You Should Know', CAST(N'2021-08-14T00:00:00.000' AS DateTime), CAST(N'2021-09-14T00:00:00.000' AS DateTime), 3, N'Programming is all about dealing with numbers and building logic around them. Mathematics is one of the most important tools for programmers to develop sophisticated applications. Without the knowledge of mathematics, a programmer is basically handicapped. Think of it like you know the English language, but you don’t know how to write an essay.
Therefore, it is important for all programmers to be well versed with certain topics in mathematics that are central to programming. In this blog, we will talk about some of such topics.', N'-fs-Math-2018.xl.jpg', 1)
INSERT [dbo].[Blog] ([blogId], [blogTitle], [created], [lastEdited], [author], [detail], [thumbnail], [status]) VALUES (6, N'Future of AI', CAST(N'2021-08-15T00:00:00.000' AS DateTime), CAST(N'2021-09-15T00:00:00.000' AS DateTime), 3, N'In a nondescript building close to downtown Chicago, Marc Gyongyosi and the small but growing crew of IFM/Onetrack.AI have one rule that rules them all: think simple. The words are written in simple font on a simple sheet of paper that’s stuck to a rear upstairs wall of their industrial two-story workspace. What they’re doing here with artificial intelligence, however, isn’t simple at all.
Sitting at his cluttered desk, located near an oft-used ping-pong table and prototypes of drones from his college days suspended overhead, Gyongyosi punches some keys on a laptop to pull up grainy video footage of a forklift driver operating his vehicle in a warehouse. It was captured from overhead courtesy of a Onetrack.AI “forklift vision system.”', N'futureai.png', 1)
INSERT [dbo].[Blog] ([blogId], [blogTitle], [created], [lastEdited], [author], [detail], [thumbnail], [status]) VALUES (7, N'What is Agile?', CAST(N'2021-08-16T00:00:00.000' AS DateTime), CAST(N'2021-09-16T00:00:00.000' AS DateTime), 1, N'Agile is an iterative approach to project management and software development that helps teams deliver value to their customers faster and with fewer headaches. Instead of betting everything on a "big bang" launch, an agile team delivers work in small, but consumable, increments. Requirements, plans, and results are evaluated continuously so teams have a natural mechanism for responding to change quickly', N'agile.png', 1)
INSERT [dbo].[Blog] ([blogId], [blogTitle], [created], [lastEdited], [author], [detail], [thumbnail], [status]) VALUES (8, N'Using workflows for fun & profit', CAST(N'2021-08-17T00:00:00.000' AS DateTime), CAST(N'2021-09-17T00:00:00.000' AS DateTime), 2, N'Every software team has a process they use to complete work. Normalizing that process–i.e., establishing it as a workflow–makes it clearly structured and repeatable, which, in turn, makes it scalable. At Atlassian, we take an iterative approach to workflow management because it helps us meet our goals faster and exemplifies our team culture. We are experts in agile workflow management (if we do say so ourselves), and we want to help you become experts too.', N'workflow.jpg', 1)
INSERT [dbo].[Blog] ([blogId], [blogTitle], [created], [lastEdited], [author], [detail], [thumbnail], [status]) VALUES (9, N'Why is it important to learn English?', CAST(N'2021-08-18T00:00:00.000' AS DateTime), CAST(N'2021-09-18T00:00:00.000' AS DateTime), 2, N'There are many, many reasons why learning a new language is a good idea. It allows you to communicate with new people. It helps you to see things from a different perspective, or get a deeper understanding of another culture. It helps you to become a better listener. It even has health benefits, as studies have shown that people who speak two or more languages have more active minds later in life!
Those are all reasons to learn any language – but did you know that there are 6,500 languages still spoken in the world today? With such an enormous number to choose from, why pick English?
Here are five big reasons that learning English can improve your life.', N'Learn-English.png', 1)
INSERT [dbo].[Blog] ([blogId], [blogTitle], [created], [lastEdited], [author], [detail], [thumbnail], [status]) VALUES (10, N'Why study Japanese?', CAST(N'2021-08-19T00:00:00.000' AS DateTime), CAST(N'2021-09-19T00:00:00.000' AS DateTime), 1, N'Japanese consumers spend 100s of billions of dollars each year on consumer goods and services like food, clothing, travel, and entertainment. The typical household has over $100,000 in savings and a disposable monthly income of $3,800. With all of that cash to spend, it is perhaps not surprising then that the United States exports more goods and services to Japan than any other overseas destination. In 2004, exports to Japan accounted for $54 billion of the U.S. GDP. In addition to these exports, 1000s of U.S. companies have successful branches in Japan. In 2004 alone, U.S. businesses spent $78 billion in direct investment in Japan.
Being able to communicate with potential customers in their own language is key to winning their business. In addition, when you learn Japanese, you become not only proficient in the language but also gain an insider view of the culture. Understanding the Japanese work ethic, their business etiquette, and knowing which cultural faux pas to avoid can often make or break an important business deal.', N'Japanese.jpg', 1)
INSERT [dbo].[Blog] ([blogId], [blogTitle], [created], [lastEdited], [author], [detail], [thumbnail], [status]) VALUES (11, N'Milk proteins may interfere with tea compounds, but research is mixed', CAST(N'2021-08-20T00:00:00.000' AS DateTime), CAST(N'2021-09-20T00:00:00.000' AS DateTime), 1, N'Given that both tea and milk contain health-promoting compounds and nutrients, combing the two may seem beneficial.
In fact, one study in over 1,800 adults in China found that both tea and milk consumption were independently linked to a lower risk of oral cancer and that they may have a particularly beneficial effect when consumed together (10Trusted Source).
Yet, some studies suggest that the proteins in milk may interfere with the absorption and antioxidant activity of the compounds tea (11Trusted Source).
One study in 16 adult women observed that drinking 2 cups (500 ml) of plain black tea significantly increased blood flow, which can help improve heart function, compared with drinking water. Meanwhile, drinking black tea with skim milk did not have these effects (11Trusted Source).', N'milktea.jpg', 1)
SET IDENTITY_INSERT [dbo].[Blog] OFF
GO
INSERT [dbo].[BlogCategory] ([blogId], [postCateId], [status]) VALUES (1, 2, 1)
INSERT [dbo].[BlogCategory] ([blogId], [postCateId], [status]) VALUES (2, 1, 1)
INSERT [dbo].[BlogCategory] ([blogId], [postCateId], [status]) VALUES (3, 3, 1)
INSERT [dbo].[BlogCategory] ([blogId], [postCateId], [status]) VALUES (4, 1, 1)
INSERT [dbo].[BlogCategory] ([blogId], [postCateId], [status]) VALUES (5, 2, 1)
INSERT [dbo].[BlogCategory] ([blogId], [postCateId], [status]) VALUES (6, 4, 1)
INSERT [dbo].[BlogCategory] ([blogId], [postCateId], [status]) VALUES (7, 2, 1)
INSERT [dbo].[BlogCategory] ([blogId], [postCateId], [status]) VALUES (8, 2, 1)
INSERT [dbo].[BlogCategory] ([blogId], [postCateId], [status]) VALUES (9, 3, 1)
INSERT [dbo].[BlogCategory] ([blogId], [postCateId], [status]) VALUES (10, 3, 1)
INSERT [dbo].[BlogCategory] ([blogId], [postCateId], [status]) VALUES (11, 4, 1)
GO
INSERT [dbo].[CategorySubject] ([subjectId], [cateId]) VALUES (1, 2)
INSERT [dbo].[CategorySubject] ([subjectId], [cateId]) VALUES (1, 3)
INSERT [dbo].[CategorySubject] ([subjectId], [cateId]) VALUES (2, 8)
INSERT [dbo].[CategorySubject] ([subjectId], [cateId]) VALUES (3, 1)
INSERT [dbo].[CategorySubject] ([subjectId], [cateId]) VALUES (3, 10)
INSERT [dbo].[CategorySubject] ([subjectId], [cateId]) VALUES (4, 7)
INSERT [dbo].[CategorySubject] ([subjectId], [cateId]) VALUES (18, 8)
GO
SET IDENTITY_INSERT [dbo].[CustomerQuiz] ON 

INSERT [dbo].[CustomerQuiz] ([quizTakeId], [quizId], [userId], [score], [time], [sumitedAt], [status]) VALUES (1, 2, 1, 10, 30, CAST(N'2022-05-26T21:30:24.303' AS DateTime), 1)
INSERT [dbo].[CustomerQuiz] ([quizTakeId], [quizId], [userId], [score], [time], [sumitedAt], [status]) VALUES (7, 4, 1, 80, 75, CAST(N'2022-06-09T08:11:02.073' AS DateTime), 1)
INSERT [dbo].[CustomerQuiz] ([quizTakeId], [quizId], [userId], [score], [time], [sumitedAt], [status]) VALUES (8, 5, 1, 12, 31, CAST(N'2022-06-09T22:28:27.827' AS DateTime), 1)
INSERT [dbo].[CustomerQuiz] ([quizTakeId], [quizId], [userId], [score], [time], [sumitedAt], [status]) VALUES (9, 6, 1, 0, 10, CAST(N'2022-06-09T22:28:59.457' AS DateTime), 1)
INSERT [dbo].[CustomerQuiz] ([quizTakeId], [quizId], [userId], [score], [time], [sumitedAt], [status]) VALUES (10, 7, 1, 0, 12, CAST(N'2022-06-09T22:30:52.060' AS DateTime), 1)
INSERT [dbo].[CustomerQuiz] ([quizTakeId], [quizId], [userId], [score], [time], [sumitedAt], [status]) VALUES (11, 8, 1, 0, 13, CAST(N'2022-06-09T22:31:55.957' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[CustomerQuiz] OFF
GO
SET IDENTITY_INSERT [dbo].[Dimension] ON 

INSERT [dbo].[Dimension] ([dimensionId], [subjectId], [dimensionTypeId], [dimensionName], [description], [status]) VALUES (1, 1, 1, N'Java Programming', N'', 1)
INSERT [dbo].[Dimension] ([dimensionId], [subjectId], [dimensionTypeId], [dimensionName], [description], [status]) VALUES (2, 2, 2, N'Japanese', N'', 1)
INSERT [dbo].[Dimension] ([dimensionId], [subjectId], [dimensionTypeId], [dimensionName], [description], [status]) VALUES (3, 3, 1, N'Physics', N'', 1)
INSERT [dbo].[Dimension] ([dimensionId], [subjectId], [dimensionTypeId], [dimensionName], [description], [status]) VALUES (4, 4, 1, N'Graphics Design', N'', 1)
SET IDENTITY_INSERT [dbo].[Dimension] OFF
GO
SET IDENTITY_INSERT [dbo].[DimensionType] ON 

INSERT [dbo].[DimensionType] ([dimensionTypeId], [dimensionTypeName], [status]) VALUES (1, N'Domain', 1)
INSERT [dbo].[DimensionType] ([dimensionTypeId], [dimensionTypeName], [status]) VALUES (2, N'Group', 1)
SET IDENTITY_INSERT [dbo].[DimensionType] OFF
GO
SET IDENTITY_INSERT [dbo].[Lesson] ON 

INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (1, 1, N'Introduction', 1, 1, N'', N'Welcome to this course. Maybe right now you dont know how to code in a object oriented way or dont even know Java but at the end of this course, we hope youll be able to master it.', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (2, 1, N'4 main ideas of OOP in Java', 2, 2, N'', N'OOP concepts in Java are the main ideas behind Java Object Oriented Programming. They are an abstraction, encapsulation, inheritance, and polymorphism. ... Basically, Java OOP concepts let us create working methods and variables, then re-use all or part of them without compromising security.', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (3, 1, N'Quiz: 4 main ideas of OOP in Java', 3, 3, N'', N'First quiz, but dont be too afraid.', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (4, 2, N'Introduction', 1, 1, N'', N'Japanese for the absolute beginners, whether for academic purposes, work or if you just admire Japan and its culture', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (5, 2, N'Hiragana', 2, 2, N'https://youtu.be/K-nw5EUxDz0', N'Hiragana and katakana are both kana systems. With few exceptions, each mora in the Japanese language is represented by one character (or one digraph) in each system. This may be either a vowel such as "a" (hiragana ?); a consonant followed by a vowel such as "ka" (?); or "n" (?), a nasal sonorant which, depending on the context, sounds either like English m, n or ng ([?]) when syllable-final or like the nasal vowels of French, Portuguese or Polish. Because the characters of the kana do not represent single consonants (except in the case of ? "n"), the kana are referred to as syllabic symbols and not alphabetic letters.', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (6, 2, N'Basic Greetings', 3, 2, N'https://youtu.be/o9O18DkU2Yc', N'Konnichiwa (Hi; Good afternoon.)
Ohayo gozaimasu/ Ohayo (Good morning [formal/informal])
Konbanwa (Good evening)
Hajimemashite. (How do you do?)
O-genki desu ka. (How are you? [formal])
Genki? (How are you? [informal])
Maiku-san wa? (How about you, Mike?)
Hai, genki desu. (Yes, I’m fine.)
E, mama desu. (Well, so-so.)
Hai, watashi mo genki desu. (Yes, I’m fine, too.)
Mata ashita. (See you tomorrow.)
Sayonara. (Goodbye.)
Oyasumi nasai. (Good night.)
', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (7, 2, N'Small Quiz', 4, 3, N'', N'Just a little practice and you can do it', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (8, 2, N'See you again', 5, 1, N'', N'Keep on learning!', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (9, 3, N'Introduction', 1, 1, N'', N'Introduce realism in apps and games!', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (10, 3, N'Basics', 2, 2, N'', N'Physics programmers create software that forms the basis of crashes, collisions and other things that move. When, for example, a car drives through water or bursts into flames, the effect needs to be similar to what would happen in real life. Physics programmers write the code, based on the laws of physics, to make this happen. It requires high-level knowledge of both physics and programming. It also requires a sense of gameplay and the right blend of realism and fun.', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (11, 3, N'Small Quiz', 3, 3, N'', N'Its not done yet, more lessons are on their way.', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (12, 4, N'Introduction', 1, 1, N'', N'Learn how to photoshop for your own needs, and for higher, professional jobs.', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (13, 4, N'Some crucial skills needed in photoshop', 2, 2, N'https://youtu.be/IyR_uYsRdPs', N'The key to photoshop is patience.', 1)
INSERT [dbo].[Lesson] ([lessonId], [subjectId], [lessonName], [lessonOrder], [lessonTypeId], [videoLink], [content], [status]) VALUES (14, 4, N'Small Quiz', 3, 3, N'', N'Just the basics so far.', 1)
SET IDENTITY_INSERT [dbo].[Lesson] OFF
GO
SET IDENTITY_INSERT [dbo].[LessonType] ON 

INSERT [dbo].[LessonType] ([lessonTypeId], [lessonTypeName], [status]) VALUES (1, N'Subject-Topic', 1)
INSERT [dbo].[LessonType] ([lessonTypeId], [lessonTypeName], [status]) VALUES (2, N'Lesson', 1)
INSERT [dbo].[LessonType] ([lessonTypeId], [lessonTypeName], [status]) VALUES (3, N'Quiz', 1)
SET IDENTITY_INSERT [dbo].[LessonType] OFF
GO
SET IDENTITY_INSERT [dbo].[PostCategory] ON 

INSERT [dbo].[PostCategory] ([postCateId], [postCateName], [status]) VALUES (1, N'Tips and Tricks', 1)
INSERT [dbo].[PostCategory] ([postCateId], [postCateName], [status]) VALUES (2, N'Review and Recommendation', 1)
INSERT [dbo].[PostCategory] ([postCateId], [postCateName], [status]) VALUES (3, N'Casuals', 1)
INSERT [dbo].[PostCategory] ([postCateId], [postCateName], [status]) VALUES (4, N'Rest and Relax', 1)
SET IDENTITY_INSERT [dbo].[PostCategory] OFF
GO
SET IDENTITY_INSERT [dbo].[PricePackage] ON 

INSERT [dbo].[PricePackage] ([packId], [packName], [subjectId], [duration], [listPrice], [salePrice], [status]) VALUES (1, N'3 months package', 1, 3, 10.0000, 8.0000, 1)
INSERT [dbo].[PricePackage] ([packId], [packName], [subjectId], [duration], [listPrice], [salePrice], [status]) VALUES (2, N'6 months package', 1, 6, 20.0000, 10.0000, 1)
INSERT [dbo].[PricePackage] ([packId], [packName], [subjectId], [duration], [listPrice], [salePrice], [status]) VALUES (3, N'12 months package', 1, 12, 30.0000, 20.0000, 1)
INSERT [dbo].[PricePackage] ([packId], [packName], [subjectId], [duration], [listPrice], [salePrice], [status]) VALUES (4, N'3 months package', 2, 3, 20.0000, 15.0000, 1)
INSERT [dbo].[PricePackage] ([packId], [packName], [subjectId], [duration], [listPrice], [salePrice], [status]) VALUES (5, N'6 months package', 2, 6, 40.0000, 30.0000, 1)
INSERT [dbo].[PricePackage] ([packId], [packName], [subjectId], [duration], [listPrice], [salePrice], [status]) VALUES (6, N'12 months package', 2, 12, 60.0000, 45.0000, 1)
INSERT [dbo].[PricePackage] ([packId], [packName], [subjectId], [duration], [listPrice], [salePrice], [status]) VALUES (7, N'3 months package', 3, 3, 10.0000, 8.0000, 1)
INSERT [dbo].[PricePackage] ([packId], [packName], [subjectId], [duration], [listPrice], [salePrice], [status]) VALUES (8, N'6 months package', 3, 6, 20.0000, 10.0000, 1)
INSERT [dbo].[PricePackage] ([packId], [packName], [subjectId], [duration], [listPrice], [salePrice], [status]) VALUES (11, N'12 month package', 3, 12, 30.0000, 15.0000, 1)
INSERT [dbo].[PricePackage] ([packId], [packName], [subjectId], [duration], [listPrice], [salePrice], [status]) VALUES (12, N'3 months package', 4, 3, 15.0000, 8.0000, 1)
INSERT [dbo].[PricePackage] ([packId], [packName], [subjectId], [duration], [listPrice], [salePrice], [status]) VALUES (13, N'6 months package', 4, 6, 35.0000, 20.0000, 1)
INSERT [dbo].[PricePackage] ([packId], [packName], [subjectId], [duration], [listPrice], [salePrice], [status]) VALUES (14, N'12 months package', 4, 12, 50.0000, 25.0000, 1)
SET IDENTITY_INSERT [dbo].[PricePackage] OFF
GO
SET IDENTITY_INSERT [dbo].[Question] ON 

INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (1, 2, 2, 5, N'Watashi', N'https://www.youtube.com/embed/0PQ9mgc55ic', N'nihongo', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (2, 2, 2, 5, N'Neko', N'https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png', N'nihongo', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (3, 2, 2, 5, N'Ohayo', N'https://www.thoughtco.com/thmb/06i-nrtpR2ZKY7G06_6yY3LktVw=/768x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/aamorning-9cf2e2f4546248899d2020857ce457bb.jpg', N'nihongo', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (4, 2, 2, 5, N'Anata', NULL, N'nihongo', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (5, 2, 2, 5, N'Konbanwa', NULL, N'nihongo', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (6, 2, 2, 5, N'Arigatou gozaimasu', NULL, N'nihongo', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (7, 2, 2, 5, N'Mijikai', NULL, N'nihongo', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (8, 2, 2, 5, N'Tokei', NULL, N'nihongo', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (9, 2, 2, 5, N'Ashi', NULL, N'nihongo', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (10, 2, 2, 5, N'Kami', NULL, N'nihongo', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (11, 1, 1, 1, N'What is Object Oriented Programming?', NULL, N'OOP Introduction', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (12, 1, 1, 1, N'What are the main features of OOPs?', NULL, N'OOP Introduction', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (13, 1, 1, 1, N'What is an object?', NULL, N'OOP Introduction', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (14, 1, 1, 1, N'What is a class?', NULL, N'OOP Introduction', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (15, 1, 1, 2, N'What is inheritance?', NULL, N'OOP Details', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (16, 1, 1, 2, N'What is polymorphism?', NULL, N'OOP Details', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (17, 1, 1, 2, N'What is encapsulation?', NULL, N'OOP Details', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (18, 1, 1, 2, N'What is Data Abstraction?', NULL, N'OOP Details', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (19, 4, 4, 12, N'What is Adobe Photoshop?', NULL, N'Photoshop Introduction', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (20, 4, 4, 12, N'Which of the following can you NOT do with Photoshop?', NULL, N'Photoshop Introduction', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (21, 4, 4, 12, N'Once you build the layers in your graphic design, you cannot rearrange them?', NULL, N'Photoshop Introduction', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (22, 4, 4, 12, N'Photoshop documents are composed of layers, which can basically be described as single transparent sheets which hold particular pieces of an image?', NULL, N'Photoshop Details', 1)
INSERT [dbo].[Question] ([questionId], [subjectId], [dimensionId], [lessonId], [content], [media], [explanation], [status]) VALUES (23, 4, 4, 12, N'Primary colors consist of:?', NULL, N'Photoshop Details', 1)
SET IDENTITY_INSERT [dbo].[Question] OFF
GO
SET IDENTITY_INSERT [dbo].[Quiz] ON 

INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (1, 5, 2, N'Practice Quiz', 3, 7200, NULL, 2, NULL, 10, 2, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (2, 5, 2, N'Exam Quiz', 3, 900, NULL, 1, NULL, 10, 2, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (3, 5, 2, N'Exam Quiz', 3, 30, NULL, 1, NULL, 10, 2, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (4, NULL, 2, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 10, 2, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (5, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (6, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (7, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (8, NULL, 2, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 10, 2, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (9, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (10, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (11, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (12, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (13, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (14, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (15, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (16, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (17, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (18, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (19, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (20, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (21, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (22, NULL, 1, N'Practice Quiz', NULL, 600, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (23, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (24, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (25, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (26, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (27, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (28, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (29, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (30, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (31, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (32, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (33, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (34, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (35, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (36, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (37, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (38, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (39, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (40, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (41, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (42, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (43, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 8, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (44, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (45, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (46, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (47, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (48, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (49, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (50, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (51, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (52, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (53, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (54, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (55, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (56, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
INSERT [dbo].[Quiz] ([quizId], [lessonId], [subjectId], [quizName], [quizLevelId], [quizDuration], [passRate], [testTypeId], [description], [numberQuestion], [dimensionTypeId], [status]) VALUES (57, NULL, 1, N'Practice Quiz', NULL, 60, NULL, 3, NULL, 1, 1, 1)
SET IDENTITY_INSERT [dbo].[Quiz] OFF
GO
SET IDENTITY_INSERT [dbo].[QuizLevel] ON 

INSERT [dbo].[QuizLevel] ([quizLevelId], [quizLevelName], [status]) VALUES (1, N'Hard', 1)
INSERT [dbo].[QuizLevel] ([quizLevelId], [quizLevelName], [status]) VALUES (2, N'Medium', 1)
INSERT [dbo].[QuizLevel] ([quizLevelId], [quizLevelName], [status]) VALUES (3, N'Easy', 1)
SET IDENTITY_INSERT [dbo].[QuizLevel] OFF
GO
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (1, 1, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (1, 2, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (1, 3, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (1, 4, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (1, 5, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (1, 6, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (1, 7, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (1, 8, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (1, 9, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (1, 10, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (2, 1, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (2, 2, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (2, 3, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (2, 4, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (2, 5, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (2, 6, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (2, 7, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (2, 8, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (2, 9, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (2, 10, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (3, 1, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (3, 2, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (3, 3, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (3, 4, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (3, 5, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (3, 6, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (3, 7, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (3, 8, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (3, 9, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (3, 10, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 14, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 13, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 17, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 12, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 15, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 11, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 18, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 16, 1)
GO
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 17, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 16, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 13, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 11, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 14, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 15, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 18, 1)
INSERT [dbo].[QuizQuestion] ([quizId], [questionId], [status]) VALUES (9, 12, 1)
GO
SET IDENTITY_INSERT [dbo].[Registration] ON 

INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (1, 1, CAST(N'2020-12-12T00:00:00.000' AS DateTime), 2, 20.0000, CAST(N'2020-12-12T00:00:00.000' AS DateTime), CAST(N'2021-06-12T00:00:00.000' AS DateTime), 1, NULL, 1)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (2, 1, CAST(N'2021-10-12T00:00:00.000' AS DateTime), 14, 20.0000, CAST(N'2020-12-12T00:00:00.000' AS DateTime), CAST(N'2021-06-12T00:00:00.000' AS DateTime), 5, N'bbbb', NULL)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (3, 8, CAST(N'2021-10-07T00:00:00.000' AS DateTime), 1, 20.0000, CAST(N'2021-10-08T00:00:00.000' AS DateTime), CAST(N'2022-06-12T00:00:00.000' AS DateTime), 1, NULL, 1)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (4, 8, CAST(N'2021-10-08T00:00:00.000' AS DateTime), 2, 20.0000, CAST(N'2021-10-09T00:00:00.000' AS DateTime), CAST(N'2022-06-12T00:00:00.000' AS DateTime), 1, NULL, NULL)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (5, 9, CAST(N'2021-10-07T00:00:00.000' AS DateTime), 2, 20.0000, CAST(N'2021-10-08T00:00:00.000' AS DateTime), CAST(N'2022-06-12T00:00:00.000' AS DateTime), 1, NULL, 1)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (6, 9, CAST(N'2021-10-06T00:00:00.000' AS DateTime), 1, 20.0000, CAST(N'2021-10-07T00:00:00.000' AS DateTime), CAST(N'2022-06-12T00:00:00.000' AS DateTime), 1, NULL, 0)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (7, 10, CAST(N'2021-10-07T00:00:00.000' AS DateTime), 3, 20.0000, CAST(N'2021-10-11T00:00:00.000' AS DateTime), CAST(N'2022-06-12T00:00:00.000' AS DateTime), 1, NULL, 1)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (8, 10, CAST(N'2021-10-06T00:00:00.000' AS DateTime), 5, 20.0000, CAST(N'2021-10-11T00:00:00.000' AS DateTime), CAST(N'2022-06-12T00:00:00.000' AS DateTime), 1, NULL, NULL)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (9, 11, CAST(N'2021-10-06T00:00:00.000' AS DateTime), 5, 20.0000, CAST(N'2021-10-11T00:00:00.000' AS DateTime), CAST(N'2022-06-12T00:00:00.000' AS DateTime), 1, NULL, 0)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (10, 11, CAST(N'2021-10-07T00:00:00.000' AS DateTime), 4, 20.0000, CAST(N'2021-10-11T00:00:00.000' AS DateTime), CAST(N'2022-06-12T00:00:00.000' AS DateTime), 1, NULL, NULL)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (11, 7, CAST(N'2021-10-08T00:00:00.000' AS DateTime), 3, 20.0000, CAST(N'2021-10-15T00:00:00.000' AS DateTime), CAST(N'2022-06-12T00:00:00.000' AS DateTime), 1, NULL, 1)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (12, 7, CAST(N'2021-10-06T00:00:00.000' AS DateTime), 5, 20.0000, CAST(N'2021-10-07T00:00:00.000' AS DateTime), CAST(N'2022-06-12T00:00:00.000' AS DateTime), 1, NULL, NULL)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (13, 6, CAST(N'2021-10-09T00:00:00.000' AS DateTime), 5, 20.0000, CAST(N'2021-10-09T00:00:00.000' AS DateTime), CAST(N'2022-06-12T00:00:00.000' AS DateTime), 1, NULL, 1)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (14, 6, CAST(N'2021-10-10T00:00:00.000' AS DateTime), 4, 20.0000, CAST(N'2021-10-12T00:00:00.000' AS DateTime), CAST(N'2022-06-12T00:00:00.000' AS DateTime), 1, NULL, 0)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (17, 1, CAST(N'2022-10-10T00:00:00.000' AS DateTime), 2, 20.0000, CAST(N'2022-10-12T00:00:00.000' AS DateTime), CAST(N'2023-06-12T00:00:00.000' AS DateTime), 1, NULL, 0)
INSERT [dbo].[Registration] ([regId], [userId], [regTime], [packId], [cost], [validFrom], [validTo], [lastUpdatedBy], [note], [status]) VALUES (19, 1, CAST(N'2022-01-01T00:00:00.000' AS DateTime), 14, 20.0000, CAST(N'2022-01-03T00:00:00.000' AS DateTime), CAST(N'2023-01-03T00:00:00.000' AS DateTime), 5, N'sadssd', NULL)
SET IDENTITY_INSERT [dbo].[Registration] OFF
GO
SET IDENTITY_INSERT [dbo].[Slider] ON 

INSERT [dbo].[Slider] ([sliderId], [sliderTitle], [image], [link], [note], [status]) VALUES (1, N'OOP with Java', N'javap.jpg', 1, N'bbbb', 1)
INSERT [dbo].[Slider] ([sliderId], [sliderTitle], [image], [link], [note], [status]) VALUES (2, N'NiHongo 101', N'japanese.jpg', 2, N'iidesune', 1)
INSERT [dbo].[Slider] ([sliderId], [sliderTitle], [image], [link], [note], [status]) VALUES (3, N'OOP with Python', N'python.jpg', 3, N'iidesune', 1)
SET IDENTITY_INSERT [dbo].[Slider] OFF
GO
SET IDENTITY_INSERT [dbo].[Subject] ON 

INSERT [dbo].[Subject] ([subjectId], [subjectName], [description], [thumbnail], [featuredSubject], [status], [created], [lastEdited]) VALUES (1, N'OOP with Java', N'Object Oriented Programming Fundamentals with Java.', N'java_oop.jpg', 1, 1, CAST(N'2021-08-20T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[Subject] ([subjectId], [subjectName], [description], [thumbnail], [featuredSubject], [status], [created], [lastEdited]) VALUES (2, N'Elementary Japanese 101', N'Japanese from zero.', N'japanCourse.png', 1, 1, CAST(N'2021-08-20T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[Subject] ([subjectId], [subjectName], [description], [thumbnail], [featuredSubject], [status], [created], [lastEdited]) VALUES (3, N'Python fundamentals', N'This course was created for complete beginners and will teach you programming', N'python.jpg', 1, 1, CAST(N'2021-08-20T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[Subject] ([subjectId], [subjectName], [description], [thumbnail], [featuredSubject], [status], [created], [lastEdited]) VALUES (4, N'C# fundamentals', N'C# has consistently been one of the top three programming languages to learn as', N'csharp.jpg', 1, 1, CAST(N'2021-08-20T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[Subject] ([subjectId], [subjectName], [description], [thumbnail], [featuredSubject], [status], [created], [lastEdited]) VALUES (18, N'tieng  nhat', N'hoc tieng nhat', N'SubjectIMG1655689045760.png', 0, 0, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Subject] OFF
GO
SET IDENTITY_INSERT [dbo].[SubjectCategory] ON 

INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (1, N'Computer Science', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (2, N'Java', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (3, N'Object Oriented Programming', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (4, N'C', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (5, N'C#', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (6, N'Web Design', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (7, N'Digital Art', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (8, N'Japanese', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (9, N'English', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (10, N'Algebra', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (11, N'Organic Chemistry', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (12, N'Basic Economic', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (13, N'Buiseness Ethics', 1)
INSERT [dbo].[SubjectCategory] ([subjectCateId], [subjectCateName], [status]) VALUES (14, N'CSS', 1)
SET IDENTITY_INSERT [dbo].[SubjectCategory] OFF
GO
INSERT [dbo].[SubjectExpert] ([subjectId], [userId], [status]) VALUES (1, 6, 1)
INSERT [dbo].[SubjectExpert] ([subjectId], [userId], [status]) VALUES (2, 7, 1)
INSERT [dbo].[SubjectExpert] ([subjectId], [userId], [status]) VALUES (3, 6, 1)
INSERT [dbo].[SubjectExpert] ([subjectId], [userId], [status]) VALUES (3, 7, 1)
INSERT [dbo].[SubjectExpert] ([subjectId], [userId], [status]) VALUES (4, 6, 1)
INSERT [dbo].[SubjectExpert] ([subjectId], [userId], [status]) VALUES (18, 6, 1)
GO
SET IDENTITY_INSERT [dbo].[TakeAnswer] ON 

INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (1, 1, 1, 1, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (2, 1, 2, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (3, 1, 3, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (4, 1, 4, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (5, 1, 5, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (6, 1, 6, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (7, 1, 7, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (8, 1, 8, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (9, 1, 9, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (10, 1, 10, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (102, 7, 5, 17, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (103, 7, 10, 37, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (104, 7, 2, 8, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (105, 7, 1, 1, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (106, 7, 6, 21, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (107, 7, 8, 29, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (108, 7, 4, 13, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (109, 7, 7, 25, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (110, 7, 9, 36, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (111, 7, 3, 9, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (112, 8, 14, 53, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (113, 8, 12, 45, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (114, 8, 16, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (115, 8, 18, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (116, 8, 17, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (117, 8, 13, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (118, 8, 15, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (119, 8, 11, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (120, 9, 14, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (121, 9, 11, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (122, 9, 16, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (123, 9, 18, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (124, 9, 17, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (125, 9, 12, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (126, 9, 13, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (127, 9, 15, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (128, 10, 16, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (129, 10, 11, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (130, 10, 13, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (131, 10, 15, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (132, 10, 17, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (133, 10, 18, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (134, 10, 14, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (135, 10, 12, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (136, 11, 2, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (137, 11, 8, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (138, 11, 1, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (139, 11, 10, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (140, 11, 7, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (141, 11, 5, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (142, 11, 9, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (143, 11, 4, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (144, 11, 6, NULL, 0)
INSERT [dbo].[TakeAnswer] ([takeAnswerId], [quizTakeId], [questionId], [answerId], [status]) VALUES (145, 11, 3, NULL, 0)
SET IDENTITY_INSERT [dbo].[TakeAnswer] OFF
GO
SET IDENTITY_INSERT [dbo].[TestType] ON 

INSERT [dbo].[TestType] ([testTypeId], [testTypeName], [status]) VALUES (1, N'Simulation', 1)
INSERT [dbo].[TestType] ([testTypeId], [testTypeName], [status]) VALUES (2, N'Lesson-Quiz', 1)
INSERT [dbo].[TestType] ([testTypeId], [testTypeName], [status]) VALUES (3, N'Practice', 1)
SET IDENTITY_INSERT [dbo].[TestType] OFF
GO
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (1, N'	TuanLDHE153375', N'202cb962ac59075b964b07152d234b70', 1, N'3userimage1654165755421.jpg', N'Tuanldhe153375@fpt.edu.vn', 1, N'0696044711', 1, NULL)
INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (2, N'	ManhNKHE153424', N'1', 5, N'user1.png', N'	Manhnkhe153424@fpt.edu.vn', 1, N'0969044712', 1, NULL)
INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (3, N'MinhLHHE153427', N'6512bd43d9caa6e02c990b0a82652dca', 5, N'user1.png', N'MinhLHHE153427@fpt.edu.vn', 1, N'12345678  ', 1, NULL)
INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (4, N'DucNTHE153430', N'1', 5, N'user1.png', N'	DucNTHE153430@fpt.edu.vn', 1, N'0969044714', 1, NULL)
INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (5, N'PhuLVHE153610', N'1', 5, N'user1.png', N'	PhuLVHE153610@fpt.edu.vn', 1, N'0969044715', 1, NULL)
INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (6, N'Expert1', N'1', 4, N'3userimage1654165755421.jpg', N'Expert1@gmail.com', 0, N'0969044716', 1, NULL)
INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (7, N'Expert2', N'1', 4, N'user1.png', N'Expert2@gmail.com', 1, N'0969044717', 1, NULL)
INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (8, N'User1', N'1', 1, N'user1.png', N'User1@gmail.com', 1, N'0969044718', 1, NULL)
INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (9, N'User2', N'1', 1, N'user1.png', N'User2@gmail.com', 1, N'0969044719', 1, NULL)
INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (10, N'User3', N'1', 1, N'user1.png', N'User3@gmail.com', 1, N'0969044720', 1, NULL)
INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (11, N'User4', N'1', 1, N'user1.png', N'User4@gmail.com', 1, N'0969044721', 1, NULL)
INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (12, N'Sale1', N'1', 3, N'user1.png', N'Sale1@gmail.com', 0, N'0969696969', 1, NULL)
INSERT [dbo].[User] ([userId], [userName], [password], [roleId], [image], [email], [gender], [phone], [status], [codeVerify]) VALUES (13, N'Manhnkhedsz153424', N'202cb962ac59075b964b07152d234b70', 5, N'3userimage1654165755421.jpg', N'Manhnkhe153424@fpt.edu.vn', 1, N'0375801453', 1, 762140)
SET IDENTITY_INSERT [dbo].[User] OFF
GO
SET IDENTITY_INSERT [dbo].[UserRole] ON 

INSERT [dbo].[UserRole] ([userRoleId], [userRoleName], [status]) VALUES (1, N'Customer', 1)
INSERT [dbo].[UserRole] ([userRoleId], [userRoleName], [status]) VALUES (2, N'Marketing', 1)
INSERT [dbo].[UserRole] ([userRoleId], [userRoleName], [status]) VALUES (3, N'Sale', 1)
INSERT [dbo].[UserRole] ([userRoleId], [userRoleName], [status]) VALUES (4, N'Expert', 1)
INSERT [dbo].[UserRole] ([userRoleId], [userRoleName], [status]) VALUES (5, N'Admin', 1)
SET IDENTITY_INSERT [dbo].[UserRole] OFF
GO
INSERT [dbo].[ViewCount] ([date], [view]) VALUES (CAST(N'2022-05-26' AS Date), 1)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__User__54F2C930DA141219]    Script Date: 6/22/2022 8:11:35 AM ******/
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [UQ__User__54F2C930DA141219] UNIQUE NONCLUSTERED 
(
	[phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__User__DF9290BDE599BCDD]    Script Date: 6/22/2022 8:11:35 AM ******/
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [UQ__User__DF9290BDE599BCDD] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Answer]  WITH CHECK ADD FOREIGN KEY([questionId])
REFERENCES [dbo].[Question] ([questionId])
GO
ALTER TABLE [dbo].[Blog]  WITH CHECK ADD  CONSTRAINT [FK__Blog__author__5165187F] FOREIGN KEY([author])
REFERENCES [dbo].[User] ([userId])
GO
ALTER TABLE [dbo].[Blog] CHECK CONSTRAINT [FK__Blog__author__5165187F]
GO
ALTER TABLE [dbo].[BlogCategory]  WITH CHECK ADD FOREIGN KEY([blogId])
REFERENCES [dbo].[Blog] ([blogId])
GO
ALTER TABLE [dbo].[BlogCategory]  WITH CHECK ADD FOREIGN KEY([postCateId])
REFERENCES [dbo].[PostCategory] ([postCateId])
GO
ALTER TABLE [dbo].[CategorySubject]  WITH CHECK ADD FOREIGN KEY([cateId])
REFERENCES [dbo].[SubjectCategory] ([subjectCateId])
GO
ALTER TABLE [dbo].[CategorySubject]  WITH CHECK ADD FOREIGN KEY([subjectId])
REFERENCES [dbo].[Subject] ([subjectId])
GO
ALTER TABLE [dbo].[CustomerQuiz]  WITH CHECK ADD FOREIGN KEY([quizId])
REFERENCES [dbo].[Quiz] ([quizId])
GO
ALTER TABLE [dbo].[CustomerQuiz]  WITH CHECK ADD  CONSTRAINT [FK__CustomerQ__userI__571DF1D5] FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([userId])
GO
ALTER TABLE [dbo].[CustomerQuiz] CHECK CONSTRAINT [FK__CustomerQ__userI__571DF1D5]
GO
ALTER TABLE [dbo].[Dimension]  WITH CHECK ADD FOREIGN KEY([dimensionTypeId])
REFERENCES [dbo].[DimensionType] ([dimensionTypeId])
GO
ALTER TABLE [dbo].[Dimension]  WITH CHECK ADD FOREIGN KEY([subjectId])
REFERENCES [dbo].[Subject] ([subjectId])
GO
ALTER TABLE [dbo].[Lesson]  WITH CHECK ADD FOREIGN KEY([lessonTypeId])
REFERENCES [dbo].[LessonType] ([lessonTypeId])
GO
ALTER TABLE [dbo].[Lesson]  WITH CHECK ADD FOREIGN KEY([subjectId])
REFERENCES [dbo].[Subject] ([subjectId])
GO
ALTER TABLE [dbo].[PricePackage]  WITH CHECK ADD FOREIGN KEY([subjectId])
REFERENCES [dbo].[Subject] ([subjectId])
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD FOREIGN KEY([dimensionId])
REFERENCES [dbo].[Dimension] ([dimensionId])
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD FOREIGN KEY([lessonId])
REFERENCES [dbo].[Lesson] ([lessonId])
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD FOREIGN KEY([subjectId])
REFERENCES [dbo].[Subject] ([subjectId])
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD FOREIGN KEY([dimensionTypeId])
REFERENCES [dbo].[DimensionType] ([dimensionTypeId])
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD FOREIGN KEY([lessonId])
REFERENCES [dbo].[Lesson] ([lessonId])
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD FOREIGN KEY([quizLevelId])
REFERENCES [dbo].[QuizLevel] ([quizLevelId])
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD FOREIGN KEY([subjectId])
REFERENCES [dbo].[Subject] ([subjectId])
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD FOREIGN KEY([testTypeId])
REFERENCES [dbo].[TestType] ([testTypeId])
GO
ALTER TABLE [dbo].[QuizQuestion]  WITH CHECK ADD FOREIGN KEY([questionId])
REFERENCES [dbo].[Question] ([questionId])
GO
ALTER TABLE [dbo].[QuizQuestion]  WITH CHECK ADD FOREIGN KEY([quizId])
REFERENCES [dbo].[Quiz] ([quizId])
GO
ALTER TABLE [dbo].[Registration]  WITH CHECK ADD  CONSTRAINT [FK__Registrat__lastU__66603565] FOREIGN KEY([lastUpdatedBy])
REFERENCES [dbo].[User] ([userId])
GO
ALTER TABLE [dbo].[Registration] CHECK CONSTRAINT [FK__Registrat__lastU__66603565]
GO
ALTER TABLE [dbo].[Registration]  WITH CHECK ADD FOREIGN KEY([packId])
REFERENCES [dbo].[PricePackage] ([packId])
GO
ALTER TABLE [dbo].[Registration]  WITH CHECK ADD  CONSTRAINT [FK__Registrat__userI__68487DD7] FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([userId])
GO
ALTER TABLE [dbo].[Registration] CHECK CONSTRAINT [FK__Registrat__userI__68487DD7]
GO
ALTER TABLE [dbo].[Slider]  WITH CHECK ADD FOREIGN KEY([link])
REFERENCES [dbo].[Subject] ([subjectId])
GO
ALTER TABLE [dbo].[SubjectExpert]  WITH CHECK ADD FOREIGN KEY([subjectId])
REFERENCES [dbo].[Subject] ([subjectId])
GO
ALTER TABLE [dbo].[SubjectExpert]  WITH CHECK ADD  CONSTRAINT [FK__SubjectEx__userI__6B24EA82] FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([userId])
GO
ALTER TABLE [dbo].[SubjectExpert] CHECK CONSTRAINT [FK__SubjectEx__userI__6B24EA82]
GO
ALTER TABLE [dbo].[TakeAnswer]  WITH CHECK ADD FOREIGN KEY([answerId])
REFERENCES [dbo].[Answer] ([answerId])
GO
ALTER TABLE [dbo].[TakeAnswer]  WITH CHECK ADD FOREIGN KEY([questionId])
REFERENCES [dbo].[Question] ([questionId])
GO
ALTER TABLE [dbo].[TakeAnswer]  WITH CHECK ADD FOREIGN KEY([quizTakeId])
REFERENCES [dbo].[CustomerQuiz] ([quizTakeId])
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK__User__roleId__6EF57B66] FOREIGN KEY([roleId])
REFERENCES [dbo].[UserRole] ([userRoleId])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK__User__roleId__6EF57B66]
GO
