package com.omdb.rohksin.Mangoose.NewSearch.Utility;

import com.omdb.rohksin.Mangoose.NewSearch.POJO.Genre;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.Language;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Illuminati on 6/22/2017.
 */
public final class FilterUtility {

    public static enum AllFilters{
        GENRES,YEAR,ORIGINAL_LANGUAGE
    }


    public static enum GenreFilters{
        WAR,ROMANCE,THRILLER,COMEDY,ANIMATED
    }

    public static enum LanguageFilters{
        HINDI,ENGLISH,MARATHI,TAMIL,TELUGU
    }

    public Map<GenreFilters,Genre> getAllGenre()
    {
        Map<GenreFilters,Genre> map = new LinkedHashMap<GenreFilters,Genre >();
        map.put(GenreFilters.WAR,new Genre("WAR","18"));
        map.put(GenreFilters.ANIMATED,new Genre("Animated","28"));
        return map;
    }

    public Map<LanguageFilters,Language> getAllLanguages()
    {
        Map<LanguageFilters,Language> map = new LinkedHashMap<LanguageFilters,Language>();

        map.put(LanguageFilters.HINDI,new Language("Hindi","hi"));
        map.put(LanguageFilters.ENGLISH,new Language("English","en"));
        map.put(LanguageFilters.MARATHI,new Language("Marathi","mr"));
        map.put(LanguageFilters.TAMIL,new Language("Tamil","ta"));
        map.put(LanguageFilters.TELUGU,new Language("Telugu","te"));
        return map;
    }


}
