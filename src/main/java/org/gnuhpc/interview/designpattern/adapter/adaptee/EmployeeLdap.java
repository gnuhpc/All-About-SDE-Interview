package org.gnuhpc.interview.designpattern.adapter.adaptee;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmployeeLdap {

    private String cn;
    private String surname;
    private String givenName;
    private String mail;

}
