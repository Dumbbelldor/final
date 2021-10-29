package org.example.model.entity;

import java.io.Serial;
import java.io.Serializable;

public class Achievement implements Entity, Serializable {

    @Serial
    private static final long serialVersionUID = 123L;

    private long achId;
    private String name;
    private String flavor;
    private String picture;

    private Achievement() {}

    public long getAchId() {
        return achId;
    }

    public String getName() {
        return name;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getPicture() {
        return picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Achievement that = (Achievement) o;

        if (achId != that.achId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (flavor != null ? !flavor.equals(that.flavor) : that.flavor != null) return false;
        return picture != null ? picture.equals(that.picture) : that.picture == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (achId ^ (achId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (flavor != null ? flavor.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Achievement{");
        sb.append("achId=").append(achId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", flavor='").append(flavor).append('\'');
        sb.append(", picture='").append(picture).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Achievement.Builder newBuilder() {
        return new Achievement().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setAchId(Long achId) {
            Achievement.this.achId = achId;
            return this;
        }

        public Builder setName(String name) {
            Achievement.this.name = name;
            return this;
        }

        public Builder setFlavor(String flavor) {
            Achievement.this.flavor = flavor;
            return this;
        }

        public Builder setPicture(String picture) {
            Achievement.this.picture = picture;
            return this;
        }

        public Achievement build() {
            return Achievement.this;
        }
    }
}
