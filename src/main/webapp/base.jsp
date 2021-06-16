<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="r" uri="http://www.rapid-framework.org.cn/rapid"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<link rel = "icon" href ="assets/img/icon.jpg" type ="image/x-icon">
<title>HA_group</title>



<!-- Core theme CSS (includes Bootstrap)-->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="assets/css/styles.css" rel="stylesheet" />
<link href="assets/icon/bootstrap-icons.css" rel="stylesheet" />
<link href='https://fonts.googleapis.com/css?family=Montserrat'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-grona">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="#!"><img alt=".." src="assets/img/haa-logo.png" width="140" id="logo-header"> </a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<r:block name="navbar">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="<% request.getContextPath(); %>/HA_group_shop">Accueil</a></li>
					<li class="nav-item"><a class="nav-link" href="#!">About</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#!">Categorie</a></li>
							<c:forEach items="${listC}" var="c">
							<li><a class="dropdown-item" href="Filter_Produit?id_c=${c.getCode_cat()}">${c.getNom_cat()}</a></li>
							</c:forEach>
						</ul></li>
					
				</ul>
				
				<div class="d-flex">
					<a class="btn btn-outline-light" type="submit" href="List_Achat">
						<i class="bi bi-basket-fill"></i> Panier <span
							class="badge bg-white text-dark ms-1 rounded-pill">${nbAchat } </span>
					</a>
				</div>
				</r:block>				
			</div>
		</div>
	</nav>
	<!-- Header-->
	<header class="bg-dark py-3">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-white">
				<r:block name="headerTxt">
					<h1 class="display-4 fw-bolder">Bienvenu chere client</h1>
					<p class="lead fw-normal text-white-50 mb-0">Vous aurez trouvé
						votre produits préférés chez nous</p>
				</r:block>
			</div>
		</div>
	</header>
	<!-- Section-->
	<section class="py-5">
		<r:block name="contenu"></r:block>
	</section>
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; HA GROUP 2021</p>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/js/jquery-3.6.0.min.js"></script>

	<!-- Core theme JS-->
	<script src="assets/js/scripts.js"></script>
</body>
</html>
