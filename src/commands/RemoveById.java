package commands;

import managers.Receiver;

public class RemoveById implements Command{
    private final Receiver receiver;
    public RemoveById(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) {
        receiver.removeById(args);
    }
}
