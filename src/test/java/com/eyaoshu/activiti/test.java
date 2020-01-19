package com.eyaoshu.activiti;

import com.eyaoshun.activiti.common.utils.SheetHandler;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.jws.Oneway;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;

/**
 * @author zmm
 * @date 2020/1/17
 */

@SpringBootTest
public class test {


    @Test
    public  void  consumer(){
        Properties p = new Properties();
        p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.23.76:9092");
        p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        p.put(ConsumerConfig.GROUP_ID_CONFIG, "duanjt_test");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(p);
        kafkaConsumer.subscribe(Arrays.asList("first"));// 订阅消息

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(String.format("topic:%s,offset:%d,消息:%s", //
                        record.topic(), record.offset(), record.value()));
            }
        }

    }

    /**
     *     从 excel 中读取  上万条数据 。
     * @throws OpenXML4JException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public  void    ReaderExcelData() throws OpenXML4JException, IOException, SAXException {

        String path="E:\\测试数据\\application.xlsx";
        try  {

            File file =  new File ( path);
            OPCPackage opcPackage=OPCPackage.open(file ,PackageAccess.READ);
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(
                    opcPackage);
            XSSFReader reader=new XSSFReader(opcPackage);


            StylesTable stylesTable = reader.getStylesTable();
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            XSSFSheetXMLHandler xssfSheetXMLHandler = new XSSFSheetXMLHandler(stylesTable,    strings,   new SheetHandler(),false);
            xmlReader.setContentHandler(xssfSheetXMLHandler );
            Iterator<InputStream> sheetsData = reader.getSheetsData();
           long start= System.currentTimeMillis();
            System.out.println("开始时间："+ System.currentTimeMillis());
            while (sheetsData.hasNext()){
                InputStream next = sheetsData.next();
                InputSource inputStream= new InputSource(next);
                xmlReader.parse(inputStream);
            }

            System.out.println("结束时间："+(System.currentTimeMillis()-start )  );
        }
        catch (Exception ex){


        }





    }
}
