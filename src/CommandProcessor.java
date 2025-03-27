import java.util.ArrayList;

public class CommandProcessor {

    ArrayList<Command> builtinCommands;

    // Loop through list of builtin commmands. If a match is found, execute it.
    public int executeCommand(String[] args) {
        int status = -1; // Status -1 means command not found. No command should return this.
        for (int i = 0; i < builtinCommands.size(); i++) {
            if (args[0].equals(builtinCommands.get(i).name())) {
                status = builtinCommands.get(i).function(args);
            }
        }
        return status;
    }

    // Add all builtin commands to the processor when instantiated.
    public CommandProcessor() {
        builtinCommands = new ArrayList<Command>();
        builtinCommands.add(new cdCommand());
        builtinCommands.add(new exitCommand());
    }

    public class cdCommand implements Command {

        public String name() {
            return "cd";
        }

        public int function(String[] args) {
            if (args.length > 1) {
                System.out.println(args[1]);
            }
            return 0;
        }
    }

    public class exitCommand implements Command {
        public String name() {
            return "exit";
        }
        public int function(String[] args) {
            int status = 0;
            if (args.length > 1) {
                status = Integer.parseInt(args[2]);
            }
            System.out.println("Exiting with code " + status);
            System.exit(status);
            return status;
        }
    }
}
