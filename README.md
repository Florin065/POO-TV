# POO-TV

<div align="center"><img src="https://media.tenor.com/bbNRRKEeV9MAAAAd/hulu-netflix-hbo-max-i-got-all-the-accounts-and-passwords-you-need-nathan.gif" width="500px"></div>

## Implementation

* checker
  * resources/
    * in/ -> contains the tests in JSON format
    * ref/ -> contains all reference output for the tests in JSON format
* src/
  * checker/ -> checker files
  * fileio/ -> contains classes used to read data from the json files
    * ActionsInput.java -> the format for actions input
    * Contains.java -> the format used for filter's contains input
    * Credentials.java -> the format used for user's credentials input
    * Filters.java -> the format used for action's filter input
    * Input.java -> the input's format
    * MovieInput.java -> the format for movies input
    * Notifications.java -> the format used for user's notifications
    * Sort.java -> the format used for filter's sort input
    * UserInput.java -> the format for user input
  * pootv/ -> contains platform implementation
    * command/ -> package for all commands that are needed; commands were implemented
                  with Command Pattern
      * authenticated/ -> package for commands that can be used when a user is logged in
                       -> homepage authenticated
        * logout/ -> logout action
          * Logout.java -> log the user off the platform
        * movies/ -> package for all commands that can be used on movies page
          * filterstrategy/ -> package for filters that are needed; filters were implemented
                               with Strategy Pattern
            * ContainsFilter.java -> filter movies by actors and genres
            * FilterStrategy.java -> interface that defines an action
            * SortFilter.java -> filter movies by duration and rating
          * Filter.java -> context class | a class which uses a Strategy
          * FilterOutput.java -> provides the output for the filtering action
          * Search.java -> searching for a movie which starts with a given String
        * seeDetails/ -> package for all commands that can be used on see details page
          * FreePurchase.java -> utility class called when a premium user wants to purchase a movie
          * Like.java -> like action | can be used only one time only if the user watched the movie
          * Purchase.java -> you can only purchase the movie if you are on its page
          * Rate.java -> rate action (rate the movie from 1 to 5) can be used only one time only
                         if the user watched the movie
          * Subscribe.java -> user's can subscribe a movie genre and receive notifications
          * TokensPurchase.java -> utility class called when a standard user or a premium user that
                                   used all of his free premium movies, wants to purchase a movie
                                   (2 tokens)
          * Watch.java -> you can only watch the movie if you are on its page and you purchased it
        * upgrades/ -> package for all commands that can be used on upgrades page
          * BuyPA.java -> the user can upgrades his account to premium (10 tokens)
                          the premium account provides him 15 free movies
          * BuyTokens.java -> the user can buy tokens | 1 token = 1 balance
      * changepage/ -> package for change page action
        * Back.java -> action with which you return to the page you were on before the
                       last change page command was performed
        * ChangePage.java -> utility class called if the user wants to change the page
        * ChangePageToMovies.java -> utility class called if the user change page to movies page
                                    displays all the movies the current user can see in his country
        * ChangePageToSeeDetails.java -> utility class called if the user wants to go to a movie
                                          page displays the movie's details
      * database/
        * Add.java -> a new movie will be added to the database; an action performed by
                      an external database administrator
        * Delete.java -> the opposite of the database add command, it will delete a movie from
                         the database; an action performed by an external database administrator
      * unAuthenticated/ -> package for commands that can be used when someone access the platform
                         -> homepage unauthenticated
        * login/ -> login page
          * Login.java -> allows the connection of a user who already registered on the platform
        * register/ -> register page
          * Register.java -> allows the user to register on the platform
        * UnauthOutput.java -> generate Login / Register output
      * Actions.java -> request class | receives the command as a parameter after which it calls
                                        the invoker, and it executes the command
      * Command.java -> interface that acts as a command
      * Invoker.java -> class that acts as an invoker object (used )
      * NotBannedMVS.java -> an utility class that provides a list with movies that are not banned
                             in user's country
    * recommendation/
      * Recommendation.java -> utility classs used at the end of the action string, the connected
                               premium user will receive a movie recommendation based on the movie
                               genres they like the most.
    * CommandOutput.java -> utility class called a command generate an output
    * DataBase.java -> implemented as a Singleton, this database contains all users and movies
    * Error.java -> provides the basic Error
    * Menu.java -> the platform's menu
    * Page.java -> page hierarchy were stored in a HashMap
  * Main.java -> the entry point to this project. It runs the checker that tests your implentation
  * Test.java -> the actual main from the initial skel structure

## Design Patterns

In my implementation I used 4 design patterns namely:

* Builder Pattern -> src/fileio/UserInput.java
* Command Pattern -> src/pootv/command/
* Strategy Pattern -> src/pootv/command/authenticated/movies/filterStrategy/
* Singleton Pattern -> src/pootv/Database.java

## Tests

 1. basic_1  - 6p
 2. basic_2  - 6p
 3. basic_3  - 6p
 4. basic_4  - 6p
 5. basic_5  - 6p
 6. basic_6  - 6p
 7. basic_7  - 6p
 8. basic_8  - 6p
 9. basic_9  - 6p
10. basic_10 - 6p
