/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.PhotoAlbum;


@WebServlet(name = "DisplayPhotoServlet", urlPatterns = {"/DisplayPhotoServlet"})
public class DisplayPhotoServlet extends HttpServlet {


   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String index = request.getParameter("photo");
        int i = Integer.parseInt(index);
        try{
        ServletContext servletContext = request.getServletContext();
        PhotoAlbum photoAlbum = PhotoAlbum.getPhotoAlbum(servletContext);
        byte[] currentPhoto = photoAlbum.getPhoto(i);
        OutputStream out = response.getOutputStream();
        for(int b:currentPhoto){
            out.write(b);
        }
         out.close();
        }catch(Exception e){
            System.out.print("Error ---- ");
            System.out.print(e);
        }
    }

}
