package autoFramework;

import java.lang.reflect.Method;

public class TestExecutionContext {

    private TestInfo context;

    private String className;

    private String packageName;

    public void getTestInfoContext(String testName, String packageClassName) throws ClassNotFoundException {

        Class testClass = Class.forName(packageClassName);
        Method[] methods = testClass.getMethods();
        Method testMethod = null;

        for (Method m : methods) {
            if (m.getName().equals(testName))
            {
                testMethod = m;
                break;
            }
        }

        SplitPackageClassName(packageClassName);

        context =  testMethod.getDeclaredAnnotation(TestInfo.class);
    }

    private void SplitPackageClassName(String packageClassName)
    {
        String[] split = packageClassName.split("\\.");

        packageName = split[0];
        className = split[1];
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

    public String getClassName() { return className;}

    public String getPackageName() { return  packageName;}


}
