package com.zengzhi.nettyall.OptionalTest;

/**
 * @author xiejiawei
 * @Date 2021-05-01
 * @Time 11:36
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String name;

    private String position;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private Address address;

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public class Address {
        private Country country;

        public Optional<Country> getCountry() {
            return Optional.ofNullable(country);
        }

        // ...
        public class Country {
            private String code;

            public String getCode() {
                return code;
            }

            // ...
        }
    }

}
