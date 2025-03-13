<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Coach Dashboard - SportFlow</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 20px; }
    table { border-collapse: collapse; width: 80%; }
    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
    th { background-color: #f2f2f2; }
  </style>
</head>
<body>
<h2>Welcome, ${entraineur.username}</h2>
<a href="logout">Logout</a>

<h3>Your Profile</h3>
<p>ID: ${entraineur.id}</p>
<p>Username: ${entraineur.username}</p>
<p>Name: ${entraineur.name}</p>
<p>Specialty: ${entraineur.specialite}</p>

<h3>Your Sessions</h3>
<c:choose>
  <c:when test="${empty seances}">
    <p>No sessions scheduled.</p>
  </c:when>
  <c:otherwise>
    <table>
      <tr>
        <th>Session ID</th>
        <th>Member ID</th>
        <th>Date/Time</th>
      </tr>
      <c:forEach var="seance" items="${seances}">
        <tr>
          <td>${seance.id}</td>
          <td>${seance.idmembre}</td>
          <td>${seance.dateetheure}</td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>
</body>
</html>