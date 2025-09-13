<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<style>
body {
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	background: #f8f9fa;
}

.form-box {
	width: 100%;
	max-width: 400px;
	padding: 20px;
	background: white;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.button-group {
	display: flex;
	justify-content: center;
}

.button-group .btn {
	margin: 0 10px;
}
</style>
</head>
<body>
	<div class="form-box">
		<form action="${pageContext.request.contextPath}/login" method="post">
			<h2 class="text-center mb-4">Login</h2>

			<c:if test="${error != null}">
				<div class="alert alert-danger text-center">${error}</div>
			</c:if>
			<c:if test="${message != null}">
				<div class="alert alert-success text-center">${message}</div>
			</c:if>

			<div class="mb-3">
				<label class="form-label">User name</label>
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-user"></i></span> <input
						type="text" placeholder="Enter username" name="username"
						class="form-control" required>
				</div>
			</div>

			<div class="mb-3">
				<label class="form-label">Password</label>
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-lock"></i></span> <input
						type="password" placeholder="Enter password" name="password"
						class="form-control" required>
				</div>
			</div>

			<div class="form-check mb-3">
				<input class="form-check-input" type="checkbox" id="rememberMe"
					name="remember"> <label
					class="form-check-label text-muted small fw-light" for="rememberMe">
					Remember me </label>
			</div>

			<div class="button-group">
				<button type="submit" class="btn btn-primary">Login</button>
				<a href="${pageContext.request.contextPath}/register"
					class="btn btn-secondary">Register</a>
			</div>
		</form>
	</div>
</body>
</html>
