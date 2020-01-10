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
    public Process startAndWait() throws IOException, InterruptedException {
        redirectErrorStream(true);
        Process process = start();
        BufferedReader outReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String outLine;
        String errorLine;
        while((outLine = outReader.readLine()) != null && outPrintStream != null) outPrintStream.println(outLine);
        while((errorLine = errorReader.readLine()) != null && errorPrintStream != null)
            errorPrintStream.println(errorLine);
        outReader.close();
        errorReader.close();
        process.waitFor();
        return process;
    }

    public PrintStream getOutPrintStream() {
        return outPrintStream;
    }

    public PrintStream getErrorPrintStream() {
        return errorPrintStream;
    }
}
