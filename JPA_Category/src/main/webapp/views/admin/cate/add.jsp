<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Category</title>
<!-- Custom CSS for styling -->
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f7fc;
	margin: 0;
	padding: 0;
}

.container {
	width: 50%;
	margin: 50px auto;
	padding: 30px;
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	color: #4CAF50;
}

form {
	display: flex;
	flex-direction: column;
}

label {
	margin: 10px 0 5px;
	font-weight: bold;
}

input[type="text"], input[type="file"], input[type="checkbox"] {
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ddd;
	border-radius: 4px;
	font-size: 16px;
}

input[type="checkbox"] {
	width: auto;
	margin-right: 10px;
}

button {
	padding: 12px 20px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
}

button:hover {
	background-color: #45a049;
}

.back-link {
	display: block;
	text-align: center;
	margin-top: 20px;
	font-size: 16px;
}

.back-link a {
	text-decoration: none;
	color: #4CAF50;
	font-weight: bold;
}

.back-link a:hover {
	color: #388e3c;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Add New Category</h1>
		<form
			action="${pageContext.request.contextPath}/admin-category/create"
			method="post" enctype="multipart/form-data">
			<label for="categoryCode">Category Code:</label> <input type="text"
				id="categoryCode" name="categoryCode" required /> <label
				for="categoryName">Category Name:</label> <input type="text"
				id="categoryName" name="categoryName" required /> <label
				for="images">Image:</label> <input type="file" id="images"
				name="images" /> <label for="status">Status:</label> <input
				type="checkbox" id="status" name="status" value="true" checked />
			Active

			<button type="submit">Add Category</button>
		</form>


		<div class="back-link">
			<a href="${pageContext.request.contextPath}/admin-category">Back</a>
		</div>
	</div>
</body>
</html>
