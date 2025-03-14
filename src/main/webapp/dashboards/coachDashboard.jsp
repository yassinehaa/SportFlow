<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Coach Dashboard - SportFlow</title>
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 min-h-screen p-6">
<div class="max-w-4xl mx-auto">
  <div class="flex justify-between items-center mb-6">
    <h2 class="text-2xl font-bold text-gray-800">Welcome, ${entraineur.username}</h2>
    <a href="${pageContext.request.contextPath}/logout" class="text-blue-600 hover:underline">Logout</a>
  </div>

  <div class="bg-white shadow-md rounded-lg p-6 mb-6">
    <h3 class="text-xl font-semibold text-gray-700 mb-4">Your Profile</h3>
    <p class="text-gray-600">ID: <span class="font-medium">${entraineur.id}</span></p>
    <p class="text-gray-600">Username: <span class="font-medium">${entraineur.username}</span></p>
    <p class="text-gray-600">Name: <span class="font-medium">${entraineur.name}</span></p>
    <p class="text-gray-600">Specialty: <span class="font-medium">${entraineur.specialite}</span></p>
  </div>

  <div class="bg-white shadow-md rounded-lg p-6">
    <h3 class="text-xl font-semibold text-gray-700 mb-4">Your Sessions</h3>
    <c:choose>
      <c:when test="${empty seances}">
        <p class="text-gray-500">No sessions scheduled.</p>
      </c:when>
      <c:otherwise>
        <div class="overflow-x-auto">
          <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
            <tr>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Session ID</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Member ID</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Date/Time</th>
            </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
            <c:forEach var="seance" items="${seances}">
              <tr>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${seance.id}</td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${seance.idmembre}</td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${seance.dateetheure}</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </c:otherwise>
    </c:choose>
  </div>
</div>
</body>
</html>