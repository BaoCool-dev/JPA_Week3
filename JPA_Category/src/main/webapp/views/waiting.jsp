<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Please Wait</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: #f8f9fa;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.spinner-border {
	width: 4rem;
	height: 4rem;
}
</style>
</head>
<body>
	<div class="text-center">
		<div class="spinner-border text-primary mb-3" role="status">
			<span class="visually-hidden">Loading...</span>
		</div>
		<h4 class="mb-2">Please wait...</h4>
		<p class="text-muted">${requestScope.waitMessage != null ? requestScope.waitMessage : "Processing your request."}</p>
	</div>
</body>
</html>
