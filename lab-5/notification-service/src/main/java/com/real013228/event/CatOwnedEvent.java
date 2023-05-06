package com.real013228.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatOwnedEvent {
    private Long ownerId;
    private Long catId;
}
