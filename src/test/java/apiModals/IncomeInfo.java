package apiModals;

public class IncomeInfo {
    private String incomeType;
    private String payrollType;
    private float payrollFrequency;
    private String lastPayrollDate;
    private String nextPayrollDate = null;

    // Getter Methods

    public String getIncomeType() {
        return incomeType;
    }

    public String getPayrollType() {
        return payrollType;
    }

    public float getPayrollFrequency() {
        return payrollFrequency;
    }

    public String getLastPayrollDate() {
        return lastPayrollDate;
    }

    public String getNextPayrollDate() {
        return nextPayrollDate;
    }

    // Setter Methods

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public void setPayrollType(String payrollType) {
        this.payrollType = payrollType;
    }

    public void setPayrollFrequency(float payrollFrequency) {
        this.payrollFrequency = payrollFrequency;
    }

    public void setLastPayrollDate(String lastPayrollDate) {
        this.lastPayrollDate = lastPayrollDate;
    }

    public void setNextPayrollDate(String nextPayrollDate) {
        this.nextPayrollDate = nextPayrollDate;
    }

}
