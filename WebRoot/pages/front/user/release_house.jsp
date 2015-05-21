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
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title></title>

<!-- scripts -->
<jsp:include page="/util/header_import.jsp" />
<script src="js/house.js"></script>
<!-- form -->
</head>

<body>
	<div id="mBody" style="width:99%;margin-left:0%;margin-top: 0%">
		<form action="${releaseHouseInforPath}" method="post" id="detailsForm">
			<table width="100%" border="1" align="center" cellpadding="5" frame="void" onsubmit="return validateInsert()"
				cellspacing="0" bgcolor="#eae5da">
				<tr onmouseOver="changeColor(this,'white')"
					onmouseOut="changeColor(this,'#eae5da')">
					<td align="center" valign="middle"><strong>小区名称：</strong></td>
					<td valign="middle"><input type="text" name="house.community"
						id="house.community" class="init" onblur="validateCommunity()">
					</td>
					<td align="center" valign="middle"><span id="house.communitySpan">&nbsp;&nbsp;&nbsp;</span>
					</td>
				</tr>
				<tr onmouseOver="changeColor(this,'white')"
					onmouseOut="changeColor(this,'#eae5da')">
					<td align="center" valign="middle"><strong>小区地址：</strong></td>
					<td valign="middle"><input type="text" name="house.address"
						id="house.address" class="init" onblur="validateAddress()">
					</td>
					<td align="center" valign="middle"><span
						id="house.addressSpan">&nbsp;&nbsp;&nbsp;</span></td>
				</tr>
				<tr onmouseOver="changeColor(this,'white')"
					onmouseOut="changeColor(this,'#eae5da')">
					<td align="center" valign="middle"><strong>房屋面积：</strong></td>
					<td valign="middle"><input type="text" name="house.area"
						id="house.area" class="init" onblur="validateArea()"></td>
					<td align="center" valign="middle"><span id="house.areaSpan">&nbsp;&nbsp;&nbsp;</span>
					</td>
				</tr>
				<tr onmouseOver="changeColor(this,'white')"
					onmouseOut="changeColor(this,'#eae5da')">
					<td align="center" valign="middle"><strong>总楼层数：</strong></td>
					<td valign="middle"><input type="text" name="house.total"
						id="house.total" class="init" onblur="validateTotal()"></td>
					<td align="center" valign="middle"><span id="house.totalSpan">&nbsp;&nbsp;&nbsp;</span>
					</td>
				</tr>
				<tr onmouseOver="changeColor(this,'white')"
					onmouseOut="changeColor(this,'#eae5da')">
					<td align="center" valign="middle"><strong>所在楼层：</strong></td>
					<td valign="middle"><input type="text" name="house.floor"
						id="house.floor" class="init" onblur="validateFloor()"></td>
					<td align="center" valign="middle"><span id="house.floorSpan">&nbsp;&nbsp;&nbsp;</span>
					</td>
				</tr>
				<tr onmouseOver="changeColor(this,'white')"
					onmouseOut="changeColor(this,'#eae5da')">
					<td align="center" valign="middle"><strong>房屋朝向：</strong></td>
					<td valign="middle"><select name="house.orientation"
						id="house.orientation">
							<%-- <c:forEach items="${allOrientation}" var="ori"> --%>
								<%-- <option value="${ori.title}">${ori.title}</option> --%>
							<%-- </c:forEach> --%>
							<option value="东">东</option>
							<option value="南">南</option>
							<option value="西">西</option>
							<option value="北">北</option>
							<option value="北">风</option>
					</select></td>
					<td align="center" valign="middle"><span
						id="house.orientationSpan">&nbsp;&nbsp;&nbsp;</span></td>
				</tr>
				<tr onmouseOver="changeColor(this,'white')"
					onmouseOut="changeColor(this,'#eae5da')">
					<td align="center" valign="middle"><strong>房屋结构：</strong></td>
					<td valign="middle"><select name="house.structure"
						id="house.structure">
							<%-- <c:forEach items="${allStructure}" var="str">
								<option value="${str.title}">${str.title}</option>
							</c:forEach> --%>
							<option value="一厅一卫">一厅一卫</option>
							<option value="一厅二室一卫">一厅二室一卫</option>
							<option value="一厅三室两卫">一厅三室两卫</option>
							<option value="二厅五室三卫">二厅五室三卫</option>
					</select></td>
					<td align="center" valign="middle"><span
						id="house.structureSpan">&nbsp;&nbsp;&nbsp;</span></td>
				</tr>
				<tr onmouseOver="changeColor(this,'white')"
					onmouseOut="changeColor(this,'#eae5da')">
					<td align="center" valign="middle"><strong>装修程度：</strong></td>
					<td valign="middle"><select name="house.renovation"
						id="house.renovation">
							<option value="精装修">精装修</option>
							<option value="普通装修">普通装修</option>
							<option value="毛坯">毛坯</option>
							<%-- <c:forEach items="${allRenovation}" var="ren">
								<option value="${ren.title}">${ren.title}</option>
							</c:forEach> --%>
					</select></td>
					<td align="center" valign="middle"><span
						id="house.structureSpan">&nbsp;&nbsp;&nbsp;</span></td>
				</tr>
				<tr onmouseOver="changeColor(this,'white')"
					onmouseOut="changeColor(this,'#eae5da')">
					<td align="center" valign="middle"><strong>房源类型：</strong></td>
					<td valign="middle"><select name="house.type" id="house.type">
							<%-- <c:forEach items="${allType}" var="type">
								<option value="${type.title}">${type.title}</option>
							</c:forEach> --%>
							<option value="0">信息已删除</option>
							<option value="1">可以正常浏览</option>
							<option value="2">已出租</option>
							<option value="3">已卖出</option>
					</select></td>
					<td align="center" valign="middle"><span id="house.typeSpan">&nbsp;&nbsp;&nbsp;</span>
					</td>
				</tr>
				<tr onmouseOver="changeColor(this,'white')"
					onmouseOut="changeColor(this,'#eae5da')">
					<td align="center" valign="middle"><strong>房屋价格：</strong></td>
					<td valign="middle"><input type="text" name="house.price"
						id="house.price" class="init" onblur="validatePrice()"> <br>
					<span style="width: 50px;">如果是出租写上月租金价格，如果是出售写上总价格</span></td>
					<td align="center" valign="middle"><span id="house.priceSpan">&nbsp;&nbsp;&nbsp;</span>
					</td>
				</tr>
				<tr onmouseOver="changeColor(this,'white')"
					onmouseOut="changeColor(this,'#eae5da')">
					<td align="center" valign="middle"><strong>小区介绍：</strong></td>
					<td valign="middle"><input type="text" name="house.note"
						id="house.note" class="init" onblur="validateNote()"></td>
					<td align="center" valign="middle"><span id="house.noteSpan">&nbsp;&nbsp;&nbsp;</span>
					</td>
				</tr>
				<tr onmouseOver="changeColor(this,'white')"
					onmouseOut="changeColor(this,'#eae5da')">
					<td valign="middle" align="center" colspan="4">
						<input type="submit" value="发布"> <input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- mBody End -->
</body>
</html>