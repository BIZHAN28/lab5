package commands;

import managers.Receiver;

public class RemoveGreater implements Command{
    private final Receiver receiver;
    public RemoveGreater(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) {
        receiver.removeGreater(args);
    }
}
