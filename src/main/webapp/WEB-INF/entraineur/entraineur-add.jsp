<%--
  Created by IntelliJ IDEA.
  User: najat
  Date: 3/12/2025
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Ajouter un Entraineur</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .form-container { max-width: 500px; margin: 50px auto; }
  </style>
</head>
<body>
<div class="form-container">
  <h2 class="text-center">Ajouter un Nouvel Entraineur</h2>
  <form action="${pageContext.request.contextPath}/entraineur" method="post">
    <input type="hidden" name="action" value="ajouter">
    <div class="mb-3">
      <label for="id" class="form-label">ID :</label>
      <input type="number" class="form-control" id="id" name="id" required>
    </div>
    <div class="mb-3">
      <label for="nom" class="form-label">Nom :</label>
      <input type="text" class="form-control" id="nom" name="nom" required>
    </div>
    <div class="mb-3">
      <label for="specialite" class="form-label">Spécialité :</label>
      <input type="text" class="form-control" id="specialite" name="specialite" required>
    </div>
    <button type="submit" class="btn btn-success w-100">Ajouter</button>
    <a href="${pageContext.request.contextPath}/entraineur" class="btn btn-secondary w-100 mt-2">Annuler</a>
  </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
