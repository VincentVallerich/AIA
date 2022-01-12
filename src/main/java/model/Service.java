package model;

import java.util.Objects;

public class Service {

    private long id;
    private String title;
    private String description;
    private int points;
    private State state;
    private long beneficiaryId;

    public Service() {
        this.id = 0;
        this.title = "";
        this.description = "";
        this.points = 0;
        this.state = State.PENDING;
        this.beneficiaryId = 0;
    }

    public Service(String title, String description, int points, long beneficiaryId) {
        this();
        this.title = title;
        this.description = description;
        this.points = points;
        this.beneficiaryId = beneficiaryId;
    }

    public Service(String title, String description, int points) {
        this(title, description, points, 0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getdescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void accept() {
        this.state = State.ACCEPT;
    }

    public void reject() {
        this.state = State.REJECT;
    }

    public long getBeneficiaryId() {
        return beneficiaryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id == service.id && points == service.points && beneficiaryId == service.beneficiaryId && Objects.equals(title, service.title) && Objects.equals(description, service.description) && state == service.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, points, state, beneficiaryId);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", state=" + state +
                ", beneficiaryId=" + beneficiaryId +
                '}';
    }
}
