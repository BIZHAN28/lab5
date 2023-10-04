package commands;

import managers.Receiver;

public class PrintUniqueCar implements Command{
    private final Receiver receiver;
    public PrintUniqueCar(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) {
        receiver.printUniqueCar(args);
    }
}
