<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath %>">
<meta charset="utf-8">
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title></title>

<!-- main JS libs -->
<script src="js/libs/modernizr.min.js"></script>
<script src="js/libs/jquery-1.10.0.js"></script>
<script src="js/libs/jquery-ui.min.js"></script>
<script src="js/libs/bootstrap.min.js"></script>

<!-- Style CSS -->
<link href="css/bootstrap.css" media="screen" rel="stylesheet">
<link href="style.css" media="screen" rel="stylesheet">

<!-- General Scripts -->
<script src="js/general.js"></script>

<jsp:include page="/util/header_import.jsp"/>
</head>
<body class="back">
<div class="body-wrap">
    <!--container-->
    <div class="container">
    	<div style="margin-top:15px;width:99%;">
    		<div style="float:left">
    			<img src="images/logo-s.gif" width="123" height="54" alt="快租">
    		</div>
			<div style="float:right;margin-top: 30px;font-size: 18px" >
				<a href="pages/front/sign_in.jsp" class="btn-link"><span>登录</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="pages/front/sign_up.jsp" class="btn-link"><span>注册</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
		  	</div>
		</div>
    </div>
     <div class="container">
      <img src="images/menu_topline.gif" width="99%" height="5">
			<div class="widget-container widget_search styled boxed-cream"
				style="float:right;margin-right:30px;">
				<form method="post" id="searchform" action="#">
					<div class="clearfix">
						<div style="float:right">
							<span class="btn"> <input type="submit" value="查询" /></span>
							<div class="field_text">
								<input name="search" value="" type="text"
									placeholder="搜房子" />
							</div>
						</div>
					</div>
				</form>
			</div>
		<div><p>注册快租客 立即领取200元房费</p></div>
		<div class="clear"></div>
		<!--  <div style="width: 100%;max-width: 1920px;	min-width: 960px;height: 100px;	background-color: #FDCA00;text-align: center;"><p style="font-size: 30px;position: relative;top: 30px;">注册快租客 立即领取200元房费</p></div> -->
		 <div class="clear"></div>
		 <div style="width: 100%;overflow: auto;">
		 <div style="width: 1000px;	margin: 0 auto;">
		  <ul style="list-style-type: none;	padding: 10px;">
		  <li  style="float:left;padding: 10px;"><img src="images/house/1.jpg" width="300" height="248"></li>
		 
		  
		    <li  style="float:left;padding: 10px;"><img src="images/house/2.jpg" width="300" height="248"></li>
		  
		  
		    <li  style="float:left;padding: 10px;"><img src="images/house/3.jpg" width="300" height="248"></li>
		
		  
		    <li style="float:left;padding: 10px;"><img src="images/house/4.jpg" width="300" height="248"></li>
		
		  
		    <li style="float:left;padding: 10px;"><img src="images/house/5.jpg" width="300" height="248"></li>
		
		  
		  
		    <li style="float:left;padding: 10px;"><img src="images/house/6.jpg" width="300" height="248"></li>
		
		  
		    <li style="float:left;padding: 10px;"><img src="images/house/7.jpg" width="300" height="248"></li>
		
		  
		    <li style="float:left;padding: 10px;"><img src="images/house/8.jpg" width="300" height="248"></li>
		
		  
		    <li style="float:left;padding: 10px;"><img src="images/house/9.jpg" width="300" height="248"></li>
		  </ul>
		 </div>
		  </div>
    </div>
    <!--/ container -->
</div>
</body>
</html>