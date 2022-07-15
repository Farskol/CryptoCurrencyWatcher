package cryptocurrency.demo.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@ToString(of = {"id", "username", "cryptocurrency_id"})
@EqualsAndHashCode(of = {"id"})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    private String username;
    private Long cryptocurrency_id;
    private Double price_usd;

    public Users(Long user_id, String username, Long cryptocurrency_id, Double price_usd) {
        this.user_id = user_id;
        this.username = username;
        this.cryptocurrency_id = cryptocurrency_id;
        this.price_usd = price_usd;
    }

    public Users(String username, Long cryptocurrency_id, Double price_usd) {
        this.username = username;
        this.cryptocurrency_id = cryptocurrency_id;
        this.price_usd = price_usd;
    }

    public Users(){}

    public Double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(Double price_usd) {
        this.price_usd = price_usd;
    }

    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCryptocurrency_id() {
        return cryptocurrency_id;
    }

    public void setCryptocurrency_id(Long cryptocurrency_id) {
        this.cryptocurrency_id = cryptocurrency_id;
    }
}
