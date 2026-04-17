package complexity.entities;

import java.time.LocalDateTime;
import java.util.Objects;

//
public class Resident implements Comparable <Resident> {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDateTime lastVisited;

    public Resident(String lastName, int id, String firstName) {
        this.lastName = lastName;
        this.id = id;
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Resident resident = (Resident) o;
        return id == resident.id && Objects.equals(firstName, resident.firstName) && Objects.equals(lastName, resident.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    // Bestemmer hvordan TreeSet sorteres
    @Override
    public int compareTo(Resident o) {
        if (this.id < o.id) {
            return -1;
        } else if (this.id > o.id) {
            return 1;
        } else {
            return 0; // Hvis de er ens
        }
    }


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(LocalDateTime lastVisited) {
        this.lastVisited = lastVisited;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
