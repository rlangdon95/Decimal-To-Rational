import java.io.*;
import java.math.*;

public class BigBasicOps {

	public static BigDecimal remainderNew(BigDecimal dividend, BigDecimal divisor) {

		if(divisor.compareTo(dividend) == 0)
			return BigDecimal.ONE;

		BigDecimal quotient = BigDecimal.ZERO;

		while(divisor.compareTo(dividend) <= 0) {

			quotient = quotient.add(BigDecimal.ONE);
			dividend = dividend.subtract(divisor);
		}

		return dividend;
	}

	public static BigDecimal divideNew(BigDecimal dividend, BigDecimal divisor) {

		if(divisor.compareTo(dividend) == 0)
			return BigDecimal.ONE;

		BigDecimal quotient = BigDecimal.ZERO;

		while(divisor.compareTo(dividend) <= 0) {

			quotient = quotient.add(BigDecimal.ONE);
			dividend = dividend.subtract(divisor);
		}

		return quotient;
	}

	public static String divideNew(BigDecimal dividend, BigDecimal divisor, BigInteger precision) {

		precision = precision.add(BigInteger.ONE);

		if(divisor.compareTo(dividend) == 0) {

			BigInteger k = BigInteger.ONE;
			String s = "1.";

			for(;k.compareTo(precision) != 0;k = k.add(BigInteger.ONE))
				s = s + "0";
			
			return s;
		}

		BigDecimal quotient = BigDecimal.ZERO;

		while(divisor.compareTo(dividend) <= 0) {

			quotient = quotient.add(BigDecimal.ONE);
			dividend = dividend.subtract(divisor);
		}

		String q = quotient.toString() + ".";

		BigInteger k = BigInteger.ONE;

		for(;k.compareTo(precision) != 0;k = k.add(BigInteger.ONE)) {

			q = q + divideNew(dividend.multiply(BigDecimal.TEN),divisor).toString();
			dividend = remainderNew(dividend.multiply(BigDecimal.TEN),divisor);
		}

		return q;
	}

	public static String divideNew(BigDecimal dividend, BigDecimal divisor, int precision) {

		return divideNew(dividend,divisor,new BigInteger(Integer.toString(precision)));
	}

	public static String divideNew(BigDecimal dividend, BigDecimal divisor, long precision) {

		return divideNew(dividend,divisor,new BigInteger(Long.toString(precision)));
	}

	public static BigDecimal divideNew2(BigDecimal dividend, BigDecimal divisor, BigInteger precision) {

		return new BigDecimal(divideNew(dividend,divisor,precision));
	}

	public static BigDecimal divideNew2(BigDecimal dividend, BigDecimal divisor, int precision) {

		return new BigDecimal(divideNew(dividend,divisor,new BigInteger(Integer.toString(precision))));
	}

	public static BigDecimal divideNew2(BigDecimal dividend, BigDecimal divisor, long precision) {

		return new BigDecimal(divideNew(dividend,divisor,new BigInteger(Long.toString(precision))));
	}

	public static void main(String[] args) {
		
		System.out.println(divideNew(new BigDecimal("5"),new BigDecimal("5"),new BigInteger("5")));
	}
}