/**
 * Copyright (C) 2013 BrewTour
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * 
 */

package za.co.brewtour.server.spring;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import za.co.brewtour.server.dispatch.GetBeersHandler;
import za.co.brewtour.server.dispatch.SendTextToServerHandler;
import za.co.brewtour.shared.dispatch.GetBeersAction;
import za.co.brewtour.shared.dispatch.SendTextToServerAction;

import com.gwtplatform.dispatch.server.actionvalidator.ActionValidator;
import com.gwtplatform.dispatch.server.spring.HandlerModule;
import com.gwtplatform.dispatch.server.spring.LoggerFactoryBean;
import com.gwtplatform.dispatch.server.spring.actionvalidator.DefaultActionValidator;
import com.gwtplatform.dispatch.server.spring.configuration.DefaultModule;

/**
 * Module which binds the handlers and configurations.
 * 
 * @author Michael Bester
 * @author Ivan Fourie
 */
@Configuration
@Import(DefaultModule.class)
@ComponentScan(basePackages = "com.gwtplatform.dispatch.server.spring")
public class ServerModule extends HandlerModule {
   public ServerModule() {
   }

   @Bean
   public SendTextToServerHandler getSendTextToServerHandler() {
      return new SendTextToServerHandler();
   }

   @Bean
   public GetBeersHandler getBeersHandler() {
      return new GetBeersHandler();
   }

   @Bean
   public ActionValidator getDefaultActionValidator() {
      return new DefaultActionValidator();
   }

   @Bean
   public LoggerFactoryBean getLogger() {
      Logger logger = Logger.getAnonymousLogger();
      logger.setLevel(Level.FINEST);
      return new LoggerFactoryBean(logger);
   }

   @Override
   protected void configureHandlers() {
      bindHandler(SendTextToServerAction.class, SendTextToServerHandler.class);
      bindHandler(GetBeersAction.class, GetBeersHandler.class);
   }
}
