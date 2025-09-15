<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage User Roles</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-4">
		<h2 class="mb-3">User Roles</h2>

		<a href="${pageContext.request.contextPath}/userrole/add"
			class="btn btn-primary mb-3"> <i class="fa fa-plus"></i> Add Role
		</a>

		<table class="table table-bordered table-hover align-middle shadow-sm">
			<thead class="table-dark">
				<tr>
					<th>ID</th>
					<th>Role Name</th>
					<th>Description</th>
					<th style="width: 150px;">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="role" items="${userRoles}">
					<tr>
						<td>${role.roleID}</td>
						<td>${role.roleName}</td>
						<td>${role.description}</td>
						<td><a
							href="${pageContext.request.contextPath}/userrole/edit?id=${role.roleID}"
							class="btn btn-warning btn-sm"> <i class="fa fa-pen"></i>
						</a> <a
							href="${pageContext.request.contextPath}/userrole/delete?id=${role.roleID}"
							class="btn btn-danger btn-sm"
							onclick="return confirm('Are you sure you want to delete this role?');">
								<i class="fa fa-trash"></i>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
