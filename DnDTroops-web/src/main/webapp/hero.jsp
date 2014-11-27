<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>DnD Troops</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:useBean id="actionBean" scope="page" class="cz.fi.muni.pa165.HeroActionBean"/>    
            <table class="table table-striped table-hover" id="tabulka">
                <thead>
                    <tr>
                        <th class="col-md-2">Name</th>
                        <th class="col-md-2">Rank</th>
                        <th class="col-md-2">Age</th>
                        <th class="col-md-2">Race</th>
                        <th class="col-md-2">Troop</th>
                        <th class="col-md-2">Role</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${actionBean.heroes}" var="hero">
                        <tr>
                            <td class="col-md-2"><c:out value="${hero.name}"/></td>
                            <td class="col-md-2"><c:out value="${hero.rank}"/></td>
                            <td class="col-md-2"><c:out value="${hero.age}"/></td>
                            <td class="col-md-2"><c:out value="${hero.race}"/></td>
                            <td class="col-md-2"><c:out value="${hero.troop.name}"/></td>
                            <td class="col-md-2">
                                <c:forEach items="${hero.role}" var="role">
                                    <c:out value="${role.name}"/>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
</body>
</html>
