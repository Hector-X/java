package com.cultivation.javaBasic.showYourIntelligence;

import java.util.Objects;

@SuppressWarnings("unused")
public class PersonForEquals {
    private final String name;
    private final short yearOfBirth;

    public PersonForEquals(String name, short yearOfBirth) {
        if (name == null) {
            throw new IllegalArgumentException("name is mandatory.");
        }

        if (yearOfBirth <= 1900 || yearOfBirth >= 2019) {
            throw new IllegalArgumentException("year of birth is out of range.");
        }

        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }


    public String getName() {
        return name;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonForEquals)) return false;
        PersonForEquals that = (PersonForEquals) o;
        return yearOfBirth == that.yearOfBirth &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, yearOfBirth);
    }
}
