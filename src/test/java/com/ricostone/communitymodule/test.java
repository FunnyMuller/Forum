package com.ricostone.communitymodule;

import org.junit.Test;

public class test {
   @Test
   public void test(){
	  int[] nums = new int[2025];
	  for(int i = 1; i <= 2024; i++){
		 for(int j = 1; j <= 2024; j++){
			if(j % i == 0){
			   nums[j] = (nums[j] + 1)%2;
			}
		 }
	  }
	  int count = 0;
	  for(int i = 0; i < 2024; i++){
		 if(nums[i] == 1){
			count++;
		 }
	  }
	  System.out.println(count);
   }
}
