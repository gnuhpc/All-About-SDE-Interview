package org.gnuhpc.interview.lang.java8.misc;

import java.util.Optional;

/**
 * Created by gnuhpc on 2017/1/11.
 */
public class OptionalAddress {
    private String addressLine;  // never null
    private String city;         // never null
    private String postcode;     // optional, thus may be null

    public OptionalAddress() {
    }

    // constructor ensures non-null fields really are non-null
    // optional field can just be stored directly, as null means optional
    public OptionalAddress(String addressLine, String city, String postcode) {
        this.addressLine = addressLine;
        this.city = city;
        this.postcode = postcode;
    }

    // normal getters
    public String getAddressLine() {
        return addressLine;
    }

    public String getCity() {
        return city;
    }

    // special getter for optional field
    public Optional<String> getPostcode() {
        return Optional.ofNullable(postcode);
    }

    // return optional instead of null for business logic methods that may not find a result
    public static Optional<OptionalAddress> findAddress(String userInput) {
        Optional<OptionalAddress> address = Optional.empty();
        if (userInput.equals("abcd")) {
            address = Optional.of(new OptionalAddress("addressline1", "city1", "postcode1"));
        }
        return address;// find the address, returning Optional.empty() if not found
    }

    public static void main(String[] args) {
        Optional<OptionalAddress> address = OptionalAddress.findAddress("abcd");
        address.ifPresent(add -> System.out.println(add.getAddressLine()));
        address = OptionalAddress.findAddress("a");
        address.ifPresent(add -> System.out.println(add.getAddressLine()));
    }
}
