package hashMap;

public class Adress
{
    private static String city;

    private static String street;

    private static String number;

    private static String zip;

    Adress(String city, String street, String number, String zip)
    {
        Adress.city = city;
        Adress.street = street;
        Adress.number = number;
        Adress.zip = zip;
    }

    public String getCity()
    {
        return city;
    }

    public static void setCity(String city)
    {
        Adress.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public static void setStreet(String street)
    {
        Adress.street = street;
    }

    public String getNumber()
    {
        return number;
    }

    public static void setNumber(String number)
    {
        Adress.number = number;
    }

    public static String getZip()
    {
        return zip;
    }

    public static void setZip(String zip)
    {
        Adress.zip = zip;
    }
}
