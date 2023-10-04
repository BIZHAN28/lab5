package commands;

import managers.Receiver;

public class Add implements Command{
    private final Receiver receiver;

    public Add(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) {
        receiver.add(args);
    }
}
