<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="r" uri="http://www.rapid-framework.org.cn/rapid"%>
<!DOCTYPE html>
<html lang="en">
<head>


</head>
<body>
	<r:override name="navbar">
		<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
			<li class="nav-item"><a class="nav-link active"
				aria-current="page" href="Accueil">Accueil</a></li>

			<c:if test="${ !empty admin }">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">Administateur</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="Ajouter_admin">Nouveau
								admin</a></li>
						<li><a class="dropdown-item" href="List_admin">Liste
								admin</a></li>
					</ul></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">Categorie</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="Ajouter_Categorie">Nouveau
								Categorie</a></li>
						<li><a class="dropdown-item" href="List_Categorie">Liste
								Categories</a></li>
					</ul></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">Marque</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="Ajouter_Marque">Nouveau
								marque</a></li>
						<li><a class="dropdown-item" href="List_Marque">Liste
								marques</a></li>
					</ul></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">Produit</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="Ajouter_Produit">Nouveau
								produit</a></li>
						<li><a class="dropdown-item" href="List_Produit">Liste
								produits</a></li>
					</ul></li>
					<li class="nav-item"><a class="nav-link active"
				aria-current="page" href="Valide_Facture">Valide Facture</a></li>
			</c:if>
		</ul>
		<c:if test="${ !empty admin }">
			<div class="d-flex">
				<p class="fw-bold m-2">${admin.getNom_complete()}</p>
				<a class="nav-link" href="Deconnecter">Deconnecter</a>
			</div>
		</c:if>
	</r:override>
	<r:override name="headerTxt">
		<h1 class="display-4 fw-bolder">Bienvenu chere Admins</h1>
		<p class="lead fw-normal text-white-50 mb-0">On veux vous remercie
			pour vos efforts</p>
	</r:override>
	<!-- Section-->
	<r:override name="contenu">
		<r:block name="adminContainer">
			<c:choose>
				<c:when test="${ empty admin }">
					<div class="d-flex justify-content-center">
						<form class="shadow-lg p-3 mb-5 bg-body rounded" action="Accueil"
							method="post">
							<h2 class="fw-bold">Se connecter</h2>

							<div class="form-group">
								<label for="email"></label>
								<div class="input-group">
									<span class="input-group-text"><i class="bi bi-envelope"></i></span>
									<input id="email" name="email" type="email"
										class="form-control" required="required" placeholder="Email">
								</div>
							</div>
							<div class="form-group">
								<label for="motdpass"></label>
								<div class="input-group">

									<span class="input-group-text"><i
										class="bi bi-file-lock2"></i></span> <input id="motdpass"
										name="motdpass" type="password" class="form-control"
										required="required" placeholder="Mot de passe">

								</div>
							</div>
							<button class="signin">connecter</button>

						</form>

					</div>
				</c:when>
				<c:when test="${ !empty admin }">
		<div class="container" style="height: 180px;">
		hey admin ${admin.getNom_complete()}
		</div>
		
		</c:when>
			</c:choose>
		</r:block>
	</r:override>
	<jsp:include page="/base.jsp" />
</body>
</html>
