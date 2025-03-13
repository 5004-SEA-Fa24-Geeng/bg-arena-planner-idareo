# Report

Submitted report to be manually graded. We encourage you to review the report as you read through the provided
code as it is meant to help you understand some of the concepts. 

## Technical Questions

1. What is the difference between == and .equals in java? Provide a code example of each, where they would return different results for an object. Include the code snippet using the hash marks (```) to create a code block.

'==' can only be used to compare primitive data types of memory addresses. '.equals' is a method in java that is assigned to every object. 
This can be overridden for a more specific comparison between two objects.
   ```java
   // your code here
   // code for '=='
    public class primitiveData{
    private String name;
    public primitiveData(String name){
        this.name = name;
    }
    
    public class primitive extends primitiveData{
        private int studentID;
        public primitive(String studentName, int studentID){
           super(name);
           this.studentID = studentID;
        }
        public String getName(){return this.name;}
        
        public int getStudentID(){
            return this.studentID;
       }
        
        public boolean equals(Object o){
            if(this == o){
                return true;
            }
            if(o == null || getClass() != o.getClass()){
                return false;
            }
            
            primitiveData prim = (primitiveData) o;
            return this.getName().equals(prim.getName()) && this.getStudentID.equals(prim.getStudentID);
        }
        
        public static void main(string[] args){
            primitiveData p1 = new primitive("George",2);
            primitveData p2 = new primitive("Alessandro",6);
            primitveData p3 = new primitive("Alessandro",4);

            primitiveData p4 = new primitive("George",2);
           System.out.println(p2 == p3); //primitive
           System.out.println(p1.equals(p4)); //.equals
        }
    }
}

   ```




2. Logical sorting can be difficult when talking about case. For example, should "apple" come before "Banana" or after? How would you sort a list of strings in a case-insensitive manner? 


[//
```java

//(public static List<String> caseSensitive&#40;List<String> words&#41;{)
//(   Collections.sort&#40;words&#41;)

class Fruits{
    private String name;
    public Fruits(String name){
        this.name = name;
    }
    
    public String getName(){
       return this.name;
    }
    
}

class sortCaseSensitive implements Comparator<Fruits> {
    @Override
    public String compare(Fruits a, Fruits b) {
        return a.getName().compareToIgnoreCase(b.getName());
    }

    class Main() {
        public static void main(String[] args) {
            List<Fruits> cs = new ArrayList<>();
            cs.add(new Fruits("banana"));
            cs.add(new Fruits("apple"));

            Collections.sort(cs, new sortCaseSensitive());
        }
    }
}

```


3. In our version of the solution, we had the following code (snippet)
    ```java
    public static Operations getOperatorFromStr(String str) {
        if (str.contains(">=")) {
            return Operations.GREATER_THAN_EQUALS;
        } else if (str.contains("<=")) {
            return Operations.LESS_THAN_EQUALS;
        } else if (str.contains(">")) {
            return Operations.GREATER_THAN;
        } else if (str.contains("<")) {
            return Operations.LESS_THAN;
        } else if (str.contains("=="))...
    ```
    Why would the order in which we checked matter (if it does matter)? Provide examples either way proving your point. 

The order does matter because the operations are done on the results and could be mismatched into the wrong category, causing it to return 
the wrong operation, therefore filter ineffectively.


4. What is the difference between a List and a Set in Java? When would you use one over the other? 
 A list will allow duplicates and adheres to the sequence. A set does not allow duplicates and may not adhere to sequence(implemented by Hashset class. 
A list would be better for storing ordered collections while a set will be better when unique elements are required.



5. In [GamesLoader.java](src/main/java/student/GamesLoader.java), we use a Map to help figure out the columns. What is a map? Why would we use a Map here? 
A map is similar to the dictionary in pythons, typically used to store key-value pairs where the keys are required to be unique, while values can be duplicate.
A map is used when finding elements via the keys matters as this is sorting by mapping to column names. This will be the key, 
therefore new boardGame objects can be added as the values for the column names(keys).


6. [GameData.java](src/main/java/student/GameData.java) is actually an `enum` with special properties we added to help with column name mappings. What is an `enum` in Java? Why would we use it for this application?

An enum is a class type that is used to store a group of named constants. These can have methods that allow you to perform operations on the constants/assign them to specific variables/operators.
In this instance, the enum class is used to handle mapping through the columns-Enums for filtering. The Enums are also used to create a group of presumably the most common column names that might
be operated on. This is why there are two methods, one to handle the just the enums and the other to handle either enums or their associated column name.



7. Rewrite the following as an if else statement inside the empty code block.
    ```java
    switch (ct) {
                case CMD_QUESTION: // same as help
                case CMD_HELP:
                    processHelp();
                    break;
                case INVALID:
                default:
                    CONSOLE.printf("%s%n", ConsoleText.INVALID);
            }
    ``` 

    ```java
    // your code here, don't forget the class name that is dropped in the switch block..
   if(ct == CMD_QUESTION || ct == HELP){
   processHelp();
   }else{
   CONSOLE.printf("%s%n", ConsoleText.INVALID);
   }
    
    ```

## Deeper Thinking

ConsoleApp.java uses a .properties file that contains all the strings
that are displayed to the client. This is a common pattern in software development
as it can help localize the application for different languages. You can see this
talked about here on [Java Localization – Formatting Messages](https://www.baeldung.com/java-localization-messages-formatting).

Take time to look through the console.properties file, and change some of the messages to
another language (probably the welcome message is easier). It could even be a made up language and for this - and only this - alright to use a translator. See how the main program changes, but there are still limitations in 
the current layout. 

Post a copy of the run with the updated languages below this. Use three back ticks (```) to create a code block. 

```text
*******Bienvenue dans le planificateur BoardGame Arena.*******

A tool to help people plan which games they 
want to play on Board Game Arena. 

To get started, enter your first command below, or type ? or help for command options.
```

Now, thinking about localization - we have the question of why does it matter? The obvious
one is more about market share, but there may be other reasons.  I encourage
you to take time researching localization and the importance of having programs
flexible enough to be localized to different languages and cultures. Maybe pull up data on the
various spoken languages around the world? What about areas with internet access - do they match? Just some ideas to get you started. Another question you are welcome to talk about - what are the dangers of trying to localize your program and doing it wrong? Can you find any examples of that? Business marketing classes love to point out an example of a car name in Mexico that meant something very different in Spanish than it did in English - however [Snopes has shown that is a false tale](https://www.snopes.com/fact-check/chevrolet-nova-name-spanish/).  As a developer, what are some things you can do to reduce 'hick ups' when expanding your program to other languages?

Localization is important not only for market share but also for enhancing user experience. When users interact with a program in their native language, it creates a more familiar and comfortable environment, which typically boosts engagement.
It’s also important to recognize that English is not the only language used online. As the world becomes increasingly globalized, 
it is essential to ensure that programs can cater to diverse audiences. For example, services like Uber or food delivery apps often have built-in translation features, 
enabling users from different regions to easily navigate and make use of the service. This is a great example of how localization makes a product more accessible to a wider audience.
Despite the rise of digital platforms, real-world interactions still play a major role in global commerce. 
Even on social media platforms like Instagram, businesses are expanding into e-commerce through features like Instagram Shops, 
which will need to incorporate localization to ensure they can reach a diverse audience.

As a reminder, deeper thinking questions are meant to require some research and to be answered in a paragraph for with references. The goal is to open up some of the discussion topics in CS, so you are better informed going into industry. 

