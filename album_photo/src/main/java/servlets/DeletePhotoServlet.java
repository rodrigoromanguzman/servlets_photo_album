/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.PhotoAlbum;

/**
 *
 * @author rodrigoroman
 */
@WebServlet(name = "DeletePhotoServlet", urlPatterns = {"/DeletePhotoServlet"})
public class DeletePhotoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String index = request.getParameter("photo");
        int i = Integer.parseInt(index);
        ServletContext servletContext = request.getServletContext();
        PhotoAlbum photoAlbum = PhotoAlbum.getPhotoAlbum(servletContext);
        photoAlbum.removePhoto(i);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("DisplayAlbumServlet");
        requestDispatcher.forward(request, response);
    }
}
