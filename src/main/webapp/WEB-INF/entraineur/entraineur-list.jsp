<%--
  Created by IntelliJ IDEA.
  User: najat
  Date: 3/12/2025
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Entraineurs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container { margin-top: 50px; }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center">Liste des Entraineurs</h2>
    <a href="${pageContext.request.contextPath}/entraineur?action=ajouter" class="btn btn-success mb-3">Ajouter Entraineur</a>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Spécialité</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entraineur" items="${entraineurs}">
            <tr>
                <td>${entraineur.id}</td>
                <td>${entraineur.nom}</td>
                <td>${entraineur.specialite}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/entraineur?action=modifier&id=${entraineur.id}" class="btn btn-primary btn-sm">Modifier</a>
                    <a href="${pageContext.request.contextPath}/entraineur?action=supprimer&id=${entraineur.id}" class="btn btn-danger btn-sm" onclick="return confirm('Voulez-vous vraiment supprimer cet entraineur ?');">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
