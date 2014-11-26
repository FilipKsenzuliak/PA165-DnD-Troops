<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>

<html>
    <head>
        <title>Hero</title>
        <style>
            table.mytable { border-collapse: collapse; margin: 10px; }
            table.mytable th, table.mytable td {  border: solid 1px black; padding: 4px; }
        </style>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <script>
        function getContextPath() {
            return $('#contextPath').val();
        }

        $(document).ready(function () {
            load_heroes();
        });
        function load_heroes() {
            var tabe = '';
            tabe ='<tr><td>afef</td><td>afaefaefaefa</td></tr>'
            $('#heroes').html(tabe);
                                
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
                    $('#heroes').html(tab);
                }
            });
        }
        function update_hero() {
            $.ajax({
                type: 'POST',
                url: getContextPath()+'/mvc/hero/update',
                contentType: "application/json",
                data: JSON.stringify({
                    'id': parseInt($('#heroId').val()),
                    'name': $('#heroName').val(),
                    'role': $('#heroRole').val(),
                    'race': $('#heroRace').val(),
                    'troop': $('#heroTroop').val(),
                    'rank': $('#heroRank').val(),
                    'age': $('#heroAge').val()
                }),
                statusCode: {
                    404: function(a,b,c) {
                        $('#msgarea').html('hero not found');
                    },
                    422: function(a,b,c) {
                        var msg='';
                        $.each(a.responseJSON.fieldErrors,function(key,value){
                            msg+='<br>'+key+": "+value;
                        });
                        $('#msgarea').html(msg);
                    }
                }
            }).done(function (data) {
                load_heroes();
                $('#msgarea').html('heroes updated');
            });
        }

        function create_hero() {
            $('#heroId').val('0');
            update_hero();
        }
        
        </script>
    </head>
    
    
    <body>
        <h1>Hello World!</h1>
        
        <div id="msgarea"></div>

        <table class="mytable" id="heroes">

        </table>
        
        <input hidden="true" id="contextPath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}">

        <br><button onclick="load_heroes()">Load table</button>
        <br><button onclick="create_hero()">Create hero</button>
        <br><button onclick="update_hero()">Update hero</button>
        <br><a href="${pageContext.request.contextPath}/mvc/hero/list">JSON list</a>
<br>

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
                    <td><input type="text" name="heroRace" id="heroRace"></td>
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
