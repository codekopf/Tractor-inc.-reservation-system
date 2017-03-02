package cz.ucl.hatchery.carevidence.web;

import java.io.FileNotFoundException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.Log4jConfigurer;

public class CarEvidenceContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(final ServletContextEvent event) {

		try {
			Log4jConfigurer.initLogging("classpath:car-evidence-log.xml");
		} catch (final FileNotFoundException e) {
			System.out.println(e);
		}

	}

	@Override
	public void contextDestroyed(final ServletContextEvent arg0) {
	}

}
