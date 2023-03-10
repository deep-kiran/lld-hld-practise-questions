package designPatterns.factory;

public class ConsoleLoggerImpl implements ILogger{
    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
