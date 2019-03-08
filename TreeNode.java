package com.cisco.gnode;

import java.util.ArrayList;

public class TreeNode implements GNode{
	String name;
	
	//this way children will not be null, will be size 0 with no child
	ArrayList<GNode> children = new ArrayList<GNode>();
	GNode parent;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public GNode[] getChildren() {
		return children.toArray(new TreeNode[children.size()]);
	}
	
	public void addAChild(GNode node){
		children.add(node);
		((TreeNode) node).setParent(this);
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public TreeNode(){};

	public TreeNode(String name2) {
		name = name2;
	}

	@Override
	public GNode getParent() {
		return parent;
	}

	public void setParent(GNode parent) {
		this.parent = parent;
	}
}
