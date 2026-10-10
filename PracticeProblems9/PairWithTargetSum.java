import java.util.*;

public class Main {
    static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(target - num))
                return true;

            seen.add(num);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        System.out.println(hasPairWithSum(nums, 9));

        int[] nums2 = {3, 4, 6};
        System.out.println(hasPairWithSum(nums2, 20));
    }
}