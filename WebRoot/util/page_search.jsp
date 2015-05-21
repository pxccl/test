<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<!-- form -->


</head>

<body>
	<div class="body_wrap">
		<div class="container">
			<div class="widget-container widget_search styled boxed-cream"
				style="float:right;">
				<form method="post" id="searchform" action="#">
					<div class="clearfix">
						<div style="float:right">
							<span class="btn"> <input type="submit" value="查询" /></span>
							<div class="field_text">
								<input name="search" value="" type="text"
									placeholder="编号/姓名/职位/领导/部门" />
							</div>
						</div>
					</div>
				</form>
			</div>
			<!--/ container -->
		</div>
	</div>
</body>
</html>