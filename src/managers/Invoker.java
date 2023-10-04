package managers;

import commands.*;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.HashMap;

public class Invoker {
    private HashMap<String, Command> commandMap = new HashMap<>();
    public Invoker(CollectionManager collectionManager, InputHandler inputHandler, ScriptStack scriptStack){
        Receiver receiver = new Receiver(collectionManager,  inputHandler, scriptStack);
        commandMap.put("help", new Help(receiver));
        commandMap.put("info", new Info(receiver));
        commandMap.put("show", new Show(receiver));
        commandMap.put("add", new Add(receiver));
        commandMap.put("update", new Update(receiver));
        commandMap.put("remove_by_id", new RemoveById(receiver));
        commandMap.put("clear", new Clear(receiver));
        commandMap.put("save", new Save(receiver));
        commandMap.put("execute_script", new ExecuteScript(receiver));
        commandMap.put("exit", new Exit(receiver));
        commandMap.put("add_if_min", new AddIfMin(receiver));
        commandMap.put("remove_greater", new RemoveGreater(receiver));
        commandMap.put("remove_lower", new RemoveLower(receiver));
        commandMap.put("filter_by_car", new FilterByCar(receiver));
        commandMap.put("print_unique_car", new PrintUniqueCar(receiver));
        commandMap.put("print_field_descending_mood", new PrintFieldDescendingMood(receiver));
    }

    public void execute(String commandName, String[] args) throws XMLStreamException, IOException {
        Command command = commandMap.get(commandName);
        command.execute(args);
    }


}
