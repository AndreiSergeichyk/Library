package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private Long id;
    private String name;
    private String password;
    private String addresMailBox;
    private Role rols;
    private Set<Book> books = new HashSet<>();
}
