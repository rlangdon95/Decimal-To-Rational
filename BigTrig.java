import java.math.*;

public class BigTrig {

	public static final BigDecimal PI = new BigDecimal("3.14159265358979323846264338327950288419716939937510582097494459230781640628620");//+
			"899862803482534211706798214808651328230664709384460955058223172535940812848111"+
			"745028410270193852110555964462294895493038196442881097566593344612847564823378"+
			"678316527120190914564856692346034861045432664821339360726024914127372458700660"+
			"631558817488152092096282925409171536436789259036001133053054882046652138414695"+
			"194151160943305727036575959195309218611738193261179310511854807446237996274956"+
			"735188575272489122793818301194912983367336244065664308602139494639522473719070"+
			"217986094370277053921717629317675238467481846766940513200056812714526356082778"+
			"577134275778960917363717872146844090122495343014654958537105079227968925892354"+
			"201995611212902196086403441815981362977477130996051870721134999999837297804995"+
			"105973173281609631859502445945534690830264252230825334468503526193118817101000"+
			"313783875288658753320838142061717766914730359825349042875546873115956286388235"+
			"378759375195778185778053217122680661300192787661119590921642019893809525720106"+
			"548586327886593615338182796823030195203530185296899577362259941389124972177528"+
			"347913151557485724245415069595082953311686172785588907509838175463746493931925"+
			"506040092770167113900984882401285836160356370766010471018194295559619894676783"+
			"744944825537977472684710404753464620804668425906949129331367702898915210475216"+
			"205696602405803815019351125338243003558764024749647326391419927260426992279678"+
			"235478163600934172164121992458631503028618297455570674983850549458858692699569"+
			"092721079750930295532116534498720275596023648066549911988183479775356636980742"+
			"654252786255181841757467289097777279380008164706001614524919217321721477235014");

	public static BigDecimal bigCos(int x, int precision) {

		return bigCos(new BigDecimal(Integer.toString(x)),new BigInteger(Integer.toString(precision)));
	}

	public static BigDecimal bigCos(BigDecimal x, BigInteger precision) {

		x = BigBasicOps.remainderNew(x,PI.multiply(new BigDecimal(2)));

		//System.out.println("x = "+x);

		BigDecimal value = BigDecimal.ONE;
		byte ctr = 1;
		BigInteger power = new BigInteger("2");
		BigDecimal temp;
		BigInteger len;

		do {

			temp = new BigDecimal(Factorial.bigFactorial(power).toString());
			//System.out.println("1. JJJ temp1 = "+temp);

			temp = new BigDecimal(BigBasicOps.divideNew((NRoot.powerNew(x,power)),temp,precision));
			//System.out.println("2. JJJ temp2 = "+temp);

			if(ctr == 1) {

				value = value.subtract(temp);
				ctr = 2;
			}

			else {

				value = value.add(temp);
				ctr = 1;
			}

			//System.out.println("3. JJJvalue = "+value);

			len = BigDecimalLength.firstNonZero(temp);
			//System.out.println(len);

			//System.out.println("4. JJJ");

			power = power.add(new BigInteger("2"));
		} while((len.compareTo(precision) <= 0));

		return value;
	}

	public static BigDecimal bigSin(double n, int precision) {

		return bigSin(new BigDecimal(Double.toString(n)), new BigInteger(Integer.toString(precision)));
	}

	public static BigDecimal bigSin(BigDecimal n, BigInteger precision) {

		BigDecimal sum = n;
		BigDecimal temp;

		for(BigInteger i = BigInteger.ONE;true;i = i.add(BigInteger.ONE)) {

			BigDecimal temp_fact = new BigDecimal(Factorial.bigFactorial(i.multiply(new BigInteger("2")).add(BigInteger.ONE)).toString());
			BigDecimal temp_pow = NRoot.powerNew(n,i.multiply(new BigInteger("2")).add(BigInteger.ONE));

			//System.out.println("fact = "+temp_fact);
			//System.out.println("pow = "+temp_pow);

			temp = new BigDecimal((BigBasicOps.divideNew(temp_pow,temp_fact,precision).toString()));

			if(i.remainder(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0)
				sum = sum.add(temp);

			else
				sum = sum.subtract(temp);

			if((BigDecimalLength.firstNonZero(temp)).compareTo(precision) > 0)
				break;

			System.out.print("A");
		}

		System.out.println(temp);
		System.out.println(BigDecimalLength.firstNonZero(temp));

		return sum;
	}

	public static void main(String[] args) {
		
		System.out.println(bigSin(9,16));
		System.out.println(Math.sin(9));
		//System.out.println(PI.toString().length());
	}
}