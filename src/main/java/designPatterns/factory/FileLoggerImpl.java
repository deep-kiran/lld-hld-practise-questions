package designPatterns.factory;

public class FileLoggerImpl implements ILogger {
    private static final String FILE_WRITE = "FILE_WRITE";

    @Override
    public void write(String message) {
        System.out.println(FILE_WRITE + " : " + message);
    }
}
