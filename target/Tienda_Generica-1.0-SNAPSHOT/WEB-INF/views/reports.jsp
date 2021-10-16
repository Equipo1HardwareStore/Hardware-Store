<%@ page import="proveedores.ProveedoresDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Proveedores</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link
            href="http://fonts.googleapis.com/css?family=Roboto:400,900,700,500,300,100"
            rel="stylesheet"
            type="text/css"
    />
    <link
            rel="stylesheet"
            type=""
            href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
    />
    <link href="../../css/main.css" rel="stylesheet"/>
    <link href="../../popup/style.css" rel="stylesheet"/>
    <script type="text/javascript">
        var success = '<%= request.getAttribute("RESULT") %>';
        var msg = '<%= request.getAttribute("MESSAGE") %>';
    </script>
</head>
<body>

<jsp:include page="../partials/navbar.jsp"/>

<div class="main-container" style="flex-direction: column">
    <h1 id="tituloReporte"></h1>
    <div class="tb-box">
        <table id="tb-report" class="table-pag"></table>
    </div>
    <div id="valorFinal-box"><label id="lb-totalVentas">Total Ventas</label>
        <label id="totalVentas"></label></div>
    <div class="button-box">
        <div class="div-btn-reports"><button class="btn-report" id="btn-users">
            <i class="fas fa-users"></i>
        </button>
            <label  class="lb-list" for="btn-users">Listado de Usuarios</label>
        </div>
        <div class="div-btn-reports"><button class="btn-report" id="btn-customers">
            <i class="fas fa-handshake"></i>
        </button>
            <label  class="lb-list" for="btn-users">Listado de Clientes</label>
        </div>
        <div class="div-btn-reports"><button class="btn-report" id="btn-sales">
            <i class="fas fa-shopping-cart"></i>
        </button>
            <label  class="lb-list" for="btn-users">Ventas por Clientes</label>
        </div>


    </div>

</div>
<script type="text/javascript" src="../../js/jquery-3.6.0.min.js"></script>
<script src="../../popup/popup.js"></script>
<script type="text/javascript" src="../../js/alerts.js"></script>
<script type="text/javascript" src="../../js/reports.js"></script>
</body>
</html>
