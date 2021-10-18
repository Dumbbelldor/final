package org.example.model.entity;

import org.example.model.entity.enumeration.Status;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable, Entity {

    @Serial
    private static final long serialVersionUID = 123L;

    private long userId;
    private String login;
    private String password;
    private String email;
    private int experience;
    private String level;
    private Status status;
    private Timestamp created;
    private Timestamp changed;
    private String picture;

    private User() {}

    public long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getExperience() {
        return experience;
    }

    public String getLevel() {
        return level;
    }

    public Status getStatus() {
        return status;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public String getPicture() {
        return picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (experience != user.experience) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (level != null ? !level.equals(user.level) : user.level != null) return false;
        if (status != user.status) return false;
        if (created != null ? !created.equals(user.created) : user.created != null) return false;
        if (changed != null ? !changed.equals(user.changed) : user.changed != null) return false;
        return picture != null ? picture.equals(user.picture) : user.picture == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + experience;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (changed != null ? changed.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", experience=").append(experience);
        sb.append(", level='").append(level).append('\'');
        sb.append(", status=").append(status);
        sb.append(", created=").append(created);
        sb.append(", changed=").append(changed);
        sb.append(", picture='").append(picture).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setUserId(long userId) {
            User.this.userId = userId;
            return this;
        }

        public Builder setLogin(String login) {
            User.this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public Builder setExperience(int experience) {
            User.this.experience = experience;
            return this;
        }

        public Builder setLevel(String level) {
            User.this.level = level;
            return this;
        }

        public Builder setStatus(Status status) {
            User.this.status = status;
            return this;
        }

        public Builder setCreated(Timestamp created) {
            User.this.created = created;
            return this;
        }

        public Builder setChanged(Timestamp changed) {
            User.this.changed = changed;
            return this;
        }

        public Builder setPicture(String picture) {
            User.this.picture = picture;
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
