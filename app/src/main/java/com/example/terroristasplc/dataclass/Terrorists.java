package com.example.terroristasplc.dataclass;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "sdnEntry", strict = false)
public class Terrorists {

    @Element(name = "firstName", required = false)
    private String firstName;
    @Element(name = "lastName", required = false)
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
