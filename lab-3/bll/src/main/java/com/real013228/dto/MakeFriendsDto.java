package com.real013228.dto;

import jakarta.validation.constraints.Min;

public record MakeFriendsDto(@Min(1) Long firstCat, @Min(1)Long secondCat){
}
