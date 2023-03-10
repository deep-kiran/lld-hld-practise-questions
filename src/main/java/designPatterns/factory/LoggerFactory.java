package designPatterns.factory;

import lombok.NonNull;

import java.util.Objects;

public class LoggerFactory {
    private static ILogger ilogger;
    public static ILogger getLogger(@NonNull final LogType logType){
        if(Objects.isNull(ilogger)){
            ilogger = logType.getConstructor().get();
        }
        return ilogger;
    }
}
