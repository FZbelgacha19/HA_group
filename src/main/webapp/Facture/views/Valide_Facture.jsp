<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="r" uri="http://www.rapid-framework.org.cn/rapid"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
	<!-- Section-->
	<r:override name="adminContainer">
		<div class="row d-flex justify-content-center container mx-5">
			<form class="col shadow-lg p-3 mb-5 bg-body rounded"
				action="Recherche_Facture" method="post">
				<h2 class="fw-bold">Rechercher Facture</h2>
				<div class="form-group">
					<div class="input-group">

						<span class="input-group-text"><i class="bi bi-grid"></i></span> <input
							id="num_facture" name="num_facture" type="search"
							required="required" class="form-control"
							placeholder="Numero de facture">

					</div>
				</div>
				<div class="form-group d-flex justify-content-end">
					<button name="submit" type="submit"
						class="btn btn-outline-primary my-3 signin">Rechercher</button>
				</div>
			</form>
			<div class="col-1">
			</div>
			<div class="col shadow-lg p-3 md-5 mb-5 bg-body rounded">
			<h2 class="fw-bold">Valider Facture</h2>
			<c:if test="${ !empty fact }">

				<form class="row "
					action="Valide_Facture?id_f=${fact.getId_facture()}" method="post">
					<div class="form-group">
						<div class="input-group">

							<label class="col">Facture N° : ${fact.getId_facture()}</label>
							
							<c:choose>
								<c:when test="${fn:contains(fact.getEtat_fact(), 'no_paye')}">
									<label class="col" style="color: red;">${fact.getEtat_fact()}</label> <br>
									<div class="row form-group d-flex justify-content-end ">
										<button name="submit" type="submit"
											class="btn btn-outline-primary my-3 signin">Valide</button>
									</div>
								</c:when>
								<c:when test="${fn:contains(fact.getEtat_fact(), 'paye')}">
									<label class="col" style="color: green;">${fact.getEtat_fact()}</label>
								</c:when>
							</c:choose>
						</div>
					</div>

				</form>

			</c:if>
			</div>
		</div>
	</r:override>
	<jsp:include page="/admin_views/accueil.jsp" />
</body>
</html>
