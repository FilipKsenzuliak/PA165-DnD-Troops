<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>DnD Troops</title>
</head>
<body>
<h1>Welcome to Forgotten realms</h1>
<p>
Through Forgotten realms are wandering various troops of several heros trying to complete assigned mission 
(mission is not an entity, it is just text attribute). Hero has a name, role, experience level.
Troop has a name, mission and amount of golden money. Role contains name, description and other suitable information.
Example of a role is "elf magician". Every hero could belong to up to one group and have assigned multiple roles.
Administrator should be able to manage (CRUD) all entities.
Hero could assign himself to some group and also can assign himself some role.
</p>
<div>
    <ul>
        <li><a href="hero.jsp">Hero</a></li>
        <li><a href="role.jsp">Role</a></li>
        <li><a href="mission.jsp">Mission</a></li>
        <li><a href="troop.jsp">Troop</a></li>
    </ul>
</div>
<stripes:form beanclass="cz.fi.muni.pa165.CalculatorActionBean" focus="">
    <table>
        <tr>
            <td>Number 1:</td>
            <td><stripes:text name="numberOne"/></td>
        </tr>
        <tr>
            <td>Number 2:</td>
            <td><stripes:text name="numberTwo"/></td>
        </tr>
        <tr>
        </tr>
            <td colspan="2">
                <stripes:submit name="addition" value="Add"/>
            </td>
        <tr>
            <td>Result:</td>
            <td>${actionBean.result}</td>
        </tr>
    </table>
</stripes:form>
</body>
</html>