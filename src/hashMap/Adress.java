package hashMap;

public class Adress
{
    private String city;

    private String street;

    private String number;

    private String zip;

    Adress(String city, String street, String number, String zip)
    {
        this.city = city;
        this.street = street;
        this.number = number;
        this.zip = zip;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getNumber()
    {
        return this.number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getZip()
    {
        return this.zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    @Override
    public int hashCode()
    {
        int hash = 13;
        hash += this.city.hashCode();
        hash += this.street.hashCode();
        hash += this.number.hashCode();
        hash += this.zip.hashCode();
        return Math.abs(hash);

    }

    @Override
    public String toString()
    {
        return city.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }

        Adress adress = (Adress) o;
        return this.city.equals(adress.city) &&
               this.street.equals(adress.street) &&
               this.number.equals(adress.number) &&
               this.zip.equals(adress.zip);
    }
}
