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
	pageContext.setAttribute("signInUrl","pages/front/user/userServlet/signIn");
	pageContext.setAttribute("logoutUrl","pages/front/user/userServlet/logout");
	pageContext.setAttribute("releaseHouseUrl","pages/front/user/userServlet/releaseHouse");
	try{
		pageContext.setAttribute("lastLoadTime",  new SimpleDateFormat("MM-dd HH:mm").format(((User)session.getAttribute("signUser")).getLastLoginDate()));
		
	}catch(Exception e){
	}
	
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
<script type="text/javascript">
	var height;
	var width;
	//
	var n = 0;
	function show(num) {
		if (n != 0) {
			$("#" + n).attr("style", "display:none");
		}
		$("#" + num).attr("style", "display:block");
		n = num;
	}
	//获取请求url路径上的参数
	function getParameters(name){
		var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)","i");
		var r = window.location.search.substr(1).match(reg);
		if(r!=null)
			return unescape(r[2]);
		return null;
	}
	$(function() {
		height = document.body.clientHeight;
		width = document.body.clientWidth;
		$("#main").attr("height", height + 99);
		
		
		var pagePath = getParameters("pagePathName");
		if(pagePath!=null&&pagePath!=''){
			//url?pagePathName=userDetailsLink  用户详情显示
			if(pagePath == 'userDetailsLink'){
				show(1);
				document.getElementById(pagePath).click();
			}
			if(pagePath == 'releaseHouseLink'){
				show(2);
				document.getElementById(pagePath).click();
			}
			if(pagePath == 'listReleaseHouseLink'){
				show(2);
				document.getElementById(pagePath).click();
			}
		}
	});
</script>
<!-- General Scripts -->
<script src="js/general.js"></script>

<jsp:include page="/util/header_import.jsp" />
<script src="js/pages/sign.js"></script>
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
					欢迎，${signUser.uid}；上次登录：${lastLoadTime}
					<a href="${logoutUrl}" class="btn-link"><span>退出</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${releaseHouseUrl}" class="btn btn-info"><span>发布房屋</span></a>
					<!-- <a href="#" class="btn btn-info"><span>发布房间</span></a>&nbsp;&nbsp;&nbsp;&nbsp; -->
				</div>
			</div>
		</div>
		<div class="container">
			<img src="images/menu_topline.gif" width="99%" height="5">
			<div id="body" style="margin-top:35px;">
				<div id="lBody" style="float:left;width:21%;margin-left:15px;">
					<div class="tabs_framed styled">
						<div class="inner">
							<div class="tab-content clearfix">
								<ul class="nav nav-tabs nav-stacked">
									<li><a href="javascript:show(1)"><i class="ico-cat"></i><strong>用户信息</strong></a>
										<div id="1" style="display:none">
											<img src="images/menu_topline.gif" width="98%" height="5" />
											<ul class="nav0 nav-tabs nav-stacked">
												<li><a id="userDetailsLink" href="pages/front/user/user_details.jsp" target="main"><i class="ico-cat ico-cat6"></i>用户详细信息</a></li>
												<li><a href="pages/front/user/update_password.jsp" target="main"><i class="ico-cat ico-cat6"></i>修改密码</a></li>
											</ul>
											<img src="images/menu_topline.gif" width="98%" height="5" />
										</div>
									</li>
									<li><a href="javascript:show(2)"><i class="ico-cat"></i><strong>房屋发布信息</strong></a>
										<div id="2" style="display:none">
											<img src="images/menu_topline.gif" width="98%" height="5" />
											<ul class="nav0 nav-tabs nav-stacked">
												<li ><a id="releaseHouseLink" href="pages/front/user/release_house.jsp" target="main"><i class="ico-cat ico-cat6"></i>发布房屋</a></li>
												<li><a id="listReleaseHouseLink" href="pages/front/user/houseServlet/listHouseInfor" target="main"><i class="ico-cat ico-cat6"></i>已发布房屋</a></li>
											</ul>
											<img src="images/menu_topline.gif" width="98%" height="5" />
										</div>
									</li>
									<li><a href="page/front/user/msgServlet/initMsgPage" target="main"><i class="ico-cat"></i><strong>消息通知</strong></a>
										<!-- <div id="3" style="display:none">
											<img src="images/menu_topline.gif" width="98%" height="5" />
											<ul class="nav0 nav-tabs nav-stacked">
												<li><a href="#" target="main"><i class="ico-cat ico-cat6"></i>新消息</a></li>
											</ul>
											<img src="images/menu_topline.gif" width="98%" height="5" />
										</div> -->
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div id="mBody" style="float:left;width:75%;margin-left:15px;">
					<div class="tabs_framed styled">
						<div class="inner">
							<div class="tab-content clearfix">
								<iframe src="page/front/user/msgServlet/initMsgPage" id="main" name="main"
									width="100%" scrolling="yes" frameborder="0"></iframe>
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