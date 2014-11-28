<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Heroes</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/dnd.css" rel="stylesheet"/>
    </head>
    <div class="main"></div>
    <div class="container">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand active" href="index.jsp">DnD Troops</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="hero.jsp">Heroes</a></li>
                    <li><a href="role.jsp">Roles</a></li>
                    <li><a href="mission.jsp">Troops</a></li>
                    <li><a href="troop.jsp">Missions</a></li>
                </ul>
            </div>
        </div>
    </nav>
            <jsp:useBean id="actionBean" scope="page" class="cz.fi.muni.pa165.HeroActionBean"/> 
            <a href="createHero.jsp">
                <button class="btn btn-primary btn-lg">Create New Hero</button>
            </a>
            <table class="table table-hover" id="tabulka">
                <thead>
                    <tr>
                        <th style="display:none">id</th>
                        <th class="col-md-2">Name</th>
                        <th class="col-md-2">Rank</th>
                        <th class="col-md-2">Age</th>
                        <th class="col-md-2">Race</th>
                        <th class="col-md-2">Troop</th>
                        <th class="col-md-2">Role</th>
                        <th class="col-md-1">Edit</th>
                        <th class="col-md-1">Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${actionBean.heroes}" var="hero">
                        <tr>
                            <td style="display:none">${hero.id}</td>
                            <td class="col-md-2"><c:out value="${hero.name}"/></td>
                            <td class="col-md-2"><c:out value="${hero.rank}"/></td>
                            <td class="col-md-2"><c:out value="${hero.age}"/></td>
                            <td class="col-md-2"><c:out value="${hero.race}"/></td>
                            <td class="col-md-2"><c:out value="${hero.troop.name}"/></td>
                            <td class="col-md-2">
                                <c:forEach items="${hero.role}" var="role">
                                    <c:out value="${role.name}"/>,
                                </c:forEach>
                            </td>
                            <td class="col-md-1">
                                <a href="updateHero.jsp">
                                    <button class="btn btn-info btn-lg edit">
                                        <span class="glyphicon glyphicon glyphicon-list"></span>
                                    </button>
                                </a>
                            </td>
                            <td class="col-md-1">
                                <button class="btn btn-lg btn-danger delete">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
</body>
</html>
