<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	pageContext.setAttribute("empList", "page/back/admin/emp/EmpServlet?state=list");
	pageContext.setAttribute("empListSplit", "page/back/admin/emp/EmpServlet?state=listSplit");
	pageContext.setAttribute("empListDetails", "page/back/admin/emp/EmpServlet?state=listDetails");
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

<!-- scripts -->
<script type="text/javascript">
	var height;
	var width;
	//
	var n = 0;
	function show(num){
		if(n != 0){
			$("#"+n).attr("style","display:none");
		}
		$("#"+num).attr("style","display:block");
		n = num;
	}
	$(function(){
		height = document.body.clientHeight;
		width = document.body.clientWidth;
		$("#main").attr("height",height+147);
	});
</script>
<script src="js/general.js"></script>
<script src="js/jquery.powerful-placeholder.min.js"></script>
<link rel="stylesheet" href="css/chosen.css">
<script src="js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="js/nicEdit.js"></script>
<jsp:include page="/util/header_import.jsp"/>
</head>

<body>
	<div class="body_wrap">
		<div class="container" >
			<div style="margin-top:15px;margin-left:15px;width:95%;">
				<h1>MLDN快租网</h1>
			</div>
			<div id="body" style="margin-top:35px;">
				<div id="mBody" style="float:left;width:64%;margin-left:15px;" >
					<img alt="No zuo No die" src="images/bgh.jpg">
				</div>
				<div id="lBody" style="float:left;width:31%;margin-left:15px;">
					<div class="add-comment styled boxed boxed-cream" id="addcomments">
					    <!-- <span class="add-comment-close">×</span> -->
					    <div class="add-comment-title gradient"><h3><strong>登录快租</strong></h3></div>
					    <div class="comment-form">
					        <form action="pages/back/admin/LoginServlet/login" method="post" id="commentForm" class="ajax_form">
					            <div class="form-inner">
					                <div class="field_text">
					                    <label for="subject" class="label_title"><strong>用户名</strong></label>
					                   <!--  <input type="text" name="admin.adid" id="admin.adid" value="" placeholder="请输入用户名" class="inputtext input_middle required" /> -->
					                  <input type="text" name="admin.adid" id="admin.adid" value="" placeholder="请输入用户名" class="inputtext input_middle required" onblur=""/>
					                  <span id="admin.adid">&nbsp;&nbsp;</span>
					                </div>
					                <div class="clear"></div>
					                 <div class="field_text">
					                    <label for="subject" class="label_title"><strong>密&nbsp;&nbsp;&nbsp;&nbsp;码</strong></label>
					                    <input type="password" name="admin.password" id="admin.password" value="" placeholder="请输入密码" class="inputtext input_middle required" onblur="validateEmpty(admin.password)"/>
					                    <span id="admin.password">&nbsp;&nbsp;</span>
					                </div>
					                 <img src="pages/image.jsp">
					                <div class="field_text">
					                    <label for="subject" class="label_title"><strong>验证码</strong></label>
					                    <input type="text" name="code" id="code" value="" placeholder="请输入验证码" class="inputtext input_middle required"   maxLength="4" size="4"/>
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
		</div>
		<!--/ container -->
</body>
</html>