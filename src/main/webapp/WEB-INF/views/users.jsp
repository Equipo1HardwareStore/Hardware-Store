<%@ page import="usuarios.UsuariosDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Usuarios</title>
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
<div class="main-container">
    <div class="form-box">
        <p>Gestion de Usuarios</p>
        <form action="Usuarios" method="post" id="main-form">
            <div class="form-info">
                <label for="cedula">Cédula</label>
                <input type="number" id="cedula" name="cedula" value="<%=request.getAttribute("USER") != null ? ((UsuariosDTO) request.getAttribute("USER")).getCedula() : ""%>"/>
                <label for="email">Correo Electrónico</label>
                <input type="email" id="email" name="email" value="<%=request.getAttribute("USER") != null ? ((UsuariosDTO) request.getAttribute("USER")).getEmail() : ""%>"/>
                <label for="name">Nombre Completo</label>
                <input type="text" id="name" name="name" value="<%=request.getAttribute("USER") != null ? ((UsuariosDTO) request.getAttribute("USER")).getName() : ""%>"/>
                <label for="password">Contraseña</label>
<%--                <input type="password" id="password" name="password" value="<%=request.getAttribute("USER") != null ? ((UsuariosDTO) request.getAttribute("USER")).getPassword() : ""%>"/>--%>
                <input type="password" id="password" name="password" value=""/>
                <label for="user">Usuario</label>
                <input type="text" id="user" name="user" value="<%=request.getAttribute("USER") != null ? ((UsuariosDTO) request.getAttribute("USER")).getUser() : ""%>"/>
            </div>
            <div class="form-buttons">
                <button type="button" name="search" id="search">Consultar</button>
                <button type="button" name="create" id="create">Crear</button>
                <button type="button" name="update" id="update">Actualizar</button>
                <button type="button" name="delete" id="delete">Borrar</button>
            </div>
<%--            <p style="margin:0; padding:0;" class="<%=request.getAttribute("RESULT") != null ? request.getAttribute("RESULT") : ""%>"><%=request.getAttribute("MESSAGE") != null ? request.getAttribute("MESSAGE") : ""%></p>--%>
        </form>
    </div>
    <div
            class="img-container"
            style="background-image: url('../../images/users2.jpg')"
    >
    </div>
</div>
<script type="text/javascript" src="../../js/jquery-3.6.0.min.js"></script>
<script src="../../popup/popup.js"></script>
<script type="text/javascript" src="../../js/alerts.js"></script>
</body>
</html>
