<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Category</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<h3 class="mb-4">Add New Category</h3>
		<form action="${pageContext.request.contextPath}/category/create"
			method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label class="form-label">Category Code</label> <input type="text"
					name="categoryCode" class="form-control" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Category Name</label> <input type="text"
					name="categoryName" class="form-control" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Image</label> <input type="file"
					name="image" class="form-control" accept="image/*">
			</div>
			<div class="mb-3">
				<label class="form-label">Status</label> <select name="status"
					class="form-select">
					<option value="true" selected>Active</option>
					<option value="false">Inactive</option>
				</select>
			</div>
			<button type="submit" class="btn btn-success">
				<i class="fa fa-save"></i> Save
			</button>
			<a href="${pageContext.request.contextPath}/category/list"
				class="btn btn-secondary">Cancel</a>
		</form>
	</div>
</body>
</html>
