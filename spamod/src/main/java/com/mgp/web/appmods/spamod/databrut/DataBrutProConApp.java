package com.mgp.web.appmods.spamod.databrut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.stream.Collectors;

//@SpringBootApplication
public class DataBrutProConApp {

    @Autowired
    static
    DataBrutGenerator dataBrutGenerator;

    @Autowired
    static DataBrutProducer producer;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DataBrutProConApp.class);
        DataBrutProducer producer = context.getBean(DataBrutProducer.class);

        // get the message fromf file
        var data = dataBrutGenerator.getDataFromFiles();
        for(String d : data ) producer.produce(d);

    }




}
