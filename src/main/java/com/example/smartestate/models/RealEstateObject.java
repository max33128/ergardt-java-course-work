package com.example.smartestate.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "real_estate_objects")
public class RealEstateObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "square")
    private Double square;

    @Column(name = "price")
    private Double price;

    @Column(name = "floor")
    private int floor;

    @Column(name = "number_of_rooms")
    private String numberOfRooms;

    @Column(name = "type_of_housing")
    private String typeOfHousing;

    @Column(name = "state_of_object")
    private String stateOfObject;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User agent;

    @Column(name = "agentText", nullable = true)
    private String agentText;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User developer;

    @Column(name = "developerText", nullable = true)
    private String developerText;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "object")
    private List<Transaction> transactions = new ArrayList<>();

    @Column(name = "description")
    private String description;

    public RealEstateObject() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RealEstateObject;
    }

    public Long getId() {
        return this.id;
    }

    public String getAddress() {
        return this.address;
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

    public String getNumberOfRooms() {
        return this.numberOfRooms;
    }

    public String getTypeOfHousing() {
        return this.typeOfHousing;
    }

    public String getStateOfObject() {
        return this.stateOfObject;
    }

    public User getAgent() {
        return this.agent;
    }

    public String getAgentText() {
        return this.agentText;
    }

    public User getDeveloper() {
        return this.developer;
    }

    public String getDeveloperText() {
        return this.developerText;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setTypeOfHousing(String typeOfHousing) {
        this.typeOfHousing = typeOfHousing;
    }

    public void setStateOfObject(String stateOfObject) {
        this.stateOfObject = stateOfObject;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    public void setAgentText(String agentText) {
        this.agentText = agentText;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public void setDeveloperText(String developerText) {
        this.developerText = developerText;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RealEstateObject)) return false;
        final RealEstateObject other = (RealEstateObject) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
        final Object this$square = this.getSquare();
        final Object other$square = other.getSquare();
        if (this$square == null ? other$square != null : !this$square.equals(other$square)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        if (this.getFloor() != other.getFloor()) return false;
        final Object this$numberOfRooms = this.getNumberOfRooms();
        final Object other$numberOfRooms = other.getNumberOfRooms();
        if (this$numberOfRooms == null ? other$numberOfRooms != null : !this$numberOfRooms.equals(other$numberOfRooms))
            return false;
        final Object this$typeOfHousing = this.getTypeOfHousing();
        final Object other$typeOfHousing = other.getTypeOfHousing();
        if (this$typeOfHousing == null ? other$typeOfHousing != null : !this$typeOfHousing.equals(other$typeOfHousing))
            return false;
        final Object this$stateOfObject = this.getStateOfObject();
        final Object other$stateOfObject = other.getStateOfObject();
        if (this$stateOfObject == null ? other$stateOfObject != null : !this$stateOfObject.equals(other$stateOfObject))
            return false;
        final Object this$agent = this.getAgent();
        final Object other$agent = other.getAgent();
        if (this$agent == null ? other$agent != null : !this$agent.equals(other$agent)) return false;
        final Object this$agentText = this.getAgentText();
        final Object other$agentText = other.getAgentText();
        if (this$agentText == null ? other$agentText != null : !this$agentText.equals(other$agentText)) return false;
        final Object this$developer = this.getDeveloper();
        final Object other$developer = other.getDeveloper();
        if (this$developer == null ? other$developer != null : !this$developer.equals(other$developer)) return false;
        final Object this$developerText = this.getDeveloperText();
        final Object other$developerText = other.getDeveloperText();
        if (this$developerText == null ? other$developerText != null : !this$developerText.equals(other$developerText))
            return false;
        final Object this$transactions = this.getTransactions();
        final Object other$transactions = other.getTransactions();
        if (this$transactions == null ? other$transactions != null : !this$transactions.equals(other$transactions))
            return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final Object $square = this.getSquare();
        result = result * PRIME + ($square == null ? 43 : $square.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        result = result * PRIME + this.getFloor();
        final Object $numberOfRooms = this.getNumberOfRooms();
        result = result * PRIME + ($numberOfRooms == null ? 43 : $numberOfRooms.hashCode());
        final Object $typeOfHousing = this.getTypeOfHousing();
        result = result * PRIME + ($typeOfHousing == null ? 43 : $typeOfHousing.hashCode());
        final Object $stateOfObject = this.getStateOfObject();
        result = result * PRIME + ($stateOfObject == null ? 43 : $stateOfObject.hashCode());
        final Object $agent = this.getAgent();
        result = result * PRIME + ($agent == null ? 43 : $agent.hashCode());
        final Object $agentText = this.getAgentText();
        result = result * PRIME + ($agentText == null ? 43 : $agentText.hashCode());
        final Object $developer = this.getDeveloper();
        result = result * PRIME + ($developer == null ? 43 : $developer.hashCode());
        final Object $developerText = this.getDeveloperText();
        result = result * PRIME + ($developerText == null ? 43 : $developerText.hashCode());
        final Object $transactions = this.getTransactions();
        result = result * PRIME + ($transactions == null ? 43 : $transactions.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    public String toString() {
        return "RealEstateObject(id=" + this.getId() + ", address=" + this.getAddress() + ", square=" + this.getSquare() + ", price=" + this.getPrice() + ", floor=" + this.getFloor() + ", numberOfRooms=" + this.getNumberOfRooms() + ", typeOfHousing=" + this.getTypeOfHousing() + ", stateOfObject=" + this.getStateOfObject() + ", agent=" + this.getAgent() + ", agentText=" + this.getAgentText() + ", developer=" + this.getDeveloper() + ", developerText=" + this.getDeveloperText() + ", transactions=" + this.getTransactions() + ", description=" + this.getDescription() + ")";
    }
}
