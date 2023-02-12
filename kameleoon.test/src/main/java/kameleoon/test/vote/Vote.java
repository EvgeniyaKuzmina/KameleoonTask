package kameleoon.test.vote;

import jakarta.persistence.*;
import kameleoon.test.quote.Quote;
import kameleoon.test.user.User;
import lombok.*;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "likes")
    private Boolean like;
    @Column(name = "dislikes")
    private Boolean dislike;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @ManyToOne
    @JoinColumn(name = "quotes_id", nullable = false)
    private Quote quote;
}
