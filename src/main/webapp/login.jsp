<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
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
    <!-- <link rel="stylesheet" type="" -->
    <!-- href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
    <style>
        body {
            font-family: "Roboto";
        }
    </style>
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

<div class="main">
    <div class="login-box">
        <i class="fas fa-laptop logo"></i>
        <!-- <img src="images/mouse-solid.png" alt="" class="logo" /> -->
        <h1>Login</h1>
        <p>Ingresa a tu cuenta</p>
        <form action="Login" method="post">
            <div class="user-icon">
                <input type="text" name="user" value="" placeholder="Usuario"/>
                <i class="fas fa-user icon"></i>
            </div>
            <div class="pass-icon">
                <input
                        type="password"
                        name="password"
                        value=""
                        placeholder="Contraseña"
                />
                <i class="fas fa-key icon"></i>
            </div>
            <div><p class="error-message" style="color:red; font-size:12px;"><%=request.getAttribute("error") != null ? request.getAttribute("error") : ""%></p>
            <p class="error-message" style="color:green; font-size:12px;"><%=request.getAttribute("success") != null ? request.getAttribute("success") : ""%></p></div>
            <input type="submit" name="login-acc" value="Ingresar"/>
            <p class="recover-password">
                <a href="recover.jsp" class="recover-password"
                >Olvidé mi contraseña</a
                >
            </p>

            <input
                    type="submit"
                    name="new-acc"
                    value="Solicitar nueva cuenta"
                    class="register-acc"
            />
        </form>
    </div>
</div>
</body>
</html>

