<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>DnD Troops</title>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
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
<h1>Welcome to Forgotten realm</h1>
<h3>Mission for heroes in realm PA165</h3>
<p>
Through Forgotten realms are wandering various troops of several heros trying to complete assigned mission 
(mission is not an entity, it is just text attribute). Hero has a name, role, experience level.
Troop has a name, mission and amount of golden money. Role contains name, description and other suitable information.
Example of a role is "elf magician". Every hero could belong to up to one group and have assigned multiple roles.
Administrator should be able to manage (CRUD) all entities.
Hero could assign himself to some group and also can assign himself some role.
</p>
</div>

</body>
</html>