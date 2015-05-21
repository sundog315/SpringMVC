package com.zhlands.honey;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DatabaseContextListener implements ServletContextListener
{

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        System.out.println("ConsumerProject has been closed");
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        System.out.println("ConsumerProject has been started");
    }
}
