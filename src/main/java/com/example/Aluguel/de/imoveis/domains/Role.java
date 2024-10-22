package com.example.Aluguel.de.imoveis.domains;

import jakarta.persistence.*;


@Entity
@Table(name = "tb_roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "role_id")
    private Long roleId;
    private String name;
    public Role(){
    }
    public Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public enum Values{
        TENANT(1L),
        OWNER(2L),
        ADMIN(3L);
        long roleId;
        Values(long roleId){
            this.roleId=roleId;
        }
    }
}
