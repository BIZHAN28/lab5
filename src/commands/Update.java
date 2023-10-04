package commands;

import managers.Receiver;

public class Update implements Command{
    private final Receiver receiver;

    public Update(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) {
        receiver.update(args);
    }
}
