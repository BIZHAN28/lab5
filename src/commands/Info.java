package commands;

import managers.Receiver;

public class Info implements Command{
    private final Receiver receiver;
    public Info(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) {
        receiver.info(args);
    }
}
