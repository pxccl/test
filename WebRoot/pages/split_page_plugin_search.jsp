<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*"%>
<%--	引入代码方式
	<jsp:include page="/pages/split_page_plugin_search.jsp"/> 
--%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%	// 这些内容全部需要通过外部传递
	String actionForm = null ;	// 搜索的提交路径
	String columnData = null ;	// 可选的搜索字段
	String column = null ;		// 检索的列
	String keyWord = null ;		// 检索的关键字
	int allRecorders = 0 ;		// 总记录数
	String paramName = null ;	// 参数的名称
	String paramValue = null ;	// 参数的内容
%>
<%
	
	try {	// 一般不应该出错，因为调用组件的时候内容必须完整
		actionForm = (String) request.getAttribute("url") ;
		columnData = (String) request.getAttribute("columnData") ;
		column = (String) request.getAttribute("column") ;
		if (column == null || "".equals(column)) {	// 接收数据
			column = (String) request.getAttribute("defaultColumn") ;
		}
		keyWord = (String) request.getAttribute("keyWord") ;
		if (keyWord == null) {
			keyWord = "" ;
		} else {
			keyWord = URLDecoder.decode(keyWord, "UTF-8") ;
		}
		allRecorders = Integer.parseInt((String) request.getAttribute("allRecorders")) ;
		paramName = (String) request.getAttribute("paramName") ;
		paramValue = (String) request.getAttribute("paramValue") ;
	} catch (Exception e){}
%>
<div style="text-align: center;">
<form action="<%=actionForm%>" method="post">
	<%
		if (columnData != null) {
	%>
	<select id="col" name="col">
	<%
		String result [] = columnData.split("\\|") ;
		for (int x = 0 ; x < result.length ; x ++) {
			String temp [] = result[x].split(":") ;
	%>
			<option value="<%=temp[1]%>" <%=temp[1].equals(column)?"selected":""%>><%=temp[0]%></option>
	<%
		}
	%>
	</select>
	<%
		}
	%>
	<input type="text" name="kw" id="kw" value="<%=keyWord%>">
	<input type="hidden" name="<%=paramName%>" value="<%=paramValue%>">
	<input type="submit" value="检索">
	<br><span>满足查询条件的数据量：<%=allRecorders%></span>
</form>
</div>

