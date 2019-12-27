package fr.il_totore.manadrop.util;

import java.io.*;

public class LoggerProcessBuilder extends AsyncProcessBuilder {

    private PrintStream outPrintStream;
    private PrintStream errorPrintStream;

    public LoggerProcessBuilder(ProcessBuilder processBuilder, PrintStream outPrintStream, PrintStream errorPrintStream) {
        super(processBuilder);
        this.outPrintStream = outPrintStream;
        this.errorPrintStream = errorPrintStream;
    }

    @Override
    public Process startAndWait() throws IOException {
        Process process = start();
        BufferedReader outReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while(process.isAlive()) {
            String outLine = outReader.readLine();
            if(outLine != null) outPrintStream.println(outLine);
            String errorLine = errorReader.readLine();
            if(errorLine != null) errorPrintStream.println(errorLine);
        }
        return process;
    }

    public PrintStream getOutPrintStream() {
        return outPrintStream;
    }

    public PrintStream getErrorPrintStream() {
        return errorPrintStream;
    }
}
