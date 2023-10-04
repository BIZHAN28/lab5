package commands;

import managers.Receiver;

public class Exit implements Command{
    private final Receiver receiver;
    public Exit(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) {
        receiver.exit(args);
    }
}
