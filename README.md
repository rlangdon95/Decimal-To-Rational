# Decimal-To-Rational
Converts a decimal number into its rational form

Synopsis:

	This project is a part of a Scientific Calculator project, whose aim was to perform calculations of arbitrary precision, irrespective of the constraints imposed by the limit of primitive datatypes such as 'int' or 'long' or even the fact that array subscripts in Java can be no larger than the highest integer value supported by the 'int' datatype.

Project Description :

	In this project, two main functions have been implemented using the BigInteger and BigDecimal and other pre-defined and user-defined classes. These two functions are :

		a) public static void decimalToRational()

				This function prompts the user to input a decimal value (such as 0.25 or 0.33333) and converts the decimal value into its corresponding rational value. Note, if the number of digits after the decimal point is less than "10", the decimal value shall be considered as non-recurring, else recurring.

					eg: 0.33333 is considered as non-recurring and rational form is 33333/100000
					    0.33333333333 is considered as recurring and rational form is 1/3

				This threshold of "10" can be changed if needed, in line 240 of the file DecToRat.java

				This function outputs the rational form of the input decimal value.

		b) public static void computeFactorial()

				This function prompts the user to input an integer value and computes the factorial of the value using three different methods :

					i)   an inbuilt method
					ii)  using normal iterations
					iii) using the fact that n! = n * (n + (n - 2)) * (n + (n - 2) + (n - 4))

				The third method tries to cut down on the number of multiplications required by half. This is done to try to reduce the computational time taken.

				This function outputs the time taken by each method mentioned above in different lines in nanoseconds.

				The actual factorial value is not displayed since for large numbers like "10000", where the difference in computational time taken is more perceivable, the factorial value takes up lot of screen space. However, the factorial value can be displayed by un-commenting the display statements in line numbers 158, 163, 168.

Running the Code :

	To run the code, one needs to unzip the files to a specific folder and then navigate to the folder containing the unzipped files. Then the following lines need to be typed in the console (Warnings, if any, can be ignored) :

		javac Main.java

		java Main

Using the Application :

	A menu is shown on executing the code, which is as follows :

		1. Decimal to Rational Conversion
		2. Compute Factorial
		Any other integer - Exit

		Enter your choice :

	Here, the choice needs to be entered. Note that "1" invokes the decimalToRational() function and "2" invokes computeFactorial() function; these two functions have been described above. Any other integer causes the program to halt.

		Choice 1 :
			If choice "1" is entered, the following prompt is shown :

				Enter decimal value :

			On this prompt, the decimal value needs to be entered and the rational form is displayed.

				eg: a)  Enter decimal value : 0.25
						Rational form : 1/4

					b)  Enter decimal value : 0.33
						Rational form : 33/100

					c)  Enter decimal value : 0.3333333333333
						Rational form : 1/3

					d)  Enter decimal value : 0.252525252525252525
						Rational form : 25/99

					e)  Enter decimal value : 0.2533
						Rational form : 2533/10000

					f)  Enter decimal value : 0.2533333333333
						Rational form : 19/75


		Choice 2 :

			If choice "1" is entered, the following prompt is shown :

				Enter number to compute factorial of : 

			On this prompt, an integer needs to be entered and the time taken to compute the factorial of the input integer is calculated for three different methods and the corresponding times are shown in nanoseconds.

				eg: a)  Input Number = 1
						Time Taken by :
						Method 1 (nanoseconds) : 14875
						Method 2 (nanoseconds) : 198499
						Method 3 (nanoseconds) : 1512597

					b)  Input Number = 10
						Time Taken by :
						Method 1 (nanoseconds) : 2083988
						Method 2 (nanoseconds) : 102583
						Method 3 (nanoseconds) : 112329

					c)  Input Number = 100
						Time Taken by :
						Method 1 (nanoseconds) : 15921499
						Method 2 (nanoseconds) : 254408
						Method 3 (nanoseconds) : 774507

					d)  Input Number = 1000
						Time Taken by :
						Method 1 (nanoseconds) : 67756003
						Method 2 (nanoseconds) : 2963642
						Method 3 (nanoseconds) : 2749243

					e)  Input Number = 10000
						Time Taken by :
						Method 1 (nanoseconds) : 1748416621
						Method 2 (nanoseconds) : 412194018
						Method 3 (nanoseconds) : 201988790
