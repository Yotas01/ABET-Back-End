package edu.javeriana.abetbackend.UseCases.FileSystem.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.script.ScriptException;
import java.io.*;
import java.util.UUID;

@Service
public class ExcelScriptService {

    public void createCoursesFromExcel(MultipartFile file, String path, Integer semester) throws IOException, InterruptedException {
        String fullPath = path.concat(UUID.randomUUID() + ".xlsx");
        File createFile = new File(fullPath);
        file.transferTo(createFile);

        String script = "src/scripts/excelFiles/main.py";
        String args = " -p " + fullPath + " -ps psw -a localhost -s quality-system -sm" + semester;
        System.out.println("python " + script + args);
        ProcessBuilder processBuilder = new ProcessBuilder("python ", script, "-p", fullPath, "-ps", "psw", "-a", "localhost", "-s", "quality-system", "-sm", semester.toString());

        executePyScriptWithExcel(createFile, processBuilder);
    }

    public void createSectionsFromExcel(MultipartFile file, String path) throws IOException, InterruptedException {
        String fullPath = path.concat(UUID.randomUUID() + ".xlsx");
        File createFile = new File(fullPath);
        file.transferTo(createFile);

        String script = "src/scripts/excelFiles/secciones.py";
        String args = " -p " + fullPath + " -ps psw -a localhost -s quality-system";
        System.out.println("python " + script + args);
        ProcessBuilder processBuilder = new ProcessBuilder("python ", script, "-p", fullPath, "-ps", "psw", "-a", "localhost", "-s", "quality-system");

        executePyScriptWithExcel(createFile, processBuilder);
    }

    private void executePyScriptWithExcel(File createFile, ProcessBuilder processBuilder) throws IOException, InterruptedException {
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        if (process.exitValue() != 0) {
            Reader reader = new InputStreamReader(process.getErrorStream());
            int ch;
            while ((ch = reader.read()) != -1)
                System.out.print((char) ch);
            reader.close();
        }
        if(createFile.delete())
            System.out.println("The file was deleted");
        else
            System.out.println("The file was not deleted");
    }

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
