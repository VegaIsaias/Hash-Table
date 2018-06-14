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
    
    // Executes input instructions
    public static void executeInput(ArrayList<Node> input, ArrayList<List> hashTable) {
        try {
            String command;
            hash hashing = null;
            int key;
            
            // Read all input and execute per command
            for (int x = 0; x < input.size(); x++) {
                
                command = input.get(x).getCommand();
                
                // Command cases
                switch(command) {
                    case "i":   // Insert to hash table
                        hashing = new hash(input.get(x).name, hashTable.size());
                        key = hashing.genHash(input.get(x).name, hashTable.size()); 
                        
                        
                        hashTable.get(key).insertAtBack(input.get(x).name); // unchecked type
                        break;
                    case "s":   // Search for name in hash table
                        hashing = new hash(input.get(x).name, hashTable.size());
                        key = hashing.genHash(input.get(x).name, hashTable.size());
                        hashTable.get(key).search(input.get(x).name, key);
                        break;
                    case "d":   // Delete name from hash table
                        hashing = new hash(input.get(x).name, hashTable.size());
                        key = hashing.genHash(input.get(x).name, hashTable.size());
                        if (hashTable.get(key).isEmpty()) {
                            System.out.println(input.get(key).name + " not found - not deleted.");
                        } else {
                            hashTable.get(key).deleteName(input.get(x).name, key);
                        }
                        break;
                    case "p":   // Print list in hash table
                        System.out.println("The Hash Table contains:");
                        for (int y = 0; y < hashTable.size(); y++) {
                            hashTable.get(y).print(y);
                        }
                        break;
                    case "q":    // EXIT PROGRAM
                        return;
                }
                    
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Data Node // Used for storing the command input
    static class Node {
        
        private String command;
        private String name;
        private Node next;

        public Node(String comm, String name) {
            this.command = comm;
            this.name = name;
            this.next = null;
        }
        
        public Node(String command) {
            this.command = command;
            this.next = null;
        }
        
        public String getCommand() { return command; }
        public String getName() { return name; }
        public Node getNext() { return next; }
        public void setNext(Node next) { this.next = next; }
    }
    
    // Generate hashKey
    static class hash {
        String name;
        int hashKey;
        int size;
        
        public hash(String name, int size) {
            this.name = name;
            this.size = size;
        }
        
        // Horners Rule Algo 
        public static int genHash(String name, int size) {
            int hash = 0;
            char[] broken = name.toCharArray();
            for (int x = 0; x < name.length(); x++) {
                hash = ((hash * 27) + ((int)broken[x] - 96)) % size;
            }
            return hash;
        }
    }

    
}






