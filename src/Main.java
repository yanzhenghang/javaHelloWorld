import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//多线程的程序在ThreadMain中
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        //388.Longest Absolute File Path
        Solution s = new Solution();
        int out = s.lengthLongestPath("yan\n\tzheng\n\thang\n\t\ttest.txt");
        System.out.println("388.Longest Absolute File Path: ");
        System.out.println(out);



        //2019年10月23日，坚持刷题，每日最少一道题

        Tools T = new Tools();
        
        System.out.println("p1_twoSum:");
        int[] nums = {2,7,11,15};
        int[] out1 = s.p1_twoSum(nums,9);
        T.printReslt(out1);
        //0,1

        nums = new int[]{-1,0,1,2,-1,-4};
//        nums = new int[]{0,0,0,0};
        List<List<Integer>> out15 = s.p15_threeSum(nums);
        System.out.println("p15_threeSum:");
        T.printReslt(out15);

        nums = new int[]{-1, 2, 1, -4};
        int out16 = s.p16_threeSumClosest(nums, 1);
        System.out.println("p16_threeSumClosest:");
        System.out.println(out16);

//        nums = new int[]{1, 0, -1, 0, -2, 2};
        nums = new int[]{-1,0,1,2,-1,-4};
//        nums = new int[]{0, 0, 0, 0};
        List<List<Integer>> out18 = s.p18_fourSum(nums, -1);
        System.out.println("p18_fourSum:");
        System.out.println(out18);

        String str = "-91283472332";
        int out8 = s.p8_myAtoi(str);
        System.out.println("p8_myAtoi:");
        System.out.println(out8);

        //无向图的复制
        Node out133 = s.p133_cloneGraph(new Node());
        System.out.println("p133_cloneGraph:");

    }
}


class Tools{
    public void printReslt(int[] nums){
        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i]);
            System.out.print(" ");
        }
        System.out.print("\n");
    }
    public void printReslt(List<List<Integer>> nums){
        for(int i = 0; i < nums.size(); i++){
            for(int j = 0; j < nums.get(i).size(); j++){
                System.out.print(nums.get(i).get(j));
                System.out.print(" ");
            }
            System.out.print("\n");
        }

    }
}
// Definition for a Node.
// for problem 133
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
class Solution {
    //388.Longest Absolute File Path
    public int lengthLongestPath(String input) {
        String[] paths = input.split("\n");
        int[] stack = new int[paths.length+1];
        int maxLen = 0;
        for(String s:paths){
            int lev = s.lastIndexOf("\t")+1, curLen = stack[lev+1] = stack[lev]+s.length()-lev+1;
            if(s.contains(".")) maxLen = Math.max(maxLen, curLen-1);
        }
        return maxLen;


    }

    public int[] p1_twoSum(int[] nums, int target) {
        int[] indexs = new int[2];
        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(hash.containsKey(nums[i])){
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs;
            }
            // 将数据存入 key为补数 ，value为下标
            hash.put(target-nums[i],i);
        }

        return indexs;
    }

//    List<List<Integer>> result = new LinkedList<>();
//    result.add(new LinkedList<>(Arrays.asList(1, 2, 3, 6)));
    public List<List<Integer>> p15_threeSum(int[] nums) {

        Arrays.sort(nums,0, nums.length);

        List<List<Integer>> out = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length - 2; i++) {
            if(nums[i] > 0){
                break;
            }
            if(i > 0 && nums[i] <= nums[i - 1]){continue;}
            int tmp = -nums[i];
            int m = i + 1, n = nums.length - 1;
            while (m < n) {
                int val = nums[m] + nums[n];
                if (val < tmp) {
                    while(m < n && nums[m] == nums[++m]){}
                } else if (val > tmp) {
                    while(m < n && nums[n] == nums[--n]){}
                } else {
                    out.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[m], nums[n])));
                    while(m < n && nums[m] == nums[++m]){}
                    while(m < n && nums[n] == nums[--n]){}
                }
            }
        }
        return out;
    }

    public int p16_threeSumClosest(int[] nums, int target) {
        if(nums.length < 3){return -1;}
        Arrays.sort(nums,0, nums.length);
        int out = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] <= nums[i - 1]){continue;}
            int m = i + 1, n = nums.length - 1;
            while (m < n) {
                int val = nums[i] + nums[m] + nums[n];
                if (val < target) {
                    out = Math.abs(target - val) < Math.abs(out - target) ? val : out;
                    while(m < n && nums[m] == nums[++m]){}
                } else if (val > target) {
                    out = Math.abs(target - val) < Math.abs(out - target) ? val : out;
                    while(m < n && nums[n] == nums[--n]){}
                } else {
                    return target;
                }
            }
        }
        return out;
    }



    public List<List<Integer>> p18_fourSum(int[] nums, int target) {
        List<List<Integer>> out = new ArrayList<List<Integer>>();
        if (nums.length < 4) {
            return out;
        }
        Arrays.sort(nums, 0, nums.length);
        for (int p = 0; p < nums.length - 3; p++) {
            if (p > 0 && nums[p] <= nums[p - 1]) {
                continue;
            }
            for (int i = p+1; i < nums.length - 2; i++) {
                if(i > p + 1 && nums[i] <= nums[i - 1]){continue;}
                int m = i + 1, n = nums.length - 1;
                while (m < n) {
                    int val = nums[p] + nums[i] + nums[m] + nums[n];
                    if (val < target) {
                        while(m < n && nums[m] == nums[++m]){}
                    } else if (val > target) {
                        while(m < n && nums[n] == nums[--n]){}
                    } else {
                        out.add(new ArrayList<Integer>(Arrays.asList(nums[p], nums[i], nums[m], nums[n])));
                        while(m < n && nums[m] == nums[++m]){}
                        while(m < n && nums[n] == nums[--n]){}
                    }
                }
            }
        }
        return out;
    }

    /*
    * 含有32bits溢出判断*/
    public int p8_myAtoi(String str) {
        int out = 0;
        int sign = 1;
        Boolean start = false;
        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);

            if(s == ' '){
                if(start){
                    break;
                }
                continue;
            }else if((s == '+' || s == '-') && !start){
                start = true;
                sign = (s == '-')? -1 : 1;
            }else if(s >= '0' && s <= '9'){
                start = true;
                int tmp = s - '0';
                if(out > Integer.MAX_VALUE / 10 || ((out == Integer.MAX_VALUE / 10) && tmp > 7)){

                    if(sign == 1){
                        return Integer.MAX_VALUE;
                    }else {
                        return Integer.MIN_VALUE;
                    }
                }
                out = (out * 10) + tmp;
            }else{
                break;
            }

        }
        return out * sign;
    }

    public Node p133_cloneGraph(Node node) {
        Node out = new Node();

        Queue<Node> table = new LinkedList<Node>();
        table.add(node);
        while(!table.isEmpty()){
//            out.neighbors = new LinkedList<Node>(table.remove()) ;
        }





        return out;
    }


}

//class Solution {
//    public int lengthLongestPath(String input) {
//        String[] paths = input.split("\n");
//        int[] stack = new int[paths.length+1];
//        int maxLen = 0;
//        for(String s:paths){
//            int lev = s.lastIndexOf("\t")+1, curLen = stack[lev+1] = stack[lev]+s.length()-lev+1;
//            if(s.contains(".")) maxLen = Math.max(maxLen, curLen-1);
//        }
//        return maxLen;
//
//
//    }
//}

//class Main {
////    public static String stringToString(String input) {
////        return JsonArray.readFrom("[" + input + "]").get(0).asString();
////    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            String input = stringToString(line);
//
//            int ret = new Solution().lengthLongestPath(input);
//
//            String out = String.valueOf(ret);
//
//            System.out.print(out);
//        }
//    }
//}