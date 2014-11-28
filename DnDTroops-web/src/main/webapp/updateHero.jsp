<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <stripes:form beanclass="cz.fi.muni.pa165.HeroActionBean" focus="">
                <table class="table table-striped table-hover" id="tabulka">
                    <tr>
                        <td>Name:</td>
                        <td><stripes:text name="name" value="{$actionBean.}"/></td>
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
                </table>
                    <td>
                        <stripes:submit name="create" value="Save"/>
                    </td>
                    <td>
                        <stripes:submit name="addition" value="test"/>
                    </td>
            </stripes:form>
            <a href="hero.jsp">
                <button class="btn btn-lg">Cancel</button>
            </a>
        </div>
    </body>
</html>