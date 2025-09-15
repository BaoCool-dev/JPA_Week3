<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body
	class="d-flex justify-content-center align-items-center bg-light vh-100">
	<div class="card p-4" style="max-width: 450px; width: 100%">
		<h3 class="text-center mb-3">Register</h3>
		<c:if test="${alert != null}">
			<div class="alert alert-danger">${alert}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/register"
			method="post">
			<div class="mb-3">
				<label class="form-label">User Name</label> <input type="text"
					name="userName" class="form-control" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Password</label> <input type="password"
					name="password" class="form-control" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Email</label> <input type="email"
					name="email" class="form-control" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Full Name</label> <input type="text"
					name="fullName" class="form-control" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Phone</label> <input type="text"
					name="phone" class="form-control" required>
			</div>
			<button type="submit" class="btn btn-success w-100">
				<i class="fa fa-user-plus"></i> Register
			</button>
			<div class="text-center mt-3">
				<a href="${pageContext.request.contextPath}/login"
					class="text-decoration-none">Back to login</a>
			</div>
		</form>
	</div>
</body>
</html>
