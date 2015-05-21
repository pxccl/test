<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	pageContext.setAttribute("releaseHouseInforPath",
			"pages/front/user/houseServlet/releaseHouseInfor");
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
<jsp:include page="/util/header_import.jsp" />
<script src="js/jquery.powerful-placeholder.min.js"></script>
<script type="text/javascript">
	var toId;
	var num = 0;
	var wsuri = "ws://127.0.0.1:8080/BuyHouse/PxcInWebsocket;${user.uid};";
	var ws = null;
	function startWebSocket() {
		if ('WebSocket' in window)
			ws = new WebSocket(wsuri);
		else if ('MozWebSocket' in window)
			ws = new MozWebSocket(wsuri);
		else
			alert("not support");
		ws.onmessage = function(evt) {
			//alert(evt.data);
			if($("#disUid").val() == toId){
				clearSreen();
				$("#requestMsg").append(evt.data+"<br>");
				num++;
			}else{
				
			}
			
		};
		ws.onclose = function(evt) {
			//alert("close");
		};
		ws.onopen = function(evt) {
			// alert("open");
		};
	}
	function sendMsg() {
		ws.send($("#disUid").val()+";"+document.getElementById('sendMsg').value);
		document.getElementById('sendMsg').value="";
	}
	$(function(){
		startWebSocket();
	});
	function selectItem(uid){
		toId = uid;
		$("#disUid").val(uid);
		$("#title").html("<strong>To: "+uid+"</strong>");
		$("#requestMsg").html("");
		num=0;
		loadData();
		//$("#requestMsg").html("");
	}
	function loadData(){
		$.post("page/front/user/msgServlet/initMsgRecorder",{toId:toId,fromId:'${user.uid}'},function(obj){
			for (var x = 0 ; x < obj.allRecorderMessage.length ; x ++) {
				clearSreen();
				$("#requestMsg").append(obj.allRecorderMessage[x].suid +": "+obj.allRecorderMessage[x].title+"<br>");
				num ++;
			}
		},"json") ;
	}
	function clears(){
		$("#sendMsg").text("");
	}
	function clearSreen(){
		if(num >= 12){
			$("#requestMsg").html("");
			num = 0;
		}
	}
	function enterKey(){
		if(event.keyCode==13){
			sendMsg();
		}
	}
</script>
<!-- form -->
</head>
<!-- <body onload="startWebSocket();">
<input type="text" id="writeMsg"/>
<input type="button" value="send" onclick="sendMsg()"/>
</body> -->
<body onKeyDown = "enterKey()">
	<div id="Body" style="width:99%;margin-left:0%;margin-top: 2%">
		<div id="lBody" style="float:left;width:30%;margin-left:10px;">
			<div class="tabs_framed styled">
				<div class="inner">
					<div class="tab-content clearfix">
						<ul class="nav nav-tabs nav-stacked">
							<c:if test="${users != null}">
								<c:forEach items="${users}" var="auser">
									<c:if test="${auser.uid != user.uid}">
										<li><a href="javascript:selectItem('${auser.uid}');"><i class="ico-cat"></i>${auser.uid}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="mBody" style="float:left;width:66%;margin-left:10px;">
			<div class="tabs_framed styled">
				<div class="inner">
					<div class="tab-content clearfix">
						<div id="title">选择用户，发送消息</div>
						<div class="field_text field_textarea" style="border:1px solid #999999">
							<!-- <textarea id="requestMsg" placeholder="" style="height:250px" readonly="readonly">aaa<br>2323<br></textarea> -->
							<div id="requestMsg" style="height:220px;font-size: 18px;color: blue" ></div>
						</div>
						<div class="field_text field_textarea">
							<textarea id="sendMsg" placeholder="" style="height:70px"></textarea>
							<input type="hidden" value="" id="disUid">
						</div>
						<div class="rowSubmit" >
							<span class="btn"><input type="button" id="btn" value="发送"
								onclick="sendMsg()" /></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>