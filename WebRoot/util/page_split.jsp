<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	pageContext.setAttribute("empListSplit", "page/back/admin/emp/EmpServlet?state=listSplit");
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
			<div id="button">
				<a href="${actionUrl}&cp=1" class="btn btn-left ${cp==1?'disabled':'' }" target="main" ><span>首页</span></a>
				<a href="${actionUrl}&cp=${cp-1}" class="btn btn-acute ${cp==1?'disabled':'' }" target="main"><span><img alt="" src="images/pre.png" style="padding-top:11px"></span></a>
				<c:if test="${count > 0 }">
				<%
					int i ;
					for(i = 0;i<(Integer)request.getAttribute("pageSize");i++){
						pageContext.setAttribute("tabNum", i+1);
				%>
						<a href="${actionUrl}&cp=${tabNum}" class="btn btn-acute ${cp==tabNum?'disabled':'' }"  target="main"><span>${tabNum}</span></a> 
				<%
					}
				%>
					
				</c:if>
				<a href="${actionUrl}&cp=${cp+1}" class="btn btn-acute ${cp==pageSize?'disabled':'' }"  target="main"><span><img alt="" src="images/next.png" style="padding-top:11px"></span></a> 
				<a href="${actionUrl}&cp=2" class="btn btn-right ${cp==pageSize?'disabled':'' }"  target="main"><span>尾页</span></a>
			</div>
			<!--/ container -->
		</div>
	</div>
</body>
</html>