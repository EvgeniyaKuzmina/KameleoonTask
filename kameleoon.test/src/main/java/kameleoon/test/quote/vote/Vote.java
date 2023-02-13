package kameleoon.test.quote.vote;

import jakarta.persistence.*;
import kameleoon.test.quote.model.Quote;
import kameleoon.test.user.User;
import lombok.*;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "likes")
    private Integer like;
    @Column(name = "dislikes")
    private Integer dislike;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @ManyToOne
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quote;
}
