package com.example.smartestate.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "square")
    private Double square;

    @Column(name = "price")
    private Double price;

    @Column(name = "floor")
    private int floor;

    @Column(name = "number_of_rooms")
    private int numberOfRooms;

    @Column(name = "type_of_housing")
    private String typeOfHousing;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User customer;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public Application() {
    }

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public Double getSquare() {
        return this.square;
    }

    public Double getPrice() {
        return this.price;
    }

    public int getFloor() {
        return this.floor;
    }

    public int getNumberOfRooms() {
        return this.numberOfRooms;
    }

    public String getTypeOfHousing() {
        return this.typeOfHousing;
    }

    public User getCustomer() {
        return this.customer;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setTypeOfHousing(String typeOfHousing) {
        this.typeOfHousing = typeOfHousing;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Application)) return false;
        final Application other = (Application) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$square = this.getSquare();
        final Object other$square = other.getSquare();
        if (this$square == null ? other$square != null : !this$square.equals(other$square)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        if (this.getFloor() != other.getFloor()) return false;
        if (this.getNumberOfRooms() != other.getNumberOfRooms()) return false;
        final Object this$typeOfHousing = this.getTypeOfHousing();
        final Object other$typeOfHousing = other.getTypeOfHousing();
        if (this$typeOfHousing == null ? other$typeOfHousing != null : !this$typeOfHousing.equals(other$typeOfHousing))
            return false;
        final Object this$customer = this.getCustomer();
        final Object other$customer = other.getCustomer();
        if (this$customer == null ? other$customer != null : !this$customer.equals(other$customer)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$creationDate = this.getCreationDate();
        final Object other$creationDate = other.getCreationDate();
        if (this$creationDate == null ? other$creationDate != null : !this$creationDate.equals(other$creationDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Application;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $square = this.getSquare();
        result = result * PRIME + ($square == null ? 43 : $square.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        result = result * PRIME + this.getFloor();
        result = result * PRIME + this.getNumberOfRooms();
        final Object $typeOfHousing = this.getTypeOfHousing();
        result = result * PRIME + ($typeOfHousing == null ? 43 : $typeOfHousing.hashCode());
        final Object $customer = this.getCustomer();
        result = result * PRIME + ($customer == null ? 43 : $customer.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $creationDate = this.getCreationDate();
        result = result * PRIME + ($creationDate == null ? 43 : $creationDate.hashCode());
        return result;
    }

    public String toString() {
        return "Application(id=" + this.getId() + ", square=" + this.getSquare() + ", price=" + this.getPrice() + ", floor=" + this.getFloor() + ", numberOfRooms=" + this.getNumberOfRooms() + ", typeOfHousing=" + this.getTypeOfHousing() + ", customer=" + this.getCustomer() + ", description=" + this.getDescription() + ", creationDate=" + this.getCreationDate() + ")";
    }
}
