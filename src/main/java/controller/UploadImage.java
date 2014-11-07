/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

@ManagedBean 
@SessionScoped 
public class UploadImage {
    private Part file;

    /**
     * @return the file
     */
    public Part getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }
    
    public String upload() throws IOException { 
        InputStream inputStream = file.getInputStream(); 

        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Florian\\Documents\\NetBeansProjects\\PetCatalog\\web\\resources\\images\\upload\\" + getFileName(file));   

        byte[] buffer = new byte[4096]; 
 
        int bytesRead = 0;   
        while(true) { 
 
            bytesRead = inputStream.read(buffer);   
            if(bytesRead > 0) {   
                outputStream.write(buffer, 0, bytesRead);   
            }else {   
                break;   
            } 
 
        }   
        outputStream.close();   
        inputStream.close();   
        return "success"; 
    } 

    public String getFileName(Part part) { 
        for(String cd : part.getHeader("content­disposition").split(";")) { 
            if(cd.trim().startsWith("filename")) { 
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", ""); 
                return filename.substring(filename.lastIndexOf("/") + 1).substring(filename.lastIndexOf("\\") + 1); 
            } 
        } 
        return null; 
    }
}
