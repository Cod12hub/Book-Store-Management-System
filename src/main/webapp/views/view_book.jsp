<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>View Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-xxx" crossorigin="anonymous" />

<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .navbar {
            background-color: #333;
            color: #fff;
            padding: 10px 0;
        }

        .container {
            width: 80%;
            margin: 0 auto;
        }

        .navbar-brand {
            color: #fff;
            text-decoration: none;
            font-size: 1.5rem;
            font-weight: bold;
        }

        .navbar-links a {
            color: #fff;
            margin-left: 15px;
            text-decoration: none;
        }

        .my-5 {
            margin-top: 5rem;
            margin-bottom: 5rem;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .btn {
            display: inline-block;
            padding: 8px 16px;
            margin-bottom: 0;
            font-size: 14px;
            font-weight: 400;
            line-height: 1.42857143;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            cursor: pointer;
            border: 1px solid transparent;
            border-radius: 4px;
        }

        .btn-sm {
            padding: 6px 12px;
            font-size: 12px;
        }

        .btn-secondary {
            color: #fff;
            background-color: #333;
            border-color: #333;
        }

        .ms-4 {
            margin-left: 1rem;
        }

        .ms-4 i {
            color: blue;
        }

        .ms-4 i:hover {
            color: red;
        }
       
    </style>
</head>
<body>

   
<nav class="navbar">
    <div class="container">
        <a class="navbar-brand" href="#">Book Store</a>
        <div class="navbar-links">
            <a href="/">Home</a>
            <a href="/view">Available Books</a>
            <a href="/cart">My Cart</a>
        </div>
    </div>
</nav>

   

<div class="container my-5 col-md-8">
    <table>
        <thead>
            <tr>
            
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Author</th>
                <th scope="col">Price</th>
                <th scope="col">Action</th>
            </tr>
        </thead>
       <tbody>
           <c:forEach var="bok" items="${e_data}">
       
        <tr>
            <td>${bok.id}</td>
            <td>${bok.title}</td>
            <td>${bok.author}</td>
            <td>${bok.price}</td>
            <td>
                <!--<a class="btn btn-secondary btn-sm" href="/regis">Add Book</a>-->
                <form method="post" action="<c:url value='/my_cart/${bok.id}'/>">
                <button type="submit" class="btn btn-secondary btn-sm">Add Cart</button>
            </form>
                
               
                <!--  <a class="btn btn-secondary btn-sm" href="<c:url value='/${bok.id}'/>"class="delete-button">Delete</a> -->
              <!--<a class="btn btn-secondary btn-sm" href="<c:url value='/edit/${bok.id}'/>"class="update-button">Update </a> -->
            </td>
        </tr>
    </c:forEach>
</tbody>

    </table>
                   
</div>
</body>
</html>