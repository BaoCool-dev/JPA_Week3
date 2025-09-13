<form action="${pageContext.request.contextPath}/admin-category/update"
	method="post" enctype="multipart/form-data">

	<input type="hidden" name="categoryId" value="${category.categoryId}" />

	<div class="mb-3">
		<label for="categoryCode">Category Code</label> <input type="text"
			id="categoryCode" name="categoryCode"
			value="${category.categoryCode}" class="form-control" required />
	</div>

	<div class="mb-3">
		<label for="categoryName">Category Name</label> <input type="text"
			id="categoryName" name="categoryName"
			value="${category.categoryName}" class="form-control" required />
	</div>

	<div class="mb-3">
		<label for="images">Image</label> <input type="file" id="images"
			name="images" class="form-control" />
		<c:if test="${not empty category.images}">
			<div class="mt-2">
				<img
					src="${pageContext.request.contextPath}/uploads/category/${category.images}"
					alt="Category Image" width="120" class="img-thumbnail" />
			</div>
		</c:if>
	</div>

	<div class="mb-3 form-check">
		<input type="checkbox" id="status" name="status" value="true"
			class="form-check-input"
			<c:if test="${category.status}">checked</c:if> /> <label
			for="status" class="form-check-label">Active</label>
	</div>

	<button type="submit" class="btn btn-success">Update</button>
	<a href="${pageContext.request.contextPath}/admin-category"
		class="btn btn-secondary">Back</a>
</form>
