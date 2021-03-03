<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<c:if test="${not empty NEWMODEL }">
			<div>${NEWMODEL.content}</div>
		</c:if>
	</div>
</body>
</html>