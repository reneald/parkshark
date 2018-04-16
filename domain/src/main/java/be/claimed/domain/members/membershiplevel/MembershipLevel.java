package be.claimed.domain.members.membershiplevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "mem_levels")
public enum MembershipLevel {
    BRONZE ("bronze", BigDecimal.ZERO, 0, 4),
    SILVER ("silver", BigDecimal.valueOf(10), 20, 6),
    GOLD ("gold", BigDecimal.valueOf(40), 30, 24);

    @Id
    @Column(name = "name")
    private String name;
    private BigDecimal monthlyCost;
    private double pricePerHourReduction;
    private double maximumAllowedAllocationTime;

    MembershipLevel() {
    }

    MembershipLevel(String name, BigDecimal monthlyCost, double pricePerHourReduction, double maximumAllowedAllocationTime) {
        this.name = name;
        this.monthlyCost = monthlyCost;
        this.pricePerHourReduction = pricePerHourReduction;
        this.maximumAllowedAllocationTime = maximumAllowedAllocationTime;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getMonthlyCost() {
        return monthlyCost;
    }

    public double getPricePerHourReduction() {
        return pricePerHourReduction;
    }

    public double getMaximumAllowedAllocationTime() {
        return maximumAllowedAllocationTime;
    }
}
