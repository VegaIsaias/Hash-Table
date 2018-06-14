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

    
    // Class represents one node in list
    static class ListNode<T> {
        // package access members; List can access these directly 
        T data; 
        ListNode<T> nextNode; 

        // constructor creates a ListNode that refers to object
        ListNode(T object) { this(object, null); }  

        // constructor creates ListNode that refers to the specified
        // object and to the next ListNode
        ListNode(T object, ListNode<T> node) {
            data = object;    
            nextNode = node;  
        } 

        // return reference to data in node
        T getData() { return data;} 

        // return reference to next node in list
        ListNode<T> getNext() { return nextNode; } 
   
    } 

    // class List definition
    public static class List<T> {
        private ListNode<T> firstNode;
        private ListNode<T> lastNode;
        private String name; 

        // constructor creates empty List with "list" as the name
        public List() { this("list"); } 

        // constructor creates an empty List with a name
        public List(String listName) {
        name = listName;
        firstNode = lastNode = null;
        } 

        // insert item at front of List
        public void insertAtFront(T insertItem) {
            if (isEmpty()) 
                firstNode = lastNode = new ListNode<T>(insertItem);
            else // firstNode refers to new node
                firstNode = new ListNode<T>(insertItem, firstNode);
        }
   
        // Search for name
        public void search(String name, int key) {
            if (isEmpty()) {
                System.out.println("Search for " + name + " failed.");
            }
            else if (firstNode.getData().equals(name)) {
                System.out.print("Search for " + name + " successful.");
                print(key);
            } else {
                ListNode<T> current = firstNode;
                while(current != null) {
                    if (current.getData().equals(name)) {
                        System.out.println("Search for " + name + " successful.");
                    }
                    current = current.nextNode;
                }
            }
        }
        
        // Delete from List
        public void deleteName(String name, int key) {
            ListNode<T> current = firstNode;
            ListNode<T> temp = current;
            boolean deleted = false;
            
             // Base case - Empty list
            if (isEmpty()) {
                System.out.println(name + " not found - not deleted.");
            } else 
                
            // Base case - Name at front
            if (firstNode.getData().equals(name)) {  
                firstNode = firstNode.nextNode;
                deleted = true;
                System.out.println(name + " found and deleted.");
            }
            
            // Name somewhere else in list
            while(current != null) {
                
                // Found, delete and stitch list
                if (current.getData().equals(name)) {
                    ListNode<T> link = current.nextNode;
                    current = null;
                    temp.nextNode = link;
                    deleted = true;
                    System.out.println(name + " found and deleted.");
                    break;
                }
                temp = current;
                current = current.nextNode;
            }
            
            if (!deleted) {
                System.out.println(name + " not found - not deleted.");
            }
        }

        // insert item at end of List
        public void insertAtBack(T insertItem) {
            if (isEmpty()) // firstNode and lastNode refer to same object
                firstNode = lastNode = new  ListNode<T>(insertItem);
            else // lastNode's nextNode refers to new node
                lastNode = lastNode.nextNode = new ListNode<T>(insertItem);
        } 

        // determine whether list is empty
        public boolean isEmpty() { 
            return firstNode == null;
        } 

        // output list contents
        public void print(int key) {
            if (isEmpty()) {
                System.out.printf("%d. List (first->last): \n", key);
                return;
            }

            System.out.printf("%d. List (first->last): ", key);
            ListNode<T> current = firstNode;

            // while not at end of list, output current node's data
            while (current != null) {
                System.out.printf("%d/%s;", key, current.data);
                current = current.nextNode;
            }
            System.out.println();
        } 
    } 

    public static class EmptyListException extends RuntimeException {
        // constructor
        public EmptyListException(){
            this("List"); // call other EmptyListException constructor
        }
    
        // constructor
        public EmptyListException(String name) {
            super(name + " is empty"); // call superclass constructor
        } 
    }
}
    







