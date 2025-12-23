package com.revature.lab;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class PipelineSimulation {

    public static void main(String[] args) {
        System.out.println("--- Starting Pipeline ---");

        // Stage 1: Build
        if (!runStage("Build", () -> checkSourceCode()))
            return;

        // Stage 2: Test
        if (!runStage("Test", () -> runRandomTests()))
            return;

        // Stage 3: Deploy
        if (!runStage("Deploy", () -> deployArtifact()))
            return;

        System.out.println("--- Pipeline SUCCESS ---");
    }

    private static boolean runStage(String name, Supplier<Boolean> task) {
        boolean success = task.get();

        if (!success) {
            System.out.println("Stage " + name + " FAILED");
        }

        return success;

    }

    // TODO: Implement helper methods

    interface Supplier<T> {
        T get();
    }

    private static boolean checkSourceCode() {
        // Simulate checking source code

        File sourceCode = new File("source_code.txt");
        if (!sourceCode.exists()) {
            System.out.println("Error");
            return false;
        }
        else{
            System.out.println("Compilation success");
            return true;
        }
    }

    private static boolean runRandomTests() {
        // Simulate running tests
        Random random = new Random();
        int passedTests = random.nextInt(2);
        if (passedTests == 0) {
            System.out.println("Tests failed");
            return false;
        } else {
            System.out.println("Tests passed");
            return true;
        }
    }


    private static boolean deployArtifact() {
        try {
            File artifact = new File("artifact.jar");
            artifact.createNewFile();
            System.out.println("Artifact packaged");
        } catch (IOException e) {
            System.out.println("Packaging failed");
            return false;
        }

        File artifact = new File("artifact.jar");
        File deployDir = new File("deploy");

        if (!deployDir.exists()) {
            deployDir.mkdir();
        }

        File deployedArtifact = new File(deployDir, "artifact.jar");

        if (artifact.renameTo(deployedArtifact)) {
            System.out.println("Artifact Deployed");
            return true;
        } else {
            System.out.println("Deployment Failed");
            return false;
        }
    }


}
