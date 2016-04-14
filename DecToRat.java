import java.math.*;
import java.io.*;

public class DecToRat {

    //Function computes 10^(bd), where 'bd' is the formal parameter in the function
    //Function not for user purpose
    private static BigDecimal tenPower(BigDecimal bd) {

        //If 'bd' is positive
        if(bd.compareTo(BigDecimal.ZERO) > 0) {

            //length = number of zeroes in 10^(bd); eg: 10^2 = 100, 2 zeroes in 100
            BigDecimal length = new BigDecimal(bd.toString());
            BigDecimal k = BigDecimal.ONE;
            String num = "1";
            int count=0;
        
            //Keep adding 'length' number of zeroes to the final answer
            for(;k.compareTo(length) <= 0;k = k.add(BigDecimal.ONE))
                num = num + "0";

            return new BigDecimal(num);
        }

        //If bd == 0, return 1
        else if(bd.compareTo(BigDecimal.ZERO) == 0)
            return BigDecimal.ONE;

        //If bd is negative
        else {

            //length = number of zeroes after the decimal point in 10^(bd); eg: 10^(-2) = 0.01, 1 zero in 0.01 after decimal point
            BigDecimal length = new BigDecimal(bd.toString());
            length = length.multiply(new BigDecimal("-1"));
            BigDecimal k = BigDecimal.ONE;
            String num = "1";

            //Add (length - 1) number of zeroes before 1
            for(;k.compareTo(length) < 0;k = k.add(BigDecimal.ONE))
                num = "0"+num;

            //Add one leading zero and the decimal point
            num = "0."+num;

            return new BigDecimal(num);
        }
    }

    //Function returns absolute value of BigDecimal value
    public static BigDecimal absoluteVal(BigDecimal x) {

        //If x < 0, multiply (-1) to it
        if(x.compareTo(BigDecimal.ZERO) < 0)
            return x.multiply(new BigDecimal("-1"));

        return x;
    }

    //overloaded function to convert to rational with default precision of 10
    public static Rational toRational2(BigDecimal number) {

        return toRational2(number, new BigDecimal("10"));
    }

    //For recurring decimals, conversion from decimal to rational
    public static Rational toRational2(BigDecimal number, BigDecimal largestRightOfDecimal) {

        long sign = 1;
        if(number.compareTo(BigDecimal.ZERO) < 0){
        
            number = number.multiply(new BigDecimal("-1"));
            sign = -1;
        }

        final BigDecimal SECOND_MULTIPLIER_MAX = tenPower(largestRightOfDecimal.subtract(BigDecimal.ONE));
        final BigDecimal FIRST_MULTIPLIER_MAX = SECOND_MULTIPLIER_MAX.multiply(new BigDecimal("10"));
        final BigDecimal ERROR = tenPower(largestRightOfDecimal.multiply(new BigDecimal("-1")).subtract(BigDecimal.ONE));
    
        BigDecimal firstMultiplier = new BigDecimal("1");
        BigDecimal secondMultiplier = new BigDecimal("1");
        boolean notIntOrIrrational = false;
        BigDecimal truncatedNumber = number.setScale(0,BigDecimal.ROUND_DOWN);

        Rational rationalNumber = new Rational(new BigInteger(((FIRST_MULTIPLIER_MAX.multiply(number)).setScale(0,BigDecimal.ROUND_DOWN)).multiply(new BigDecimal(Long.toString(sign))).toString()), new BigInteger(FIRST_MULTIPLIER_MAX.toString()));

        BigDecimal error = number.subtract(truncatedNumber);
        
        while((error.compareTo(ERROR) >= 0) && (firstMultiplier.compareTo(FIRST_MULTIPLIER_MAX)) <= 0) {
    
            secondMultiplier = BigDecimal.ONE;
            firstMultiplier = firstMultiplier.multiply(BigDecimal.TEN);

            while((secondMultiplier.compareTo(SECOND_MULTIPLIER_MAX) <= 0) && (secondMultiplier.compareTo(firstMultiplier) < 0)) {

                BigDecimal difference = new BigDecimal(subtractBigDecimal((number.multiply(firstMultiplier)),(number.multiply(secondMultiplier))));

                truncatedNumber = difference.setScale(0,BigDecimal.ROUND_DOWN);
                error = difference.subtract(truncatedNumber);

                if(error.compareTo(ERROR) < 0) {

                    notIntOrIrrational = true;
                    break;
                }

                secondMultiplier = secondMultiplier.multiply(BigDecimal.TEN);
            }
        }

        if(notIntOrIrrational)
            rationalNumber = new Rational(new BigInteger((truncatedNumber.multiply(new BigDecimal(Long.toString(sign)))).toString()), new BigInteger((firstMultiplier.subtract(secondMultiplier)).toString()));

        return rationalNumber;
    }

    //Customised subtract function to subtract remove precision error in recurring decimals
    //eg: 3.333 - 0.3333 = 3.0000, since the decimal is assumed to be recurring based on the predefined number of digits in the decimal part
    public static String subtractBigDecimal(BigDecimal a, BigDecimal b) {

        a = new BigDecimal(a.toString());
        b = new BigDecimal(b.toString());

        String sa = a.toString();
        String sb = b.toString();
        String ssb = b.divide(BigDecimal.ONE,0,RoundingMode.DOWN).toString();
        BigDecimal b_backup = b.subtract(new BigDecimal(ssb));

        ssb = ssb + ".";

        BigInteger decimal_len_a = BigDecimalLength.totalLength(sa,3);
        BigInteger decimal_len_b = BigDecimalLength.totalLength(sb,3);

        if(decimal_len_a.compareTo(decimal_len_b) == 0)
            return (a.subtract(b)).toString();

        BigInteger k = BigInteger.ZERO;

        for(;k.compareTo(decimal_len_a) < 0;k = k.add(BigInteger.ONE))
            b_backup = b_backup.multiply(new BigDecimal("10"));

        b_backup = b_backup.divide(BigDecimal.ONE,0,RoundingMode.DOWN);//0.333

        ssb = ssb + b_backup.toString();

        k = BigInteger.ZERO;
        for(;k.compareTo(decimal_len_b.subtract(decimal_len_a)) < 0;k = k.add(BigInteger.ONE))
            ssb = ssb + "0";

        b = new BigDecimal(ssb);
        String ret = (a.subtract(b)).toString();

        return ret;
    }

    //For non-recurring decimals, conversion from decimal to rational
	public static Rational toRational3(BigDecimal number, BigDecimal largestRightOfDecimal) {

        long sign = 1;
        if(number.compareTo(BigDecimal.ZERO) < 0){
        
            number = number.multiply(new BigDecimal("-1"));
            sign = -1;
        }

        final BigDecimal SECOND_MULTIPLIER_MAX = tenPower(largestRightOfDecimal.subtract(BigDecimal.ONE));
        final BigDecimal FIRST_MULTIPLIER_MAX = SECOND_MULTIPLIER_MAX.multiply(new BigDecimal("10"));
        final BigDecimal ERROR = tenPower(largestRightOfDecimal.multiply(new BigDecimal("-1")).subtract(BigDecimal.ONE));
    
        BigDecimal firstMultiplier = new BigDecimal("1");
        BigDecimal secondMultiplier = new BigDecimal("1");
        boolean notIntOrIrrational = false;
        BigDecimal truncatedNumber = number.setScale(0,BigDecimal.ROUND_DOWN);

        Rational rationalNumber = new Rational(new BigInteger(((FIRST_MULTIPLIER_MAX.multiply(number)).setScale(0,BigDecimal.ROUND_DOWN)).multiply(new BigDecimal(Long.toString(sign))).toString()), new BigInteger(FIRST_MULTIPLIER_MAX.toString()));

        BigDecimal error = number.subtract(truncatedNumber);
        
        while((error.compareTo(ERROR) >= 0) && (firstMultiplier.compareTo(FIRST_MULTIPLIER_MAX)) <= 0) {
    
            secondMultiplier = BigDecimal.ONE;
            firstMultiplier = firstMultiplier.multiply(BigDecimal.TEN);

            while((secondMultiplier.compareTo(SECOND_MULTIPLIER_MAX) <= 0) && (secondMultiplier.compareTo(firstMultiplier) < 0)) {

                BigDecimal difference = (number.multiply(firstMultiplier)).subtract((number.multiply(secondMultiplier)));
                truncatedNumber = difference.setScale(0,BigDecimal.ROUND_DOWN);
                error = difference.subtract(truncatedNumber);

                if(error.compareTo(ERROR) < 0) {

                    notIntOrIrrational = true;
                    break;
                }

                secondMultiplier = secondMultiplier.multiply(BigDecimal.TEN);
            }
        }

        if(notIntOrIrrational)
            rationalNumber = new Rational(new BigInteger((truncatedNumber.multiply(new BigDecimal(Long.toString(sign)))).toString()), new BigInteger((firstMultiplier.subtract(secondMultiplier)).toString()));

        return rationalNumber;
    }

    //checks if the given decimal value is recurring or not
    public static boolean isRecurring(BigDecimal bd, BigDecimal largestRightOfDecimal) {

        if(new BigDecimal(BigDecimalLength.totalLength(bd, 3)).compareTo(largestRightOfDecimal) < 0)
            return false;

        else
            return true;
    }

    public static Rational toRationalMain(BigDecimal val) {

        BigDecimal largestRightOfDecimal = new BigDecimal("10");

        if(isRecurring(val, largestRightOfDecimal))
            return toRational2(val, largestRightOfDecimal);

        else
            return toRational3(val, largestRightOfDecimal);
    }

    public static void decimalToRational() throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        System.out.print("Enter decimal value : ");

        //Take decimal value as input from standard input device
        BigDecimal a2 = new BigDecimal(br.readLine());

        //Setting threshold value of number of digits in decimal part
        //If nummber of digits in decimal is less than this value, it is considered as non-recurring
        //Else the decimal is recurring
        //This value can be made user-defined
        BigDecimal largestRightOfDecimal = new BigDecimal("10");

        if(isRecurring(a2, largestRightOfDecimal))
            System.out.println("Rational form : " + toRational2(a2, largestRightOfDecimal));

        else
            System.out.println("Rational form : " + toRational3(a2, largestRightOfDecimal));
    }
}