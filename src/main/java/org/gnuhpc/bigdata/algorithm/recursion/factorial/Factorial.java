package org.gnuhpc.bigdata.algorithm.recursion.factorial;

import org.junit.Assert;
import org.junit.Test;

public class Factorial {

    //这种方法为保存中间结果，不是很直观，但是非常节省空间，而且返回的更快，这个是推荐的做法
	public int fact(int accumulator, int n){
		if( n == 1 ) return accumulator;
		return fact(accumulator*n, n-1);
	}
	
	public int factorial(int n){
		return fact(1, n);
	}

	public int factorial2(int n){
		if (n==1) return 1;
		else return factorial2(n-1)*n;
	}

	@Test
	public void test(){
		Assert.assertEquals(factorial(5), factorial2(5));
	}
}
