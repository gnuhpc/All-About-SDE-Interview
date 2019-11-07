package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.unionfind.QuickUnion;
import org.gnuhpc.bigdata.datastructure.unionfind.UnionFind;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

public class AccountsMerge721 {
    //Graph + DFS
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //无向图
        Map<String, Set<String>> graph = new HashMap<>();  //<email node, neighbor nodes>
        Map<String, String> emailUsername = new HashMap<>();        //<email, username>

        for (List<String> account : accounts) {
            String userName = account.get(0);
            // Build Mapping
            IntStream.range(1,account.size()).forEach(i->emailUsername.put(account.get(i),userName));

            // Build the graph;
            //First, process the situation in which the only one email account exists for one name
            // Just Create a empty set as a lonely island
            if (account.size() == 2){
                graph.put(account.get(1), new HashSet<>());
            }

            //Second, build the graph if the number of email is not only one.
            for (int i = 1; i < account.size()-1; i++) {
                if (!graph.containsKey(account.get(i))) {
                    graph.put(account.get(i), new HashSet<>());
                }

                if (!graph.containsKey(account.get(i+1))) {
                    graph.put(account.get(i+1), new HashSet<>());
                }

                graph.get(account.get(i)).add(account.get(i + 1));
                graph.get(account.get(i + 1)).add(account.get(i));
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> res = new LinkedList<>();
        // DFS search the graph, 逐个的Email找到和他相连的其他Email
        // DFS 无权无向图
        for (String email : emailUsername.keySet()) {
            List<String> list = new LinkedList<>();

            if (visited.contains(email)) continue;

            //开始DFS
            visited.add(email);
            dfs(graph, email, visited, list);

            //根据题意找到这个列表还得排序，然后把对应的Username加在第一位
            Collections.sort(list);
            String userName = emailUsername.get(email);
            list.add(0, userName);
            res.add(list);
        }

        return res;
    }

    public void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> list) {
        list.add(email);
        for (String next : graph.get(email)) {
            if (visited.add(next)) {
                dfs(graph, next, visited, list);
            }
        }
    }

    //Method 2: Union Find 并查集ID映射

    public ArrayList accountsMerge2(List<List<String>> accounts) {
        UnionFind uf = new UnionFind(10001);
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToID = new HashMap<>();

        int id = 0;
        for (List<String> account: accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, name);
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }

                //构建连接
                uf.union(emailToID.get(account.get(1)), emailToID.get(email));
            }
        }

        //查找一组Email
        Map<Integer, List<String>> ans = new HashMap<>();
        for (String email: emailToName.keySet()) {
            int index = uf.find(emailToID.get(email));
            ans.computeIfAbsent(index, x-> new ArrayList<>()).add(email);
        }

        //排序，添加Name
        for (List<String> component: ans.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }
        return new ArrayList<>(ans.values());
    }


    @Test
    public void test(){
        List<List<String>> accounts = new ArrayList<>();
        List<String> a1 = new ArrayList<>();
        a1.addAll(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
        List<String> a2 = new ArrayList<>();
        a2.addAll(Arrays.asList("John","johnsmith@mail.com","john00@mail.com"));
        List<String> a3 = new ArrayList<>();
        a3.addAll(Arrays.asList("Mary","mary@mail.com"));
        List<String> a4 = new ArrayList<>();
        a4.addAll(Arrays.asList("John","johnnybravo@mail.com"));

        accounts.add(a1);
        accounts.add(a2);
        accounts.add(a3);
        accounts.add(a4);

        accountsMerge2(accounts);

    }
}
