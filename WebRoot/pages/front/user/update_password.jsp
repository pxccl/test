<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	pageContext.setAttribute("updatePasswdUrl", "pages/front/user/userServlet/updatePasswd");
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

<!-- scripts -->
<script src="js/general.js"></script>
<jsp:include page="/util/header_import.jsp" />
<script src="js/pages/sign.js"></script>
<!-- form -->
</head>

<body>
	<div class="body_wrap">
		<div class="container">
			<div id="mBody" style="width:47%;margin-left:25%;margin-top: 10%">
				<div class="add-comment styled boxed boxed-cream" id="addcomments">
					
					<div class="comment-form">
						<form action="${updatePasswdUrl}" method="post"
							onsubmit="return validateUpdateForm();" id="commentForm"
							class="ajax_form">
							<div class="form-inner">
								<div class="field_text">
									<label for="subject" class="label_title"><strong>原密码</strong></label>
									<input type="password" name="oldpassword" id="oldpassword" value=""
										placeholder="请输入原密码" class="inputtext input_middle required"
										onblur="validateOldPassword()" style="width:180px" /> <span
										id="oldpasswordSpan">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</div>
								<div class="clear"></div>
								<div class="field_text">
									<label for="subject" class="label_title"><strong>新密码</strong></label>
									<input type="password" name="newpassword" id="newpassword"
										value="" placeholder="请输入新密码"
										class="inputtext input_middle required"
										onblur="validateNewPassword()" style="width:180px" /> <span
										id="newpasswordSpan">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</div>
								<div class="field_text">
									<label for="subject" class="label_title"><strong>确认密码</strong></label>
									<input type="password" name="confpassword" id="confpassword" value=""
										placeholder="请输入确认密码" class="inputtext input_middle required"
										style="width:180px" onblur="validateConfPassword()" /> <span
										id="confpasswordSpan">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</div>
								<div class="clear"></div>
							</div>

							<div class="rowSubmit">
								<span class="btn"><input type="submit" id="submit"
									value="修改" /></span> <a
									onclick="document.getElementById('commentForm').reset();return false"
									href="#" class="link-reset btn btn-white"><span>重置</span></a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>