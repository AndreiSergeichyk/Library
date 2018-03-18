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
public class Book {

    private Long id;
    private String name;
    private Genre genre;
    private Integer page;
    private Set<Review> reviews = new HashSet<>();
    private Set<Author> authors = new HashSet<>();
}
