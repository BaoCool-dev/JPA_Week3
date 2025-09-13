<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Category</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>
<body class="bg-light">

	<div class="container mt-5">
		<h2 class="mb-4">Edit Category</h2>

		<div class="card shadow p-4">
			<form
				action="${pageContext.request.contextPath}/admin-category/update"
				method="post" enctype="multipart/form-data">

				<input type="hidden" name="categoryId"
					value="${category.categoryId}" />

				<div class="mb-3">
					<label for="categoryCode" class="form-label fw-bold">Category
						Code</label> <input type="text" id="categoryCode" name="categoryCode"
						value="${category.categoryCode}" class="form-control" required />
				</div>

				<div class="mb-3">
					<label for="categoryName" class="form-label fw-bold">Category
						Name</label> <input type="text" id="categoryName" name="categoryName"
						value="${category.categoryName}" class="form-control" required />
				</div>

				<div class="mb-3">
					<label for="images" class="form-label fw-bold">Image</label> <input
						type="file" id="images" name="images" class="form-control" />
					<c:if test="${not empty category.images}">
						<div class="mt-2">
							<<img
								src="${pageContext.request.contextPath}/images/${category.images}"
								alt="Category Image" width="120" class="img-thumbnail">

						</div>
					</c:if>

				</div>

				<div class="mb-3 form-check">
					<input type="checkbox" id="status" name="status" value="true"
						class="form-check-input"
						<c:if test="${category.status}">checked</c:if> /> <label
						class="form-check-label fw-bold" for="status">Active</label>
				</div>

				<div class="d-flex gap-2">
					<button type="submit" class="btn btn-success">
						<i class="bi bi-check-circle"></i> Update
					</button>
					<a href="${pageContext.request.contextPath}/admin-category"
						class="btn btn-secondary"> <i class="bi bi-arrow-left"></i>
						Back
					</a>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
