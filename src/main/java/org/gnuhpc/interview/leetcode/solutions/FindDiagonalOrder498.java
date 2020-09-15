package org.gnuhpc.interview.leetcode.solutions;

public class FindDiagonalOrder498 {
    class Pair{
        public int x;
        public int y;

        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj)
        {

            if(this == obj)
                return true;

            if(obj == null || obj.getClass()!= this.getClass())
                return false;

            Pair pair = (Pair) obj;


            return (pair.x == this.x && pair.y == this.y);
        }

        @Override
        public int hashCode()
        {

            return this.x*31 + this.y;
        }
    }
    int n;
    int m;
    public int[] findDiagonalOrder(int[][] matrix) {
        n = matrix.length;
        m = matrix[0].length;

        int[] res = new int[n*m];

        LinkedList<Pair> q = new LinkedList<>();
        Pair init = new Pair(0,0);
        q.offer(init);
        Set<Pair> visited = new HashSet<>();

        boolean direction = false;
        int i = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int j = 0; j< size;j++){
                Pair p = null;
                if(direction){
                    p = q.removeFirst();

                    if(visited.contains(p)) break;
                    res[i++] = matrix[p.x][p.y];
                    visited.add(p);

                    Pair newP1 = new Pair(p.x,p.y+1);
                    if(isValid(newP1)){
                        q.offer(newP1);
                    }

                    Pair newP2 = new Pair(p.x+1,p.y);
                    if(isValid(newP2)){
                        q.offer(newP2);
                    }
                } else{
                    p = q.removeLast();
                    res[i++] = matrix[p.x][p.y];

                    Pair newP1 = new Pair(p.x,p.y+1);
                    if(isValid(newP1) && !visited.contains(newP1)){
                        q.offer(newP1);
                        visited.add(newP1);
                    }

                    Pair newP2 = new Pair(p.x+1,p.y);
                    if(isValid(newP2)&& !visited.contains(newP2)){
                        q.offer(newP2);
                        visited.add(newP2);
                    }
                }
            }
            direction = !direction;
        }
        return res;
    }

    boolean isValid(Pair pair){
        return (pair.x>=0 && pair.x<n) && (pair.y>=0 && pair.y<m);
    }
}
