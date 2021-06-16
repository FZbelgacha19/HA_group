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
		<div class="d-flex justify-content-center">
			<form class="shadow-lg p-3 mb-5 bg-body rounded"
				action="Ajouter_admin" method="post">
				<h2 class="fw-bold">Ajouter Nouveau Adminstrateur</h2>
				<div class="form-group">
					<label for="nom_complete"></label>
					<div class="input-group">

						<span class="input-group-text"><i
							class="bi bi-person-badge"></i></span> <input id="nom_complete"
							name="nom_complete" type="text" required="required"
							class="form-control" placeholder="Nom complete">

					</div>
				</div>
				<div class="form-group">
					<label for="email"></label>
					<div class="input-group">
						<span class="input-group-text"><i class="bi bi-envelope"></i></span> <input id="email"
							name="email" type="email" class="form-control"
							required="required" placeholder="Email">
					</div>
				</div>
				<div class="form-group">
					<label for="motdpass"></label>
					<div class="input-group">

						<span class="input-group-text"><i class="bi bi-file-lock2"></i></span> <input
							id="motdpass" name="motdpass" type="password"
							class="form-control" required="required" placeholder="Mot de passe">

					</div>
				</div>
				<div class="form-group">
					<label for="adress"></label>
					<div class="input-group">

						<span class="input-group-text"><i class="bi bi-house"></i></span> <input id="adress"
							name="adress" type="text" class="form-control"
							required="required" placeholder="Address">
					</div>
				</div>
				<div class="form-group">
					<label for="tele"></label>
					<div class="input-group">

						<span class="input-group-text"><i class="bi bi-telephone"></i></span> <input id="tele"
							name="tele" type="tel" class="form-control" required="required" placeholder="Numero de telephone">

					</div>
				</div>
				<div class="form-group">
					<label for="dateNaissance"></label>
					<div class="input-group">

						<span class="input-group-text"><i class="bi bi-calendar3"></i></span> <input
							id="dateNaissance" name="dateNaissance" type="date"
							class="form-control" required="required" placeholder="Date de naissance">
					</div>
				</div>
				<div class="form-group d-flex justify-content-end">
					<button name="submit" type="submit"
						class="btn btn-outline-primary my-3 signin">Ajouter</button>
				</div>
			</form>
		</div>
	</r:override>
	<jsp:include page="/admin_views/accueil.jsp" />
</body>
</html>
