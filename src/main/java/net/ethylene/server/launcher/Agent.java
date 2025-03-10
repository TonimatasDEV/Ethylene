package net.ethylene.server.launcher;

import java.lang.instrument.Instrumentation;
import java.util.jar.JarFile;

public class Agent {
    public static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation instrumentation) {
        Agent.instrumentation = instrumentation;
    }

    public static void agentmain(String args, Instrumentation instrumentation) {
        Agent.instrumentation = instrumentation;
    }

    public static void appendJarFile(JarFile file) {
        if (instrumentation != null) {
            instrumentation.appendToSystemClassLoaderSearch(file);
        }
    }
}
