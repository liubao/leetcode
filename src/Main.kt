import java.util.*
import kotlin.collections.HashSet
import kotlin.math.max

object Main {

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

    /**
     *删除链表的倒数第N个节点 https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null
        var nodeToDelete: ListNode? = head
        var nodeToDeletePre: ListNode? = head
        var h = head
        var index = 0

        while (h != null) {
            if (index < n + 1) {
            } else {
                nodeToDeletePre = nodeToDeletePre?.next
            }
            if (index < n) {
            } else {
                nodeToDelete = nodeToDelete?.next
            }
            index++
            h = h.next

        }
        if (index == 1) return null
        if (index == 2) {
            if (n == 1) {
                head.next = null
                return head
            } else {
                return head.next
            }
        }
        if (nodeToDeletePre == head && nodeToDelete == head) return head.next
        if (nodeToDeletePre == null) return nodeToDelete?.next
        nodeToDeletePre.next = nodeToDeletePre.next?.next
        return head
    }


    /**
     * x 的平方根 二分查找 https://leetcode-cn.com/problems/sqrtx/
     */
    fun mySqrt(x: Int): Int {
        if (x == 1) return 1
        var low = 1
        var high = x
        var mid: Int = ((high + low) / 2).toInt()
        while (low < high) {
            if (mid.toLong() * mid <= x && (mid + 1).toLong() * (mid + 1) > x) return mid
            if (mid.toLong() * mid <= x) {
                low = mid + 1
            } else {
                high = mid - 1
            }
            mid = (high + low) / 2
        }
        return mid
    }

    /**
     * 二叉树的中序遍历 https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     */

    fun inorderTraversal(root: TreeNode?): List<Int> {
        val list = ArrayList<Int>()
        if (root == null) return list
        inorder(list, root)
        return list
    }

    fun inorder(list: ArrayList<Int>, node: TreeNode?) {
        if (node == null) return
        inorder(list, node?.left)
        list.add(node.`val`)
        inorder(list, node?.right)
    }

    /**
     * 二叉搜索树的最小绝对差  https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
     */

    var result = 0
    var pre = -1

    fun getMinimumDifference(root: TreeNode?): Int {
        if (root == null) return Int.MAX_VALUE
        dfs2(result, root)
        return result
    }

    fun dfs2(v: Int, node: TreeNode?) {
        if (node == null) return
        dfs2(v, node?.left)
        if (pre == -1) {
            pre = node.`val`
        } else {
            result = Math.min(result, Math.abs(node.`val` - pre))
            pre = node.`val`
        }
        dfs2(v, node?.right)
    }

    /**
     * 118. 杨辉三角 https://leetcode-cn.com/problems/pascals-triangle/
     */
    fun generate(numRows: Int): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        if (numRows == 0) return result
        result.add(arrayListOf(1))
        for (i in 1 until numRows) {
            if (i == 1) {
                result.add(arrayListOf(1, 1))
            } else {
                val pre = result.get(i - 1)
                val newList = ArrayList<Int>()
                for (j in 0 until pre.size + 1) {
                    if (j == 0) {
                        newList.add(1)
                    } else if (j == pre.size) {
                        newList.add(1)
                    } else {
                        newList.add(pre[j] + pre[j - 1])
                    }
                }
                result.add(newList)
            }
        }
        return result
    }

    /**
     *844. 比较含退格的字符串 https://leetcode-cn.com/problems/backspace-string-compare/
     */

    fun backspaceCompare(S: String, T: String): Boolean {
        if (S.isNullOrEmpty() || T.isNullOrEmpty()) return true
        val sStack = Stack<Char>()
        val tStack = Stack<Char>()
        fun doSome(string: String, stack: Stack<Char>) {
            for (s in string) {
                if (s == '#') {
                    if (!stack.isEmpty())
                        stack.pop()
                } else {
                    stack.push(s)
                }
            }
        }
        doSome(S, sStack)
        doSome(T, tStack)
        while (!sStack.isEmpty() && !tStack.isEmpty()) {
            if (sStack.pop() != tStack.pop()) {
                return false
            }
        }
        if (!sStack.isEmpty() || !tStack.isEmpty()) return false
        return true
    }


    @JvmStatic
    fun main(args: Array<String>) {
        println(backspaceCompare("ab#c", "ad#c"))
        println(backspaceCompare("ab##", "c#d#"))
        println(backspaceCompare("a##c", "#a#c"))
        println(backspaceCompare("a#c", "b"))
    }
}