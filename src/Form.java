/*
 * Created by Alexey Yarkov on 25.10.17
 * Copyright © 2017 Alexey Yarkov. All rights reserved.
 */

import javax.swing.*;
import java.awt.event.*;

import org.w3c.dom.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import java.io.*;

import org.xml.sax.*;

public class Form extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textAreaOut;
    private JButton buttonAdd;
    private JTextArea textAreaIn;
    private JButton buttonEdit;
    private JButton buttonFind;
    private JButton buttonDel;
    private JButton buttonSort;
    private JButton buttonCheckDTD;
    private JButton buttonCreateHTML;
    private JButton buttonCreateXML;
    private JButton buttonCheckXSD;
    private JButton buttonRead;

    private CarShop A;
    private Car X;

    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;

    public Form() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        A = new CarShop();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Buffer = textAreaIn.getText().trim();
                X = new Car();
                int space;
                try {
                    for (int i = 0; i < 6; i++) {
                        space = Buffer.indexOf(",");
                        switch (i) {
                            case 0: {
                                X.setID(Integer.parseInt(Buffer.substring(0, space).trim()));
                            }
                            break;
                            case 1: {
                                X.setModel(Buffer.substring(0, space).trim());
                            }
                            break;
                            case 2: {
                                X.setCountry(Buffer.substring(0, space).trim());
                            }
                            break;
                            case 3: {
                                X.setYear(Integer.parseInt(Buffer.substring(0, space).trim()));
                            }
                            break;
                            case 4: {
                                X.setV(Integer.parseInt(Buffer.substring(0, space).trim()));
                            }
                            break;
                            case 5: {
                                X.setPrice(Integer.parseInt(Buffer.trim()));
                            }
                            break;
                        }
                        Buffer = Buffer.substring(space + 1);

                    }
                    A.addCar(X);
                    textAreaOut.setText(A.toString());
                } catch (Exception a) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c,
                            "Ошбика при добавлении!\nФормат ввода данных:\nУникальный идентификатор, марка автомобиля, страна-производитель, год выпуска, объем двигателя, стоимость", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Buffer = textAreaIn.getText();
                X = new Car();
                int posID = 0;
                try {
                    for (int i = 0; i < 7; i++) {
                        int space = Buffer.indexOf(",");
                        switch (i) {
                            case 0: {
                                posID = Integer.parseInt(Buffer.substring(0, space).trim());
                            }
                            break;
                            case 1: {
                                X.setID(Integer.parseInt(Buffer.substring(0, space).trim()));
                            }
                            break;
                            case 2: {
                                X.setModel(Buffer.substring(0, space).trim());
                            }
                            break;
                            case 3: {
                                X.setCountry(Buffer.substring(0, space).trim());
                            }
                            break;
                            case 4: {
                                X.setYear(Integer.parseInt(Buffer.substring(0, space).trim()));
                            }
                            break;
                            case 5: {
                                X.setV(Integer.parseInt(Buffer.substring(0, space).trim()));
                            }
                            break;
                            case 6: {
                                X.setPrice(Integer.parseInt(Buffer.trim()));
                            }
                            break;
                        }
                        Buffer = Buffer.substring(space + 1);
                    }
                    A.editCarShop(posID, X);
                    textAreaOut.setText(A.toString());
                } catch (Exception a) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c,
                            "Ошбика при изменении!\nФормат ввода данных:\nПозиция изменения, новый уникальный идентификатор, новая марка автомобиля, новая страна-производитель, новый год выпуска, новый объем двигателя, новая стоимость", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Buffer = textAreaIn.getText();
                    int posID = Integer.parseInt(Buffer.trim());
                    textAreaOut.setText(A.toString() + "\nЭлемент " + posID + ": " + A.findCar(posID));
                } catch (Exception a) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c,
                            "Ошбика при поиске!\nФормат ввода данных:\nПозиция элемента", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Buffer = textAreaIn.getText();
                    int posID = Integer.parseInt(Buffer.trim());
                    A.delCar(posID);
                    textAreaOut.setText(A.toString());
                } catch (Exception a) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c,
                            "Ошбика при удалении!\nФормат ввода данных:\nПозиция элемента", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    A.bubbleSortCar();
                    textAreaOut.setText(A.toString());
                } catch (Exception a) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c,
                            "Ошбика при сортировке!\nДанные отсутсвуют", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dbf = DocumentBuilderFactory.newInstance();//создаём специальную фабрику паресеров
                    dbf.setValidating(true); //включаем проверку структуры документа
                    db = dbf.newDocumentBuilder();//создаём парсер для документа
                    db.setErrorHandler(new SimpleErrorHandler()); //создаём экземпляр обработчика
                    Document document = db.parse(new File("CR.xml")); //создавём документ на основе файла xml

                    NodeList carShop = document.getElementsByTagName("Car"); // Получаем корневой элемент
                    for (int i = 0; i < carShop.getLength(); i++) {
                        if (carShop.item(i).getNodeType() == Node.ELEMENT_NODE) {
                            X = new Car();
                            Element carShopElement = (Element) carShop.item(i);

                            NodeList car = carShopElement.getChildNodes();
                            for (int j = 0; j < car.getLength(); j++) {
                                if (car.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                    Element carElement = (Element) car.item(j);
                                    switch (carElement.getNodeName().trim()) {
                                        case "ID": {
                                            X.setID(Integer.parseInt(carElement.getTextContent().trim()));
                                        }
                                        break;
                                        case "Model": {
                                            X.setModel(carElement.getTextContent().trim());
                                        }
                                        break;
                                        case "Country": {
                                            X.setCountry(carElement.getTextContent().trim());
                                        }
                                        break;
                                        case "Year": {
                                            X.setYear(Integer.parseInt(carElement.getTextContent().trim()));
                                        }
                                        break;
                                        case "V": {
                                            X.setV(Integer.parseInt(carElement.getTextContent().trim()));
                                        }
                                        break;
                                        case "Price": {
                                            X.setPrice(Integer.parseInt(carElement.getTextContent().trim()));
                                        }
                                        break;
                                    }
                                }
                            }
                            A.addCar(X);
                        }
                    }
                    textAreaOut.setText(A.toString());
                } catch (ParserConfigurationException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при считывании XML-файла!\nОшибка конфигурации " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } catch (SAXException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при считывании XML-файла!\nОшибка SAX " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при считывании XML-файла!\nФайл не найден: " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCheckDTD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dbf = DocumentBuilderFactory.newInstance();//создаём специальную фабрику паресеров
                    dbf.setValidating(true); //включаем проверку структуры документа
                    db = dbf.newDocumentBuilder();//создаём парсер для документа
                    db.setErrorHandler(new SimpleErrorHandler()); //создаём экземпляр обработчика
                    Document document = db.parse(new File("CR.xml")); //создавём документ на основе файла xml
                } catch (ParserConfigurationException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при проверке XML-файла по DTD-схеме!\nОшибка конфигурации " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } catch (SAXException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при проверке XML-файла по DTD-схеме!\nОшибка SAX " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при проверке XML-файла по DTD-схеме!\nФайл не найден: " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCheckXSD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);//создаём фабрику схем
                    Schema s = sf.newSchema(new File("CarShop.xsd"));//создаём схему из xsd файла
                    dbf = DocumentBuilderFactory.newInstance();//создаём специальную фабрику паресеров
                    dbf.setValidating(false);
                    dbf.setSchema(s);//Привязываю схему к фабрике парсеров
                    db = dbf.newDocumentBuilder();//создаём парсер для документа
                    db.setErrorHandler(new SimpleErrorHandler()); //создаём экземпляр обработчика
                    Document document = db.parse(new File("CR.xml")); //создавём документ на основе файла xml
                } catch (ParserConfigurationException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при проверке XML-файла по XSD-схеме!\nОшибка конфигурации " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } catch (SAXException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при проверке XML-файла по XSD-схеме!\nОшибка SAX " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при проверке XML-файла по XSD-схеме!\nФайл не найден: " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCreateXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dbf = DocumentBuilderFactory.newInstance();//создаём специальную фабрику паресеров
                    db = dbf.newDocumentBuilder();//создаём парсер для нового чистого документа
                    Document doc = db.newDocument();
                    Car T[] = A.getCarShop();

                    Element carShop = doc.createElement("CarShop"); // Создаем корневой элемент CarShop
                    doc.appendChild(carShop); // Присоединяем корневой элемент CarShop

                    Element carProp[][] = new Element[A.getS()][6]; // Создаём массив автомобильный свойств
                    for (int i = 0; i < A.getS(); i++) {
                        Element car;
                        car = doc.createElement("Car"); // Создаем вложенный элемент car
                        carShop.appendChild(car); // Присоединяем вложенный элемент car
                        //Создаем и присоединяем вложенные элементы carProp
                        carProp[i][0] = doc.createElement("ID");
                        carProp[i][0].appendChild(doc.createTextNode(String.valueOf(T[i].getID())));
                        car.appendChild(carProp[i][0]);
                        carProp[i][1] = doc.createElement("Model");
                        carProp[i][1].appendChild(doc.createTextNode(String.valueOf(T[i].getModel())));
                        car.appendChild(carProp[i][1]);
                        carProp[i][2] = doc.createElement("Country");
                        carProp[i][2].appendChild(doc.createTextNode(String.valueOf(T[i].getCountry())));
                        car.appendChild(carProp[i][2]);
                        carProp[i][3] = doc.createElement("Year");
                        carProp[i][3].appendChild(doc.createTextNode(String.valueOf(T[i].getYear())));
                        car.appendChild(carProp[i][3]);
                        carProp[i][4] = doc.createElement("V");
                        carProp[i][4].appendChild(doc.createTextNode(String.valueOf(T[i].getV())));
                        car.appendChild(carProp[i][4]);
                        carProp[i][5] = doc.createElement("Price");
                        carProp[i][5].appendChild(doc.createTextNode(String.valueOf(T[i].getPrice())));
                        car.appendChild(carProp[i][5]);
                    }
                    Source domSource = new DOMSource(doc);
                    Result fileResult = new StreamResult(new File("CR_out.xml"));
                    TransformerFactory factory = TransformerFactory.newInstance();
                    Transformer transformer = factory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.transform(domSource, fileResult);

                } catch (ParserConfigurationException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при создании XML-файла по XSD-схеме!\nОшибка конфигурации " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } catch (TransformerConfigurationException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при создании XML-файла по XSD-схеме!\nОшибка конфигурации " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } catch (TransformerException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при создании XML-файла по XSD-схеме!\nОшибка конфигурации " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        buttonCreateHTML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TransformerFactory tFactory = TransformerFactory.newInstance();
                    Source xslDoc = new StreamSource("CarShop.xsl");
                    Source xmlDoc = new StreamSource("CR_out.xml");
                    String outputFileName = "CR_out.html";
                    OutputStream htmlFile = new FileOutputStream(outputFileName);
                    Transformer trasform = tFactory.newTransformer(xslDoc);
                    trasform.transform(xmlDoc, new StreamResult(htmlFile));
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "HTML-файл успешно создан!", "Уведомление", JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при создании HTML-файла!\nXML-файл не найден: " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } catch (TransformerConfigurationException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при создании HTML-файла!\nКонфигурация шаблона некорректна: " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } catch (TransformerFactoryConfigurationError ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при создании HTML-файла!\nКонфигурация шаблона некорректна: " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } catch (TransformerException ex) {
                    JFrame c = new JFrame();
                    JOptionPane.showMessageDialog(c, "Ошибка при создании HTML-файла!\nСбой конвертирования: " + ex.getMessage() + "\n", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        Form dialog = new Form();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    static class SimpleErrorHandler implements ErrorHandler {
        JFrame c;

        // метод для обработки предупреждений
        public void warning(SAXParseException e) throws SAXException {
            c = new JFrame();
            JOptionPane.showMessageDialog(c, "Строка " + e.getLineNumber() + ": " + e.getMessage(), "Предупреждение", JOptionPane.WARNING_MESSAGE);
        }

        // метод для обработки ошибок
        public void error(SAXParseException e) throws SAXException {
            c = new JFrame();
            JOptionPane.showMessageDialog(c, "Строка " + e.getLineNumber() + ": " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

        // метод для обработки критических ошибок
        public void fatalError(SAXParseException e) throws SAXException {
            c = new JFrame();
            JOptionPane.showMessageDialog(c, "Строка " + e.getLineNumber() + ": " + e.getMessage(), "Критическая ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
