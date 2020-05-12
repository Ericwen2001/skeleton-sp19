public class UnionFind {
    int[] parentIndex;
    // TODO - Add instance variables?

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parentIndex =  new int[n];
        for (int i : parentIndex) {
            i = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= parentIndex.length) {
            throw new IllegalArgumentException(vertex + "is not a valid index.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
       int size = 0;
       for (int i : parentIndex) {
           if (connected(i,v1)) {
               size += 1;
           }
       }
       return size;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {

        return parentIndex[v1] == -1 ? -sizeOf(v1) : parentIndex[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if (sizeOf(v1) > sizeOf(v2)) {
            parentIndex[v2] = find(v1);
        } else {
            parentIndex[v1] = find(v2);
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int pointer1 = vertex;
        while (parentIndex[pointer1] != -1) {
            pointer1 = parent(pointer1);
        }
        parentIndex[vertex] = pointer1;
        return pointer1;
    }

}
