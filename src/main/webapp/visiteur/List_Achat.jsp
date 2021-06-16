<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="r" uri="http://www.rapid-framework.org.cn/rapid"%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
	<!-- Section-->
	<r:override name="contenu">

		<div class="container px-4 px-lg-5 mt-5">
		<div class="container m-2 d-flex justify-content-end">
		<a href="valideCommande" class="btn btn-outline-dark">Valide les commandes</a>
		</div>
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach items="${listAchat}" var="la">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top" src="Produit/img/${la.value.getImage()}"
								alt="..." />
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">${la.value.getNom()}</h5>
									<!-- Product price-->
									${la.value.getPrix()} DH
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto"
										href="Supp_panier?id=${la.key}"><i
										class="bi bi-cart-dash"></i></a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>
			
		</div>
	</r:override>
	<jsp:include page="/base.jsp" />
</body>
</html>
