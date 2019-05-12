/* Copyright (c) 2006, 2009, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */
 
package com.cburch.logisim.gui.test;

import com.cburch.logisim.data.Value;
import com.cburch.logisim.data.TestVector;
import com.cburch.logisim.data.TestException;
import com.cburch.logisim.data.FailException;
import com.cburch.logisim.circuit.Circuit;
import com.cburch.logisim.circuit.CircuitEvent;
import com.cburch.logisim.circuit.CircuitListener;
import com.cburch.logisim.circuit.CircuitState;
import com.cburch.logisim.comp.Component;
import com.cburch.logisim.instance.Instance;
import com.cburch.logisim.instance.InstanceFactory;
import com.cburch.logisim.instance.InstancePainter;
import com.cburch.logisim.instance.InstanceState;
import com.cburch.logisim.instance.Port;
import com.cburch.logisim.instance.StdAttr;
import com.cburch.logisim.std.wiring.Pin;
import com.cburch.logisim.util.StringUtil;
import com.cburch.logisim.proj.Project;

public class TestThread extends Thread implements CircuitListener {
    private Instance[] pin;
    private Project project;
    private Circuit circuit;
    private TestVector vector;

    private Model model;
    private boolean canceled = false, paused = false;
    private Object lock = new Object();

    public TestThread(Model model) throws TestException {
      this.model = model;

	    this.project = model.getProject();
    	this.circuit = model.getCircuit();
    	this.vector = model.getVector();

    	matchPins();

    	model.getCircuit().addCircuitListener(this);
    }

    void matchPins() throws TestException
    {
    	int n = vector.columnName.length;
    	pin = new Instance[n];
    	CircuitState state = new CircuitState(this.project, this.circuit);

    	for (int i = 0; i < n; i++) {
	      String columnName = vector.columnName[i];
	     for (Component comp : circuit.getNonWires()) {
	    	if(!(comp.getFactory() instanceof Pin)) continue;
	    	Instance inst = Instance.getInstanceFor(comp);
	    	InstanceState pinState = state.getInstanceState(comp);
	    	String label = pinState.getAttributeValue(StdAttr.LABEL);
	    	if (label == null || !label.equals(columnName)) continue;
	    	if (Pin.FACTORY.getWidth(inst).getWidth() != vector.columnWidth[i].getWidth())
		    throw new TestException("test vector column '"+columnName+"' has width " + vector.columnWidth[i] +", but pin has width "+Pin.FACTORY.getWidth(inst));
	    	pin[i] = inst;
	    	break;
	    }
	    if (pin[i] == null)
		    throw new TestException("test vector column '"+columnName+"' has no matching pin");
	    }
    }

    private void test(int idx) throws TestException
    {
	    circuit.doTestVector(project, pin, vector.data.get(idx));
    }

    public void run() {
    	try {

	     for (int i = 0; i < vector.data.size() && !canceled; i++) {
	    	while (paused) {		    
		      if (canceled) return;
		      try { Thread.sleep(1000); } catch(InterruptedException e) { }
		    }
		    try {
		      test(i);
		      canceled = canceled || !model.setResult(vector, i, null);
		    } catch (TestException e) {
		       canceled = canceled || !model.setResult(vector, i, e);
	    	}
		    Thread.yield();
	    }
	    } finally {
	      model.stop();
	    }
    }

    public void cancel() {
	    canceled = true;
    }

    public void setPaused(boolean paused) {
	    this.paused = paused;
    }

    public void circuitChanged(CircuitEvent event) {
	    int action = event.getAction();
	    if(action == CircuitEvent.ACTION_SET_NAME)
	      return;
    	else
	      model.clearResults();
    }

    // used only for automated testing via command line arguments
    private TestThread(Project proj, Circuit circuit, TestVector vec) throws TestException
    {
	    this.project = proj;
	    this.circuit = circuit;
	    this.vector = vec;

	    matchPins();
    }

    // used only for automated testing via command line arguments
    public static int doTestVector(Project proj, Circuit circuit, String vectorname) {

	    System.out.println(StringUtil.format(Strings.get("testLoadingVector"), vectorname));
	    TestVector vec;
	    try {
	      vec = new TestVector(vectorname);
	    } catch (Exception e) {
	      System.err.println(StringUtil.format(Strings.get("testLoadingFailed"), e.getMessage()));
	      return -1;
	    }

	    TestThread tester;
	    try {
	      tester = new TestThread(proj, circuit, vec);
	    } catch (TestException e) {
	      System.err.println(StringUtil.format(Strings.get("testSetupFailed"), e.getMessage()));
	      return -1;
	    }

	System.out.println(StringUtil.format(Strings.get("testRunning"), Integer.toString(vec.data.size())));

	int numPass = 0, numFail = 0;
	for (int i = 0; i < vec.data.size(); i++) {
	    try {
		System.out.print((i+1) + " \r");
		tester.test(i);
		numPass++;
	    } catch (FailException e) {
		System.out.println();
		System.err.println(StringUtil.format(Strings.get("testFailed"), Integer.toString(i+1)));
		for ( ; e != null; e = e.getMore())
		    System.out.println("  " + e.getMessage());
		numFail++;
		continue;
	    } catch (TestException e) {
		System.out.println();
		System.err.println(StringUtil.format(Strings.get("testFailed"), Integer.toString(i+1) + " " + e.getMessage()));
		numFail++;
		continue;
	    }
	}
	System.out.println();
	System.out.println(StringUtil.format(Strings.get("testResults"), Integer.toString(numPass), Integer.toString(numFail)));
	return 0;
    }

}
