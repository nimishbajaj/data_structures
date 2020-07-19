package week1;

import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeHeight {
		int n;
		int parent[];
		Node nodes[];
		Node root;
		int maxHeight;

		class Node {
			int val;
			List<Node> child;

			public Node(int val) {
				this.val = val;
				this.child = new ArrayList<>();
			}

			public void addChild(Node node){
				child.add(node);
			}
		}
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			nodes = new Node[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
				nodes[i] = new Node(i);
			}

			for (int i = 0; i < n; i++) {
				if(parent[i]==-1) {
					root = nodes[i];
					continue;
				}
				nodes[parent[i]].addChild(nodes[i]);
			}
		}

		int computeHeight() {
                        // Replace this code with a faster implementation
			maxHeight = 0;

			maxDepth(root, 1);

			return maxHeight;
		}

		void maxDepth(Node root, int depth){
			if(root==null) return;
			maxHeight = Math.max(maxHeight, depth);
			for(Node node: root.child){
				maxDepth(node, depth+1);
			}
		}


	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
