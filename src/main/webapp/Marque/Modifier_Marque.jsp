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
	<r:override name="adminContainer">
		<div class="d-flex justify-content-center p-3">
			<form class="shadow-lg p-3 mb-5 bg-body rounded"
				action="Modifier_Marque?id=${m.getId_marque()}" method="post">
				<h2 class="fw-bold">Modifier Marque</h2>
				<div class="form-group">
					<div class="input-group">

						<span class="input-group-text"><i class="bi bi-grid"></i></span> <input id="Nom_Marq"
							name="Nom_Marq" type="text" required="required"
							class="form-control" placeholder="Nom du marque" value="${m.getLib_marque()}">

					</div>
				</div>
				<div class="form-group d-flex justify-content-end">
					<button name="submit" type="submit"
						class="btn btn-outline-primary my-3 signin">Modifier</button>
				</div>
			</form>
		</div>
	</r:override>
	<jsp:include page="/admin_views/accueil.jsp" />
</body>
</html>
