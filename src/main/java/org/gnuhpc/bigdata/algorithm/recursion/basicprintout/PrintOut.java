package org.gnuhpc.bigdata.algorithm.recursion.basicprintout;

import org.junit.Test;

public class PrintOut{

	public void buildLayersTailRecursion(int height){
		if( height == 0 ) return;
		buildLayersTailRecursion(height-1);
		System.out.println(height);
	}

	public void buildLayersHeadRecursion(int height){
		if( height == 0 ) return;
		System.out.println(height);
		buildLayersHeadRecursion(height-1);
	}

	@Test
	public void test(){
		buildLayersTailRecursion(4);
		System.out.println("===================");
		buildLayersHeadRecursion(4);
	}
}
