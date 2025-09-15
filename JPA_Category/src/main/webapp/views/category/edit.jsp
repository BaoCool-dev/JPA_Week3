<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Category</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<h3 class="mb-4">Edit Category</h3>
		<form action="${pageContext.request.contextPath}/category/update"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="categoryId" value="${category.categoryId}">
			<div class="mb-3">
				<label class="form-label">Category Code</label> <input type="text"
					name="categoryCode" value="${category.categoryCode}"
					class="form-control" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Category Name</label> <input type="text"
					name="categoryName" value="${category.categoryName}"
					class="form-control" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Image</label> <input type="file"
					name="image" class="form-control" accept="image/*">
				<c:if test="${category.images != null}">
					<img
						src="${pageContext.request.contextPath}/images/${category.images}"
						class="mt-2" style="width: 120px;">
				</c:if>
			</div>
			<div class="mb-3">
				<label class="form-label">Status</label> <select name="status"
					class="form-select">
					<option value="true" ${category.status ? 'selected':''}>Active</option>
					<option value="false" ${!category.status ? 'selected':''}>Inactive</option>
				</select>
			</div>
			<button type="submit" class="btn btn-warning">
				<i class="fa fa-save"></i> Update
			</button>
			<a href="${pageContext.request.contextPath}/category/list"
				class="btn btn-secondary">Cancel</a>
		</form>
	</div>
</body>
</html>
