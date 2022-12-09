import java.util.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.Random;
import org.junit.runner.RunWith;
import com.pholser.junit.quickcheck.*;
import com.pholser.junit.quickcheck.generator.*;
import edu.berkeley.cs.jqf.fuzz.*;

import static java.lang.Double.NaN;
import java.lang.Math;
import com.pholser.junit.quickcheck.generator.java.lang.DoubleGenerator;
import com.pholser.junit.quickcheck.generator.InRange;

@RunWith(JQF.class)
public class CalculatorTest {
	private static Random ran = new Random();

	@Fuzz
    public void testPosInfBiOp(@From(DoubleGenerator.class)double x) {
		double a = x * ran.nextInt();
		Calculator calculator = new Calculator();

		calculator.calculateBi(Calculator.BiOperatorModes.add, a);
		Double ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, Double.POSITIVE_INFINITY);
		assertTrue(Double.toString(a) + " + +INFINITY = " +  Double.toString((a + Double.POSITIVE_INFINITY)) + ", is " + Double.toString(ans)
		,(a + Double.POSITIVE_INFINITY) == ans);

		calculator.reset();
		calculator.calculateBi(Calculator.BiOperatorModes.minus, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, Double.POSITIVE_INFINITY);
		assertTrue(Double.toString(a) + " - +INFINITY = " +  Double.toString((a - Double.POSITIVE_INFINITY)) + ", is " + Double.toString(ans)
		,(a - Double.POSITIVE_INFINITY) == ans);

		calculator.reset();
		calculator.calculateBi(Calculator.BiOperatorModes.multiply, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, Double.POSITIVE_INFINITY);
		assertTrue(Double.toString(a) + " * +INFINITY = " +  Double.toString((a * Double.POSITIVE_INFINITY)) + ", is " + Double.toString(ans)
		,(a * Double.POSITIVE_INFINITY) == ans);

		calculator.reset();
		calculator.calculateBi(Calculator.BiOperatorModes.divide, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, Double.POSITIVE_INFINITY);
		assertTrue(Double.toString(a) + " / *INFINITY = " +  Double.toString((a / Double.POSITIVE_INFINITY)) + ", is " + Double.toString(ans)
		,(a / Double.POSITIVE_INFINITY) == ans);
	}

	@Fuzz
    public void testNegInfBiOp(@From(DoubleGenerator.class)double x) {
		double a = x * ran.nextInt();
		Calculator calculator = new Calculator();

		calculator.calculateBi(Calculator.BiOperatorModes.add, a);
		Double ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, Double.NEGATIVE_INFINITY);
		assertTrue(Double.toString(a) + " + -INFINITY = " +  Double.toString((a + Double.NEGATIVE_INFINITY)) + ", is " + Double.toString(ans)
		,(a + Double.NEGATIVE_INFINITY) == ans);

		calculator.reset();
		calculator.calculateBi(Calculator.BiOperatorModes.minus, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, Double.NEGATIVE_INFINITY);
		assertTrue(Double.toString(a) + " - -INFINITY = " +  Double.toString((a - Double.NEGATIVE_INFINITY)) + ", is " + Double.toString(ans)
		,(a - Double.NEGATIVE_INFINITY) == ans);

		calculator.reset();
		calculator.calculateBi(Calculator.BiOperatorModes.multiply, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, Double.NEGATIVE_INFINITY);
		assertTrue(Double.toString(a) + " * -INFINITY = " +  Double.toString((a * Double.NEGATIVE_INFINITY)) + ", is " + Double.toString(ans)
		,(a * Double.NEGATIVE_INFINITY) == ans);

		calculator.reset();
		calculator.calculateBi(Calculator.BiOperatorModes.divide, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, Double.NEGATIVE_INFINITY);
		assertTrue(Double.toString(a) + " / -INFINITY = " +  Double.toString((a / Double.NEGATIVE_INFINITY)) + ", is " + Double.toString(ans)
		,(a / Double.NEGATIVE_INFINITY) == ans);
	}

	@Fuzz
	public void testNaNBiOps(@From(DoubleGenerator.class)double x) {
		double a = x * ran.nextInt();
		Calculator calculator = new Calculator();

		calculator.calculateBi(Calculator.BiOperatorModes.add, a);
		Double ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, NaN);
		assertTrue(Double.toString(a) + " + NaN = " +  Double.toString((a + NaN)) + ", is " + Double.toString(ans)
		, ans.isNaN());

		calculator.reset();
		calculator.calculateBi(Calculator.BiOperatorModes.minus, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, NaN);
		assertTrue(Double.toString(a) + " - NaN = " +  Double.toString((a - NaN)) + ", is " + Double.toString(ans)
		,ans.isNaN());

		calculator.reset();
		calculator.calculateBi(Calculator.BiOperatorModes.multiply, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, NaN);
		assertTrue(Double.toString(a) + " * NaN = " +  Double.toString((a * NaN)) + ", is " + Double.toString(ans)
		,ans.isNaN());

		calculator.reset();
		calculator.calculateBi(Calculator.BiOperatorModes.divide, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, NaN);
		assertTrue(Double.toString(a) + " / NaN = " +  Double.toString((a / NaN)) + ", is " + Double.toString(ans)
		,ans.isNaN());
	}

	@Fuzz
    public void testAllBiOps(@From(DoubleGenerator.class)double x, @From(DoubleGenerator.class)double y) {
		double a = x * ran.nextInt();
		double b = y * ran.nextInt();
		Calculator calculator = new Calculator();

		calculator.calculateBi(Calculator.BiOperatorModes.add, a);
		Double ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, b);
		assertTrue(Double.toString(a) + " + " + Double.toString(b) + " = " +  Double.toString((a + b)) + ", is " + Double.toString(ans)
		,(a + b) == ans);

		calculator.reset();
		calculator.calculateBi(Calculator.BiOperatorModes.minus, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, b);
		assertTrue(Double.toString(a) + " - " + Double.toString(b) + " = " +  Double.toString((a - b)) + ", is " + Double.toString(ans)
		,(a - b) == ans);

		calculator.reset();
		calculator.calculateBi(Calculator.BiOperatorModes.multiply, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, b);
		assertTrue(Double.toString(a) + " * " + Double.toString(b) + " = " +  Double.toString((a * b)) + ", is " + Double.toString(ans)
		,(a * b) == ans);

		calculator.reset();
		calculator.calculateBi(Calculator.BiOperatorModes.divide, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, b);
		assertTrue(Double.toString(a) + " / " + Double.toString(b) + " = " +  Double.toString((a / b)) + ", is " + Double.toString(ans)
		,(a / b) == ans);
	}
}