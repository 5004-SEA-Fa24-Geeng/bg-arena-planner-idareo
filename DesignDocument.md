# Board Game Arena Planner Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram 

Place your class diagrams below. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

### Provided Code

Provide a class diagram for the provided code as you read through it.  For the classes you are adding, you will create them as a separate diagram, so for now, you can just point towards the interfaces for the provided code diagram.

[//]: # ( [MermaidChart: 871fddb0-ed18-452b-9e6b-9467173a8399]

---
title: bg_arena_planner - Initial design
---
classDiagram
direction BT
class BGArenaPlanner {
- DEFAULT_COLLECTION: static final string
- BGArenaPlanner()
+ main(String[] args) : static void
}
class ConsoleApp {
- IN: static final Scanner
- DEFAULT_FILENAME: static final String
- RND: static final Random
- current: Scanner
- gameList: final IGameList
- planner: final IPlanner
+ ConsoleApp(IGameList gameList, IPlanner planner)
+ start() : void
- randomNumber() : void
- processHelp() : void
- processFilter() : void
- printFilterStream(Stream~BoardGame~ games, GameData sortON) : void
- processListCommands() : void
- printCurrentList() : void
- nextCommand() : ConsoleText
- remainder() : String
- getInput(String format, Object... args) : static String
- printOutput(String format, Object... output) : static void
- ConsoleText(Enum)
}
class Planner {
games: Set~BoardGame~
+ Planner(games: Set)
+ filter(filter: String) : Stream~BoardGame~
+ filter(String filter, GameData sortOn) : Stream~BoardGame~
+ filter(String filter, GameData sortOn, boolean ascending) : Stream~BoardGame~
+ reset() : void
}
class GameList {
- gameNames: Set~String~
+ GameList()
+ getGameNames() : List~String~
+ clear() : void
+ count() : int
+ saveGame(filename: String) : void
+ addToList(String str, Stream filtered) : void
+ removeFromList(String str) : void
}
class IGameList {
getGameNames() : List~String~
clear() : void
count() : int
saveGame(filename: String) : void
addToList(String str, Stream filtered) : void
removeFromList(String str) : void
}
class IPlanner {
filter(filter: String) : Stream~BoardGame~
filter(String filter, GameData sortOn) : Stream~BoardGame~
filter(String filter, GameData sortOn, boolean ascending) : Stream~BoardGame~
reset() : void
}
class Operations {
- operator: final String
Operations(operator: String)
+ getOperator() : String
+ fromOperator(operator: String) : static Operations
+ getOperatorFromStr(str: String) : static Operations
}
class GameData {
- columnName: final String
GameData(columnName: String)
+ getColumnName() : String
+ fromColumnName(columnName: String) : static GameData
+ fromString(name: String) : static GameData
}
class Filter {
- Filters()
+ filter(BoardGame game, GameData column, Operations op, String value) : static Boolean
+ filterString(String gameData, Operations op, String value) : static boolean
}
class BoardGame {
- name: final String
- id: final int
- minPlayers: int
- maxPlayers: int
- maxPlayTime: int
- minPlayTime: int
- difficulty: double
- rank: int
- averageRating: double
- yearPublished: int
+ BoardGame(String name, int id, int minPlayers, int maxPlayers, int minPlayTime,int maxPlayTime, double difficulty, int rank, double averageRating, int yearPublished)
+ getName() : String
+ getId() : int
+ getMinPlayers() : int
+ getMaxPlayers() : int
+ getMaxPlayTime() : int
+ getMinPlayTime() : int
+ getDifficulty() : double
+ getRank() : int
+ getRating() : double
+ getYearPublished() : int
+ toStringWithInfo(GameData col) : String
+ toString() : String
+ equals(Object obj) : boolean
+ hashCode() : int
main(String[] args) : void
}
class GameLoader {
- DELIMITER: String
+ GamesLoader()
+ loadGamesFile(String filename) : Set~BoardGame~
- toBoardGame(String line, Map columnMap) : BoardGame
- processHeader(String header) : Map~GameData, Integer~
}

	<<Interface>> IGameList
	<<Interface>> IPlanner
	<<Enum>> Operations
	<<Enum>> GameData

    GameList ..|> IGameList
    Planner ..|> IPlanner
    BoardGame --* GameLoader
    Filter --> Operations
    Planner --> Filter
    BGArenaPlanner --> ConsoleApp
    ConsoleApp --> Planner
    ConsoleApp --> GameList
    Planner --> GameLoader
    GameLoader .. GameData


### Your Plans/Design

Create a class diagram for the classes you plan to create. This is your initial design, and it is okay if it changes. Your starting points are the interfaces. 





## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test 1..
2. Test 2..




## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

For the final design, you just need to do a single diagram that includes both the original classes and the classes you added. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.





## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning to information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 
