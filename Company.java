package mini;

public class Company {
    private int id;
    private String name, contactPerson, phone, email, address;
    public Company(int id, String name, String contactPerson,
                   String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getContactPerson() { return contactPerson; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
}