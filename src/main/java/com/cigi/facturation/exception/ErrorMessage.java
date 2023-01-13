package com.cigi.facturation.exception;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@Data
@FieldDefaults( level = AccessLevel.PRIVATE)
@Builder
public class ErrorMessage {

    String Message;
    Date time;
    Integer code;
}
