package tomato_th.project_tomato.model.service;

public class CalenderOrder {
    private Calender calender;
    private CustomerServiceOrder customerServiceOrder;
    private int status;
    private int active;

    public CalenderOrder() {
    }

    public CalenderOrder(Calender calender, int status) {
        this.calender = calender;
        this.status = status;
    }

    public CalenderOrder(Calender calender, CustomerServiceOrder customerServiceOrder, int status) {
        this.calender = calender;
        this.customerServiceOrder = customerServiceOrder;
        this.status = status;
    }

    public CalenderOrder(Calender calender, CustomerServiceOrder customerServiceOrder, int status, int active) {
        this.calender = calender;
        this.customerServiceOrder = customerServiceOrder;
        this.status = status;
        this.active = active;
    }

    public Calender getCalender() {
        return calender;
    }

    public void setCalender(Calender calender) {
        this.calender = calender;
    }

    public CustomerServiceOrder getCustomerServiceOrder() {
        return customerServiceOrder;
    }

    public void setCustomerServiceOrder(CustomerServiceOrder customerServiceOrder) {
        this.customerServiceOrder = customerServiceOrder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
