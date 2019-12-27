package fr.il_totore.manadrop.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AsyncProcessBuilder {

    private ProcessBuilder processBuilder;

    public AsyncProcessBuilder(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
    }

    public ProcessBuilder getProcessBuilder() {
        return processBuilder;
    }

    public ProcessBuilder command(List<String> command) {
        return processBuilder.command(command);
    }

    public ProcessBuilder command(String... command) {
        return processBuilder.command(command);
    }

    public List<String> command() {
        return processBuilder.command();
    }

    public Map<String, String> environment() {
        return processBuilder.environment();
    }

    public File directory() {
        return processBuilder.directory();
    }

    public ProcessBuilder directory(File directory) {
        return processBuilder.directory(directory);
    }

    public ProcessBuilder redirectInput(ProcessBuilder.Redirect source) {
        return processBuilder.redirectInput(source);
    }

    public ProcessBuilder redirectOutput(ProcessBuilder.Redirect destination) {
        return processBuilder.redirectOutput(destination);
    }

    public ProcessBuilder redirectError(ProcessBuilder.Redirect destination) {
        return processBuilder.redirectError(destination);
    }

    public ProcessBuilder redirectInput(File file) {
        return processBuilder.redirectInput(file);
    }

    public ProcessBuilder redirectOutput(File file) {
        return processBuilder.redirectOutput(file);
    }

    public ProcessBuilder redirectError(File file) {
        return processBuilder.redirectError(file);
    }

    public ProcessBuilder.Redirect redirectInput() {
        return processBuilder.redirectInput();
    }

    public ProcessBuilder.Redirect redirectOutput() {
        return processBuilder.redirectOutput();
    }

    public ProcessBuilder.Redirect redirectError() {
        return processBuilder.redirectError();
    }

    public ProcessBuilder inheritIO() {
        return processBuilder.inheritIO();
    }

    public boolean redirectErrorStream() {
        return processBuilder.redirectErrorStream();
    }

    public ProcessBuilder redirectErrorStream(boolean redirectErrorStream) {
        return processBuilder.redirectErrorStream(redirectErrorStream);
    }

    public Process start() throws IOException {
        return processBuilder.start();
    }

    public Process startAndWait() throws IOException, InterruptedException {
        Process process = start();
        process.waitFor();
        return process;
    }

    public CompletableFuture<Process> startAsync() {
        return CompletableFuture.supplyAsync((ThrowingSupplier<Process>) this::startAndWait);
    }
}
