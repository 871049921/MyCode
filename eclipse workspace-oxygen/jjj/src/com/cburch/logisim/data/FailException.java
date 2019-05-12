/* Copyright (c) 2006, 2009, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */
 
package com.cburch.logisim.data;

import com.cburch.logisim.data.Value;

public class FailException extends TestException {
    private int column;
    private Value expected, computed;
    private FailException more;

    public FailException(int column, String columnName, Value expected, Value computed) {
	super(columnName + " = " + computed.toDisplayString(2)
		+ " (expected " + expected.toDisplayString(2) + ")");
	this.column = column;
	this.expected = expected;
	this.computed = computed;
    }

    public void add(FailException another) {
	more = another;
    }

    public int getColumn() { return column; }
    public Value getExpected() { return expected; }
    public Value getComputed() { return computed; }
    public FailException getMore() { return more; }

}
