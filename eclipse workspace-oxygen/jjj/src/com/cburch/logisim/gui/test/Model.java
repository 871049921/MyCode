/* Copyright (c) 2006, 2009, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */
 
package com.cburch.logisim.gui.test;

import java.util.HashMap;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.cburch.logisim.circuit.Circuit;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.data.TestVector;
import com.cburch.logisim.data.TestException;
import com.cburch.logisim.util.EventSourceWeakSupport;
import com.cburch.logisim.proj.Project;

class Model {
    private EventSourceWeakSupport<ModelListener> listeners;
    private boolean selected = false;
    private Project project;
    private Circuit circuit;
    private boolean running, paused;
    private TestThread tester;
    private int numPass = 0, numFail = 0;
    private TestVector vec = null;
    private TestException results[];
    
    public Model(Project proj, Circuit circuit) {
        listeners = new EventSourceWeakSupport<ModelListener>();
	this.circuit = circuit;
	this.project = proj;
    }
    
    public void addModelListener(ModelListener l) { listeners.add(l); }
    public void removeModelListener(ModelListener l) { listeners.remove(l); }

    public boolean isRunning() { return running; }
    public boolean isPaused() { return paused; }

    public void start() throws TestException
    {
	synchronized(this) {
	    if (vec == null) return;
	    if (running) {
		setPaused(false);
		return;
	    }
	    tester = new TestThread(this);
	    running = true;
	    paused = false;
	    tester.start();
	}
	fireTestingChanged();
    }

    public void stop()
    {
	synchronized(this) {
	    if (!running) return;
	    running = false;
	    if (tester != null) tester.cancel();
	    tester = null;
	}
	fireTestingChanged();
    }

    public void setPaused(boolean paused)
    {
	synchronized(this) {
	    if (running && tester != null)
		tester.setPaused(paused);
	    this.paused = paused;
	}
	fireTestingChanged();
    }
    
    private void fireTestingChanged() {
        for (ModelListener listener : listeners) {
            listener.testingChanged();
        }
    }


    public TestVector getVector() { return vec; }
    public TestException[] getResults() { return results; }
    
    public synchronized void setVector(TestVector v)
    {
	stop();
	synchronized(this) {
	    vec = v;
	    results = (v != null ? new TestException[v.data.size()] : null);
	    numPass = numFail = 0;
	    failed.clear();
	    passed.clear();
	}
        fireVectorChanged();
    }

    private void fireVectorChanged() {
        for (ModelListener listener : listeners) {
            listener.vectorChanged();
        }
    }

    public int getPass() { return numPass; }
    public int getFail() { return numFail; }

    public boolean setResult(TestVector v, int idx, TestException err) {
	synchronized(this) {
	    if (v != vec || idx < 0 || idx >= results.length || idx != numPass + numFail)
		return false;
	    results[idx] = err;
	    if (err == null) numPass++;
	    else numFail++;
	}
	if (!SwingUtilities.isEventDispatchThread()) {
	    SwingUtilities.invokeLater(myUpdateResultSort);
	} else {
	    updateResultSort();
	}
	return true;
    }

    private class UpdateResultSort implements Runnable {
	public void run() { updateResultSort(); }
    }

    private UpdateResultSort myUpdateResultSort = new UpdateResultSort();

    private ArrayList<Integer> failed = new ArrayList<Integer>();
    private ArrayList<Integer> passed = new ArrayList<Integer>();

    private void updateResultSort() {
	if (vec == null)
	    return;
	for (int i = failed.size() + passed.size(); i < numPass + numFail; i++) {
	    if (results[i] == null)
		passed.add(new Integer(i));
	    else
		failed.add(new Integer(i));
	}
	fireTestResultsChanged();
    }

    public int sortedIndex(int i) {
	if (i < failed.size()) 
	    return (failed.get(i)).intValue();
	if (i < failed.size() + passed.size()) 
	    return (passed.get(i - failed.size())).intValue();
	return i;
    }

    public void clearResults() {
	stop();
	synchronized(this) {
	    if (vec == null || results == null) return;
	    numPass = numFail = 0;
	    failed.clear();
	    passed.clear();
	}
	fireTestResultsChanged();
    }
    
    private void fireTestResultsChanged() {
        for (ModelListener listener : listeners) {
            listener.testResultsChanged(numPass, numFail);
        }
    }
   

    public Project getProject() { return project; }
    public Circuit getCircuit() { return circuit; }

    public boolean isSelected() { return selected; }

    public void setSelected(boolean value) {
        if(selected == value) return;
        selected = value;
        if (!selected)
	    setPaused(true);
	else
	    setPaused(false);
    }
}
