# Implementing a domain-driven design for a payment system in Java

This repository contains the source of the domain-driven design referred to from the ``article.pdf``.
We will study the applicability of DDD in the domain of an on-line banking application which we call Payments. 
It is based on the concepts and ideas introduced by Eric Evans and are applied in this application. 


Run the following 3 Maven steps to start the Payment system:

(1) Setup the database by running:

``mvn compile exec:java -Dexec.mainClass="com.infosupport.poc.db.Database"``

(2) Launch the Payment system by running:

``mvn jetty:run``

(3) Point your browser to:

``http://localhost:8080/addAjax``
