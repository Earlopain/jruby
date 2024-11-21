package org.jruby.test;

import junit.framework.TestCase;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

import static org.jruby.api.Create.newArray;
import static org.jruby.api.Create.newFixnum;

public class TestArrayFlatten extends TestCase {

    public void testFlatten() {
        var context = Ruby.newInstance().getCurrentContext();
        var keys = newArray(context);
        var values = newArray(context);

        for (int i = 0; i < 10_000; ++i) {
            keys.append(context, newFixnum(context, i));
            values.append(context, newFixnum(context, i));
        }
        
        var preHash = ((RubyArray<?>) keys.zip(context, new IRubyObject[] {values}, Block.NULL_BLOCK)).flatten(context);

        assertNotNull("We have a hash back", preHash);
    }
}
