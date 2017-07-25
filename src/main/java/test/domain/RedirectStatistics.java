package test.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "redirect_statistics")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class RedirectStatistics {

    @Id
    private String fullUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UrlMapping urlMapping;

    @Column
    private Long redirectCount;

}
