package com.ctbc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.ctbc.model.dao.EmpPhotoDAO;
import com.ctbc.model.vo.EmpPhotoVO;

@WebServlet(value = "/display.do", name = "DisplayPhotoController")
public class DisplayPhotoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	@Autowired
	private EmpPhotoDAO empPhotoDAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionName = request.getParameter("action");
		
		switch (actionName) {
			case "getPhotoById":
				String photoId = request.getParameter("photoId");
				try {
					EmpPhotoVO empPhotoVO = empPhotoDAO.getEmpPhotoById(Integer.parseInt(photoId));
					System.out.println(" >>> " + empPhotoVO);
					
					InputStream is = empPhotoVO.getPhotoFile();
					try(BufferedInputStream bis = new BufferedInputStream(is);
							BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());) {
						
						int readed = 0;
						byte[] bytes = new byte[2048 * 2];
						while ( ( readed = bis.read(bytes)) != -1  ) {
							System.out.println(" readed : " + readed + " kb.");
							bos.write(bytes);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (is != null) {
							is.close();
						}
					}
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
