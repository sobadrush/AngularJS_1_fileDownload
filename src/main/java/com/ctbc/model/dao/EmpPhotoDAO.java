package com.ctbc.model.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ctbc.model.vo.EmpPhotoVO;

@Repository
public class EmpPhotoDAO {

	@Autowired
	private DataSource ds;
	
	/**
	 * 查詢所有的EmpPhoto
	 */
	public List<EmpPhotoVO> getAllEmpPhoto() throws SQLException {
		Connection conn = ds.getConnection();
		final String SQL_COMMAND = "SELECT * FROM EMP_PHOTO;"; 
		PreparedStatement pstmt = conn.prepareStatement(SQL_COMMAND);
		ResultSet rs = pstmt.executeQuery();
		
		List<EmpPhotoVO> empPhotoList = new ArrayList<>();
		while (rs.next()) {
			EmpPhotoVO vo = new EmpPhotoVO();
			vo.setPhotoId(rs.getInt("PHOTO_ID"));
			vo.setPhotoName(rs.getString("PHOTO_NAME"));
			Blob blob = rs.getBlob("PHOTO_FILE");
			vo.setPhotoFile(blob.getBinaryStream());
			// System.out.println( " >>> " + rs.getInt("PHOTO_ID") + " ___ " + rs.getString("PHOTO_NAME") );
			empPhotoList.add(vo);
		}
		return empPhotoList;
	}
	
	/**
	 * 查詢EmpPhoto by Id
	 */
	public EmpPhotoVO getEmpPhotoById(int photoId) throws SQLException {
		Connection conn = ds.getConnection();
		final String SQL_COMMAND = "SELECT * FROM EMP_PHOTO WHERE PHOTO_ID = ?"; 
		PreparedStatement pstmt = conn.prepareStatement(SQL_COMMAND);
		pstmt.setInt(1, photoId);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			EmpPhotoVO vo = new EmpPhotoVO();
			vo.setPhotoId(rs.getInt("PHOTO_ID"));
			vo.setPhotoName(rs.getString("PHOTO_NAME"));
			Blob blob = rs.getBlob("PHOTO_FILE");
			vo.setPhotoFile(blob.getBinaryStream());
			// System.out.println( " >>> " + rs.getInt("PHOTO_ID") + " ___ " + rs.getString("PHOTO_NAME") );
			return vo;
		}
		return null;
	}
	
}











