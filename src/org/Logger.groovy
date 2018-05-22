package org.jenkins

class Logger {

    static void warn(message) {
        echo "WARN: $message"
    }

    static void info(message) {
        echo "INFO: $message"
    }
}
