package nyp_8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JUnitTest {
	
	Generator generator = new Generator();
	
	
	@Test
	public void doesContainTwoRepeatedOperators() {
		
		String eq = generator.GenerateEquation(8).toString();
		assertEquals(false, eq.contains("++") && eq.contains("--") && eq.contains("**") && eq.contains("//") && eq.contains("==") );
		
	}
	
	@Test
	public void doesContainEquals() {
		String equ = generator.concatEquation(generator.GenerateEquation(8));
		assertEquals(true, equ.contains("="));
	}
	
	@Test
	public void isValid() {
		
		String equ2 = generator.concatEquation(generator.GenerateEquation(8));
		assertEquals(8,equ2.length());	
	}
	
	@Test
	public void isAnyOperation(){
		
		String equ3 = generator.concatEquation(generator.GenerateEquation(8));
		assertEquals(true, equ3.contains("+") || equ3.contains("-") || equ3.contains("*") || equ3.contains("/"));
	}
	
}
