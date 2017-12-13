package team.max.jsonplaceholder.main.database.entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "address")
public class Address {

    @DatabaseField(columnName = "address_id", canBeNull = false, generatedId = true)
    private long addressId;

    @DatabaseField(columnName = "street")
    private String street;

    @DatabaseField(columnName = "suite")
    private String suite;

    @DatabaseField(columnName = "city")
    private String city;

    @DatabaseField(columnName = "zipcode")
    private String zipcode;

    public Address() {
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
