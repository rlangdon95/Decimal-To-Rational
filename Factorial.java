import java.io.*;
import java.util.* ;
import java.math.* ;


/** Factorials.
* @since 2006-06-25
* @since 2012-02-15 Storage of the values based on Ifactor, not BigInteger.
* @author Richard J. Mathar
*/
public class Factorial
{
        /** The list of all factorials as a vector.
        */
        static Vector<Ifactor> a = new Vector<Ifactor>() ;

        /** ctor().
        * Initialize the vector of the factorials with 0!=1 and 1!=1.
        */
        public Factorial()
        {
                if ( a.size() == 0 )
                {
                        a.add(Ifactor.ONE) ;
                        a.add(Ifactor.ONE) ;
                }
        } /* ctor */

        /** Compute the factorial of the non-negative integer.
        * @param n the argument to the factorial, non-negative.
        * @return the factorial of n.
        */
        public BigInteger at(int n)
        {
                /* extend the internal list if needed.
                */
                growto(n) ;
                return a.elementAt(n).n ;
        } /* at */

        /** Compute the factorial of the non-negative integer.
        * @param n the argument to the factorial, non-negative.
        * @return the factorial of n.
        */
        public Ifactor toIfactor(int n)
        {
                /* extend the internal list if needed.
                */
                growto(n) ;
                return a.elementAt(n) ;
        } /* at */

        /** Extend the internal table to cover up to n!
        * @param n The maximum factorial to be supported.
        * @since 2012-02-15
        */
        private void growto(int n)
        {
                /* extend the internal list if needed. Size to be 2 for n<=1, 3 for n<=2 etc.
                */
                while ( a.size() <=n )
                {
                        final int lastn = a.size()-1 ;
                        final Ifactor nextn = new Ifactor(lastn+1) ;
                        a.add(a.elementAt(lastn).multiply(nextn) ) ;
                }
        } /* growto */

        public static BigInteger oddprod(BigInteger l,BigInteger h) {

                BigInteger p = BigInteger.ONE;
                BigInteger ml = (l.remainder(new BigInteger("2"))).compareTo(BigInteger.ZERO) > 0 ? l : l.add(BigInteger.ONE);
                BigInteger mh = (h.remainder(new BigInteger("2"))).compareTo(BigInteger.ZERO) > 0 ? h : h.subtract(BigInteger.ONE);

                while(ml.compareTo(mh) <= 0) {

                        p = p.multiply(ml);
                        ml = ml.add(new BigInteger("2"));
                }

                return p;
        }

        public static BigInteger fact(BigInteger n) {

                BigInteger f = BigInteger.ONE;

                for(BigInteger i=BigInteger.ONE;i.compareTo(n) < 0;i=i.add(BigInteger.ONE))
                        f = f.multiply(oddprod(new BigInteger("3"),new BigInteger(NRoot.powerNew(2,i.add(BigInteger.ONE)).toString()).subtract(BigInteger.ONE)));

                return new BigInteger((NRoot.powerNew(new BigDecimal("2"),(new BigInteger(NRoot.powerNew(new BigInteger("2"),n).toString())).subtract(BigInteger.ONE))).toString()).multiply(f);
        }

        public static BigInteger fact(int n) {

                return fact(new BigInteger(Integer.toString(n)));
        }

        public static BigInteger bigFactorial(int n) {

                return bigFactorial(new BigInteger(Integer.toString(n)));
        }

        public static BigInteger bigFactorial(BigInteger n) {

                BigInteger f = BigInteger.ONE;

                for(BigInteger i=BigInteger.ONE;i.compareTo(n) <= 0;i=i.add(BigInteger.ONE))
                        f = f.multiply(i);

                return f;
        }

        public static BigInteger bigFactorial2(int n) {

                return bigFactorial2(new BigInteger(Integer.toString(n)));
        }

        public static BigInteger bigFactorial2(BigInteger n) {

                if(n.compareTo(BigInteger.ZERO) == 0)
                        return BigInteger.ONE;

                BigInteger number = n;
                String n_string = n.toString();
                char last = n_string.charAt(n_string.length() - 1);
                
                if((last == '1') || (last == '3') || (last == '5') || (last == '7') || (last == '9')) {

                        System.out.println("HELLO");
                        number = number.subtract(BigInteger.ONE);
                        return n.multiply(bigFactorial2(number));
                }

                BigInteger i = BigInteger.ONE;
                BigInteger temp1 = number;
                BigInteger temp2 = number.subtract(new BigInteger("2"));

                for(; i.compareTo(n.divide(new BigInteger("2"))) < 0; i = i.add(BigInteger.ONE)) {


                        number = number.multiply(temp1.add(temp2));

                        temp1 = temp1.add(temp2);
                        temp2 = temp2.subtract(new BigInteger("2"));
                }

                return number;
        }

        public static void computeFactorial() throws IOException {
                
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                System.out.print("Enter number to compute factorial of : ");
                int val = Integer.parseInt(br.readLine());

                long start = System.nanoTime();

                BigInteger a = new Factorial().at(val);
                //System.out.println("Factorial Method 1 = " + a);
                
                long end1 = System.nanoTime();
                
                a = bigFactorial(val);
                //System.out.println("Factorial Method 2 = " + a);
                
                long end2 = System.nanoTime();

                a = bigFactorial2(val);
                //System.out.println("Factorial Method 3 = " + a);

                long end3 = System.nanoTime();

                System.out.println("Input Number = " + val);
                System.out.println("Time Taken by :\nMethod 1 (nanoseconds) : " + (end1 - start));
                System.out.println("Method 2 (nanoseconds) : " + (end2 - end1));
                System.out.println("Method 3 (nanoseconds) : " + (end3 - end2));
        }
} /* Factorial */