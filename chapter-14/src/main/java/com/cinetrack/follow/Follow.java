package com.cinetrack.follow;

import com.cinetrack.user.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "follows")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "follower_id")
    private AppUser follower;

    @ManyToOne(optional = false)
    @JoinColumn(name = "following_id")
    private AppUser following;

    protected Follow() {
        // JPA
    }

    public Follow(AppUser follower, AppUser following) {
        this.follower = follower;
        this.following = following;
    }

    public Long getId() { return id; }

    public AppUser getFollower() { return follower; }

    public AppUser getFollowing() { return following; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Follow follow)) return false;
        return id != null && Objects.equals(id, follow.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
