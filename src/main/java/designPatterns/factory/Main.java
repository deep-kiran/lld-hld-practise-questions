package designPatterns.factory;

import lombok.val;

public class Main {
    public static void main(String[] args) {
        val logger = LoggerFactory.getLogger(LogType.FILE_SYSTEM);
        logger.write("Message");
    }
}
