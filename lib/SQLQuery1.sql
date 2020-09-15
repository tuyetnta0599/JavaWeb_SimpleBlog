USE master
GO 
CREATE DATABASE SimpleBlog
GO 
USE SimpleBlog
GO
CREATE TABLE [users]
(
	[email] VARCHAR(50) PRIMARY KEY,
	[password] VARCHAR(64) NOT NULL,
	[fullname] NVARCHAR(50) NOT NULL,
	[idRole] int NOT NULL,
	[status] VARCHAR(10) NOT NULL
)
GO 
CREATE TABLE [roles]
(
	[id] INT IDENTITY PRIMARY KEY,
	[name] VARCHAR(10) NOT NULL
)
GO 
CREATE TABLE [articles]
(
	[id] INT IDENTITY PRIMARY KEY,
	[title] NVARCHAR(100) NOT NULL,
	[content] NVARCHAR(500) NOT NULL,
	[shortDescription] NVARCHAR(200) NOT NULL,
	[author] VARCHAR(50) NOT NULL,
	[postingDate] DATETIME NOT NULL,
	[status] VARCHAR(10) NOT NULL
)
GO 
CREATE TABLE [articleComments]
(
	[id] INT IDENTITY PRIMARY KEY,
	[idArticle] INT ,
	[userComment] VARCHAR(50),
	[comment] NVARCHAR(400) NOT NULL,
	[commentDate] DATETIME NOT NULL,
)
GO 
ALTER TABLE dbo.users
ADD FOREIGN KEY (idRole) REFERENCES dbo.roles(id);

ALTER TABLE dbo.articles 
ADD FOREIGN KEY (author) REFERENCES dbo.users(email);

ALTER TABLE dbo.[articleComments]
ADD FOREIGN KEY (idArticle) REFERENCES dbo.articles(id);

ALTER TABLE dbo.[articleComments] 
ADD FOREIGN KEY (userComment) REFERENCES dbo.users(email);


--insert role
INSERT INTO dbo.roles
        ( name )
VALUES  ( 'admin'  -- name - varchar(10)
          )
INSERT INTO dbo.roles
        ( name )
VALUES  ( 'member'  -- name - varchar(10)
          )

--insert user
INSERT INTO dbo.users
        ( email ,
          password ,
          fullname ,
          idRole ,
          status
        )
VALUES  ( 'tuyet@gmail.com' , -- email - varchar(50)
          '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b' , -- password - varchar(64)
          N'Nguyen Thi Anh Tuyet' , -- fullname - nvarchar(50)
          '1' , -- idRole - varchar(10)
          'active'  -- status - varchar(10)
        )
INSERT INTO dbo.users
        ( email ,
          password ,
          fullname ,
          idRole ,
          status
        )
VALUES  ( 'hoang@gmail.com' , -- email - varchar(50)
          '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b' , -- password - varchar(64)
          N'Nguyen Minh Hoang' , -- fullname - nvarchar(50)
          '2' , -- idRole - varchar(10)
          'active'  -- status - varchar(10)
        )
INSERT INTO dbo.users
        ( email ,
          password ,
          fullname ,
          idRole ,
          status
        )
VALUES  ( 'trang@gmail.com' , -- email - varchar(50)
          '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b' , -- password - varchar(64)
          N'Cao Quynh Trang' , -- fullname - nvarchar(50)
          '2' , -- idRole - int
          'active'  -- status - varchar(10)
        )
INSERT INTO dbo.users
        ( email ,
          password ,
          fullname ,
          idRole ,
          status
        )
VALUES  ( 'nhu@gmail.com' , -- email - varchar(50)
          '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b' , -- password - varchar(64)
          N'nguyen ngoc quynh nhu' , -- fullname - nvarchar(50)
          '2' , -- idRole - int
          'active'  -- status - varchar(10)
        )

--insert article
INSERT INTO dbo.articles
        ( title ,
          content ,
          shortDescription ,
          author ,
          postingDate ,
          status
        )
VALUES  ( N'How to drink water' , -- title - nvarchar(100)
          N'abc' , -- content - nvarchar(50)
          N'abc' , -- shortDescription - nvarchar(200)
          'hoang@gmail.com' , -- author - varchar(50)
          GETDATE() , -- postingDate - datetime
          'active'  -- status - varchar(10)
        )
INSERT INTO dbo.articles
        ( title ,
          content ,
          shortDescription ,
          author ,
          postingDate ,
          status
        )
VALUES  ( N'How to drink water1' , -- title - nvarchar(100)
          N'abc' , -- content - nvarchar(500)
          N'abc' , -- shortDescription - nvarchar(200)
          'hoang@gmail.com' , -- author - varchar(50)
          GETDATE() , -- postingDate - datetime
          'active'  -- status - varchar(10)
        )

INSERT INTO dbo.articles
        ( title ,
          content ,
          shortDescription ,
          author ,
          postingDate ,
          status
        )
VALUES  ( N'tran anaconda' , -- title - nvarchar(100)
          N'tran khong lo' , -- content - nvarchar(500)
          N'tran cuc to' , -- shortDescription - nvarchar(200)
          'hoang@gmail.com' , -- author - varchar(50)
          GETDATE() , -- postingDate - datetime
          'active'  -- status - varchar(10)
        )
INSERT INTO dbo.articles
        ( title ,
          content ,
          shortDescription ,
          author ,
          postingDate ,
          status
        )
VALUES  ( N'ghien mi cay' , -- title - nvarchar(100)
          N'mi cay cua han quoc' , -- content - nvarchar(500)
          N'mi cay' , -- shortDescription - nvarchar(200)
          'hoang@gmail.com' , -- author - varchar(50)
          GETDATE() , -- postingDate - datetime
          'active'  -- status - varchar(10)
        )
INSERT INTO dbo.articles
        ( title ,
          content ,
          shortDescription ,
          author ,
          postingDate ,
          status
        )
VALUES  ( N'nuoc cam' , -- title - nvarchar(100)
          N'uong nuoc cam rat tot cho suc khoe, vi no co vitamin c' , -- content - nvarchar(50)
          N'nuoc cam rat tot' , -- shortDescription - nvarchar(200)
          'trang@gmail.com' , -- author - varchar(50)
          GETDATE() , -- postingDate - datetime
          'active'  -- status - varchar(10)
        )
INSERT INTO dbo.articles
        ( title ,
          content ,
          shortDescription ,
          author ,
          postingDate ,
          status
        )
VALUES  ( N'nuoc cam222' , -- title - nvarchar(100)
          N'uong nuoc cam rat tot cho suc khoe, vi no co vitamin c' , -- content - nvarchar(50)
          N'nuoc cam rat tot' , -- shortDescription - nvarchar(200)
          'nhu@gmail.com' , -- author - varchar(50)
          GETDATE() , -- postingDate - datetime
          'active'  -- status - varchar(10)
        )
INSERT INTO dbo.articles
        ( title ,
          content ,
          shortDescription ,
          author ,
          postingDate ,
          status
        )
VALUES  ( N'mi cay co hai' , -- title - nvarchar(100)
          N'mi cay cua han quoc' , -- content - nvarchar(500)
          N'tac hai cua mi cay' , -- shortDescription - nvarchar(200)
          'hoang@gmail.com' , -- author - varchar(50)
          GETDATE() , -- postingDate - datetime
          'active'  -- status - varchar(10)
        )
-- check login
--SELECT u.email, u.fullname, r.name AS roleName FROM dbo.users AS u JOIN dbo.roles AS r ON r.id = u.idRole WHERE u.email = ? AND u.password = ? AND u.status = 'new'

--getAllArticle
--SELECT a.id, a.title, a.shortDescription, u.fullname AS authorName, a.postingDate FROM dbo.articles AS a JOIN dbo.users AS u ON u.email = a.author WHERE a.status = 'active'

--update  status
--UPDATE dbo.articles SET status = ? WHERE id = ? AND status = ? 

--Select a.id, a.title, a.shortDescription, author = (select name from Registration where email = a.accountEmail), a.date from Article as a where a.content like ? and a.status = ? order by a.date desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY


--search with paging
--SELECT  a.id ,
--        a.title ,
--        a.shortDescription ,
--        u.fullname AS authorName ,
--        a.postingDate ,
--        a.status,
--		totalRow = COUNT(*) OVER()
--FROM    dbo.articles AS a
--        JOIN dbo.users AS u ON u.email = a.author
--WHERE   a.title LIKE ? COLLATE Latin1_General_CI_AI
--        AND (? IS NULL OR a.status = ?)
--		ORDER BY a.postingDate DESC
--		OFFSET ? ROW FETCH NEXT ? ROW ONLY

--SELECT id , title FROM dbo.articles WHERE id = ? AND status = ?;
--UPDATE dbo.articles SET status = 'new'
--SELECT a.title , a.content , a.shortDescription , u.fullname, a.postingDate , a.status FROM dbo.articles AS a JOIN dbo.users AS u ON u.email = a.author WHERE a.id = ?
--INSERT INTO dbo.users ( email , password , fullname , idRole , status ) VALUES ( z , '' , N'' , 0 , '' )
--INSERT INTO dbo.articles ( title , content , shortDescription , author , postingDate , status ) VALUES ( ? ,? , ? , ? , GETDATE() , 'new' )
--INSERT INTO dbo.comments ( idArticle , userComment , comment , commentDate ) VALUES ( 0 , '' , N'' , GETDATE() )
--SELECT ac.id , u.fullname AS email , ac.comment , ac.commentDate FROM dbo.articleComments AS ac	JOIN dbo.users AS u ON u.email = ac.userComment WHERE ac.idArticle = ?
SELECT u.email , u.password , u.fullname , r.name FROM dbo.users AS u JOIN dbo.roles AS r ON r.id = u.idRole