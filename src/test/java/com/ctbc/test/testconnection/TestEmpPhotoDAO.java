package com.ctbc.test.testconnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ctbc.model.dao.EmpPhotoDAO;
import com.ctbc.model.vo.EmpPhotoVO;

import _01_config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { RootConfig.class })
@Transactional
//@ActiveProfiles(profiles = { "" })
public class TestEmpPhotoDAO {

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
	private EmpPhotoDAO empPhotoDAO;

	@Test
	@Ignore
	@Rollback(true)
	public void test_001() throws SQLException, IOException {
		List<EmpPhotoVO> empPhotoList = empPhotoDAO.getAllEmpPhoto();
		for (EmpPhotoVO vo : empPhotoList) {
			System.out.println(" >>> " + vo);
		}
	}

	@Test
//	@Ignore
	@Rollback(true)
	public void test_002() throws SQLException, IOException {
		EmpPhotoVO empPhotoVO = empPhotoDAO.getEmpPhotoById(1);
		System.out.println(" >>> " + empPhotoVO);
	}

}
