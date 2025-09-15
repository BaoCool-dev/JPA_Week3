<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Category List</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: #f8f9fa;
}

.container {
	margin-top: 40px;
}

.portlet-title {
	font-size: 1.8rem;
	font-weight: bold;
	margin-bottom: 20px;
}

.table th, .table td {
	text-align: center;
	vertical-align: middle;
}

.img-thumb {
	width: 120px;
	border-radius: 8px;
	object-fit: cover;
}

.table-toolbar {
	margin-bottom: 20px;
	display: flex;
	justify-content: space-between;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="portlet-title">Category Management</h1>

		<!-- Toolbar -->
		<div class="table-toolbar">
			<div>
				<c:if
					test="${sessionScope.userLogin.roleID == 2 || sessionScope.userLogin.roleID == 3}">
					<a href="${pageContext.request.contextPath}/category/add"
						class="btn btn-success"> <i class="fas fa-plus"></i> Add New
					</a>
				</c:if>
			</div>
			<button class="btn btn-outline-secondary"
				onclick="location.reload();">
				<i class="fas fa-sync"></i> Refresh
			</button>
		</div>

		<!-- Table -->
		<div class="card shadow-sm">
			<div class="card-body p-0">
				<table class="table table-hover table-bordered m-0">
					<thead class="table-dark">
						<tr>
							<th>Category Code</th>
							<th>Category Name</th>
							<th>Image</th>
							<th>Status</th>
							<th style="width: 180px;">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="category" items="${categories}">
							<tr>
								<td>${category.categoryCode}</td>
								<td>${category.categoryName}</td>
								<td><img
									src="${pageContext.request.contextPath}/images/${category.images}"
									class="img-thumb" alt="Category Image"></td>
								<td><c:choose>
										<c:when test="${category.status}">
											<span class="badge bg-success">Active</span>
										</c:when>
										<c:otherwise>
											<span class="badge bg-secondary">Inactive</span>
										</c:otherwise>
									</c:choose></td>
								<td><c:if
										test="${sessionScope.userLogin.roleID == 2 || sessionScope.userLogin.roleID == 3}">
										<a
											href="${pageContext.request.contextPath}/category/edit?categoryId=${category.categoryId}"
											class="btn btn-warning btn-sm"> <i class="fas fa-edit"></i>
											Edit
										</a>
										<a
											href="${pageContext.request.contextPath}/category/delete?categoryId=${category.categoryId}"
											class="btn btn-danger btn-sm"
											onclick="return confirm('Are you sure you want to delete this category?');">
											<i class="fas fa-trash"></i> Delete
										</a>
									</c:if> <c:if test="${sessionScope.userLogin.roleID == 1}">
										<span class="badge bg-info">View Only</span>
									</c:if></td>
							</tr>
						</c:forEach>
						<c:if test="${empty categories}">
							<tr>
								<td colspan="5" class="text-center text-muted py-3"><i
									class="fas fa-folder-open"></i> No categories found.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
