# POO-TV

## Implementation

* checker
  * resources/
    * in/ -> contains the tests in JSON format
    * ref/ -> contains all reference output for the tests in JSON format
* src/
  * checker/ -> checker files
  * fileio/ -> contains classes used to read data from the json files
  * pootv/ -> contains platform implementation
    * command/ -> package for all commands that are needed
      * authenticated/ -> package for commands that can be used when a user is logged in -> homepage authenticated
        * logout/ -> logout page
          * Logout.java -> log the user off the platform
        * movies/ -> movies page
          * filterstrategy/ ->
            * ContainsFilter.java ->
            * FilterStrategy.java ->
            * SortFilter.java ->
          * Filter.java ->
          * Filter.Output.java ->
          * Search.java ->
        * seedetails/ -> see details page
          * FreePurchase.java ->
          * Like.java ->
          * Purchase.java ->
          * Rate.java ->
          * TokensPurchase.java ->
          * Watch.java ->
        * upgrades/ ->
          * BuyPA.java ->
          * BuyTokens.java ->
      * changepage/ ->
        * BasicCP.java ->
        * FindMovieByName.java ->
        * MoviesCP.java ->
        * SeeDetailsCP.java ->
      * unauthenticated/ -> package for commands that can be used when someone access the platform -> homepage unauthenticated
        * login/ ->
          * Login.java -> allows the connection of a user who already has an account on the platform
        * register/ ->
          * Register.java -> allows the user to register on the platform
      * Actions.java ->
      * Command.java ->
      * Invoker.java ->
      * NonBannedMVS.java ->
    * DataBase.java ->
    * Error.java ->
    * Menu.java ->
    * Page.java ->
  * Main.java ->
  * Test.java ->

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
