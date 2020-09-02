import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class test {
public static void test1() {
    Graph graph = new Graph();
    System.out.println(graph.size());
    graph.addVertex("b");
    graph.addVertex("a");
    graph.addEdge("b", "a");
    graph.addEdge("a", "c");
    graph.removeVertex("a");
    graph.addEdge("a", "b");
    graph.addVertex("a");
    System.out.println(graph.size());
   // graph.removeVertex("a");
   // System.out.println(graph.getAllVertices());
    for(String vertex: graph.getAllVertices()) {
        System.out.println(vertex+" "+graph.getAdjacentVerticesOf(vertex)+" "+ graph.size()+" "+graph.order());
    }
    
}
public static void test2() throws FileNotFoundException, IOException, ParseException, CycleException, PackageNotFoundException {
    PackageManager pManager=new PackageManager();
    pManager.constructGraph("valid.json");
    //System.out.println(pManager.getAllPackages());
    System.out.println(pManager.getInstallationOrder("E"));
    System.out.println(pManager.getInstallationOrder("A"));
    System.out.println(pManager.toInstall("A", "E"));
    System.out.println(pManager.getInstallationOrderForAllPackages());
    System.out.println(pManager.getPackageWithMaxDependencies());
    
}
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, CycleException, PackageNotFoundException {
        // TODO Auto-generated method stub
       test2();
       //test1();
        
    }

}
