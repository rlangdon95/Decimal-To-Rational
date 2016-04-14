import java.io.*;
import java.math.*;

public class NRoot {

	//Compute absolute value of given BigDecimal value
	public static BigDecimal absoluteVal(BigDecimal x) {

        if(x.compareTo(BigDecimal.ZERO) < 0)
            return x.multiply(new BigDecimal("-1"));

        return x;
    }

    //Overloaded function to compute x^n and return value in BigDecimal form
    public static BigDecimal powerNew(int x, String n) {

    	return powerNew(new BigDecimal(Integer.toString(x)),new BigDecimal(n));
    }

    //Overloaded function to compute x^n and return value in BigDecimal form
    public static BigDecimal powerNew(int x, BigInteger n) {

    	return powerNew(new BigDecimal(Integer.toString(x)),new BigDecimal(n.toString()));
    }

    //Overloaded function to compute x^n and return value in BigDecimal form
    public static BigDecimal powerNew(BigInteger x, BigInteger n) {

    	return powerNew(new BigDecimal(x.toString()),new BigDecimal(n.toString()));
    }

    //Overloaded function to compute x^n and return value in BigDecimal form
    public static BigDecimal powerNew(BigDecimal x, BigInteger n) {

    	return powerNew(x,new BigDecimal(n.toString()));
    }

    //Function to compute x^n and return value in BigDecimal form
	public static BigDecimal powerNew(BigDecimal x, BigDecimal n) {

		byte flag = 0;

		if(n.compareTo(BigDecimal.ZERO) == 0)
			return BigDecimal.ONE;

		if(n.compareTo(BigDecimal.ZERO) < 0) {

			n = n.multiply(new BigDecimal("-1"));
			flag = -1;
		}

		if(n.remainder(new BigDecimal("2")).compareTo(BigDecimal.ONE) == 0) {

			BigDecimal temp = powerNew(x,n.subtract(BigDecimal.ONE));
			
			if(flag == 0)
				return (x.multiply(temp));
			
			else
				return BigBasicOps.divideNew2(x.multiply(temp),BigDecimal.ONE,20);
		}

		else {

			BigDecimal temp = powerNew(x,n.divide(new BigDecimal("2")));

			if(flag == 0)
				return (temp.multiply(temp));

			else {

				System.out.println("HELLO");
				return BigBasicOps.divideNew2(temp.multiply(temp),BigDecimal.ONE,20);
			}
		}
	}

	public static double nthRoot(double x, double n) {

		if(x < 0) {

			System.out.println("ERROR!!!");
			System.exit(0);
		}

		if(x == 0) 
            return 0;
        
        double x1 = x;
        double x2 = x / n;  
        double p = 0.00000001;

        while(Math.abs(x1 - x2) > p) {
            
            x1 = x2;
            x2 = ((n - 1.0) * x2 + x / Math.pow(x2, n - 1.0)) / n;
        }

        return x2;
	}

	public static BigDecimal nthRoot2(BigDecimal x, BigInteger n) {

		return nthRoot2(x,new BigDecimal(n.toString()),new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000001"));
	}

	public static BigDecimal nthRoot2(BigDecimal x, BigDecimal n) {

		return nthRoot2(x,n,new BigDecimal("0.0000000000000000000000000000000000001"));
	}

	public static BigDecimal nthRoot2(BigDecimal x, BigDecimal n, BigDecimal error) {

		if(x.compareTo(BigDecimal.ZERO) < 0)
			throw new ArithmeticException("Cannot implement on negative base");

		else if((x.compareTo(BigDecimal.ZERO) == 0) && (n.compareTo(BigDecimal.ZERO) == 0))
			throw new ArithmeticException("Zero to the power zero is not defined");

		else if(x.compareTo(BigDecimal.ZERO) == 0)
            return BigDecimal.ZERO;

        if(n.compareTo(BigDecimal.ONE) == 0)
        	return x;
        
        BigDecimal x1 = x;
        BigDecimal x2 = x.multiply(n);//BigBasicOps.divideNew2(x,n,20);

        while(absoluteVal(x1.subtract(x2)).compareTo(error) > 0) {

            x1 = x2;
            x2 = BigBasicOps.divideNew2((((n.subtract(BigDecimal.ONE)).multiply(x2)).add(BigBasicOps.divideNew2(x,powerNew(x2,n.subtract(BigDecimal.ONE)),20))),n,20);
        }

        return x2;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("BASE = ");
		
		BigDecimal base = new BigDecimal(br.readLine());
		System.out.print("EXPONENT = ");
		
		BigDecimal xexp = new BigDecimal(br.readLine());

		
		BigInteger exp_numer = (DecToRat.toRationalMain(xexp)).numer();
		BigInteger exp_denom = (DecToRat.toRationalMain(xexp)).denom();
		
		System.out.println(exp_numer);
		System.out.println(exp_denom);

		BigDecimal ans = powerNew(base,exp_numer);
		System.out.println("ANSWER = " + ans);
		System.out.println("EXP DENOM = " + exp_denom);
		ans = nthRoot2(ans,exp_denom);
		System.out.println("ANSWER = " + ans);
		//System.out.println("ANSWER = " + nthRoot2(new BigDecimal("3"),new BigDecimal("2")));
	}
}