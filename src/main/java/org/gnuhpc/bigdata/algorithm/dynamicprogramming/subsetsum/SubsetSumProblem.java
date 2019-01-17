package org.gnuhpc.bigdata.algorithm.dynamicprogramming.subsetsum;

public class SubsetSumProblem {
	
	private boolean[][] dpTable;
	private int[] numbers;
	private int sum;
	
	public SubsetSumProblem(int[] numbers, int sum){
		this.numbers = numbers;
		this.sum = sum;
		this.dpTable = new boolean[numbers.length + 1][sum + 1];	
	}
	
	public void solveProblem() {
				
//		for(int i=0;i<=this.sum;i++){ // if sum is not zero and subset is 0 -> no feasible solution
//			this.dpTable[0][i] = false;
//		}
		
		for(int i=0;i<=this.numbers.length;i++){ // if sum is 0 the we can make the empty subset to make sum 0
			this.dpTable[i][0]=true;
		}
		
		for(int rowIndex=1;rowIndex<=numbers.length;++rowIndex){
			for(int columnIndex=1;columnIndex<=sum;++columnIndex){				
				
				if( columnIndex < numbers[rowIndex-1] ){
					this.dpTable[rowIndex][columnIndex] = this.dpTable[rowIndex-1][columnIndex];
				}else{
					if( this.dpTable[rowIndex-1][columnIndex] ){
						this.dpTable[rowIndex][columnIndex] = true;
					}else{
						this.dpTable[rowIndex][columnIndex] = this.dpTable[rowIndex-1][columnIndex-numbers[rowIndex-1]];
					}
				}				
			}
		}
	}
	
	public void hasSolution(){
		
		for(int i=0;i<this.numbers.length+1;i++){
			for(int j=0;j<this.sum+1;j++){
				System.out.print(dpTable[i][j]+" ");
			}
			
			System.out.println();
		}
		
		if( this.dpTable[numbers.length][sum] ){
			System.out.println("There is a solution for the problem...");
		}else{
			System.out.println("No feasible solution for the problem...");
		}	
	}
	
	public void showSums(){
		
		int columnIndex = this.sum;
		int rowIndex = this.numbers.length;
		
		while( columnIndex > 0 || rowIndex > 0 ){
			
			if( this.dpTable[rowIndex][columnIndex] == this.dpTable[rowIndex-1][columnIndex] ){
				rowIndex = rowIndex - 1;
			}else{
				System.out.println("We take item: " + numbers[rowIndex-1]);
				columnIndex = columnIndex - numbers[rowIndex-1];
				rowIndex = rowIndex - 1;
			}
		}
	}
}
