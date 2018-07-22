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
    
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script><!-- jQuery -->
    
    <style type="text/css">
		#gridSample .col-1{
			background: red;
			height: 0.5cm;
		}    
		#gridSample .col-10{
			background: lightBlue;
			height: 0.5cm;
		}
		.center {
			text-align: center;
		}    
    </style>
  </head>
  <body ng-app="myApp">
      
  	  <c:url var="queryPhotoUrl" value="/display.do">
     	 <c:param name="action" value="getPhotoById"/>
         <c:param name="photoId" value="3"/>
      </c:url>
      
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
			      <h1>angularJS 顯示圖片測試</h1>
			      <h4 style="color:blue;">${ queryPhotoUrl }</h4>
			 </div>      	
			 <div class="col-1"></div>      	
      	  </div>
      </div>
      
      <div class="container" ng-controller="firstController" style="margin-top:2cm;">
      	  <div class="row">
			 <div class="col-1"></div>      	
			 <div class="col-10">
			 	
			 	<input type="text" ng-class="{ center : true }" value="{{ imgInedx }}" ng-model="imgInedx"/>
			 	<b>雙向綁定 imgInedx >>> {{ imgInedx }}</b>
			 	<br/><br/>
			 	<button type="button" ng-click="doClick()">顯示圖片(From DB)</button>
			 	<img ng-src="{{ myImgBlob }}"/>
			 
			 </div>      	
			 <div class="col-1"></div>      	
      	  </div>
      </div>
      
      <script type="text/javascript">
		   var myApp = angular.module("myApp", []);
		   myApp.controller('firstController', [ '$scope', '$http' , function($scope, $http){
			   
			   // 【init】
			   $scope.init = function(){
				    $scope.imgInedx = '1';
					$scope.myImgBlob = undefined;
			   };
			   $scope.init();
			   
			   $scope.doClick = function(){
				   
				   var postData = {
						'action'  : 'getPhotoById' , 
						'photoId' :  parseInt( $scope.imgInedx ) , 
				   };
				   
				   $http({	method: 'POST', 
							url: './display.do',
							headers: {
						        "Content-Type": "application/x-www-form-urlencoded"
						    },
	 						data: $.param( postData ),// 使用jQuery的 $.param ( https://www.jb51.net/article/114873.htm )
	 						responseType: 'arraybuffer',
	 						timeout: 120000 ,
 				   }).then(
 				      function( resp ){
 					     // console.log( ' SUCCESS resp >>> ' , resp );
 					     // console.log( " SUCCESS resp['data'] >>> " , resp['data'] );
 					     
 					     var blob = new Blob( [resp.data] , { type: "image/jpg" });
 					     $scope.myImgBlob = window.URL.createObjectURL( blob );
 					     
 				   	  }, 
 				   	  function( resp ){
 					     console.log( ' ERROR resp >>> ' , resp );
 				      }
 				   );
			   };
			   
		   }]);
      </script>
  </body>
</html>
