# [Checkout Kata](http://ecararus.github.io/kata) [![Build Status](https://travis-ci.org/ecararus/kata.svg?branch=master)](https://travis-ci.org/ecararus/kata) [![Coverage Status](https://coveralls.io/repos/github/ecararus/kata/badge.svg?branch=master)](https://coveralls.io/github/ecararus/kata?branch=master)

Implement the code for a supermarket checkout that calculates the total price of a number of items. 
In a normal supermarket, things are identified using Stock Keeping Units, or SKUs. 
In our store, we’ll use individual letters of the alphabet (A, B, C, and so on as the SKUs). 
Our goods are priced individually. In addition, some items are multi-priced: buy n of them, and they’ll cost you y. 
For example, item ‘A’ might cost 50 pence individually, but this week we have a special
offer: buy three ‘A’s and they’ll cost you £1.30. 

In fact this week’s prices are:

|Item |Unit Price |Special Price|
| --- |:---------:|:-----------:|
| A   |   50      | 3 for 130   |
| B   |   30      | 2 for 45    |
| C   |   20      |             |
| D   |   15      |             |

Our checkout accepts items in any order, so that if we scan a B, an A, and another B, we’ll
recognise the two B’s and price them at 45 (for a total price so far of 95). 
Because the pricing changes frequently, we need to be able to pass in a set of pricing rules each time we start
handling a checkout transaction.

##Deployment 
Pivotal cloud: 
```
cf login -a https://api.run.pivotal.io
cf push 
cf logs kata
```
check http://kata.cfapps.io/swagger-ui.html

## Convention:
 - Test names does not contains "test" prefix and should provide as message the purpose of test(will be easy to read in case it is failing).
 - Test should have coverage > 70%.
 - Unit test class will have "Test" postfix.
 - Integration test class will have "IT" postfix.

## DISCLAIMER:
Purpose of project is only educational.
This project should not be used for any commercial purpose.
This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
http://creativecommons.org/licenses/by-nc-sa/4.0/.

## Author:
Eugeniu Cararus
cararuseugeniu@gmail.com
