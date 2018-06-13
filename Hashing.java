import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Hashing {

    public static void main(String[] args) {
    	
    	// Terminal Input Setup
        if(args.length > 0) {
            
            // Parameters
            int size = Integer.parseInt(args[0]);
            File fileName = new File(args[1]);
            
            // Read input
            ArrayList<Node> input = new ArrayList<Node>();
            readList(fileName, input);
            
            // State output
            ComplexityIndicator();
            printList(input, fileName);
            System.out.println();
            
            // Hash Table setup  
            ArrayList<List> hTable = null;
            hTable = new ArrayList<List>();
            for (int x = 0; x < size; x++) {
                List<String> linked = null;
                linked = new List<>();
                hTable.add(linked);
            }
            
            executeInput(input, hTable);
        }   
    }

	// Complexity function
    public static void ComplexityIndicator() {
        String nid = "is352549;";
        double difficulty = 3.2;
        double duration = 21.5;
        System.err.println(nid + difficulty + ";" + duration + ";");
    }
    
    // Reads file at file, returns arrayList with Nodes(cmm, name) initiated //
    public static ArrayList readList(File file, ArrayList<Node> table) {
        
        try {
            Scanner scan = new Scanner(file);
            
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] command = line.split(" ");
                
                Node element = null;
                if (command.length > 1) {
                    element = new Node(command[0], command[1]);
                } else {
                    element = new Node(command[0]);
                }
                table.add(element);
            }
            scan.close();
            return table;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }
    
    // Prints list //
    public static void printList(ArrayList<Node> input, File file) {
        try {
            System.out.println(file + " contains:");
            for (int x = 0; x < input.size(); x++) {
                if (input.get(x).getName() == null) {
                   System.out.printf("%s", input.get(x).getCommand());
                } else {
                    System.out.printf("%s %s\n", input.get(x).getCommand(), input.get(x).getName());
                }
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}






