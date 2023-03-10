package org.real013228.pl.models;

import java.util.Calendar;
import java.util.List;

public record CreateOwnerModel(String name, Calendar birthDate, List<Integer> cats) {
}
