<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-4">
		<h3 class="mb-4">Your Profile</h3>
		<div class="card p-3 shadow-sm">
			<p>
				<strong>User Name:</strong> ${sessionScope.userLogin.userName}
			</p>
			<p>
				<strong>Full Name:</strong> ${sessionScope.userLogin.fullName}
			</p>
			<p>
				<strong>Email:</strong> ${sessionScope.userLogin.email}
			</p>
			<p>
				<strong>Phone:</strong> ${sessionScope.userLogin.phone}
			</p>
			<p>
				<strong>Role:</strong>
				<c:choose>
					<c:when test="${sessionScope.userLogin.roleID == 1}">User</c:when>
					<c:when test="${sessionScope.userLogin.roleID == 2}">Manager</c:when>
					<c:when test="${sessionScope.userLogin.roleID == 3}">Admin</c:when>
				</c:choose>
			</p>
			<a href="${pageContext.request.contextPath}/home"
				class="btn btn-primary">Back to Home</a>
		</div>
	</div>
</body>
</html>
