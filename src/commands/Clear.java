package commands;

import managers.Receiver;

public class Clear implements Command{
    private final Receiver receiver;
    public Clear(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) {
        receiver.clear(args);
    }
}
