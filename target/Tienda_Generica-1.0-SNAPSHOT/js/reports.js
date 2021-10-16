$(document).ready(function () {
    $("#valorFinal-box").hide()

    function reports_users() {
        $.ajax({
            url: "Reportes",
            type: "post",
            dataType: "json",
            data: {option: "users"},

            success: function (users) {
                var table = document.getElementById("tb-report")
                table.innerHTML = ""
                table.innerHTML += `<tr>
                    <th>Cedula</th>
                    <th>Email</th>
                    <th>Nombre</th>
                    <th>Usuario</th>
                </tr>`
                for (let user of users) {
                    table.innerHTML += `<tr>
                    <td>${user.cedula}</td>
                    <td>${user.email}</td>
                    <td>${user.name}</td>
                    <td>${user.user}</td>
                </tr>`
                }

            }
        })
    }

    function reports_clients() {
        $.ajax({
            url: "Reportes",
            type: "post",
            dataType: "json",
            data: {option: "clients"},

            success: function (clients) {
                var table = document.getElementById("tb-report")
                table.innerHTML = ""
                table.innerHTML += `<tr>
                    <th>Cedula</th>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Dirección</th>
                    <th>Teléfono</th>
                </tr>`
                for (let client of clients) {
                    table.innerHTML += `<tr>
                    <td>${client.cedula}</td>
                    <td>${client.name}</td>
                    <td>${client.email}</td>
                    <td>${client.adress}</td>
                    <td>${client.phone}</td>
                </tr>`
                }

            }
        })
    }

    function reports_sales() {
        $.ajax({
            url: "Reportes",
            type: "post",
            dataType: "json",
            data: {option: "sales"},

            success: function (sales) {
                console.log(sales)
                var table = document.getElementById("tb-report")
                table.innerHTML = ""
                table.innerHTML += `<tr>
                    <th>Cedula</th>
                    <th>Nombre</th>
                    <th>Valor Total Ventas</th>
                </tr>`
                var total = 0
                for (let sale of sales) {
                    table.innerHTML += `<tr>
                    <td>${sale.id}</td>
                    <td>${sale.name}</td>
                    <td style="text-align: right">$ ${sale.totalSales}</td>
                </tr>`
                    total += sale.totalSales
                    console.log(total)
                }
                $("#totalVentas").text("$ " + total.toFixed(2))

            }
        })
    }

    $("#btn-customers").on("click", function () {
        reports_clients()
        changeStyle()
        $("#tituloReporte").text("Listado de Clientes")
    })

    $("#btn-users").on("click", function () {
        reports_users()
        changeStyle()
        $("#tituloReporte").text("Listado de Usuarios")
    })

    $("#btn-sales").on("click", function () {
        reports_sales()
        changeStyle()
        $("#tb-report").css("width", "45%")
        $("#valorFinal-box").show()
        $("#tituloReporte").text("Reporte de Ventas")
    })

    function changeStyle() {
        $("i").css("font-size", "2em")
        $(".button-box").css({"gap": "0", "position": "absolute", "margin-top": "80vh"})
        $(".lb-list").hide()
        $("#valorFinal-box").hide()
    }
})