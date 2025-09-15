<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit User Role</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-4">
		<h3 class="mb-3">Edit Role</h3>
		<form action="${pageContext.request.contextPath}/userrole/update"
			method="post">
			<input type="hidden" name="roleID" value="${role.roleID}">
			<div class="mb-3">
				<label class="form-label">Role Name</label> <input type="text"
					name="roleName" value="${role.roleName}" class="form-control"
					required>
			</div>
			<div class="mb-3">
				<label class="form-label">Description</label>
				<textarea name="description" class="form-control" rows="3">${role.description}</textarea>
			</div>
			<button type="submit" class="btn btn-warning">
				<i class="fa fa-save"></i> Update
			</button>
			<a href="${pageContext.request.contextPath}/userrole/list"
				class="btn btn-secondary">Cancel</a>
		</form>
	</div>
</body>
</html>
