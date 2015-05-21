<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<script type="text/javascript">
		if(${flag == 'true'}){
			if(${msg != 'null'}){
				if(window.confirm("${msg}")){
					window.location = "<%=basePath%>${path}${pathExt}";
				}else{
					window.location = "<%=basePath%>${path}";
				}
			}else{
				window.location = "<%=basePath%>${path}${pathExt}";
			}
		}else{
			if(${msg != 'null'}){
				alert("${msg}") ;
			}
			window.location = "<%=basePath%>${path}";
		}
		
</script>