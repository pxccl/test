<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	
	pageContext.setAttribute("updateUserUrl","pages/front/user/userServlet/updateUser");
	
%>
<!doctype html>
<html>
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

<script src="js/jquery.powerful-placeholder.min.js"></script>

<!-- Style CSS -->
<link href="css/bootstrap.css" media="screen" rel="stylesheet">
<link href="style.css" media="screen" rel="stylesheet">
<style type="text/css">
		 #preview, .img, img  {
			width:200px;
			height:200px;
		}
		#preview {
			border:0px solid #000;
		}
	</style>
<script type="text/javascript">
	function sDialog(){
		var detailsForm = document.forms['detailsForm'];
		detailsForm.photoFile.click();
	}
	function previews(file) {
		var prevDiv = document.getElementById('preview');
		if (file.files && file.files[0]) {
			var reader = new FileReader();
			reader.onload = function(evt) {
				prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
			} 
			reader.readAsDataURL(file.files[0]);
		} else {
			prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
		}
	}
</script>
<!-- scripts -->
<script src="js/general.js"></script>
<jsp:include page="/util/header_import.jsp" />
<script src="js/pages/sign.js"></script>
<!-- form -->
</head>

<body>
	<div class="body_wrap">
		<div class="container">
			<div id="mBody" style="width:95%;margin-left:5%;margin-top: 5%">
				<form action="${updateUserUrl}" method="post"
					id="detailsForm"
					class="ajax_form" enctype="multipart/form-data">
					<table class="table table-responsive">
						<tbody>
							<tr>
								<td>
									<label for="user.uid" >用户名</label>
								</td>
								<td>
									<div class="col-md-4">
										<input readonly="readonly" class="form-control" type="text" id="user.uid" name="user.uid" value="${signUser.uid}"/>
									</div>
								</td>
								<td>&nbsp;</td>
								<td colspan="1" rowspan="4">
									<input name="photoFile" id="photoFile" type="file" style="display:none;" onchange="previews(this)">
									
									<a href="javascript:void(sDialog());" >
										<div id="preview">
											<img src="upload/${signUser.photo==null?'nophoto.jpg':signUser.photo}" alt="" />
										</div>
									</a>
								</td>
							</tr>
							<tr>
								<td>
									<label for="user.name" >姓名</label>
								</td>
								<td>
									<div class="col-md-4">
										<input class="form-control" type="text" id="user.name" name="user.name" value="${signUser.name}"/>
									</div>
								</td>
								
							</tr>
							<tr>
								<td>
									<label for="user.card" >身份证号</label>
								</td>
								<td>
									<div class="col-md-4">
										<input class="form-control" type="text" id="user.card" name="user.card" value="${signUser.card}"/>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<label for="user.regdate" >注册日期</label>
								</td>
								<td>
									<div class="col-md-4">
										<input  readonly="readonly" class="form-control" type="text" id="user.regdate" name="user.regdate" value="${signUser.regdate}"/>
									</div>
								</td>
							</tr>
							<tr>
								<td> 
									<label for="user.phone" >电话号码</label>
								</td>
								<td>
									<div class="col-md-4">
										<input class="form-control" type="text" id="user.phone" name="user.phone" value="${signUser.phone}"/>
									</div>
								</td>
								<td> 
									<label for="user.lastLoginDate" >上次登录</label>
								</td>
								<td>
									<div class="col-md-4">
										<input readonly="readonly" class="form-control" type="text" id="user.lastLoginDate" name="user.lastLoginDate" value="${signUser.lastLoginDate}"/>
									</div>
								</td>
							</tr>
							<tr>
								<td> 
									<label for="user.address" >地址</label>
								</td>
								<td colspan="3">
									<div class="col-md-4">
										<input class="form-control" type="text" id="user.address" name="user.address" value="${signUser.address}"/>
									</div>
								</td>
							</tr>
							<tr >	
								<td colspan="4">
									<div style="margin-left: 29%">
										<span class="btn" style="float:left"><input type="submit" id="submit" value="修改" /></span> 
										<a onclick="document.getElementById('detailsForm').reset();return false" href="#" class="link-reset btn btn-white" style="float:left;margin-left: 10%"><span>重置</span></a>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<!-- mBody End -->
		</div>
	</div>
</body>
</html>