<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
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

.error-box {
	text-align: center;
	padding: 30px;
	background: white;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	max-width: 400px;
}
</style>
</head>
<body>
	<div class="error-box">
		<i class="fa-solid fa-triangle-exclamation fa-3x text-danger mb-3"></i>
		<h3 class="mb-3">Oops! Something went wrong</h3>
		<p class="text-muted">${requestScope.errorMessage != null ? requestScope.errorMessage : "An unexpected error occurred."}</p>
		<a href="${pageContext.request.contextPath}/home"
			class="btn btn-primary mt-3"> <i class="fa fa-home"></i> Go Home
		</a>
	</div>
</body>
</html>
