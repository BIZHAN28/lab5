package commands;

import managers.Receiver;

public class Help implements Command{
    private final Receiver receiver;

    public Help(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute(String[] args) {
        receiver.help(args);
    }
}
