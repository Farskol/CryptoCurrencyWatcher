package cryptocurrency.demo.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@ToString(of = {"cryptocurrency_id", "symbol", "name", "price_usd"})
@EqualsAndHashCode(of = {"cryptocurrency_id", "symbol", "name", "price_usd"})
public class Cryptocurrency {

    @Id
    private Long cryptocurrency_id;
    private String symbol;
    private String name;
    private Double price_usd;

    public Cryptocurrency(Long cryptocurrency_id, String symbol, String name, double price_usd){
        this.cryptocurrency_id = cryptocurrency_id;
        this.symbol = symbol;
        this.name = name;
        this.price_usd = price_usd;
    }

    public Cryptocurrency() {}

    public Long getCryptocurrency_id() {
        return cryptocurrency_id;
    }

    public void setCryptocurrency_id(Long cryptocurrency_id) {
        this.cryptocurrency_id = cryptocurrency_id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(Double price_usd) {
        this.price_usd = price_usd;
    }
}
