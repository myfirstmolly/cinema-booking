package com.rita.cinema.domain;

public enum Rating {
    G("G", 0),
    PG13("PG-13", 13),
    R("R", 16),
    NC17("NC-17", 18);

    private final String rating;
    private final int age;

    Rating(String rating, int age) {
        this.rating = rating;
        this.age = age;
    }

    public String getRating() {
        return rating;
    }

    public int getAge() {
        return age;
    }

}
