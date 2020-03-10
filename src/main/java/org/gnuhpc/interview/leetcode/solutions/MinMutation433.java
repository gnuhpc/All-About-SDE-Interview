package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

public class MinMutation433 {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) return -1;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);

        int step = 0;

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                for (String next : getNextGene(cur, bankSet)) {
                    if (next.equals(end)) return step;
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
        }

        return -1;
    }

    public Set<String> getNextGene(String gene, Set<String> bankSet) {
        Set<String> res = new HashSet<>();

        char[] geneChars = {'A', 'C', 'G', 'T'};
        char[] genes = gene.toCharArray();

        for (int i = 0; i < genes.length; i++) {//go though every char of gene
            char tmp = genes[i];
            for (int j = 0; j < geneChars.length; j++) {
                if (genes[i] != geneChars[j]) {
                    genes[i] = geneChars[j];
                    String tmpGene = new String(genes);
                    if (bankSet.contains(tmpGene)) {
                        res.add(tmpGene);
                    }
                }
            }
            genes[i] = tmp;
        }

        return res;
    }

    @Test
    public void test() {
        System.out.println(minMutation("AAAACCCC", "CCCCCCCC", new String[]{
                "AAAACCCA", "AAACCCCA",
                "AACCCCCA", "AACCCCCC",
                "ACCCCCCC", "CCCCCCCC",
                "AAACCCCC", "AACCCCCC"}));
    }

}
