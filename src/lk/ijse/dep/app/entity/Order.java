package lk.ijse.dep.app.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order extends SuperEntity{
    @Id
    private String id;
    @Temporal(TemporalType.DATE)
    private Date date;


    @ManyToOne
    @JoinColumn(name="customerId", referencedColumnName = "id")
    private Customer customer;


    public Order(String id, java.sql.Date date, String customerId) {

    }

    public Order(String id, Date date, Customer customer) {
        this.id = id;
        this.date = date;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomerId() {
        return customer;
    }

    public void setCustomerId(Customer customerId) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", customer='" + customer + '\'' +
                '}';
    }
}
