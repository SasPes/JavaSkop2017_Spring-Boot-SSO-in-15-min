package com.saspes.admin.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ControllerHelper {
    
    public static void constructRedirectURL(String path, HttpServletRequest request, HttpServletResponse response) {
        String rUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(path).build().toUriString();
        String replaceFirst = rUrl.replaceFirst("/admin", "");
        try {
            response.sendRedirect(replaceFirst);
        } catch (IOException ex) {
            Logger.getLogger(ControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
