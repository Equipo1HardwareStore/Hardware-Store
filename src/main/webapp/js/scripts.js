var prods
var idCod = 1
$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "Ventas",
        dataType: "json",
        data: {
            option: "clientes"
        },
        success: function (clientes) {
            console.log(clientes);
            var lista = document.getElementById("listaClientes")
            console.log(lista)
            // lista.innerHTML=''
            for (let cliente of clientes) {
                lista.innerHTML += `<option value="${cliente.name}">${cliente.cedula}</option>`
            }
        }
    })
    $.ajax({
        type: "POST",
        url: "Ventas",
        dataType: "json",
        data: {
            option: "productos"
        },
        success: function (productos) {
            console.log(productos);
            prods = productos
            var listaProductos = document.getElementById("cod-prod")
            console.log(listaProductos)
            // listaProductos.innerHTML=''
            for (let producto of productos) {
                listaProductos.innerHTML += `<option value="${producto.nombre_producto}">${producto.codigo_producto}</option>`
            }
        }
    })
});

function populateCodes(id) {
    var listaProductos = document.getElementById("cod-prod" + id)
    console.log(listaProductos)
    // listaProductos.innerHTML=''
    for (let producto of prods) {
        listaProductos.innerHTML += `<option value="${producto.nombre_producto}">${producto.codigo_producto}</option>`
    }
}

$(".add-row").click(function () {
    var newRow =
        "<tr>" +
        "<td><select id='cod-prod" + idCod + "' class='cod-prod'><option selected hidden >Código</option></td>" +
        "<td><input type='text' placeholder='nombre producto' class='name-prod' readonly></td>" +
        "<td><input type='number' placeholder='cant.' class='cant-prod'></td>" +
        "<td><input type='number' placeholder='vlr. total' class='total-prod' readonly></td>" +
        "<td><button type='button' class='delete-row'><i class='fa fa-trash'></i></button></td>" +
        "</tr>";
    console.log(newRow);
    $("table tbody").append(newRow);
    populateCodes(idCod)
    idCod += 1
});

$("#tproducts").on("click", ".delete-row", function () {
    $(this).closest("tr").remove();
});

$("#listaClientes").change(function () {
    $("#name").val($(this).val())
})

$("body").on('change', ".cod-prod", function () {
    $(this).closest('tr').find('.name-prod').val($(this).val())
    $(this).closest('tr').find('.cant-prod').val('')
    $(this).closest('tr').find('.total-prod').val('')
});

$("body").on('change', ".cant-prod", function () {
    console.log("start total calculation")
    let cod = $(this).closest('tr').find('.cod-prod').children(':selected').text()
    console.log(cod)
    let prod = prods.find(p => p.codigo_producto == cod)
    console.log(prod)
    let price = prod.precio_venta
    let total = $(this).val() * price
    console.log(price, total)
    $(this).closest('tr').find('.total-prod').val(total)
})

$('#generate').click(function () {
    var productsTable = [];
    $("#tproducts")
        .find("tr")
        .each(function () {
            var tableRow = {};
            var jRow = $(this);
            tableRow.codigoProduto = jRow.find('.cod-prod').children(':selected').text();
            tableRow.cantidadProduto = jRow.find('.cant-prod').val();
            if (tableRow.cantidadProduto) productsTable.push(tableRow);
        });
    console.log(productsTable);

    var clienteCedula = $('#listaClientes').children(':selected').text();
    console.log(clienteCedula)

    $.ajax({
        type: "POST",
        url: "Ventas",
        dataType: "json",
        data: {
            option: "generate",
            productos: JSON.stringify(productsTable),
            clienteCedula: clienteCedula
        },
        success: function (data) {
            console.log(data)
            // console.log(typeof(data))
            $('#consec').val(data[2])
            $('#vlr').text(data[3])
            $('#iva').text(data[4])
            $('#total').text(data[5])
            $('#result-msg').addClass(data[0])
            $('#result-msg').text(data[1])

        }
    })
});

