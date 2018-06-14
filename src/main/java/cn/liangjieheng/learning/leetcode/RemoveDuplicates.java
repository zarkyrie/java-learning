package cn.liangjieheng.learning.leetcode;

public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int len = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[len]) {
                nums[len + 1] = nums[i];
                len++;
            }
        }
        return len + 1;
    }

    public static void main(String[] args) {
        int[] is = {0,0,1,1,2,2};
        System.out.println(removeDuplicates(is));
    }
}
