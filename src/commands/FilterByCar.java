package commands;

import managers.Receiver;

public class FilterByCar implements Command{
    private final Receiver receiver;
    public FilterByCar(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String [] args) {
        receiver.filterByCar(args);
    }
}
