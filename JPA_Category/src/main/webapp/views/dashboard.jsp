<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.table th, .table td {
	text-align: center;
	vertical-align: middle;
}

.img-thumb {
	width: 100px;
	border-radius: 6px;
}
</style>
</head>
<body class="bg-light">
	<div class="container mt-4">
		<h2 class="mb-3">Welcome, ${sessionScope.userLogin.fullName}!</h2>
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h4>Your Categories</h4>
			<div>
				<c:if
					test="${sessionScope.userLogin.roleID == 2 || sessionScope.userLogin.roleID == 3}">
					<a href="${pageContext.request.contextPath}/category/create"
						class="btn btn-success"> <i class="fa fa-plus"></i> Add New
					</a>
				</c:if>
				<button class="btn btn-info" onclick="location.reload()">
					<i class="fa fa-sync"></i> Refresh
				</button>
			</div>
		</div>

		<table class="table table-bordered table-hover">
			<thead class="table-dark">
				<tr>
					<th>Code</th>
					<th>Name</th>
					<th>Image</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="category" items="${categories}">
					<tr>
						<td>${category.categoryCode}</td>
						<td>${category.categoryName}</td>
						<td><img
							src="${pageContext.request.contextPath}/images/${category.images}"
							class="img-thumb"></td>
						<td><span
							class="badge ${category.status ? 'bg-success':'bg-secondary'}">
								${category.status ? "Active" : "Inactive"} </span></td>
						<td><c:choose>
								<c:when test="${sessionScope.userLogin.roleID == 1}">
									<span class="badge bg-secondary">View Only</span>
								</c:when>
								<c:otherwise>
									<a
										href="${pageContext.request.contextPath}/category/edit?categoryId=${category.categoryId}"
										class="btn btn-warning btn-sm"> <i class="fa fa-edit"></i>
									</a>
									<a
										href="${pageContext.request.contextPath}/category/delete?categoryId=${category.categoryId}"
										class="btn btn-danger btn-sm"
										onclick="return confirm('Delete this category?');"> <i
										class="fa fa-trash"></i>
									</a>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
