<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Category List</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
<style>
.portlet-title {
	font-size: 1.5em;
	margin-bottom: 20px;
}

.table-toolbar {
	margin: 20px 0;
}

.portlet-body {
	margin-top: 20px;
}

.table th, .table td {
	text-align: center;
	vertical-align: middle;
}

.img-thumb {
	width: 120px;
	border-radius: 6px;
}
</style>
</head>
<body>
	<div class="container mt-5">
		<h1 class="portlet-title">Category Management</h1>

		<!-- Toolbar -->
		<div class="table-toolbar">
			<c:if
				test="${sessionScope.userLogin.roleID == 2 || sessionScope.userLogin.roleID == 3}">
				<a href="${pageContext.request.contextPath}/category/create"
					class="btn btn-success"> <i class="fas fa-plus"></i> Add New
				</a>
			</c:if>
			<button class="btn btn-info" onclick="location.reload();">
				<i class="fas fa-sync"></i> Refresh
			</button>
		</div>

		<!-- Table -->
		<div class="portlet-body">
			<table class="table table-bordered table-hover">
				<thead class="table-dark">
					<tr>
						<th>Category Code</th>
						<th>Category Name</th>
						<th>Image</th>
						<th>Status</th>
						<th>Actions</th>
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
									<c:when test="${category.status}">Active</c:when>
									<c:otherwise>Inactive</c:otherwise>
								</c:choose></td>
							<td>
								<!-- Only Manager (2) & Admin (3) can edit/delete --> <c:if
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
								</c:if> <!-- User chỉ được xem --> <c:if
									test="${sessionScope.userLogin.roleID == 1}">
									<span class="badge bg-secondary">View Only</span>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
