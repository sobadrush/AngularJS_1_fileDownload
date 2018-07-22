USE DB_Emp_Dept;

-- DROP TABLE EMP_PHOTO;

CREATE TABLE EMP_PHOTO
(
	PHOTO_ID int IDENTITY PRIMARY KEY,
    PHOTO_NAME nVarchar(20) NOT NULL,
	PHOTO_FILE varbinary(MAX)
);

SELECT * FROM EMP_PHOTO;

GO;


DECLARE @_i int;
DECLARE @_MAX int;
DECLARE @_i_string varchar(1);
DECLARE @_BASE_PATH nvarchar(100);
SET @_i = 1;
SET @_MAX = 6; -- 要產生幾筆資料
SET @_BASE_PATH = 'E:\CTBC_workspace_Oxygen_3a\AngularJS_1_fileDownload\src\main\webapp\WEB-INF\photos\';
 WHILE ( @_i <= @_MAX )
 BEGIN	
	--要迴圈的語法
	SET @_i_string = CONVERT(varchar(1) , @_i);
	-- SELECT @_i_string;
	-- PRINT @_i_string;

	DECLARE @FILE_NAME nVarchar(20);
	SET @FILE_NAME = 'pic' + @_i_string + '.jpg';
	-- PRINT @_BASE_PATH + @FILE_NAME;

	DECLARE @FILE_FULL_PATH nVarchar(100);
	SET @FILE_FULL_PATH = @_BASE_PATH + @FILE_NAME;
	-- PRINT @FILE_FULL_PATH;

	DECLARE @COMMAND nVarchar(1000);
	SET @COMMAND = 
			'INSERT INTO EMP_PHOTO ( PHOTO_NAME , PHOTO_FILE ) VALUES (''' +  + @FILE_NAME  + ''' , (' + -- T-SQL中，雙' → 表示 單'
				'SELECT * FROM ' + 
					'OPENROWSET ( BULK ''' + @FILE_FULL_PATH + ''' , SINGLE_BLOB ) AS TEMP' +
			'))'; 
	-- PRINT @COMMAND; 

	EXECUTE (@COMMAND);

	--加1
	Set @_i = @_i + 1
 END

