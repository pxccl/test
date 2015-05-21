<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

<!-- Include all needed stylesheets and scripts here -->


<!--[if lt IE 9]><script src="js/respond.min.js"></script><![endif]-->
<!--[if gte IE 9]>
<style type="text/css">
    .gradient {filter: none !important;}
</style>
<![endif]-->
</head>

<body>
	<div class="body_wrap">
		<div class="container">
			<div style="margin-top:15px;margin-left:15px;width:95%;">
				<h1>MLDN快租网</h1>
			</div>
			<div id="body" style="margin-top:35px;">
				<div id="lBody" style="float:left;width:21%;margin-left:15px;">
					<div class="tabs_framed styled">
						<div class="inner">
							<div class="tab-content clearfix">
								<ul class="nav nav-tabs nav-stacked">
								<c:if test="${allPrivileges != null}">
									<c:forEach items="${allPrivileges}" var="privileges">
										<li><a href="javascript:show(${privileges.pid})"><i class="ico-cat"></i>${privileges.title}</a>
											<div id="${privileges.pid}" style="display:none">
												<img src="images/menu_topline.gif" width="182" height="5" />
												<ul class="nav0 nav-tabs nav-stacked">
													<c:if test="${allPrivilegeDetails != null}">
														<c:forEach items="${allPrivilegeDetails}" var="privilegeDetails">
															<c:if test="${privilegeDetails.privilege.pid == privileges.pid}">
																<li><a href="#" target="main"><i class="ico-cat ico-cat6"></i>${privilegeDetails.title}</a></li>
															</c:if>
														</c:forEach>
													</c:if>
												</ul>
												<img src="images/menu_topline.gif" width="182" height="5" />
											</div>
										</li>
									</c:forEach>
								</c:if>
									
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div id="mBody" style="float:left;width:75%;margin-left:15px;">
					<div class="tabs_framed styled">
						<div class="inner">
							<div class="tab-content clearfix">
								<iframe src="" id="main" name="main"  style="width:'100%'; scrolling:'no'; frameborder:'0'"></iframe>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/ container -->
	</div>
</body>
</html>