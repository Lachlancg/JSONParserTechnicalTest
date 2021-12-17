# JSONParserTechnicalTest

In this coding exercise you are required to process an array of JSON input data that represents a number of fictitious vehicles. The “Data” at the end of this document gives an example containing information for 6 vehicles to use as test data for the code that you write, though the code should cope with any number of other similar records.

You may use any programming language of your choice for these exercises. 
You may pass the test data to your code either directly as a String or loaded from a file.

Please spend no more than two hours working through these exercises (you do not have to complete all of them, just see how far you get).

Please submit files containing your code and any associated tests.

1.	Print out the name field for each vehicle in alphabetically sorted order.

2.	Print out a JSON document containing all the distinct values of “make” of vehicle with a count of the number of vehicles of that make in the form:
{“Audi” : 21, “Ford” : 123, “<ANOTHER_MAKE>” : X...}

3.	Modify each record where “model” contains the word “Jumpy” (case insensitive) so that the “assetType” is changed from “CAR” to “VAN”. The output should contain all the same fields as the input.

4.	Print out the sequences of the “_id”s of vehicles that have replaced others based on the values in the “replaced” field (which gives the _id of the vehicle that it replaced after it was sold), for example, in the below data the expected output would be:
398119 > 422108
414231 > 414215 > 414331
