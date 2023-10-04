package managers;

import exceptions.NoneValueArgumentException;
import exceptions.RecursiveException;
import jakarta.xml.bind.JAXBException;
import models.Car;
import models.HumanBeing;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class Receiver {
    private final CollectionManager collectionManager;
    private final ScriptStack scriptStack;
    private final InputHandler inputHandler;
    public Receiver(CollectionManager collectionManager,  InputHandler inputHandler, ScriptStack scriptStack) {
        this.collectionManager = collectionManager;
        this.inputHandler = inputHandler;
        this.scriptStack = scriptStack;
    }

    public void help(String[] args){
        File file = new File("Commands.txt");
        try( Scanner scanner = new Scanner(file, String.valueOf(StandardCharsets.UTF_8))) {

            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void info(String[] args){
        LocalDateTime initTime = collectionManager.getCreationDate();
        System.out.println("Collection info");
        System.out.println("Type: " + collectionManager.getHumanBeings().getClass().getName());
        System.out.println("Size: " + collectionManager.getHumanBeings().size());
        System.out.println("Time: " + initTime);
    }
    public void show(String[] args){
        if (collectionManager.getHumanBeings().size() == 0){
            System.out.println("Collection is empty");
        }
        collectionManager.getHumanBeings().forEach((value) -> System.out.println(value.toString()));
    }

    public void add(String[] args){
        AskManager askManager = new AskManager(inputHandler);
        try {
            collectionManager.add((HumanBeing) askManager.ask(new HumanBeing()));
            System.out.println("Element was added");
        } catch (Exception ignored){}
    }
    public void update(String[] args){
        try {
            if (args.length == 0) {
                throw new NoneValueArgumentException();
            }
            int id = Integer.parseInt(args[0]);
            AskManager askManager = new AskManager(inputHandler);
            collectionManager.update((HumanBeing) askManager.ask(new HumanBeing()),id);
            System.out.println("Element was updated");
        } catch (NoneValueArgumentException e){
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Argument should be int");
        } catch (IllegalAccessException ignored) {}
    }
    public void removeById(String[] args){
        try {
            if (args.length == 0) {
                throw new NoneValueArgumentException();
            }
            int id = Integer.parseInt(args[0]);
            collectionManager.removeById(id);
            System.out.println("Element was removed");
        } catch (NoneValueArgumentException e){
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("Argument should be int");
        }
    }
    public void clear(String[] args){
        collectionManager.clear();
        System.out.println("Collection was cleared");
    }
    public void save(String[] args) {
        try {
            collectionManager.saveToXML();
            System.out.println("Collection was saved");
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void executeScript(String[] args) throws IOException, XMLStreamException {
        try {
            if (args.length == 0) {
                throw new NoneValueArgumentException();
            }
            File file = new File(args[0]);
            if (scriptStack.contains(args[0])) {
                throw new RecursiveException();
            }
            FileInput fileInput = new FileInput(file);
            Invoker invoker = new Invoker(collectionManager, fileInput, scriptStack);
            scriptStack.push(args[0]);

            while (fileInput.handleInput().hasNextLine()) {
                String s = fileInput.handleInput().nextLine();
                String[] spl = s.split(" ", 2);
                invoker.execute(spl[0], Arrays.copyOfRange(spl, 1, spl.length));
            }
            scriptStack.pop();
            System.out.println("Script " + args[0] + " was executed");
        } catch (RecursiveException | NoneValueArgumentException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
    public void exit(String[] args){
        System.exit(0);
    }
    public void addIfMin(String[] args){
        int lastSize = collectionManager.getHumanBeings().size();
        AskManager askManager = new AskManager(inputHandler);
        try {
            collectionManager.addIfMin((HumanBeing) askManager.ask(new HumanBeing()));
        } catch (Exception ignored){}
        if (lastSize != collectionManager.getHumanBeings().size()) {
            System.out.println("Element was added");
        } else {
            System.out.println("Element wasn't added");
        }
    }
    public void removeGreater(String[] args){
        int lastSize = collectionManager.getHumanBeings().size();
        AskManager askManager = new AskManager(inputHandler);
        try {
            collectionManager.removeGreater((HumanBeing) askManager.ask(new HumanBeing()));
        } catch (Exception ignored){}
        if (lastSize != collectionManager.getHumanBeings().size()) {
            System.out.println("Element was added");
        } else {
            System.out.println("Element wasn't added");
        }
    }
    public void removeLower(String[] args){
        int lastSize = collectionManager.getHumanBeings().size();
        AskManager askManager = new AskManager(inputHandler);
        try {
            collectionManager.removeLower((HumanBeing) askManager.ask(new HumanBeing()));
        } catch (Exception ignored){}
        if (lastSize != collectionManager.getHumanBeings().size()) {
            System.out.println("Element was added");
        } else {
            System.out.println("Element wasn't added");
        }
    }
    public void filterByCar(String[] args){
        if (collectionManager.getHumanBeings().size() == 0){
            System.out.println("Collection is empty");
        }
        try {
            collectionManager.filterByCar((Car)new AskManager(inputHandler).ask(new Car())).forEach((value) -> System.out.println(value.toString()));;
        } catch (IllegalAccessException ignored){}
    }
    public void printUniqueCar(String[] args){
        collectionManager.printUniqueCar();
    }
    public void printFieldDescendingMood(String[] args){
        collectionManager.printFieldDescendingMood();
    }

}
