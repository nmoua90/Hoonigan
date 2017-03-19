import java.awt.print.Book;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.io.*;
import java.util.List;

/**
 * Edited by Matthew on 3/19/2017.
 */
public class XMLParser {

    /**xmlReader() -
     * Parses an xml file.
     */

    //Would have the GUI call FileChooser() which would allow the user to select a file. That file's location gets fed
    //into xmlParser(File xmlFile).
    public static void xmlParser(File xmlFile){
        try{

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            org.w3c.dom.Document document = documentBuilder.parse(xmlFile);

            NodeList list = document.getElementsByTagName("Item");

            List<Library_Items> xmlLibraryItems = new ArrayList<Library_Items>();

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    if (element.getElementsByTagName("type").item(i).getTextContent() == "CD"){
                        CD cd = new CD(
                                //Create a new CD constructor for this xml order. This constructor only called for xml
                                //Same for all other Items
                                element.getElementsByTagName("type").item(i).getTextContent(),
                                element.getElementsByTagName("id").item(i).getTextContent(),
                                element.getElementsByTagName("Artist").item(i).getTextContent(),
                                element.getElementsByTagName("Name").item(i).getTextContent(),
                                true
                        );
                        xmlLibraryItems.add(cd);
                    }

                    else if (element.getElementsByTagName("type").item(i).getTextContent() == "DVD"){
                        DVD dvd = new DVD(
                                element.getElementsByTagName("type").item(i).getTextContent(),
                                element.getElementsByTagName("id").item(i).getTextContent(),
                                element.getElementsByTagName("Name").item(i).getTextContent(),
                                true
                        );
                        xmlLibraryItems.add(dvd);
                    }

                    else if (element.getElementsByTagName("type").item(i).getTextContent() == "MAGAZINE"){
                        Magazine magazine = new Magazine(
                                element.getElementsByTagName("type").item(i).getTextContent(),
                                element.getElementsByTagName("id").item(i).getTextContent(),
                                element.getElementsByTagName("Name").item(i).getTextContent(),
                                Integer.parseInt(element.getElementsByTagName("Volume").item(i).getTextContent())
                        );
                        xmlLibraryItems.add(magazine);
                    }

                    else if (element.getElementsByTagName("type").item(i).getTextContent() == "BOOK"){
                        Book book = new Book(
                                element.getElementsByTagName("type").item(i).getTextContent(),
                                element.getElementsByTagName("id").item(i).getTextContent(),
                                element.getElementsByTagName("Author").item(i).getTextContent(),
                                element.getElementsByTagName("Name").item(i).getTextContent(),
                                true
                        );
                        xmlLibraryItems.add(book);
                    }

                }
            }

            // at this point we have a list of xmlLibraryItems
            // print out all the xmlLibraryItems
//            for (Library_Items items : xmlLibraryItems) {
//                System.out.println("ID: " + items.getId());
//                System.out.println("Name: " + items.getName());
//                System.out.println("Surname: " + items.getSurname());
//                System.out.println("Age: " + items.getAge() + "\n");
//            }
        }catch(IOException i){
            i.printStackTrace();
        }catch(SAXException s){
            s.printStackTrace();
        }catch(ParserConfigurationException p){
            p.printStackTrace();
        }
    }//end of xmlParaser()

}//end of Driver class