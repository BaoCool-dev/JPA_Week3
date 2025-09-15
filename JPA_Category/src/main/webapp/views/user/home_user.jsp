<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-4">
		<h2>Welcome, ${sessionScope.userLogin.fullName}</h2>
		<p class="text-muted">You can view categories here.</p>
		<jsp:include page="../category/list.jsp" />
	</div>
</body>
</html>
