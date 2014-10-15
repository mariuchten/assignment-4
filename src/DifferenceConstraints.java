
package DifferenceConstraints;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;


public class DifferenceConstraints {
    
    
  public static void main(String[] args) throws IOException  {
       
      DifferenceConstraints r = new DifferenceConstraints();
   
        r.relax();
        int min = 1000;
        if (r.cycle()) {
            for (int i = 0; i < r.n; i++) {
                if (r.d[i] > 0) {
                    r.d[i] = 0;
                }
                if (r.d[i] < min) {
                    min = r.d[i];
                }

            }
            for (int i = 0; i < r.n; i++) {
                int num = i + 1;
                System.out.println("x" + num + " ==> " + (r.d[i] - min));
            }
        } else {
            System.out.println(" There is a negative edge cycle ");
        }

           }
 

    
    
    
    LinkedList<Edge> edges;
    int d[], p[];
    int n, e, s;
    final int INFINITY = 999;

    private static class Edge {

        int u, v, w;

        public Edge(int a, int b, int c) {
            u = a;
            v = b;
            w = c;
        }
    }

    DifferenceConstraints() throws IOException {
        BufferedReader br = null;
        String line = "";
        String fileSplitBy = " ";
        int x = 0;
        String file = "example.txt";
        int item = 0;
        String x1 = "";
        String x2 = "";
        edges = new LinkedList<Edge>();
        br = new BufferedReader(new FileReader(file));

        while (((line = br.readLine()) != null)) {

            String[] dj = line.split(fileSplitBy);
            x1 = dj[0].substring(1);
            x2 = dj[1].substring(1);
            int i = Integer.parseInt(x1) - 1;
            int j = Integer.parseInt(x2) - 1;
            item = Integer.parseInt(dj[2]);
        
            n = 5;
            edges.add(new Edge(i, j, -item));
        }

        e = edges.size();
        d = new int[n];
        p = new int[n];
        
        
        s = 0;
        
    }

    void relax() {
        int i, j;
        for (i = 0; i < n; ++i) {
            d[i] = INFINITY;
            p[i] = -1;
        }

        d[s] = 0;

        for (i = 0; i < n - 1; ++i) {
            for (j = 0; j < e; ++j) { //here i am calculating the shortest path
                if (d[edges.get(j).u] + edges.get(j).w < d[edges.get(j).v]) {
                    d[edges.get(j).v] = d[edges.get(j).u] + edges.get(j).w;
                    p[edges.get(j).v] = edges.get(j).u;

                }
            }
        }
    }

    boolean cycle() {
        int j;
        for (j = 0; j < e; ++j) {
            if (d[edges.get(j).u] + edges.get(j).w < d[edges.get(j).v]) {
                return false;
            }
        }
        return true;
    }
  
}
