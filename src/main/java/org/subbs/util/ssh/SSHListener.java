package org.subbs.util.ssh;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/18/19
 * Time: 9:27 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@WebListener
public class SSHListener implements ServletContextListener {
    private SSHConnection conexionssh;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Context initialized ... !");
        try {
            conexionssh = new SSHConnection();
        } catch (Throwable e) {
            e.printStackTrace(); // error connecting SSH server
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Context destroyed ... !");
        try {
            conexionssh.closeSSH(); // disconnect
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}