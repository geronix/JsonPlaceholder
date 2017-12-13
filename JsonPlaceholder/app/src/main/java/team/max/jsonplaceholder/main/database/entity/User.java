package team.max.jsonplaceholder.main.database.entity;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User {

    @DatabaseField(columnName = "user_id", canBeNull = false, generatedId = true, dataType = DataType.LONG)
    private long userId;

    @DatabaseField(columnName = "server_user_id", canBeNull = false)
    private long serverUserId;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "username")
    private String username;

    @DatabaseField(columnName = "email")
    private String email;

    @DatabaseField(columnName = "phone")
    private String phone;

    @DatabaseField(columnName = "website")
    private String website;

    @DatabaseField(columnName = "address_id", canBeNull = false,
            foreign = true, foreignAutoRefresh = true)
    private Address address;

    @DatabaseField(columnName = "company_id", canBeNull = false,
            foreign = true, foreignAutoRefresh = true)
    private Company company;

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getServerUserId() {
        return serverUserId;
    }

    public void setServerUserId(long serverUserId) {
        this.serverUserId = serverUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
