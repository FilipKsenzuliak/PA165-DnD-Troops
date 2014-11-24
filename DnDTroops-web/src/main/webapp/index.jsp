<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hero</title>
        
        <script>
        function getContextPath() {
            return $('#contextPath').val();
        }

        $(document).ready(function () {
            load_heroes();
        });
        function load_heroes() {

            $.ajax({
                type: 'GET',
                url: getContextPath()+'/mvc/hero/list',
                success: function (data) {
                    var tab = '';
                    $.each(data,function(idx,hero) {
                        tab+='<tr>' +
                                '<td>'+hero.id+'</td>' +
                                '<td>'+hero.race+'</td>'+
                                '<td>'+hero.age+'</td>'+
                                '<td>'+hero.rank+'</td>'+
                                '<td>'+hero.role+'</td>'+
                                '<td>'+hero.troop+'</td>'+
                                '<td>'+hero.name+'</td>'+
                                '<td><button onclick="delete_hero('+hero.id+')">delete</button></td>'+
                                '<td><button onclick="edit_hero('+hero.id+')">edit</button></td>'+
                                '</tr>';
                    });
                    $('#heros').html(tab);
                }
            });
        }
        </script>
    </head>
    
    
    <body>
        <h1>Hello World!</h1>
        
        <div id="msgarea"></div>

        <table class="mytable" id="heros">

        </table>

        <br><button onclick="load_heros()">Load table</button>
        <br><button onclick="delete_hero(100)">Delete non-existent</button>
        <br><a href="${pageContext.request.contextPath}/mvc/hero/list">JSON list</a>
        <br>
        <br><button onclick="create_hero()">Create hero</button>
        <br><button onclick="update_hero()">Update hero</button>

        <input id="contextPath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}">

        <form id="f1" name="f1" onsubmit="submit_hero()">
            <table>
                <tr>
                    <td>id</td>
                    <td><input type="text" name="heroId" id="heroId" value="0"></td>
                </tr>
                <tr>
                    <td>name</td>
                    <td><input type="text" name="heroName" id="heroName"></td>
                </tr>
                <tr>
                    <td>role</td>
                    <td><input type="text" name="heroRole" id="heroRole"></td>
                </tr>
                <tr>
                    <td>race</td>
                    <td><input type="checkbox" name="heroRace" id="heroRace"></td>
                </tr>
                <tr>
                    <td>troop</td>
                    <td><input type="text" name="heroTroop" id="heroTroop"></td>
                </tr>
                <tr>
                    <td>rank</td>
                    <td><input type="text" name="heroRank" id="heroRank"></td>
                </tr>
                <tr>
                    <td>age</td>
                    <td><input type="text" name="heroAge" id="heroAge"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
