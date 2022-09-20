package edu.javeriana.abetbackend.UseCases.FileSystem.Services;


import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ExcelScriptService {

    public void runPython() throws IOException, InterruptedException, ScriptException {
        ProcessBuilder processBuilder = new ProcessBuilder("python", "src/scripts/excelFiles/return_all_courses.py");
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        int exitCode = process.waitFor();
        System.out.println(exitCode);
        if (process.exitValue() != 0) {
            System.out.println();
            InputStream errorStream = process.getErrorStream();
            System.out.println(errorStream);

        }



    }

}
