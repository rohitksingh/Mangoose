package com.omdb.rohksin.Mangoose.Redesign.MoshiModels;

import android.support.annotation.NonNull;

import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable, Comparable<Role>{

    public String release_date;
    public boolean adult;
    public String title;
    public String original_language;
    public String character;
    public String original_title;
    public String poster_path;
    public int id;
    public String backdrop_path;
    public String overview;
    public String credit_id;
    public List<Integer> genre_ids;

    @Override
    public int compareTo(@NonNull Role role) {

        String date1 = MovieUtils.getSortedDate(this.release_date);

        String date2 = MovieUtils.getSortedDate(role.release_date);

        return date2.compareTo(date1);

    }
}
