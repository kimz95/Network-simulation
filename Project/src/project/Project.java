package project;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


public class Project {

    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of nodes");
        int n=sc.nextInt(),    //no. of nodes
            msgcount=1000;
        double [][] AdjacencyMatrix= new double[n][n];
        ArrayList<Vertex> nodes=new ArrayList<>();
        ArrayList<Edge> edges=new ArrayList<>();
        ArrayList<Message> messages= new ArrayList<>();
        initNodes(nodes,n);
        initAdjMatrix(nodes,n,AdjacencyMatrix,edges);
        initMessages(messages,msgcount,nodes,n);
        
        Graph G= new Graph(nodes,edges);
        DijkstraAlgorithm DA= new DijkstraAlgorithm(G);
        SendMessages(messages,DA);
        
        
        System.out.println(AvgPwr(nodes));
        
    }
    
    static void initMessages(ArrayList<Message> messages,int msgcount,ArrayList<Vertex> nodes,int n){
        Random rand1,rand2;
        for(int i=0;i<msgcount;i++){
            rand1= new Random();
            rand2= new Random();
            
            Vertex SrcNode = nodes.get(rand1.nextInt(n));
            Vertex DstNode = nodes.get(rand2.nextInt(n));
                    
            Message m=new Message("Outgoing",
                    "Message "+(i+1),
                    SrcNode,
                    DstNode,
                    n);
            
            messages.add(m);
        }
    }
    
    static void initNodes(ArrayList<Vertex> nodes,int n){
        Random RandX,RandY;
        for(int i=0;i<n;i++){
            RandX = new Random();
            int x = RandX.nextInt(100) + 1;

            RandY = new Random();
            int y = RandY.nextInt(100) + 1;

            Vertex node= new Vertex(Integer.toString(i+1),"Vertex "+Integer.toString(i+1),x,y);
            nodes.add(node);
        }
    }
    
    static void initAdjMatrix(ArrayList<Vertex> nodes,int n,double[][] AdjacencyMatrix,ArrayList<Edge> edges){
        int ID=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j)
                    continue;
                
                int Xdisp=(abs(nodes.get(i).x-nodes.get(j).x));
                int Ydisp=(abs(nodes.get(i).y-nodes.get(j).y));
                int Distance = (int) (round(sqrt(pow(Xdisp,2)+pow(Ydisp,2))*100)/100);
                if(Xdisp<20 && Ydisp<20){
                    nodes.get(i).btypwr-=0.05*Distance;
                    AdjacencyMatrix[i][j]=Distance;
                    if(AdjacencyMatrix[i][j]!=AdjacencyMatrix[j][i])
                        edges.add(new Edge(Integer.toString(++ID),nodes.get(i),nodes.get(j),Distance));
                }
            }
        }
        
        System.out.println("Printing weighted adjacency matrix");
        for(int i=0;i<n;i++)
        {
            System.out.println();
            for(int j=0;j<n;j++)
                System.out.print(AdjacencyMatrix[i][j]+"\t");
        }
        System.out.println();
    }
    static void SendMessages(ArrayList<Message> messages,DijkstraAlgorithm DA){
        for(int i=0;i<messages.size();i++){
            messages.get(i).getSource().msg="message"+(i+1);
            DA.execute(messages.get(i).getSource());
            LinkedList<Vertex> path=DA.getPath(messages.get(i).getDestination());
            
            if(path==null)
                continue;
            
            for(int j=0;j<path.size()-1;j++){
                path.get(j).btypwr-=1;
                path.get(j+1).msg=path.get(j).msg;
                path.get(j).msg=null;
            }
            
        }
    }
    
    static int AvgPwr(ArrayList<Vertex> nodes){
        int total=0;
        for(int i=0;i<nodes.size();i++)
            total+=nodes.get(i).btypwr;
        return total/nodes.size();
    }

}
