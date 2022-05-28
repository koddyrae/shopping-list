/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Koddy Rae Madriaga
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DBoperations dbOps = new DBoperations();
        String action = request.getParameter("action");
        String productName = request.getParameter("productname");
        String productCost = request.getParameter("unitcost");
        String delete = request.getParameter("delete");
        
        if (delete != null ) {
            if (dbOps.deleteProduct(delete)) {
                request.setAttribute("message", "Product deleted");
                
            }
            else {
                request.setAttribute("message", "Error deleting product");
            }
        }
        
        //it adds stuff but it doesnt show :(
        if(productName != null && productCost != null) {
            if (action != null) {
                if (action.equals("add")) {
                    if (dbOps.addProduct(productName, productCost)) {
                        request.setAttribute("message", "New product added");
                    }
                    else {
                        request.setAttribute("message", "Error adding product");
                    }
                }
            }
                    
        }
        request.setAttribute("productList", dbOps.getProducts());
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
