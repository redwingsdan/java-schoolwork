package com.tags;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import app.RegisterBean;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author djp217
 */
public class NewTagHandler extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    private RegisterBean bean = null;
    
    public void setBean(RegisterBean bean){
        this.bean = bean;
    }
    public RegisterBean getBean(){
        return bean;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            //out.println("Start of Custom Tag");
            Class b = null;
            try {
                b = Class.forName("app.RegisterBean");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewTagHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            Field[] fields = b.getDeclaredFields();
            for (Field f : fields) {
                String name = f.getName();
                out.println("<tr><td class=\"key\">");
                String camelName = "get" +
                (name.substring(0,1).toUpperCase() + name.substring(1));
                Method m = null;
                try {
                    m = b.getMethod(camelName);
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(NewTagHandler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(NewTagHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                out.println(name + "</td><td class=\"value\">");
                try {
                    out.println(m.invoke(bean));
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(NewTagHandler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(NewTagHandler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(NewTagHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                out.println("</td></tr>");
            }
   

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in NewTagHandler tag", ex);
        }
    }
    
}
