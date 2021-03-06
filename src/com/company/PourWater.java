package com.company;


public class PourWater {
    public void pourWater(int[] heights, int water, int location) {
        int[] waters = new int[heights.length];
        int pourLocation;
        while (water > 0) {
            int left = location - 1;

            // Left
            while (left >= 0) {
                if (heights[left] + waters[left] > heights[left + 1] + waters[left+ 1]) {
                    break;
                }
                left --;
            }
            if (heights[left + 1] + waters[left + 1] < heights[location] + waters[location]) {
                pourLocation = left + 1;
                waters[pourLocation]++;
                water--;
                continue;
            }

            // Right
            int right = location + 1;
            while (right < heights.length) {
                if (heights[right] + waters[right] > heights[right - 1] + waters[right - 1]) {
                    break;
                }
                right++;
            }
            if (heights[right - 1] + waters[right - 1] < heights[location] + waters[location]) {
                pourLocation = right - 1;
                waters[pourLocation]++;
                water--;
                continue;
            }

            // Center
            pourLocation = location;
            waters[pourLocation]++;
            water--;

        }
        print(heights, waters);
    }

    private void print(int[] heights, int[] waters) {
        int n = heights.length;
        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
        }
        for (int height = maxHeight; height >= 0; height--) {
            for (int i = 0; i < n; i++) {
                if (height <= heights[i]) {
                    System.out.print("+");
                } else if (height > heights[i] && height <= heights[i] + waters[i]) {
                    System.out.print("W");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
