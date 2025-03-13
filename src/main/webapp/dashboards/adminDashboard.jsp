<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Admin Dashboard - SportFlow</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 20px; }
    table { border-collapse: collapse; width: 80%; }
    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
    th { background-color: #f2f2f2; }
    form { margin: 10px 0; }
  </style>
</head>
<body>
<h2>Admin Dashboard</h2>
<a href="logout">Logout</a>

<!-- Add User -->
<h3>Add User</h3>
<form action="adminDashboard" method="post">
  <input type="hidden" name="action" value="addUser">
  <label>Username: <input type="text" name="username" required></label><br>
  <label>Password: <input type="password" name="password" required></label><br>
  <label>Role: <select name="role"><option value="ADMIN">Admin</option><option value="MEMBER">Member</option><option value="ENTRAINEUR">Coach</option></select></label><br>
  <input type="submit" value="Add User">
</form>

<!-- Add Member -->
<h3>Add Member</h3>
<form action="adminDashboard" method="post">
  <input type="hidden" name="action" value="addMembre">
  <label>Username: <input type="text" name="username" required></label><br>
  <label>Password: <input type="password" name="password" required></label><br>
  <label>Name: <input type="text" name="name" required></label><br>
  <input type="submit" value="Add Member">
</form>

<!-- Add Coach -->
<h3>Add Coach</h3>
<form action="adminDashboard" method="post">
  <input type="hidden" name="action" value="addEntraineur">
  <label>Username: <input type="text" name="username" required></label><br>
  <label>Password: <input type="password" name="password" required></label><br>
  <label>Name: <input type="text" name="name" required></label><br>
  <label>Specialty: <input type="text" name="specialite" required></label><br>
  <input type="submit" value="Add Coach">
</form>

<!-- Add Session -->
<h3>Add Session</h3>
<form action="adminDashboard" method="post">
  <input type="hidden" name="action" value="addSeance">
  <label>Member ID: <input type="number" name="idmembre" required></label><br>
  <label>Coach ID: <input type="number" name="identraineur" required></label><br>
  <label>Date/Time: <input type="datetime-local" name="dateetheure" required></label><br>
  <input type="submit" value="Add Session">
</form>

<!-- Users List -->
<h3>Users</h3>
<table>
  <tr><th>ID</th><th>Username</th><th>Role</th><th>Actions</th></tr>
  <c:forEach var="user" items="${users}">
    <tr>
      <td>${user.id}</td>
      <td>${user.username}</td>
      <td>${user.role}</td>
      <td>
        <form action="adminDashboard" method="post" style="display:inline;">
          <input type="hidden" name="action" value="updateUser">
          <input type="hidden" name="id" value="${user.id}">
          <input type="text" name="username" value="${user.username}" required>
          <input type="password" name="password" value="${user.password}" required>
          <select name="role">
            <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>Admin</option>
            <option value="MEMBER" ${user.role == 'MEMBER' ? 'selected' : ''}>Member</option>
            <option value="ENTRAINEUR" ${user.role == 'ENTRAINEUR' ? 'selected' : ''}>Coach</option>
          </select>
          <input type="submit" value="Update">
        </form>
        <form action="adminDashboard" method="post" style="display:inline;">
          <input type="hidden" name="action" value="deleteUser">
          <input type="hidden" name="id" value="${user.id}">
          <input type="submit" value="Delete">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

<!-- Members List -->
<h3>Members</h3>
<table>
  <tr><th>ID</th><th>Username</th><th>Name</th><th>Actions</th></tr>
  <c:forEach var="membre" items="${membres}">
    <tr>
      <td>${membre.id}</td>
      <td>${membre.username}</td>
      <td>${membre.name}</td>
      <td>
        <form action="adminDashboard" method="post" style="display:inline;">
          <input type="hidden" name="action" value="updateMembre">
          <input type="hidden" name="id" value="${membre.id}">
          <input type="text" name="username" value="${membre.username}" required>
          <input type="password" name="password" value="${membre.password}" required>
          <input type="text" name="name" value="${membre.name}" required>
          <input type="submit" value="Update">
        </form>
        <form action="adminDashboard" method="post" style="display:inline;">
          <input type="hidden" name="action" value="deleteMembre">
          <input type="hidden" name="id" value="${membre.id}">
          <input type="submit" value="Delete">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

<!-- Coaches List -->
<h3>Coaches</h3>
<table>
  <tr><th>ID</th><th>Username</th><th>Name</th><th>Specialty</th><th>Actions</th></tr>
  <c:forEach var="entraineur" items="${entraineurs}">
    <tr>
      <td>${entraineur.id}</td>
      <td>${entraineur.username}</td>
      <td>${entraineur.name}</td>
      <td>${entraineur.specialite}</td>
      <td>
        <form action="adminDashboard" method="post" style="display:inline;">
          <input type="hidden" name="action" value="updateEntraineur">
          <input type="hidden" name="id" value="${entraineur.id}">
          <input type="text" name="username" value="${entraineur.username}" required>
          <input type="password" name="password" value="${entraineur.password}" required>
          <input type="text" name="name" value="${entraineur.name}" required>
          <input type="text" name="specialite" value="${entraineur.specialite}" required>
          <input type="submit" value="Update">
        </form>
        <form action="adminDashboard" method="post" style="display:inline;">
          <input type="hidden" name="action" value="deleteEntraineur">
          <input type="hidden" name="id" value="${entraineur.id}">
          <input type="submit" value="Delete">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

<!-- Sessions List -->
<h3>Sessions</h3>
<table>
  <tr><th>ID</th><th>Member ID</th><th>Coach ID</th><th>Date/Time</th><th>Actions</th></tr>
  <c:forEach var="seance" items="${seances}">
    <tr>
      <td>${seance.id}</td>
      <td>${seance.idmembre}</td>
      <td>${seance.identraineur}</td>
      <td>${seance.dateetheure}</td>
      <td>
        <form action="adminDashboard" method="post" style="display:inline;">
          <input type="hidden" name="action" value="updateSeance">
          <input type="hidden" name="id" value="${seance.id}">
          <input type="number" name="idmembre" value="${seance.idmembre}" required>
          <input type="number" name="identraineur" value="${seance.identraineur}" required>
          <input type="datetime-local" name="dateetheure" value="${seance.dateetheure.toString().replace(' ', 'T')}" required>
          <input type="submit" value="Update">
        </form>
        <form action="adminDashboard" method="post" style="display:inline;">
          <input type="hidden" name="action" value="deleteSeance">
          <input type="hidden" name="id" value="${seance.id}">
          <input type="submit" value="Delete">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

<% if (request.getAttribute("error") != null) { %>
<p style="color:red"><%= request.getAttribute("error") %></p>
<% } %>
</body>
</html>