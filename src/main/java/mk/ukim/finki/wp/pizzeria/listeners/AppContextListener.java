package mk.ukim.finki.wp.pizzeria.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class AppContextListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(AppContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("[WP-Log] App Context Created");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("[WP-Log] App Context Destroyed");
    }
}
