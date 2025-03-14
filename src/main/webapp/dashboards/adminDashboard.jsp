<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard - SportFlow</title>
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 min-h-screen p-6">
<div class="max-w-6xl mx-auto">
  <div class="flex justify-between items-center mb-6">
    <h2 class="text-2xl font-bold text-gray-800">Admin Dashboard</h2>
    <a href="logout" class="text-blue-600 hover:underline">Logout</a>
  </div>

  <!-- Add User Form -->
  <div class="bg-white shadow-md rounded-lg p-6 mb-6">
    <h3 class="text-xl font-semibold text-gray-700 mb-4">Add User</h3>
    <form action="adminDashboard" method="post" class="space-y-4">
      <input type="hidden" name="action" value="addUser">
      <div>
        <label class="block text-gray-600">Username:</label>
        <input type="text" name="username" required class="w-full border rounded-md p-2">
      </div>
      <div>
        <label class="block text-gray-600">Password:</label>
        <input type="password" name="password" required class="w-full border rounded-md p-2">
      </div>
      <div>
        <label class="block text-gray-600">Role:</label>
        <select name="role" class="w-full border rounded-md p-2">
          <option value="admin">Admin</option>
          <option value="membre">Member</option>
          <option value="entraineur">Coach</option>
        </select>
      </div>
      <input type="submit" value="Add User" class="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700">
    </form>
  </div>

  <!-- Add Member Form -->
  <div class="bg-white shadow-md rounded-lg p-6 mb-6">
    <h3 class="text-xl font-semibold text-gray-700 mb-4">Add Member</h3>
    <form action="adminDashboard" method="post" class="space-y-4">
      <input type="hidden" name="action" value="addMembre">
      <div>
        <label class="block text-gray-600">Username:</label>
        <input type="text" name="username" required class="w-full border rounded-md p-2">
      </div>
      <div>
        <label class="block text-gray-600">Password:</label>
        <input type="password" name="password" required class="w-full border rounded-md p-2">
      </div>
      <div>
        <label class="block text-gray-600">Name:</label>
        <input type="text" name="name" required class="w-full border rounded-md p-2">
      </div>
      <input type="submit" value="Add Member" class="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700">
    </form>
  </div>

  <!-- Add Coach Form -->
  <div class="bg-white shadow-md rounded-lg p-6 mb-6">
    <h3 class="text-xl font-semibold text-gray-700 mb-4">Add Coach</h3>
    <form action="adminDashboard" method="post" class="space-y-4">
      <input type="hidden" name="action" value="addEntraineur">
      <div>
        <label class="block text-gray-600">Username:</label>
        <input type="text" name="username" required class="w-full border rounded-md p-2">
      </div>
      <div>
        <label class="block text-gray-600">Password:</label>
        <input type="password" name="password" required class="w-full border rounded-md p-2">
      </div>
      <div>
        <label class="block text-gray-600">Name:</label>
        <input type="text" name="name" required class="w-full border rounded-md p-2">
      </div>
      <div>
        <label class="block text-gray-600">Specialty:</label>
        <input type="text" name="specialite" required class="w-full border rounded-md p-2">
      </div>
      <input type="submit" value="Add Coach" class="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700">
    </form>
  </div>

  <!-- Add Session Form -->
  <div class="bg-white shadow-md rounded-lg p-6 mb-6">
    <h3 class="text-xl font-semibold text-gray-700 mb-4">Add Session</h3>
    <form action="adminDashboard" method="post" class="space-y-4">
      <input type="hidden" name="action" value="addSeance">
      <div>
        <label class="block text-gray-600">Member ID:</label>
        <input type="number" name="idmembre" required class="w-full border rounded-md p-2">
      </div>
      <div>
        <label class="block text-gray-600">Coach ID:</label>
        <input type="number" name="identraineur" required class="w-full border rounded-md p-2">
      </div>
      <div>
        <label class="block text-gray-600">Date/Time:</label>
        <input type="datetime-local" name="dateetheure" required class="w-full border rounded-md p-2">
      </div>
      <input type="submit" value="Add Session" class="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700">
    </form>
  </div>

  <!-- Users List -->
  <div class="bg-white shadow-md rounded-lg p-6 mb-6">
    <h3 class="text-xl font-semibold text-gray-700 mb-4">Users</h3>
    <div class="overflow-x-auto">
      <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Username</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Role</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
        <c:forEach var="user" items="${users}">
          <tr>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${user.id}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${user.username}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${user.role}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
              <form action="adminDashboard" method="post" class="inline-flex space-x-2">
                <input type="hidden" name="action" value="updateUser">
                <input type="hidden" name="id" value="${user.id}">
                <input type="text" name="username" value="${user.username}" required class="border rounded-md p-1">
                <input type="password" name="password" value="${user.password}" required class="border rounded-md p-1">
                <select name="role" class="border rounded-md p-1">
                  <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>Admin</option>
                  <option value="membre" ${user.role == 'membre' ? 'selected' : ''}>Member</option>
                  <option value="entraineur" ${user.role == 'entraineur' ? 'selected' : ''}>Coach</option>
                </select>
                <input type="submit" value="Update" class="bg-green-600 text-white px-2 py-1 rounded-md hover:bg-green-700">
              </form>
              <form action="adminDashboard" method="post" class="inline-flex">
                <input type="hidden" name="action" value="deleteUser">
                <input type="hidden" name="id" value="${user.id}">
                <input type="submit" value="Delete" class="bg-red-600 text-white px-2 py-1 rounded-md hover:bg-red-700">
              </form>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <!-- Members List -->
  <div class="bg-white shadow-md rounded-lg p-6 mb-6">
    <h3 class="text-xl font-semibold text-gray-700 mb-4">Members</h3>
    <div class="overflow-x-auto">
      <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Username</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
        <c:forEach var="membre" items="${membres}">
          <tr>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${membre.id}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${membre.username}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${membre.nom}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
              <form action="adminDashboard" method="post" class="inline-flex space-x-2">
                <input type="hidden" name="action" value="updateMembre">
                <input type="hidden" name="id" value="${membre.id}">
                <input type="text" name="username" value="${membre.username}" required class="border rounded-md p-1">
                <input type="password" name="password" value="${membre.password}" required class="border rounded-md p-1">
                <input type="text" name="name" value="${membre.nom}" required class="border rounded-md p-1">
                <input type="submit" value="Update" class="bg-green-600 text-white px-2 py-1 rounded-md hover:bg-green-700">
              </form>
              <form action="adminDashboard" method="post" class="inline-flex">
                <input type="hidden" name="action" value="deleteMembre">
                <input type="hidden" name="id" value="${membre.id}">
                <input type="submit" value="Delete" class="bg-red-600 text-white px-2 py-1 rounded-md hover:bg-red-700">
              </form>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <!-- Coaches List -->
  <div class="bg-white shadow-md rounded-lg p-6 mb-6">
    <h3 class="text-xl font-semibold text-gray-700 mb-4">Coaches</h3>
    <div class="overflow-x-auto">
      <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Username</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Specialty</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
        <c:forEach var="entraineur" items="${entraineurs}">
          <tr>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${entraineur.id}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${entraineur.username}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${entraineur.name}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${entraineur.specialite}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
              <form action="adminDashboard" method="post" class="inline-flex space-x-2">
                <input type="hidden" name="action" value="updateEntraineur">
                <input type="hidden" name="id" value="${entraineur.id}">
                <input type="text" name="username" value="${entraineur.username}" required class="border rounded-md p-1">
                <input type="password" name="password" value="${entraineur.password}" required class="border rounded-md p-1">
                <input type="text" name="name" value="${entraineur.name}" required class="border rounded-md p-1">
                <input type="text" name="specialite" value="${entraineur.specialite}" required class="border rounded-md p-1">
                <input type="submit" value="Update" class="bg-green-600 text-white px-2 py-1 rounded-md hover:bg-green-700">
              </form>
              <form action="adminDashboard" method="post" class="inline-flex">
                <input type="hidden" name="action" value="deleteEntraineur">
                <input type="hidden" name="id" value="${entraineur.id}">
                <input type="submit" value="Delete" class="bg-red-600 text-white px-2 py-1 rounded-md hover:bg-red-700">
              </form>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <!-- Sessions List -->
  <div class="bg-white shadow-md rounded-lg p-6">
    <h3 class="text-xl font-semibold text-gray-700 mb-4">Sessions</h3>
    <div class="overflow-x-auto">
      <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
        <tr>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Member ID</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Coach ID</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Date/Time</th>
          <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
        </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
        <c:forEach var="seance" items="${seances}">
          <tr>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${seance.id}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${seance.idmembre}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${seance.identraineur}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${seance.dateetheure}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
              <form action="adminDashboard" method="post" class="inline-flex space-x-2">
                <input type="hidden" name="action" value="updateSeance">
                <input type="hidden" name="id" value="${seance.id}">
                <input type="number" name="idmembre" value="${seance.idmembre}" required class="border rounded-md p-1">
                <input type="number" name="identraineur" value="${seance.identraineur}" required class="border rounded-md p-1">
                <input type="datetime-local" name="dateetheure" value="${seance.dateetheure.toString().replace(' ', 'T')}" required class="border rounded-md p-1">
                <input type="submit" value="Update" class="bg-green-600 text-white px-2 py-1 rounded-md hover:bg-green-700">
              </form>
              <form action="adminDashboard" method="post" class="inline-flex">
                <input type="hidden" name="action" value="deleteSeance">
                <input type="hidden" name="id" value="${seance.id}">
                <input type="submit" value="Delete" class="bg-red-600 text-white px-2 py-1 rounded-md hover:bg-red-700">
              </form>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <% if (request.getAttribute("error") != null) { %>
  <p class="text-red-600 mt-4"><%= request.getAttribute("error") %></p>
  <% } %>
</div>
</body>
</html>