package com.ctbc.test.testconnection;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ctbc.model.vo.EmpPhotoVO;

import _01_config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { RootConfig.class })
@Transactional
//@ActiveProfiles(profiles = { "" })
public class TestQueryBinaryPhoto {

	private static int TEST_CASE_NUM = 1;

	@Before
	public void setUp() throws Exception {
		String testNumStr = String.format("%03d", TEST_CASE_NUM++);
		System.out.println("========================================================");
		System.out.println("================= TEST_CASE_NUM : " + testNumStr + " ==================");
		System.out.println("========================================================");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("========================================================");
		System.out.println("========================================================");
		System.out.println("========================================================");
	}

	@Autowired
	private DataSource ds;

	@Test
//	@Ignore
	@Rollback(true)
	public void test_001() throws SQLException, IOException {
		Connection conn = ds.getConnection();
		final String SQL_COMMAND = "SELECT * FROM EMP_PHOTO;"; 
		PreparedStatement pstmt = conn.prepareStatement(SQL_COMMAND);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			EmpPhotoVO vo = new EmpPhotoVO();
			vo.setPhotoId(rs.getInt("PHOTO_ID"));
			vo.setPhotoName(rs.getString("PHOTO_NAME"));
			Blob blob = rs.getBlob("PHOTO_FILE");
			vo.setPhotoFile(blob.getBinaryStream());
			System.out.println( " >>> " + rs.getInt("PHOTO_ID") + " ___ " + rs.getString("PHOTO_NAME") );
		}
		
	}

}









