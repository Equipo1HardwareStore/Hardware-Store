<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10/15/2021
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reestrablecer Contraseña</title>
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
<body>
<div class="public-form">
    <div class="login-box recovery-box">
        <h1>Reestrablecer contraseña</h1>
        <form action="Email" method="post">
            <input type="Email" name="email" placeholder="Correo"
                   value="<%=request.getAttribute("email") != null ? request.getAttribute("email") : ""%>" required>
            <button class="btn-landing" type="submit" name="recover" formnovalidate>Enviar código</button>
            <input type="text" name="code" placeholder="código" required>
            <input type="password" name="new_pass" placeholder="Nueva contraseña" required>
            <div><p class="error-message"
                    style="color:red; font-size:12px;"><%=request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
            </p>
                <p class="error-message"
                   style="color:green; font-size:12px;"><%=request.getAttribute("success") != null ? request.getAttribute("success") : ""%>
                </p></div>
            <button class="btn-landing" type="submit" name="reset" style="width:-webkit-fill-available;">Reestablecer</button>
        </form>
    </div>
    <div class="recover-info">
       <p>Se enviará un código al correo registrado, por favor ingrese el codigo y la nueva contraseña para reestrablecerla.</p>
        <p>*El código expira en 10 minutos</p>
    </div>
</div>

</body>
</html>
