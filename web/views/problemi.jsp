<%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 28/08/2019
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="app.DAO.LoginDao"%>
<%@ page import="app.entities.User" %>
<html>
<head>
    <title>Pagina Problemi</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 87%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }
    </style>
    <script>
        function myFunction() {
            // Declare variables
            var input, filter, table, tr, td, i, txtValue, v1;
            //v1 = document.getElementById("b1");
            v1=document.querySelector('input[name="colum"]:checked').value;
            input = document.getElementById("myInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("myTable");
            tr = table.getElementsByTagName("tr");

            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[v1];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>

    <%
        if (session != null) {
            if (session.getAttribute("user") != null) {
                // String name = (String) session.getAttribute("nome");
                User usern = (User) session.getAttribute("user");
                request.setAttribute("usern",usern);
            } else {
                response.sendRedirect("index.jsp");
            }
        }
    %>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Benvenuto <c:out value="${usern.getNome()}" /></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <c:if test="${usern.tipologia == 'Admin'}">
                <a class="nav-item nav-link" href="User">Homepage</a>
            </c:if>
            <c:if test="${usern.tipologia == 'Customer'}">
                <a class="nav-item nav-link" href="Prenotazione?id=${usern.id}">Homepage</a>
            </c:if>
            <a class="nav-item nav-link" href="Park">Parco Auto</a>
            <c:if test="${usern.tipologia == 'Admin'}">
                <a class="nav-item nav-link" href="Codice">Codici Sconto</a>
            </c:if>
            <c:if test="${usern.tipologia == 'Customer'}">
                <a class="nav-item nav-link" href="Problema?id=${usern.id}">Multe</a>
            </c:if>
            <a class="nav-item nav-link" href="Ruoli">Ruoli</a>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-4" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-user"></i> Profilo </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-4">
                        <a class="dropdown-item" href="Profile">Il Mio Profilo: <c:out value="${usern.getNome()}"/></a>
                        <div style="text-align: center;"> <form class="dropdown-item" action="Logout" method="post">
                            <input type="submit" class="btn btn-danger btn-sm" value="Logout">
                        </form></div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>

<center>
    <h3>Problem Management:</h3>
    <h4>
        <c:url var="link" value="Operation">
            <c:param name="id" value='<%=request.getParameter("id")%>'>
            </c:param>
            <c:param name="operation" value="new">
            </c:param>
            <c:param name="tipo" value="problema">
            </c:param>
        </c:url>

        <c:if test="${usern.tipologia == 'Admin'}">
            <a href="${link}">Aggiungi Nuovi Problemi</a>
        </c:if>
    </h4>
</center>

<div align="center">
    <%--    <a class="btn btn-primary" href="<c:url value = "Park"/>">Modifica</a>--%>
    <label class="radio-inline">
        <input type="radio" name="colum" id="b1" value="0"> Id<br>
    </label>
    <label class="radio-inline">
        <input type="radio" name="colum" id="b1" value="1"> Descrizione<br>
    </label>
    <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Cerca per Selezione">
    <table border="1" cellpadding="5" id="myTable">
        <caption><h2>Lista Prenotazioni</h2></caption>
        <tr class="header">
            <th>ID</th>
            <th>Descrizione</th>
            <th>Data Probl</th>
            <th>ID Pren</th>
            <c:if test="${usern.tipologia == 'Admin'}">
                <th>Azioni</th>
            </c:if>

        </tr>
        <c:forEach var="probl" items="${requestScope.listProbl}">

            <c:url var="linkModifica" value="Operation">
                <c:param name="idProbl" value='${probl.getId()}'>
                </c:param>
                <c:param name="idutente" value='<%=request.getParameter("id")%>'>
                </c:param>
                <c:param name="operation" value="update">
                </c:param>
                <c:param name="tipo" value="problema">
                </c:param>
            </c:url>

            <c:url var="linkCanc" value="Operation">
                <c:param name="id" value='${probl.getId()}'>
                </c:param>
                <c:param name="idutente" value='<%=request.getParameter("id")%>'>
                </c:param>
                <c:param name="operation" value="delete">
                </c:param>
                <c:param name="tipo" value="problema">
                </c:param>
            </c:url>
            <tr>
                <td><c:out value="${probl.getId()}" /></td>
                <td><c:out value="${probl.getDescrizione()}" /></td>
                <td><c:out value="${probl.getDataprob()}" /></td>
                <td><c:out value="${probl.getPren().getId()} ${probl.getPren().getAuto().getNome()}" /></td>
                <c:if test="${usern.tipologia == 'Admin'}">
                <td>

                        <%--
                                            <a class="btn btn-primary" href="edit?id=<c:out value='${user.getId()}' />">Modifica</a>
                        --%>
                    <a class="btn btn-primary" href="${linkModifica}">Modifica</a>
                    &nbsp;
                    <a class="btn btn-outline-danger" onclick="return confirm('Sei sicuro di voler eliminare questo problema?');"href="${linkCanc}">Elimina</a>
                    &nbsp;&nbsp;
                </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>