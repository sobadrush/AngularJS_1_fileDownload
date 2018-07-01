package com.ctbc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/download.do", name = "FileDownloadServlet")
public class FileDownloadController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 2048;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.err.println("================== 【doGet】 ===================");
		String filename = req.getParameter("filename");

		try (InputStream photoInputStream = req.getServletContext().getResourceAsStream("/WEB-INF/photos/" + filename);
			 ServletOutputStream os = resp.getOutputStream();
			 BufferedInputStream buff_is = new BufferedInputStream(photoInputStream);
			 BufferedOutputStream buff_os = new BufferedOutputStream(os);) 
		{
			
			byte[] readed = new byte[BUFFER_SIZE];
			int bb = 0;
			while ((bb = buff_is.read(readed)) != -1) {
				System.out.println(" >>> READ SIZE >>> " + bb + " kb");
				buff_os.write(readed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.err.println("================== 【doPost】 ===================");
		doGet(req, resp);
	}

}
