package commands;

import managers.Receiver;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class ExecuteScript implements Command{
    private final Receiver receiver;
    public ExecuteScript(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) throws IOException, XMLStreamException {
        receiver.executeScript(args);
    }
}
