<%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 07/08/2019
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>RentalCar</title>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <!------ Include the above in your HEAD tag ---------->
  <style>
    .login-container{
      align:center;
      margin-top: 5%;
      margin-bottom: 5%;
    }
    .login-form-1{
      padding: 5%;
      box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0 rgba(0, 0, 0, 0.19);
    }
    .login-form-1 h3{
      text-align: center;
      color: #333;
    }

    .login-container form{
      padding: 10%;
    }
    .btnSubmit
    { align-content: center;
      width: 100%;
      border-radius: 1rem;
      padding: 1.5%;
      border: none;
      cursor: pointer;
    }
    .login-form-1 .btnSubmit{
      font-weight: 600;
      color: #fff;
      background-color: #0062cc;
    }


  </style>
</head>
<body class="w3-light-grey">
<div class="container login-container">
  <div>
    <div class="md-6 login-form-1">
      <h3>Login</h3>
      <form action="Login" method="post">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Nome *" name="nome" />
        </div>
        <div class="form-group">
          <input type="password" class="form-control" placeholder="Password *" name="passut" />
        </div>
        <div class="form-group">
          <input type="submit" class="btnSubmit" value="Entra" />
        </div>
      </form>
      <c:if test="${er1 == true}">
        <b> <span style="color: red; ">ERRORE CREDENZIALI</span></b>
      </c:if>
    </div>
  </div>
</div>
</body>
</html>
