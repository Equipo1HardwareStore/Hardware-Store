<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10/15/2021
  Time: 2:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solicitar Cuenta</title>
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
    <link href="css/navbar.css" rel="stylesheet"/>
    <link href="css/style.css" rel="stylesheet"/>
</head>
<body>
<header>
    <div class="main-nav">
        <div class="top-bar">
            <span class="store-name">Hardware Store</span>
            <span class="exit"><a href="index.jsp">Salir</a></span>
        </div>
    </div>
</header>
<div class="public-form">
    <div class="info">
        <h1>Solicitud de Nueva Cuenta</h1>
        <form action="Email" method="post">
            <input type="text" name="name" placeholder="Nombre">
            <input type="email" name="email" placeholder="Correo">
            <textarea name="message" id="" cols="30" rows="10"
                      placeholder="Escriba la razón para solicitar una cuenta"></textarea>
            <button type="submit" name="new-acc" class="btn-landing">Enviar</button>
        </form>
    </div>
</div>
</body>
</html>
