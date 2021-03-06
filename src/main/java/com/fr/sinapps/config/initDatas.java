package com.fr.sinapps.config;

import com.fr.sinapps.model.CarPark;
import com.fr.sinapps.repository.CarParkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Configuration
public class initDatas {

    // Error on carParkRepository for intellij ide. But when we build the project the repository is founded by the app
    @Bean
    CommandLineRunner fillCarParkList(CarParkRepository carParkRepository) {

        return args -> {

            // Fill the datas from an xml url
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder docBuilder;
            URL url;
            InputStream stream;
            Document doc = null;
            try {
                url = new URL("http://data.lacub.fr/wfs?key=9Y2RU3FTE8&SERVICE=WFS&VERSION=1.1.0&REQUEST=GetFeature&TYPENAME=ST_PARK_P&SRSNAME=EPSG:4326");
                docBuilder = dbf.newDocumentBuilder();
                stream = url.openStream();
                doc = docBuilder.parse(stream);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }

            if (doc != null) {
                addCarParkObjectFromNode(doc.getElementsByTagName("gml:featureMember"), carParkRepository);
            } else {
                System.out.println(" The document is null");
            }
        };

        /* Display all datas

        int counter =0;
        for(CarPark cp : carParks) {
            counter++;
            System.out.println("-------------"+counter+"\n"+cp.toString()+"\n");
        }*/
    }

    private static void addCarParkObjectFromNode(NodeList list, CarParkRepository carParkRepository) {

        for (int temp = 0; temp < list.getLength(); temp++) {

            Node node = list.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                CarPark currentCarPark = new CarPark();
                Element element = (Element) node;

                // get attributes and set it to current object. Checking if isn't not empty
                String currentAttribute = element.getElementsByTagName("gml:pos").item(0).getTextContent();
                currentCarPark.setPos(currentAttribute.isEmpty() ? "No data" : currentAttribute);
                currentAttribute = element.getElementsByTagName("bm:IDENT").item(0).getTextContent();
                currentCarPark.setIdent(currentAttribute.isEmpty() ? "No data" : currentAttribute);
                currentAttribute = element.getElementsByTagName("bm:NOM").item(0).getTextContent();
                currentCarPark.setName(currentAttribute.isEmpty() ? "No data" : currentAttribute);
                currentAttribute = element.getElementsByTagName("bm:ADRESSE").item(0).getTextContent();
                currentCarPark.setAddress(currentAttribute.isEmpty() ? "No data" : currentAttribute);
                currentAttribute = element.getElementsByTagName("bm:ETAT").item(0).getTextContent();
                currentCarPark.setStatus(currentAttribute.isEmpty() ? "No data" : currentAttribute);
                currentAttribute = element.getElementsByTagName("bm:URL").item(0).getTextContent();
                currentCarPark.setUrl(currentAttribute.isEmpty() ? "No data" : currentAttribute);
                currentAttribute = element.getElementsByTagName("bm:INFOR").item(0).getTextContent();
                currentCarPark.setInfo(currentAttribute.isEmpty() ? "No data" : currentAttribute);
                currentAttribute = element.getElementsByTagName("bm:GID").item(0).getTextContent();
                currentCarPark.setId(Long.parseLong(currentAttribute.isEmpty() ? "-1" : currentAttribute));
                currentAttribute = element.getElementsByTagName("bm:LIBRES").item(0).getTextContent();
                currentCarPark.setFreeSpot(Integer.parseInt(currentAttribute.isEmpty() ? "-1" : currentAttribute));
                currentAttribute = element.getElementsByTagName("bm:TOTAL").item(0).getTextContent();
                currentCarPark.setTotalSpot(Integer.parseInt(currentAttribute.isEmpty() ? "-1" : currentAttribute));
                currentAttribute = element.getElementsByTagName("bm:NP_COVOIT").item(0).getTextContent();
                currentCarPark.setCarpollingSpot(Integer.parseInt(currentAttribute.isEmpty() ? "-1" : currentAttribute));
                currentAttribute = element.getElementsByTagName("bm:NP_VELTOT").item(0).getTextContent();
                currentCarPark.setBikeSpot(Integer.parseInt(currentAttribute.isEmpty() ? "-1" : currentAttribute));

                carParkRepository.save(currentCarPark);
            }
        }
    }
}
