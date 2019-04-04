package com.company;

// Or shuffle: Collections.shuffle(Arrays.asList(elements));


import java.util.PriorityQueue;
import java.util.Queue;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int maxNum = Integer.MIN_VALUE;
        Queue<Integer> queue = new PriorityQueue<>();


        for(int i=nums.length-1; i>=0; i--){
            if(maxNum > nums[i]) {
                int index = 1;
                boolean flag = true;
                while(!queue.isEmpty()) {
                    int digit = queue.poll();
                    if(digit > nums[i] && flag) {
                        queue.offer(nums[i]);
                        nums[i] = digit;
                        flag = false;
                    } else {
                        nums[i+index] = digit;
                        index ++;
                    }
                }
                break;
            }
            else {
                maxNum = Math.max(maxNum, nums[i]);
                queue.offer(nums[i]);
            }
        }

        int index = 0;
        while(!queue.isEmpty()) {
            nums[index++] = queue.poll();
        }
        return;
    }
}
