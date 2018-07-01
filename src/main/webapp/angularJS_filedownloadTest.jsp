<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 
<!DOCTYPE html>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="UTF-8">
    <title>angularJS_filedownload 測試</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.8/angular.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style type="text/css">
		#gridSample .col-1{
			background: red;
			height: 0.5cm;
		}    
		#gridSample .col-10{
			background: lightBlue;
			height: 0.5cm;
		}    
    </style>
  </head>
  <body ng-app="myApp">
      
      <div id="gridSample" class="container">
      	  <div class="row">
			 <div class="col-1"></div>      	
			 <div class="col-10"></div>      	
			 <div class="col-1"></div>      	
      	  </div>
      </div>
      
      <div  class="container">
      	  <div class="row">
			 <div class="col-1"></div>      	
			 <div class="col-10">
			      <h1>angularJS_filedownload 測試</h1>
			      <h4 style="color:blue;">http://localhost:8080/AngularJS_1_fileDownload/angularJS_filedownloadTest.jsp</h4>
			 </div>      	
			 <div class="col-1"></div>      	
      	  </div>
      </div>
      
      <div class="container" ng-controller="firstController" style="margin-top:2cm;">
      	  <div class="row">
			 <div class="col-1"></div>      	
			 <div class="col-10">
			 	<p>下載檔案的URL Pattern : <strong style="color:red;">{{ downloadFileUrlPattern }}</strong></p>
			 	
			 	<p>下載檔案的檔名 : <input type="text" value="" style="text-align:center;" ng-model="myFileName"/></p>
			 	
			 	<button type="button" class="btn btn-primary" ng-click="doDownload($event);">檔案下載：使用<c:out value="<button>" escapeXml="true"/></button><br><br><br>
			 	<a class="btn btn-warning" ng-click="doDownload($event);">檔案下載：使用<c:out value="<a>" escapeXml="true"/></a>
			 </div>      	
			 <div class="col-1"></div>      	
      	  </div>
      </div>
      
      
      
      <script type="text/javascript">
		   var myApp = angular.module("myApp", []);
		   myApp.controller('firstController', [ '$scope', '$http' , function($scope, $http){
			   
			   // 【init】
			   $scope.init = function(){
				   $scope.downloadFileUrlPattern = '<c:url value="/download.do"/>';
				   $scope.myFileName = 'salter.jpg';
			   };
			   $scope.init();
			   
			   // 【檔案下載】
			   $scope.doDownload = function( _$event ){
				   
				   var myParams = {
						"filename": $scope.myFileName
				   };
				   
				   $http({
					        url: "<%=request.getContextPath()%>/download.do",
					        method: "POST",
					        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
							params : myParams , // 送到Server端的參數
					        responseType: 'arraybuffer',
				         }
				   ).success(function (data, status, headers, config) {
					  		console.log( ' >>> data >>> ' , data );
				            //-----------------------------
				            var filename = "fuckYou.jpg";
				            var blob = new Blob( [data] , { type: "application/octet-stream"} );
				            var objectUrl = URL.createObjectURL( blob );
				            window.open(objectUrl);
				            console.log( ' objectUrl >>> ' , objectUrl );
			                var downloadLink = angular.element('<a></a>');
			                downloadLink.attr('href', URL.createObjectURL(blob));
			                downloadLink.attr('target','_self');
			                downloadLink.attr('download', myParams['filename']);
			                downloadLink[0].click();
				        }
				   ).error(function (data, status, headers, config) {
				            alert("download failed!!");
				   });
				   
			   };
			   
		   }]);
      </script>
  </body>
</html>
