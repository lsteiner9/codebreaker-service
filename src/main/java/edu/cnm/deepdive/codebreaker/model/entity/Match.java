package edu.cnm.deepdive.codebreaker.model.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(
    indexes = {
        @Index(columnList = "codeLength"),
        @Index(columnList = "gameCount"),
        @Index(columnList = "started,deadline")
    }
)
public class Match {

  @NonNull
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "match_id", nullable = false, updatable = false,
      columnDefinition = "CHAR(16) FOR BIT DATA")
  private UUID id;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date started;

  @Column(updatable = false)
  private int codeLength;

  @NonNull
  @Column(nullable = false, updatable = false)
  private String pool;

  @Column(updatable = false)
  private int gameCount;

  @NonNull
  @Column(nullable = false, updatable = false)
  private Criterion criterion;

  @NonNull
  @Column(nullable = false, updatable = false)
  private Date deadline;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "originator_id", nullable = false, updatable = false)
  private User originator;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "winner_id")
  private User winner;

  @NonNull
  @ManyToMany(fetch = FetchType.EAGER,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(joinColumns = {@JoinColumn(name = "match_id")},
      inverseJoinColumns = {@JoinColumn(name = "user_id")})
  @OrderBy("displayName ASC")
  private final List<User> players = new LinkedList<>();

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public Date getStarted() {
    return started;
  }

  public int getCodeLength() {
    return codeLength;
  }

  public void setCodeLength(int codeLength) {
    this.codeLength = codeLength;
  }

  @NonNull
  public String getPool() {
    return pool;
  }

  public void setPool(@NonNull String pool) {
    this.pool = pool;
  }

  public int getGameCount() {
    return gameCount;
  }

  public void setGameCount(int gameCount) {
    this.gameCount = gameCount;
  }

  @NonNull
  public Criterion getCriterion() {
    return criterion;
  }

  public void setCriterion(@NonNull Criterion criterion) {
    this.criterion = criterion;
  }

  @NonNull
  public Date getDeadline() {
    return deadline;
  }

  public void setDeadline(@NonNull Date deadline) {
    this.deadline = deadline;
  }

  @NonNull
  public User getOriginator() {
    return originator;
  }

  public void setOriginator(@NonNull User originator) {
    this.originator = originator;
  }

  public User getWinner() {
    return winner;
  }

  public void setWinner(User winner) {
    this.winner = winner;
  }

  @NonNull
  public List<User> getPlayers() {
    return players;
  }

  public enum Criterion {
    GUESSES_TIME, TIME_GUESSES
  }
}
