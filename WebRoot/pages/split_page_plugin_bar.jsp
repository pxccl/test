<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<link href="css/style.css" type="text/css" rel="stylesheet">
<%--	代码引入形式
	<jsp:include page="/pages/split_page_plugin_bar.jsp"/>
--%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%	// 如果要想针对于分页进行控制需要四个参数的内容
	int currentPage = 1 ;	// 表示当前所在的页
	int lineSize = 5 ;	// 每页显示的数据行
	String column = null ;	// 此内容暂时不进行修改
	String keyWord = null ;	// 表示查询全部
	int allRecorders = 0 ;	// 总记录数
	int pageSize = 0 ;	// 总页数，需要通过计算得到结果
	String url = null ;	// 分页地址
	StringBuffer actionUrl = new StringBuffer() ;	// 表示链接地址
	String paramName = null ;	// 参数的名称
	String paramValue = null ;	// 参数的内容
%>
<%
	try {
		currentPage = Integer.parseInt(request.getAttribute("currentPage").toString()) ;
		pageContext.setAttribute("currentPage", currentPage);
		lineSize = Integer.parseInt(request.getAttribute("lineSize").toString()) ;
		allRecorders = Integer.parseInt(request.getAttribute("allRecorders").toString()) ;
		column = (String) request.getAttribute("column") ;
		if (column == null || "".equals(column)) {	// 接收数据
			column = request.getParameter("defaultColumn") ;
		}
		keyWord = (String) request.getAttribute("keyWord") ;
		if (keyWord == null) {
			keyWord = "" ;
		}
		url = (String) request.getAttribute("url") ;
		paramName = (String) request.getAttribute("paramName") ;
		paramValue = (String) request.getAttribute("paramValue") ;
	} catch (Exception e) {}
	actionUrl.append(url).append("?")
		.append("col=").append(column) ;
	if (paramName != null && paramValue != null) {	// 有参数有内容
		actionUrl.append("&").append(paramName).append("=").append(paramValue) ;
	}
	actionUrl.append("&kw=").append(URLEncoder.encode(keyWord, "UTF-8"))
		.append("&ls=").append(lineSize)
		.append("&cp=");
%>

<%	// 计算总页数
	pageSize = (allRecorders + lineSize - 1) / lineSize ;
	if (pageSize == 0) {
		pageSize = 1 ;	// 因为程序的页数都是从1开始的
	}
%>

<%
	// 设置一个显示的基数，利用它来实现前几页或者是后几页选项的出现
	int pageFlag = 3 ;	// 每次可以显示前n页面，和后n页
	int startPage = 0 ;	// 设置循环的开始页
	int endPage = 0 ;	// 表示设置当前页之后的循环操作
%>
<div id="split_bar" style="text-align: right;">
	<ul class="pagination">
		<!-- 开始：控制上一页和首页，永恒都要进行显示 --> 
		<li class="<%=currentPage == 1 ? "disabled" : ""%>">
			<${currentPage == 1?'span':'a'} href="<%=actionUrl%><%=currentPage-1%>">&lt;&lt;</${currentPage == 1?'span':'a'}>
		</li>
		<%
			if (currentPage != 1) {
		%>
		<li class="<%=currentPage == 1 ? "active" : ""%>"><a href="<%=actionUrl%>1">1</a></li>
		<%
			}
		%>
		<!-- 结束：控制上一页和首页，永恒都要进行显示 -->
		
		<!-- 开始：设置中间的省略页 -->
		<%
			startPage = currentPage - pageFlag ;	// 计算开始页
			if (startPage <= 0) {	// 还没开始进行省略
				startPage = 2 ;	// 从第2页开始
			} else {
				if (startPage <= 2) {
					startPage = 2 ;
				} else {
		%>
					<li><span>...</span></li>
		<%
				}
			}
		%>
		<!-- 结束：设置中间的省略页 -->
		
		<!-- 开始：设置当前页之前的显示页面 -->
		<%
			// startPage = currentPage - pageFlag ;	// 确定开始页数
			for (int x = startPage ; x < currentPage ; x ++) {
		%>
				<li><a href="<%=actionUrl%><%=x%>"><%=x%></a></li>
		<%
			}
		%>
		<!-- 结束：设置当前页之前的显示页面 -->
		
		<!-- 开始：当前所在页 -->
		<li class="active"><a href="<%=actionUrl%><%=currentPage%>"><%=currentPage%></a></li>
		<!-- 结束：当前所在页 -->
		
		<!-- 开始：设置当前页之后的显示页面 -->
		<%
			endPage = currentPage + pageFlag ;	// 设置之后的几个页面
			if (endPage >= pageSize) {	// 超过了总页数
				endPage = pageSize - 1 ;	// 设置为总页数	
			}
			for (int x = currentPage + 1 ; x <= endPage ; x ++) {
		%>
				<li><a href="<%=actionUrl%><%=x%>"><%=x%></a></li>
		<%
			}
		%>
		<!-- 结束：设置当前页之后的显示页面 -->
		
		<!-- 开始：设置后面页数的省略页 -->
		<%
			if (endPage < pageSize - 1) {
		%>
				<li><span>...</span></li>
		<%
			}
		%>
		<!-- 结束：设置后面页数的省略页 -->
		
		<!-- 开始：控制下一页和尾页，永恒都要进行显示 -->
		<%
			if (currentPage != pageSize) {
		%>
		<li class="<%=currentPage == pageSize ? "active" : ""%>"><a href="<%=actionUrl%><%=pageSize%>"><%=pageSize%></a></li>
		<%
			}
		%>
		<li class="<%=currentPage == pageSize ? "disabled" : ""%>">
			<<%=currentPage == pageSize ? "span" : "a"%> href="<%=actionUrl%><%=currentPage+1%>">&gt;&gt;</<%=currentPage == pageSize ? "span" : "a"%>></li>
		<!-- 结束：控制下一页和尾页，永恒都要进行显示 -->
	</ul>
</div>  