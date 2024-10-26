package com.example.hotel.core.record;

import java.util.List;

public record UserRecord(Integer userId, List<Integer> roleIds) {
}
