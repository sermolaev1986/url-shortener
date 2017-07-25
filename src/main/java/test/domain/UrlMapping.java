package test.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "url_mapping")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class UrlMapping {

    @Id
    private String fullUrl;

    @Column
    private String shortUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
