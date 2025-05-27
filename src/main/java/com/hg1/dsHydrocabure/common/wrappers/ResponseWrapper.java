package com.hg1.dsHydrocabure.common.wrappers;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResponseWrapper<T>{
    private boolean status;
    private String message;
    private T entite;

    public static ResponseWrapper ko(String message) {
        return new ResponseWrapper(false, message, null);
    }

    public static <T> ResponseWrapper<T> ok(T entite) {
        return new ResponseWrapper(true, null, entite);
    }


}
