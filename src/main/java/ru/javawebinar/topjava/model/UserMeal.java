package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */

@Entity
@Table(name = "meals") //, uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
@NamedQueries({
        @NamedQuery(name = UserMeal.GET, query = "SELECT um FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId"),
        @NamedQuery(name = UserMeal.GET_ALL, query = "SELECT um FROM UserMeal um WHERE um.user.id=:userId " +
                "ORDER BY um.dateTime DESC"),
        @NamedQuery(name = UserMeal.GET_BETWEEN, query = "SELECT um FROM UserMeal um WHERE um.user.id=:userId "
       + "AND um.dateTime BETWEEN :startDate AND :endDate ORDER BY um.dateTime DESC"),
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId"),
        @NamedQuery(name = UserMeal.UPDATE, query = "UPDATE UserMeal um SET um.calories=:calories, " +
                "um.dateTime=:dateTime, um.description=:description WHERE um.id=:id AND um.user.id=:userId")
})
public class UserMeal extends BaseEntity {

    public static final String GET = "UserMeal.get";
    public static final String GET_ALL = "UserMeal.getAll";
    public static final String GET_BETWEEN = "UserMeal.getBetween";
    public static final String DELETE = "UserMeal.delete";
    public static final String UPDATE = "UserMeal.update";

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @NotEmpty
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "calories")
    @Digits(fraction = 0, integer = 4)
    protected int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private User user;

    public UserMeal() {
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

}

