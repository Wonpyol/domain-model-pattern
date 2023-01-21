package com.example.repository;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class OrderSearch {
    private String memberName;
    private String orderStatus;
}
