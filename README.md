[![Build Status](https://travis-ci.org/wolkenschieber/e4JPA.png)](https://travis-ci.org/wolkenschieber/e4JPA)


e4JPA - Eclipse 4 RCP client with JPA/EclipseLink data handling
===============================================================

This projects consists of a simple Eclipse e4 RCP client which uses EclipseLink/JPA to store some sample data through a service.


Import and run source
=====================

- Import projects into clean workspace
- Set targetplatform provided by de.morannon.e4JPA.target
- Run product from de.morannon.e4JPA.client.product
- The add button will add a random entry
- The refresh button will query the db and redraw the tableviewer
- Observe the console output


Maven Build
===========

- Run mvn clean verify or mvn clean package in project root
