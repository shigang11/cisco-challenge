package com.cisco.gnode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class NodeService {
	
	//return all nodes in the graph
	public ArrayList<GNode> walkGraph(GNode start) {
		ArrayList<GNode> list = new ArrayList<GNode>();
		list = getGraphNodes(start, list);
		return list;
	}
	
	//return arrayList of arrayLists, each of which is a path
	public List<List<GNode>> paths(GNode startNode) {
		List<List<GNode>> pathList = new ArrayList<List<GNode>>();
		
		//get all end nodes who has no children
		ArrayList<GNode> list = new ArrayList<GNode>();
		list = getEndNodes(startNode, list);
		
		//get full path for each end node using parent node
		for(GNode node : list){
			//use Deque so that I can add parent before child
			Deque<GNode> que = new LinkedList<GNode>();			
			GNode  tmpNode = node;
			
			//get all of its ancestor nodes
			do {
				//parent goes before a child
				que.addFirst(tmpNode);
				tmpNode = tmpNode.getParent();
			} while (tmpNode != null);	
			
			pathList.add(new ArrayList<GNode>(que));
		}
		
		return pathList;
	}

	//recursively walk thru the graph, and add all nodes in the list
	private ArrayList<GNode> getGraphNodes(GNode start, ArrayList<GNode> list){
		//add current node
		list.add(start);
		
		//add children and children's children recursively
		if(start.getChildren().length > 0)
		{
			for(GNode child: start.getChildren())
			{
				getGraphNodes(child, list);
			}
		}

		return list;			
	}
	
	//add all end nodes (who has no child) recursively to list
	private ArrayList<GNode> getEndNodes(GNode node, ArrayList<GNode> list){		
		//found an end node, keep it
		if(node.getChildren().length == 0){
			list.add(node);
		}
		
		//recursively go thru all child nodes
		if(node.getChildren().length > 0)
		{
			for(GNode child: node.getChildren())
			{
				getEndNodes(child, list);
			}
		}
		
		return list;
	}
}
