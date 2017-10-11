package com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import spoon.Launcher;

public class Main {

    public static void main(String[] args) throws IOException {
        Launcher launcher = new Launcher();
        launcher.getEnvironment().setSourceClasspath(new String[]{getAnnotationClasspath()});
        launcher.addInputResource(Paths.get(System.getProperty("user.dir")).resolve("com").toString());
        launcher.addProcessor(new AnnotationProcessor());
        launcher.setSourceOutputDirectory(Paths.get(System.getProperty("user.dir")).resolve("target").toString());
        launcher.run();
    }

    private static String getAnnotationClasspath() throws IOException {
        return Files.newDirectoryStream(
                Paths.get(System.getProperty("user.dir")).resolve("processor/target"),
                path -> path.toString().endsWith(".jar")
        )
                .iterator().next()
                .toString();
    }

}
