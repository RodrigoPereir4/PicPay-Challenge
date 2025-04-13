package com.picpaychallenge.dto;

import java.math.BigDecimal;

public record TransferDto(BigDecimal value, Long senderId, Long receiverId) {
    
}
