<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Inicio</title>
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
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet">
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
            <%--            <span class="exit"><a href="index.jsp">Salir</a></span>--%>
        </div>
    </div>
</header>

<div class="main">
    <div class="landing-content">
        <div class="left">
            <h1>HARDWARE STORE</h1>
            <p>Proyecto desarrollado en el tercer ciclo de Misión TIC 2022 por:
            </p>
            <div class="team">
                <div class="member pink marly"><span class="first">M</span><span class="second">arly Gallardo</span></div>
                <div class="member"><span class="first">C</span><span class="second">arlos García</span></div>
                <div class="member pink "><span class="first">C</span><span class="second">arlos Palacios</span></div>
                <div class="member pink "><span class="first">W</span><span class="second">illiam Erazo</span></div>
                <div class="member "><span class="first">S</span><span class="second">ebastián Castaño</span></div>
            </div>
            <a href="/login.jsp" class="btn-landing">Iniciar Sesión</a>
        </div>
        <div class="right">
            <img src="images/landing.jpg" alt="" width="600px">
            <div class="mintic">
                <div>
                    <span class="pink"><</span>Misi<span class="pink">ó</span>n
                </div>
                <div>
                    <span class="pink">TIC</span> 20<span class="pink">22</span>>
                </div>
            </div>
        </div>
    </div>

</div>
</div>
</body>
</html>

