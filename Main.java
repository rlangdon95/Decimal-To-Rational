import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		Scanner ss = new Scanner(System.in);

		while(true) {
		
			System.out.print("1. Decimal to Rational Conversion\n2. Compute Factorial\nAny other integer - Exit\n");
			System.out.print("\nEnter your choice : ");
			int choice = ss.nextInt();

			switch(choice) {

				case 1 :
				DecToRat.decimalToRational();
				break;

				case 2 :
				Factorial.computeFactorial();
				break;

				default :
				System.exit(0);
			}

			System.out.println();
		}
	}
}