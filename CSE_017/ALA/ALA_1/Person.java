/**
 * Abstract class Person to model the entity Person
 */
public abstract class Person{
    // Data members 
    protected int id;
    protected String name, address, phone, email;
    /**
     * Default constructor
     */
    protected Person(){
        this(0, "none", "none", "none", "none");
    }
    /**
     * Constructor with five parameters
     * @param id initial value for the id
     * @param name initial value for the name
     * @param address initial value for the address
     * @param phone initial value for the phone number
     * @param email initial value for the email address
     */
    protected Person(int id, String name, String address, String phone, String email){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    /**
     * Accessor for the id
     * @return value of the id
     */
    public int getID(){
        return id;
    }
    /**
     * Accessor for the name
     * @return value of the name
     */
    public String getName(){
        return name;
    }
    /**
     * Accessor for the address
     * @return value of the address
     */
    public String getAddress(){
        return address;
    }
    /**
     * Accessor for the phone
     * @return value of the phone
     */
    public String getPhone(){
        return phone;
    }
    /**
     * Accessor for the email address
     * @return value of the email address
     */
    public String getEmail(){
        return email;
    }
    /**
     * Accessor for the Person attributes
     * @return formatted string with the object attributes
     */
    public String toString(){
        String str = String.format("ID: %d\nName: %s\nAddress: %s\nPhone: %s\nEmail: %s\n", id, name, address, phone, email);
        return str;
    }
    /**
     * Mutator for the id
     * @param id value of the id
     */
    public void setID(int id){
        this.id = id;
    }
    /**
     * Mutator for the name
     * @param name value of the name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Mutator for the address
     * @param address value of the address
     */
    public void setAdress(String address){
        this.address = address;
    }
    /**
     * Mutator for the phone number
     * @param phone value of the phone number
     */
    public void setPhone(String phone){
        this.phone = phone;
    }
    /**
     * Mutator for the email address
     * @param email value of the email address
     */
    public void setEmail(String email){
        this.email = email;
    }
}