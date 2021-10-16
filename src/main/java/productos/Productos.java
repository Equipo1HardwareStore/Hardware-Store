package productos;

import utils.SessionChecker;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.*;

@WebServlet(name = "Productos", value = "/Productos")
@MultipartConfig
public class Productos extends SessionChecker {

    private static final long serialVersionUID = 1L;


    public Productos() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validate(request,response);

        ProductosDAO productosDAO = new ProductosDAO();

        if (request.getParameter("search")!=null) {
            int codigo_producto;

            codigo_producto = Integer.parseInt(request.getParameter("codigo_producto"));
            ProductosDTO productos = productosDAO.searchProducto(codigo_producto);

            if (productos!= null) {
                request.setAttribute("PRODUCT",productos);

            }else {
                request.setAttribute("RESULT","error-msg");
                request.setAttribute("MESSAGE","Producto no encontrado.");
            }
            request.getRequestDispatcher("WEB-INF/views/products.jsp").forward(request, response);
        }

        if (request.getParameter("update")!=null) {
            int codigo_producto;
            double  precio_venta;
            String nombre_producto;

            codigo_producto = Integer.parseInt(request.getParameter("codigo_producto"));
            precio_venta = Double.parseDouble(request.getParameter("precio_venta"));
            nombre_producto = request.getParameter("nombre_producto");


            ProductosDTO productos = new ProductosDTO();
            productos.setCodigo_producto(codigo_producto);
            productos.setPrecio_venta(precio_venta);
            productos.setNombre_producto(nombre_producto);

            if (productosDAO.updateProducto(productos)) {
                request.setAttribute("RESULT","success-msg");
                request.setAttribute("MESSAGE","Producto modificado con éxito.");

            }else {
                request.setAttribute("RESULT","error-msg");
                request.setAttribute("MESSAGE","Error al modificar el producto.");
            }
            request.getRequestDispatcher("WEB-INF/views/products.jsp").forward(request, response);

        }

        if (request.getParameter("upload")!=null) {
            Part archivo = request.getPart("archivo");
//            String URL = "C:/users/user/IdeaProjects/demo/Hardware-Store/src/main/webapp/documents/";
            String URL = "C:/productos/";
//            String URL = "D:/Programacion Web/Proyecto Ciclo 3/Hardware-Store/src/main/webapp/documents";

            if(archivo.getContentType().equals("application/vnd.ms-excel")) {
                try {
                    InputStream file= archivo.getInputStream();
                    File copia= new File(URL + "productos.csv");
                    FileOutputStream escribir= new FileOutputStream(copia);
                    int num=file.read();
                    while(num !=-1) {
                        escribir.write(num);
                        num=file.read();
                    }
                    file.close();
                    escribir.close();
                    System.out.println("Archivo copiado");

//                    if(productosDAO.uploadProducto(URL + "productos.csv")) {
                        if(productosDAO.uploadCSV(URL + "productos.csv")) {

//                        response.sendRedirect("products.jsp?men=Registro de productos exitoso");
                        request.setAttribute("RESULT","success-msg");
                        request.setAttribute("MESSAGE","Registor de productos exitoso.");
                    }else
                    {
//                        response.sendRedirect("products.jsp?men=Carga fallida de archivo, por falla en nombre");
                        request.setAttribute("RESULT","error-msg");
                        request.setAttribute("MESSAGE","Carga no exitosa por nombre de archivo incorrecto.");
                    }
                    request.getRequestDispatcher("WEB-INF/views/products.jsp").forward(request, response);
                }catch(Exception e) {
//                    JOptionPane.showMessageDialog(null, "Carga fallida de archivo por error de" +
//                            " formato: "+e);
//                    response.sendRedirect("products.jsp?men=Carga fallida de archivo por error de validacion: ");
                    request.setAttribute("RESULT","error-msg");
                    request.setAttribute("MESSAGE","Error al validar el archivo."+e);
                    request.getRequestDispatcher("WEB-INF/views/products.jsp").forward(request, response);
                }
            }else
            {
//                response.sendRedirect("products.jsp?men=Formato de Archivo no permitido");
                request.setAttribute("RESULT","error-msg");
                request.setAttribute("MESSAGE","Formato de archivo no permitido.");
                request.getRequestDispatcher("WEB-INF/views/products.jsp").forward(request, response);
            }
        }
    }
}
