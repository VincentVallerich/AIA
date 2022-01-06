package model;

import java.util.Objects;

public class Service {

    private long id;
    private String title;
    private String desctiption;
    private int points;
    private State state;
    private long beneficiaryId;

    public Service() {
        this.id = 0;
        this.title = "";
        this.desctiption = "";
        this.points = 0;
        this.state = State.PENDING;
        this.beneficiaryId = 0;
    }

    public Service(String title, String description, int points, long beneficiaryId) {
        this();
        this.title = title;
        this.desctiption = description;
        this.points = points;
        this.beneficiaryId = beneficiaryId;
    }

    public Service(String title, String desctiption, int points) {
        this(title, desctiption, points, 0);
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

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
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
        return id == service.id && points == service.points && beneficiaryId == service.beneficiaryId && Objects.equals(title, service.title) && Objects.equals(desctiption, service.desctiption) && state == service.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, desctiption, points, state, beneficiaryId);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desctiption='" + desctiption + '\'' +
                ", points=" + points +
                ", state=" + state +
                ", beneficiaryId=" + beneficiaryId +
                '}';
    }
}
