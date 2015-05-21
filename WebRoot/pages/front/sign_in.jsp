<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	pageContext.setAttribute("signInUrl", "pages/front/userServlet/signIn");
%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
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

<jsp:include page="/util/header_import.jsp" />
<script src="js/pages/sign.js"></script>
<script type="text/javascript">
	$.post("utilServlet/getRand",{},function(obj){
		$("#code").val(obj);
	},"text");
</script>
</head>
<body class="back">
	<div class="body-wrap">
		<!--container-->
		<div class="container">
			<div style="margin-top:15px;width:99%;">
				<div style="float:left">
					<img src="images/logo-s.gif" width="123" height="54" alt="快租">
				</div>
				<div style="float:right;margin-top: 30px;font-size: 18px">
					<a href="pages/front/sign_up.jsp" class="btn-link"><span>注册</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- <a href="#" class="btn btn-info"><span>发布房间</span></a>&nbsp;&nbsp;&nbsp;&nbsp; -->
				</div>
			</div>
		</div>
		<div class="container">
			<img src="images/menu_topline.gif" width="99%" height="5">
			<div style="margin-top:55px;width:99%;">
			<div id="mBody" style="float:left;width:64%;margin-left:15px;">
				<div class="widget-container widget_gallery boxed boxed-cream">
					<div class="inner" >
						<div id="myCarousel" class="carousel slide" data-interval="2500" >
							<!-- Carousel items -->
							<div class="carousel-inner" >
								<div class="active item">
									<img src="images/bgh.jpg" alt="" />
									<div class="carousel-title">
										<h6>Ratatouille</h6>
										<p>The latest unstoppable hero</p>
									</div>
								</div>
								<div class="item">
									<img src="images\temp/top-slider-1.jpg" alt="" />
									<div class="carousel-title">
										<h6>Horton</h6>
										<p>Change your fate</p>
									</div>
								</div>
								<div class="item">
									<img src="images\temp/top-slider-2.jpg" alt="" />
									<div class="carousel-title">
										<h6>Horton</h6>
										<p>Change your fate</p>
									</div>
								</div>
								<div class="item">
									<img src="images/bg-foot.jpg" alt="" />
									<div class="carousel-title">
										<h6>Horton</h6>
										<p>Change your fate</p>
									</div>
								</div>
							</div>
							<!-- Carousel indicators -->
							<ol class="carousel-indicators">
								<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
								<li data-target="#myCarousel" data-slide-to="1"></li>
								<li data-target="#myCarousel" data-slide-to="2"></li>
								<li data-target="#myCarousel" data-slide-to="3"></li>
							</ol>
							<!-- Carousel nav -->
							<a class="carousel-control left" href="#myCarousel"
								data-slide="prev"></a> <a class="carousel-control right"
								href="#myCarousel" data-slide="next"></a>
						</div>
					</div>
				</div>
			</div>
			<div id="lBody" style="float:left;width:31%;margin-left:15px;">

				<div class="add-comment styled boxed boxed-cream" id="addcomments">
					<div class="add-comment-title gradient">
						<h3>
							<strong>登录快租</strong>
						</h3>
					</div>
					<div class="comment-form">
						<form action="${signInUrl}" method="post" onsubmit="return validateLoginForm();"
							id="commentForm" class="ajax_form">
								            <div class="form-inner">
					                <div class="field_text">
					                    <label for="subject" class="label_title"><strong>用户名</strong></label>
					                   <!--  <input type="text" name="admin.adid" id="admin.adid" value="" placeholder="请输入用户名" class="inputtext input_middle required" /> -->
					                  <input type="text" name="user.uid" id="user.uid" value="admin" placeholder="请输入用户名" class="inputtext input_middle required" onblur="validateUid()" style="width:180px"/>
					                  <span id="user.uidSpan">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					                </div>
					                <div class="clear"></div>
					                 <div class="field_text">
					                    <label for="subject" class="label_title"><strong>密&nbsp;&nbsp;&nbsp;&nbsp;码</strong></label>
					                    <input type="password" name="user.password" id="user.password" value="hello" placeholder="请输入密码" class="inputtext input_middle required" onblur="validatePassword()" style="width:180px"/>
					                    <span id="user.passwordSpan">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					                </div>
					                 <img src="pages/image.jsp">
					                <div class="field_text">
					                    <label for="subject" class="label_title"><strong>验证码</strong></label>
					                    <input type="text" name="code" id="code" value="" placeholder="请输入验证码" class="inputtext input_middle required"  style="width:180px" onblur="validateCode()"/>
					                    <span id="codeSpan">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					                </div>
					                <div class="clear"></div>
					            </div>
					
					            <div class="rowSubmit">
					           		<span class="btn"><input type="submit" id="submit" value="登录" /></span>
					                <a onclick="document.getElementById('commentForm').reset();return false" href="#" class="link-reset btn btn-white"><span>重置</span></a>
					            </div>
						</form>
					</div>
				</div>
				</div>
			</div>
		</div>
		<!--/ container -->
	</div>
</body>
</html>