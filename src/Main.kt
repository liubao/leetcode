import java.util.*
import kotlin.collections.HashSet
import kotlin.math.max

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val l1 = ListNode(1).also { it.next = ListNode(3).also { it.next = ListNode(5) } }
        val l2 = ListNode(2).also { it.next = ListNode(4).also { it.next = ListNode(6) } }
        println(mergeTwoLists(l1, l2).also {
            var v = it
            while (v != null) {
                println(v.`val`)
                v = v.next
            }
        })
    }

    /**
     *整数反转 https://leetcode-cn.com/problems/reverse-integer/
     */
    fun reverse(x: Int): Int {
        var myX = x
        var temp = 0L
        while (myX != 0) {
            val pop = myX % 10
            temp = temp * 10 + pop
            if (temp > Int.MAX_VALUE || temp < Int.MIN_VALUE) {
                return 0
            }
            myX /= 10
        }
        return temp.toInt()
    }

    /**
     * 最长公共前缀https://leetcode-cn.com/problems/longest-common-prefix/
     */
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        val firstStr = strs[0]
        var firstLength = firstStr.length
        var result = ""
        while (firstLength != 0) {
            val currentIndex = firstStr.length - firstLength
            val currentStr = firstStr[currentIndex]

            for (i in 1 until strs.size) {
                val cu = strs[i]
                if (cu.length > currentIndex && cu.get(currentIndex) != currentStr) {
                    return result
                }
                if (cu.length == currentIndex)
                    return result
            }
            result += currentStr
            firstLength--
        }
        return result
    }

    /**
     *有效的括号https://leetcode-cn.com/problems/valid-parentheses/
     */
    fun isValid(s: String): Boolean {
        if (s.isEmpty()) return true
        val stack = LinkedList<String>()
        val size = s.length
        val map = hashMapOf("(" to ")", "{" to "}", "[" to "]")
        for (i in 0 until size) {
            val c = s[i].toString()
            if (map[stack.peek()] == c) {
                stack.pop()
                continue
            }
            stack.push(c)
        }
        return stack.isEmpty()
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    /**
     * 合并两个有序链表
     */

    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) return l2
        if (l2 == null) return l1
        if (l1.`val` < l2.`val`) {
            l1.next = mergeTwoLists(l1.next, l2)
            return l1
        } else {
            l2.next = mergeTwoLists(l1, l2.next)
            return l2
        }
    }

    /**
     * 环形链表
     */

    class Solution {
        fun hasCycle(head: ListNode?): Boolean {
            if (head == null) return false
            var slow = head
            var fast = head.next
            while (slow != fast) {
                if (slow?.next == null || fast?.next == null) {
                    return false
                }
                slow = slow?.next
                fast = fast?.next?.next
            }
            return true
        }
    }

    /**
     * 最后一个单词的长度
     */

    fun lengthOfLastWord(s: String): Int {
        var end = s.length - 1
        while (end >= 0 && s.get(end) == ' ') end--
        var start = end
        while (start >= 0 && s.get(start) != ' ') start--
        return end - start
    }

    /**
     * 合并两个有序数组
     */

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var vN = n - 1
        var vM = m - 1
        var index = m + n - 1
        while (index >= 0) {
            if (vN < 0) {
                nums1[index] = nums1[vM]
                vM--
                index--
                continue
            }
            if (vM < 0) {
                nums1[index] = nums2[vN]
                vN--
                index--
                continue
            }
            if (nums1[vM] < nums2[vN]) {
                nums1[index] = nums2[vN]
                vN--
            } else {
                nums1[index] = nums1[vM]
                vM--
            }
            index--
        }
    }

    /**
     * 环形链表 II
     */
    fun detectCycle(head: ListNode?): ListNode? {
        if (head == null) return null
        val hashSet = HashSet<ListNode>()
        var h = head
        while (h != null) {
            if (!hashSet.add(h)) return h
            h = h.next
        }
        return null
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null

    }

    /**
     * 相同的树
     */

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p?.`val` != q?.`val`) return false
        val l = isSameTree(p?.left, q?.left)
        val r = isSameTree(p?.right, q?.right)
        return l && r
    }

    /**
     * 二叉树的最大深度
     */
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        val l = maxDepth(root.left)
        val r = maxDepth(root.right)
        return Math.max(l, r) + 1
    }
}