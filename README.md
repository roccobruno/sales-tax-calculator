# Sales Taxes Calculator

[![Build Status](https://travis-ci.org/roccobruno/sales-tax-calculator.svg?branch=master)](https://travis-ci.org/roccobruno/sales-tax-calculator)

Simple solution to the following problem:

SALES TAXES 

Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions. 

When I purchase items I receive a receipt which lists the name of all the items and their price (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains 

(np/100 rounded up to the nearest 0.05) amount of sales tax. 


- [Build the calculator](#build-the-calculator)
- [User the calculator](#user-the-calculator)
- [Requirements](#requirements)
- [More info](#more-info)

## Build the calculator

* clone the project in any DIR
* go into DIR/sales-taxes-calculator
* build the project by executing the command 'gradle build'

## Use the calculator

* run the script 'salesTaxCalculator.sh' and follow instructions

### Requirements

* Java 8
* Gradle >= 2.2

### More info

The solution has been developed following TDD best practises. Some of the advantages of it:

* Writing tests first require you to really consider what you want from the code
* Short feedback loop
* Creates a detailed specification
* Reduced time in rework
* Allows the design to evolve and adapt to your changing understanding of the problem.

Most importantly : Simplification
* Forces radical simplification of the code – you will only write code in response to the requirements of the tests.
* Forces you to write small classes focused on one thing.
* Helps create loosely coupled code
* The resulting Unit Tests are simple and act as documentation for the code
