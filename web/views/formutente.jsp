<%@ page import="app.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 08/08/2019
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
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
    <title>User Management Application</title>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 90%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="User">Benvenuto <c:out value="${usern.getNome()}" /></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <c:if test="${usern.tipologia == 'Admin'}">
                <a class="nav-item nav-link" href="User">Homepage <span class="sr-only">(current)</span></a>
            </c:if>
            <c:if test="${usern.tipologia == 'Customer'}">
                <a class="nav-item nav-link" href="Prenotazione?id=${usern.getNome()}">Homepage <span class="sr-only">(current)</span></a>
            </c:if>
            <a class="nav-item nav-link" href="Park">Parco Auto</a>
            <a class="nav-item nav-link" href="Codice">Codici Sconto</a>
            <a class="nav-item nav-link" href="Ruoli">Ruoli</a>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-4" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-user"></i> Profilo </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-4">
                        <a class="dropdown-item" href="Profile">Il Mio Profilo: <c:out value="${usern.getNome()}"/></a>
                        <div style="text-align: center;"> <form action="Logout" method="post">
                            <input type="submit" class="btn btn-danger btn-sm" value="Logout">
                        </form></div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
    <h2 align="center">User Management</h2>
<div align="center">
    <c:if test="${requestScope.user != null}">
    <form action="User" method="post">
        <input type="hidden" name="idUtente" value="${requestScope.user.id}">
        </c:if>
        <c:if test="${requestScope.user == null}">
        <form action="User" method="post">
        </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${requestScope.user != null}">
                            Modifica Utente
                        </c:if>
                        <c:if test="${requestScope.user == null}">
                            Aggiungi Nuovo Utente
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${requestScope.user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.getId()}' />" />
                </c:if>
                <tr>
                    <th>Nome: </th>
                    <td>
                        <input class="form-control" type="text" name="nome" size="45"
                               value="<c:out value='${user.getNome()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>User Email: </th>
                    <td>
                        <input class="form-control" type="text" name="email" size="45"
                               value="<c:out value='${user.getEmail()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Password: </th>
                    <td>
                        <input class="form-control" type="text" name="passut" size="15"
                               value="<c:out value='${user.getPassut()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Seleziona il Ruolo:  </th>
                    <td>
                        <select class="form-control" id="tipologia" name="tipologia">
                            <c:forEach var="ruo" items="${requestScope.listRuo}">
                                <%--                        <% for(int i = 1; i < 40; i++) { %>--%>
                                <option value="${ruo.getDettagli()}"
                                        <c:if test="${ruo.getStato()=='nonat'}">
                                            disabled
                                        </c:if>
                                        <c:if test="${ruo.getDettagli() == user.getTipologia()}">
                                            selected="selected"
                                        </c:if>
                                >Ruolo Numero ${ruo.getId()} &nbsp ${ruo.getDettagli()} &nbsp Stato ${ruo.getStato()}  </option>
                                <%--                        <%}%>--%>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Data di Nascita: </th>
                    <td>
                        <input class="form-control" type="date" name="datan" min="1900-1-31" value="<c:out value='${user.getDatan()}' />"><br>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input class="btn btn-primary" type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
