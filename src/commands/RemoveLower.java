package commands;

import managers.Receiver;

public class RemoveLower implements Command{
    private final Receiver receiver;
    public RemoveLower(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) {
        receiver.removeLower(args);
    }
}
