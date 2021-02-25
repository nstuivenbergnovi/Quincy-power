package com.quincy.database_test.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

    @Entity
    @Table(name = "users")
    public class User {

        @Id
        @Column(nullable = false, unique = true)
        private String username;

        @Column(nullable = false, length = 255)
        private String password;

        @Column
        private boolean enabled = true;

        @Column
        private String apikey;

        @OneToMany(
                targetEntity = com.quincy.database_test.model.Authority.class,
                mappedBy = "username",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.EAGER)

        private Set<Authority> authorities = new HashSet<>();


        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getApikey() { return apikey; }
        public void setApikey(String apikey) { this.apikey =apikey; }

        public Set<com.quincy.database_test.model.Authority> getAuthorities() { return authorities; }
        public void addAuthority(com.quincy.database_test.model.Authority authority) {
            this.authorities.add(authority);
        }
        public void removeAuthority(com.quincy.database_test.model.Authority authority) {
            this.authorities.remove(authority);
        }

    }
