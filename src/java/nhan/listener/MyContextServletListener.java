/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author ADMIN
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String siteMapsPath = context.getInitParameter("SITE_MAPS_PATH");
        Properties siteMaps = new Properties();
        InputStream is = null;
        try {
            is = context.getResourceAsStream(siteMapsPath);
            siteMaps.load(is);
            context.setAttribute("SITEMAPS", siteMaps);
        } catch (IOException ex) {
            context.log("MyContextServletListener _ IOE " + ex.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    context.log("MyContextServletListener _ IOE " + e.getMessage());
                }
            }
        }
        //Stirng txtFile = realPath + "WEB_INF/roadmap.txt";
        // read file and load in map
        // store attribute context scope
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application is deploying ....................");
    }
}
