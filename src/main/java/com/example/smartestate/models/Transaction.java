package com.example.smartestate.models;


import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User customer;

    @Column(name = "customerTextId")
    private String customerTextId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User developer;

    @Column(name = "developerTextId")
    private String developerTextId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private RealEstateObject object;

    @Column(name = "objectTextId")
    private String objectTextId;

    @Column(name = "summary")
    private Double summary;

    public Transaction() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Transaction;
    }

    public Long getId() {
        return this.id;
    }

    public User getCustomer() {
        return this.customer;
    }

    public String getCustomerTextId() {
        return this.customerTextId;
    }

    public User getDeveloper() {
        return this.developer;
    }

    public String getDeveloperTextId() {
        return this.developerTextId;
    }

    public RealEstateObject getObject() {
        return this.object;
    }

    public String getObjectTextId() {
        return this.objectTextId;
    }

    public Double getSummary() {
        return this.summary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setCustomerTextId(String customerTextId) {
        this.customerTextId = customerTextId;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public void setDeveloperTextId(String developerTextId) {
        this.developerTextId = developerTextId;
    }

    public void setObject(RealEstateObject object) {
        this.object = object;
    }

    public void setObjectTextId(String objectTextId) {
        this.objectTextId = objectTextId;
    }

    public void setSummary(Double summary) {
        this.summary = summary;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Transaction)) return false;
        final Transaction other = (Transaction) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$customer = this.getCustomer();
        final Object other$customer = other.getCustomer();
        if (this$customer == null ? other$customer != null : !this$customer.equals(other$customer)) return false;
        final Object this$customerTextId = this.getCustomerTextId();
        final Object other$customerTextId = other.getCustomerTextId();
        if (this$customerTextId == null ? other$customerTextId != null : !this$customerTextId.equals(other$customerTextId))
            return false;
        final Object this$developer = this.getDeveloper();
        final Object other$developer = other.getDeveloper();
        if (this$developer == null ? other$developer != null : !this$developer.equals(other$developer)) return false;
        final Object this$developerTextId = this.getDeveloperTextId();
        final Object other$developerTextId = other.getDeveloperTextId();
        if (this$developerTextId == null ? other$developerTextId != null : !this$developerTextId.equals(other$developerTextId))
            return false;
        final Object this$object = this.getObject();
        final Object other$object = other.getObject();
        if (this$object == null ? other$object != null : !this$object.equals(other$object)) return false;
        final Object this$objectTextId = this.getObjectTextId();
        final Object other$objectTextId = other.getObjectTextId();
        if (this$objectTextId == null ? other$objectTextId != null : !this$objectTextId.equals(other$objectTextId))
            return false;
        final Object this$summary = this.getSummary();
        final Object other$summary = other.getSummary();
        if (this$summary == null ? other$summary != null : !this$summary.equals(other$summary)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $customer = this.getCustomer();
        result = result * PRIME + ($customer == null ? 43 : $customer.hashCode());
        final Object $customerTextId = this.getCustomerTextId();
        result = result * PRIME + ($customerTextId == null ? 43 : $customerTextId.hashCode());
        final Object $developer = this.getDeveloper();
        result = result * PRIME + ($developer == null ? 43 : $developer.hashCode());
        final Object $developerTextId = this.getDeveloperTextId();
        result = result * PRIME + ($developerTextId == null ? 43 : $developerTextId.hashCode());
        final Object $object = this.getObject();
        result = result * PRIME + ($object == null ? 43 : $object.hashCode());
        final Object $objectTextId = this.getObjectTextId();
        result = result * PRIME + ($objectTextId == null ? 43 : $objectTextId.hashCode());
        final Object $summary = this.getSummary();
        result = result * PRIME + ($summary == null ? 43 : $summary.hashCode());
        return result;
    }

    public String toString() {
        return "Transaction(id=" + this.getId() + ", customer=" + this.getCustomer() + ", customerTextId=" + this.getCustomerTextId() + ", developer=" + this.getDeveloper() + ", developerTextId=" + this.getDeveloperTextId() + ", object=" + this.getObject() + ", objectTextId=" + this.getObjectTextId() + ", summary=" + this.getSummary() + ")";
    }
}
