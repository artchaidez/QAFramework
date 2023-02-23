package autoFramework;

import org.testng.Assert;
import org.testng.asserts.IAssert;
import org.testng.asserts.IAssertLifecycle;
import org.testng.collections.Maps;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

// TODO: rename this class
public class NewAssert implements IAssertLifecycle {

    // Needs to be static for Listeners
    private static Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();

    /** Clear all asserts after each test and to be used in TestSuite Teardown */
    public void clearAsserts()
    {
        m_errors.clear();
    }

    public int getErrors()
    {
        return m_errors.size();
    }

    protected void doAssert(IAssert<?> var1) {
        this.onBeforeAssert(var1);

        try {
            var1.doAssert();
            this.onAssertSuccess(var1);
        } catch (AssertionError var6) {
            this.onAssertFailure(var1, var6);
            this.m_errors.put(var6, var1);
        } finally {
            this.onAfterAssert(var1);
        }

    }

    // TODO: log asserts in this order- observed: expected
    // TODO: clearly state what Assert failed
    public void assertAll() {
        if (!m_errors.isEmpty()) {
            StringBuilder sb = new StringBuilder("The following asserts failed:");
            boolean first = true;
            for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append("\n\t");
                sb.append(ae.getKey().getMessage());
            }
            throw new AssertionError(sb.toString());
        }
    }

    public void assertTrue(final boolean var1, final String var2) {
        this.doAssert(new SimpleAssert<Boolean>(var1, Boolean.TRUE, var2) {
            public void doAssert() {
                Assert.assertTrue(var1, var2);
            }
        });
    }

    public void assertTrue(final boolean var1) {
        this.doAssert(new SimpleAssert<Boolean>(var1, Boolean.TRUE) {
            public void doAssert() {
                Assert.assertTrue(var1);
            }
        });
    }

    public void assertFalse(final boolean var1, final String var2) {
        this.doAssert(new SimpleAssert<Boolean>(var1, Boolean.FALSE, var2) {
            public void doAssert() {
                Assert.assertFalse(var1, var2);
            }
        });
    }

    public void assertFalse(final boolean var1) {
        this.doAssert(new SimpleAssert<Boolean>(var1, Boolean.FALSE) {
            public void doAssert() {
                Assert.assertFalse(var1);
            }
        });
    }

    public void fail(final String var1, final Throwable var2) {
        this.doAssert(new SimpleAssert<Object>(var1) {
            public void doAssert() {
                Assert.fail(var1, var2);
            }
        });
    }

    public void fail(final String var1) {
        this.doAssert(new SimpleAssert<Object>(var1) {
            public void doAssert() {
                Assert.fail(var1);
            }
        });
    }

    public void fail() {
        this.doAssert(new SimpleAssert<Object>((String)null) {
            public void doAssert() {
                Assert.fail();
            }
        });
    }

    public <T> void assertEquals(final T var1, final T var2, final String var3) {
        this.doAssert(new SimpleAssert<T>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3);
            }
        });
    }

    public <T> void assertEquals(final T var1, final T var2) {
        this.doAssert(new SimpleAssert<T>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2);
            }
        });
    }

    public void assertEquals(final String var1, final String var2, final String var3) {
        this.doAssert(new SimpleAssert<String>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3);
            }
        });
    }

    public void assertEquals(final String var1, final String var2) {
        this.doAssert(new SimpleAssert<String>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2);
            }
        });
    }

    public void assertEquals(final double var1, final double var3, final double var5, final String var7) {
        this.doAssert(new SimpleAssert<Double>(var1, var3, var7) {
            public void doAssert() {
                Assert.assertEquals(var1, var3, var5, var7);
            }
        });
    }

    public void assertEquals(final double var1, final double var3, final double var5) {
        this.doAssert(new SimpleAssert<Double>(var1, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var3, var5);
            }
        });
    }

    public void assertEquals(final float var1, final float var2, final float var3, final String var4) {
        this.doAssert(new SimpleAssert<Float>(var1, var2, var4) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3, var4);
            }
        });
    }

    public void assertEquals(final float var1, final float var2, final float var3) {
        this.doAssert(new SimpleAssert<Float>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3);
            }
        });
    }

    public void assertEquals(final long var1, final long var3, final String var5) {
        this.doAssert(new SimpleAssert<Long>(var1, var3, var5) {
            public void doAssert() {
                Assert.assertEquals(var1, var3, var5);
            }
        });
    }

    public void assertEquals(final long var1, final long var3) {
        this.doAssert(new SimpleAssert<Long>(var1, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var3);
            }
        });
    }

    public void assertEquals(final boolean var1, final boolean var2, final String var3) {
        this.doAssert(new SimpleAssert<Boolean>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3);
            }
        });
    }

    public void assertEquals(final boolean var1, final boolean var2) {
        this.doAssert(new SimpleAssert<Boolean>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2);
            }
        });
    }

    public void assertEquals(final byte var1, final byte var2, final String var3) {
        this.doAssert(new SimpleAssert<Byte>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3);
            }
        });
    }

    public void assertEquals(final byte var1, final byte var2) {
        this.doAssert(new SimpleAssert<Byte>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2);
            }
        });
    }

    public void assertEquals(final char var1, final char var2, final String var3) {
        this.doAssert(new SimpleAssert<Character>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3);
            }
        });
    }

    public void assertEquals(final char var1, final char var2) {
        this.doAssert(new SimpleAssert<Character>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2);
            }
        });
    }

    public void assertEquals(final short var1, final short var2, final String var3) {
        this.doAssert(new SimpleAssert<Short>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3);
            }
        });
    }

    public void assertEquals(final short var1, final short var2) {
        this.doAssert(new SimpleAssert<Short>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2);
            }
        });
    }

    public void assertEquals(final int var1, final int var2, final String var3) {
        this.doAssert(new SimpleAssert<Integer>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3);
            }
        });
    }

    public void assertEquals(final int var1, final int var2) {
        this.doAssert(new SimpleAssert<Integer>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2);
            }
        });
    }

    public void assertNotNull(final Object var1) {
        this.doAssert(new SimpleAssert<Object>(var1, (Object)null) {
            public void doAssert() {
                Assert.assertNotNull(var1);
            }
        });
    }

    public void assertNotNull(final Object var1, final String var2) {
        this.doAssert(new SimpleAssert<Object>(var1, (Object)null, var2) {
            public void doAssert() {
                Assert.assertNotNull(var1, var2);
            }
        });
    }

    public void assertNull(final Object var1) {
        this.doAssert(new SimpleAssert<Object>(var1, (Object)null) {
            public void doAssert() {
                Assert.assertNull(var1);
            }
        });
    }

    public void assertNull(final Object var1, final String var2) {
        this.doAssert(new SimpleAssert<Object>(var1, (Object)null, var2) {
            public void doAssert() {
                Assert.assertNull(var1, var2);
            }
        });
    }

    public void assertSame(final Object var1, final Object var2, final String var3) {
        this.doAssert(new SimpleAssert<Object>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertSame(var1, var2, var3);
            }
        });
    }

    public void assertSame(final Object var1, final Object var2) {
        this.doAssert(new SimpleAssert<Object>(var1, var2) {
            public void doAssert() {
                Assert.assertSame(var1, var2);
            }
        });
    }

    public void assertNotSame(final Object var1, final Object var2, final String var3) {
        this.doAssert(new SimpleAssert<Object>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertNotSame(var1, var2, var3);
            }
        });
    }

    public void assertNotSame(final Object var1, final Object var2) {
        this.doAssert(new SimpleAssert<Object>(var1, var2) {
            public void doAssert() {
                Assert.assertNotSame(var1, var2);
            }
        });
    }

    public void assertEquals(final Collection<?> var1, final Collection<?> var2) {
        this.doAssert(new SimpleAssert<Collection<?>>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2);
            }
        });
    }

    public void assertEquals(final Collection<?> var1, final Collection<?> var2, final String var3) {
        this.doAssert(new SimpleAssert<Collection<?>>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3);
            }
        });
    }

    public void assertEquals(final Object[] var1, final Object[] var2, final String var3) {
        this.doAssert(new SimpleAssert<Object[]>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3);
            }
        });
    }

    public void assertEqualsNoOrder(final Object[] var1, final Object[] var2, final String var3) {
        this.doAssert(new SimpleAssert<Object[]>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertEqualsNoOrder(var1, var2, var3);
            }
        });
    }

    public void assertEquals(final Object[] var1, final Object[] var2) {
        this.doAssert(new SimpleAssert<Object[]>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2);
            }
        });
    }

    public void assertEqualsNoOrder(final Object[] var1, final Object[] var2) {
        this.doAssert(new SimpleAssert<Object[]>(var1, var2) {
            public void doAssert() {
                Assert.assertEqualsNoOrder(var1, var2);
            }
        });
    }

    public void assertEquals(final byte[] var1, final byte[] var2) {
        this.doAssert(new SimpleAssert<byte[]>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2);
            }
        });
    }

    public void assertEquals(final byte[] var1, final byte[] var2, final String var3) {
        this.doAssert(new SimpleAssert<byte[]>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3);
            }
        });
    }

    public void assertEquals(final Set<?> var1, final Set<?> var2) {
        this.doAssert(new SimpleAssert<Set<?>>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2);
            }
        });
    }

    public void assertEquals(final Set<?> var1, final Set<?> var2, final String var3) {
        this.doAssert(new SimpleAssert<Set<?>>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertEquals(var1, var2, var3);
            }
        });
    }

    public void assertEquals(final Map<?, ?> var1, final Map<?, ?> var2) {
        this.doAssert(new SimpleAssert<Map<?, ?>>(var1, var2) {
            public void doAssert() {
                Assert.assertEquals(var1, var2);
            }
        });
    }

    public void assertNotEquals(final Object var1, final Object var2, final String var3) {
        this.doAssert(new SimpleAssert<Object>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2, var3);
            }
        });
    }

    public void assertNotEquals(final Object var1, final Object var2) {
        this.doAssert(new SimpleAssert<Object>(var1, var2) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2);
            }
        });
    }

    public void assertNotEquals(final String var1, final String var2, final String var3) {
        this.doAssert(new SimpleAssert<String>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2, var3);
            }
        });
    }

    public void assertNotEquals(final String var1, final String var2) {
        this.doAssert(new SimpleAssert<String>(var1, var2) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2);
            }
        });
    }

    public void assertNotEquals(final long var1, final long var3, final String var5) {
        this.doAssert(new SimpleAssert<Long>(var1, var3, var5) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var3, var5);
            }
        });
    }

    public void assertNotEquals(final long var1, final long var3) {
        this.doAssert(new SimpleAssert<Long>(var1, var3) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var3);
            }
        });
    }

    public void assertNotEquals(final boolean var1, final boolean var2, final String var3) {
        this.doAssert(new SimpleAssert<Boolean>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2, var3);
            }
        });
    }

    public void assertNotEquals(final boolean var1, final boolean var2) {
        this.doAssert(new SimpleAssert<Boolean>(var1, var2) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2);
            }
        });
    }

    public void assertNotEquals(final byte var1, final byte var2, final String var3) {
        this.doAssert(new SimpleAssert<Byte>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2, var3);
            }
        });
    }

    public void assertNotEquals(final byte var1, final byte var2) {
        this.doAssert(new SimpleAssert<Byte>(var1, var2) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2);
            }
        });
    }

    public void assertNotEquals(final char var1, final char var2, final String var3) {
        this.doAssert(new SimpleAssert<Character>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2, var3);
            }
        });
    }

    public void assertNotEquals(final char var1, final char var2) {
        this.doAssert(new SimpleAssert<Character>(var1, var2) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2);
            }
        });
    }

    public void assertNotEquals(final short var1, final short var2, final String var3) {
        this.doAssert(new SimpleAssert<Short>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2, var3);
            }
        });
    }

    public void assertNotEquals(final short var1, final short var2) {
        this.doAssert(new SimpleAssert<Short>(var1, var2) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2);
            }
        });
    }

    public void assertNotEquals(final int var1, final int var2, final String var3) {
        this.doAssert(new SimpleAssert<Integer>(var1, var2, var3) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2, var3);
            }
        });
    }

    public void assertNotEquals(final int var1, final int var2) {
        this.doAssert(new SimpleAssert<Integer>(var1, var2) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2);
            }
        });
    }

    public void assertNotEquals(final float var1, final float var2, final float var3, final String var4) {
        this.doAssert(new SimpleAssert<Float>(var1, var2, var4) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2, var3, var4);
            }
        });
    }

    public void assertNotEquals(final float var1, final float var2, final float var3) {
        this.doAssert(new SimpleAssert<Float>(var1, var2) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var2, var3);
            }
        });
    }

    public void assertNotEquals(final double var1, final double var3, final double var5, final String var7) {
        this.doAssert(new SimpleAssert<Double>(var1, var3, var7) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var3, var5, var7);
            }
        });
    }

    public void assertNotEquals(final double var1, final double var3, final double var5) {
        this.doAssert(new SimpleAssert<Double>(var1, var3) {
            public void doAssert() {
                Assert.assertNotEquals(var1, var3, var5);
            }
        });
    }

    private abstract static class SimpleAssert<T> implements IAssert<T> {
        private final T actual;
        private final T expected;
        private final String m_message;

        public SimpleAssert(String var1) {
            this((T) null, (T) null, var1);
        }

        public SimpleAssert(T var1, T var2) {
            this(var1, var2, (String)null);
        }

        public SimpleAssert(T var1, T var2, String var3) {
            this.actual = var1;
            this.expected = var2;
            this.m_message = var3;
        }

        public String getMessage() {
            return this.m_message;
        }

        public T getActual() {
            return this.actual;
        }

        public T getExpected() {
            return this.expected;
        }

        public abstract void doAssert();
    }

    @Override
    public void executeAssert(IAssert<?> iAssert) {

    }

    @Override
    public void onAssertSuccess(IAssert<?> iAssert) {

    }

    @Override
    public void onAssertFailure(IAssert<?> iAssert) {

    }

    @Override
    public void onAssertFailure(IAssert<?> iAssert, AssertionError assertionError) {

    }

    @Override
    public void onBeforeAssert(IAssert<?> iAssert) {

    }

    @Override
    public void onAfterAssert(IAssert<?> iAssert) {

    }
}
