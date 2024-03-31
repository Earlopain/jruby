package org.jruby.ir.targets.indy;

import org.jruby.RubyRange;
import org.jruby.RubyString;
import org.jruby.ir.runtime.IRRuntimeHelpers;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.ByteList;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Opcodes;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static org.jruby.util.CodegenUtils.p;
import static org.jruby.util.CodegenUtils.sig;

public class RangeObjectSite extends LazyObjectSite {
    protected final boolean exclusive;

    public RangeObjectSite(MethodType type, boolean exclusive) {
        super(type);

        this.exclusive = exclusive;
    }

    public static final Handle BOOTSTRAP = new Handle(
            Opcodes.H_INVOKESTATIC,
            p(RangeObjectSite.class),
            "bootstrap",
            sig(CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class, int.class),
            false);

    public static CallSite bootstrap(MethodHandles.Lookup lookup, String name, MethodType type, int exclusive) {
        return new RangeObjectSite(type, exclusive == 1 ? true : false).bootstrap(lookup);
    }

    public IRubyObject construct(ThreadContext context, IRubyObject begin, IRubyObject end) throws Throwable {
        return RubyRange.newRange(context, begin, end, exclusive);
    }

    public static final Handle BOOTSTRAP_LONG_LONG = new Handle(
            Opcodes.H_INVOKESTATIC,
            p(RangeObjectSite.class),
            "bootstrapFixnums",
            sig(CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class, long.class, long.class, int.class),
            false);

    public static CallSite bootstrapFixnums(MethodHandles.Lookup lookup, String name, MethodType type, long begin, long end, int exclusive) {
        return new FixnumRangeObjectSite(type, begin, end, exclusive != 0).bootstrap(lookup);
    }

    public static class FixnumRangeObjectSite extends RangeObjectSite {
        protected final long begin;
        protected final long end;

        public FixnumRangeObjectSite(MethodType type, long begin, long end, boolean exclusive) {
            super(type, exclusive);

            this.begin = begin;
            this.end = end;
        }

        public IRubyObject construct(ThreadContext context) throws Throwable {
            return RubyRange.newRange(context, begin, end, exclusive);
        }
    }

    public static final Handle BOOTSTRAP_STRING_STRING = new Handle(
            Opcodes.H_INVOKESTATIC,
            p(RangeObjectSite.class),
            "bootstrapStrings",
            sig(CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class, String.class, String.class, int.class, String.class, String.class, int.class, int.class),
            false);

    public static CallSite bootstrapStrings(MethodHandles.Lookup lookup, String name, MethodType type, String begin, String beginEnc, int beginCR, String end, String endEnc, int endCR, int exclusive) {
        return new StringRangeObjectSite(type, StringBootstrap.bytelist(begin, beginEnc), beginCR, StringBootstrap.bytelist(end, endEnc), endCR, exclusive != 0).bootstrap(lookup);
    }

    public static class StringRangeObjectSite extends RangeObjectSite {
        protected final ByteList begin;
        protected final int beginCR;
        protected final ByteList end;
        protected final int endCR;

        public StringRangeObjectSite(MethodType type, ByteList begin, int beginCR, ByteList end, int endCR, boolean exclusive) {
            super(type, exclusive);

            this.begin = begin;
            this.beginCR = beginCR;
            this.end = end;
            this.endCR = endCR;
        }

        public IRubyObject construct(ThreadContext context) throws Throwable {
            return RubyRange.newRange(
                    context,
                    IRRuntimeHelpers.newFrozenString(context, begin, beginCR),
                    IRRuntimeHelpers.newFrozenString(context, end, endCR),
                    exclusive);
        }
    }
}
