<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<td>ID</td>
			<td>商品名称</td>
			<td>商品价格</td>
			<td>已售百分比</td>
		</tr>
		<c:forEach items="${range}" var="g">
		<tr>
			<td>${g.gid}</td>
			<td>${g.gname}</td>
			<td>${g.price}</td>
			<td>${g.bfb}</td>
		</tr>	
		</c:forEach>
		<tr>
			<td colspan="11">
				<a href="1">首页</a>
				<a href="${page-1}">上一页</a>
				<a href="${page+1}">下一页</a>
				<a href="${pages}">尾页</a>
			</td>
		</tr>
	</table>

</body>
</html>