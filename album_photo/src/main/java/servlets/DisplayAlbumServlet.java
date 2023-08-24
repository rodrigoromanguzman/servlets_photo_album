/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.PhotoAlbum;


@WebServlet(name = "DisplayAlbumServlet", urlPatterns = {"/DisplayAlbumServlet"})
@MultipartConfig()
public class DisplayAlbumServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         ServletContext servletContext = request.getServletContext();
         PhotoAlbum photoAlbum = PhotoAlbum.getPhotoAlbum(servletContext);
        if(request.getContentType()!=null && request.getContentType().contains("multipart/form-data")){
            this.uploadPhoto(request,photoAlbum);
        } 
        
        PrintWriter out = response.getWriter();
        out.print("<html>");
            out.print("<body>");
                out.print("<h3 align = 'center'>Album de Fotos</h3>");
                this.displayAlbum(photoAlbum, out);
            out.print("</body>");
        out.print("</html>");
        
    }
    
    private void displayAlbum(PhotoAlbum photoAlbum, PrintWriter out){
        out.print("<table align = 'center'>");
            out.print("<tr>");
                    for(int j =0; j<photoAlbum.getPhotosCount();j++){
                      out.print("<td>");
                       out.print("<a href = './DisplayPhotoServlet?photo="+j+"'>");
                        out.print("<img src ='./DisplayPhotoServlet?photo="+j+"' width='120' height='120' >");
                       out.print("</a>");
                      out.print("</td>");

                    }
                
                out.print("<td bgcolor = '#cccccc' width ='120' height = '120'>");
                    out.print("<form action ='DisplayAlbumServlet' method = 'post' enctype='multipart/form-data'>");
                        out.print("<input type = 'file' value = 'Selecciona Foto' name ='photo' accept = 'image/jpeg'>");
                        out.print("<button type = 'submit/'>Guardar</button>");
                    out.print("</form>");
                out.print("</td>");
            out.print("</tr>");
            
            out.print("<tr>");
                for(int j=0;j<photoAlbum.getPhotosCount();j++){
                    out.print("<td align = 'center'>");
                        out.print("<h3>"+photoAlbum.getName(j)+"</h3>");
                    out.print("</td>");
                }
            out.print("</tr>");
            
             out.print("<tr>");
                for(int j=0;j<photoAlbum.getPhotosCount();j++){
                    out.print("<td align='center'>");
                        out.print("<a href ='DeletePhotoServlet?photo="+j+"'>"+"Quitar foto"+"</a>");
                    out.print("</td>");
                }
            out.print("</tr>");
            
        out.print("</table>");
    }
    
    private void uploadPhoto(HttpServletRequest request, PhotoAlbum photoAlbum) throws IOException, ServletException{
        ByteArrayOutputStream bae = new ByteArrayOutputStream();
        String fileName = null;
        for(Part p:request.getParts()){
            fileName = p.getSubmittedFileName();
            this.copyBytes(p.getInputStream(),bae);
        }
        if(fileName != ""){
            photoAlbum.addPhoto(bae.toByteArray(), fileName);
        }
    }
    
    private void copyBytes(InputStream is,OutputStream os) throws IOException{
        int i;
        while((i=is.read())!=-1){
            os.write(i);
        }
        is.close();
        os.close();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
