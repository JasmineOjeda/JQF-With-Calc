/*package simplejavacalculatorTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static java.lang.Double.NaN;
import java.lang.Math;


import simplejavacalculator.Calculator;

class CalculatorTest {
	
	@Test
	void calculateBiNormalTest() {
		Calculator calculator = new Calculator();
		calculator.calculateBi(Calculator.BiOperatorModes.normal, 2.0);
		Assertions.assertEquals(NaN, calculator.calculateBi(Calculator.BiOperatorModes.normal, 3.0));
    }

	@Test
	void calculateBiMinusTest() {
		Calculator calculator = new Calculator();
		calculator.calculateBi(Calculator.BiOperatorModes.minus, 3.1415);
		Assertions.assertEquals(2.0415, calculator.calculateBi(Calculator.BiOperatorModes.normal, 1.1));
	}
	
	@Test
	void calculateBiMultiplyTest() {
		Calculator calculator = new Calculator();
		calculator.calculateBi(Calculator.BiOperatorModes.multiply, 3.2);
		Assertions.assertEquals(6.4, calculator.calculateBi(Calculator.BiOperatorModes.normal, 2.0));
	}
	
	@Test
	void calculateBiDivideTest() {
		Calculator calculator = new Calculator();
		calculator.calculateBi(Calculator.BiOperatorModes.divide, 6.4);
		Assertions.assertEquals(3.2, calculator.calculateBi(Calculator.BiOperatorModes.normal, 2.0));
	}
	
	@Test
	void calculateEqualTest() {
		Calculator calculator = new Calculator();
		calculator.calculateBi(Calculator.BiOperatorModes.add, 6.4);
		calculator.calculateBi(Calculator.BiOperatorModes.add, 2.0);
		Assertions.assertEquals(11.4, calculator.calculateEqual(3.0));
	}
	
	@Test
	void resetTest() {
		Calculator calculator = new Calculator();
		calculator.calculateBi(Calculator.BiOperatorModes.add, 6.4);
		Assertions.assertEquals(8.4, calculator.calculateBi(Calculator.BiOperatorModes.add, 2.0));
		Assertions.assertEquals(NaN, calculator.reset());		
	}
	
	@Test
	void CalculateMonoSquareTest() {
		Calculator calculator = new Calculator();
		Assertions.assertEquals(9.0, calculator.calculateMono(Calculator.MonoOperatorModes.square, 3.0));
	}
	
	@Test
	void CalculateMonoSquareRootTest() {
		Calculator calculator = new Calculator();
		Assertions.assertEquals(5.0, calculator.calculateMono(Calculator.MonoOperatorModes.squareRoot, 25.0));
	}
	
	@Test
	void CalculateMonoOneDividedByTest() {
		Calculator calculator = new Calculator();
		Assertions.assertEquals(0.10, calculator.calculateMono(Calculator.MonoOperatorModes.oneDividedBy, 10.0));
	}
	
	@Test
	void CalculateMonoSinTest() {
		Calculator calculator = new Calculator();
		Assertions.assertEquals(0.5, calculator.calculateMono(Calculator.MonoOperatorModes.sin, java.lang.Math.PI / 6), 0.0000000001);
	}
	
	@Test
	void CalculateMonoCosTest() {
		Calculator calculator = new Calculator();
		Assertions.assertEquals(0.5, calculator.calculateMono(Calculator.MonoOperatorModes.cos, java.lang.Math.PI / 3), 0.0000000001);
	}
	
	@Test
	void CalculateMonoTanTest() {
		Calculator calculator = new Calculator();
		Assertions.assertEquals(1.0, calculator.calculateMono(Calculator.MonoOperatorModes.tan, java.lang.Math.PI / 4), 0.0000000001);
	}
	
	@Test
	void CalculateMonoLogTest() {
		Calculator calculator = new Calculator();
		Assertions.assertEquals(2.0, calculator.calculateMono(Calculator.MonoOperatorModes.log, 100.0));
	}
	
	@Test
	void CalculateMonoRateTest() {
		Calculator calculator = new Calculator();
		Assertions.assertEquals(.75, calculator.calculateMono(Calculator.MonoOperatorModes.rate, 75.0));
	}
	
	@Test
	void CalculateMonoAbsTest() {
		Calculator calculator = new Calculator();
		Assertions.assertEquals(3.0, calculator.calculateMono(Calculator.MonoOperatorModes.abs, -3.0));
		Assertions.assertEquals(3.0, calculator.calculateMono(Calculator.MonoOperatorModes.abs, 3.0));
	}

}
*/
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

//import simplejavacalculator.Calculator;

@RunWith(JQF.class)
public class CalculatorTest {
	private static Random ran = new Random();

	@Fuzz
	public void calculateBiNormalTest(@From(DoubleGenerator.class)double x, @From(DoubleGenerator.class)double y) {
		double a = x * ran.nextInt();
		double b = y * ran.nextInt();

		Calculator calculator = new Calculator();
		calculator.calculateBi(Calculator.BiOperatorModes.normal, a);
		assertTrue(calculator.calculateBi(Calculator.BiOperatorModes.normal, b).isNaN());
    }

	@Fuzz
    public void calculateBiAddTest(@From(DoubleGenerator.class)double x, @From(DoubleGenerator.class)double y) {
		double a = x * ran.nextInt();
		double b = y * ran.nextInt();

		Calculator calculator = new Calculator();
		calculator.calculateBi(Calculator.BiOperatorModes.add, a);
		assertTrue((a + b) == calculator.calculateBi(Calculator.BiOperatorModes.normal, b));
	}
}