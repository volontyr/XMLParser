<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>XMLParser</title>
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <style>
        body { padding-top: 60px; }
    </style>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#myForm").submit(function(e) {
               e.preventDefault();
               parseXML(this);
            });

            function parseXML(form) {
                $.post('parse', {
                    nodeName : $(form).find("#fileNodeName").val(),
                    cnt : $(form).find("#cnt").val()
                }, function(data) {
                    $("#parseResponse").html($(data).find("#parseResponse").html());
                });
            }
        });
    </script>
</head>
<body>
    <div class="navbar navbar-fixed-top navbar-inverse">
        <div class="navbar-inner">
            <div class="container">
                Parser
            </div>
        </div>
    </div>
    <div class="container">
        <form id="myForm" action="parse" method="post">
            <div class="form-group">
                <label for="fileNodeName">Node name</label>
                <input type="text" class="form-control" id="fileNodeName" value="" name="fileNodeName" />
                <label for="cnt">Quantity</label>
                <input type="number" class="form-control" id="cnt" value="0" name="cnt" /><br>
                <input type="submit" value="Parse" class="btn btn-primary">
            </div>
        </form>
        <div id="parseResponse" class="panel panel-default">
            <c:if test="${nodes != null}">
                <div class="panel-heading">Result:</div>
                <div class="panel-body">
                    <c:forEach items="${nodes}" var="node">
                        ${node}<br>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>
