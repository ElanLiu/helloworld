package utils;

import org.apache.log4j.Logger;

/**
 * 
 * @author yu.liuyly
 *
 *计算质数，该方法的思想很值得借鉴：
 *
 *1 假定所有的自然数（int）都是质数，所以prime = true
 *
 *2 设置一个计数器，用于计算当前是第几个质数，count
 *
 *3 合数可以分解成几个质数的积，利用这个方法，每个自然数都模之前的质数
 *
 *4 如果余0，说明是合数，break，计数器不操作
 *
 *5 如果不余0，说明是质数，计数器+1，写入数组
 */


public class CalculatePrimes{
	
	private static final Logger logger = Logger.getLogger(CalculatePrimes.class);

	//需要多少质数
	private static final int MAX_PRIME = 100000;
	
	
	//用于某种完成条件
	private boolean finished = false;
	
	public void execute(){
		int[] primes = new int[MAX_PRIME];
		int count = 0;
		
		for(int i=2; count<MAX_PRIME; i++){
			if(finished)
				break;
			
			boolean prime = true;
			
			for(int j=0; j<count; j++){
				if(i % primes[j] == 0){
					prime = false;
					break;
				}	
			}
			
			if(prime){
				primes[count++] = i;
				logger.info("Found prime: " + i);
			}
		}
	}

}
