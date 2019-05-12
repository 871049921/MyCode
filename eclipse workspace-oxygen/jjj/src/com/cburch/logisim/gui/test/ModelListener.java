/* Copyright (c) 2006, 2009, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */
 
package com.cburch.logisim.gui.test;

interface ModelListener {
    public void testResultsChanged(int numPass, int numFail);
    public void vectorChanged();
    public void testingChanged();
}
