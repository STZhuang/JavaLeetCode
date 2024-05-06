class leetcode {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int p1 = m - 1, p2 = n - 1, p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] >= nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p--;
            p2--;
        }
    }

    /*
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     */
    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int p1 = 0, p2 = 0;
        while (p2 < length) {
            if (nums[p2] != val) {
                nums[p1] = nums[p2];
                p1++;
            }
            p2++;
        }
        return p1;
    }

    public int removeDuplicates(int[] nums) {
        /*
         *
         * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持
         * 一致 。然后返回 nums 中唯一元素的个数。
         *
         * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
         *
         * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums
         * 的大小不重要。
         * 返回 k 。
         */
        int length = nums.length;
        if (length <= 1) {
            return length;
        }
        int p1 = 0;
        for (int p2 = 1; p2 < length; p2++) {
            if (nums[p1] != nums[p2]) {
                nums[p1 + 1] = nums[p2];
                p1++;
            }
        }
        return p1 + 1;
    }

    public int removeDuplicates_2(int[] nums) {
        int length = nums.length;
        if (length <= 2) {
            return length;
        }
        int p1 = 2;
        for (int p2 = 2; p2 < length; p2++) {
            if (nums[p1 - 2] != nums[p2]) {
                nums[p1] = nums[p2];
                p1++;
            }
        }
        return p1;
    }

    public int majorityElement(int[] nums) {
        int count = 0;
        Integer numbers = null;
        for (int i : nums) {
            if (count == 0) {
                numbers = i;
            }
            if (numbers == i) {
                count++;
            } else {
                count--;
            }
        }
        return numbers;
    }

    public void rotate(int[] nums, int k) {
        int length = nums.length;
        int[] nums2 = new int[length];
        for (int i = 0; i < nums2.length; i++) {
            nums2[(i + k) % length] = nums[i];
        }
        System.arraycopy(nums2, 0, nums, 0, length);
    }

    public int maxProfit(int[] prices) {
        /*
         * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
         * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
         * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
         */
        int ans = Integer.MAX_VALUE, profit = 0;
        for (int i : prices) {
            ans = Math.min(i, ans);
            profit = Math.max(profit, i - ans);
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int[] sold = new int[n];
        int[] hold = new int[n];
        sold[0] = 0;
        hold[0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            sold[i] = Math.max(sold[i - 1], hold[i - 1] + prices[i]);
            hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i]);
        }
        return sold[n - 1];
    }

    /**
     * @param head 链表的输入
     * @param k    移动的位置
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int count = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            count++;
        }

        k = k % count;
        if (k == 0) {
            return head;
        }
        tail.next = head;
        for (int i = 0; i < count - k; i++) {
            tail = tail.next;
        }
        ListNode result = tail.next;
        tail.next = null;
        return result;
        /*
         if (head == null || head.next == null) {
         return head; // 如果链表为空或只有一个节点，则无需旋转
         }

         int count = 1;
         ListNode tail = head;
         // 找到链表的尾节点，并计算链表长度
         while (tail.next != null) {
         tail = tail.next;
         count++;
         }

         k = k % count; // 处理k大于链表长度的情况
         if (k == 0) {
         return head; // 如果k等于链表长度的整数倍，则无需旋转
         }

         tail.next = head; // 形成循环链表

         // 找到新的尾节点（即旋转后的头节点的前一个节点）
         for (int i = 0; i < count - k; i++) {
         tail = tail.next;
         }

         ListNode newHead = tail.next; // 新的头节点
         tail.next = null; // 断开链表，形成新的链表尾部

         return newHead;
         */
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0, head);
        ListNode prev = dummyHead, p1, p2, temp;
        while (prev.next != null && prev.next.next != null) {

            p1 = prev.next;
            p2 = prev.next.next;
            temp = p2.next;

            prev.next = p2;
            p1.next = temp;
            p2.next = p1;
            prev = p1;
        }

        return dummyHead.next;
    }
}


public class sol {
    public static void main(String[] args) {
        ListNode solution = new ListNode();
        int[] nums = {1, 2, 3, 4, 5};
//        int n = solution.maxProfit2(nums);
//        System.out.println(n);
    }
}

