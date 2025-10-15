package command;

import client.ClientManager;
import console.ConsoleManager;
import console.ReaderWriter;

import jakarta.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;


public abstract class Base {
    ClientManager clientManager = new ClientManager();
    ReaderWriter readerWriter = new ConsoleManager();
    private Object argument;


    public Object getArgument() {
        return argument;
    }
    public void setArgument(Object argument) {
        this.argument = argument;
    }
    public abstract void execute(String[] args) throws JAXBException, IOException, FileNotFoundException;
    public abstract String getDescription();


    protected void validateArgsLength(String[] args, int expectedLength) {
        if (args.length != expectedLength) {
            throw new IllegalArgumentException("Неправильное количество аргументов");
        }
    }

}
