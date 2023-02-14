package kameleoon.test.quote.model;

import kameleoon.test.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "modification_date", nullable = false)
    private LocalDateTime modificationDate;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

}
