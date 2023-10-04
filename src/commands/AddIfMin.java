package commands;

import managers.Receiver;

public class AddIfMin implements Command{
    private final Receiver commandReceiver;
        public AddIfMin(Receiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    @Override
    public void execute(String[] args) {
        commandReceiver.addIfMin(args);
    }
}
