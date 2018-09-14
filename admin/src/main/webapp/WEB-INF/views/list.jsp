<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<table>
	<tr>
		<th>name</th>
		<th>age</th>
		<th>hobby</th>
	</tr>
	
	<c:forEach items="${list }" var="user">
	<tr>
		
		<td>${user.name }</td>
		<td>${user.age }</td>
		<td>${user.hobby }</td>
		
	</tr>
	</c:forEach>
</table>
<script>
	var result = '${msg}';
	if (result == 'success') {
		alert("게시물 업데이트 성공");
	}
</script>

