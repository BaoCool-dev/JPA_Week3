<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: #f8f9fa;
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 100vh;
}

.card {
	max-width: 400px;
	width: 100%;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
	<div class="card p-4">
		<h3 class="text-center mb-3">Login</h3>

		<c:if test="${error != null}">
			<div class="alert alert-danger">${error}</div>
		</c:if>
		<c:if test="${message != null}">
			<div class="alert alert-success">${message}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="mb-3">
				<label class="form-label">User Name</label>
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-user"></i></span> <input
						type="text" class="form-control" name="username" required>
				</div>
			</div>
			<div class="mb-3">
				<label class="form-label">Password</label>
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-lock"></i></span> <input
						type="password" class="form-control" name="password" required>
				</div>
			</div>
			<div class="form-check mb-3">
				<input class="form-check-input" type="checkbox" name="remember"
					id="remember"> <label class="form-check-label"
					for="remember">Remember me</label>
			</div>
			<button type="submit" class="btn btn-primary w-100">
				<i class="fa fa-sign-in-alt"></i> Login
			</button>
			<div class="text-center mt-3">
				<a href="${pageContext.request.contextPath}/register"
					class="text-decoration-none">Create an account</a>
			</div>
		</form>
	</div>
</body>
</html>
