package commands;

import managers.Receiver;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Save implements Command{
    private final Receiver receiver;
    public Save(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) throws XMLStreamException, IOException {
        receiver.save(args);
    }
}
