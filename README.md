# Hash-Table
Hash table supports searching, insertion, deletion, printing, and integer hash key creation based on text or string input data. In the event of collisions, this separate chaining hash table will use a singly linked list to store *duplicate* keys.


## Sample Input

The input file will contains at least one command per line, either insert, delete, search, print, or quit. These are defined in detail below. And if appropriate, a second parameter may be required. This string would contain a name, 
typically, less than seven characters. This name will be the data
used to generate the hash. For example, one of the input files, named `5inserts.txt` contains the following:

```
i homer
i merge
i nelson
i gloria
i duffman
p
```



### Commands 
The specific commands are `i` for insert, `d` for delete, `s` for search, `p` for print, and `q` for quit.

* **Insert:** The insert command uses the single character `i` as the command token. The command
token will be followed by a single `space`, then the `name` which will be the
characters used to calculate the hash key as defined below. The program will
then insert the key and the data in the hash table. In the event that there is a
duplicate key, the new key (and data) would be added to the appropriate slot’s
linked list.
(This command’s success can be verified by using the print command.)

* **Delete** The delete command uses the single character `d` as the command token. The
command token will be followed by a single `space`, then the `name` which will
contain the characters used to calculate the hash key as defined below. The
program will then delete that key and corresponding data from the hash table.
In the event that the key cannot be found, the program will issue an error message
and recover gracefully to continue to accept commands.
(This command’s success can be verified by using the print command.)

* **Search** The insert command uses the single character `s` as the command token. The
command token will be followed by a single `space`, then the `name` which will
contain the characters used to calculate the hash key as defined in the formula below.
Upon successful location of the key, the corresponding key and data shall be output.
In the event that the key cannot be located, the program will advise the user with
a message. See the Output section for an example.

* **Print** The print command uses the single character `p` as the command token. This
command will invoke the print function which will output all the slots in the hash
table and subsequently, all the data in those slots. Given the size of the test data,
the data ouput for each slot should contain the slot number, the hash key, and
the data for that key. In the event that there is more than one data element in
the slot, each element should be output, in the linked list order, until there is
no more data in the slot. (See the Output section below for detailed formatting
information.) This command is critical for verification of all the commands specified above.

* **Quit** The quit command uses the single character `q` as the command token. In the
event the quit command is invoked, the program exits. There is no requirement
for data persistence.

---

## Hashing Algorithm

![](http://everythingcomputerscience.com/images/phone_book_HashTable.jpg)

#### Horner's Rule

```
h ← 0; for i ← 0 to s − 1 do h ← (h · C + ord(ci))mod n

// where C is a constant larger than every ord(ci),  27 as the value for C
// and the first command line parameter as the array size for the value of n.

```

## Collision Resolution

### Separate Chaining

To resolve the problem where there are two keys that were hashed to the same location and both keys need to be preserved as entries to the table, this implementation uses open hashing to resolve the collisions. Each cell of the table houses a node that belongs to a linked-list.

![](https://he-s3.s3.amazonaws.com/media/uploads/0e2c706.png)

### Performance 

Chained hash tables are effective data structures even when the number of table entries is much higher than the number of slots since the cost of lookup is that of iterating through the elements of the selected linked-list. If the hashing yields a uniform distibution then the average cost of a lookup depends only on the average number of keys per linked list.

For separate chaining, the worst-case scenario is when all the entries are inserted into the same linked-list. The lookup procedure may have to scan all its entries, so the worst-case cost is proportional to the number `n` of entries in the table, or `O(n)`


## Testing Lunix/macOS

### Test Input Files
|Filename| Description|
|:----:|:----:|
|5inserts.txt|Five names with no duplicate names.|
|5in2out.txt | Five names added followed by two deletes. One will be a delete of a nonexistent name.|
|5in1del2srch.txt | Five names added, one valid delete, followed by a valid search, then an invalid search.|
|5in1dup.txt| Five names will be inserted with one name being a duplicate, to verify linked list performance.|
|10in.txt|10 names inserted with no duplicates.|

### Test Output Files

|Run Command| Output File Names|
|:----:|:----:|
|java Hashing 5 5inserts.txt|5in-ExpectedOut.txt |
|java Hashing 5 5in2out.txt |5in2-ExpectedOut.txt|
|java Hashing 5 5in1del2srch.txt | 5in1d-ExpectedOut.txt|
|java Hashing 5 5in1dup.txt | 5in1dup-ExpectedOut.txt |
|java Hashing 20 10in.txt |10in-ExpectedOut.txt|

### Comparing output

To redirect the output to a file:
```
java Hashing 5 inputFile > output.txt
```

To compare the contents of two files:
```
diff output.txt expectedOutFile.txt
```











