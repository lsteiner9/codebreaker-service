package edu.cnm.deepdive.codebreaker.model.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class Game {

  @NonNull
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "game_id", nullable = false, updatable = false,
      columnDefinition = "CHAR(16) FOR BIT DATA")
  private UUID id;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "match_id", nullable = false, updatable = false)
  private Match match;

  @NonNull
  @Column(nullable = false, updatable = false)
  private String code;

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public Match getMatch() {
    return match;
  }

  public void setMatch(@NonNull Match match) {
    this.match = match;
  }

  @NonNull
  public String getCode() {
    return code;
  }

  public void setCode(@NonNull String code) {
    this.code = code;
  }
}
