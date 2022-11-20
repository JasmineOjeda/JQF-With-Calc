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
	public void calculateBiNormalTest(@From(DoubleGenerator.class)double x, @From(DoubleGenerator.class)double y) {
		double a = x * ran.nextInt();
		double b = y * ran.nextInt();

		Calculator calculator = new Calculator();
		calculator.calculateBi(Calculator.BiOperatorModes.normal, a);
		Double ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, b);
		assertTrue(Double.toString(ans) + " should be NaN", ans.isNaN());
    }

	@Fuzz
    public void calculateBiAddTest(@From(DoubleGenerator.class)double x, @From(DoubleGenerator.class)double y) {
		double a = x * ran.nextInt();
		double b = y * ran.nextInt();

		Calculator calculator = new Calculator();
		calculator.calculateBi(Calculator.BiOperatorModes.add, a);
		Double ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, b);
		assertTrue(Double.toString(a) + " + " + Double.toString(b) + " = " +  Double.toString((a + b)) + ", is " + Double.toString(ans)
		,(a + b) == ans);
	}

	@Fuzz
    public void calculateMultiTest(@From(DoubleGenerator.class)double x, @From(DoubleGenerator.class)double y) {
		double a = x * ran.nextInt();
		double b = y * ran.nextInt();

		Calculator calculator = new Calculator();
		calculator.calculateBi(Calculator.BiOperatorModes.add, a);
		calculator.calculateBi(Calculator.BiOperatorModes.multiply, b);
		Double ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, b);
		assertTrue(Double.toString(a) + " + " + Double.toString(b) + " * " + Double.toString(b) + " = " + Double.toString(((a + b) * b)) + ", \nis " + Double.toString(ans)
									,((a + b) * b) == ans);
	}

	@Fuzz
    public void calculateDivideZeroTest(@From(DoubleGenerator.class)double x) {
		double a = x * ran.nextInt();

		Calculator calculator = new Calculator();
		calculator.calculateBi(Calculator.BiOperatorModes.divide, a);
		Double ans = calculator.calculateBi(Calculator.BiOperatorModes.normal, 0.0);
		assertTrue(Double.toString(a) + " / 0 should be Infinity, is " + Double.toString(ans)
					, (ans == Double.POSITIVE_INFINITY) || (ans == Double.NEGATIVE_INFINITY) || ans.isNaN());
	}
	@Fuzz
    public void resetTest(@From(DoubleGenerator.class)double x, @From(DoubleGenerator.class)double y) {
		Calculator calculator = new Calculator();
		double a = x * ran.nextInt();
		double b = y * ran.nextInt();


		calculator.calculateBi(Calculator.BiOperatorModes.add, a);
		Double ans = calculator.calculateBi(Calculator.BiOperatorModes.add, b);
		assumeTrue((a != +0.0) && (b != -0.0));
		assertTrue(Double.toString(a) + " + " + Double.toString(b) + " = " +  Double.toString((a + b)) + ", is " + Double.toString(ans)
					,((a + b) == ans));
		ans = calculator.reset();
		assertTrue(Double.toString(ans), ans.isNaN());		
		calculator.calculateBi(Calculator.BiOperatorModes.divide, a);
		ans = calculator.calculateBi(Calculator.BiOperatorModes.add, b);
		assertTrue(Double.toString(a) + " + " + Double.toString(b) + " = " +  Double.toString((a / b)) + ", is " + Double.toString(ans) 
					,(a / b) == ans);
	}

	@Fuzz
    public void calculateAbsolute(@From(DoubleGenerator.class)double x, @From(DoubleGenerator.class)double y, @From(DoubleGenerator.class)double z) {
		Calculator calculator = new Calculator();
		double a = x * ran.nextInt();
		double b = y * ran.nextInt();

		calculator.calculateBi(Calculator.BiOperatorModes.add, a);
		Double ans = calculator.calculateBi(Calculator.BiOperatorModes.multiply, b);
		assumeTrue((a != +0.0) && (b != -0.0));
		assertTrue(Double.toString(a) + " * " + Double.toString(b) + " = " +  Double.toString((a + b)) + ", is " + Double.toString(ans)
					,((a + b) == ans));

		//assumeTrue(ans <= 0);
		ans = calculator.calculateMono(Calculator.MonoOperatorModes.abs, ans);
		assertTrue("Should be positive, is "+ Double.toString(ans), ans >= 0);		
	}

	@Fuzz
    public void calculateMonoTanTest(@From(DoubleGenerator.class)double x) {
		double a = x * ran.nextInt();

		Calculator calculator = new Calculator();
		double ans = calculator.calculateMono(Calculator.MonoOperatorModes.tan, a);
		assertTrue("Expected: " + Double.toString(Math.tan(Math.toRadians(a))) + "\nActual: " + Double.toString(ans),
								ans == Math.tan(Math.toRadians(a)));
	}
}