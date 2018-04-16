package com.zehava.cityforest;

import com.mapbox.services.commons.models.Position;

public final class Constants {

    public static final Position DEFAULT_JERUSALEM_COORDINATE
            = Position.fromCoordinates(35.207089, 31.771559);

    public static final String CHOSEN_COORDINATE = "Chosen Coordinate";
    public static final String NEW_COORDINATE_ID = "Coordinate ID";
    public static final String IS_COORDINATE_MAP_CREATED = "Is coordinate map created";
    public static final String COORDINATE_KEY = "Coordinate key";
    public static final String CHOSEN_TRACK = "Chosen Track";
    public static final String TRACK_EDIT = "Track Edit";
    public static final String SELECTED_TRACK = "Selected Track";
    public static final String CREATED_COORDINATE_FOR_ZOOM = "Created coordinate for zoom";
    public static final String EDITED_COORDINATE_FOR_ZOOM = "Edited coordinate for zoom";
    public static final String CREATED_UPDATE_FOR_ZOOM = "Created update for zoom";
    public static final String TRACK_STARTING_POINT = "Track Starting point";
    public static final String TRACK_ENDING_POINT = "Track Ending point";
    public static final String TRACK_STARTING_POINT_NAME = "Track Starting name";
    public static final String TRACK_ENDING_POINT_NAME = "Track Ending name";

    public static final String SEARCH_RESULT_QUERY = "Search Result query";
    public static final String Q_STARTING_POINT = "Starting point";
    public static final String Q_ENDING_POINT = "Ending point";
    public static final String Q_LEVEL = "Level";
    public static final String Q_SEASON = "Season";
    public static final String Q_HAS_WATER = "Has water";
    public static final String Q_SUITABLE_FOR_BIKES = "Suitable for bikes";
    public static final String Q_SUITABLE_FOR_DOGS = "Suitable for dogs";
    public static final String Q_SUITABLE_FOR_FAMILIES = "Suitable for families";
    public static final String Q_IS_ROMANTIC = "Is romantic";

    public static final String LANGUAGE_TO_LOAD = "languageToLoad";
    public static final String LANGUAGE = "language";
    public static final String HEBREW = "he";
    public static final String ENGLISH = "en";


    public static final int NEW_COORDINATE = 100;
    public static final int COORDINATE_CREATED = 101;
    public static final int EDIT_COORDINATE = 102;
    public static final int COORDINATE_EDITED = 103;
    public static final int MAX_NUM_OF_TRACK_COORDINATES = 25;
    public static final int NEW_TRACK = 200;
    public static final int TRACK_CREATED = 201;
    public static final int TRACK_EDITED = 202;
    public static final int NEW_USER_UPDATE = 300;
    public static final int USER_UPDATE_CREATED = 301;
    public static final int USER_UPDATE_EDITED = 302;
    public static final int PICK_IMAGE_REQUEST = 400;
    public static final int PICK_POINTS_REQUEST =500;
    public static final int PICK_POINTS_CANCALED =500;
    public static final int PICK_POINTS_DONE =501;


    public static boolean ADD_COORDINATE_MODE = false;
    public static boolean DELETE_COORDINATE_MODE = false;
    public static boolean EDIT_COORDINATE_MODE = false;
    public static boolean ADD_TRACK_MODE = false;
    public static boolean FINISH_EDIT_TRACK_MODE = false;
    public static boolean SHOW_DETAILS_POPUP = false;
    public static boolean SHOW_TRACK_POPUP = false;
    public static boolean SHOW_UPDATE_POPUP = false;
    public static boolean MOVE_MARKER = false;

    public static int ROUTE_LINE_WIDTH = 4;
    public static int ZOOM_LEVEL_CURRENT_LOCATION = 13;
    public static int ZOOM_LEVEL_MARKER_CLICK = 16;
    public static int ZOOM_LEVEL_LOCATION = 18;
    public static int RC_SIGN_IN = 1;


    public static final String CURRENT_USER_UID = "current user uid";
    public static final String CURRENT_USER_NAME = "current user name";
    public static final String UPDATE_POSITION = "update position";
    public static final String IMAGE_NAME = "image name";
    public static final String IMAGE_EXTRA = "image extra";

}
