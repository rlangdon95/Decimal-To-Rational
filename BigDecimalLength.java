/* Return Values:
 * Option 	Parameter Returned		Positive Values							Negative Values
 * 1.		Length 					number of digits						number of digits
 * 2.		Decimal Index 			Index of '.' in the String 				Index of '.' in the String including '-'
 * 3. 		Decimal Length 			number of digits after '.' 				number of digits after '.'
 * 			Example Input			0012345.12340							-0012345.12340
 * 			Example Output			9,5,4 respectively						9,6,4 respectively
 */

import java.math.*;

public class BigDecimalLength {

	private static BigInteger decimalIndex;
	private static BigInteger length;

	//Overloaded function to compute total length if input is in BigDecimal form and based on input option 1, 2, or 3
	public static BigInteger totalLength(BigDecimal ss, int option) {

		return totalLength(ss.toString(), option);
	}

	//Function to compute number of digits in the input String, or effective-index of the decimal point (excluding
	//leading and trailing zeros, or number of digits after the decimal point (excluding trailing zeros)
	public static BigInteger totalLength(String ss, int option) {
		
		decimalIndex = BigInteger.ZERO;
		byte sign_val = new BigDecimal(ss).compareTo(BigDecimal.ZERO) >= 0 ? (byte)1 : (byte)0;

		if(ss.charAt(0) != '-')
			ss = "8"+ss;

		else {

			BigDecimal bi = new BigDecimal(ss);
			bi = bi.multiply(new BigDecimal("-1"));
			ss = "8"+bi.toString();
			decimalIndex = decimalIndex.subtract(BigInteger.ONE);
		}

		BigDecimal n = new BigDecimal(ss);
		BigInteger v = BigInteger.ZERO;
		length = BigInteger.ZERO;

		int flag = 1;
		int l = 0;

		if(n.compareTo(n.setScale(0,RoundingMode.DOWN)) == 0)
			flag = 0;

		while(flag == 1) {

			decimalIndex = decimalIndex.add(BigInteger.ONE);
			try {
				
				v = new BigInteger(n.toString());
				flag = 0;
			}

			catch(NumberFormatException ee) {

				flag = 1;
				n = n.multiply(BigDecimal.TEN);

				if(n.compareTo(n.setScale(0,RoundingMode.DOWN)) == 0)
					flag = 0;
			}
		}

		v = (new BigInteger(n.setScale(0,RoundingMode.DOWN).toString()));
		while(v.compareTo(BigInteger.ZERO) != 0) {

			length = length.add(BigInteger.ONE);
			v = v.divide(BigInteger.TEN);
		}

		length = length.subtract(BigInteger.ONE);

		if(option == 1)
			return length;

		else if(option == 2)
			return length.subtract(decimalIndex);

		else if((option == 3) && sign_val == 1)
			return decimalIndex;

		else if((option == 3) && sign_val == 0)
			return decimalIndex.add(BigInteger.ONE);

		else
			throw new RuntimeException("Call by Invalid Input");
	}

	//Reserve Function, for future use, if required
	//returns index of the first non-zero digit in the decimal part
	public static BigInteger firstNonZero(BigDecimal bd) {

		if(bd.compareTo(BigDecimal.ZERO) == 0)
			bd = new BigDecimal(bd.toString() + "8");

		BigInteger ret = BigInteger.ZERO;

		if(bd.compareTo(BigDecimal.ZERO) < 0)
			bd = bd.multiply(new BigDecimal("-1"));

		if(bd.compareTo(BigDecimal.ONE) > 0)
			bd = bd.subtract(bd.setScale(0,BigDecimal.ROUND_DOWN));

		while((bd = bd.multiply(BigDecimal.TEN)).setScale(0,BigDecimal.ROUND_DOWN).intValue() == 0)
			ret = ret.add(BigInteger.ONE);

		return ret;
	}
}