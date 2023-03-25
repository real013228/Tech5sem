package org.real013228.lab3.pl.models;

import java.util.Calendar;
import java.util.List;

public record CreateCatModel(String name, Calendar birthDate, String breed, String color, int ownerId, List<Integer> friends) {
}
