<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Verify Code</title>
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
		<form action="${pageContext.request.contextPath}/verify" method="post">
			<h2 class="text-center mb-4">Verify Your Code</h2>

			<c:if test="${error != null}">
				<div class="alert alert-danger text-center">${error}</div>
			</c:if>
			<c:if test="${message != null}">
				<div class="alert alert-success text-center">${message}</div>
			</c:if>

			<div class="mb-3">
				<label class="form-label">Enter Verification Code</label>
				<div class="input-group">
					<span class="input-group-text"><i class="fa fa-key"></i></span> <input
						type="text" name="verifyCode" placeholder="6-digit code"
						class="form-control" required>
				</div>
			</div>

			<div class="button-group">
				<button type="submit" class="btn btn-primary">Verify</button>
				<a href="${pageContext.request.contextPath}/forgotpassword"
					class="btn btn-secondary">Back</a>
			</div>
		</form>
	</div>
</body>
</html>
