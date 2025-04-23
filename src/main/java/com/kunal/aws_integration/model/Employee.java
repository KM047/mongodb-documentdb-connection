package com.kunal.aws_integration.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    @Indexed(unique = true)
    private String email;
    @NonNull
    private String role;

    // getters and setters
}
