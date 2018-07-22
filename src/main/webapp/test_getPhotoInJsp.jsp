<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="UTF-8">
    <title>測試JSP顯示圖片(From DB)</title>
  </head>
  <body>
      <h1 style="color: blue;">>測試JSP顯示圖片(From DB)</h1>
      
      <c:url var="queryPhotoUrl" value="/display.do">
      	  <c:param name="action" value="getPhotoById"/>
          <c:param name="photoId" value="3"/>
      </c:url>
      
      <c:url value="/display.do">
      	  <c:param name="action" value="getPhotoById"/>
          <c:param name="photoId" value="3"/>
      </c:url>
      
      <hr>
      
      <hr/>
      圖片：<img alt="" src='${queryPhotoUrl}'/>
      
  </body>
</html>
