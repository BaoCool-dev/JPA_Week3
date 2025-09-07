<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Category</title>
</head>
<body>
	<h1>Edit Category</h1>

	<form action="${pageContext.request.contextPath}/admin-category/update"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="categoryId" value="${category.categoryId}" />

		<label for="categorycode">Category Code:</label> <input type="text"
			id="categorycode" name="categorycode"
			value="${category.categorycode}" required /><br>
		<br> <label for="categoryname">Category Name:</label> <input
			type="text" id="categoryname" name="categoryname"
			value="${category.categoryname}" required /><br>
		<br> <label for="images">Image:</label> <input type="file"
			id="images" name="images" /><br>
		<br> <label for="status">Status:</label> <input type="checkbox"
			id="status" name="status"
			<c:if test="${category.status}">checked</c:if> /><br>
		<br>

		<button type="submit">Update</button>
	</form>

	<br>
	<a href="${pageContext.request.contextPath}/admin-category">Back</a>
</body>
</html>
