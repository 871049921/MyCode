/* Copyright (c) 2006, 2009, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */
 
package com.cburch.logisim.gui.test;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.cburch.logisim.data.Value;
import com.cburch.logisim.data.BitWidth;
import com.cburch.logisim.data.TestVector;
import com.cburch.logisim.data.TestException;
import com.cburch.logisim.data.FailException;
import com.cburch.logisim.gui.log.ValueTable;
import com.cburch.logisim.util.StringUtil;

class TestPanel extends JPanel implements ValueTable.Model {

    static final Color failColor = new Color(0xff9999);

    private class MyListener implements ModelListener {
        public void vectorChanged() {
	    table.modelChanged();
        }
        public void testResultsChanged(int numPass, int numFail) {
	    table.dataChanged();
	}
	public void testingChanged() { }
    }
    
    private TestFrame testFrame;
    private ValueTable table;
    private MyListener myListener = new MyListener();
    
    public TestPanel(TestFrame frame) {
	this.testFrame = frame;
	table = new ValueTable(getModel() == null ? null : this);
	setLayout(new BorderLayout());
	add(table);
	modelChanged(null, getModel());
    }

    
    Model getModel() {
        return testFrame.getModel();
    }
    
    public void localeChanged() {
        table.modelChanged();
    }
    
    public void modelChanged(Model oldModel, Model newModel) {
        if(oldModel != null) oldModel.removeModelListener(myListener);
        if(newModel != null) newModel.addModelListener(myListener);
	table.setModel(newModel == null ? null : this);
    }

    // ValueTable.Model implementation
	
    public int getColumnCount()
    {
	TestVector vec = getModel().getVector();
	return vec == null ? 0 : vec.columnName.length + 1;
    }

    public String getColumnName(int i)
    {
	TestVector vec = getModel().getVector();
	return i == 0 ? Strings.get("statusHeader") : vec.columnName[i-1];
    }

    public BitWidth getColumnValueWidth(int i)
    {
	TestVector vec = getModel().getVector();
	return i == 0 ? null : vec.columnWidth[i-1];
    }

    public int getColumnValueRadix(int i) {
	TestVector vec = getModel().getVector();
	return i == 0 ? 0 : vec.columnRadix[i-1];
    }

    public void changeColumnValueRadix(int i) {
	if (i == 0) return;
	TestVector vec = getModel().getVector();
	switch(vec.columnRadix[i-1]) {
	    case 2:   vec.columnRadix[i-1] = 10; break;
	    case 10:  vec.columnRadix[i-1] = 16; break;
	    default:  vec.columnRadix[i-1] = 2; break;
	}
	table.modelChanged();
    }

    public int getRowCount()
    {
	TestVector vec = getModel().getVector();
	return vec == null ? 0 : vec.data.size();
    }

    public void getRowData(int firstRow, int numRows, ValueTable.Cell[][] rowData)
    {
	Model model = getModel();
	TestException[] results = model.getResults();
	int numPass = model.getPass();
	int numFail = model.getFail();
	TestVector vec = model.getVector();
	int columns = vec.columnName.length;
	String msg[] = new String[columns];
	Value[] altdata = new Value[columns];
	String passMsg = Strings.get("passStatus");
	String failMsg = Strings.get("failStatus");
	for(int i = firstRow; i < firstRow + numRows; i++) {
	    int row = model.sortedIndex(i);
	    Value[] data = vec.data.get(row);
	    String rowmsg = null;
	    String status = null;
	    boolean failed = false;
	    if (row < numPass + numFail) {
		TestException err = results[row];
		if (err != null && err instanceof FailException) {
		    failed = true;
		    for (FailException e = (FailException)err; e != null; e = e.getMore()) {
			int col = e.getColumn();
			msg[col] = StringUtil.format(Strings.get("expectedValueMessage"), 
				e.getExpected().toDisplayString(getColumnValueRadix(col+1)));
			altdata[col] = e.getComputed();
		    }
		} else if (err != null) {
		    failed = true;
		    rowmsg = err.getMessage();
		}
		status = failed ? failMsg : passMsg;
	    }
	    rowData[i-firstRow][0] = new ValueTable.Cell(status, rowmsg != null ? failColor : null, null, rowmsg);
	    for (int col = 0; col < columns; col++) {
		rowData[i-firstRow][col+1] = new ValueTable.Cell(altdata[col] != null ? altdata[col] : data[col], msg[col] != null ? failColor : null, null, msg[col]);
		msg[col] = null;
		altdata[col] = null;
	    }
	}
    }

}
