package com.cisco.gnode;

public interface GNode {
	String getName();
	GNode[] getChildren();
	
	//My I should talk to the design about this line, 
	//but it makes my code much cleaner:
	GNode getParent();
}
