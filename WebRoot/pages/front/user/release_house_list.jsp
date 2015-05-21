<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	
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
<!-- form -->
<script type="text/javascript">
	var height;
	var wHeight = 300;
	var wWidth = 700;
	var width;
	var iHeight;
	var iWidth;
	$(function(){
		height = document.body.clientHeight;
		width = document.body.clientWidth;
		
		iHeight = (window.screen.availHeight-30-wHeight)/2;
		iWidth = (window.screen.availWidth-10-wWidth)/2;
	});
//height=100,width=100,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no
	function addPic(id){
		window.open("pages/front/user/uploadPic.jsp?hsid="+id, "添加图片", "resizable=no,location=no,height="+wHeight+",width="+wWidth+",top="+iHeight+",left="+iWidth, false);
	}
</script>
</head>

<body>
	<div class="body_wrap">
		<div class="container">
			<div id="mBody" style="width:99%;margin-left:0%;margin-top: 5%">
				<table class="table table-hover">
					<thead>
						<tr>
							<td>
								<input type="checkbox" id="selAll" name="selAll" onclick="selectAll(this,'empno')" />
							</td>
							<td>房屋编号</td>
							<td>用户名称</td>
							<td>小区名称</td>
							<td>地址</td>
							<td>面积</td>
							<td>总楼层</td>
							<td>房屋结构</td>
							<td>访问量</td>
							<td>图片</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${allHouse != null}">
							<c:forEach items="${allHouse}" var="house" end="10">
								<tr>
									<td>
										<input type="checkbox" id="house" name="house" value="${house.hsid}" />
									</td>
									<td>${house.hsid}</td>
									<td>${house.user.uid}</td>
									<td>${house.community}</td>
									<td>${house.address}</td>
									<td>${house.area}</td>
									<td>${house.total}</td>
									<td>${house.structure}</td>
									<td>${house.visits}</td>
									<td>
										<a  href="javascript:addPic(${house.hsid})">
											<img alt="" src="images/icons/plus.png">
										</a>
										
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<!-- mBody End -->
		</div>
	</div>
</body>
</html>