<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Member Dashboard - SportFlow</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 20px; }
    table { border-collapse: collapse; width: 80%; }
    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
    th { background-color: #f2f2f2; }
  </style>
</head>
<body>
<h2>Welcome, ${membre.username}</h2>
<a href="logout">Logout</a>

<h3>Your Profile</h3>
<p>ID: ${membre.id}</p>
<p>Username: ${membre.username}</p>
<p>Name: ${membre.nom}</p>

<h3>Your Sessions</h3>
<c:choose>
  <c:when test="${empty seances}">
    <p>No sessions scheduled.</p>
  </c:when>
  <c:otherwise>
    <table>
      <tr>
        <th>Session ID</th>
        <th>Coach ID</th>
        <th>Date/Time</th>
      </tr>
      <c:forEach var="seance" items="${seances}">
        <tr>
          <td>${seance.id}</td>
          <td>${seance.identraineur}</td>
          <td>${seance.dateetheure}</td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>
</body>
</html>