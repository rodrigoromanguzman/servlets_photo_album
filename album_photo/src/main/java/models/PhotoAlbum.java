/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.*;
import javax.servlet.ServletContext;

public class PhotoAlbum {
    public static String NAME_ATTRIBUTE = "photo_album";
    private List<byte[]> photos = new ArrayList<byte[]>();
    private List<String> names = new ArrayList<String>();
    
    public static PhotoAlbum getPhotoAlbum(ServletContext servletContext){
        if(servletContext.getAttribute(NAME_ATTRIBUTE)==null){
            servletContext.setAttribute(NAME_ATTRIBUTE, new PhotoAlbum());
        }
        return (PhotoAlbum) servletContext.getAttribute(NAME_ATTRIBUTE);
    }
    
    public synchronized void addPhoto(byte[] newPhoto, String newName){
        names.add(newName);
        photos.add(newPhoto);
    }
    
    public synchronized void removePhoto(int index){
        names.remove(index);
        photos.remove(index);
    }
    
    public synchronized byte[] getPhoto(int index){
        return photos.get(index);
    }
    
    public synchronized String getName(int index){
        return names.get(index);
    }
    
    public synchronized int getPhotosCount(){
        return photos.size();
    } 
}
