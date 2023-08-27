package net.greeta.movie.vote.common.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.greeta.movie.cqrs.core.domain.BaseEntity;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserVote extends BaseEntity {
    @Id
    private String id;
    private String username;
    private String imdbId;
    private int score;
    private Date creationDate;
}
