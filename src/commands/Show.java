package commands;

import managers.Receiver;

public class Show implements Command{
    private final Receiver receiver;

    public Show(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) {
        receiver.show(args);
    }
}
