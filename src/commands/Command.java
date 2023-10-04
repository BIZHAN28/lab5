package commands;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface Command {
    void execute(String[] args) throws XMLStreamException, IOException;
}
