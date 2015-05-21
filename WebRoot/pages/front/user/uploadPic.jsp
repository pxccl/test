<%@page import="java.text.SimpleDateFormat"%>
<%@page import="cn.mldn.house.vo.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	pageContext.setAttribute("uploadPicUrl","pages/front/user/houseServlet/uploadPic");
	
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
<!-- scripts -->
<!-- General Scripts -->
<script src="js/general.js"></script>
<style type="text/css">
		 #preview, .img1, img  {
			width:200px;
			height:200px;
		}
		#preview {
			border:0px;
		}
</style>
<script type="text/javascript">
	var hsid;
	function sDialog(n){
		var detailsForm = document.forms['picForm'];
		if(n == 1){
			detailsForm.photoFile1.click();
		}
		if(n == 2){
			detailsForm.photoFile2.click();
		}
		if(n == 3){
			detailsForm.photoFile3.click();
		}
	}
	function previews(file,n) {
		var prevDiv = document.getElementById('preview'+n);
		if (file.files && file.files[0]) {
			var reader = new FileReader();
			reader.onload = function(evt) {
				prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
			} 
			reader.readAsDataURL(file.files[0]);
		} else {
			prevDiv.innerHTML = '<div class="img1" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
		}
	}
	//获取请求url路径上的参数
	function getParameters(name){
		var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)","i");
		var r = window.location.search.substr(1).match(reg);
		if(r!=null)
			return unescape(r[2]);
		return null;
	}
	$(function(){
		hsid = getParameters("hsid");
	});
	function submits(){
		var form = document.getElementById("picForm");
		form.action="<%=basePath%>pages/front/user/houseServlet/uploadPic";
		form.submit();
	}
</script>
<jsp:include page="/util/header_import.jsp" />
</head>
<body class="back">
	<div class="body-wrap">
		<!--container-->
		<div class="container">
			<form action="" method="post"
					id="picForm"
					class="ajax_form" enctype="multipart/form-data">
					<table class="table table-responsive">
						<tbody>
							<tr>
								<td>
									<input name="photoFile1" id="photoFile1" type="file" style="display:none;" onchange="previews(this,1)">
									
									<a href="javascript:void(sDialog(1));" >
										<div id="preview1">
											<img src="upload/" alt="" />
										</div>
									</a>
								</td>
								<td>
									<input name="photoFile2" id="photoFile2" type="file" style="display:none;" onchange="previews(this,2)">
									
									<a href="javascript:void(sDialog(2));" >
										<div id="preview2">
											<img src="upload/" alt="" />
										</div>
									</a>
								</td>
								<td>
									<input name="photoFile3" id="photoFile3" type="file" style="display:none;" onchange="previews(this,3)">
									
									<a href="javascript:void(sDialog(3));" >
										<div id="preview3">
											<img src="upload/" alt="" />
										</div>
									</a>
								</td>
							</tr>
							<tr >	
								<td colspan="3">
									<div style="margin-left: 29%">
										<span class="btn" style="float:left"><input type="button" id="submit" onclick="submits" value="上传" /></span> 
									</div>
									<input type="hidden" name="house.hsid" id="hsid" value=""/>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
		</div>
		<!--/ container -->
	</div>
</body>
</html>