package managers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import models.Car;
import models.HumanBeing;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class CollectionManager {

    private static LinkedHashSet<HumanBeing> humanBeings;
    @XmlRootElement
    private static class HumanBeingCollection{
        @XmlElementWrapper
        LinkedHashSet<HumanBeing> humanBeings;
    }

    private final LocalDateTime creationDate;

    public CollectionManager() {
        humanBeings = new LinkedHashSet<HumanBeing>();
        creationDate = LocalDateTime.now();
    }

    public LocalDateTime getCreationDate(){
        return creationDate;
    }
    public LinkedHashSet<HumanBeing> getHumanBeings() {
        return humanBeings;
    }

    public void saveToXML() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(HumanBeingCollection.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        PrintWriter printWriter = new PrintWriter("output.xml");
        HumanBeingCollection humanBeingCollection = new HumanBeingCollection();
        humanBeingCollection.humanBeings = humanBeings;
        marshaller.marshal(humanBeingCollection, printWriter);
        printWriter.close();
    }
    public void loadFromXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(HumanBeingCollection.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File("output.xml");
            Scanner scanner = new Scanner(file);
            String xml = "";
            while (scanner.hasNextLine()) {
                xml += scanner.nextLine();
            }
            Reader reader = new StringReader(xml);
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader streamReader = inputFactory.createXMLStreamReader(reader);
            HumanBeingCollection humanBeingCollection = (HumanBeingCollection) unmarshaller.unmarshal(streamReader);
            humanBeings = humanBeingCollection.humanBeings;
        } catch (Exception e) {
            System.out.println("Can't load collection");
        }
    }
    public void add(HumanBeing humanBeing) {
        humanBeings.add(humanBeing);
    }
    public void update(HumanBeing humanBeing, int id){
        humanBeing.setId(id);
        LinkedHashSet<HumanBeing> newHumanBeings = new LinkedHashSet<HumanBeing>();
        for (HumanBeing humanBeingIterator : humanBeings){
            if (humanBeingIterator.getId() == id){
                newHumanBeings.add(humanBeing);
            } else {
                newHumanBeings.add(humanBeingIterator);
            }
        }
        humanBeings = newHumanBeings;
    }
    public void removeById(int id){
        HumanBeing humanBeing = this.getById(id);
        humanBeings.remove(humanBeing);
    }
    public HumanBeing getById(int id){
        for (HumanBeing humanBeing : humanBeings){
            if (humanBeing.getId() == id){
                return humanBeing;
            }
        }
        return null;
    }
    public void removeGreater(HumanBeing humanBeing){
        humanBeings.removeIf(iterHumanBeing -> iterHumanBeing.compareTo(humanBeing) > 0);
    }
    public void removeLower(HumanBeing humanBeing){
        humanBeings.removeIf(iterHumanBeing -> iterHumanBeing.compareTo(humanBeing) < 0);
    }
    public void clear(){
        humanBeings.clear();
    }
    public LinkedHashSet<HumanBeing> filterByCar(Car car){
        LinkedHashSet<HumanBeing> filteredHumanBeings = new LinkedHashSet<HumanBeing>(humanBeings);
        filteredHumanBeings.removeIf(humanBeing -> !(humanBeing.getCar().equals(car)));
        return filteredHumanBeings;
    }


    public static int generateId() {
        int id = 0;
        for (HumanBeing humanBeing : humanBeings) {
            if (humanBeing.getId() > id) {
                id = humanBeing.getId();
            }
        }
        return id + 1;
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public void addIfMin(HumanBeing humanBeing) {
        if (humanBeing.compareTo(getMinimumHumanBeing()) < 0) {
            humanBeings.add(humanBeing);
        }
    }
    public HumanBeing getMinimumHumanBeing(){

        if (!humanBeings.isEmpty()) {
            HumanBeing min = humanBeings.iterator().next();
            for (HumanBeing humanBeing : humanBeings) {
                if (humanBeing.compareTo(min) < 0) {
                    min = humanBeing;
                }
            }
            return min;
        } else {
            return null;
        }
    }

    public void printUniqueCar() {
        LinkedHashSet<Car> cars = new LinkedHashSet<>();
        for (HumanBeing humanBeing : humanBeings) {
            cars.add(humanBeing.getCar());
        }
        if (cars.size() == 0) {
            System.out.println("Collection is empty");
        } else {
            System.out.println(cars);
        }
    }
    public LinkedHashSet<HumanBeing> sortByImpactSpeed() {
        LinkedHashSet<HumanBeing> sortedHumanBeings = new LinkedHashSet<>(humanBeings);
        for (HumanBeing humanBeing : sortedHumanBeings) {
            for (HumanBeing humanBeingIterator : sortedHumanBeings) {
                if (humanBeing.getImpactSpeed() > humanBeingIterator.getImpactSpeed()) {
                    sortedHumanBeings.add(humanBeing);
                    sortedHumanBeings.remove(humanBeingIterator);
                }
            }
        }
        return sortedHumanBeings;
    }
    public void printFieldDescendingMood() {
        if (sortByImpactSpeed().size() == 0){
            System.out.println("Collection is empty");
        }
        for (HumanBeing humanBeing : sortByImpactSpeed()){
            System.out.println(humanBeing.getMood());
        }
    }
}