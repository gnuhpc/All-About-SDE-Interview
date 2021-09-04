package org.gnuhpc.interview.leetcode.solutions;

public class ReorderLogFiles937 {

    /*
    func isDigit(c byte) bool {
    return c >= '0' && c <= '9'
}

func reorderLogFiles(logs []string) []string {
    sort.SliceStable(logs, func (i, j int) bool {
        l1 := strings.SplitN(logs[i], " ", 2)
        l2 := strings.SplitN(logs[j], " ", 2)

        if isDigit(l1[1][0]) && isDigit(l2[1][0]) {
            return i < j
        } else if isDigit(l1[1][0]) && !isDigit(l2[1][0]) {
            return false
        } else if !isDigit(l1[1][0]) && isDigit(l2[1][0]) {
            return true
        } else if l1[1] == l2[1] {
            return l1[0] < l2[0]
        }

        return l1[1] < l2[1]

    })
    return logs
}
     */
}
