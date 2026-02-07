package db.env_example;

/*
This is the structure I'm using to store user data and database connection information. 
Replace the empty constants with your user information and import the classes into your code to 
establish the database connection.
*/

public class SqlAdmin {
    //Admin com privilégios totais
    static public class AdminDb{
        static private final String URL;
        static private final String USER;
        static private final String PASSWORD;

        public static String getUrl(){ return URL; };
        public static String getUser(){ return USER; };
        public static String getPassword(){ return PASSWORD; };
    }

    //Gerenciador dos inventários
    static public class AdminInventories{
        static private final String URL;
        static private final String USER;
        static private final String PASSWORD;

        public static String getUrl(){ return URL; };
        public static String getUser(){ return USER; };
        public static String getPassword(){ return PASSWORD; };
    }

    //Gerenciador dos usuários
    static public class AdminUsers{
        static private final String URL;
        static private final String USER;
        static private final String PASSWORD;

        public static String getUrl(){ return URL; };
        public static String getUser(){ return USER; };
        public static String getPassword(){ return PASSWORD; };
    }
}

