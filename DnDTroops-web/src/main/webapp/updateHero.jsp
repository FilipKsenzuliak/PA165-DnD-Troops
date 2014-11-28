<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/dnd.css" rel="stylesheet"/>
    </head>
    <body>
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
            <stripes:form beanclass="cz.fi.muni.pa165.HeroActionBean" focus="">
                <table class="table table-hover" id="tabulka">
                    <tr>
                        <td>Name:</td>
                        <td><stripes:text name="name" value=""/></td>
                    </tr>
                    <tr>
                        <td>Age:</td>
                        <td><stripes:text name="age"/></td>
                    </tr>
                    <tr>
                        <td>Race:</td>
                        <td>
                            <stripes:select name="race"> 
                                <stripes:options-enumeration enum="cz.fi.muni.pa165.entity.Race"/>
                            </stripes:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Role:</td>
                        <td>
                            <select>
                                <c:forEach items="${actionBean.roles}" var="role">
                                    <option><c:out value="${role.name}"/></option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Troop:</td>
                        <td>
                            <select>
                                <c:forEach items="${actionBean.troops}" var="troop">
                                    <option><c:out value="${troop.name}"/></option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="col-md-1">
                            <stripes:submit class="btn btn-lg" name="create" value="Create"/>
                        </td>
                        <td class="col-md-1">
                            <button class="btn btn-lg">Cancel</button>
                        </td>
                        <td class="col-md-10"/>
                    </tr>
                </table>
            </stripes:form>
        </div>
    </body>
</html>