package com.kosta.finalProject.controllers;



import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Controller
public class ExceptionHandlingController implements ErrorController {
 
    private static final String PATH = "/error";     
    
    
    public String getErrorPath() {
        // TODO Auto-generated method stub
        return "error";
    }
    
    @RequestMapping(value = PATH)
    public String Error(HttpServletRequest req) {
        
        String status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) + "";
        log.info("Error !!!!!!!!!!!!!! info:" + status);
        
        switch(status) {
            case "500" : return "error/500"; 
            case "404" : return "error/404";
            default : return "error/error";
        }
        
    }
}