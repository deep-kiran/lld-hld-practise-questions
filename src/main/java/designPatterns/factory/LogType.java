package designPatterns.factory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;


@Getter
public enum LogType {
    FILE_SYSTEM(() -> new FileLoggerImpl()),
    CONSOLE_SYSTEM(() -> new ConsoleLoggerImpl());

    LogType(Supplier<ILogger> constructor){
        this.constructor = constructor;
    }
    private final Supplier<ILogger> constructor;
}

