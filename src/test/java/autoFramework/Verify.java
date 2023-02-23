package autoFramework;

/** fluent interface */
public class Verify extends AutoLogger {

    private NewAssert newAssert;

    // TODO: likely only need one private var per type
    private boolean boolVar1;
    private boolean boolVar2;

    private String stringVar1;
    private String stringVar2;

    private int intVar1;
    private int intVar2;

    public Verify() {
        newAssert = new NewAssert();
    }

    public Verify That(boolean boolVar)
    {
        boolVar1 = boolVar;
        return this;
    }

    public Verify That(String stringVar)
    {
        stringVar1 = stringVar;
        return this;
    }

    public Verify That(int intVar)
    {
        intVar1 = intVar;
        return this;
    }

    public Verify Equals(boolean boolVar)
    {
        boolVar2 = boolVar;
        Info(String.format("Observed: %s, Expected: %s", boolVar1, boolVar2));
        newAssert.assertEquals(boolVar1, boolVar2);
        return this;
    }

    public Verify Equals(String stringVar)
    {
        stringVar2 = stringVar;
        Info(String.format("Observed: %s, Expected: %s", stringVar1, stringVar2));
        newAssert.assertEquals(stringVar1, stringVar2);
        return this;
    }

    // NOTE: void works fine. Needs to be changed to Verify after creating WithDescription()
    public void Equals(int intVar)
    {
        intVar2 = intVar;
        Info(String.format("Observed: %s, Expected: %s", intVar1, intVar2));
        newAssert.assertEquals(intVar1, intVar2);
    }

    public Verify NotEquals(String stringVar)
    {
        stringVar2 = stringVar;
        Info(String.format("SHOULD NOT BE EQUAL: Observed: %s, Expected: %s", stringVar1, stringVar2));
        newAssert.assertNotEquals(stringVar1, stringVar2);
        return this;
    }


    // TODO: Log additional description. Will refactor asserts out of Equals() methods.
    public Verify WithDescription(String message)
    {
        Info(message);
        return this;
    }

    protected void assertAll(){
        newAssert.assertAll();
    }
    // TODO: should not be public; currently debugging
    public void clearAsserts() {
        newAssert.clearAsserts();
    }

    // TODO: should not be public; currently debugging
    public int getErrors()
    {
        return newAssert.getErrors();
    }

}
