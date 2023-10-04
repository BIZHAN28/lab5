package commands;

import managers.Receiver;

public class PrintFieldDescendingMood implements Command{
    private Receiver receiver;
    public PrintFieldDescendingMood(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) {
        receiver.printFieldDescendingMood(args);
    }
}
