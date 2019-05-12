/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.data;

import java.awt.Color;
import java.util.Arrays;

import com.cburch.logisim.util.Cache;

public class Value {
	public static final Value FALSE   = new Value(1, 0, 0, 0);
	public static final Value TRUE    = new Value(1, 0, 0, 1);
	public static final Value UNKNOWN = new Value(1, 0, 1, 0);
	public static final Value ERROR   = new Value(1, 1, 0, 0);
	public static final Value NIL     = new Value(0, 0, 0, 0);

	public static final int MAX_WIDTH = 32;

	public static final Color NIL_COLOR = Color.GRAY;
	public static final Color FALSE_COLOR = new Color(0, 100, 0);
	public static final Color TRUE_COLOR = new Color(0, 210, 0);
	public static final Color UNKNOWN_COLOR = new Color(40, 40, 255);
	public static final Color ERROR_COLOR = new Color(192, 0, 0);
	public static final Color WIDTH_ERROR_COLOR = new Color(255, 123, 0);
	public static final Color MULTI_COLOR = Color.BLACK;
	
	private static final Cache cache = new Cache();

	public static Value create(Value[] values) {
		if (values.length == 0) return NIL;
		if (values.length == 1) return values[0];
		if (values.length > MAX_WIDTH) throw new RuntimeException(
			"Cannot have more than " + MAX_WIDTH + " bits in a value");

		int width = values.length;
		int value = 0;
		int unknown = 0;
		int error = 0;
		for (int i = 0; i < values.length; i++) {
			int mask = 1 << i;
			if (values[i] == TRUE)         value |= mask;
			else if (values[i] == FALSE)   /* do nothing */;
			else if (values[i] == UNKNOWN) unknown |= mask;
			else if (values[i] == ERROR)   error |= mask;
			else {
				throw new RuntimeException("unrecognized value "
					+ values[i]);
			}
		}
		return Value.create(width, error, unknown, value);
	}

	public static Value createKnown(BitWidth bits, int value) {
		return Value.create(bits.getWidth(), 0, 0, value);
	}

	public static Value createUnknown(BitWidth bits) {
		return Value.create(bits.getWidth(), 0, -1, 0);
	}

	public static Value createError(BitWidth bits) {
		return Value.create(bits.getWidth(), -1, 0, 0);
	}

	private static Value create(int width, int error, int unknown, int value) {
		if (width == 0) {
			return Value.NIL;
		} else if (width == 1) {
			if ((error & 1) != 0)       return Value.ERROR;
			else if ((unknown & 1) != 0)    return Value.UNKNOWN;
			else if ((value & 1) != 0)  return Value.TRUE;
			else return Value.FALSE;
		} else {
			int mask = (width == 32 ? -1 : ~(-1 << width));
			error = error & mask;
			unknown = unknown & mask & ~error;
			value = value & mask & ~unknown & ~error;

			int hashCode = 31 * (31 * (31 * width + error) + unknown) + value;
			Object cached = cache.get(hashCode);
			if (cached != null) {
				Value val = (Value) cached;
				if (val.value == value && val.width == width && val.error == error
						&& val.unknown == unknown) return val;
			}
			Value ret= new Value(width, error, unknown, value);
			cache.put(hashCode, ret);
			return ret;
		}
	}
	
	public static Value repeat(Value base, int bits) {
		if (base.getWidth() != 1) {
			throw new IllegalArgumentException("first parameter must be one bit");
		}
		if (bits == 1) {
			return base;
		} else {
			Value[] ret = new Value[bits];
			Arrays.fill(ret, base);
			return create(ret);
		}
	}

    public static int radixOfLogString(BitWidth width, String t)
    {
      if(t.startsWith("0x")) return 16;
	    if(t.startsWith("0o")) return 8;
	    if(t.length() == width.getWidth()) return 2;
	    return 10;
    }

    public static Value fromLogString(BitWidth width, String t) throws Exception
    {
	    //System.out.println(" Input string is " + t.toString());
	    
      int radix = radixOfLogString(width, t);
	    int offset;
	    
     // System.out.println("radix is " + radix);
      if (radix == 16 || radix == 8) offset = 2;
	    else if (radix == 10 && t.startsWith("-")) offset = 1;
	    else offset = 0;
	    
      int n = t.length();
	    if (n <= offset) throw new Exception("expected digits");
	    int w = width.getWidth();
	    long value = 0, unknown = 0;
	    //System.out.println("n is " + n + " w is " + w);
	    for(int i = offset; i < n; i++) {
	      char c = t.charAt(i);
	      int d;
	      if (c == 'x' && radix != 10) d = -1;
	      else if ('0' <= c && c <= '9') d = c - '0';
	      else if ('a' <= c && c <= 'f') d = 0xa + (c - 'a');
	      else if ('A' <= c && c <= 'F') d = 0xA + (c - 'A');
	      else throw new Exception("unexpected character '" + t.substring(i, i+1)+"' in \"" + t + "\"");
	      if (d >= radix)
		      throw new Exception("unexpected character '" + t.substring(i, i+1)+"' in \"" + t + "\"");
	      value *= radix;
	      unknown *= radix;
	      if ((value >> (radix == 10 ? 33 : w)) != 0 || (unknown >> 36) != 0)
		      throw new Exception("too many bits in \"" + t + "\"");

        //System.out.println("d = " + d);
        if (radix != 10) {
	        if (d == -1) unknown |= (radix-1);
	        else value |= d;
        } else {
          if (d == -1) unknown += (radix - 1);
          else value += d;
        }
	      //System.out.println("Pos " + i + " with value " + value + " unknown " + unknown);
	    }
	    if (radix == 10 && t.charAt(0) == '-')
	      value = -value;
      
      if (w == 32){
	      if (((value & 0x7FFFFFFF) >> (w - 1)) != 0)
	        throw new Exception("too many bits in \"" + t + "\"");
      } else {
	      if ((value >> w) != 0)
	        throw new Exception("too many bits in \"" + t + "\"");
      }

	    unknown &= ((1L << w) - 1);
	    int v = (int)(value & 0x00000000ffffffff);
	    int u = (int)(unknown & 0x00000000ffffffff);
      //System.out.println("create value width " + w + " unknown " + u + " value " + v); 
	    return create(w, 0, u, v);
	}
	private final int width;
	private final int error;
	private final int unknown;
	private final int value;

	private Value(int width, int error, int unknown, int value) {
		// To ensure that the one-bit values are unique, this should be called only
		// for the one-bit values and by the private create method
		this.width = width;
		this.error = error;
		this.unknown = unknown;
		this.value = value;
	}

	public boolean isErrorValue() {
		return error != 0;
	}

	public Value extendWidth(int newWidth, Value others) {
		if (width == newWidth) return this;
		int maskInverse = (width == 32 ? 0 : (-1 << width));
		if (others == Value.ERROR) {
			return Value.create(newWidth, error | maskInverse, unknown, value);
		} else if (others == Value.FALSE) {
			return Value.create(newWidth, error, unknown, value);
		} else if (others == Value.TRUE) {
			return Value.create(newWidth, error, unknown, value | maskInverse);
		} else {
			return Value.create(newWidth, error, unknown | maskInverse, value);
		}
	}

	public boolean isUnknown() {
		if (width == 32) {
			return error == 0 && unknown == -1;
		} else {
			return error == 0 && unknown == ((1 << width) - 1);
		}
	}

	public boolean isFullyDefined() {
		return width > 0 && error == 0 && unknown == 0;
	}

	public Value set(int which, Value val) {
		if (val.width != 1) {
			throw new RuntimeException("Cannot set multiple values");
		} else if (which < 0 || which >= width) {
			throw new RuntimeException("Attempt to set outside value's width");
		} else if (width == 1) {
			return val;
		} else {
			int mask = ~(1 << which);
			return Value.create(this.width,
				(this.error   & mask) | (val.error   << which),
				(this.unknown & mask) | (val.unknown << which),
				(this.value   & mask) | (val.value   << which));
		}
	}

	public Value[] getAll() {
		Value[] ret = new Value[width];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = get(i);
		}
		return ret;
	}

	public Value get(int which) {
		if (which < 0 || which >= width) return ERROR;
		int mask = 1 << which;
		if ((error & mask) != 0) return ERROR;
		else if ((unknown & mask) != 0) return UNKNOWN;
		else if ((value & mask) != 0) return TRUE;
		else return FALSE;
	}

	public BitWidth getBitWidth() {
		return BitWidth.create(width);
	}

	public int getWidth() {
		return width;
	}

	@Override
	public boolean equals(Object other_obj) {
		if (!(other_obj instanceof Value)) return false;
		Value other = (Value) other_obj;
		boolean ret = this.width == other.width
			&& this.error == other.error
			&& this.unknown == other.unknown
			&& this.value == other.value;
		return ret;
	}
	
    	public boolean compatible(Value other) {
		// where this has a value, other must have same value
		// where this has unknown, other can have unknown or any value
		// where this has error, other must have error
		return (this.width == other.width &&
			this.error == other.error &&
			this.value == (other.value & ~this.unknown) &&
			this.unknown == (other.unknown | this.unknown));
    	}   

	@Override
	public int hashCode() {
		int ret = width;
		ret = 31 * ret + error;
		ret = 31 * ret + unknown;
		ret = 31 * ret + value;
		return ret;
	}

	public int toIntValue() {
		if (error != 0) return -1;
		if (unknown != 0) return -1;
		return value;
	}

	@Override
	public String toString() {
		switch (width) {
		case 0: return "-";
		case 1:
			if (error != 0)        return "E";
			else if (unknown != 0) return "x";
			else if (value != 0)   return "1";
			else                  return "0";
		default:
			StringBuilder ret = new StringBuilder();
			for (int i = width - 1; i >= 0; i--) {
				ret.append(get(i).toString());
				if (i % 4 == 0 && i != 0) ret.append(" ");
			}
			return ret.toString();
		}
	}
	
	public String toOctalString() {
		if (width <= 1) {
			return toString();
		} else {
			Value[] vals = getAll();
			char[] c = new char[(vals.length + 2) / 3];
			for (int i = 0; i < c.length; i++) {
				int k = c.length - 1 - i;
				int frst = 3 * k;
				int last = Math.min(vals.length, 3 * (k + 1));
				int v = 0;
				c[i] = '?';
				for (int j = last - 1; j >= frst; j--) {
					if (vals[j] == Value.ERROR) { c[i] = 'E'; break; }
					if (vals[j] == Value.UNKNOWN) { c[i] = 'x'; break; }
					v = 2 * v;
					if (vals[j] == Value.TRUE) v++;
				}
				if (c[i] == '?') c[i] = Character.forDigit(v, 8);
			}
			return new String(c);
		}
	}
	
	public String toHexString() {
		if (width <= 1) {
			return toString();
		} else {
			Value[] vals = getAll();
			char[] c = new char[(vals.length + 3) / 4];
			for (int i = 0; i < c.length; i++) {
				int k = c.length - 1 - i;
				int frst = 4 * k;
				int last = Math.min(vals.length, 4 * (k + 1));
				int v = 0;
				c[i] = '?';
				for (int j = last - 1; j >= frst; j--) {
					if (vals[j] == Value.ERROR) { c[i] = 'E'; break; }
					if (vals[j] == Value.UNKNOWN) { c[i] = 'x'; break; }
					v = 2 * v;
					if (vals[j] == Value.TRUE) v++;
				}
				if (c[i] == '?') c[i] = Character.forDigit(v, 16);
			}
			return new String(c);
		}
	}
	
	public String toDecimalString(boolean signed) {
		if (width == 0) return "-";
		if (isErrorValue()) return Strings.get("valueError");
		if (!isFullyDefined()) return Strings.get("valueUnknown");
		
		int value = toIntValue();
		if (signed) {
			if (width < 32 && (value >> (width - 1)) != 0) {
				value |= (-1) << width;
			}
			return "" + value;
		} else {
			return "" + ((long) value & 0xFFFFFFFFL);
		}
	}
	
	public String toDisplayString(int radix) {
		switch (radix) {
		case 2:  return toDisplayString();
		case 8:  return toOctalString();
		case 16: return toHexString();
		default:
			if (width == 0) return "-";
			if (isErrorValue()) return Strings.get("valueError");
			if (!isFullyDefined()) return Strings.get("valueUnknown");
			return Integer.toString(toIntValue(), radix);
		}
	}

	public String toDisplayString() {
		switch (width) {
		case 0: return "-";
		case 1:
			if (error != 0)        return Strings.get("valueErrorSymbol");
			else if (unknown != 0) return Strings.get("valueUnknownSymbol");
			else if (value != 0)   return "1";
			else                  return "0";
		default:
			StringBuilder ret = new StringBuilder();
			for (int i = width - 1; i >= 0; i--) {
				ret.append(get(i).toString());
				if (i % 4 == 0 && i != 0) ret.append(" ");
			}
			return ret.toString();
		}
	}

	public Value combine(Value other) {
		if (other == null) return this;
		if (this == NIL) return other;
		if (other == NIL) return this;
		if (this.width == 1 && other.width == 1) {
			if (this == other) return this;
			if (this == UNKNOWN) return other;
			if (other == UNKNOWN) return this;
			return ERROR;
		} else {
			int disagree = (this.value ^ other.value)
				& ~(this.unknown | other.unknown);
			return Value.create(Math.max(this.width, other.width),
				this.error | other.error | disagree,
				this.unknown & other.unknown,
				(this.value & ~this.unknown) | (other.value & ~other.unknown));
		}
	}

	public Value and(Value other) {
		if (other == null) return this;
		if (this.width == 1 && other.width == 1) {
			if (this == FALSE || other == FALSE) return FALSE;
			if (this == TRUE  && other == TRUE ) return TRUE;
			return ERROR;
		} else {
			int false0 = ~this.value & ~this.error & ~this.unknown;
			int false1 = ~other.value & ~other.error & ~other.unknown;
			int falses = false0 | false1;
			return Value.create(Math.max(this.width, other.width),
					(this.error | other.error | this.unknown | other.unknown) & ~falses,
					0,
					this.value & other.value);
		}
	}

	public Value or(Value other) {
		if (other == null) return this;
		if (this.width == 1 && other.width == 1) {
			if (this == TRUE  || other == TRUE ) return TRUE;
			if (this == FALSE && other == FALSE) return FALSE;
			return ERROR;
		} else {
			int true0 = this.value & ~this.error & ~this.unknown;
			int true1 = other.value & ~other.error & ~other.unknown;
			int trues = true0 | true1;
			return Value.create(Math.max(this.width, other.width),
				(this.error | other.error | this.unknown | other.unknown) & ~trues,
				0,
				this.value | other.value);
		}
	}

	public Value xor(Value other) {
		if (other == null) return this;
		if (this.width <= 1 && other.width <= 1) {
			if (this == ERROR || other == ERROR) return ERROR;
			if (this == UNKNOWN || other == UNKNOWN) return ERROR;
			if (this == NIL || other == NIL) return ERROR;
			if ((this == TRUE) == (other == TRUE)) return FALSE;
			return TRUE;
		} else {
			return Value.create(Math.max(this.width, other.width),
				this.error | other.error | this.unknown | other.unknown,
				0,
				this.value ^ other.value);
		}
	}

	public Value not() {
		if (width <= 1) {
			if (this == TRUE) return FALSE;
			if (this == FALSE) return TRUE;
			return ERROR;
		} else {
			return Value.create(this.width,
				this.error | this.unknown,
				0,
				~this.value);
		}
	}

	public Color getColor() {
		if (error != 0) {
			return ERROR_COLOR;
		} else if (width == 0) {
			return NIL_COLOR;
		} else if (width == 1) {
			if (this == UNKNOWN) return UNKNOWN_COLOR;
			else if (this == TRUE) return TRUE_COLOR;
			else return FALSE_COLOR;
		} else {
			return MULTI_COLOR;
		}
	}
}
