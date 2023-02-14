package kameleoon.test.quote.vote;

import javax.persistence.*;
import kameleoon.test.quote.model.Quote;
import kameleoon.test.user.User;
import lombok.*;

import java.time.LocalDateTime;


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
    @Column(name = "date_added", nullable = false)
    private LocalDateTime modificationDate;
}
