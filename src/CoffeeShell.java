import java.io.*;
import java.lang.ProcessBuilder;
import java.util.Arrays;
import java.util.List;

public class CoffeeShell {

    public static void main(String[] args) throws java.io.IOException {
        String commandLine;
        BufferedReader console = new BufferedReader(
            new InputStreamReader(System.in)
        );

        // Break out with <ctrl><C>
        while (true) {
            // Read user input
            System.out.print("CoffeeShell $ ");
            commandLine = console.readLine();

            if (!commandLine.equals("")) {
                /*
                    (1) parse the input to obtain the command and any parameters
                    (2) create a ProcessBuilder object
                    (3) start the process
                    (4) obtain the input stream
                    (5) output the contents returned by the command
                */
                List<String> parameters = Arrays.asList(commandLine.split(" "));
                try {
                    // Creating a new process with user-provided parameters
                    ProcessBuilder procBuilder = new ProcessBuilder(parameters);
                    Process proc = procBuilder.start();
                    // Obtaining input stream
                    InputStream inputStream = proc.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(
                        inputStream
                    );
                    BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader
                    );

                    // Output the process output
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        System.out.println(line);
                        line = bufferedReader.readLine();
                    }
                    bufferedReader.close();
                } catch (java.io.IOException e) {
                    System.out.println(
                        "Command " + parameters.get(0) + " not found!"
                    );
                }
            }
        }
    }
}
