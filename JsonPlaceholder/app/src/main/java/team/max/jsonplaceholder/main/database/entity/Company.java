package team.max.jsonplaceholder.main.database.entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "company")
public class Company {

    @DatabaseField(columnName = "company_id", canBeNull = false, generatedId = true)
    private long companyId;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "catchPhrase")
    private String catchPhrase;

    @DatabaseField(columnName = "bs")
    private String bs;

    public Company() {
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
