<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Forgot Password</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div
		class="container d-flex align-items-center justify-content-center min-vh-100">
		<div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
			<h4 class="text-center mb-3">Reset Your Password</h4>

			<!-- Message Display -->
			<c:if test="${not empty message}">
				<div class="alert alert-success text-center">${message}</div>
			</c:if>
			<c:if test="${not empty error}">
				<div class="alert alert-danger text-center">${error}</div>
			</c:if>

			<!-- Email/Username Input Form -->
			<form action="${pageContext.request.contextPath}/forgot-password"
				method="post">
				<div class="mb-3">
					<label for="email" class="form-label">Email or User Name</label> <input
						type="text" class="form-control" id="email" name="emailOrUsername"
						placeholder="Enter your email or username" required>
				</div>

				<button type="submit" class="btn btn-primary w-100">Send
					Reset Link</button>
			</form>

			<div class="text-center mt-3">
				<a href="${pageContext.request.contextPath}/login"
					class="text-decoration-none">Back to Login</a>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
