package kameleoon.test.vote;

import kameleoon.test.quote.Quote;
import kameleoon.test.user.User;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "like")
    private Boolean like;
    @Column(name = "dislike")
    private Boolean dislike;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @Column(name = "quotes_id", nullable = false)
    private Quote quote;
}
