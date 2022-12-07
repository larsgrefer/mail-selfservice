package de.greferhosting.selfservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.unit.DataSize;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64)
    private String username;

    private String domain;

    private String password;

    private int quota;

    private boolean enabled;

    private boolean sendonly;

    public DataSize getQuota() {
        return DataSize.ofMegabytes(quota);
    }

    public void setQuota(DataSize quota) {
        this.quota = (int) quota.toMegabytes();
    }


}
