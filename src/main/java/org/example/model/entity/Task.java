package org.example.model.entity;

import org.example.model.entity.enumeration.Status;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class Task implements Serializable, Entity {

    @Serial
    private static final long serialVersionUID = 123L;

    private long taskId;
    private String name;
    private String description;
    private int experience;
    private String difficulty;
    private String category;
    private Status status;
    private Timestamp created;
    private Timestamp changed;
    private String answer;

    private Task() {}

    public long getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getExperience() {
        return experience;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCategory() {
        return category;
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

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (taskId != task.taskId) return false;
        if (experience != task.experience) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (difficulty != null ? !difficulty.equals(task.difficulty) : task.difficulty != null) return false;
        if (category != null ? !category.equals(task.category) : task.category != null) return false;
        if (status != task.status) return false;
        if (created != null ? !created.equals(task.created) : task.created != null) return false;
        if (changed != null ? !changed.equals(task.changed) : task.changed != null) return false;
        return answer != null ? answer.equals(task.answer) : task.answer == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (taskId ^ (taskId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + experience;
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (changed != null ? changed.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("taskId=").append(taskId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", experience=").append(experience);
        sb.append(", difficulty='").append(difficulty).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", status=").append(status);
        sb.append(", created=").append(created);
        sb.append(", changed=").append(changed);
        sb.append(", answer='").append(answer).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Builder newBuilder() {
        return new Task().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setTaskId(long taskId) {
            Task.this.taskId = taskId;
            return this;
        }

        public Builder setName(String name) {
            Task.this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            Task.this.description = description;
            return this;
        }

        public Builder setExperience(int experience) {
            Task.this.experience = experience;
            return this;
        }

        public Builder setDifficulty(String difficulty) {
            Task.this.difficulty = difficulty;
            return this;
        }


        public Builder setCategory(String category) {
            Task.this.category = category;
            return this;
        }

        public Builder setStatus(Status status) {
            Task.this.status = status;
            return this;
        }

        public Builder setCreated(Timestamp created) {
            Task.this.created = created;
            return this;
        }

        public Builder setChanged(Timestamp changed) {
            Task.this.changed = changed;
            return this;
        }

        public Builder setAnswer(String answer) {
            Task.this.answer = answer;
            return this;
        }

        public Task build() {
            return Task.this;
        }
    }
}
