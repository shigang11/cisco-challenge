package com.cisco;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cisco.gnode.GNode;
import com.cisco.gnode.NodeService;
import com.cisco.gnode.TreeNode;
import com.cisco.word.WordCounter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CiscoChallengeApplicationTests {

	TreeNode start = new TreeNode();
	NodeService service = new NodeService();
	
	@Before
	public void before()
	{
		//create graph
    	start.setName("A");
    	TreeNode child1 = new TreeNode("B");
    	TreeNode child2 = new TreeNode("C");
    	child1.addAChild( new TreeNode("D"));//grand child
    	child1.addAChild( new TreeNode("E"));//grand child
    	child2.addAChild( new TreeNode("F"));//grand child
    	start.addAChild(child1);
    	start.addAChild(child2);
	}
	
	@Test
	public void walkGraphTest() {
    	List<GNode> allNodes = service.walkGraph(start);  
    	assertTrue(allNodes.get(0).getName().equals("A"));//A, 
    	assertEquals(allNodes.size(), 6);
	}
	
	@Test
	public void fullPathTest() {
    	List<List<GNode>> allPaths = service.paths(start);     	
    	assertTrue(allPaths.get(0).get(2).getName().equals("D"));//D    	
    	assertEquals(allPaths.size(), 3);
	}
	
	@Test 
	public void wordCounterTest()
	{
    	WordCounter wc = new WordCounter();
    	Map<String, Integer> map = wc.count("I am from Alabama originally, not from Georgia");
    	assertEquals(map.get("i").intValue(), 1);
    	assertEquals(map.get("from").intValue(), 2);
	}
}
