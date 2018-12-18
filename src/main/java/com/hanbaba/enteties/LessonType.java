package com.hanbaba.enteties;


public enum LessonType {
    GMARA,
    HALACHOT,
    MOED,
    PARASHA,
    AVOT,
    MUSAR;


    public static LessonType getLessonFromString(String lessonType) throws Exception {
        switch (lessonType) {
            case "gmara":
                return GMARA;
            case "GMARA":
                return GMARA;
            case "halacha":
                return HALACHOT;
            case "HALACHOT":
                return HALACHOT;
            case "avot":
                return AVOT;
            case "AVOT":
                return AVOT;
            case "parasha":
                return PARASHA;
            case "PARASHA":
                return PARASHA;
            case "moed":
                return MOED;
            case "MOED":
                return MOED;
            case "musar":
                return MUSAR;
            case "MUSAR":
                return MUSAR;
            default:
                throw new Exception("Couldn't find lesson type in enum!");
        }
    }

    public static String getPathToFileFromType(LessonType type) throws Exception {
        switch (type) {
            case AVOT:
                return "/public/music/MP3/PerkeyAvot/";
            case MOED:
                return "/public/music/MP3/Moed/";
            case GMARA:
                return "/public/music/MP3/Gmara/";
            case PARASHA:
                return "/public/music/MP3/Parashot/";
            case HALACHOT:
                return "/public/music/MP3/HalahotShabat/";
            case MUSAR:
                return "/public/music/MP3/Musar/";
            default:
                throw new Exception("Couldn't find lesson type in enum!");
        }
    }


    public static String getTitleFromType(LessonType type) throws Exception {
        switch (type) {
            case AVOT:
                return "פרקי אבות";
            case MOED:
                return "מועדים";
            case GMARA:
                return "גמרא - מסכת קידושין";
            case PARASHA:
                return "פרשת השבוע";
            case HALACHOT:
                return "הלכות שבת קודש";
            case MUSAR:
                return "מוסר";
            default:
                throw new Exception("Couldn't find lesson type in enum!");
        }
    }

}
