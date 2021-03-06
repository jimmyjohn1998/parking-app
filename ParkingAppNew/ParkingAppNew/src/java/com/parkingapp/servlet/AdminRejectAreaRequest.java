package com.parkingapp.servlet;

import com.parkingapp.bean.AreaAdminRegisterBean;
import com.parkingapp.mail.MailSender;
import com.parkingapp.manager.QueryManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminRejectAreaRequest extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            QueryManager manager = new QueryManager();
            int aproveAreaReq = manager.arejectAreaReq(request.getParameter("user"));
            if (aproveAreaReq > 0) {
                AreaAdminRegisterBean padd = manager.getParkingAdminDetails(request.getParameter("user"));
                new MailSender(padd.getEmail_id(),
                        "Hai " + padd.getUsername() + ". Your Request Rejected", "Parking Admin");
                response.sendRedirect("AdminViewAreaRequestServlet?status=true");
            } else {
                response.sendRedirect("AdminViewAreaRequestServlet?status=false");
            }
        }
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
