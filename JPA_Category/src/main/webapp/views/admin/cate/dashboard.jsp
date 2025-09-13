<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard - Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet">
<style>
.dashboard-card {
	transition: transform 0.2s;
	border: none;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.dashboard-card:hover {
	transform: translateY(-5px);
}

.stat-icon {
	font-size: 3rem;
}

.sidebar {
	min-height: 100vh;
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.sidebar .nav-link {
	color: white !important;
	padding: 15px 20px;
	border-radius: 10px;
	margin: 5px 0;
	transition: all 0.3s;
}

.sidebar .nav-link:hover {
	background-color: rgba(255, 255, 255, 0.2);
	transform: translateX(5px);
}

.sidebar .nav-link.active {
	background-color: rgba(255, 255, 255, 0.3);
}

.main-content {
	background-color: #f8f9fa;
	min-height: 100vh;
}

.header-section {
	background: white;
	padding: 20px;
	margin-bottom: 30px;
	border-radius: 10px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<!-- Sidebar -->
			<div class="col-md-3 col-lg-2 px-0 sidebar">
				<div class="p-3">
					<h4 class="text-white text-center mb-4">
						<i class="fas fa-tachometer-alt"></i> AdminPanel
					</h4>
					<nav class="nav flex-column">
						<a class="nav-link active"
							href="${pageContext.request.contextPath}/dashboard"> <i
							class="fas fa-home me-2"></i> Dash Board
						</a> <a class="nav-link"
							href="${pageContext.request.contextPath}/admin/category/list">
							<i class="fas fa-list me-2"></i> Manage Categories
						</a> <a class="nav-link"
							href="${pageContext.request.contextPath}/admin/user/list"> <i
							class="fas fa-users me-2"></i> Manage Users
						</a> <a class="nav-link"
							href="${pageContext.request.contextPath}/admin/product/list">
							<i class="fas fa-box me-2"></i> Manage Products
						</a>
						<hr class="text-white">
						<a class="nav-link"
							href="${pageContext.request.contextPath}/logout"> <i
							class="fas fa-sign-out-alt me-2"></i> Log Out
						</a>
					</nav>
				</div>
			</div>

			<!-- Main Content -->
			<div class="col-md-9 col-lg-10 main-content">
				<!-- Header -->
				<div class="header-section">
					<div class="row align-items-center">
						<div class="col">
							<h2 class="mb-0">Dash Board</h2>
							<p class="text-muted mb-0">Welcome back, ${user.fullName}!</p>
						</div>
						<div class="col-auto">
							<div class="d-flex align-items-center">
								<img src="https://via.placeholder.com/40" alt="Avatar"
									class="rounded-circle me-2">
								<div>
									<strong>${user.fullName}</strong> <small
										class="d-block text-muted">${user.email}</small>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Statistics Cards -->
				<div class="row mb-4">
					<div class="col-xl-3 col-md-6 mb-4">
						<div class="card dashboard-card h-100">
							<div class="card-body text-center">
								<div class="stat-icon text-primary mb-3">
									<i class="fas fa-list"></i>
								</div>
								<h5 class="card-title">Categories</h5>
								<h2 class="text-primary">${totalCategories}</h2>
								<p class="text-muted">Total categories</p>
							</div>
						</div>
					</div>

					<div class="col-xl-3 col-md-6 mb-4">
						<div class="card dashboard-card h-100">
							<div class="card-body text-center">
								<div class="stat-icon text-success mb-3">
									<i class="fas fa-users"></i>
								</div>
								<h5 class="card-title">Users</h5>
								<h2 class="text-success">${totalUsers}</h2>
								<p class="text-muted">Total users</p>
							</div>
						</div>
					</div>

					<div class="col-xl-3 col-md-6 mb-4">
						<div class="card dashboard-card h-100">
							<div class="card-body text-center">
								<div class="stat-icon text-warning mb-3">
									<i class="fas fa-box"></i>
								</div>
								<h5 class="card-title">Products</h5>
								<h2 class="text-warning">${totalProducts}</h2>
								<p class="text-muted">Total products</p>
							</div>
						</div>
					</div>

					<div class="col-xl-3 col-md-6 mb-4">
						<div class="card dashboard-card h-100">
							<div class="card-body text-center">
								<div class="stat-icon text-info mb-3">
									<i class="fas fa-chart-line"></i>
								</div>
								<h5 class="card-title">Revenue</h5>
								<h2 class="text-info">$12,340</h2>
								<p class="text-muted">This month's revenue</p>
							</div>
						</div>
					</div>
				</div>

				<!-- Quick Actions -->
				<div class="row mb-4">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h5 class="card-title mb-0">
									<i class="fas fa-bolt"></i> Quick Actions
								</h5>
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col-md-3 mb-3">
										<a
											href="${pageContext.request.contextPath}/admin/category/add"
											class="btn btn-outline-primary btn-lg w-100"> <i
											class="fas fa-plus-circle"></i><br> Add Category
										</a>
									</div>
									<div class="col-md-3 mb-3">
										<a href="${pageContext.request.contextPath}/admin/user/add"
											class="btn btn-outline-success btn-lg w-100"> <i
											class="fas fa-user-plus"></i><br> Add User
										</a>
									</div>
									<div class="col-md-3 mb-3">
										<a href="${pageContext.request.contextPath}/admin/product/add"
											class="btn btn-outline-warning btn-lg w-100"> <i
											class="fas fa-box"></i><br> Add Product
										</a>
									</div>
									<div class="col-md-3 mb-3">
										<a href="${pageContext.request.contextPath}/admin/report"
											class="btn btn-outline-info btn-lg w-100"> <i
											class="fas fa-chart-bar"></i><br> View Reports
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Recent Activities -->
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h5 class="card-title mb-0">
									<i class="fas fa-history"></i> Recent Activities
								</h5>
							</div>
							<div class="card-body">
								<div class="timeline">
									<div class="d-flex mb-3">
										<div class="flex-shrink-0">
											<span class="badge bg-primary rounded-pill"> <i
												class="fas fa-user"></i>
											</span>
										</div>
										<div class="flex-grow-1 ms-3">
											<h6 class="mb-0">New user registered</h6>
											<small class="text-muted">2 minutes ago</small>
										</div>
									</div>
									<div class="d-flex mb-3">
										<div class="flex-shrink-0">
											<span class="badge bg-success rounded-pill"> <i
												class="fas fa-plus"></i>
											</span>
										</div>
										<div class="flex-grow-1 ms-3">
											<h6 class="mb-0">New category added</h6>
											<small class="text-muted">15 minutes ago</small>
										</div>
									</div>
									<div class="d-flex mb-3">
										<div class="flex-shrink-0">
											<span class="badge bg-warning rounded-pill"> <i
												class="fas fa-edit"></i>
											</span>
										</div>
										<div class="flex-grow-1 ms-3">
											<h6 class="mb-0">Updated product information</h6>
											<small class="text-muted">1 hour ago</small>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
        // Number counting animation
        function animateNumbers() {
            const numbers = document.querySelectorAll('.card-body h2');
            numbers.forEach(number => {
                const finalNumber = parseInt(number.textContent.replace(/[^0-9]/g, ''));
                let currentNumber = 0;
                const increment = finalNumber / 30;
                
                const timer = setInterval(() => {
                    currentNumber += increment;
                    if (currentNumber >= finalNumber) {
                        number.textContent = finalNumber.toLocaleString();
                        clearInterval(timer);
                    } else {
                        number.textContent = Math.floor(currentNumber).toLocaleString();
                    }
                }, 50);
            });
        }
        
        // Run animation when the page loads
        document.addEventListener('DOMContentLoaded', animateNumbers);
    </script>
</body>
</html>
