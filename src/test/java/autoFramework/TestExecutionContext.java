package autoFramework;

import java.lang.reflect.Method;

public class TestExecutionContext {

    private TestInfo context;

    public void getTestInfoContext(String testName, String moduleName) throws ClassNotFoundException {

        Class testClass = Class.forName(moduleName);
        Method[] methods = testClass.getMethods();
        Method testMethod = null;

        for (Method m : methods) {
            if (m.getName().equals(testName))
            {
                testMethod = m;
                break;
            }
        }

        context =  testMethod.getDeclaredAnnotation(TestInfo.class);
    }

    public String getDescription()
    {
        return context.description();
    }

    public String getLevel()
    {
        return context.level();
    }

    public String getCategories()
    {
        return context.categories();
    }


}
