/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.contam.controller.api;

import com.bugbusters.contam.business.Business;
import com.bugbusters.contam.business.BusinessDAOImpl;
import com.bugbusters.contam.location.FindMyLocation;
import com.bugbusters.contam.location.Ip;
import com.bugbusters.contam.location.ServerLocation;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Vasilis Naskos <http://vnaskos.com>
 */
@WebServlet(name = "SearchApiController", urlPatterns = {"/api/search"}, initParams = {
    @WebInitParam(name = "keyword", value = "business"),
    @WebInitParam(name = "x", value = "0.0"),
    @WebInitParam(name = "y", value = "0.0")})
public class SampleSearchApiController extends HttpServlet {

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
        response.setContentType("application/json");
         
        String clientIP = Ip.getClientPublicIP();
        ServerLocation location = FindMyLocation.getLocation(clientIP);
        
        String keyword = request.getParameter("keyword");
        double x = Double.parseDouble(location.getLatitude());
        double y = Double.parseDouble(location.getLongitude());

         BusinessDAOImpl businessDAO = new BusinessDAOImpl();
        List<Business> resutls = businessDAO.searchBusiness(keyword, x, y);



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
