package com.mgp.web.appmods.spamod.databrut;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.record.FileLogInputStream;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Component
public class DataBrutGenerator {


    @Value("${io.reflectoring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public KafkaTemplate<String, String>  kafkaTemplate () {
        return new KafkaTemplate<>(producerFactory());
    }


    private static final String  DATA_FILE_NAME= "electricityDataset.csv";
    //TODO: in main method fetch the csv data file every 2 seconds and read the file and produce the data to kafka
    // use java 17 features of reading and writing the date


    public  List<String> getDataFromFiles() {
        var records = new ArrayList<String>();
        try(Scanner sc = new Scanner(new File(DATA_FILE_NAME));) {
            while(sc.hasNextLine()) {
                records.addAll(getRecordFromDelim(sc.nextLine()));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return records;
    }

    private List<String> getRecordFromDelim(String line) {
        var vals = new ArrayList<String>();

        try(Scanner rowScan = new Scanner(line)){
            rowScan.useDelimiter(",");
            while(rowScan.hasNext()) {
                vals.add(rowScan.next());
            }
        }
        return vals;
    }



    public ProducerFactory<String, String> producerFactory (){
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                String.valueOf(StringSerializer.class));
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                String.valueOf(StringSerializer.class));
        return props;
    }
}
