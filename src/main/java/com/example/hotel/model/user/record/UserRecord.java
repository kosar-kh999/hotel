package com.example.hotel.model.user.record;

import java.util.List;

public record UserRecord(Integer userId, List<Integer> roleIds) {
}
