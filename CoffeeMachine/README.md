# Coffee Machine

Coffee Machine Application has been created as per the given specs.

Relevant documentation and comments have been added at all the places.

Given functional test has been added to main application run class itself, so whenever you run the application
it will run. I am also attaching the output of one of the runs down below in the file.

Also, many other functional scenarios has been added to the test cases which cover most of the real scenarios. This gives us maximum code coverage too.


## Basic Design Overview
Application is using ThreadPoolExecutor to concurrently manage 'N' outlets with thread-safety.

Following Service classes have been created
1. Beverage Class 
   * ```createBeverage( name, Map<String, Integer> ingredients)```
2. CoffeeMachineService Class
    * ```addBeverage(Beverage beverage)```
    * ```prepareBeverage(String beverageName)```
    * ```addIngredient(String ingredient, Integer quantity)```

## Output on Given Json Input
Just run the application from any IDE (eg. Intellij or Eclipse) and given case will run by default
Output which I got in one run is follows

```
[INFO ] 2021-04-13 15:12:13.284 [pool-2-thread-1] PrepareBeverageTask - hot_tea is prepared

[INFO ] 2021-04-13 15:12:13.284 [pool-2-thread-2] PrepareBeverageTask - hot_coffee is prepared

[ERROR] 2021-04-13 15:12:13.284 [pool-2-thread-3] CoffeeMachine - black_tea cannot be prepared because sugar_syrup is not sufficient

[ERROR] 2021-04-13 15:12:13.289 [pool-2-thread-3] CoffeeMachine - green_tea cannot be prepared because green_mixture is not available
```

## Setup Commands

1. Install
```
mvn -u clean install
```
2. Run test cases
```
mvn test
```