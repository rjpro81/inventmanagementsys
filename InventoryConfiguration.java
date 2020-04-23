package com.ralph.inventmanagementsys;

import org.springframework.context.annotation.Bean;

/**
 * This class is a SpringFramework configuration class for Bean components.
 * @author Ralph Julsaint
 */
public class InventoryConfiguration {
    @Bean
    Item getItem(){
        return new Item();
    }

    @Bean
    Activity getActivity(){
        return new Activity();
    }

    @Bean
    Customer getCustomer(){
        return new Customer();
    }

    @Bean
    Invoice getInvoice(){
        return new Invoice();
    }

    @Bean
    OrderTable getOrder(){
        return new OrderTable();
    }

    @Bean
    AppUser getUser(){
        return new AppUser();
    }   
    
    @Bean
    DatabaseManager getDatabaseManager(){
        final String url = "jdbc:derby://localhost:1527/inventory_management";
        return new DatabaseManager(url);
    }
}
