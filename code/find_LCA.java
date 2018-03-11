/*
Problem statement

Develop a Java application which can read a simple family tree in .dot (graphviz) format and compute the closest common ancestor of two people. For the .dot file, use Listing 1 from this PDF file. Given this file as the tree to work from, and the two inputs "Jon" and "Daenerys", the result should be "Aerys II The Mad". Or given the two inputs "Sansa" and "Arya", the result should be "Eddard".

If there are multiple common ancestors of equal distance, output all of them.

The Java application should take three parameters on the command line:

    name of .dot file
    name of person 1
    name of person 2

The result should be written to standard output.
*/

import java.io.File;
import java.util.Set;

import org.jgrapht.io.*;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.alg.NaiveLcaFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.Graph;


public final class find_LCA
{
  //  private Main() {}
      public static void main(String[] args)
       {
        /* Read input parameters */
        if (args.length != 3)
         {
            System.out.println("Please enter only three input parameters:\n" +
              " - path of .dot file\n" + " - name of person 1\n" + " - name of person 2");
       }
        File infile = new File(args[0]);
        String p1 = args[1];
        String p2 = args[2];

        /*graph building from infile */
        Graph<String, DefaultEdge> newGOT = new SimpleDirectedGraph<>(DefaultEdge.class);
        getDOTgraph(newGOT, infile);

        /* To check for nodes availability in the graph */
        if (!newGOT.containsVertex(p1))
        {
            System.out.println(p1 + " is not present in the input graph");
            return;
        }
        if (!newGOT.containsVertex(p2))
        {
            System.out.println(p2 + " is not present in the input graph");
            return;
        }

        /* To check for any cycle in the graph and return if true, as class NaiveLcaFinder is used for DAGs*/
        CycleDetector<String, DefaultEdge> detect = new CycleDetector<>(newGOT);
        if (detect.detectCycles())
        {
            System.out.println("The input graph has cycle in it, please input an acyclic graph file");
            return;
        }

        /* To find the Lowest Common Ancestor of given two input nodes */
        NaiveLcaFinder<String, DefaultEdge> lcaFinder = new NaiveLcaFinder<>(newGOT);
        String lca = lcaFinder.findLca(p1, p2);
        System.out.println(lca.toString());

     }

    private static void getDOTgraph(Graph<String, DefaultEdge> graph, File inputFile)
     {
        VertexProvider<String> VP = (label, attributes) -> label;
        EdgeProvider<String, DefaultEdge> EP = (from, to, label, attributes) -> new DefaultEdge();
        GraphImporter<String, DefaultEdge> importer = new DOTImporter<>(VP, EP);
        try {
            importer.importGraph(graph, inputFile);
            }
            catch (ImportException e)
            {
            e.printStackTrace();
            }
     }
}
