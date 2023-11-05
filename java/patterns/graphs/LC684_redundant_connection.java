// Union Find
// Time: O(n^2), amortized O(n) with path compression
// Space: O(n)
/**
    The Union Find algorithm. It has int[] parent as its main data structure, and find() and union() for its functions.
    Typically it also uses a rank array.

    The rank of a node is the depth of the tree. We only increase the rank if a tree with the same rank is added to it.
    The reason we use ranks is to keep the trees as flat as possible.

    The main idea is that we connect nodes by first finding their roots (or most leading parent nodes), then if they are not
    already connected, connect them. However, we make the higher ranking root the parent.

    Path compression will change every node on the path to point directly to the root.
 */


class Solution {
    int[] parent; // core data structure

    public int[] findRedundantConnection(int[][] edges) {
        parent = new int[edges.length];
        for (int i=0;i<edges.length;i++){ // initalize each node's parent is set to itself
            parent[i] = i + 1;
        }

        for (int[] edge : edges){ // iterates all edges
            if (find(edge[0]) == find(edge[1])){ // if the edges are already connected, then the current edge is redundant
                return edge; // return the redundant connection
            } else {
                union(edge[0], edge[1]); // if the edges are not connected, then connect them
            }
        }
        return new int[2]; // returns an empty array if no redundant connection found
    }

    public int find(int x){ // recursively looks for the root of the set that a given node x belongs to.
    // it uses the parents links to find the root. This version does not use path compression.
        if (x == parent[x-1]) return x;
        return find(parent[x-1]);
    }

    public void union(int x, int y){ // sets the parent of the root of one node y to be the root of the other node x,
    // effectively merging the sets.
        parent[find(y)-1] = find(x);
    }
}